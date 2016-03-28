package br.inpe.worldwind.view;

import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class ApplicationFXML extends Application {
	private static Stage currentStage;

	protected abstract String getSceneTitle();

	protected abstract URL getFXML();

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent parent = FXMLLoader.load(getFXML());
		Scene scene = new Scene(parent);
		primaryStage.setScene(scene);
		primaryStage.setTitle(getSceneTitle());
		primaryStage.show();
		currentStage = primaryStage;
	}
	
	public static boolean closeStage() {
		if (currentStage == null)
			return false;
		currentStage.close();
		return true;
	}
}
