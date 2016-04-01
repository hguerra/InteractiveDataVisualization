package br.inpe.worldwind.defaultcontroller;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.inpe.worldwind.controller.GeoJSONController;
import br.inpe.worldwind.controller.LayerController;
import br.inpe.worldwind.engine.GeoJSONProperties;
import gov.nasa.worldwind.awt.WorldWindowGLCanvas;
import gov.nasa.worldwind.formats.geojson.GeoJSONObject;
import gov.nasa.worldwind.layers.Layer;

public class GeoJSONLayer implements GeoJSONController {
	private WorldWindowGLCanvas canvas;
	private GeoJSONProperties properties;
	private List<Layer> layers;

	public GeoJSONLayer(WorldWindowGLCanvas canvas, String attributeName) {
		this.canvas = canvas;
		this.properties = new GeoJSONProperties(attributeName);
		this.layers = new ArrayList<>();
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
	public void addGeoJSON(Layer layer) {
		this.layers.add(layer);

	}

	@Override
	public void addGeoJSON(String layerName, GeoJSONObject json, Map<Object, Color> colors) {
		properties.setDefaultAttributesColor(colors);
		Layer layer = properties.createLayerFromGeoJSON(json);
		layer.setName(layerName);
		addGeoJSON(layer);
	}

	@Override
	public boolean addGeoJSON(String layerName, String filepath, Map<Object, Color> colors) {
		boolean success = true;
		try {
			properties.setDefaultAttributesColor(colors);
			List<Layer> jsonLayers = properties.createLayersFromSource(filepath);

			if (jsonLayers.isEmpty()) {
				success = false;
			} else if (jsonLayers.size() == 1) {
				Layer layer = jsonLayers.get(0);
				layer.setName(layerName);
				addGeoJSON(layer);
			} else {
				jsonLayers.forEach(layer -> {
					layer.setName(layerName);
				});
			}
		} catch (Exception e) {
			success = false;
			System.err.println(e);
		}
		return success;
	}

}
