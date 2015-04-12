package br.com.inpe.interactivedatavisualization.worldwind;

import gov.nasa.worldwind.BasicModel;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.awt.WorldWindowGLCanvas;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.AnnotationLayer;
import gov.nasa.worldwind.layers.CompassLayer;
import gov.nasa.worldwind.layers.Layer;
import gov.nasa.worldwind.layers.LayerList;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.layers.Earth.CountryBoundariesLayer;
import gov.nasa.worldwind.render.ScreenAnnotation;
import gov.nasa.worldwindx.examples.util.PowerOfTwoPaddedImage;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;

import br.com.inpe.interactivedatavisualization.data.DataRetriever;
import br.com.inpe.interactivedatavisualization.kinect.view.KinectInternalFrame;

public class AmazonDeforestation {
	public static class AppFrame extends JFrame {
		
		
		
		RenderableLayer ama2002;
		RenderableLayer ama2003;
		RenderableLayer ama2004;
		RenderableLayer ama2005;
		RenderableLayer ama2006;
		RenderableLayer ama2007;
		RenderableLayer ama2008;

		RenderableLayer ama2002a;
		RenderableLayer ama2003a;
		RenderableLayer ama2004a;
		RenderableLayer ama2005a;
		RenderableLayer ama2006a;
		RenderableLayer ama2007a;
		RenderableLayer ama2008a;
		
		RenderableLayer ama2002b;
		RenderableLayer ama2003b;
		RenderableLayer ama2004b;
		RenderableLayer ama2005b;
		RenderableLayer ama2006b;
		RenderableLayer ama2007b;
		RenderableLayer ama2008b;
		
		AnnotationLayer anoLayer2002;
		AnnotationLayer anoLayer2003;
		AnnotationLayer anoLayer2004;
		AnnotationLayer anoLayer2005;
		AnnotationLayer anoLayer2006;
		AnnotationLayer anoLayer2007;
		AnnotationLayer anoLayer2008;

		AnnotationLayer anoLayer2002a;
		AnnotationLayer anoLayer2003a;
		AnnotationLayer anoLayer2004a;
		AnnotationLayer anoLayer2005a;
		AnnotationLayer anoLayer2006a;
		AnnotationLayer anoLayer2007a;
		AnnotationLayer anoLayer2008a;
		
		AnnotationLayer anoLayer2002b;
		AnnotationLayer anoLayer2003b;
		AnnotationLayer anoLayer2004b;
		AnnotationLayer anoLayer2005b;
		AnnotationLayer anoLayer2006b;
		AnnotationLayer anoLayer2007b;
		AnnotationLayer anoLayer2008b;
		
		AnnotationLayer generalAnoLayer2002;
		AnnotationLayer generalAnoLayer2003;
		AnnotationLayer generalAnoLayer2004;
		AnnotationLayer generalAnoLayer2005;
		AnnotationLayer generalAnoLayer2006;
		AnnotationLayer generalAnoLayer2007;
		AnnotationLayer generalAnoLayer2008;
		
		AnnotationLayer generalAnoLayer2002a;
		AnnotationLayer generalAnoLayer2003a;
		AnnotationLayer generalAnoLayer2004a;
		AnnotationLayer generalAnoLayer2005a;
		AnnotationLayer generalAnoLayer2006a;
		AnnotationLayer generalAnoLayer2007a;
		AnnotationLayer generalAnoLayer2008a;

		AnnotationLayer generalAnoLayer2002b;
		AnnotationLayer generalAnoLayer2003b;
		AnnotationLayer generalAnoLayer2004b;
		AnnotationLayer generalAnoLayer2005b;
		AnnotationLayer generalAnoLayer2006b;
		AnnotationLayer generalAnoLayer2007b;
		AnnotationLayer generalAnoLayer2008b;
		
		ScreenAnnotation logoAnnotation;
		
				
		boolean year2002 = false;
		
		String activeLayer = "c";
		ArrayList activeLayers = new ArrayList();

