package br.inpe.app;

import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.inpe.worldwind.engine.ShapefileProperties;
import gov.nasa.worldwind.formats.shapefile.Shapefile;
import gov.nasa.worldwind.layers.Layer;
import gov.nasa.worldwind.layers.RenderableLayer;

/**
 * 
 * @author Heitor
 */
public class LayerFactory {

	private static final String COLUMN_NAME = "attr";
	private ShapefileProperties properties;

	public LayerFactory() {
		properties = new ShapefileProperties();
	}

	/**
	 * Used in VegetationScenarioLayer
	 * 
	 * TODO
	 * 
	 * @param filePath
	 * @param interiorColor
	 */

	public RenderableLayer getRenderableLayer(String filePath, Color[] interiorColor1, Color[] interiorColor2) {
		Shapefile shape = createShapeFile(filePath);
		Shapefile shapeReverse = createShapeFile(filePath);
		Shapefile shapeColor = createShapeFile(filePath);
		int size = ShapefileProperties.getShapefileUniqueAttributes(shapeColor,COLUMN_NAME).size();
		Map<Object, Color> colors;
		if (size <= 7) {
			colors = properties.createPolygonColors(shape,COLUMN_NAME, interiorColor1);
		} else {
			colors = properties.createPolygonColors(shape, COLUMN_NAME, interiorColor2);
		}
		List<Layer> layers = new ArrayList<Layer>();

		properties.addRenderablesForPolygon(shapeReverse, null, COLUMN_NAME, layers, colors);
		return (RenderableLayer) layers.get(0);
	}

	/**
	 * End VegetationScenarioLayer used
	 */
	/**
	 * Create Shapefile
	 * 
	 * @param filepath
	 * @return
	 */

	private Shapefile createShapeFile(String filepath) {
		return new Shapefile(new File(filepath));
	}
}
