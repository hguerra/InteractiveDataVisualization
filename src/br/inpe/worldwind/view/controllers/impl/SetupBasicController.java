package br.inpe.worldwind.view.controllers.impl;

import br.inpe.worldwind.view.controllers.ApplicationSetupController;
import br.inpe.worldwind.view.controllers.ManagerSetupController;
import br.inpe.worldwind.view.controllers.ManagerSetupController.SetupView;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class SetupBasicController extends ApplicationSetupController {
	private static final ManagerSetupController MANAGER = ManagerSetupController.getInstance();

	@FXML
	private AnchorPane anchorPane;

	@FXML
	private Pane paneSetup;

	@FXML
	private Label lblLayerName;

	@FXML
	private Label lblData;

	@FXML
	private ComboBox<String> comboLayer;

	@FXML
	private Label lblScenario;

	@FXML
	private ListView<String> listViewScenario;

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
	protected void initPaneSetup() {
		/*Add elements in comboLayer*/
		comboLayer.setItems(MANAGER.getTitleFromDataSourceGroup());
		/*set selected comboLayer*/
		comboLayer.getSelectionModel().selectFirst();
		/*get selected comboLayer*/
		String group = comboLayer.getSelectionModel().getSelectedItem();
		/*add elements based on comboLayer*/
		listViewScenario.setItems(MANAGER.getTitleFromDataSourceGroup(group));
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

	@Override
	protected SetupView getSetupView() {
		return SetupView.BASIC;
	}

	@Override
	protected Pane getPaneView() {
		return paneSetup;
	}

	@Override
	public void update(Object object) {
		// TODO Auto-generated method stub

	}
}
