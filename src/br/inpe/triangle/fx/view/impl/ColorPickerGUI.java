package br.inpe.triangle.fx.view.impl;

import resources.Resource;

import java.net.URL;

import br.inpe.triangle.fx.view.ApplicationFXML;
import br.inpe.triangle.fx.view.SetupView;

public class ColorPickerGUI extends ApplicationFXML {
	@Override
	protected String getSceneTitle() {
		return "Color Picker";
	}

	@Override
	protected URL getFXML() {
		return Resource.getColorPickerFXML();
	}

	@Override
	protected SetupView getSetupView() {
		return SetupView.LAYER_COLOR;
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
