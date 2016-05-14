package br.inpe.worldwind.view.impl;

import br.inpe.worldwind.view.ApplicationFXML;
import br.inpe.worldwind.view.Resource;
import br.inpe.worldwind.view.controllers.SetupView;

import java.net.URL;

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

	@Override
	protected boolean getResizable() {
		return false;
	}
}
