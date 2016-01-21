package br.inpe.worldwind.defaultcontroller;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Point;

import br.inpe.worldwind.controller.LayerController;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.awt.WorldWindowGLCanvas;
import gov.nasa.worldwind.layers.AnnotationLayer;
import gov.nasa.worldwind.render.ScreenAnnotation;
import gov.nasa.worldwindx.examples.util.PowerOfTwoPaddedImage;

public class ScreenAnnotationLayer implements LayerController {
	private WorldWindowGLCanvas canvas;
	private int width;
	private int height;
	private String image;
	private Insets insets;
	private Dimension dimension;
	private AnnotationLayer annLayer;

	public ScreenAnnotationLayer(WorldWindowGLCanvas canvas, int width, int height, String image, Insets insets,
			Dimension dimension) {
		this.canvas = canvas;
		this.width = width;
		this.height = height;
		this.image = image;
		this.insets = insets;
		this.dimension = dimension;
		this.annLayer = new AnnotationLayer();
	}

	@Override
	public void draw() {
		try {
			PowerOfTwoPaddedImage pic = PowerOfTwoPaddedImage.fromPath(image);

			ScreenAnnotation logo = new ScreenAnnotation("", new Point(width, height));

			logo.getAttributes().setImageSource(pic.getPowerOfTwoImage());
			logo.getAttributes().setImageRepeat(AVKey.REPEAT_NONE);
			logo.getAttributes().setAdjustWidthToText(AVKey.SIZE_FIXED);
			logo.getAttributes().setDrawOffset(new Point(100, 0));
			logo.getAttributes().setHighlightScale(1);

			logo.getAttributes().setInsets(insets);
			logo.getAttributes().setSize(dimension);

			logo.getAttributes().setImageScale(0.22);
			logo.getAttributes().setImageOffset(new Point(10, 10));

			annLayer.addAnnotation(logo);

			LayerController.insertBeforeCompass(canvas, annLayer);

		} catch (Exception e) {
			System.err.println(e);
		}

	}

	@Override
	public void remove() {
		try {
			LayerController.removeBeforeCompass(canvas, annLayer);
		} catch (Exception e) {
			System.err.println(e);
		}
	}

}
