package br.inpe.worldwind.view.controllers.impl;

import java.net.URL;
import java.util.ResourceBundle;

import br.inpe.worldwind.view.controllers.ManagerSetupController;
import br.inpe.worldwind.view.controllers.SetupController;
import br.inpe.worldwind.view.controllers.ManagerSetupController.SetupView;
import br.inpe.worldwind.view.impl.DataAttributesGUI;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

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
	private ListView<Color> listViewScenario;

	@FXML
	private ContextMenu contextMenu;

	@FXML
	private MenuItem menuItemRemove;

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
	
	private ObservableList<Color> listOfView = FXCollections.observableArrayList();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initPaneSetupEvents();
		addSetupController(SetupView.LAYER_ATTRIBUTES, anchorPane);
	}

	@Override
	public void initPaneSetupEvents() {
		
		btnAddColor.setOnAction(event -> {
			Color value = colorPicker.getValue();
			listOfView.add(value);
			refreshScenario();
		});
		
	
		menuItemRemove.setOnAction(event -> {
			int index = listViewScenario.getSelectionModel().getSelectedIndex();
			if(index < 0) return;
			listOfView.remove(index);
		});
		
		btnTrash.setOnAction(event -> {
			listOfView.clear();
		});
	
		btnSave.setOnAction(event -> {
			String attr = txtAttribute.getText();
			
			if(attr.isEmpty())
				attr = "attr";
			
			ManagerSetupController manager = ManagerSetupController.getInstance();
			
			
			Color[] colors = new Color[listOfView.size()];
			
			for(int i = 0; i < colors.length; i ++){
				colors[i] = listOfView.get(i);
			}
			manager.addAttributesColor(attr, colors);
			// end
			DataAttributesGUI.closeStage();
		});

	}

	@Override
	public ObservableList<Node> getPaneSetupChildren() {
		return this.paneSetup.getChildren();
	}
	
	private void refreshScenario(){
		listViewScenario.setItems(listOfView);
	}

}
