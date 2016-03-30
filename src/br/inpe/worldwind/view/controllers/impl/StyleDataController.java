package br.inpe.worldwind.view.controllers.impl;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import br.inpe.util.color.ColorBrewer;
import br.inpe.util.color.ColorBrewer.ColorBrewerName;
import br.inpe.util.color.ColorMath;
import br.inpe.worldwind.controller.ShapefileController;
import br.inpe.worldwind.engine.ShapefileProperties;
import br.inpe.worldwind.view.DataProperty;
import br.inpe.worldwind.view.controllers.ApplicationSetupController;
import br.inpe.worldwind.view.controllers.ManagerSetupController;
import br.inpe.worldwind.view.controllers.ManagerSetupController.SetupView;
import gov.nasa.worldwind.formats.shapefile.Shapefile;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class StyleDataController<T> extends ApplicationSetupController {

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

	/* actual data */
	private Shapefile shp;
	private ColorBrewer colorBrewer = ManagerSetupController.getInstance().getColorBrewer();

	@Override
	protected void initPaneSetup() {
		/* tblViewStyle */
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
		/* comboColorBrewer */

		comboColorBrewer.setItems(FXCollections.observableArrayList(ColorBrewerName.values()));
		comboColorBrewer.getSelectionModel().select(ColorBrewerName.YlGn);

	}

	@Override
	public void initPaneSetupEvents() {
		btnClassify.setOnAction(event -> {
			if (shp == null)
				return;
			/* get uniques attrs */
			String key = comboColumn.getSelectionModel().getSelectedItem();
			Set<Object> uniqueAttrs = ShapefileProperties.getShapefileUniqueAttributes(shp, key);
			/* add list to the tableview */
			tblViewStyle.setItems(classifyByColorBrewer(uniqueAttrs));
		});
		btnOK.setOnAction(event -> {
			Color color = ColorMath.generateRandomColor();
			DataProperty selected = tblViewStyle.getSelectionModel().getSelectedItem();
			if (selected == null)
				return;
			selected.setColor(color);
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
		if (!(object instanceof String))
			return;

		try {
			this.shp = ShapefileController.createShapefile(object.toString());
			Shapefile shpColumns = ShapefileController.createShapefile(object.toString());
			Set<String> columns = ShapefileProperties.getShapefileColumns(shpColumns);
			comboColumn.setItems(FXCollections.observableArrayList(columns));
			comboColumn.getSelectionModel().selectFirst();
		} catch (Exception e) {
			e.printStackTrace();
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
			values.add(new DataProperty(java.awt.Color.black, v, ""));
		});
	}

}