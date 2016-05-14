package br.inpe.worldwind.view.impl;

import br.inpe.worldwind.view.Resource;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HomeGUI extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent parent = FXMLLoader.load(Resource.getHomeDefaultFXML());
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setTitle("Triangle of Sustainability");
        stage.setResizable(false);
        stage.setOnCloseRequest(t -> {
            Platform.exit();
            System.exit(0);
        });
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
