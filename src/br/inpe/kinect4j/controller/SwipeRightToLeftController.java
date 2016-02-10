package br.inpe.kinect4j.controller;

import br.com.kinect4j.controller.Controller;
import br.inpe.kinect4j.view.KinectView;

public class SwipeRightToLeftController implements Controller {
	private KinectView view;

	public SwipeRightToLeftController(KinectView view) {
		this.view = view;
	}

	@Override
	public void kinectActionPerformed() {
		System.out.println("Swipe Right to Left!");
	}

}
