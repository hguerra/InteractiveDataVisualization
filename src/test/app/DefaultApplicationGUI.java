package test.app;

import br.inpe.worldwind.view.ModelFX;
import br.inpe.worldwind.view.resources.Resource;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class DefaultApplicationGUI extends ModelFX {
	/* Container */
	private Scene scene;

	private AnchorPane anchorPane;

	/* Pane Options */
	private Pane paneOptions;

	private Button btnKinect;

	private Button btnGlobe;

	private Button btnLayer;

	private Button btnClose;

	private Button btnStart;

	private Button btnProfile;

	private Button btnDataBase;

	/* Pane Setup */
	private Pane paneSetup;

	private Label lblLayerName;

	private Separator sepLayerName;

	private Label lblLayers;

	private ComboBox<?> comboLayer;

	/* Pane View */
	private Pane paneView;

	private ImageView imgMock;

	@Override
	public Scene getScene() {
		return scene;
	}

	@Override
	protected String getSceneTitle() {
		return "Default GUI";
	}

	@Override
	protected double getDefaultComponentSize() {
		return 40;
	}

	@Override
	protected void initComponents() {
		/* Container */
		this.anchorPane = new AnchorPane();
		this.anchorPane.setPrefWidth(640.0);
		this.anchorPane.setPrefHeight(480.0);
		this.anchorPane.getStylesheets().add(Resource.getStylesheet("home-css.css"));

		/* Componentes */
		this.paneOptions = new Pane();
		addStyleClass(paneOptions, "pane-options");

		this.btnKinect = new Button();
		addStyleClass(btnKinect, "btnKinect");

		this.btnGlobe = new Button();
		addStyleClass(btnGlobe, "btnGlobe");

		this.btnLayer = new Button();
		addStyleClass(btnLayer, "btnClose");

		this.btnClose = new Button();
		addStyleClass(btnClose, "btnClose");

		this.btnStart = new Button();
		addStyleClass(btnStart, "btnStart");

		this.btnProfile = new Button();
		addStyleClass(btnProfile, "btnProfile");

		this.btnDataBase = new Button();
		addStyleClass(btnDataBase, "btnDataBase");

		this.paneSetup = new Pane();
		addStyleClass(paneSetup, "pane-setup");

		this.lblLayerName = new Label();
		addStyleClass(lblLayerName, "pane-options-title");

		this.sepLayerName = new Separator();

		this.lblLayers = new Label();
		addStyleClass(lblLayers, "pane-options-subtitle");

		this.comboLayer = new ComboBox<>();

		this.paneView = new Pane();

		this.imgMock = new ImageView(new Image(Resource.RESOURCE_PATH + "worldwind-flat-mock.png"));

		/* Scene */
		this.paneOptions.getChildren().addAll(btnKinect, btnGlobe, btnLayer, btnClose, btnStart, btnProfile,
				btnDataBase);
		this.paneSetup.getChildren().addAll(sepLayerName, lblLayerName, lblLayers);
		this.paneView.getChildren().addAll(imgMock);
		this.anchorPane.getChildren().addAll(paneOptions, paneSetup, paneView);
		this.scene = new Scene(anchorPane);
	}

	@Override
	protected void initListeners() {
		btnGlobe.setOnAction(e -> System.out.println("Existe um botao aqui"));
	}

	@Override
	protected void initLayout() {
		setLayout(paneOptions, 213.0, 480.0, 55.0, 0.0);

		setLayout(btnKinect, 13.0, 166.0);

		setLayout(btnGlobe, 13.0, 8.0);

		setLayout(btnLayer, 13.0, 61.0);

		setLayout(btnClose, 13.0, 380.0);

		setLayout(btnStart, 13.0, 434.0);

		setLayout(btnProfile, 13.0, 211.0);

		setLayout(btnDataBase, 13.0, 116.0);

		setLayout(paneSetup, 213.0, 480.0, 55.0, 0.0);

		setLayout(lblLayerName, 143.0, 25.0, 7.0, 0);

		setLayout(sepLayerName, 200.0, 0.0, 5.0, 24.0);
		sepLayerName.setOpacity(0.3);

		lblLayers.setLayoutX(9.0);
		lblLayers.setLayoutY(37.0);

		setLayout(paneView, 373.0, 480.0, 269.0, 0);

	}

	public static void main(String[] args) {
		launch(args);
	}

}
