package br.inpe.worldwind.view.controllers.impl;

import br.inpe.gdal.transform.GeoFormat;
import br.inpe.worldwind.view.controllers.ApplicationSceneController;
import br.inpe.worldwind.view.controllers.SceneView;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * @author Heitor
 * @since 06/05/2016
 */
public class SceneLayerController extends ApplicationSceneController {
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Pane paneView;

    @FXML
    private TextArea txtOgrInfo;

    @FXML
    private ComboBox<GeoFormat> comboOutputFormat;
    
    @Override
    protected void initPaneSetup() {
        this.comboOutputFormat.setItems(FXCollections.observableArrayList(GeoFormat.values()));
        this.comboOutputFormat.getSelectionModel().selectFirst();
    }

    @Override
    protected SceneView getSceneView() {
        return SceneView.LAYER_VIEW;
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
