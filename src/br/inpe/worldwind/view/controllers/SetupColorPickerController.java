package br.inpe.worldwind.view.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import br.inpe.worldwind.view.controllers.ManagerSetupController.SetupView;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class SetupColorPickerController implements SetupController {
	@FXML
	private AnchorPane anchorPane;

	@FXML
	private Pane paneSetup;

	@FXML
	private Label lblLayerName;

	@FXML
	private Button btnAddColor;

	@FXML
	private ColorPicker colorPicker;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initPaneSetupEvents();
		addSetupController(SetupView.LAYER_COLOR, anchorPane);

	}

	@Override
	public void initPaneSetupEvents() {
		// TODO Auto-generated method stub

	}

	@Override
	public ObservableList<Node> getPaneSetupChildren() {
		return this.paneSetup.getChildren();
	}

}
