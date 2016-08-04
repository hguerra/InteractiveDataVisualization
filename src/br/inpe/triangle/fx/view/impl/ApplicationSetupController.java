package br.inpe.triangle.fx.view.impl;

import java.net.URL;
import java.util.ResourceBundle;

import br.inpe.triangle.fx.view.SetupController;
import br.inpe.triangle.fx.view.SetupView;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public abstract class ApplicationSetupController implements SetupController {
	protected abstract void initPaneSetup();

	protected abstract SetupView getSetupView();

	protected abstract Pane getPaneView();
	
	@Override
	public ObservableList<Node> getPaneSceneChildren() {
		return getPaneView().getChildren();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		/* Load all panes before */
		initPaneSetup();
		/* add events */
		initPaneSetupEvents();
		/* set screen */
		addSetupView(getSetupView(), getPaneView());
		/* set controller */
		addSetupController(getSetupView(), this);
	}

}
