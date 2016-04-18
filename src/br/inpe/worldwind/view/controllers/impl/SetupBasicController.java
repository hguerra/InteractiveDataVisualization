package br.inpe.worldwind.view.controllers.impl;

import br.inpe.triangle.defaultproperties.DefaultTriangleProperties;
import br.inpe.worldwind.view.controllers.ApplicationSetupController;
import br.inpe.worldwind.view.controllers.ManagerSetupController;
import br.inpe.worldwind.view.controllers.ManagerSetupController.SetupView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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

	private ObservableList<String> listOfCombLayer;
	private ObservableList<String> listOfView;

	@Override
	protected void initPaneSetup() {
		listOfCombLayer = MANAGER.getTitleFromDataSourceGroup();
		/* Add elements in comboLayer */
		comboLayer.setItems(listOfCombLayer);
		/* set selected comboLayer */
		comboLayer.getSelectionModel().selectFirst();
		/* get selected comboLayer */
		String group = comboLayer.getSelectionModel().getSelectedItem();
		listOfView = MANAGER.getTitleFromDataSourceGroup(group);
		/* add elements based on comboLayer */
		listViewScenario.setItems(listOfView);
	}

	@Override
	public void initPaneSetupEvents() {
		menuItemRemove.setOnAction(event -> {
			int index = listViewScenario.getSelectionModel().getSelectedIndex();

			if (index < 0)
				return;

			listOfView.remove(index);
			listViewScenario.setItems(listOfView);
		});
		btnTrash.setOnAction(event -> {
			listOfView.clear();
		});

		toggleBtnKinect.setOnAction(event -> {
			DefaultTriangleProperties.getInstance().setKinectEnable(toggleBtnKinect.isSelected());
			System.out.println("toggleBtnKinect:" + toggleBtnKinect.isSelected());
		});
		
		comboLayer.valueProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				MANAGER.getController(SetupView.BASIC).update(newValue);
			}
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
