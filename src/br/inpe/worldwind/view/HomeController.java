package br.inpe.worldwind.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import test.app.WorldWindControllersTest;

public class HomeController implements Initializable{

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Pane paneOptions;

    @FXML
    private Label lblLayerName;

    @FXML
    private Label lblLayers;

    @FXML
    private ComboBox<?> comboLayer;

    @FXML
    private Pane paneSetup;

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
		btnStart.setOnAction(event -> new WorldWindControllersTest().run());
		btnClose.setOnAction(event -> System.exit(0));
		
	}

}

