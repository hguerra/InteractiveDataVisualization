package br.com.inpe.kinect.controller;

import br.com.inpe.app.RegisterVirtualGlobe;


/**
 * @author Heitor Guerra Carneiro.
 * @version 1.0
 * @since March 2015.
 */
public class ZoomIn implements Zoom {
	@Override
	public void setZoom() {
		System.out.println("ZOOM IN!");
		//RegisterVirtualGlobe.getFrameController().zoom(0.90);
	}

}
