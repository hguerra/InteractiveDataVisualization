package br.inpe.worldwind.view.controllers;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import br.inpe.triangle.defaultproperties.DefaultTriangleProperties;
import br.inpe.worldwind.controller.ShapefileController;
import br.inpe.worldwind.view.controllers.ManagerSetupController.SetupView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;

public class SetupLayerController implements SetupController {
	@FXML
	private AnchorPane anchorPane;

	@FXML
	private Pane paneSetup;

	@FXML
	private Label lblLayerName;

	@FXML
	private Label lblTitle;

	@FXML
	private Label lblScenario;

	@FXML
	private ListView<String> listViewScenario;

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
	private TextField txtTitle;

	@FXML
	private TextArea txtReference;

	@FXML
	private Label lblReference;

	@FXML
	private Button btnLoad;
	
    @FXML
    private Button btnColor;
	
	private Map<String, String> externalFilePath = new HashMap<>();
	
	private Map<String, String> dataReference = new HashMap<>();
	
	private ObservableList<String> listOfView = FXCollections.observableArrayList();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initPaneSetupEvents();
		addSetupController(SetupView.LAYER, anchorPane);
	}

	@Override
	public void initPaneSetupEvents() {

		btnLoad.setOnAction(event -> {
			FileChooser fileChooser = new FileChooser();
			FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
					"Shapefiles (*.shp), GeoJson (*.geojson)", "*.shp", "*.geojson");
			fileChooser.getExtensionFilters().add(extFilter);
			File file = fileChooser.showOpenDialog(null);
			if (file != null) {
				String path = file.getAbsolutePath();
				String title = txtTitle.getText();
				String reference = txtReference.getText();

				if (title.equals("")) 
					title = ShapefileController.getDisplayName(path);
		
				externalFilePath.put(title, path);
				listOfView.add(title);
				
				if(!reference.equals("")){
					dataReference.put(title, reference);
				}
			}
			listViewScenario.setItems(listOfView);
		});
		
		menuItemRemove.setOnAction(event -> {
			int index = listViewScenario.getSelectionModel().getSelectedIndex();
			listOfView.remove(index);
			listViewScenario.setItems(listOfView);
		});
		
		btnTrash.setOnAction(event -> {
			listOfView.clear();
		});

		toggleBtnKinect.setOnAction(event -> {
			DefaultTriangleProperties.getInstance().setKinectEnable(toggleBtnKinect.isSelected());
			System.out.println("toggleBtnKinect:" + toggleBtnKinect.isSelected());
		});
	}

	@Override
	public ObservableList<Node> getPaneSetupChildren() {
		return this.paneSetup.getChildren();
	}

}
