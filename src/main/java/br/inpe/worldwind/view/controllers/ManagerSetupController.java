package br.inpe.worldwind.view.controllers;

import br.inpe.triangle.conf.Data;
import br.inpe.triangle.conf.DataSource;
import br.inpe.triangle.conf.DataSourceGroup;
import gov.nasa.worldwind.layers.Layer;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.*;

public class ManagerSetupController {
    public enum SetupView {
        BASIC, LAYER, LAYER_ATTRIBUTES, LAYER_COLOR, DATABASE, KINECT, PROFILE, STYLE_DATA;
    }

    /* Javafx */
    private static ManagerSetupController uniqueInstance;
    private Map<SetupView, ObservableList<Node>> elementsView;
    private Map<SetupView, SetupController> controllers;
    private List<String> selectedBasicScenario;
    /* DataSource Group */
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

    public synchronized ObservableList<Node> removeElement(SetupView setup) {
        return this.elementsView.remove(setup);
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

    public synchronized SetupController removeController(SetupView view) {
        return this.controllers.remove(view);
    }

    public synchronized SetupController getController(SetupView view) {
        return this.controllers.get(view);
    }

    /**
     * Color
     * <p>
     * TODO refactor this using datasource
     */
    private Map<String, Color[]> attributesColor = new HashMap<>();

    public Color[] addAttributesColor(String attrName, Color... color) {
        return attributesColor.put(attrName, color);
    }

    public Color[] removeAttributesColor(String attrName) {
        return attributesColor.remove(attrName);
    }

    public Map<String, Color[]> getAttributesColor() {
        return attributesColor;
    }

    public Color[] getColors(String attrName) {
        return attributesColor.get(attrName);
    }

    /**
     * ColorBrewer
     *
     * @return
     */

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

    /**
     * remove Data from memory
     *
     * @param name
     * @return
     */
    public Data removeData(String name) {
        return this.dataSourceGroup.removeData(name);
    }

    public Data getData(String name) {
        return this.dataSourceGroup.getData(name);
    }

    /**
     * Get specific list of layers
     *
     * @param title
     * @return
     */
    public List<Layer> getLayerFromDataSource(String title) {
        return this.dataSourceGroup.getLayersFromDataSource().get(title);
    }

    /**
     * Get all layers in memory
     *
     * @return
     */
    public Map<String, List<Layer>> getLayersFromDataSource() {
        return this.dataSourceGroup.getLayersFromDataSource();
    }

    /**
     * Get Layer Title from DataSource
     *
     * @return
     */
    public ObservableList<String> getTitleFromDataSource() {
        return this.dataSourceGroup.getTitleFromDataSource();
    }

    /**
     * DefaultData
     */

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
     *
     * @param scenario
     * @return
     */
    public boolean addBasicScenario(String scenario){
        return this.selectedBasicScenario.add(scenario);
    }

    /**
     *
     * @param scenario
     * @return
     */
    public boolean removeBasicScenario(String scenario){
        return this.selectedBasicScenario.remove(scenario);
    }

    /**
     *
     * @return
     */
    public List<String> getSelectedBasicScenario() {
        return Collections.unmodifiableList(selectedBasicScenario);
    }
}
