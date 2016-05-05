package inpe.app.javafx;

import br.inpe.worldwind.view.ApplicationFX;
import br.inpe.worldwind.view.controllers.SetupView;
import com.google.common.collect.Sets;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import javax.swing.*;

public class ModelFxTest extends ApplicationFX {
	private Scene scene;
	private AnchorPane pane;
	private TextField txLogin;
	private PasswordField txSenha;
	private Button btEntrar, btSair;

	@Override
	public Scene getScene() {
		return scene;
	}

	@Override
	protected String getSceneTitle() {
		return "Test";
	}

	@Override
	protected void initComponents() {
		pane = new AnchorPane();
		pane.setPrefSize(400, 300);
		pane.getStyleClass().add("pane");
		txLogin = new TextField();
		txLogin.setPromptText("Digite seu login...");
		txSenha = new PasswordField();
		txSenha.setPromptText("Digite sua senha...");
		btEntrar = new Button("Entrar");
		btEntrar.getStyleClass().add("btEntrar");
		btSair = new Button("Sair");
		btSair.getStyleClass().add("btSair");
		pane.getChildren().addAll(txLogin, txSenha, btEntrar, btSair);
		
		scene = new Scene(pane);
		addStylesheet("/login-css.css");
	}

	@Override
	protected void initListeners() {
		btSair.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				System.exit(0);
			}
		});

		btEntrar.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (txLogin.getText().equals("admin") && txSenha.getText().equals("11")) {
					try {
						//
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Login e/ou senha inv�lidos", "Erro",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

	}

	@Override
	protected void initLayout() {
		txLogin.setLayoutX((pane.getWidth() - txLogin.getWidth()) / 2);
		txLogin.setLayoutY(50);
		txSenha.setLayoutX((pane.getWidth() - txSenha.getWidth()) / 2);
		txSenha.setLayoutY(100);
		btEntrar.setLayoutX((pane.getWidth() - btEntrar.getWidth()) / 2);
		btEntrar.setLayoutY(150);
		btSair.setLayoutX((pane.getWidth() - btSair.getWidth()) / 2);
		btSair.setLayoutY(200);

	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	protected double getDefaultComponentSize() {
		return 40;
	}

	@Override
	protected boolean exitOnCloseRequest() {
		return true;
	}

}
