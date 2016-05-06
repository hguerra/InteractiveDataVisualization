package br.inpe.worldwind.view.controllers.impl;

import br.inpe.triangle.conf.Data;
import br.inpe.triangle.conf.DataSource;
import br.inpe.worldwind.view.Resource;
import br.inpe.worldwind.view.controllers.*;
import br.inpe.worldwind.view.impl.WorldWindView;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class DefaultController implements SetupController {
    private final static ManagerSetupController SETUP_CONTROLLER = ManagerSetupController.getInstance();
    private final static ManagerSceneController SCENE_CONTROLLER = ManagerSceneController.getInstance();

    /* Pane Options */
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Pane paneOptions;

    @FXML
    private Button btnKinect;

    @FXML
    private Button btnGlobe;

    @FXML
    private Button btnLayer;

    @FXML
    private Button btnStart;

    @FXML
    private Button btnProfile;

    @FXML
    private Button btnDataBase;

    /* Pane View */
    @FXML
    private Pane paneView;
    /* Pane Setup */
    @FXML
    private Pane paneSetup;


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        Platform.runLater(() -> {
            /* Load all panes before */
            loadSetupViewFromFXML();
            loadSceneViewFromFXML();
            /* add events */
            initPaneSetupEvents();
            /* set default screen */
            setPaneSetupComponents(SetupView.BASIC);
            setPaneSceneComponents(SceneView.BASIC_VIEW);
        });
    }

    @Override
    public void initPaneSetupEvents() {
        btnStart.setOnAction(event -> {
            List<Data> dataset = getDatasetFromBasicController();
            try {
                WorldWindView.run(dataset);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        btnGlobe.setOnAction(event -> {
            setPaneSetupComponents(SetupView.BASIC);
            setPaneSceneComponents(SceneView.BASIC_VIEW);
        });

        btnLayer.setOnAction(event -> {
            setPaneSetupComponents(SetupView.LAYER);
            setPaneSceneComponents(SceneView.LAYER_VIEW);
        });

        btnDataBase.setOnAction(event -> {
            setPaneSetupComponents(SetupView.DATABASE);
            setPaneSceneComponents(SceneView.DATABASE_VIEW);
        });

        btnKinect.setOnAction(event -> {
            setPaneSetupComponents(SetupView.KINECT);
            setPaneSceneComponents(SceneView.KINECT_VIEW);
        });

        btnProfile.setOnAction(event -> {
            setPaneSetupComponents(SetupView.PROFILE);
        });
    }


    @Override
    public ObservableList<Node> getPaneSceneChildren() {
        return this.paneSetup.getChildren();
    }


    private void setPaneSetupComponents(SetupView key) {
        key.clearButtonStyle();
        clearPaneSetup();
        this.paneSetup.getChildren().addAll(SETUP_CONTROLLER.getElement(key));
        key.addButtonStyle();
    }

    private void setPaneSceneComponents(SceneView sceneView) {
        paneView.getChildren().clear();
        this.paneView.getChildren().addAll(SCENE_CONTROLLER.getElement(sceneView));
    }

    @Override
    public void update(Object object) {
    }

    private List<Data> getDatasetFromBasicController(DataSource dataSource) {
        List<Data> dataset = new ArrayList<>();
        SETUP_CONTROLLER.getSelectedBasicScenario().forEach(key -> {
            dataset.add(dataSource.getDataSet().get(key));
        });
        return dataset;
    }

    private List<Data> getDatasetFromBasicController() {
        Supplier<Stream<Node>> streamSupplier = () -> SETUP_CONTROLLER.getController(SetupView.BASIC).getPaneSceneChildren()
                .parallelStream();

        Node node = streamSupplier.get().filter(component -> component instanceof ComboBox).findFirst().get();
        if (node == null)
            return null;
        @SuppressWarnings("unchecked")
        ComboBox<String> comboBox = (ComboBox<String>) node;
        String group = comboBox.getSelectionModel().getSelectedItem();

        return getDatasetFromBasicController(SETUP_CONTROLLER.getDataSourceFromGroup(group));
    }

    private void loadSetupViewFromFXML() {
        /* create list */
        ObservableList<Node> elementsSetupPanelBasic = FXCollections.observableArrayList();
        ObservableList<Node> elementsSetupPanelLayer = FXCollections.observableArrayList();
        ObservableList<Node> elementsSetupPanelDatabase = FXCollections.observableArrayList();
        ObservableList<Node> elementsSetupPanelKinect = FXCollections.observableArrayList();
        ObservableList<Node> elementsSetupPanelProfile = FXCollections.observableArrayList();
        /* add elements */
        loadPane(elementsSetupPanelBasic, Resource.getPaneSetupBasicFXML());
        loadPane(elementsSetupPanelLayer, Resource.getPaneSetupLayerFXML());
        loadPane(elementsSetupPanelDatabase, Resource.getPaneSetupDatabaseFXML());
        loadPane(elementsSetupPanelKinect, Resource.getPaneSetupKinectFXML());
        loadPane(elementsSetupPanelProfile, Resource.getPaneSetupProfileFXML());
        /* add elements in ManagerSetupController */
        SETUP_CONTROLLER.addElement(SetupView.BASIC, elementsSetupPanelBasic);
        SETUP_CONTROLLER.addElement(SetupView.LAYER, elementsSetupPanelLayer);
        SETUP_CONTROLLER.addElement(SetupView.DATABASE, elementsSetupPanelDatabase);
        SETUP_CONTROLLER.addElement(SetupView.KINECT, elementsSetupPanelKinect);
        SETUP_CONTROLLER.addElement(SetupView.PROFILE, elementsSetupPanelProfile);
        /*Add buttons in SetupView*/
        SetupView.BASIC.setButton(btnGlobe);
        SetupView.LAYER.setButton(btnLayer);
        SetupView.DATABASE.setButton(btnDataBase);
        SetupView.KINECT.setButton(btnKinect);
        SetupView.PROFILE.setButton(btnProfile);
    }

    private void loadSceneViewFromFXML() {
        /* create list */
        ObservableList<Node> elementsScenePanelBasic = FXCollections.observableArrayList();
        ObservableList<Node> elementsScenePanelLayer = FXCollections.observableArrayList();
        ObservableList<Node> elementsScenePanelDatabase = FXCollections.observableArrayList();
        ObservableList<Node> elementsScenePanelKinect = FXCollections.observableArrayList();
        /* add elements */
        loadPane(elementsScenePanelBasic, Resource.getPaneViewBasicFXML());
        loadPane(elementsScenePanelLayer, Resource.getPaneViewLayerFXML());
        loadPane(elementsScenePanelDatabase, Resource.getPaneViewDatabaseFXML());
        loadPane(elementsScenePanelKinect, Resource.getPaneViewKinectFXML());
        /* add elements in ManagerSceneController */
        SCENE_CONTROLLER.addElement(SceneView.BASIC_VIEW, elementsScenePanelBasic);
        SCENE_CONTROLLER.addElement(SceneView.LAYER_VIEW, elementsScenePanelLayer);
        SCENE_CONTROLLER.addElement(SceneView.DATABASE_VIEW, elementsScenePanelDatabase);
        SCENE_CONTROLLER.addElement(SceneView.KINECT_VIEW, elementsScenePanelKinect);
    }
}
