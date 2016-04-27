package br.inpe.worldwind.view.panels;

import br.inpe.worldwind.view.Resource;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SetupProfileGUI extends Application{

	@Override
	public void start(Stage stage) throws Exception {
		Parent parent = FXMLLoader.load(Resource.getPaneSetupProfileFXML());
		Scene scene = new Scene(parent);
		stage.setScene(scene);
		stage.setTitle("SetupProfileGUI");
		//stage.setResizable(false);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
