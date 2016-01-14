package br.inpe.worldwind.layer;

import gov.nasa.worldwind.formats.shapefile.Shapefile;
import gov.nasa.worldwind.formats.shapefile.ShapefileRecord;
import gov.nasa.worldwind.layers.Layer;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.BasicShapeAttributes;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.Renderable;
import gov.nasa.worldwind.render.ShapeAttributes;
import gov.nasa.worldwind.util.Logging;
import gov.nasa.worldwindx.examples.util.ShapefileLoader;

import java.awt.Color;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class ShapefileProperties extends ShapefileLoader {
	/**
	 * AttributeName attr
	 */
	private static final String ATTRIBUTE_NAME = "attr";
	private HashMap<Renderable, Set<Entry<String, Object>>> atable = new HashMap<Renderable, Set<Entry<String, Object>>>();

	public Map<Double, Color> createPolygonColors(Shapefile shp,
			String attributeName, Color[] interiorMaterial) {
		Object[] variation = getShapefileUniqueAttributes(shp, attributeName)
				.toArray();
		Map<Double, Color> colors = new TreeMap<Double, Color>();
		if (variation.length <= interiorMaterial.length) {
			for (int i = 0; i < variation.length; i++) {
				double key = (double) variation[i];
				colors.put(key, interiorMaterial[i]);
			}
		} else if (variation.length > interiorMaterial.length) {
			for (int i = 0; i < interiorMaterial.length; i++) {
				double key = (double) variation[i];
				colors.put(key, interiorMaterial[i]);
			}
		}
		return colors;
	}

	public ShapeAttributes createPolygonAttributes(ShapefileRecord record,
			Map<Double, Color> colors) {
		ShapeAttributes attrs = new BasicShapeAttributes();
		double key = (double) record.getAttributes().getValue(ATTRIBUTE_NAME);
		Material interior = new Material(colors.get(key));
		attrs.setInteriorMaterial(interior);
		attrs.setDrawOutline(false);
		return attrs;
	}

	public void addRenderablesForPolygon(Shapefile shp, List<Layer> layers,
			Map<Double, Color> colors) {
		RenderableLayer layer = new RenderableLayer();
		layers.add(layer);
		int recordNumber = 0;
		while (shp.hasNext()) {

			try {
				ShapefileRecord record = shp.nextRecord();
				recordNumber = record.getRecordNumber();

				if (!Shapefile.isPolygonType(record.getShapeType()))
					continue;
				ShapeAttributes attrs = this.createPolygonAttributes(record,
						colors);
				this.createPolygon(record, attrs, layer);
				if (layer.getNumRenderables() > this.numPolygonsPerLayer) {
					layer = new RenderableLayer();
					layer.setEnabled(false);
					layers.add(layer);
				}
			} catch (Exception e) {
				Logging.logger()
						.warning(
								Logging.getMessage(
										"SHP.ExceptionAttemptingToConvertShapefileRecord",
										recordNumber, e));

			}
		}
	}

	protected void addRenderablesForPolylines(Shapefile shp,
			RenderableLayer layer) {
		while (shp.hasNext()) {
			ShapefileRecord record = shp.nextRecord();
			ShapeAttributes attrs = this.createPolylineAttributes(record);
			layer.addRenderable(this.createPolyline(record, attrs));

		}
	}

	protected Renderable createPolyline(ShapefileRecord record,
			ShapeAttributes attrs) {
		Renderable r = super.createPolyline(record, attrs);
		atable.put(r, record.getAttributes().getEntries());
		return r;
	}

	protected void createPolygon(ShapefileRecord record, ShapeAttributes attrs,
			RenderableLayer layer) {
		super.createPolygon(record, attrs, layer);
		java.util.Iterator<Renderable> iterator = layer.getRenderables()
				.iterator();
		while (iterator.hasNext()) {
			Renderable next = null;
			next = iterator.next();
			atable.put(next, record.getAttributes().getEntries());
		}
	}

	// /** Method for retrieving attributes associated with each Renderable

	public Set<Entry<String, Object>> getAttr(Renderable r, String attributeName) {
		Set<Entry<String, Object>> attrs = getAttr(r).stream()
				.filter(entries -> entries.getKey().equals(attributeName))
				.collect(Collectors.toSet());
		return attrs;
	}

	public Set<Object> getAttrValues(Renderable r) {
		Set<Object> attrs = getAttr(r).stream()
				.map(entries -> entries.getValue()).collect(Collectors.toSet());
		return attrs;
	}

	public Set<Object> getAttrValues(Renderable r, String attributeName) {
		Set<Object> attrs = getAttr(r, attributeName).stream()
				.map(entries -> entries.getValue()).collect(Collectors.toSet());
		return attrs;
	}

	public Set<Entry<String, Object>> getAttr(Renderable r) {
		return atable.get(r);
	}

	public static Set<Object> getShapefileUniqueAttributes(Shapefile shapefile,
			String attributeName) {
		Set<Object> values = new HashSet<Object>();
		while (shapefile.hasNext()) {
			ShapefileRecord r = shapefile.nextRecord();
			values.add(r.getAttributes().getValue(attributeName));
		}
		return values;
	}

	public static Set<Object> getShapefileUniqueAttributes(ShapefileRecord r,
			String attributeName) {
		Set<Object> found = r.getAttributes().getEntries().stream()
				.filter(entries -> entries.getKey().equals(attributeName))
				.collect(Collectors.toSet()).stream()
				.map(entries -> entries.getValue()).collect(Collectors.toSet());
		return found;
	}
}