package br.inpe.worldwind.view;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import br.inpe.worldwind.view.resources.Resource;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
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
import test.app.WorldWindControllersTest;

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

	/* Backup */
	private ObservableList<Node> elementsSetupPanelBasic;
	private ObservableList<Node> elementsSetupPanelLayer;
	private ObservableList<Node> elementsSetupPanelDatabase;
	private ObservableList<Node> elementsSetupPanelKinect;
	private ObservableList<Node> elementsSetupPanelProfile;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		/* Load all panes before */
		initSetupPane();

		/* add events */

		btnStart.setOnAction(event -> new WorldWindControllersTest().run());
		btnClose.setOnAction(event -> {

			clearPaneSetup();

			Task<Void> task = new Task<Void>() {
				@Override
				protected Void call() throws Exception {
					Thread.sleep(2000);
					return null;
				}

			};
			task.setOnSucceeded(e -> {
				JOptionPane.showMessageDialog(null, "Bye Bye!");
			});
			new Thread(task).start();
		});

		btnGlobe.setOnAction(event -> this.paneSetup.getChildren().addAll(elementsSetupPanelBasic));

		btnLayer.setOnAction(event -> this.paneSetup.getChildren().addAll(elementsSetupPanelLayer));

		btnDataBase.setOnAction(event -> this.paneSetup.getChildren().addAll(elementsSetupPanelDatabase));

		btnKinect.setOnAction(event -> this.paneSetup.getChildren().addAll(elementsSetupPanelKinect));

		btnProfile.setOnAction(event -> this.paneSetup.getChildren().addAll(elementsSetupPanelProfile));

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

	/* Load Setup panel */
	protected boolean loadSetupPane(URL location) {
		try {
			Parent parent = FXMLLoader.load(location);
			return this.paneSetup.getChildren().addAll(parent.getChildrenUnmodifiable());
		} catch (Exception e) {
			System.err.println(e);
			return false;
		}
	}

	protected boolean loadSetupPane(ObservableList<Node> elements, URL location) {
		try {
			Parent parent = FXMLLoader.load(location);
			ObservableList<Node> parentList = parent.getChildrenUnmodifiable();
			elements.addAll(parentList);
			return true;
		} catch (Exception e) {
			System.err.println(e);
			return false;
		}
	}

	private void initSetupPane() {
		this.elementsSetupPanelBasic = FXCollections.observableArrayList();
		this.elementsSetupPanelLayer = FXCollections.observableArrayList();
		this.elementsSetupPanelDatabase = FXCollections.observableArrayList();
		this.elementsSetupPanelKinect = FXCollections.observableArrayList();
		this.elementsSetupPanelProfile = FXCollections.observableArrayList();
		// loadSetupPane(elementsSetupPanelBasic,
		// Resource.getPaneSetupBasicFXML());
		loadSetupPane(elementsSetupPanelLayer, Resource.getPaneSetupLayerFXML());
		// loadSetupPane(elementsSetupPanelDatabase,
		// Resource.getPaneSetupDatabaseFXML());
		// loadSetupPane(elementsSetupPanelKinect,
		// Resource.getPaneSetupKinectFXML());
		// loadSetupPane(elementsSetupPanelProfile,
		// Resource.getPaneSetupProfileFXML());

	}
}
