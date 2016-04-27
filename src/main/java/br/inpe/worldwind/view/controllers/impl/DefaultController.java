package br.inpe.worldwind.view.controllers.impl;

import br.inpe.triangle.conf.Data;
import br.inpe.triangle.conf.DataSource;
import br.inpe.worldwind.view.Resource;
import br.inpe.worldwind.view.controllers.ManagerSetupController;
import br.inpe.worldwind.view.controllers.ManagerSetupController.SetupView;
import br.inpe.worldwind.view.controllers.SetupController;
import br.inpe.worldwind.view.impl.WorldWindView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class DefaultController implements SetupController {
	private final static ManagerSetupController MANAGER = ManagerSetupController.getInstance();

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

	@FXML
	private Pane paneView;
	/* Pane Setup */
	@FXML
	private Pane paneSetup;
	/* Pane View */
	@FXML
	private ImageView imgMock;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		/* Load all panes before */
		initPaneSetup();
		/* add events */
		initPaneSetupEvents();
		/* set default screen */
		setPaneSetupComponents(SetupView.BASIC);
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
		MANAGER.addElement(SetupView.BASIC, elementsSetupPanelBasic);
		MANAGER.addElement(SetupView.LAYER, elementsSetupPanelLayer);
		MANAGER.addElement(SetupView.DATABASE, elementsSetupPanelDatabase);
		MANAGER.addElement(SetupView.KINECT, elementsSetupPanelKinect);
		MANAGER.addElement(SetupView.PROFILE, elementsSetupPanelProfile);
	}

	private void setPaneSetupComponents(SetupView key) {
		clearPaneSetup();
		this.paneSetup.getChildren().addAll(MANAGER.getElement(key));
	}

	@Override
	public void update(Object object) {
	}

	private List<Data> getDatasetFromBasicController(Supplier<Stream<Node>> streamSupplier, DataSource dataSource) {
		Node node = streamSupplier.get().filter(component -> component instanceof ListView).findFirst().get();
		if (node == null)
			return null;

		@SuppressWarnings("unchecked")
		ListView<String> listView = (ListView<String>) node;

		List<Data> dataset = new ArrayList<>();
		listView.getItems().forEach(key -> {
			dataset.add(dataSource.getDataSet().get(key));
		});

		return dataset;
	}

	private List<Data> getDatasetFromBasicController() {
		Supplier<Stream<Node>> streamSupplier = () -> MANAGER.getController(SetupView.BASIC).getPaneSetupChildren()
				.parallelStream();

		Node node = streamSupplier.get().filter(component -> component instanceof ComboBox).findFirst().get();
		if (node == null)
			return null;
		@SuppressWarnings("unchecked")
		ComboBox<String> comboBox = (ComboBox<String>) node;
		String group = comboBox.getSelectionModel().getSelectedItem();

		return getDatasetFromBasicController(streamSupplier, MANAGER.getDataSourceFromGroup(group));
	}

}
