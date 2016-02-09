package br.inpe.worldwind.view.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import br.inpe.worldwind.view.controllers.ManagerSetupController.SetupView;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class SetupBasicController implements SetupController {
	@FXML
	private AnchorPane anchorPane;

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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initPaneSetupEvents();
		addSetupController(SetupView.BASIC, paneSetup);

	}

	@Override
	public void initPaneSetupEvents() {
		btnTrash.setOnAction(event -> {
			System.out.println("btnTrash");
		});
		
		toggleBtnKinect.setOnAction(event -> {
			System.out.println("toggleBtnKinect:" + toggleBtnKinect.isSelected());
		});

	}

	@Override
	public ObservableList<Node> getPaneSetupChildren() {
		return this.paneSetup.getChildren();
	}
}
