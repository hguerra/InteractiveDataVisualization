package br.inpe.worldwind.view.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import br.inpe.worldwind.view.controllers.ManagerSetupController.SetupView;
import br.inpe.worldwind.view.resources.Resource;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import test.app.WorldWindControllersTest;

public class SetupDefaultController implements SetupController {
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
	private Button btnClose;

	@FXML
	private Button btnStart;

	@FXML
	private Button btnProfile;

	@FXML
	private Button btnDataBase;

	@FXML
	private Pane paneView;
	/* Pane Setup Basic */
	@FXML
	private Pane paneSetup;

	@FXML
	private Label lblLayerName;

	@FXML
	private Label lblData;

	@FXML
	private ComboBox<?> comboLayer;

	@FXML
	private Label lblScenario;

	@FXML
	private ListView<?> listViewScenario;

	@FXML
	private ContextMenu contextMenu;

	@FXML
	private MenuItem menuItemRemove;

	@FXML
	private Label lblKinect;

	@FXML
	private ToggleButton toggleBtnKinect;

	@FXML
	private Button btnTrash;
	/* Pane View */
	@FXML
	private ImageView imgMock;

	private ManagerSetupController manager = ManagerSetupController.getInstance();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		/* Load all panes before */
		initPaneSetup();
		/* add events */
		initPaneSetupEvents();
	}

	@Override
	public void initPaneSetupEvents() {
		btnStart.setOnAction(event -> new WorldWindControllersTest().run());
		btnClose.setOnAction(event -> {

			clearPaneSetup();

			Task<Void> task = new Task<Void>() {
				@Override
				protected Void call() throws Exception {
					Thread.sleep(2000);
					return null;
				}

			};
			task.setOnSucceeded(e -> {
				JOptionPane.showMessageDialog(null, "Bye Bye!");
			});
			new Thread(task).start();
		});

		btnGlobe.setOnAction(event -> {
			setPaneSetupComponents(SetupView.BASIC);
		});

		btnLayer.setOnAction(event -> {
			setPaneSetupComponents(SetupView.LAYER);
		});

		btnDataBase.setOnAction(event -> {
			setPaneSetupComponents(SetupView.DATABASE);
		});

		btnKinect.setOnAction(event -> {
			setPaneSetupComponents(SetupView.KINECT);
		});

		btnProfile.setOnAction(event -> {
			setPaneSetupComponents(SetupView.PROFILE);
		});
	}

	@Override
	public ObservableList<Node> getPaneSetupChildren() {
		return this.paneSetup.getChildren();
	}

	private void initPaneSetup() {
		/* create list */
		ObservableList<Node> elementsSetupPanelBasic = FXCollections.observableArrayList();
		ObservableList<Node> elementsSetupPanelLayer = FXCollections.observableArrayList();
		ObservableList<Node> elementsSetupPanelDatabase = FXCollections.observableArrayList();
		ObservableList<Node> elementsSetupPanelKinect = FXCollections.observableArrayList();
		ObservableList<Node> elementsSetupPanelProfile = FXCollections.observableArrayList();
		/* add elements */
		loadPaneSetup(elementsSetupPanelBasic, Resource.getPaneSetupBasicFXML());
		loadPaneSetup(elementsSetupPanelLayer, Resource.getPaneSetupLayerFXML());
		loadPaneSetup(elementsSetupPanelDatabase, Resource.getPaneSetupDatabaseFXML());
		loadPaneSetup(elementsSetupPanelKinect, Resource.getPaneSetupKinectFXML());
		loadPaneSetup(elementsSetupPanelProfile, Resource.getPaneSetupProfileFXML());
		/* add elements in ManagerSetupController */
		manager.addElement(SetupView.BASIC, elementsSetupPanelBasic);
		manager.addElement(SetupView.LAYER, elementsSetupPanelLayer);
		manager.addElement(SetupView.DATABASE, elementsSetupPanelDatabase);
		manager.addElement(SetupView.KINECT, elementsSetupPanelKinect);
		manager.addElement(SetupView.PROFILE, elementsSetupPanelProfile);
	}

	private void setPaneSetupComponents(SetupView key) {
		clearPaneSetup();
		this.paneSetup.getChildren().addAll(manager.getElement(key));
	}

}
