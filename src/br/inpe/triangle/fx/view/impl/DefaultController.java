package br.inpe.triangle.fx.view.impl;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import br.inpe.triangle.app.WorldWindView;
import br.inpe.triangle.data.Data;
import br.inpe.triangle.fx.view.SceneView;
import br.inpe.triangle.fx.view.SetupController;
import br.inpe.triangle.fx.view.SetupView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import resources.Resource;

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

	/* Pane View */
	@FXML
	private Pane paneView;
	/* Pane Setup */
	@FXML
	private Pane paneSetup;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		/* Load all panes before */
		loadSetupViewFromFXML();
		loadSceneViewFromFXML();
		/* add events */
		initPaneSetupEvents();
		/* set default screen */
		setPaneSetupComponents(SetupView.BASIC);
		setPaneSceneComponents(SceneView.BASIC_VIEW);
	}

	@Override
	public void initPaneSetupEvents() {
		btnStart.setOnAction(event -> {
			List<Data> dataset = SETUP_CONTROLLER.getDatasetFromBasicController();
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

		btnKinect.setOnAction(event -> {
			setPaneSetupComponents(SetupView.KINECT);
			setPaneSceneComponents(SceneView.KINECT_VIEW);
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

	private void loadSetupViewFromFXML() {
		/* create list */
		ObservableList<Node> elementsSetupPanelBasic = FXCollections.observableArrayList();
		ObservableList<Node> elementsSetupPanelLayer = FXCollections.observableArrayList();
		ObservableList<Node> elementsSetupPanelKinect = FXCollections.observableArrayList();
		/* add elements */
		loadPane(elementsSetupPanelBasic, Resource.getPaneSetupBasicFXML());
		loadPane(elementsSetupPanelLayer, Resource.getPaneSetupLayerFXML());
		loadPane(elementsSetupPanelKinect, Resource.getPaneSetupKinectFXML());
		/* add elements in ManagerSetupController */
		SETUP_CONTROLLER.addElement(SetupView.BASIC, elementsSetupPanelBasic);
		SETUP_CONTROLLER.addElement(SetupView.LAYER, elementsSetupPanelLayer);
		SETUP_CONTROLLER.addElement(SetupView.KINECT, elementsSetupPanelKinect);
		/* Add buttons in SetupView */
		SetupView.BASIC.setButton(btnGlobe);
		SetupView.LAYER.setButton(btnLayer);
		SetupView.KINECT.setButton(btnKinect);
	}

	private void loadSceneViewFromFXML() {
		/* create list */
		ObservableList<Node> elementsScenePanelBasic = FXCollections.observableArrayList();
		ObservableList<Node> elementsScenePanelLayer = FXCollections.observableArrayList();
		ObservableList<Node> elementsScenePanelKinect = FXCollections.observableArrayList();
		/* add elements */
		loadPane(elementsScenePanelBasic, Resource.getPaneViewBasicFXML());
		loadPane(elementsScenePanelLayer, Resource.getPaneViewLayerFXML());
		loadPane(elementsScenePanelKinect, Resource.getPaneViewKinectFXML());
		/* add elements in ManagerSceneController */
		SCENE_CONTROLLER.addElement(SceneView.BASIC_VIEW, elementsScenePanelBasic);
		SCENE_CONTROLLER.addElement(SceneView.LAYER_VIEW, elementsScenePanelLayer);
		SCENE_CONTROLLER.addElement(SceneView.KINECT_VIEW, elementsScenePanelKinect);
	}
}
