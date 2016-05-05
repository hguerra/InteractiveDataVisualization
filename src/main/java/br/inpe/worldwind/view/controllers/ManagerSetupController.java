package br.inpe.worldwind.view.controllers;

import br.inpe.triangle.conf.Data;
import br.inpe.triangle.conf.DataSource;
import br.inpe.triangle.conf.DataSourceGroup;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

import java.util.*;

public class ManagerSetupController {
    private static ManagerSetupController uniqueInstance;
    private Map<SetupView, ObservableList<Node>> elementsView;
    private Map<SetupView, SetupController> controllers;
    private List<String> selectedBasicScenario;
    private DataSourceGroup dataSourceGroup;

    private ManagerSetupController() {
        this.elementsView = new HashMap<>();
        this.controllers = new HashMap<>();
        this.selectedBasicScenario = new ArrayList<>();
        this.dataSourceGroup = new DataSourceGroup();
    }

    /**
     * Singleton
     *
     * @return
     */
    public static ManagerSetupController getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new ManagerSetupController();
        }
        return uniqueInstance;
    }

    /**
     * Elements of view
     *
     * @param setup
     * @param parent
     * @return
     */
    public synchronized ObservableList<Node> addElement(SetupView setup, Pane parent) {
        return this.elementsView.put(setup, parent.getChildren());
    }

    public synchronized ObservableList<Node> addElement(SetupView setup, ObservableList<Node> parent) {
        return this.elementsView.put(setup, parent);
    }

    public synchronized ObservableList<Node> getElement(SetupView key) {
        return this.elementsView.get(key);
    }

    /**
     * Controllers
     *
     * @param view
     * @param controller
     * @return
     */
    public synchronized SetupController addController(SetupView view, SetupController controller) {
        return this.controllers.put(view, controller);
    }

    public synchronized SetupController getController(SetupView view) {
        return this.controllers.get(view);
    }
    /**
     * Data Source
     */
    /**
     * add data in DataSource
     *
     * @param name
     * @param data
     * @return
     */
    public Data addData(String name, Data data) {
        return this.dataSourceGroup.addData(name, data);
    }

    public Data getData(String name) {
        return this.dataSourceGroup.getData(name);
    }

    public Data removeData(Data data) {
        return this.dataSourceGroup.removeData(data);
    }

    public DataSource addDataSource(String group, DataSource dataSource) {
        return this.dataSourceGroup.addDataSource(group, dataSource);
    }

    /**
     * @param group
     * @return
     */
    public DataSource getDataSourceFromGroup(String group) {
        return this.dataSourceGroup.getDataSourceFromGroup(group);
    }

    /**
     * @return
     */
    public ObservableList<String> getTitleFromDataSourceGroup() {
        return this.dataSourceGroup.getTitleFromDataSourceGroup();
    }

    public ObservableList<String> getTitleFromDataSourceGroup(String group) {
        return this.dataSourceGroup.getTitleFromDataSourceGroup(group);
    }

    /**
     * @param scenario
     * @return
     */
    public boolean addBasicScenario(String scenario) {
        return this.selectedBasicScenario.add(scenario);
    }

    /**
     * @param scenario
     * @return
     */
    public boolean removeBasicScenario(String scenario) {
        return this.selectedBasicScenario.remove(scenario);
    }

    /**
     * @return
     */
    public List<String> getSelectedBasicScenario() {
        return Collections.unmodifiableList(selectedBasicScenario);
    }
}
