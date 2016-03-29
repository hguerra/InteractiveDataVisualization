package br.inpe.worldwind.view.controllers;

import java.util.HashMap;
import java.util.Map;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class ManagerSetupController {
	public enum SetupView {
		BASIC, LAYER, LAYER_ATTRIBUTES, LAYER_COLOR, DATABASE, KINECT, PROFILE, STYLE_DATA;
	}

	private static ManagerSetupController uniqueInstance;
	private Map<SetupView, ObservableList<Node>> elementsView;
	private Map<SetupView, SetupController> controllers;

	private ManagerSetupController() {
		this.elementsView = new HashMap<>();
		this.controllers = new HashMap<>();
	}

	public static ManagerSetupController getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new ManagerSetupController();
		}
		return uniqueInstance;
	}

	/* Elements of view */
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

	/* Controllers */
	public synchronized SetupController addController(SetupView view, SetupController controller) {
		return this.controllers.put(view, controller);
	}

	public synchronized SetupController removeController(SetupView view) {
		return this.controllers.remove(view);
	}

	public synchronized SetupController getController(SetupView view) {
		return this.controllers.get(view);
	}

	/* Color */

	private Map<String, Color[]> attributesColor = new HashMap<>();

	public Color[] addAttributesColor(String attrName, Color... color) {
		return attributesColor.put(attrName, color);
	}

	public Color[] removeAttributesColor(String attrName) {
		return attributesColor.remove(attrName);
	}

	public Map<String, Color[]> getAttributesColor() {
		return attributesColor;
	}

	public Color[] getColors(String attrName) {
		return attributesColor.get(attrName);
	}

}
