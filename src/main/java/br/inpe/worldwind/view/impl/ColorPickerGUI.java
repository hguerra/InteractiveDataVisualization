package br.inpe.worldwind.view.impl;

import br.inpe.worldwind.view.ApplicationFXML;
import br.inpe.worldwind.view.Resource;
import br.inpe.worldwind.view.controllers.SetupView;

import java.net.URL;

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
