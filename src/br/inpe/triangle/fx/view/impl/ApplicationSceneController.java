package br.inpe.triangle.fx.view.impl;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

import br.inpe.triangle.fx.view.SceneView;
import br.inpe.triangle.fx.view.SetupController;

/**
 * @author Heitor
 * @since 05/05/2016
 */
public abstract class ApplicationSceneController implements SetupController {
    protected abstract void initPaneSetup();

    protected abstract SceneView getSceneView();

    protected abstract Pane getPaneView();

    @Override
    public ObservableList<Node> getPaneSceneChildren() {
        return getPaneView().getChildren();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /* Load all panes before */
        initPaneSetup();
		/* add events */
        initPaneSetupEvents();
		/* set screen */
        addSceneView(getSceneView(), getPaneView());
		/* set controller */
        addSceneController(getSceneView(), this);
    }

}