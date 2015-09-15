package br.com.inpe.kinect.controller;

import br.com.inpe.app.RegisterVirtualGlobe;

/**
 * @author Heitor Guerra Carneiro.
 * @version 1.0
 * @since March 2015.
 */
public class ZoomOut implements Zoom{

	@Override
	public void setZoom() {
		System.out.println("ZOOM OUT!");
		RegisterVirtualGlobe.getFrameController().zoom(1.10);
	}
}
