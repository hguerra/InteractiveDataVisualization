package br.com.inpe.worldwind.model;

import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.formats.shapefile.Shapefile;
import gov.nasa.worldwind.formats.shapefile.ShapefileRecord;
import gov.nasa.worldwind.geom.LatLon;
import gov.nasa.worldwind.layers.CompassLayer;
import gov.nasa.worldwind.layers.Layer;
import gov.nasa.worldwind.layers.LayerList;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.AbstractSurfaceShape;
import gov.nasa.worldwind.render.BasicShapeAttributes;
import gov.nasa.worldwind.render.ExtrudedPolygon;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.Polygon;
import gov.nasa.worldwind.render.Renderable;
import gov.nasa.worldwind.render.ShapeAttributes;
import gov.nasa.worldwind.util.VecBuffer;
import gov.nasa.worldwind.util.WWIO;
import gov.nasa.worldwindx.examples.util.OpenStreetMapShapefileLoader;
import gov.nasa.worldwindx.examples.util.ShapefileLoader;

import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author Heitor Class used to add shapefiles
 */
public class ShapefilesLayer {
	private WorldWindow wwd;

	public ShapefilesLayer(WorldWindow wwd) {
		this.wwd = wwd;
	}

	public void insertLayer(List<Layer> layers) {
		for (Layer l : layers) {
			insertBeforeCompass(wwd, l);
		}
		wwd.redraw();
	}

	public void insertLayer(String path, String layerName, boolean pickEnabled) {
		List<Layer> layers = makeShapefileLayers(path, layerName, pickEnabled);
		insertLayer(layers);
	}

	public void insertLayer(String path) {
		List<Layer> layers = makeShapefileLayers(path);
		insertLayer(layers);
	}

	public void removeLayer(String layerName) {
		List<Layer> toRemoveLayers = wwd.getModel().getLayers();
		for (Layer l : toRemoveLayers) {
			if (l.getName().equals(layerName)) {
				toRemoveLayers.remove(l);
			}

		}
		wwd.redraw();
	}

	public void removeLayer() {
		removeLayer("Renderable");
	}

	/**
	 * Important Method
	 * 
	 * @param wwd
	 * @param layer
	 */
	public void insertBeforeCompass(WorldWindow wwd, Layer layer) {
		// Insert the layer into the layer list just before the compass.
		int compassPosition = 0;
		LayerList layers = wwd.getModel().getLayers();
		for (Layer l : layers) {
			if (l instanceof CompassLayer)
				compassPosition = layers.indexOf(l);
		}
		layers.add(compassPosition, layer);
	}

	public void removeCompassLayer(WorldWindow wwd) {
		int compassPosition = 0;
		LayerList toRemoveLayers = wwd.getModel().getLayers();
		for (Layer l : toRemoveLayers) {
			if (l instanceof CompassLayer)
				compassPosition = toRemoveLayers.indexOf(l);
		}
		toRemoveLayers.remove(compassPosition);
	}

	/**
	 * Create a layer
	 * 
	 * @param filepath
	 * @return
	 */
	public List<Layer> makeShapefileLayers(String filepath) {
		if (OpenStreetMapShapefileLoader.isOSMPlacesSource(filepath)) {
			Layer layer = OpenStreetMapShapefileLoader
					.makeLayerFromOSMPlacesSource(filepath);
			List<Layer> layers = new ArrayList<Layer>();
			layers.add(layer);
			return layers;
		} else {
			ShapefileLoader loader = new ShapefileLoader();
			return loader.createLayersFromSource(filepath);
		}
	}

	public List<Layer> makeShapefileLayers(String filepath, String layerName,
			boolean pickEnabled) {
		List<Layer> layers = makeShapefileLayers(filepath);
		setLayersName(layers, layerName, pickEnabled);
		return layers;
	}
	
	public List<RenderableLayer> makeShapefileRenderableLayers(String filepath) {
		List<Layer> layers = makeShapefileLayers(filepath);
		List<RenderableLayer> renderableLayers = new ArrayList<RenderableLayer>();
		for(Layer l: layers){
			renderableLayers.add((RenderableLayer)l);
		}
		return renderableLayers;
	}
	
	public List<RenderableLayer> makeShapefileRenderableLayers(String filepath, String layerName,
			boolean pickEnabled) {
		List<RenderableLayer> layers = makeShapefileRenderableLayers(filepath);
		//setLayersName(layers, layerName, pickEnabled);
		return layers;
	}

	public void setLayersName(List<Layer> layers, String makeDisplayName,
			boolean pickEnabled) {
		for (int i = 0; i < layers.size(); i++) {
			layers.get(i).setName(
					i == 0 ? makeDisplayName : makeDisplayName + "-"
							+ Integer.toString(i));
			layers.get(i).setPickEnabled(pickEnabled);
		}
	}

