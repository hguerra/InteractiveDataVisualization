package br.inpe.worldwind.view.impl;

import java.net.URL;

import br.inpe.worldwind.view.ApplicationFXML;
import br.inpe.worldwind.view.controllers.ManagerSetupController.SetupView;
import br.inpe.worldwind.view.resources.Resource;

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

}
