package br.inpe.worldwind.view.controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

import java.net.URL;

public interface SetupController extends Initializable {
    void initPaneSetupEvents();

    ObservableList<Node> getPaneSceneChildren();

    void update(Object object);

    default void clearPaneSetup() {
        getPaneSceneChildren().clear();
    }

    default boolean loadPane(URL location) {
        try {
            Parent parent = FXMLLoader.load(location);
            return getPaneSceneChildren().addAll(parent.getChildrenUnmodifiable());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    default boolean loadPane(ObservableList<Node> elements, URL location) {
        try {
            Parent parent = FXMLLoader.load(location);
            ObservableList<Node> parentList = parent.getChildrenUnmodifiable();
            elements.addAll(parentList);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    default ObservableList<Node> addSetupView(SetupView setup, Pane pane) {
        ManagerSetupController manager = ManagerSetupController.getInstance();
        return manager.addElement(setup, pane);
    }

    default SetupController addSetupController(SetupView setup, Object controller) {
        if (!(controller instanceof SetupController)) {
            return null;
        }

        ManagerSetupController manager = ManagerSetupController.getInstance();
        return manager.addController(setup, (SetupController) controller);
    }

    default ObservableList<Node> addSceneView(SceneView sceneView, Pane pane) {
        ManagerSceneController manager = ManagerSceneController.getInstance();
        return manager.addElement(sceneView, pane);
    }

    default SetupController addSceneController(SceneView sceneView, Object controller) {
        if (!(controller instanceof SetupController)) {
            return null;
        }

        ManagerSceneController manager = ManagerSceneController.getInstance();
        return manager.addController(sceneView, (SetupController) controller);
    }
}
