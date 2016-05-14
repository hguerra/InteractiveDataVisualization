package br.inpe.worldwind.view.controllers.impl;

import br.inpe.gdal.transform.GeoFormat;
import br.inpe.gdal.transform.OGRInfo;
import br.inpe.gdal.transform.OGRVector;
import br.inpe.triangle.conf.Data;
import br.inpe.worldwind.view.controllers.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

/**
 * @author Heitor
 * @since 06/05/2016
 */
public class SceneLayerController extends ApplicationSceneController {
    private static final ManagerSetupController SETUP_CONTROLLER = ManagerSetupController.getInstance();

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Pane paneView;

    @FXML
    private TextArea txtOgrInfo;

    @FXML
    private ComboBox<GeoFormat> comboOutputFormat;

    @FXML
    private Button btnInfo;

    @FXML
    private Button btnConvert;

    @FXML
    private Button btnTrash;

    private SetupLayerController layerController;

    private OGRVector ogrVector;


    @Override
    protected void initPaneSetup() {
        this.layerController = (SetupLayerController) SETUP_CONTROLLER.getController(SetupView.LAYER);
        this.ogrVector = new OGRVector();
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
        btnInfo.setOnAction(event -> {
            Data data = getSelectedData();
            if (data == null) return;

            try {
                String info = new OGRInfo().info(data.getFilepath());
                txtOgrInfo.setText(info);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        btnTrash.setOnAction(event -> txtOgrInfo.clear());

        btnConvert.setOnAction(event -> {
            Data data = getSelectedData();
            if (data == null) return;

            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showSaveDialog(null);

            if (file == null) {
                JOptionPane.showMessageDialog(null, "Select the file path", "OGR warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
            try {
                ogrVector.transform(comboOutputFormat.getValue(), file.getAbsolutePath(), data.getFilepath());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error to load GDAL", "GDAL ERROR", JOptionPane.ERROR_MESSAGE);
            }

        });

    }

    private Data getSelectedData() {
        String item = layerController.getListViewScenario().getSelectionModel().getSelectedItem();
        if (item == null) return null;
        return SETUP_CONTROLLER.getData(item);
    }

    @Override
    public void update(Object object) {

    }

}
