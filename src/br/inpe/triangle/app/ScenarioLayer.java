package br.inpe.triangle.app;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;

import br.inpe.triangle.data.Data;
import br.inpe.triangle.data.DataSource;
import br.inpe.triangle.defaultproperties.DefaultDataSource;
import br.inpe.triangle.fx.view.impl.ManagerSetupController;
import br.inpe.triangle.kinect.SkeletonKinectHandler;
import br.inpe.triangle.wwj.layer.LayerController;
import br.inpe.triangle.wwj.layer.WorldWindController;
import br.inpe.triangle.wwj.layer.impl.ScreenAnnotationLayer;
import gov.nasa.worldwind.BasicModel;
import gov.nasa.worldwind.awt.WorldWindowGLCanvas;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.Layer;

public class ScenarioLayer {
	public static class ScenarioLayerFrame extends JFrame {
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

		private SkeletonKinectHandler kinectHandler;

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
			controller = new WorldWindControllerImpl(this);

			// load dataset
			try {
				loadDataset();
			} catch (Exception e) {
				System.err.println("Cannot load dataset");
			}

			// refresh compass
			LayerController.removeAllLayersCompass(getWwd());

			// init kinect
			initKinectHandler();
		}

		private void addLogo() {
			LayerController screenAnnotation = new ScreenAnnotationLayer(wwd, 780, 530, "images/ccst-novo2.png",
					new Insets(0, 40, 0, 0), new Dimension(265, 200));
			screenAnnotation.asyncDraw();
		}

		private void loadDataset() throws Exception {
			// Create RenderableLayer instance
			try {
				ManagerSetupController SETUP_CONTROLLER = ManagerSetupController.getInstance();
				String group = SETUP_CONTROLLER.getSelectedDataSourceGroup();
				Map<String, Data> dataset = SETUP_CONTROLLER.getSortedDataset(group);

				Dataset data = new Dataset.Builder().group(group).data(dataset).get();
				datasetController.addDataset(data);
			} catch (Exception e) {
				System.err.println("Unable to load dataset from GUI");
				Map<String, DataSource> datasourceGroup = DefaultDataSource.getInstance().createDataSourceGroup();
				for (Entry<String, DataSource> entry : datasourceGroup.entrySet()) {
					Dataset data = new Dataset.Builder().group(entry.getKey()).data(entry.getValue()).get();
					datasetController.addDataset(data);
				}
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
		private void initKinectHandler() {
			try {
				this.kinectHandler = new SkeletonKinectHandler(controller);
				kinectHandler.setBounds(15, 585, 224, 168);
				layeredPane.add(kinectHandler, new Integer(JLayeredPane.DEFAULT_LAYER.intValue() + 1));
			} catch (Exception e) {
				System.err.println("Cannot initialize Kinect");
			}
		}

	}

	public static void main(String[] args) {
		ScenarioLayerFrame frame = new ScenarioLayerFrame();
		frame.setVisible(true);
	}
}
