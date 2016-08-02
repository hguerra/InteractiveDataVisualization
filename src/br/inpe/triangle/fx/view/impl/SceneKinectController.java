package br.inpe.triangle.fx.view.impl;

import br.inpe.triangle.fx.view.SceneView;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * @author Heitor
 * @since 06/05/2016
 */
public class SceneKinectController extends ApplicationSceneController {
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Pane paneView;

    @Override
    protected void initPaneSetup() {
        
    }

    @Override
    protected SceneView getSceneView() {
        return SceneView.KINECT_VIEW;
    }

    @Override
    protected Pane getPaneView() {
        return paneView;
    }

    @Override
    public void initPaneSetupEvents() {

    }

    @Override
    public void update(Object object) {

    }
}
