package br.inpe.worldwind.view.controllers.impl;

import java.util.Set;

import javax.swing.JOptionPane;

import br.inpe.worldwind.controller.ShapefileController;
import br.inpe.worldwind.engine.ShapefileProperties;
import br.inpe.worldwind.view.ColorBrewerName;
import br.inpe.worldwind.view.DataProperty;
import br.inpe.worldwind.view.controllers.ApplicationSetupController;
import br.inpe.worldwind.view.controllers.ManagerSetupController.SetupView;
import gov.nasa.worldwind.formats.shapefile.Shapefile;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
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

	@Override
	protected void initPaneSetup() {
		/* tblViewStyle */
		columnColor.setCellValueFactory(cellData -> cellData.getValue().getColorProperty());
		columnValue.setCellValueFactory(cellData -> cellData.getValue().getValueProperty());
		columnDescription.setCellValueFactory(cellData -> cellData.getValue().getDescriptionProperty());

		/* comboColorBrewer */
		comboColorBrewer.setItems(FXCollections.observableArrayList(ColorBrewerName.values()));
		comboColorBrewer.getSelectionModel().select(ColorBrewerName.YlGn);

	}

	@Override
	public void initPaneSetupEvents() {
		btnClassify.setOnAction(event -> {
			if (shp == null)
				return;
			String key = comboColumn.getSelectionModel().getSelectedItem();
			Set<Object> uniqueAttrs = ShapefileProperties.getShapefileUniqueAttributes(shp, key);
			/**
			 * TODO
			 * 
			 * using DataProperty to insert in tableview
			 */
			ObservableList<DataProperty> values = FXCollections.observableArrayList();

			uniqueAttrs.forEach(v -> {
				values.add(new DataProperty(java.awt.Color.black, v, "Test"));
			});

			tblViewStyle.setItems(values);
		});
		btnOK.setOnAction(event -> {
			JOptionPane.showMessageDialog(null, "OK");
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

}