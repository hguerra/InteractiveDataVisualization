package br.inpe.worldwind.view.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import br.inpe.worldwind.view.controllers.ManagerSetupController.SetupView;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class SetupDataAttributeController implements SetupController {
	@FXML
	private AnchorPane anchorPane;

	@FXML
	private Pane paneSetup;

	@FXML
	private Label lblLayerName;

	@FXML
	private Label lblAttribute;

	@FXML
	private Label lblValues;

	@FXML
	private ListView<?> listViewScenario;

	@FXML
	private ContextMenu contextMenu;

	@FXML
	private MenuItem menuItemRemove;

	@FXML
	private MenuItem menuItemAddColor;

	@FXML
	private Button btnTrash;

	@FXML
	private TextField txtAttribute;

	@FXML
	private Button btnLoad;

	@FXML
	private Button btnSave;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initPaneSetupEvents();
		addSetupController(SetupView.LAYER_ATTRIBUTES, anchorPane);

	}

	@Override
	public void initPaneSetupEvents() {
		

	}

	@Override
	public ObservableList<Node> getPaneSetupChildren() {
		return this.paneSetup.getChildren();
	}

}
