package br.inpe.worldwind.view.controllers.impl;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import br.inpe.triangle.conf.Data;
import br.inpe.util.color.ColorBrewer;
import br.inpe.util.color.ColorBrewer.ColorBrewerName;
import br.inpe.util.color.ColorMath;
import br.inpe.util.color.SwingUtils;
import br.inpe.worldwind.controller.ShapefileController;
import br.inpe.worldwind.engine.ShapefileProperties;
import br.inpe.worldwind.view.DataProperty;
import br.inpe.worldwind.view.controllers.ApplicationSetupController;
import br.inpe.worldwind.view.controllers.ManagerSetupController;
import br.inpe.worldwind.view.controllers.ManagerSetupController.SetupView;
import br.inpe.worldwind.view.impl.ColorPickerGUI;
import br.inpe.worldwind.view.impl.StyleData;
import gov.nasa.worldwind.formats.shapefile.Shapefile;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class StyleDataController<T> extends ApplicationSetupController {
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
	private TableColumn<DataProperty, java.awt.Color> columnColor;

	@FXML
	private TableColumn<DataProperty, Object> columnValue;

	@FXML
	private TableColumn<DataProperty, String> columnDescription;

	@FXML
	private ComboBox<ColorBrewerName> comboColorBrewer;

	@FXML
	private Button btnClassify;

	@FXML
	private Button btnApply;

	@FXML
	private Button btnOK;
	/* ColorBrewer */
	private ColorBrewer colorBrewer = MANAGER.getColorBrewer();
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
		columnColor.setCellFactory(column -> {
			return new TableCell<DataProperty, java.awt.Color>() {
				@Override
				protected void updateItem(java.awt.Color item, boolean empty) {
					super.updateItem(item, empty);
					if (item == null || empty) {
						setText(null);
						setStyle("");
					} else {
						setStyle(new StringBuilder().append("-fx-background-color: ").append(ColorMath.toHex(item))
								.toString());
					}
				}
			};
		});
		/* set if is editable */
		columnDescription.setCellFactory(TextFieldTableCell.<DataProperty> forTableColumn());

		/* comboColorBrewer */
		comboColorBrewer.setItems(FXCollections.observableArrayList(ColorBrewerName.values()));
		comboColorBrewer.getSelectionModel().select(ColorBrewerName.YlGn);

	}

	@Override
	public void initPaneSetupEvents() {
		/* Buttons */
		btnClassify.setOnAction(event -> {
			try {
				Shapefile shp = ShapefileController.createShapefile(data.getFilepath());
				if (shp == null)
					return;
				/* get uniques attrs */
				String key = comboColumn.getSelectionModel().getSelectedItem();
				Set<Object> uniqueAttrs = ShapefileProperties.getShapefileUniqueAttributes(shp, key);
				/* add list to the tableview */
				tblViewStyle.setItems(classifyByColorBrewer(uniqueAttrs));
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		btnApply.setOnAction(event -> {
			// maps
			Map<Double, java.awt.Color> awtColors = new HashMap<>();
			Map<Double, String> description = new HashMap<>();
			// insert values
			tblViewStyle.getItems().forEach(data -> {
				try {
					/**
					 * TODO refactor in the future, remove casting
					 */
					awtColors.put((Double) data.getValue(), data.getColor());
					description.put((Double) data.getValue(), data.getDescription());
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
			// apply
			this.data.setAwtColors(awtColors);
			this.data.setDescription(description);
		});
		btnOK.setOnAction(event -> {
			StyleData.closeStage();
		});

		/* Table */
		columnColor.setOnEditStart(new EventHandler<TableColumn.CellEditEvent<DataProperty, Color>>() {
			@Override
			public void handle(CellEditEvent<DataProperty, Color> event) {
				try {
					new ColorPickerGUI().start(new Stage());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		columnDescription.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<DataProperty, String>>() {
			@Override
			public void handle(CellEditEvent<DataProperty, String> event) {
				event.getTableView().getItems().get(event.getTablePosition().getRow())
						.setDescription(event.getNewValue());
			}
		});

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
				java.awt.Color awtColor = SwingUtils.toAwt(javafxColor);
				item.setColor(awtColor);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	private ObservableList<DataProperty> classifyByColorBrewer(Set<Object> uniqueAttrs) {
		ObservableList<DataProperty> values = FXCollections.observableArrayList();
		ColorBrewerName selected = comboColorBrewer.getSelectionModel().getSelectedItem();
		Map<Integer, List<Color>> colors = colorBrewer.getAwtColors(colorBrewer.getValue(selected));

		int attrSize = uniqueAttrs.size();

		if (colors.isEmpty())
			defaultClassify(uniqueAttrs, values);
		else {
			int colorsSize = colors.size();
			int cont = 0;
			Iterator<Object> iterator = uniqueAttrs.iterator();
			List<Color> listColors = null;
			/**
			 * ColorBrewer values: 3,4,5,6,7,8,9
			 */
			if (attrSize < 3)
			// values < 3
			{
				listColors = colors.get(3);
			} else if (attrSize > colorsSize)
			// values > 9
			{
				int dif = attrSize - colorsSize;
				List<Color> newListColor = new ArrayList<>();
				newListColor.addAll(colors.get(9));

				for (int i = 0; i < dif; i++) {
					newListColor.add(Color.BLACK);
				}

				listColors = newListColor;
			} else
			// values values >= 3 && values <= 9
			{
				listColors = colors.get(attrSize);
			}

			while (iterator.hasNext()) {
				values.add(new DataProperty(listColors.get(cont), iterator.next(), ""));
				cont++;
			}
		} // END
		return values;
	}

	private void defaultClassify(Set<Object> uniqueAttrs, ObservableList<DataProperty> values) {
		uniqueAttrs.forEach(v -> {
			values.add(new DataProperty(java.awt.Color.BLACK, v, ""));
		});
	}

}