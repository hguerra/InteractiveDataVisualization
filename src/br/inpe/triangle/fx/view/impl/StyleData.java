package br.inpe.triangle.fx.view.impl;

import resources.Resource;

import java.net.URL;

import br.inpe.triangle.fx.view.ApplicationFXML;
import br.inpe.triangle.fx.view.SetupView;

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
