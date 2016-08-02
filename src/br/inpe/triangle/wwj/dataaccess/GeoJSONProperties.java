package br.inpe.triangle.wwj.dataaccess;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import gov.nasa.worldwind.avlist.AVList;
import gov.nasa.worldwind.exception.WWRuntimeException;
import gov.nasa.worldwind.formats.geojson.GeoJSONDoc;
import gov.nasa.worldwind.formats.geojson.GeoJSONGeometry;
import gov.nasa.worldwind.formats.geojson.GeoJSONLineString;
import gov.nasa.worldwind.formats.geojson.GeoJSONMultiLineString;
import gov.nasa.worldwind.formats.geojson.GeoJSONMultiPoint;
import gov.nasa.worldwind.formats.geojson.GeoJSONMultiPolygon;
import gov.nasa.worldwind.formats.geojson.GeoJSONObject;
import gov.nasa.worldwind.formats.geojson.GeoJSONPoint;
import gov.nasa.worldwind.formats.geojson.GeoJSONPolygon;
import gov.nasa.worldwind.formats.geojson.GeoJSONPositionArray;
import gov.nasa.worldwind.layers.Layer;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.BasicShapeAttributes;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.PointPlacemarkAttributes;
import gov.nasa.worldwind.render.ShapeAttributes;
import gov.nasa.worldwind.util.Logging;
import gov.nasa.worldwind.util.WWIO;
import gov.nasa.worldwind.util.WWUtil;
import gov.nasa.worldwindx.examples.GeoJSONLoader;

public class GeoJSONProperties extends GeoJSONLoader {
	private String attributeName;
	private Map<Object, Color> defaultAttributesColor;
	private Color defaultPointInternalColor;
	private Color defaultFontColor;
	private Font defaultFont;

	public GeoJSONProperties(String attributeName) {
		this.attributeName = attributeName;
		this.defaultAttributesColor = new HashMap<>();
		this.defaultPointInternalColor = Color.red;
		this.defaultFontColor = Color.white;
		this.defaultFont = Font.decode("Verdana-Bold-22");
	}

	/**
	 * Parse a GeoJSON document and add it to a layer.
	 */
	public List<GeoJSONObject> createGeoJSONObjectFromSource(Object docSource) {
		if (WWUtil.isEmpty(docSource)) {
			String message = Logging.getMessage("nullValue.SourceIsNull");
			Logging.logger().severe(message);
			throw new IllegalArgumentException(message);
		}
		List<GeoJSONObject> objects = new ArrayList<>();

		GeoJSONDoc doc = null;

		try {
			doc = new GeoJSONDoc(docSource);
			doc.parse();

			if (doc.getRootObject() instanceof GeoJSONObject) {
				objects.add((GeoJSONObject) doc.getRootObject());
			} else if (doc.getRootObject() instanceof Object[]) {
				for (Object o : (Object[]) doc.getRootObject()) {
					if (o instanceof GeoJSONObject) {
						objects.add((GeoJSONObject) o);
					} else {
						this.handleUnrecognizedObject(o);
					}
				}
			} else {
				this.handleUnrecognizedObject(doc.getRootObject());
			}
		} catch (IOException e) {
			String message = Logging.getMessage("generic.ExceptionAttemptingToReadGeoJSON", docSource);
			Logging.logger().log(Level.SEVERE, message, e);
			throw new WWRuntimeException(message, e);
		} finally {
			WWIO.closeStream(doc, docSource.toString());
		}

		return objects;
	}

	/**
	 * Create a layer from a GeoJSON document.
	 */
	public List<Layer> createLayersFromSource(Object docSource) {
		List<GeoJSONObject> geoObjects = this.createGeoJSONObjectFromSource(docSource);
		List<Layer> layers = new ArrayList<>();
		geoObjects.forEach(g -> {
			RenderableLayer layer = new RenderableLayer();
			this.addGeoJSONGeometryToLayer(g, layer);
			layers.add(layer);
		});
		return layers;
	}

	/**
	 * Create a layer from a GeoJSON object.
	 */
	@Override
	public Layer createLayerFromGeoJSON(GeoJSONObject object) {
		if (object == null) {
			String message = Logging.getMessage("nullValue.ObjectIsNull");
			Logging.logger().severe(message);
			throw new IllegalArgumentException(message);
		}

		RenderableLayer layer = new RenderableLayer();
		this.addGeoJSONGeometryToLayer(object, layer);
		return layer;
	}

	// **************************************************************//
	// ******************** Geometry Conversion *******************//
	// **************************************************************//
	@Override
	protected void addRenderableForGeometry(GeoJSONGeometry geom, RenderableLayer layer, AVList properties) {
		if (geom.isPoint())
			this.addRenderableForPoint(geom.asPoint(), layer, properties, getDefaultPointInternalColor(),
					getDefaultFontColor(), getDefaultFont());

		else if (geom.isMultiPoint())
			this.addRenderableForMultiPoint(geom.asMultiPoint(), layer, properties, getDefaultPointInternalColor(),
					getDefaultFontColor(), getDefaultFont());

		else if (geom.isLineString())
			this.addRenderableForLineString(geom.asLineString(), layer, properties, getDefaultAttributesColor());

		else if (geom.isMultiLineString())
			this.addRenderableForMutiLineString(geom.asMultiLineString(), layer, properties,
					getDefaultAttributesColor());

		else if (geom.isPolygon())
			this.addRenderableForPolygon(geom.asPolygon(), layer, properties, getDefaultAttributesColor());

		else if (geom.isMultiPolygon())
			this.addRenderableForMultiPolygon(geom.asMultiPolygon(), layer, properties, getDefaultAttributesColor());

		else if (geom.isGeometryCollection())
			this.addRenderableForGeometryCollection(geom.asGeometryCollection(), layer, properties);

		else
			this.handleUnrecognizedObject(geom);
	}

