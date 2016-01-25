package br.inpe.worldwind.controller;

import java.util.List;

import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.layers.CompassLayer;
import gov.nasa.worldwind.layers.Layer;
import gov.nasa.worldwind.layers.LayerList;

public interface LayerController {
	void draw();

	void remove();
	
	default void asyncDraw() {
		new Thread(() -> draw()).start();
	}
	
	default void asyncRemove() {
		new Thread(() -> remove()).start();
	}
	
	
	public static void insertBeforeCompass(WorldWindow wwd, Layer layer) {
		// Insert the layer into the layer list just before the compass.
		int compassPosition = 0;
		LayerList layers = wwd.getModel().getLayers();
		for (Layer l : layers) {
			if (l instanceof CompassLayer)
				compassPosition = layers.indexOf(l);
		}
		layers.add(compassPosition, layer);
	}

	public static void insertBeforeBeforeCompass(WorldWindow wwd, Layer layer) {
		// Insert the layer into the layer list just before the compass.
		int compassPosition = 0;
		LayerList layers = wwd.getModel().getLayers();
		for (Layer l : layers) {
			if (l instanceof CompassLayer)
				compassPosition = layers.indexOf(l);
		}
		layers.add(compassPosition - 1, layer);
	}
	
	public static void insertBeforeCompass(WorldWindow wwd, List<Layer> layers){
		for(Layer l: layers){
			insertBeforeCompass(wwd, l);
		}
		wwd.redraw();
	}
	
	public static void removeAllLayersCompass(WorldWindow wwd) {
		// Insert the layer into the layer list just before the compass.
		int compassPosition = 0;
		LayerList layers = wwd.getModel().getLayers();
		for (Layer l : layers) {
			if (l instanceof CompassLayer)
				compassPosition = layers.indexOf(l);
		}
		layers.remove(compassPosition);
	}
	
	public static void removeBeforeCompass(WorldWindow wwd, Layer layer){
		wwd.getModel().getLayers().remove(layer);
		wwd.redraw();
	}
	public static void removeBeforeCompass(WorldWindow wwd, String layerName){
		List<Layer> allLayers =  wwd.getModel().getLayers();
		for (Layer layer : allLayers) {
			if (layer.getName().equals(layerName)) {
				allLayers.remove(layer);
			}
		}
		wwd.redraw();
	}
	
	public static void removeBeforeCompass(WorldWindow wwd, List<Layer> layers){
		for (Layer l : layers) {
			wwd.getModel().getLayers().remove(l);
		}
		wwd.redraw();
	}

}
