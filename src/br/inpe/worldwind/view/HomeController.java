package br.inpe.worldwind.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import test.app.WorldWindControllersTest;

public class HomeController implements Initializable {

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
	
		btnGlobe.setOnAction(event -> {
			
			clearPaneSetup();
			
			Task<Void> task = new Task<Void>() {
				@Override
				protected Void call() throws Exception {
					Thread.sleep(10000);
					return null;
				}

			};
			task.setOnSucceeded(e  -> {
				createlblLayerName("New Layer !!!");
			});
			new Thread(task).start();
		});
		
		btnLayer.setOnAction(event->{});
		
		btnDataBase.setOnAction(event->{});
		
		btnKinect.setOnAction(event->{});
		
		btnProfile.setOnAction(event->{});
	}

	public boolean addElementsPaneSetup(Node... elements) {
		return getPaneSetupChildren().addAll(elements);
	}

	public void clearPaneSetup() {
		getPaneSetupChildren().clear();
	}

	private ObservableList<Node> getPaneSetupChildren() {
		return this.paneSetup.getChildren();
	}
	
	/*Default components*/
	
	private boolean createlblLayerName(String text){
		/*Label*/
		Label label = new Label();
		label.setText(text);
		label.setPrefWidth(143.0);
		label.setPrefHeight(25.0);
		label.setLayoutX(7.0);
		label.getStyleClass().add("pane-options-title");
		
		/*Separator*/
		Separator sepLayerName = new Separator();
		sepLayerName.setPrefWidth(200.0);
		sepLayerName.setPrefHeight(0.0);
		sepLayerName.setLayoutX(5.0);
		sepLayerName.setLayoutY(24.0);
		sepLayerName.setOpacity(0.3);
		
		/*add in paneSetup*/
		return addElementsPaneSetup(label, sepLayerName);
	}

}
