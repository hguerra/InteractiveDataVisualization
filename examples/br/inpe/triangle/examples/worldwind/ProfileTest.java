package br.inpe.triangle.examples.worldwind;

import br.inpe.triangle.data.Profile;
import br.inpe.triangle.defaultproperties.DefaultTriangleProperties;
import br.inpe.triangle.wwj.layer.ShapefileController;
import br.inpe.triangle.wwj.layer.impl.ShapefileLayer;
import gov.nasa.worldwind.BasicModel;
import gov.nasa.worldwind.awt.WorldWindowGLJPanel;
import gov.nasa.worldwind.layers.Layer;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingNode;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.List;

public class ProfileTest extends Application {
	// JavaFX
	private StackPane stackPane;
	private SwingNode swingNode;
	private Scene scene;
	// NASA World Wind
	private WorldWindowGLJPanel wwd;
	private ShapefileController shpController;
	private DefaultTriangleProperties defaultTriangle = DefaultTriangleProperties.getInstance();

	@Override
	public void start(Stage stage) throws Exception {
		initComponents(stage);

	}

	public Profile getProfile() {
		return defaultTriangle.getProfile();
	}

	/**
	 * Shapefile
	 */
	private void drawShapefile(List<Layer> layers) {
		shpController.addShapefile(layers.get(0));

		shpController.asyncDraw();
	}

	private void removeShapefile() {
		shpController.asyncRemove();
	}

	private void drawScenario(long millis) {
		Task<Void> task = new Task<Void>() {
			@Override
			protected Void call() throws Exception {
				defaultTriangle.getDefaultKeys().forEach(k -> {
					try {
						System.out.println(k);

						List<Layer> layers = getProfile().getLayer(k);

						drawShapefile(layers);

						Thread.sleep(millis);

						removeShapefile();
					} catch (Exception e) {
						e.printStackTrace();
					}
				});
				return null;
			}
		};

		final Thread thread = new Thread(task);

		task.setOnSucceeded(e -> {
			System.out.println("finished");
		});

		thread.start();
	}

	/**
	 * Default config
	 * 
	 * @param args
	 * @throws Exception
	 */

	private void initComponents(Stage stage) throws Exception {
		// create container
		stackPane = new StackPane();

		// create World Wind
		wwd = new WorldWindowGLJPanel();
		wwd.setPreferredSize(new java.awt.Dimension(300, 300)); // initial size
		wwd.setModel(new BasicModel());

		// Shapefile
		shpController = new ShapefileLayer(wwd);
		// drawShapefile();
		drawScenario(2000);

		// create SwingNode
		swingNode = new SwingNode();
		swingNode.setContent(wwd);

		// add World Wind in container
		stackPane.getChildren().add(swingNode);

		// add container scene
		scene = new Scene(stackPane, 800, 600);
		// add scene in stage
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}