package br.inpe.worldwind.defaultcontroller;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.inpe.worldwind.controller.LayerController;
import br.inpe.worldwind.controller.ShapefileController;
import br.inpe.worldwind.engine.ShapefileProperties;
import gov.nasa.worldwind.awt.WorldWindowGLCanvas;
import gov.nasa.worldwind.formats.shapefile.Shapefile;
import gov.nasa.worldwind.layers.Layer;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.util.WWIO;

public class ShapefileLayer implements ShapefileController {
	private WorldWindowGLCanvas canvas;
	private ShapefileProperties properties;
	private List<Layer> layers;
	private String attributeName;

	public ShapefileLayer(WorldWindowGLCanvas canvas, String attributeName) {
		this.canvas = canvas;
		this.properties = new ShapefileProperties(attributeName);
		this.layers = new ArrayList<>();
		this.attributeName = attributeName;
	}

	@Override
	public void draw() {
		if (layers.isEmpty())
			return;
		LayerController.insertBeforeCompass(canvas, layers);
	}

	@Override
	public void remove() {
		LayerController.removeBeforeCompass(canvas, layers);
	}

	@Override
	public String getDisplayName(Object source) {
		String name = WWIO.getSourcePath(source);
		if (name != null)
			name = WWIO.getFilename(name);
		if (name == null) {
			name = new StringBuilder().append("Shapefile-").append(layers.size()).toString();
		}
		return name;
	}

	@Override
	public List<Layer> shapefile2Layers(String layerName, Shapefile shapefile, Map<Double, Color> colors) {
		List<Layer> layers = new ArrayList<>();
		properties.addRenderablesForPolygon(shapefile, layerName, layers, colors);
		return layers;
	}

	@Override
	public RenderableLayer shapefile2RenderableLayer(String layerName, Shapefile shapefile, Map<Double, Color> colors) {
		return (RenderableLayer) shapefile2Layers(layerName, shapefile, colors).get(0);
	}

	@Override
	public void addShapefile(Layer layer) {
		this.layers.add(layer);
	}

	@Override
	public void addShapefile(String layerName, Shapefile shapefile, Map<Double, Color> colors) {
		RenderableLayer layer = shapefile2RenderableLayer(layerName, shapefile, colors);
		addShapefile(layer);
	}

	@Override
	public boolean addShapefile(String filepath, Color... c) {
		boolean success = true;
		try {
			String layerName = getDisplayName(filepath);
			Shapefile shpColors = createShapefile(filepath);
			Shapefile shapefile = createShapefile(filepath);
			Map<Double, Color> colors = properties.createPolygonColors(shpColors, attributeName, c);
			addShapefile(layerName, shapefile, colors);
		} catch (Exception e) {
			success = false;
			System.err.println(e);
		}
		return success;
	}

	public List<Layer> getLayers() {
		return layers;
	}

}
