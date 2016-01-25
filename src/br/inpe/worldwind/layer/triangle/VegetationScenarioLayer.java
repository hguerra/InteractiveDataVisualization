package br.inpe.worldwind.layer.triangle;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

import com.primesense.nite.UserTracker;

import br.com.kinect4j.device.DeviceConfig;
import br.inpe.message.properties.DefaultColors;
import br.inpe.message.properties.DefaultFilePath;
import br.inpe.worldwind.annotation.VegetationScenearioAnnotation;
import br.inpe.worldwind.controller.ScenarioController;
import br.kinect4j.view.KinectView;
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

public class VegetationScenarioLayer {
	@SuppressWarnings("serial")
	public static class VegetationScenarioApp extends JFrame {

		RenderableLayer veg2000;
		RenderableLayer veg2005;
		RenderableLayer veg2010;
		RenderableLayer veg2015;
		RenderableLayer veg2020;
		RenderableLayer veg2025;
		RenderableLayer veg2030;
		RenderableLayer veg2035;
		RenderableLayer veg2040;
		RenderableLayer veg2045;
		RenderableLayer veg2050;

		Map<Integer, RenderableLayer> vegLayers = new HashMap<Integer, RenderableLayer>();
		Map<Integer, AnnotationLayer> annotationLayers = new HashMap<Integer, AnnotationLayer>();

		ScreenAnnotation logoAnnotation;

		private LayerFactory shapefile;

		String activeLayer = "a";

		ArrayList activeLayers = new ArrayList();

		private ScenarioController controller;

		private WorldWindowGLCanvas wwd;

		private KinectView kinectHandler;

		private int height = 768;
		private int width = 1024;

		public static final double INITIAL_ZOOM = 2.3e7;
		public static final Position PARA_POS = Position.fromDegrees(-4.72826, -52.302247, 7000000);

		int layerChanger = 0;

		private JLayeredPane layeredPane;

		public VegetationScenarioApp() {

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
			this.getContentPane().add(layeredPane, java.awt.BorderLayout.CENTER);
			this.pack();
			this.setBounds(0, 0, width, height);

			this.controller = new ScenarioController(this);

			shapefile = new LayerFactory();

			/**
			 * Dados
			 */

			addVegetationLayer();
			/**
			 * Kinect
			 */
			removeCompass(this.getWwd());

			initKinectHandler();

		}

		private void printLogo() {
			PowerOfTwoPaddedImage pic = PowerOfTwoPaddedImage.fromPath("images/ccst-novo2.png");
			AnnotationLayer annLayer = new AnnotationLayer();

			ScreenAnnotation logoDWIH = new ScreenAnnotation("", new Point(780, 530));
			logoDWIH.getAttributes().setImageSource(pic.getPowerOfTwoImage());
			logoDWIH.getAttributes().setImageRepeat(AVKey.REPEAT_NONE);
			logoDWIH.getAttributes().setAdjustWidthToText(AVKey.SIZE_FIXED);
			logoDWIH.getAttributes().setDrawOffset(new Point(100, 0));
			logoDWIH.getAttributes().setHighlightScale(1);

			logoDWIH.getAttributes().setInsets(new Insets(0, 40, 0, 0));
			logoDWIH.getAttributes().setSize(new Dimension(265, 200));

			logoDWIH.getAttributes().setImageScale(0.22);
			logoDWIH.getAttributes().setImageOffset(new Point(10, 10));

			annLayer.addAnnotation(logoDWIH);
			insertBeforeBeforeCompass(this.wwd, annLayer);

		}

		public void addAnnotation() {
			VegetationScenearioAnnotation vegAnnotation = new VegetationScenearioAnnotation();

			annotationLayers.put(0, vegAnnotation.getTitleAnnotion(0));
			annotationLayers.put(1, vegAnnotation.getTitleAnnotion(1));
			annotationLayers.put(2, vegAnnotation.getTitleAnnotion(2));
			annotationLayers.put(3, vegAnnotation.getTitleAnnotion(3));
			annotationLayers.put(4, vegAnnotation.getTitleAnnotion(4));
			annotationLayers.put(5, vegAnnotation.getTitleAnnotion(5));
			annotationLayers.put(6, vegAnnotation.getTitleAnnotion(6));
			annotationLayers.put(7, vegAnnotation.getTitleAnnotion(7));
			annotationLayers.put(8, vegAnnotation.getTitleAnnotion(8));
			annotationLayers.put(9, vegAnnotation.getTitleAnnotion(9));
			annotationLayers.put(10, vegAnnotation.getTitleAnnotion(10));

			insertBeforeBeforeCompass(this.wwd, annotationLayers.get(0));
		}

