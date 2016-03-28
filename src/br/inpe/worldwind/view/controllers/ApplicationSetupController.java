package br.inpe.worldwind.view.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import br.inpe.worldwind.view.controllers.ManagerSetupController.SetupView;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public abstract class ApplicationSetupController implements SetupController {
	protected abstract void initPaneSetup();

	protected abstract SetupView getSetupView();

	protected abstract Pane getPaneView();

	@Override
	public ObservableList<Node> getPaneSetupChildren() {
		return getPaneView().getChildren();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		/* Load all panes before */
		initPaneSetup();
		/* add events */
		initPaneSetupEvents();
		/* set screen */
		addSetupController(getSetupView(), getPaneView());
	}

}
