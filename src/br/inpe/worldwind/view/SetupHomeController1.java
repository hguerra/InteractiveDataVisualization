package br.inpe.worldwind.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class SetupHomeController1 implements Initializable {
	@FXML
	private AnchorPane anchorPane;

	@FXML
	private Pane paneSetup;

	@FXML
	private Label lblLayerName;

	@FXML
	private Label lblData;

	@FXML
	private ComboBox<?> comboLayer;

	@FXML
	private Label lblScenario;

	@FXML
	private ListView<?> listViewScenario;

	@FXML
	private ContextMenu contextMenu;

	@FXML
	private MenuItem menuItemRemove;

	@FXML
	private Label lblKinect;

	@FXML
	private ToggleButton toggleBtnKinect;

	@FXML
	private Button btnTrash;

	@FXML
	private Pane paneOptions;

	@FXML
	private Button btnKinect;

	@FXML
	private Button btnGlobe;

	@FXML
	private Button btnLayer;

	@FXML
	private Button btnClose;

	@FXML
	private Button btnStart;

	@FXML
	private Button btnProfile;

	@FXML
	private Button btnDataBase;

	@FXML
	private Pane paneView;

	@FXML
	private ImageView imgMock;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

}
