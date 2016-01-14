package br.kinect4j.controller;

import br.com.kinect4j.controller.Controller;
import br.kinect4j.view.KinectView;

public class ZoomOutController implements Controller {
	private KinectView view;

	public ZoomOutController(KinectView view) {
		this.view = view;
	}

	@Override
	public void kinectActionPerformed() {
		System.out.println("Zoom Out!");
	}

}