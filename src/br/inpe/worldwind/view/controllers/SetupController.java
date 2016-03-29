package br.inpe.worldwind.view.controllers;

import java.net.URL;

import br.inpe.worldwind.view.controllers.ManagerSetupController.SetupView;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

public interface SetupController extends Initializable {
	void initPaneSetupEvents();

	ObservableList<Node> getPaneSetupChildren();
	
	void update(Object object);

	default boolean addElementsPaneSetup(Node... elements) {
		return getPaneSetupChildren().addAll(elements);
	}

	default void clearPaneSetup() {
		getPaneSetupChildren().clear();
	}

	default boolean loadPaneSetup(URL location) {
		try {
			Parent parent = FXMLLoader.load(location);
			return getPaneSetupChildren().addAll(parent.getChildrenUnmodifiable());
		} catch (Exception e) {
			System.err.println(e);
			return false;
		}
	}

	default boolean loadPaneSetup(ObservableList<Node> elements, URL location) {
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

	default ObservableList<Node> addView(SetupView setup, Pane pane) {
		ManagerSetupController manager = ManagerSetupController.getInstance();
		return manager.addElement(setup, pane);
	}

	default SetupController addController(SetupView setup, Object controller) {
		if (!(controller instanceof SetupController)){
			return null;			
		}

		ManagerSetupController manager = ManagerSetupController.getInstance();
		return manager.addController(setup, (SetupController) controller);
	}
}
