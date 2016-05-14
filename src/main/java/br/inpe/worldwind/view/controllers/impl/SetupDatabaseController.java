package br.inpe.worldwind.view.controllers.impl;

import br.inpe.worldwind.view.Resource;
import br.inpe.worldwind.view.controllers.ApplicationSetupController;
import br.inpe.worldwind.view.controllers.SetupView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class SetupDatabaseController extends ApplicationSetupController {
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Pane paneSetup;

    @FXML
    private TextField txtIp;

    @FXML
    private TextField txtDatabase;

    @FXML
    private Button btnConnect;

    @FXML
    private PasswordField pwdDatabase;

    @FXML
    private ComboBox comboDatabase;

    @FXML
    private ImageView imgDatabaseStatus;

    private Image imgDisconnected;

    private Image imgConnected;

    private boolean isConnected = false;


    @Override
    protected void initPaneSetup() {
        this.imgDisconnected = imgDatabaseStatus.getImage();
        this.imgConnected = new Image(Resource.getImageDatabaseStatusConnected());

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
        btnConnect.setOnAction(event -> {
            isConnected = !isConnected;
            if (isConnected) imgDatabaseStatus.setImage(imgConnected);
            else imgDatabaseStatus.setImage(imgDisconnected);
        });

    }

    @Override
    public void update(Object object) {

    }
}
