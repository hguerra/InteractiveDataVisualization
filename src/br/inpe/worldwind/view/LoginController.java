package br.inpe.worldwind.view;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController implements Initializable {

	@FXML
	private TextField txLogin;
	@FXML
	private PasswordField txSenha;
	@FXML
	private Button btEntrar, btSair;

	@Override
	public void initialize(URL url, ResourceBundle bundle) {
		btEntrar.setOnAction(event -> {
			JOptionPane.showMessageDialog(null, "Entrar");
		});

		btSair.setOnAction(event -> {
			JOptionPane.showMessageDialog(null, "Sair");
		});

	}

}
