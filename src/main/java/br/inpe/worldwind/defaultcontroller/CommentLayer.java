package br.inpe.worldwind.defaultcontroller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import br.inpe.worldwind.controller.LayerController;
import gov.nasa.worldwind.awt.WorldWindowGLCanvas;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.AnnotationLayer;
import gov.nasa.worldwind.render.GlobeAnnotation;
import gov.nasa.worldwindx.examples.util.PowerOfTwoPaddedImage;

public class CommentLayer implements LayerController {
	private WorldWindowGLCanvas canvas;
	private AnnotationLayer annotationLayer;
	private Position position;
	private Dimension dimension;
	private String text;
	private String image;
	private double imageScale;
	private Color borderColor;
	private Color backgroundColor;
	private double maxActiveAltitude = 1081941;

	public CommentLayer(WorldWindowGLCanvas canvas, Position position, Dimension dimension, String text, String image,
			double imageScale, Color borderColor, Color backgroundColor) {
		this.canvas = canvas;
		this.position = position;
		this.dimension = dimension;
		this.text = text;
		this.image = image;
		this.imageScale = imageScale;
		this.borderColor = borderColor;
		this.backgroundColor = backgroundColor;
		this.annotationLayer = new AnnotationLayer();
	}
	
	public CommentLayer(WorldWindowGLCanvas canvas, Position position, Dimension dimension, String text, String image,
			double imageScale, Color borderColor, Color backgroundColor, double maxActiveAltitude) {
		this.canvas = canvas;
		this.position = position;
		this.dimension = dimension;
		this.text = text;
		this.image = image;
		this.imageScale = imageScale;
		this.borderColor = borderColor;
		this.backgroundColor = backgroundColor;
		this.maxActiveAltitude = maxActiveAltitude;
		this.annotationLayer = new AnnotationLayer();
	}

	@Override
	public void draw() {
		PowerOfTwoPaddedImage pic = PowerOfTwoPaddedImage.fromPath(image);
		GlobeAnnotation ga = new GlobeAnnotation(text, position, Font.decode("Arial-BOLD-13"));

		ga.getAttributes().setImageSource(pic.getPowerOfTwoImage());
		ga.getAttributes().setBorderColor(borderColor);
		ga.getAttributes().setBackgroundColor(backgroundColor);
		ga.getAttributes().setBorderWidth(1);
		ga.getAttributes().setImageScale(imageScale);
		ga.getAttributes().setSize(dimension);
		ga.setAlwaysOnTop(true);

		ga.setMaxActiveAltitude(maxActiveAltitude);

		annotationLayer.addAnnotation(ga);

		LayerController.insertBeforeBeforeCompass(canvas, annotationLayer);
	}

	@Override
	public void remove() {
		LayerController.removeBeforeCompass(canvas, annotationLayer);
	}
	
	public void setMaxActiveAltitude(double maxActiveAltitude) {
		this.maxActiveAltitude = maxActiveAltitude;
	}

}
