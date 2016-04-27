package br.inpe.worldwind.view.controllers.impl;

import java.net.URL;
import java.util.ResourceBundle;

import br.inpe.worldwind.view.controllers.SetupController;
import br.inpe.worldwind.view.controllers.ManagerSetupController.SetupView;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class SetupProfileController implements SetupController {
	@FXML
	private AnchorPane anchorPane;

	@FXML
	private Pane paneSetup;

	@FXML
	private Label lblLayerName;

	@FXML
	private Label lblDatabase;

	@FXML
	private TextField txtDatabase;

	@FXML
	private Label lblPassword;

	@FXML
	private Button btnConnect;

	@FXML
	private PasswordField pwdDatabase;

	@FXML
	private ComboBox<?> comboDatabase;

	@FXML
	private Label lblName;

	@FXML
	private TextField txtName;

	@FXML
	private PasswordField password;

	@FXML
	private Button btnSave;

	@FXML
	private Button btnLoad;

	@FXML
	private ListView<?> listViewScenario;

	@FXML
	private ContextMenu contextMenu;

	@FXML
	private MenuItem menuItemRemove;

	@FXML
	private Button btnTrash;

	@FXML
	private Label lblScenario;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initPaneSetupEvents();
		addView(SetupView.PROFILE, anchorPane);

	}

	@Override
	public void initPaneSetupEvents() {
		btnTrash.setOnAction(event -> {
			System.out.println("btnTrash");
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
