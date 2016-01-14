package br.kinect4j.controller;

import br.com.kinect4j.controller.Controller;
import br.kinect4j.view.KinectView;

public class WaveRightController implements Controller {
	private KinectView view;

	public WaveRightController(KinectView view) {
		this.view = view;
	}

	@Override
	public void kinectActionPerformed() {
		System.out.println("Wave Right!");
	}

}