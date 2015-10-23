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
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.Renderable;
import gov.nasa.worldwind.render.ShapeAttributes;
import gov.nasa.worldwind.util.VecBuffer;
import gov.nasa.worldwind.util.WWIO;
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

	/**
	 * Important Method
	 * 
	 * @param wwd
	 * @param layer
	 */
	protected void insertLayerListBeforeCompass(List layers) {
		for (Object i : layers) {
			Layer l = (Layer) i;
			insertBeforeCompass(wwd, l);
		}
		wwd.redraw();
	}

	public void addLayer(Layer layer) {
		wwd.getModel().getLayers().add(layer);
		wwd.setModel(wwd.getModel());
		wwd.redraw();
	}

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

	public String makeDisplayName(Object source) {
		String name = WWIO.getSourcePath(source);
		if (name != null)
			name = WWIO.getFilename(name);
		if (name == null)
			name = "Shapefile";
		return name;
	}

	/**
	 * Insert Layer
	 * 
	 * @param layers
	 */
	public void insertLayerBeforeCompass(List<Layer> layers) {
		insertLayerListBeforeCompass(layers);
	}

	public void insertLayerBeforeCompass(String path, String layerName,
			boolean pickEnabled) {
		List<Layer> layers = createLayersFromSource(path, layerName,
				pickEnabled);
		insertLayerBeforeCompass(layers);
	}

	public void insertLayerBeforeCompass(String path) {
		List<Layer> layers = createLayersFromSource(path);
		insertLayerBeforeCompass(layers);
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
	 * Insert RenderableLayer
	 * 
	 * @param filePath
	 * @param interiorColor
	 * @param outLineColor
	 */
	public void insertRenderableLayer(String filePath, Color interiorColor,
			Color outLineColor) {
		Shapefile shape = createShapeFile(filePath);
		RenderableLayer layer = createRenderableLayer(shape);
		setRenderableLayerColor(layer, interiorColor, outLineColor);
		addLayer(layer);
	}

	public void insertRenderableLayer(String filePath) {
		Shapefile shape = createShapeFile(filePath);
		RenderableLayer layer = createRenderableLayer(shape);
		addLayer(layer);
	}

	public void insertRenderableLayerBeforeCompass(String filePath) {
		List<RenderableLayer> layers = createRenderableLayersFromSource(filePath);
		insertLayerListBeforeCompass(layers);
	}

	public void insertRenderableLayerBeforeCompass(String filePath,
			String layerName, boolean pickEnabled) {
		List<RenderableLayer> layers = createRenderableLayersFromSource(
				filePath, layerName, pickEnabled);
		insertLayerListBeforeCompass(layers);
	}

	/**
	 * Create a layer
	 * 
	 * @param filepath
	 * @return
	 */

	public List<Layer> createLayersFromSource(String filepath) {
		return new ShapefileLoader().createLayersFromSource(filepath);

	}

	public List<Layer> createLayersFromSource(String filepath,
			String layerName, boolean pickEnabled) {
		List<Layer> layers = createLayersFromSource(filepath);
		setLayersName(layers, layerName, pickEnabled);
		return layers;
	}

	protected void setLayersName(List layers, String makeDisplayName,
			boolean pickEnabled) {
		for (int i = 0; i < layers.size(); i++) {
			Layer l = (Layer) layers.get(i);
			l.setName(i == 0 ? makeDisplayName : makeDisplayName + "-"
					+ Integer.toString(i));
			l.setPickEnabled(pickEnabled);
		}
	}

	/**
	 * Create a RenderableLayer
	 * 
	 * @param filepath
	 * @return
	 */
	public List<RenderableLayer> createRenderableLayersFromSource(
			String filepath) {
		List<RenderableLayer> layers = new ArrayList<RenderableLayer>();
		RenderableLayer layer = createRenderableLayer(filepath);
		layers.add(layer);
		return layers;
	}

	public List<RenderableLayer> createRenderableLayersFromSource(
			String filepath, String layerName, boolean pickEnabled) {
		List<RenderableLayer> layers = createRenderableLayersFromSource(filepath);
		setLayersName(layers, layerName, pickEnabled);
		return layers;
	}

	public Shapefile createShapeFile(String filepath) {
		return new Shapefile(new File(filepath));
	}

	public RenderableLayer createRenderableLayer(Shapefile shapeFile) {
		return (RenderableLayer) new ShapefileLoader()
				.createLayerFromShapefile(shapeFile);
	}

	public RenderableLayer createRenderableLayer(String filepath) {
		return createRenderableLayer(createShapeFile(filepath));
	}

	/**
	 * Set RenderableLayerColor
	 * 
	 * @param layer
	 * @param interiorColor
	 * @param outLineColor
	 */
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
