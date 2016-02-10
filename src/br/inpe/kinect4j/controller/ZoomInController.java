package br.inpe.kinect4j.controller;

import br.com.kinect4j.controller.Controller;
import br.inpe.kinect4j.view.KinectView;

public class ZoomInController implements Controller {
	private KinectView view;

	public ZoomInController(KinectView view) {
		this.view = view;
	}

	@Override
	public void kinectActionPerformed() {
		System.out.println("Zoom In!");
	}

}