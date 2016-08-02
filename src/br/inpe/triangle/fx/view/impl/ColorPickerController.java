package br.inpe.triangle.fx.view.impl;

import java.net.URL;
import java.util.ResourceBundle;

import br.inpe.triangle.fx.view.SetupController;
import br.inpe.triangle.fx.view.SetupView;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class ColorPickerController implements SetupController {
	@FXML
	private AnchorPane anchorPane;

	@FXML
	private Pane paneSetup;

	@FXML
	private Label lblLayerName;

	@FXML
	private Button btnAddColor;

	@FXML
	private ColorPicker colorPicker;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initPaneSetupEvents();
		addSetupView(SetupView.LAYER_COLOR, anchorPane);

	}

	@Override
	public void initPaneSetupEvents() {
		btnAddColor.setOnAction(event -> {
			Color c = colorPicker.getValue();
			SetupController controller = ManagerSetupController.getInstance().getController(SetupView.STYLE_DATA);
			controller.update(c);
			ColorPickerGUI.closeStage();
		});

	}

	@Override
	public ObservableList<Node> getPaneSceneChildren() {
		return this.paneSetup.getChildren();
	}

	@Override
	public void update(Object object) {
	}

}
