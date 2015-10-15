package br.com.inpe.kinect.controller;

import br.com.inpe.app.RegisterVirtualGlobe;
import br.com.inpe.kinect.model.SwitchTracker;


/**
 * @author Heitor Guerra Carneiro.
 * @version 1.0
 * @since March 2015.
 */
public class ZoomIn implements Zoom {
	private final static double ZOOM_FACTOR_MIN = 0.97;
	@Override
	public void setZoom() {
		RegisterVirtualGlobe.getFrameController().zoom(ZOOM_FACTOR_MIN);
		SwitchTracker.allTurnOn();
	}

}