		private AppFrameController controller;

		private WorldWindowGLCanvas wwd;

		public WorldWindowGLCanvas getWwd() {
			return wwd;
		}

		public void setWwd(WorldWindowGLCanvas wwd) {
			this.wwd = wwd;
		}

		private KinectInternalFrame kinectHandler;

		private int height = 768;
		private int width = 1300;

		public static final double INITIAL_ZOOM = 2.3e7;
		public static final Position PARA_POS = Position.fromDegrees(-4.72826,
				-52.302247, 7000000);

		int layerChanger = 0;

		private JLayeredPane layeredPane;

		public AppFrame() {

			wwd = new WorldWindowGLCanvas();
			wwd.setPreferredSize(new java.awt.Dimension(width, height));
			wwd.setModel(new BasicModel());
			wwd.setBounds(0, 0, width + 1, height + 1); // +1 because without it
														// the camera image isnt
														// shown

			layeredPane = new JLayeredPane();
			layeredPane.setPreferredSize(new java.awt.Dimension(width, height));

			layeredPane.add(wwd, java.awt.BorderLayout.CENTER);

			layeredPane.setBounds(0, 0, width, height);

			layeredPane.doLayout();

			this.printLogo();
			
			addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent event) {
					event.getWindow().setVisible(false);
					event.getWindow().dispose();
					System.exit(0);
				}
			});
			this.setUndecorated(true);
			this.getContentPane()
					.add(layeredPane, java.awt.BorderLayout.CENTER);
			this.pack();
			this.setBounds(0, 0, width, height);


			this.controller = new AppFrameController(this);

			DataRetriever dataR = new DataRetriever();
			/*
			dataR.queryForStates();
			addGDPlayer(dataR); //economical

			addLanduseLayer(dataR); // ecological

			year2002 = true; // social
			addAcumPoplayer(dataR); // social
*/
			
			removeCompass(this.getWwd());

			initKinectHandler();


			
		}

		private void printLogo(){
			
			PowerOfTwoPaddedImage pic = PowerOfTwoPaddedImage.fromPath("images/DuB_Horizontal_PT.png");
			AnnotationLayer annLayer = new AnnotationLayer();
			
			ScreenAnnotation logoDWIH = new ScreenAnnotation("", new Point(780,530));

			logoDWIH.getAttributes().setImageSource(pic.getPowerOfTwoImage());
			logoDWIH.getAttributes().setImageRepeat(AVKey.REPEAT_NONE);
			logoDWIH.getAttributes().setAdjustWidthToText(AVKey.SIZE_FIXED);
			logoDWIH.getAttributes().setDrawOffset(new Point(100,0));
			logoDWIH.getAttributes().setHighlightScale(1);
			
			logoDWIH.getAttributes().setInsets(new Insets(0,40,0,0));
			logoDWIH.getAttributes().setSize(new Dimension(265,150));
			
			logoDWIH.getAttributes().setImageScale(0.22);
			logoDWIH.getAttributes().setImageOffset(new Point(10,10));
			
			annLayer.addAnnotation(logoDWIH);
			insertBeforeBeforeCompass(this.wwd, annLayer);
			
		}
		private void initKinectHandler() {
			//KinectInternalFrame(this)
			kinectHandler = new KinectInternalFrame();
			kinectHandler.setBounds(15, 585, 224, 168);
			layeredPane.add(kinectHandler, new Integer(
					JLayeredPane.DEFAULT_LAYER.intValue() + 1));
		}
		
	public static void removeCompass(WorldWindow wwd) {
		// Insert the layer into the layer list just before the compass.
		int compassPosition = 0;
		LayerList layers = wwd.getModel().getLayers();
		for (Layer l : layers) {
			if (l instanceof CompassLayer)
				compassPosition = layers.indexOf(l);
		}
		layers.remove(compassPosition);
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

	public static void main(String[] args) {
		AppFrame app = new AppFrame();
		app.setVisible(true);
		
		
	}
}
}
