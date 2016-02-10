package br.inpe.worldwind.view.controllers;

import java.util.HashMap;
import java.util.Map;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class ManagerSetupController {
	public enum SetupView {
		BASIC, LAYER, LAYER_ATTRIBUTES, LAYER_COLOR, DATABASE, KINECT, PROFILE;
	}

	private static ManagerSetupController uniqueInstance;
	private Map<SetupView, ObservableList<Node>> elementsView;

	private ManagerSetupController() {
		this.elementsView = new HashMap<>();
	}

	public static ManagerSetupController getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new ManagerSetupController();
		}
		return uniqueInstance;
	}

	public synchronized ObservableList<Node> addElement(SetupView setup, Pane parent) {
		return this.elementsView.put(setup, parent.getChildren());
	}
	public synchronized ObservableList<Node> addElement(SetupView setup, ObservableList<Node> parent) {
		return this.elementsView.put(setup, parent);
	}

	public synchronized ObservableList<Node> removeElement(SetupView setup) {
		return this.elementsView.remove(setup);
	}

	public synchronized ObservableList<Node> getElement(SetupView key) {
		return this.elementsView.get(key);
	}

}
