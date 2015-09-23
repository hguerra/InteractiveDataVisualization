package br.com.inpe.kinect.model.gesture.detector;

import br.com.inpe.kinect.model.Subject;

public class Posture {
	private IPosture posture;
	private Subject KinectModel;

	public Posture(IPosture posture, Subject kinectModel) {
		this.posture = posture;
		this.KinectModel = kinectModel;
	}

	public void updatePosture(int userId) {
		EPostureType result = posture.updatePosture(userId);
		if (!result.equals(EPostureType.UNDEFINED))
			KinectModel.notifyObserverPosture(result);
	}

}
