package br.inpe.worldwind.view.controllers.impl;

import br.inpe.worldwind.view.controllers.ApplicationSceneController;
import br.inpe.worldwind.view.controllers.SceneView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * @author Heitor
 * @since 05/05/2016
 */
public class SceneDatabaseController extends ApplicationSceneController {
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Pane paneView;

    @FXML
    private TextArea txtSQL;

    @FXML
    private Button btnExecute;

    @FXML
    private Button btnTrash;

    @FXML
    private TableView tableResult;

    @FXML
    private Button btnExport;

    @Override
    protected void initPaneSetup() {

    }

    @Override
    protected SceneView getSceneView() {
        return SceneView.DATABASE_VIEW;
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
