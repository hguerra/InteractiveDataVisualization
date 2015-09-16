package br.com.inpe.worldwind.model;

import gov.nasa.worldwind.layers.Layer;
import gov.nasa.worldwind.util.WWIO;
import gov.nasa.worldwindx.examples.util.OpenStreetMapShapefileLoader;
import gov.nasa.worldwindx.examples.util.ShapefileLoader;

import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @author Heitor
 * Class used to add shapefiles
 */
public class ShapefilesLayer {
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
