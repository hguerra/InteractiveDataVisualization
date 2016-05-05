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
    public AnchorPane anchorPane;

    @FXML
    public Pane paneSetup;

    @FXML
    public Label lblLayerName;

    @FXML
    public Label lblDatabase;

    @FXML
    public TextField txtDatabase;

    @FXML
    public Label lblPassword;

    @FXML
    public Button btnConnect;

    @FXML
    public PasswordField pwdDatabase;

    @FXML
    public ComboBox comboDatabase;

    @FXML
    public ImageView imgDatabaseStatus;

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
