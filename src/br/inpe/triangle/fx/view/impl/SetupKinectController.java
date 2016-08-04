package br.inpe.triangle.fx.view.impl;

import java.net.URL;
import java.util.ResourceBundle;

import br.inpe.triangle.fx.view.SetupController;
import br.inpe.triangle.fx.view.SetupView;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class SetupKinectController implements SetupController {
	@FXML
	private AnchorPane anchorPane;

	@FXML
	private Pane paneSetup;

	@FXML
	private Label lblLayerName;

	@FXML
	private Label lblKinect;

	@FXML
	private ToggleButton toggleBtnKinect;

	@FXML
	private ComboBox<?> comboStream;

	@FXML
	private Label lblDevice;

	@FXML
	private Label lblDeviceNumber;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initPaneSetupEvents();
		addSetupView(SetupView.KINECT, anchorPane);

	}

	@Override
	public void initPaneSetupEvents() {
		toggleBtnKinect.setOnAction(event -> {
			System.out.println("toggleBtnKinect:" + toggleBtnKinect.isSelected());
		});

	}

	@Override
	public ObservableList<Node> getPaneSceneChildren() {
		return this.paneSetup.getChildren();
	}
	
	@Override
	public void update(Object object) {
		// TODO Auto-generated method stub
		
	}
}