		private void initKinectHandler() {
			DeviceConfig kinect = DeviceConfig.getInstance();

			if (!kinect.isDeviceConnected()) {
				JOptionPane.showMessageDialog(null, "No device is connected", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}

			kinect.startFirstDevice();

			UserTracker userTracker = UserTracker.create();

			kinectHandler = new KinectView(userTracker, this);

			kinectHandler.setBounds(15, 585, 224, 168);
			layeredPane.add(kinectHandler, new Integer(JLayeredPane.DEFAULT_LAYER.intValue() + 1));
		}

		/**
		 * Manuel
		 */

		public void addVegetationLayer() {
			veg2000 = shapefile.getRenderableLayer(DefaultFilePath.VEGTYPE_2000, DefaultColors.getOriginalColors1(),
					DefaultColors.getOriginalColors2());

			veg2005 = shapefile.getRenderableLayer(DefaultFilePath.VEGTYPE_2005, DefaultColors.getOriginalColors1(),
					DefaultColors.getOriginalColors2());

			veg2010 = shapefile.getRenderableLayer(DefaultFilePath.VEGTYPE_2010, DefaultColors.getOriginalColors1(),
					DefaultColors.getOriginalColors2());

			veg2015 = shapefile.getRenderableLayer(DefaultFilePath.VEGTYPE_2015, DefaultColors.getOriginalColors1(),
					DefaultColors.getOriginalColors2());

			veg2020 = shapefile.getRenderableLayer(DefaultFilePath.VEGTYPE_2020, DefaultColors.getOriginalColors1(),
					DefaultColors.getOriginalColors2());

			veg2025 = shapefile.getRenderableLayer(DefaultFilePath.VEGTYPE_2025, DefaultColors.getOriginalColors1(),
					DefaultColors.getOriginalColors2());

			veg2030 = shapefile.getRenderableLayer(DefaultFilePath.VEGTYPE_2030, DefaultColors.getOriginalColors1(),
					DefaultColors.getOriginalColors2());

			veg2035 = shapefile.getRenderableLayer(DefaultFilePath.VEGTYPE_2035, DefaultColors.getOriginalColors1(),
					DefaultColors.getOriginalColors2());

			veg2040 = shapefile.getRenderableLayer(DefaultFilePath.VEGTYPE_2040, DefaultColors.getOriginalColors1(),
					DefaultColors.getOriginalColors2());

			veg2045 = shapefile.getRenderableLayer(DefaultFilePath.VEGTYPE_2045, DefaultColors.getOriginalColors1(),
					DefaultColors.getOriginalColors2());

			veg2050 = shapefile.getRenderableLayer(DefaultFilePath.VEGTYPE_2050, DefaultColors.getOriginalColors1(),
					DefaultColors.getOriginalColors2());

			vegLayers.put(0, veg2000);
			vegLayers.put(1, veg2005);
			vegLayers.put(2, veg2010);
			vegLayers.put(3, veg2015);
			vegLayers.put(4, veg2020);
			vegLayers.put(5, veg2025);
			vegLayers.put(6, veg2030);
			vegLayers.put(7, veg2035);
			vegLayers.put(8, veg2040);
			vegLayers.put(9, veg2045);
			vegLayers.put(10, veg2050);

			addAnnotation();

			insertBeforeBeforeCompass(this.getWwd(), veg2000);

			activeLayers.add(veg2000);

			insertBeforeBeforeCompass(this.getWwd(), new CountryBoundariesLayer());

			controller.flyToPosition(PARA_POS, INITIAL_ZOOM);
		}

		public void refreshActiveLayers(RenderableLayer layer1) {
			activeLayers = new ArrayList();
			activeLayers.add(layer1);
		}

		public void refreshActiveLayers(RenderableLayer layer1, AnnotationLayer layer2, AnnotationLayer layer3) {
			activeLayers = new ArrayList();
			activeLayers.add(layer1);
			activeLayers.add(layer2);
			activeLayers.add(layer3);
		}

		public void changeLayers() {
			getWwd().getModel().getLayers().remove(activeLayers.get(0));
			activeLayers = new ArrayList();
			setLayerChanger(0);

			if (activeLayer == "a") {
				activeLayer = "b";
				getWwd().getModel().getLayers().add(getVegLayers().get(0));
				activeLayers.add(getVegLayers().get(0));
			} else if (activeLayer == "b") {
				activeLayer = "a";
				// dados ana paula
			}
		}

		/**
		 * Heitor getters and setters
		 * 
		 * @return
		 */

		public RenderableLayer getVeg2000() {
			return veg2000;
		}

		public Map<Integer, AnnotationLayer> getAnnotationLayers() {
			return annotationLayers;
		}

		public void setAnnotationLayers(Map<Integer, AnnotationLayer> annotationLayers) {
			this.annotationLayers = annotationLayers;
		}

		public Map<Integer, RenderableLayer> getVegLayers() {
			return vegLayers;
		}

		public void setVegLayers(Map<Integer, RenderableLayer> vegLayers) {
			this.vegLayers = vegLayers;
		}

		public void setVeg2000(RenderableLayer veg2000) {
			this.veg2000 = veg2000;
		}

		public RenderableLayer getVeg2005() {
			return veg2005;
		}

		public void setVeg2005(RenderableLayer veg2005) {
			this.veg2005 = veg2005;
		}

		public RenderableLayer getVeg2010() {
			return veg2010;
		}

		public void setVeg2010(RenderableLayer veg2010) {
			this.veg2010 = veg2010;
		}

		public RenderableLayer getVeg2015() {
			return veg2015;
		}

		public void setVeg2015(RenderableLayer veg2015) {
			this.veg2015 = veg2015;
		}

		public RenderableLayer getVeg2020() {
			return veg2020;
		}

		public void setVeg2020(RenderableLayer veg2020) {
			this.veg2020 = veg2020;
		}

		public RenderableLayer getVeg2025() {
			return veg2025;
		}

		public void setVeg2025(RenderableLayer veg2025) {
			this.veg2025 = veg2025;
		}

		public RenderableLayer getVeg2030() {
			return veg2030;
		}

		public void setVeg2030(RenderableLayer veg2030) {
			this.veg2030 = veg2030;
		}

		public RenderableLayer getVeg2035() {
			return veg2035;
		}

		public void setVeg2035(RenderableLayer veg2035) {
			this.veg2035 = veg2035;
		}

		public RenderableLayer getVeg2040() {
			return veg2040;
		}

		public void setVeg2040(RenderableLayer veg2040) {
			this.veg2040 = veg2040;
		}

		public RenderableLayer getVeg2045() {
			return veg2045;
		}

		public void setVeg2045(RenderableLayer veg2045) {
			this.veg2045 = veg2045;
		}

		public RenderableLayer getVeg2050() {
			return veg2050;
		}

		public void setVeg2050(RenderableLayer veg2050) {
			this.veg2050 = veg2050;
		}

		public ScreenAnnotation getLogoAnnotation() {
			return logoAnnotation;
		}

		public void setLogoAnnotation(ScreenAnnotation logoAnnotation) {
			this.logoAnnotation = logoAnnotation;
		}

		public LayerFactory getShapefile() {
			return shapefile;
		}

		public void setShapefile(LayerFactory shapefile) {
			this.shapefile = shapefile;
		}

		public ArrayList getActiveLayers() {
			return activeLayers;
		}

		public void setActiveLayers(ArrayList activeLayers) {
			this.activeLayers = activeLayers;
		}

		public KinectView getKinectHandler() {
			return kinectHandler;
		}

		public void setKinectHandler(KinectView kinectHandler) {
			this.kinectHandler = kinectHandler;
		}

		public int getHeight() {
			return height;
		}

		public void setHeight(int height) {
			this.height = height;
		}

		public int getWidth() {
			return width;
		}

		public void setWidth(int width) {
			this.width = width;
		}

		public JLayeredPane getLayeredPane() {
			return layeredPane;
		}

		public void setLayeredPane(JLayeredPane layeredPane) {
			this.layeredPane = layeredPane;
		}

		public static double getInitialZoom() {
			return INITIAL_ZOOM;
		}

		public static Position getParaPos() {
			return PARA_POS;
		}

		public void setController(ScenarioController controller) {
			this.controller = controller;
		}

		public void setWwd(WorldWindowGLCanvas wwd) {
			this.wwd = wwd;
		}

		/**
		 * @author Umut Tas
		 */
		public int getLayerChanger() {
			return layerChanger;
		}

		public void setLayerChanger(int layerChanger) {
			this.layerChanger = layerChanger;
		}

		public WorldWindowGLCanvas getWwd() {
			return wwd;
		}

		public ScenarioController getController() {
			return controller;
		}

		public String getActiveLayer() {
			return activeLayer;
		}

		public void setActiveLayer(String activeLayer) {
			this.activeLayer = activeLayer;
		}
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
		VegetationScenarioApp app = new VegetationScenarioApp();
		app.setVisible(true);

	}
}
