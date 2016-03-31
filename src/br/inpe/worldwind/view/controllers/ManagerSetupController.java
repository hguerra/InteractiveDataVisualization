package br.inpe.worldwind.view.controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

import br.inpe.triangle.conf.Data;
import br.inpe.triangle.conf.DataSource;
import br.inpe.triangle.defaultproperties.DefaultDataSource;
import br.inpe.triangle.defaultproperties.DefaultFilePath;
import br.inpe.util.color.ColorBrewer;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class ManagerSetupController {
	public enum SetupView {
		BASIC, LAYER, LAYER_ATTRIBUTES, LAYER_COLOR, DATABASE, KINECT, PROFILE, STYLE_DATA;
	}

	/* Javafx */
	private static ManagerSetupController uniqueInstance;
	private Map<SetupView, ObservableList<Node>> elementsView;
	private Map<SetupView, SetupController> controllers;
	/* ColorBrewer */
	private ColorBrewer colorBrewer;
	/* Data */
	private DataSource sessionDataSource;

	private ManagerSetupController() {
		this.elementsView = new HashMap<>();
		this.controllers = new HashMap<>();
		this.colorBrewer = createColorBrewer();
		this.sessionDataSource = DefaultDataSource.getInstance().createDefaultDataSource();
	}

	/**
	 * Singleton
	 * 
	 * @return
	 */
	public static ManagerSetupController getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new ManagerSetupController();
		}
		return uniqueInstance;
	}

	/**
	 * Elements of view
	 * 
	 * @param setup
	 * @param parent
	 * @return
	 */
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

	/**
	 * Controllers
	 * 
	 * @param view
	 * @param controller
	 * @return
	 */
	public synchronized SetupController addController(SetupView view, SetupController controller) {
		return this.controllers.put(view, controller);
	}

	public synchronized SetupController removeController(SetupView view) {
		return this.controllers.remove(view);
	}

	public synchronized SetupController getController(SetupView view) {
		return this.controllers.get(view);
	}

	/**
	 * Color
	 * 
	 * TODO refactor this using datasource
	 */
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

	/**
	 * ColorBrewer
	 * 
	 * @return
	 */
	public ColorBrewer getColorBrewer() {
		return colorBrewer;
	}

	private ColorBrewer createColorBrewer() {
		File file = new File(DefaultFilePath.COLOR_BREWER_JSON);
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			return new Gson().fromJson(br, ColorBrewer.class);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return new ColorBrewer();
	}

	/**
	 * Data Source
	 * @return 
	 */
	public Data addData(String name, Data data) {
		return this.sessionDataSource.addData(name, data);
	}
	public Data removeData(String name){
		return this.sessionDataSource.removeData(name);
	}
	public Data getData(String name){
		return this.sessionDataSource.getDataSet().get(name);
	}

}
