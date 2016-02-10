package br.inpe.kinect4j.controller;

import br.com.kinect4j.controller.Controller;
import br.inpe.kinect4j.view.KinectView;

public class SwipeLeftToRightController implements Controller {
	private KinectView view;

	public SwipeLeftToRightController(KinectView view) {
		this.view = view;
	}

	@Override
	public void kinectActionPerformed() {
		System.out.println("Swipe Left to Right!");
	}

}
