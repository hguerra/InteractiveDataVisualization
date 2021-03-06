package br.inpe.triangle.fx.view;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public abstract class ApplicationFXML extends Application {
    private static Stage currentStage;

    protected abstract String getSceneTitle();

    protected abstract URL getFXML();

    protected abstract SetupView getSetupView();

    protected abstract boolean exitOnCloseRequest();

    protected abstract boolean getResizable();

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getFXML());
        Parent parent = fxmlLoader.load();
        /* add scene */
        Scene scene = new Scene(parent);
        primaryStage.setScene(scene);
        primaryStage.setTitle(getSceneTitle());
        primaryStage.setResizable(getResizable());
        setOnCloseRequest(primaryStage);
        primaryStage.show();
        currentStage = primaryStage;
    }

    private void setOnCloseRequest(Stage stage) {
        if (!exitOnCloseRequest()) return;
        stage.setOnCloseRequest(t -> {
            Platform.exit();
            System.exit(0);
        });
    }

    public static boolean closeStage() {
        if (currentStage == null)
            return false;
        currentStage.close();
        return true;
    }

}
