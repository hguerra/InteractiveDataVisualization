package br.inpe.worldwind.defaultcontroller;

import java.util.List;
import java.util.Map;

import br.inpe.worldwind.controller.LayerController;
import gov.nasa.worldwind.awt.WorldWindowGLCanvas;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.PointPlacemark;

public class PointLayer implements LayerController {
	private WorldWindowGLCanvas canvas;
	private Map<String, List<Position>> positions;
	private RenderableLayer renderableLayer;

	public PointLayer(WorldWindowGLCanvas canvas, Map<String, List<Position>> positions) {
		this.canvas = canvas;
		this.positions = positions;
		this.renderableLayer = new RenderableLayer();
	}

	@Override
	public void draw() {
		positions.forEach((text, listOfPositions) -> {
			listOfPositions.forEach(p -> {
				PointPlacemark mark = new PointPlacemark(p);
				mark.setLabelText(text);
				renderableLayer.addRenderable(mark);
			});
		});
		LayerController.insertBeforeBeforeCompass(canvas, renderableLayer);
	}

	@Override
	public void remove() {
		LayerController.removeBeforeCompass(canvas, renderableLayer);
	}

}
