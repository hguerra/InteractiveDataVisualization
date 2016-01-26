package br.inpe.worldwind.controller;

import java.awt.Color;
import java.io.File;
import java.util.List;
import java.util.Map;

import gov.nasa.worldwind.formats.shapefile.Shapefile;
import gov.nasa.worldwind.layers.Layer;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.AbstractSurfaceShape;
import gov.nasa.worldwind.render.BasicShapeAttributes;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.Renderable;
import gov.nasa.worldwind.render.ShapeAttributes;

public interface ShapefileController extends LayerController {

	String getDisplayName(Object source);

	List<Layer> shapefile2Layers(String layerName, Shapefile shapefile, Map<Double, Color> colors);

	RenderableLayer shapefile2RenderableLayer(String layerName, Shapefile shapefile, Map<Double, Color> colors);

	void addShapefile(Layer layer);

	void addShapefile(String layerName, Shapefile shapefile, Map<Double, Color> colors);

	boolean addShapefile(String filepath, Color... colors);

	default Shapefile createShapefile(String filepath) throws Exception {
		return new Shapefile(new File(filepath));
	}

	default void setRenderableLayerColor(RenderableLayer layer, Color interiorColor) {
		java.util.Iterator<Renderable> iterator = layer.getRenderables().iterator();
		while (iterator.hasNext()) {
			Renderable next = null;
			next = iterator.next();
			if (next instanceof AbstractSurfaceShape) {
				ShapeAttributes attr = new BasicShapeAttributes();
				Material interior = new Material(interiorColor);
				attr.setInteriorMaterial(interior);
				attr.setDrawOutline(false);
				((AbstractSurfaceShape) next).setAttributes(attr);
			}
		}
	}

}