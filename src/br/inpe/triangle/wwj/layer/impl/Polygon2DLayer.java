package br.inpe.triangle.wwj.layer.impl;

import java.util.List;

import br.inpe.triangle.postgis.GeometryRecord;
import br.inpe.triangle.wwj.layer.LayerController;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.awt.WorldWindowGLCanvas;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.Polygon;

public class Polygon2DLayer implements LayerController {
	private WorldWindowGLCanvas canvas;
	private List<GeometryRecord> geometryRecords;
	private RenderableLayer renderableLayer;

	public Polygon2DLayer(WorldWindowGLCanvas canvas, List<GeometryRecord> geometryRecords) {
		this.canvas = canvas;
		this.geometryRecords = geometryRecords;
		this.renderableLayer = new RenderableLayer();
	}

	@Override
	public void draw() {
		geometryRecords.forEach(geom -> {
			Polygon polygon = new Polygon(geom.getBorderPositions());
			polygon.setAttributes(geom.getSideAttributes());
			polygon.setValue(AVKey.DISPLAY_NAME, geom.getDisplayName());

			renderableLayer.addRenderable(polygon);

		});

		LayerController.insertBeforeCompass(canvas, renderableLayer);
	}

	@Override
	public void remove() {
		LayerController.removeBeforeCompass(canvas, renderableLayer);
	}

}
