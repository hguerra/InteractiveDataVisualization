package br.inpe.worldwind.view.controllers;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.Parent;

public class ManagerSetupController {
	public enum SetupView {
		BASIC, LAYER, DATABASE, KINECT, PROFILE;
	}

	private static ManagerSetupController uniqueInstance;
	private Map<SetupView, Parent> elementsView;

	private ManagerSetupController() {
		this.elementsView = new HashMap<>();
	}

	public static ManagerSetupController getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new ManagerSetupController();
		}
		return uniqueInstance;
	}

	public synchronized Parent addElement(SetupView setup, Parent parent) {
		return this.elementsView.put(setup, parent);
	}

	public synchronized Parent removeElement(SetupView setup) {
		return this.elementsView.remove(setup);
	}

	public synchronized Parent getElement(SetupView key) {
		return this.elementsView.get(key);
	}

}
