package br.inpe.worldwind.view.controllers;

import br.inpe.triangle.conf.Data;
import br.inpe.triangle.conf.DataSource;
import br.inpe.triangle.conf.DataSourceGroup;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class ManagerSetupController {
    private static ManagerSetupController uniqueInstance;
    private Map<SetupView, ObservableList<Node>> elementsView;
    private Map<SetupView, SetupController> controllers;
    private Set<String> selectedBasicScenario;
    private DataSourceGroup dataSourceGroup;

    private ManagerSetupController() {
        this.elementsView = new HashMap<>();
        this.controllers = new HashMap<>();
        this.selectedBasicScenario = new TreeSet<>();
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
     * @return items from basic scenario
     */
    public Set<String> getSelectedBasicScenario() {
        return selectedBasicScenario;
    }

    public List<Data> getDatasetFromBasicController(DataSource dataSource) {
        List<Data> dataset = new ArrayList<>();
        getSelectedBasicScenario().forEach(key -> {
            dataset.add(dataSource.getDataSet().get(key));
        });
        return dataset;
    }


    public List<Data> getDatasetFromBasicController() {
        Supplier<Stream<Node>> streamSupplier = () -> getController(SetupView.BASIC).getPaneSceneChildren().parallelStream();

        Optional<Node> node = streamSupplier.get().filter(component -> component instanceof ComboBox).findFirst();

        if (!node.isPresent())
            return null;

        @SuppressWarnings("unchecked")
        ComboBox<String> comboBox = (ComboBox<String>) node.get();
        String group = comboBox.getSelectionModel().getSelectedItem();

        return getDatasetFromBasicController(getDataSourceFromGroup(group));
    }

    public void saveNodeAsImage(Node node, File file) {
        AnchorPane pane = new AnchorPane();
        pane.getChildren().add(node);
        Scene scene = new Scene(pane);
        WritableImage image = scene.snapshot(null);
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex, "Error to export chart", JOptionPane.ERROR_MESSAGE);
        }
    }
}
