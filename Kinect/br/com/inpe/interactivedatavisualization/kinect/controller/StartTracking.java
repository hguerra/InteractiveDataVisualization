package br.com.inpe.interactivedatavisualization.kinect.controller;

import testeGestureDetector.GestureControlerTest;
import br.com.inpe.interactivedatavisualization.kinect.model.Movements;
import br.com.inpe.interactivedatavisualization.kinect.view.Observer;
import SimpleOpenNI.SimpleOpenNI;

/**
 * @author Heitor Guerra Carneiro.
 * @version 1.0
 * @since March 2015.
 */
public class StartTracking implements Bridge {
	private SimpleOpenNI context;
	private Movements movement;

	public StartTracking(SimpleOpenNI context) {
		initModel(context);
	}

	@Override
	public void initModel(SimpleOpenNI context) {
		movement = new Movements(context);

	}

	@Override
	public void initPoseCheck(int userId) {
		movement.poseCheck(userId);
	}

	@Override
	public void initRegisterObserver(Observer kinect) {
		movement.registerObserver(kinect);

	}
}
