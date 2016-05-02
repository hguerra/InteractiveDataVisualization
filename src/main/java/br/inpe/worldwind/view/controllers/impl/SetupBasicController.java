package br.inpe.worldwind.view.controllers.impl;

import br.inpe.worldwind.view.ScenarioProperty;
import br.inpe.worldwind.view.controllers.ApplicationSetupController;
import br.inpe.worldwind.view.controllers.ManagerSetupController;
import br.inpe.worldwind.view.controllers.ManagerSetupController.SetupView;
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

import java.util.stream.Collectors;

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
        loadComboLayer();
        loadListView();
    }
    @Override
    public void initPaneSetupEvents() {
        comboLayer.valueProperty().addListener((observable, oldValue, newValue) -> {
            /**
             * FIXME Heitor
             * Quando retirado da null pointer
             */
            MANAGER.getController(SetupView.BASIC).update(newValue);//
            loadListView();
            /**
             * TODO Heitor
             * se executar sem selecionar um padrao de cor, nao fazer nada!!
             */

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

    private void loadListView() {
        /* get selected comboLayer */
        String group = comboLayer.getSelectionModel().getSelectedItem();
        listOfView = MANAGER.getTitleFromDataSourceGroup(group);
        /* add elements based on comboLayer */
        ObservableList<ScenarioProperty> scenarioProperties = FXCollections.observableArrayList(
                listOfView.stream().map(ScenarioProperty::new).collect(Collectors.toList())
        );
        scenarioProperties.forEach(scenarioProperty -> scenarioProperty.selectedProperty().addListener((observable, wasSelected, isSelected) -> {
            if (isSelected) {
                MANAGER.addBasicScenario(scenarioProperty.getName());
            } else {
                MANAGER.removeBasicScenario(scenarioProperty.getName());
            }
        }));
        listViewScenario.setItems(scenarioProperties);
        listViewScenario.setCellFactory(CheckBoxListCell.forListView(ScenarioProperty::selectedProperty, new StringConverter<ScenarioProperty>() {
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
        if (object == null) {
            initPaneSetup();
        }
    }
}
