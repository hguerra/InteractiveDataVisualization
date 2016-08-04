package br.inpe.triangle.app2;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;

import com.primesense.nite.UserTracker;

import br.com.kinect4j.device.DeviceConfig;
import br.inpe.triangle.data.DataSource;
import br.inpe.triangle.defaultproperties.DefaultDataSource;
import br.inpe.triangle.wwj.layer.LayerController;
import br.inpe.triangle.wwj.layer.WorldWindController;
import br.inpe.triangle.wwj.layer.impl.ScreenAnnotationLayer;
import gov.nasa.worldwind.BasicModel;
import gov.nasa.worldwind.awt.WorldWindowGLCanvas;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.Layer;

public class ScenarioLayer {
	public static class ScenarioLayerFrame extends JFrame implements Runnable {
		private static final long serialVersionUID = 1L;
		public static final double INITIAL_ZOOM = 2.3e7;
		public static final Position PARA_POS = Position.fromDegrees(-4.72826, -52.302247, 7000000);
		private final int HEIGHT = 768;
		private final int WIDTH = 1024;

		private JLayeredPane layeredPane;
		private WorldWindowGLCanvas wwd;
		private WorldWindController controller;

		private DatasetController datasetController = DatasetController.getInstance();
		private HashSet<Layer> activeLayers = new HashSet<Layer>();

		private KinectLayer kinectHandler;

		public ScenarioLayerFrame() {
			// create canvas
			wwd = new WorldWindowGLCanvas();
			wwd.setPreferredSize(new java.awt.Dimension(WIDTH, HEIGHT));
			wwd.setModel(new BasicModel());
			wwd.setBounds(0, 0, WIDTH + 1, HEIGHT + 1); // +1 because without it
														// the camera image isnt
														// shown
			// KeyListener
			wwd.addKeyListener(new TriangleKeyListener(this));

			// create pane
			layeredPane = new JLayeredPane();
			layeredPane.setPreferredSize(new java.awt.Dimension(WIDTH, HEIGHT));
			layeredPane.add(wwd, java.awt.BorderLayout.CENTER);
			layeredPane.setBounds(0, 0, WIDTH, HEIGHT);
			layeredPane.doLayout();

			// add logo
			addLogo();

			// add frame events
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
			this.setBounds(0, 0, WIDTH, HEIGHT);

			// add WorldWindController
			controller = new WorldWindControllerImpl(getWwd());

			// load dataset
			try {
				loadDataset();
			} catch (Exception e) {
				System.err.println("Cannot load dataset");
			}

			// refresh compass
			LayerController.removeAllLayersCompass(getWwd());

			// init kinect
			initKinect();
		}

		private void addLogo() {
			LayerController screenAnnotation = new ScreenAnnotationLayer(wwd, 780, 530, "images/ccst-novo2.png",
					new Insets(0, 40, 0, 0), new Dimension(265, 200));
			screenAnnotation.asyncDraw();
		}

		private void loadDataset() throws Exception {
			// Create RenderableLayer instance
			Map<String, DataSource> datasourceGroup = DefaultDataSource.getInstance().createDataSourceGroup();
			for (Entry<String, DataSource> entry : datasourceGroup.entrySet()) {
				Dataset data = new Dataset.Builder().group(entry.getKey()).data(entry.getValue()).get();
				datasetController.addDataset(data);
			}

			// load annotation
			LayerController.insertBeforeBeforeCompass(this.getWwd(), datasetController.getAnnotation());
			activeLayers.add(datasetController.getAnnotation());

			// insert first layer into wwj
			LayerController.insertBeforeBeforeCompass(this.getWwd(), datasetController.getLayer());
			activeLayers.add(datasetController.getLayer());

			// add boudaries layer
			// LayerController.insertBeforeBeforeCompass(this.getWwd(), new
			// CountryBoundariesLayer());

			// set wwj start position
			controller.flyToPosition(PARA_POS, INITIAL_ZOOM);
		}

		public void removeActiveLayers() {
			getWwd().getModel().getLayers().removeAll(activeLayers);
		}

		public void refreshActiveLayers() {
			activeLayers.clear();
			activeLayers.add(datasetController.getAnnotation());
			activeLayers.add(datasetController.getLayer());
			getWwd().getModel().getLayers().addAll(activeLayers);
		}

		public JLayeredPane getLayeredPane() {
			return layeredPane;
		}

		public WorldWindowGLCanvas getWwd() {
			return wwd;
		}

		// Kinect
		private UserTracker createUserTracker() {
			DeviceConfig kinect = DeviceConfig.getInstance();
			if (!kinect.isDeviceConnected()) {
				throw new RuntimeException("No device is connected");
			}
			kinect.startFirstDevice();
			return UserTracker.create();
		}

		private void initKinect() {
			try {
				this.kinectHandler = new KinectLayer(createUserTracker());
				kinectHandler.get().setBounds(15, 585, 224, 168);
				kinectHandler.get().setWorldWindController(controller);
				layeredPane.add(kinectHandler.get(), new Integer(JLayeredPane.DEFAULT_LAYER + 1));

				new Thread(kinectHandler).start();
				getWwd().redraw();
			} catch (Exception e) {
				System.err.println("Cannot initialize Kinect");
			}
		}

		@Override
		public void run() {
			while (true) {
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				getWwd().redraw();
				repaint();
			}
		}

	}

	public static void main(String[] args) {
		ScenarioLayerFrame frame = new ScenarioLayerFrame();
		frame.setVisible(true);
		new Thread(frame).start();
	}
}
