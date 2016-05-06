package br.inpe.worldwind.view.controllers.impl;

import br.inpe.worldwind.view.controllers.ApplicationSetupController;
import br.inpe.worldwind.view.controllers.SetupView;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class SetupDatabaseController extends ApplicationSetupController {
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
    private ComboBox comboDatabase;

    @FXML
    private ImageView imgDatabaseStatus;

    @Override
    protected void initPaneSetup() {

    }

    @Override
    protected SetupView getSetupView() {
        return SetupView.DATABASE;
    }

    @Override
    protected Pane getPaneView() {
        return paneSetup;
    }

    @Override
    public void initPaneSetupEvents() {

    }

    @Override
    public void update(Object object) {

    }
}