	protected void addRenderableForPoint(GeoJSONPoint geom, RenderableLayer layer, AVList properties,
			Color internalColor, Color fontColor, Font font) {
		PointPlacemarkAttributes attrs = this.createPointAttributes(internalColor, fontColor, font);
		layer.addRenderable(this.createPoint(geom, geom.getPosition(), attrs, properties));
	}

	protected void addRenderableForMultiPoint(GeoJSONMultiPoint geom, RenderableLayer layer, AVList properties,
			Color internalColor, Color fontColor, Font font) {
		PointPlacemarkAttributes attrs = this.createPointAttributes(internalColor, fontColor, font);
		for (int i = 0; i < geom.getPointCount(); i++) {
			layer.addRenderable(this.createPoint(geom, geom.getPosition(i), attrs, properties));
		}
	}

	protected void addRenderableForLineString(GeoJSONLineString geom, RenderableLayer layer, AVList properties,
			Map<Object, Color> colors) {
		ShapeAttributes attrs = this.createPolylineAttributes(geom, properties, colors);

		layer.addRenderable(this.createPolyline(geom, geom.getCoordinates(), attrs, properties));
	}

	protected void addRenderableForMutiLineString(GeoJSONMultiLineString geom, RenderableLayer layer, AVList properties,
			Map<Object, Color> colors) {
		ShapeAttributes attrs = this.createPolylineAttributes(geom, properties, colors);

		for (GeoJSONPositionArray coords : geom.getCoordinates()) {
			layer.addRenderable(this.createPolyline(geom, coords, attrs, properties));
		}
	}

	protected void addRenderableForPolygon(GeoJSONPolygon geom, RenderableLayer layer, AVList properties,
			Map<Object, Color> colors) {
		ShapeAttributes attrs = this.createPolylineAttributes(geom, properties, colors);

		layer.addRenderable(
				this.createPolygon(geom, geom.getExteriorRing(), geom.getInteriorRings(), attrs, properties));
	}

	protected void addRenderableForMultiPolygon(GeoJSONMultiPolygon geom, RenderableLayer layer, AVList properties,
			Map<Object, Color> colors) {
		ShapeAttributes attrs = this.createPolylineAttributes(geom, properties, colors);

		for (int i = 0; i < geom.getPolygonCount(); i++) {
			layer.addRenderable(
					this.createPolygon(geom, geom.getExteriorRing(i), geom.getInteriorRings(i), attrs, properties));
		}
	}

	// **************************************************************//
	// ******************** Attribute Construction ****************//
	// **************************************************************//

	protected PointPlacemarkAttributes createPointAttributes(Color internalColor, Color fontColor, Font font) {
		PointPlacemarkAttributes attrs = new PointPlacemarkAttributes();
		attrs.setImageColor(internalColor);
		attrs.setLabelFont(font);
		Material fontMaterial = new Material(fontColor);
		attrs.setLabelMaterial(fontMaterial);
		return attrs;
	}

	protected ShapeAttributes createPolylineAttributes(GeoJSONGeometry geom, AVList properties,
			Map<Object, Color> colors) {
		return this.createPolygonAttributes(geom, properties, colors);
	}

	protected ShapeAttributes createPolygonAttributes(GeoJSONGeometry geom, AVList properties,
			Map<Object, Color> colors) {
		ShapeAttributes attrs = new BasicShapeAttributes();
		attrs.setDrawOutline(false);

		Object key = properties.getValue(attributeName);

		if (key != null) {
			Material interior = new Material(colors.get((double) key));
			attrs.setInteriorMaterial(interior);
		}

		return attrs;
	}

	/**
	 * Default Methods
	 */
	public Color addAttributeColor(double key, Color value) {
		return this.defaultAttributesColor.put(key, value);
	}

	public Color removeAttributeColor(double key) {
		return this.defaultAttributesColor.remove(key);
	}

	public String getAttributeName() {
		return attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	public Map<Object, Color> getDefaultAttributesColor() {
		return defaultAttributesColor;
	}

	public void setDefaultAttributesColor(Map<Object, Color> defaultAttributesColor) {
		this.defaultAttributesColor = defaultAttributesColor;
	}

	public Color getDefaultPointInternalColor() {
		return defaultPointInternalColor;
	}

	public void setDefaultPointInternalColor(Color defaultPointInternalColor) {
		this.defaultPointInternalColor = defaultPointInternalColor;
	}

	public Color getDefaultFontColor() {
		return defaultFontColor;
	}

	public void setDefaultFontColor(Color defaultFontColor) {
		this.defaultFontColor = defaultFontColor;
	}

	public Font getDefaultFont() {
		return defaultFont;
	}

	public void setDefaultFont(Font defaultFont) {
		this.defaultFont = defaultFont;
	}

}
