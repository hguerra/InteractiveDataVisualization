package br.inpe.worldwind.view.controllers;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Heitor
 * @since 05/05/2016
 */
public class ManagerSceneController {
    private static ManagerSceneController ourInstance = new ManagerSceneController();

    public static ManagerSceneController getInstance() {
        return ourInstance;
    }

    private Map<SceneView, ObservableList<Node>> elementsView;
    private Map<SceneView, SetupController> controllers;

    private ManagerSceneController() {
        this.elementsView = new HashMap<>();
        this.controllers = new HashMap<>();
    }

    public synchronized ObservableList<Node> addElement(SceneView sceneView, Pane parent) {
        return this.elementsView.put(sceneView, parent.getChildren());
    }

    public synchronized ObservableList<Node> addElement(SceneView sceneView, ObservableList<Node> parent) {
        return this.elementsView.put(sceneView, parent);
    }

    public synchronized ObservableList<Node> getElement(SceneView sceneView) {
        return this.elementsView.get(sceneView);
    }

    public synchronized SetupController addController(SceneView sceneView, SetupController controller) {
        return this.controllers.put(sceneView, controller);
    }

    public synchronized SetupController getController(SceneView sceneView) {
        return this.controllers.get(sceneView);
    }


}
