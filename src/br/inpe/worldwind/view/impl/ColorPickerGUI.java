package br.inpe.worldwind.view.impl;

import br.inpe.worldwind.view.controllers.ManagerSetupController;
import br.inpe.worldwind.view.controllers.ManagerSetupController.SetupView;
import br.inpe.worldwind.view.controllers.SetupController;
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
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(Resource.getColorPickerFXML());
		Parent parent = fxmlLoader.load();
		Object controller = fxmlLoader.getController();

		if (controller instanceof SetupController) {
			System.out.println(controller);
			ManagerSetupController instance = ManagerSetupController.getInstance();
			instance.addController(SetupView.LAYER_COLOR, (SetupController) controller);
			System.out.println(instance.getController(SetupView.LAYER_COLOR));
		}
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
