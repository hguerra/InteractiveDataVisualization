package br.inpe.worldwind.view.controllers.impl;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import br.inpe.gdal.transform.GeoFormat;
import br.inpe.triangle.conf.Data;
import br.inpe.triangle.defaultproperties.DefaultTriangleProperties;
import br.inpe.worldwind.controller.ShapefileController;
import br.inpe.worldwind.view.controllers.ManagerSetupController;
import br.inpe.worldwind.view.controllers.ManagerSetupController.SetupView;
import br.inpe.worldwind.view.controllers.SetupController;
import br.inpe.worldwind.view.impl.StyleData;
import br.inpe.worldwind.view.impl.WorldWindView;
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
import javafx.stage.Stage;

public class SetupLayerController implements SetupController {
	private static final ManagerSetupController MANAGER = ManagerSetupController.getInstance();

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

	@FXML
	private Button btnRun;

	private ObservableList<String> listOfView = FXCollections.observableArrayList();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initPaneSetupEvents();
		addView(SetupView.LAYER, anchorPane);
	}

	@Override
	public void initPaneSetupEvents() {

		btnLoad.setOnAction(event -> {
			FileChooser fileChooser = new FileChooser();
			// default
			FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Shapefiles (*.shp)", "*.shp");
			/**
			 * TODO
			 */
			// FileChooser.ExtensionFilter extFilter = new
			// FileChooser.ExtensionFilter(
			// "Shapefiles (*.shp), GeoJson (*.geojson)", "*.shp", "*.geojson");
			// add extension filter
			fileChooser.getExtensionFilters().add(extFilter);
			File file = fileChooser.showOpenDialog(null);
			if (file != null) {
				String path = file.getAbsolutePath();
				String title = txtTitle.getText();
				String reference = txtReference.getText();
				/* title */
				if (title.equals(""))
					title = ShapefileController.getDisplayName(path);

				/* add Data */
				Data data = new Data();
				data.setTitle(title);
				data.setFormat(GeoFormat.SHAPEFILE);
				data.setFilepath(path);

				/* reference */
				if (!reference.equals(""))
					data.setReference(reference);

				// add in observable list
				listOfView.add(title);
				// add data in Manager
				MANAGER.addData(title, data);
			}
			// end
			listViewScenario.setItems(listOfView);
		});

		menuItemRemove.setOnAction(event -> {
			int index = listViewScenario.getSelectionModel().getSelectedIndex();

			if (index < 0)
				return;

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

		btnColor.setOnAction(event -> {
			try {
				/* data */
				if (!listOfView.isEmpty()) {
					String item = listViewScenario.getSelectionModel().getSelectedItem();
					if (item == null) {
						JOptionPane.showMessageDialog(null, "Please selected some data before!", "NO DATA SELECTED",
								JOptionPane.WARNING_MESSAGE);
						return;
					}
					new StyleData().start(new Stage());

					SetupController controller = MANAGER.getController(SetupView.STYLE_DATA);
					Data selectedData = MANAGER.getData(item);
					controller.update(selectedData);
				} else
					JOptionPane.showMessageDialog(null, "Please add some data before!", "NO DATA",
							JOptionPane.WARNING_MESSAGE);

			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		btnRun.setOnAction(event -> {
			List<Data> dataset = new ArrayList<>();
			listOfView.forEach(key -> {
				dataset.add(MANAGER.getData(key));
			});
			try {
				WorldWindView.run(dataset);
			} catch (Exception e) {
				e.printStackTrace();
			}

		});
	}

	@Override
	public ObservableList<Node> getPaneSetupChildren() {
		return this.paneSetup.getChildren();
	}

	@Override
	public void update(Object object) {
		// TODO Auto-generated method stub

	}
}
