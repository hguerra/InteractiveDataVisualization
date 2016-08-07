package br.inpe.triangle.fx.view.impl;

import java.util.stream.Collectors;

import javax.swing.JOptionPane;

import br.inpe.triangle.fx.view.ApplicationFXAction;
import br.inpe.triangle.fx.view.ScenarioProperty;
import br.inpe.triangle.fx.view.SetupView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.StringConverter;

public class SetupBasicController extends ApplicationSetupController {
	private static final ManagerSetupController MANAGER = ManagerSetupController.getInstance();

	@FXML
	private AnchorPane anchorPane;

	@FXML
	private Pane paneSetup;

	@FXML
	private ComboBox<String> comboLayer;

	@FXML
	private ListView<ScenarioProperty> listViewScenario;

	private ObservableList<String> listOfView;

	@Override
	protected void initPaneSetup() {
		try {
			loadComboLayer();
			loadListView(comboLayer.getSelectionModel().getSelectedItem());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Can't find data folder!", "Data Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public void initPaneSetupEvents() {
		comboLayer.valueProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue == null)
				return;
			loadListView(newValue);
		});
	}

	/**
	 * Elements of view
	 */
	private void loadComboLayer() {
		ObservableList<String> listOfCombLayer = MANAGER.getTitleFromDataSourceGroup();
		/* Add elements in comboLayer */
		comboLayer.setItems(listOfCombLayer);
		/* set selected comboLayer */
		comboLayer.getSelectionModel().selectFirst();
	}

	private void loadListView(String group) {
		listOfView = MANAGER.getTitleFromDataSourceGroup(group);
		ObservableList<ScenarioProperty> scenarioProperties = FXCollections
				.observableArrayList(listOfView.stream().map(ScenarioProperty::new).collect(Collectors.toList()));
		scenarioProperties.forEach(scenarioProperty -> scenarioProperty.selectedProperty()
				.addListener((observable, wasSelected, isSelected) -> {
					if (isSelected) {
						MANAGER.addBasicScenario(scenarioProperty.getName());
					} else {
						MANAGER.removeBasicScenario(scenarioProperty.getName());
					}
				}));
		FXCollections.sort(scenarioProperties);
		listViewScenario.setItems(scenarioProperties);
		listViewScenario.setCellFactory(CheckBoxListCell.forListView(ScenarioProperty::selectedProperty,
				new StringConverter<ScenarioProperty>() {
					@Override
					public String toString(ScenarioProperty object) {
						return object.getName();
					}

					@Override
					public ScenarioProperty fromString(String string) {
						return new ScenarioProperty(string);
					}
				}));
	}

	@Override
	public ObservableList<Node> getPaneSceneChildren() {
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
		if (object instanceof ApplicationFXAction) {
			ApplicationFXAction action = (ApplicationFXAction) object;
			switch (action) {
			case LOAD_COMPONENTS:
				initPaneSetup();
				break;
			case LOAD_LISTENERS:
				break;
			}
		}
	}
}
