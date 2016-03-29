package br.inpe.worldwind.view;

import java.net.URL;

import br.inpe.worldwind.view.controllers.ManagerSetupController.SetupView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class ApplicationFXML extends Application {
	private static Stage currentStage;

	protected abstract String getSceneTitle();

	protected abstract URL getFXML();

	protected abstract SetupView getSetupView();

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getFXML());
		Parent parent = fxmlLoader.load();
		/* add scene */
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
