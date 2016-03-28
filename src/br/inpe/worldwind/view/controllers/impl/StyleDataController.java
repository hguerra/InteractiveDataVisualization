package br.inpe.worldwind.view.controllers.impl;

import javax.swing.JOptionPane;

import br.inpe.worldwind.view.ColorBrewerName;
import br.inpe.worldwind.view.controllers.ApplicationSetupController;
import br.inpe.worldwind.view.controllers.ManagerSetupController.SetupView;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class StyleDataController extends ApplicationSetupController {

	@FXML
	private AnchorPane anchorPane;

	@FXML
	private Pane paneSetup;

	@FXML
	private Label lblLayerName;

	@FXML
	private ComboBox<?> comboColumn;

	@FXML
	private TableView<?> tblViewStyle;

	@FXML
	private TableColumn<?, ?> columnColor;

	@FXML
	private TableColumn<?, ?> columnValue;

	@FXML
	private TableColumn<?, ?> columnDescription;

	@FXML
	private ComboBox<ColorBrewerName> comboColorBrewer;

	@FXML
	private Button btnClassify;

	@FXML
	private Button btnApply;

	@FXML
	private Button btnOK;

	@Override
	protected void initPaneSetup() {
		comboColorBrewer.setItems(FXCollections.observableArrayList(ColorBrewerName.values()));
		comboColorBrewer.getSelectionModel().select(ColorBrewerName.YlGn);
	}

	@Override
	public void initPaneSetupEvents() {
		btnOK.setOnAction(event -> {
			JOptionPane.showMessageDialog(null, "OK");
		});
	}

	@Override
	protected SetupView getSetupView() {
		return SetupView.BASIC;
	}

	@Override
	protected Pane getPaneView() {
		return paneSetup;
	}

}