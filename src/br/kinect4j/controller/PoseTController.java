package br.kinect4j.controller;

import br.com.kinect4j.controller.Controller;
import br.kinect4j.view.KinectView;

public class PoseTController implements Controller {
	private KinectView view;
	


	public PoseTController(KinectView view) {
		this.view = view;
	}



	@Override
	public void kinectActionPerformed() {
		System.err.println("POSE_T");
		
	}

}
