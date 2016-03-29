package br.inpe.worldwind.view.controllers.impl;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import br.inpe.triangle.defaultproperties.DefaultTriangleProperties;
import br.inpe.worldwind.controller.ShapefileController;
import br.inpe.worldwind.view.controllers.ManagerSetupController;
import br.inpe.worldwind.view.controllers.ManagerSetupController.SetupView;
import br.inpe.worldwind.view.controllers.SetupController;
import br.inpe.worldwind.view.impl.StyleData;
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
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

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

	@FXML
	private Button btnRun;

	private Map<String, String> externalFilePath = new HashMap<>();

	private Map<String, String> dataReference = new HashMap<>();

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
			FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
					"Shapefiles (*.shp), GeoJson (*.geojson)", "*.shp", "*.geojson");
			fileChooser.getExtensionFilters().add(extFilter);
			File file = fileChooser.showOpenDialog(null);
			if (file != null) {
				String path = file.getAbsolutePath();
				String title = txtTitle.getText();
				String reference = txtReference.getText();
				/* title */
				if (title.equals(""))
					title = ShapefileController.getDisplayName(path);

				externalFilePath.put(title, path);
				listOfView.add(title);
				/* reference */
				if (!reference.equals("")) {
					dataReference.put(title, reference);
				}
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
					SetupController controller = ManagerSetupController.getInstance().getController(SetupView.STYLE_DATA);
					controller.update(externalFilePath.get(item));
				} else
					JOptionPane.showMessageDialog(null, "Please add some data before!", "NO DATA",
							JOptionPane.WARNING_MESSAGE);

			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		btnRun.setOnAction(event -> {
			DefaultTriangleProperties triangle = DefaultTriangleProperties.getInstance();

			String attr = "attr";

			Color[] colors = ManagerSetupController.getInstance().getColors(attr);

			String title = "vegtype-gdal.shp";

			try {
				String path = externalFilePath.get(title);

				if (path == null) {
					JOptionPane.showMessageDialog(null, "Please add some file!", "Erro", JOptionPane.ERROR_MESSAGE);
					return;
				} else if (colors == null) {
					JOptionPane.showMessageDialog(null, "Please choice the colors!", "Erro", JOptionPane.ERROR_MESSAGE);
					return;
				}

				java.awt.Color[] awtColors = convertFX2AWT(colors);

				System.out.println(awtColors);

				triangle.addLayers(path, attr, awtColors);

			} catch (Exception e) {
				e.printStackTrace();
			}

		});
	}

	@Override
	public ObservableList<Node> getPaneSetupChildren() {
		return this.paneSetup.getChildren();
	}

	private java.awt.Color[] convertFX2AWT(javafx.scene.paint.Color... colors) {
		java.awt.Color[] awtColors = new java.awt.Color[colors.length];

		for (int i = 0; i < colors.length; i++) {
			Color c = colors[i];
			int r = (int) c.getRed();
			int g = (int) c.getGreen();
			int b = (int) c.getBlue();
			java.awt.Color newColor = new java.awt.Color(r, g, b);
			awtColors[i] = newColor;
		}
		return awtColors;
	}
	
	@Override
	public void update(Object object) {
		// TODO Auto-generated method stub
		
	}
}
