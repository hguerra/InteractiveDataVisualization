package br.inpe.triangle.fx.view.impl;

import br.inpe.triangle.data.Data;
import br.inpe.triangle.data.DataSource;
import br.inpe.triangle.fx.view.ApplicationFXAction;
import br.inpe.triangle.fx.view.SetupController;
import br.inpe.triangle.fx.view.SetupView;
import br.inpe.triangle.gdal.GeoFormat;
import br.inpe.triangle.wwj.layer.ShapefileController;

import com.google.common.base.Splitter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.File;
import java.util.List;
import java.util.regex.Pattern;

public class SetupLayerController extends ApplicationSetupController {
    private static final ManagerSetupController MANAGER = ManagerSetupController.getInstance();

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Pane paneSetup;

    @FXML
    private ListView<String> listViewScenario;

    @FXML
    private ContextMenu contextMenu;

    @FXML
    private MenuItem menuItemRemove;

    @FXML
    private Button btnTrash;

    @FXML
    private TextField txtTitle;

    @FXML
    private TextArea txtReference;

    @FXML
    private Button btnLoad;

    @FXML
    private Button btnColor;

    @FXML
    private Button btnAddData;

    private ObservableList<String> listOfView = FXCollections.observableArrayList();

    private Pattern pattern = Pattern.compile("[_./]", Pattern.CASE_INSENSITIVE);

    @Override
    protected void initPaneSetup() {

    }


    @Override
    public void initPaneSetupEvents() {

        btnLoad.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Shapefiles (*.shp)", "*.shp");
            /**
             * TODO add extension filter .geojson
             * "Shapefiles (*.shp), GeoJson (*.geojson)", "*.shp", "*.geojson"
             */
            fileChooser.getExtensionFilters().add(extFilter);
            File file = fileChooser.showOpenDialog(null);
            if (file != null) {
                String path = file.getAbsolutePath();
                String title = txtTitle.getText();
                String reference = txtReference.getText();
                /* title */
                if (title.equals(""))
                    title = ShapefileController.getDisplayName(path);

				/* add Data */
                Data data = new Data();
                data.setTitle(title);
                data.setFormat(GeoFormat.SHAPEFILE);
                data.setFilepath(path);

				/* reference */
                if (!reference.equals(""))
                    data.setReference(reference);

                // add in observable list
                listOfView.add(title);
                // add data in Manager
                MANAGER.addData(title, data);
            }
            // end
            listViewScenario.setItems(listOfView);
        });

        menuItemRemove.setOnAction(event -> {
            int index = listViewScenario.getSelectionModel().getSelectedIndex();

            if (index < 0)
                return;

            listOfView.remove(index);
            listViewScenario.setItems(listOfView);
        });

        btnTrash.setOnAction(event -> listOfView.clear());


        btnColor.setOnAction(event -> {
            try {
                /* data */
                if (!listOfView.isEmpty()) {
                    String item = listViewScenario.getSelectionModel().getSelectedItem();
                    if (item == null) {
                        JOptionPane.showMessageDialog(null, "Please selected some data before!", "NO DATA SELECTED",
                                JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    new StyleData().start(new Stage());

                    SetupController controller = MANAGER.getController(SetupView.STYLE_DATA);
                    Data selectedData = MANAGER.getData(item);
                    controller.update(selectedData);
                } else
                    JOptionPane.showMessageDialog(null, "Please add some data before!", "NO DATA",
                            JOptionPane.WARNING_MESSAGE);

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        btnAddData.setOnAction(event -> {
            if (listOfView.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No data in scene", "Add data", JOptionPane.WARNING_MESSAGE);
                return;
            }
            final StringBuilder infoMessage = new StringBuilder();
            Task<Void> task = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    listOfView.forEach(titleData -> {
                        // Get data from session
                        Data data = MANAGER.getData(titleData);
                        if (data.getAwtColors().isEmpty()) {
                            infoMessage.append(data.getTitle()).append("\n");
                            return;
                        }

                        List<String> datasetGroupIterator = Splitter.on(pattern)
                                .trimResults()
                                .omitEmptyStrings()
                                .splitToList(titleData);

                        if (datasetGroupIterator.size() < 2)
                            return;

                        String nameOfDataSourceGroup = datasetGroupIterator.get(0); // title
                        String nameOfDataSource = datasetGroupIterator.get(1); // year
                        data.setTitle(nameOfDataSourceGroup);
                        data.setDate(nameOfDataSource);
                        //create a new datasource
                        DataSource dataSource = new DataSource();
                        dataSource.addData(nameOfDataSource, data);
                        //add in dataSourceGroup
                        MANAGER.addDataSource(nameOfDataSourceGroup, dataSource);
                        //Remove data from session
                        MANAGER.removeData(data);
                    });
                    MANAGER.getController(SetupView.BASIC).update(ApplicationFXAction.LOAD_COMPONENTS);
                    return null;
                }
            };
            task.setOnSucceeded(onSucceeded -> {
                if (!infoMessage.toString().isEmpty())
                    JOptionPane.showMessageDialog(null, infoMessage.toString(), "Color not defined", JOptionPane.WARNING_MESSAGE);
                else
                    JOptionPane.showMessageDialog(null, "Data add with succeeded");
            });

            new Thread(task).start();
        });
    }


    @Override
    protected SetupView getSetupView() {
        return SetupView.LAYER;
    }

    @Override
    protected Pane getPaneView() {
        return paneSetup;
    }

    ListView<String> getListViewScenario() {
        return listViewScenario;
    }

    @Override
    public void update(Object object) {

    }


}
