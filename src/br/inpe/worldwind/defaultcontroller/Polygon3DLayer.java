package br.inpe.worldwind.defaultcontroller;

import java.util.List;

import br.inpe.worldwind.controller.LayerController;
import br.inpe.worldwind.dao.GeometryRecord;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.awt.WorldWindowGLCanvas;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.ExtrudedPolygon;

public class Polygon3DLayer implements LayerController {
	private WorldWindowGLCanvas canvas;
	private List<GeometryRecord> geometryRecords;
	private RenderableLayer renderableLayer;

	public Polygon3DLayer(WorldWindowGLCanvas canvas, List<GeometryRecord> geometryRecords) {
		this.canvas = canvas;
		this.geometryRecords = geometryRecords;
		this.renderableLayer = new RenderableLayer();
	}

	@Override
	public void draw() {
		geometryRecords.forEach(geom -> {
			ExtrudedPolygon polygon = new ExtrudedPolygon(geom.getBorderPositions());
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
