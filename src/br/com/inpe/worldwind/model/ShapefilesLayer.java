package br.com.inpe.worldwind.model;

import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.layers.CompassLayer;
import gov.nasa.worldwind.layers.Layer;
import gov.nasa.worldwind.layers.LayerList;
import gov.nasa.worldwind.util.WWIO;
import gov.nasa.worldwindx.examples.util.OpenStreetMapShapefileLoader;
import gov.nasa.worldwindx.examples.util.ShapefileLoader;

import java.util.ArrayList;
import java.util.List;

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
		List<Layer> layers = makeShapefileLayers(path, layerName,
				pickEnabled);
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
		List<Layer> layers = new ArrayList<Layer>();
		if (OpenStreetMapShapefileLoader.isOSMPlacesSource(filepath)) {
			Layer layer = OpenStreetMapShapefileLoader
					.makeLayerFromOSMPlacesSource(filepath);
			layers = new ArrayList<Layer>();
			layers.add(layer);
		} else {
			ShapefileLoader loader = new ShapefileLoader();
			layers = loader.createLayersFromSource(filepath);
		}
		setLayersName(layers, layerName, pickEnabled);
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
}
