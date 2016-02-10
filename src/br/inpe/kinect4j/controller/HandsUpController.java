package br.inpe.kinect4j.controller;

import br.com.kinect4j.controller.Controller;
import br.inpe.kinect4j.view.KinectView;

public class HandsUpController implements Controller {
	private KinectView view;

	public HandsUpController(KinectView view) {
		this.view = view;
	}

	@Override
	public void kinectActionPerformed() {
		System.err.println("Hands UP");
		view.switchGestureTraking();
	}

}