	public String makeDisplayName(Object source) {
		String name = WWIO.getSourcePath(source);
		if (name != null)
			name = WWIO.getFilename(name);
		if (name == null)
			name = "Shapefile";
		return name;
	}
	
	public Shapefile createShapeFile(String filePath) {
		return new Shapefile(new File(filePath));
	}

	public RenderableLayer createRenderableLayer(Shapefile shapeFile) {
		return (RenderableLayer) new ShapefileLoader()
				.createLayerFromShapefile(shapeFile);
	}

	public void setRenderableLayerColor(RenderableLayer layer,
			Color interiorColor, Color outLineColor) {
		java.util.Iterator<Renderable> iterator = layer.getRenderables()
				.iterator();

		while (iterator.hasNext()) {
			Renderable next = null;
			next = iterator.next();

			if (next instanceof AbstractSurfaceShape) {
				ShapeAttributes attr = new BasicShapeAttributes();
				Material interior = new Material(interiorColor);
				Material outLine = new Material(outLineColor);
				attr.setInteriorMaterial(interior);
				attr.setOutlineMaterial(outLine);
				((AbstractSurfaceShape) next).setAttributes(attr);
			}
		}
	}
	
	public void insertRenderableLayer(String filePath, Color interiorColor, Color outLineColor){
		Shapefile shape = createShapeFile(filePath);
		RenderableLayer layer = createRenderableLayer(shape);
		setRenderableLayerColor(layer, interiorColor, outLineColor);
		
		wwd.getModel().getLayers().add(layer);
		wwd.setModel(wwd.getModel());
		wwd.redraw();
	}
	
	
	public void insertRenderableLayer(String filePath){
		List<Layer> layers = makeShapefileLayers(filePath);
		List<RenderableLayer> renderableLayers = new ArrayList<RenderableLayer>();
		for(Layer l: layers){
			renderableLayers.add((RenderableLayer)l);
		}
		
	}
	
	public void insertBeforeCompass(WorldWindow wwd, RenderableLayer layer) {
		// Insert the layer into the layer list just before the compass.
		int compassPosition = 0;
		LayerList layers = wwd.getModel().getLayers();
		for (Layer l : layers) {
			if (l instanceof CompassLayer)
				compassPosition = layers.indexOf(l);
		}
		layers.add(compassPosition, layer);
	}
	
	protected RenderableLayer LayerToRenderableLayer(Layer layer) {
		RenderableLayer renderableLayer = (RenderableLayer) layer;
		return renderableLayer;
	}
	
	/**
	 * TO TEST
	 */
	public RenderableLayer createRenderableLayerFromShapefile(File file) {
		Shapefile sf = new Shapefile(file);
		final RenderableLayer layer = new RenderableLayer();
		try {
			while (sf.hasNext()) {
				ShapefileRecord r = sf.nextRecord();
				if (r == null)
					continue;
				// printShapefileInfo(r);
				if (r.getNumberOfPoints() < 4)
					continue;

				layer.addRenderable(this.makeShape(r));
			}
		} finally {
			sf.close();
		}
		return layer;
	}

	public ExtrudedPolygon makeShape(ShapefileRecord record) {
		String[] heightKeys = new String[] { "height", "Height", "HEIGHT" };
		Double height = null;
		for (String key : heightKeys) {
			Object o = record.getAttributes().getValue(key);
			if (o != null) {
				height = Double.parseDouble(o.toString());
			}
		}

		ExtrudedPolygon pgon = new ExtrudedPolygon();
		VecBuffer vb = record.getPointBuffer(0);
		pgon.setOuterBoundary(vb.getLocations(), height);

		return pgon;
	}

	public static void printShapefileInfo(ShapefileRecord r) {
		System.out.printf("%d, %s: %d parts, %d points", r.getRecordNumber(),
				r.getShapeType(), r.getNumberOfParts(), r.getNumberOfPoints());
		for (Map.Entry<String, Object> a : r.getAttributes().getEntries()) {
			if (a.getKey() != null)
				System.out.printf(", %s", a.getKey());
			if (a.getValue() != null)
				System.out.printf(", %s", a.getValue());
		}
		System.out.println();

		System.out.print("\tAttributes: ");
		for (Map.Entry<String, Object> entry : r.getAttributes().getEntries()) {
			System.out.printf("%s = %s, ", entry.getKey(), entry.getValue());
		}
		System.out.println();

		VecBuffer vb = r.getPointBuffer(0);
		for (LatLon ll : vb.getLocations()) {
			System.out.printf("\t%f, %f\n", ll.getLatitude().degrees,
					ll.getLongitude().degrees);
		}
	}

}
