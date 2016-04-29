package br.inpe.worldwind.view.controllers.impl;

import br.inpe.triangle.conf.Data;
import br.inpe.util.color.ColorBrewerNatureData;
import br.inpe.util.color.ColorMath;
import br.inpe.util.color.SwingUtils;
import br.inpe.worldwind.controller.ShapefileController;
import br.inpe.worldwind.engine.ShapefileProperties;
import br.inpe.worldwind.view.DataProperty;
import br.inpe.worldwind.view.controllers.ApplicationSetupController;
import br.inpe.worldwind.view.controllers.ManagerSetupController;
import br.inpe.worldwind.view.controllers.ManagerSetupController.SetupView;
import br.inpe.worldwind.view.impl.ColorPickerGUI;
import gov.nasa.worldwind.formats.shapefile.Shapefile;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.geotools.brewer.color.BrewerPalette;

import java.awt.*;
import java.util.*;

public class StyleDataController extends ApplicationSetupController {
    private static final ManagerSetupController MANAGER = ManagerSetupController.getInstance();

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Pane paneSetup;

    @FXML
    private Label lblLayerName;

    @FXML
    private ComboBox<String> comboColumn;

    @FXML
    private TableView<DataProperty> tblViewStyle;

    @FXML
    private TableColumn<DataProperty, Color> columnColor;

    @FXML
    private TableColumn<DataProperty, Object> columnValue;

    @FXML
    private TableColumn<DataProperty, String> columnDescription;

    @FXML
    private ComboBox<String> comboColorScheme;

    @FXML
    private Button btnClassify;

    @FXML
    private Button btnApply;

    @FXML
    private Button btnOK;

    @FXML
    public ChoiceBox<ColorBrewerNatureData> comboNature;

    @FXML
    public ComboBox<Integer> comboClasses;
    /* actual data */
    private Data data;

    @Override
    protected void initPaneSetup() {
        /* tblViewStyle */
        tblViewStyle.setEditable(true);
        /* set cell value factory (type of things) */
        columnValue.setCellValueFactory(cellData -> cellData.getValue().getValueProperty());
        columnDescription.setCellValueFactory(cellData -> cellData.getValue().getDescriptionProperty());
        columnColor.setCellValueFactory(cellData -> cellData.getValue().getColorProperty());
        columnColor.setCellFactory(column -> new TableCell<DataProperty, Color>() {
            @Override
            protected void updateItem(Color item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(null);
                    setStyle("");
                } else {
                    setStyle(new StringBuilder().append("-fx-background-color: ").append(ColorMath.toHex(item))
                            .toString());
                }
            }
        });
        /* set if is editable */
        columnDescription.setCellFactory(TextFieldTableCell.forTableColumn());

        /*comboNumberDataClasses*/
        comboClasses.setItems(FXCollections.observableArrayList(Arrays.asList(2, 3, 4, 5, 6, 7, 8, 9, 10, 11)));
        comboClasses.getSelectionModel().selectFirst();

        /*comboNatureData*/
        comboNature.setItems(FXCollections.observableArrayList(ColorBrewerNatureData.values()));
        comboNature.getSelectionModel().selectFirst();

		/* comboColorBrewer */
        comboColorScheme.setItems(getComboColorBrewer());
        comboColorScheme.getSelectionModel().selectFirst();
    }


    @Override
    public void initPaneSetupEvents() {
        /*Combo Events*/
        comboNature.setOnAction(event -> {
            comboColorScheme.setItems(getComboColorBrewer());
            comboColorScheme.getSelectionModel().selectFirst();
        });
        /* Buttons */
        btnClassify.setOnAction(event -> {
            try {
                Shapefile shp = ShapefileController.createShapefile(data.getFilepath());
                if (shp == null)
                    return;
                /* get uniques attrs */
                String key = comboColumn.getSelectionModel().getSelectedItem();
                Set<Object> uniqueAttrs = ShapefileProperties.getShapefileUniqueAttributes(shp, key);
                data.setColumn(key);
                /* add list to the tableview */
                tblViewStyle.setItems(classifyUsingGeotools(uniqueAttrs));

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        btnApply.setOnAction(event -> {
            // maps
            Map<Object, Color> awtColors = new HashMap<>();
            Map<Object, String> description = new HashMap<>();
            // insert values
            tblViewStyle.getItems().forEach(data -> {
                try {
                    awtColors.put(data.getValue(), data.getColor());
                    description.put(data.getValue(), data.getDescription());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            // apply
            this.data.setAwtColors(awtColors);
            this.data.setDescription(description);
        });
        btnOK.setOnAction(event -> {
            Stage stage = (Stage) btnOK.getScene().getWindow();
            stage.close();
        });

		/* Table */
        columnColor.setOnEditStart(event -> {
            try {
                new ColorPickerGUI().start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        columnDescription.setOnEditCommit(event -> event.getTableView().getItems().get(event.getTablePosition().getRow())
                .setDescription(event.getNewValue()));

    }

    @Override
    protected SetupView getSetupView() {
        return SetupView.STYLE_DATA;
    }

    @Override
    protected Pane getPaneView() {
        return paneSetup;
    }

    @Override
    public void update(Object object) {
        if (object instanceof Data) {
            try {
                this.data = (Data) object;

                Shapefile shpColumns = ShapefileController.createShapefile(data.getFilepath());
                Set<String> columns = ShapefileProperties.getShapefileColumns(shpColumns);
                comboColumn.setItems(FXCollections.observableArrayList(columns));
                comboColumn.getSelectionModel().selectFirst();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (object instanceof javafx.scene.paint.Color) {
            DataProperty item = tblViewStyle.getSelectionModel().getSelectedItem();
            if (item == null)
                return;
            try {
                // javafx
                javafx.scene.paint.Color javafxColor = (javafx.scene.paint.Color) object;
                // java.awt.Color
                Color awtColor = SwingUtils.toAwt(javafxColor);
                item.setColor(awtColor);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private ObservableList<DataProperty> classifyUsingGeotools(Set<Object> uniqueAttrs) {
    ObservableList<DataProperty> values = FXCollections.observableArrayList();

        String colorScheme = comboColorScheme.getSelectionModel().getSelectedItem();

        Color[] colors = org.geotools.brewer.color.ColorBrewer.instance().getPalette(colorScheme).getColors();

        Iterator<Object> attrsIterator = uniqueAttrs.iterator();
        for (Color color : colors) {
            if (attrsIterator.hasNext()) {
                Object unique = attrsIterator.next();
                values.add(new DataProperty(color, unique, ""));
            }
        }
        return values;
    }

    private ObservableList<String> getComboColorBrewer() {
        Integer numberClasses = comboClasses.getSelectionModel().getSelectedItem();
        ColorBrewerNatureData natureData = comboNature.getSelectionModel().getSelectedItem();
        BrewerPalette[] brewerPalettes = natureData.getPallete(numberClasses);

        ObservableList<String> comboColorScheme = FXCollections.observableArrayList();
        for (BrewerPalette brewerPalette : brewerPalettes) {
            comboColorScheme.add(brewerPalette.getName());
        }

        return comboColorScheme;
    }
}