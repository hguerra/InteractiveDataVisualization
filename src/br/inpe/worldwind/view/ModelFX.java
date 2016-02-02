package br.inpe.worldwind.view;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public abstract class ModelFX extends Application {

	private Stage stage;

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
		/**
		 * Layout deve ser iniciado depois do stage.show(), antes disso, o valor
		 * sera -1.0.
		 */
		initLayout();
		this.stage = primaryStage;
	}

	public static void main(String[] args) {
		launch(args);
	}

	/* Getters and Setters */
	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}
}
