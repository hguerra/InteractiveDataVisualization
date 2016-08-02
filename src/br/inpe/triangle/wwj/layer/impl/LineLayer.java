package br.inpe.triangle.wwj.layer.impl;

import java.awt.Color;
import java.util.List;

import br.inpe.triangle.wwj.layer.LayerController;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.awt.WorldWindowGLCanvas;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.Polyline;
import gov.nasa.worldwind.util.UnitsFormat;
import gov.nasa.worldwind.util.measure.MeasureTool;
import gov.nasa.worldwind.util.measure.MeasureToolController;

public class LineLayer implements LayerController {
	private WorldWindowGLCanvas canvas;
	private int lineWidth;
	private List<Position> positions;
	private String displayName;
	private Color backgroundColor;
	private MeasureTool measure;

	private RenderableLayer layer;

	public LineLayer(WorldWindowGLCanvas canvas, int lineWidth, List<Position> positions, String displayName,
			Color backgroundColor, MeasureTool measureTool) {
		this.canvas = canvas;
		this.lineWidth = lineWidth;
		this.positions = positions;
		this.displayName = displayName;
		this.backgroundColor = backgroundColor;
		this.measure = measureTool;
		this.layer = new RenderableLayer();
	}

	public LineLayer(WorldWindowGLCanvas canvas, int lineWidth, List<Position> positions, String displayName,
			Color backgroundColor) {
		this(canvas, lineWidth, positions, displayName, backgroundColor, new MeasureTool(canvas));
	}

	@Override
	public void draw() {
		Polyline polyline = new Polyline(positions, 3e4);
		polyline.setColor(backgroundColor);
		polyline.setLineWidth(lineWidth);
		polyline.setValue(AVKey.DISPLAY_NAME, displayName);
		layer.addRenderable(polyline);

		LayerController.insertBeforeCompass(canvas, layer);

		measure.setController(new MeasureToolController());
		measure.getUnitsFormat().setLengthUnits(UnitsFormat.KILOMETERS);
		measure.setFollowTerrain(true);
		measure.setMeasureShape(new Polyline(positions));
	}

	@Override
	public void remove() {
		LayerController.removeBeforeCompass(canvas, layer);

	}

	public MeasureTool getMeasureTool() {
		return measure;
	}

}
