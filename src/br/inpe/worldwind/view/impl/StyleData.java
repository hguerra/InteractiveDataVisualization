package br.inpe.worldwind.view.impl;

import java.net.URL;

import br.inpe.worldwind.view.ApplicationFXML;
import br.inpe.worldwind.view.controllers.ManagerSetupController.SetupView;
import br.inpe.worldwind.view.resources.Resource;

public class StyleData extends ApplicationFXML {

	@Override
	protected String getSceneTitle() {
		return "Style Data";
	}

	@Override
	protected URL getFXML() {
		return Resource.getStyleDataFXML();
	}

	@Override
	protected SetupView getSetupView() {
		return SetupView.STYLE_DATA;
	}

	@Override
	protected boolean exitOnCloseRequest() {
		return false;
	}
}
