package br.inpe.app.worldwind;

import gov.nasa.worldwind.BasicModel;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.awt.WorldWindowGLJPanel;
import gov.nasa.worldwind.view.orbit.OrbitView;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingNode;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class WorldWindFXTest extends Application {
	// JavaFX
	private StackPane stackPane;
	private SwingNode swingNode;
	private Scene scene;
	// NASA World Wind
	private WorldWindowGLJPanel wwd;

	@Override
	public void start(Stage stage) throws Exception {
		// create container
		stackPane = new StackPane();

		// create World Wind
		wwd = new WorldWindowGLJPanel();
		wwd.setPreferredSize(new java.awt.Dimension(300, 300)); // initial size
		wwd.setModel(new BasicModel());

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

		/**
		 * World Wind events
		 */
		worldWindFrameEvents();
		worldWindTimeEvents();

	}

	private void worldWindTimeEvents() {
		new Thread(() -> {
			try {
				Thread.sleep(5000);

				double ratio = 0.5;

				// reduces size in 50%
				wwd.setSize((int) (wwd.getWidth() * ratio), (int) (wwd.getHeight() * ratio));

				// increases zoom in 50%
				zoom(ratio, wwd);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();
	}

	private void worldWindFrameEvents() {
		resizeChangeEventListner(0.5); // reduce in 50%
		zoomChangeEventListner(0.5); // increases in 50%
	}

	public void resizeChangeEventListner(double ratio) {
		scene.widthProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				/**
				 * JavaFX runs in another thread, need this to change values
				 */
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						System.out.println("resize...");
						wwd.setSize((int) (newValue.intValue() * ratio), wwd.getHeight());
					}
				});

			}
		});
	}

	public void zoomChangeEventListner(double ratio) {
		scene.widthProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				/**
				 * JavaFX runs in another thread, need this to change values
				 */
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						System.out.println("zoom...");
						zoom(ratio, wwd);
					}
				});

			}
		});
	}

	public void zoom(double ratio, WorldWindowGLJPanel wwd2) {
		OrbitView view = (OrbitView) wwd2.getView();

		if (view == null)
			return;

		final double zoomFactor = view.getZoom();

		System.out.println("zoomFactor:" + zoomFactor);

		int newzoom = (int) (zoomFactor * ratio);

		System.out.println("newzoom:" + newzoom);

		if (newzoom >= 1071941 && newzoom <= 18437542) {
			System.out.println("Zoom Within limits!");
			view.setZoom(newzoom);
			view.firePropertyChange(AVKey.VIEW, null, view);
		} else {
			System.out.println("Zoom out of bounds!");
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}