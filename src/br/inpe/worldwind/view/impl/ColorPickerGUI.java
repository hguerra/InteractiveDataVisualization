package br.inpe.worldwind.view.impl;

import br.inpe.worldwind.view.resources.Resource;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ColorPickerGUI extends Application {

	private static Stage currentStage;

	public static boolean closeStage() {
		if (currentStage == null)
			return false;
		currentStage.close();
		return true;
	}
	

	@Override
	public void start(Stage stage) throws Exception {
		Parent parent = FXMLLoader.load(Resource.getColorPickerFXML());
		Scene scene = new Scene(parent);
		stage.setScene(scene);
		stage.setTitle("ColorPickerGUI");
		// stage.setResizable(false);
		stage.show();
		currentStage = stage;
	}

	public static void main(String[] args) {
		launch(args);
	}

}
