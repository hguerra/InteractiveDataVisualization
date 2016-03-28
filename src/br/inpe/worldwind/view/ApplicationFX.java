package br.inpe.worldwind.view;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public abstract class ApplicationFX extends Application {

	private static Stage currentStage;

	public abstract Scene getScene();

	protected abstract String getSceneTitle();

	protected abstract double getDefaultComponentSize();

	protected abstract void initComponents();

	protected abstract void initListeners();

	protected abstract void initLayout();

	public boolean addStylesheet(String stylesheet) {
		return getScene().getStylesheets().add(stylesheet);
	}

	public boolean removeStylesheet(String stylesheet) {
		return getScene().getStylesheets().remove(stylesheet);
	}

	public boolean addStyleClass(Parent root, String className) {
		return root.getStyleClass().add(className);
	}

	public boolean removeStyleClass(Parent root, String className) {
		return root.getStyleClass().remove(className);
	}

	public void setLayout(Control control, double setPrefWidth, double setPrefHeight, double setLayoutX,
			double setLayoutY) {
		control.setPrefWidth(setPrefWidth);
		control.setPrefHeight(setPrefHeight);
		control.setLayoutX(setLayoutX);
		control.setLayoutY(setLayoutY);
	}
	
	public void setLayout(Pane control, double setPrefWidth, double setPrefHeight, double setLayoutX,
			double setLayoutY) {
		control.setPrefWidth(setPrefWidth);
		control.setPrefHeight(setPrefHeight);
		control.setLayoutX(setLayoutX);
		control.setLayoutY(setLayoutY);
	}

	public void setLayout(Control control, double setLayoutX, double setLayoutY) {
		setLayout(control, getDefaultComponentSize(), getDefaultComponentSize(), setLayoutX, setLayoutY);
	}

	public void setComponentProperties(Control control, String styleClassName, double setPrefWidth,
			double setPrefHeight, double setLayoutX, double setLayoutY) {
		addStyleClass(control, styleClassName);
		setLayout(control, setPrefWidth, setPrefHeight, setLayoutX, setLayoutY);
	}

	public void setComponentProperties(Control control, String styleClassName, double setLayoutX, double setLayoutY) {
		addStyleClass(control, styleClassName);
		setLayout(control, setLayoutX, setLayoutY);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		initComponents();
		initListeners();
		primaryStage.setScene(getScene());
		primaryStage.setTitle(getSceneTitle());
		primaryStage.show();
		initLayout();
		currentStage = primaryStage;
	}

	/* Getters and Setters */
	public Stage getStage() {
		return currentStage;
	}

	public void setStage(Stage stage) {
		currentStage = stage;
	}
	
	public static boolean closeStage() {
		if (currentStage == null)
			return false;
		currentStage.close();
		return true;
	}
}
