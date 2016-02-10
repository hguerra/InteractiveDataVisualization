package br.inpe.worldwind.view;

import br.inpe.worldwind.view.resources.Resource;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DataAttributesGUI extends Application{

	@Override
	public void start(Stage stage) throws Exception {
		Parent parent = FXMLLoader.load(Resource.getDataAttributesFXML());
		Scene scene = new Scene(parent);
		stage.setScene(scene);
		stage.setTitle("DataAttributesGUI");
		//stage.setResizable(false);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
