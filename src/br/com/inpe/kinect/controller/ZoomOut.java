package br.com.inpe.kinect.controller;

import br.com.inpe.app.RegisterVirtualGlobe;
import br.com.inpe.kinect.model.SwitchTracker;

/**
 * @author Heitor Guerra Carneiro.
 * @version 1.0
 * @since March 2015.
 */
public class ZoomOut implements Zoom{
	private final static double ZOOM_FACTOR_MAX = 1.03;
	@Override
	public void setZoom() {
		RegisterVirtualGlobe.getFrameController().zoom(ZOOM_FACTOR_MAX);
		SwitchTracker.allTurnOn();
	}
}
