package br.inpe.worldwind.view.controllers.impl;

import java.net.URL;
import java.util.ResourceBundle;

import br.inpe.worldwind.view.controllers.SetupController;
import br.inpe.worldwind.view.controllers.ManagerSetupController.SetupView;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class SetupDataAttributeController2 implements SetupController {
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
	private Button btnTrash;

	@FXML
	private TextField txtAttribute;

	@FXML
	private Button btnAddColor;

	@FXML
	private Button btnSave;

	@FXML
	private ColorPicker colorPicker;
	
    @FXML
    private TableView<?> tableValues;

	@FXML
	private TableColumn<?, ?> columnAttr;

	@FXML
	private TableColumn<?, ?> columnColor;

	@FXML
	private Button btnSearch;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		btnAddColor.setOnAction(event -> {
			int index = tableValues.getSelectionModel().getSelectedIndex();
		});

	}

	@Override
	public void initPaneSetupEvents() {
		initPaneSetupEvents();
		addView(SetupView.LAYER_ATTRIBUTES, anchorPane);

	}

	@Override
	public ObservableList<Node> getPaneSetupChildren() {
		return this.paneSetup.getChildren();
	}
	
	private void mockValues(){
		
	}
	@Override
	public void update(Object object) {
		// TODO Auto-generated method stub
		
	}
}
