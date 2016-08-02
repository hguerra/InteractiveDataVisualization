package br.inpe.triangle.wwj.layer;

import java.util.Map;

import gov.nasa.worldwind.awt.WorldWindowGLCanvas;
import gov.nasa.worldwind.layers.AnnotationLayer;
import gov.nasa.worldwind.layers.Layer;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.view.orbit.OrbitView;

public interface Scenario {
	WorldWindowGLCanvas getWwd();

	OrbitView getView();

	void changeLayers();

	String getActiveLayer();

	int getLayerChanger();

	Map<Integer, RenderableLayer> getVegLayers();

	Map<Integer, AnnotationLayer> getAnnotationLayers();

	void setLayerChanger(int i);

	void refreshActiveLayers(Layer layer);
}
