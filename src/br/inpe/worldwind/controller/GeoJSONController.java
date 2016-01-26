package br.inpe.worldwind.controller;

import java.awt.Color;
import java.util.Map;

import gov.nasa.worldwind.formats.geojson.GeoJSONObject;
import gov.nasa.worldwind.layers.Layer;

public interface GeoJSONController extends LayerController {
	void addGeoJSON(Layer layer);

	void addGeoJSON(String layerName, GeoJSONObject json, Map<Double, Color> colors);

	boolean addGeoJSON(String layerName, String filepath, Map<Double, Color> colors);
}
