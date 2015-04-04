package br.com.inpe.interactivedatavisualization.kinect.model.gesture;

import br.com.inpe.interactivedatavisualization.kinect.model.gesturecheck.BaseGesture;
import br.com.inpe.interactivedatavisualization.kinect.model.gesturecheck.GestureRecognize;
import br.com.inpe.interactivedatavisualization.kinect.model.gesturecheck.PoseRecognize;
import br.com.inpe.interactivedatavisualization.kinect.model.gesturecheck.NewPose;
import SimpleOpenNI.SimpleOpenNI;

public class WaveGesture extends BaseGesture implements NewPose {
	private SimpleOpenNI context;
	private GestureRecognize poseOne;
	private GestureRecognize poseTwo;
	private GestureRecognize poseThree;

	public WaveGesture(SimpleOpenNI context) {
		constructorMethod(context);
	}

	@Override
	public void constructorMethod(SimpleOpenNI context) {
		this.context = context;
		poseOne = new GestureRecognize(context);
		poseTwo = new GestureRecognize(context);
		poseThree = new GestureRecognize(context);

	}

	@Override
	public boolean segmentOne(int userId) {
		// 1
		poseOne.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRecognize.ABOVE,
				SimpleOpenNI.SKEL_HEAD);
		poseOne.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRecognize.ABOVE,
				SimpleOpenNI.SKEL_RIGHT_SHOULDER);
		poseOne.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRecognize.ABOVE,
				SimpleOpenNI.SKEL_RIGHT_ELBOW);
		poseOne.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRecognize.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_ELBOW);
		return segmentCheck(userId, poseOne);
	}

	@Override
	public boolean segmentTwo(int userId) {
		// 2
		poseTwo.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRecognize.ABOVE,
				SimpleOpenNI.SKEL_HEAD);
		poseTwo.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRecognize.ABOVE,
				SimpleOpenNI.SKEL_RIGHT_SHOULDER);
		poseTwo.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRecognize.ABOVE,
				SimpleOpenNI.SKEL_RIGHT_ELBOW);
		poseTwo.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRecognize.RIGHT_OF,
				SimpleOpenNI.SKEL_RIGHT_ELBOW);
		return segmentCheck(userId, poseTwo);

	}

	@Override
	public boolean segmentThree(int userId) {
		poseThree.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRecognize.ABOVE,
				SimpleOpenNI.SKEL_HEAD);
		poseThree.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRecognize.ABOVE,
				SimpleOpenNI.SKEL_RIGHT_SHOULDER);
		poseThree.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRecognize.ABOVE,
				SimpleOpenNI.SKEL_RIGHT_ELBOW);
		return segmentCheck(userId, poseThree);
	}

	@Override
	public boolean segmentCheck(int userId, GestureRecognize pose) {
		return pose.check(userId);
	}
	
	@Override
	protected boolean checkStartCondition(int userId) {
		if(segmentOne(userId))
			return true;
		return false;
	}

	@Override
	protected boolean stillValidPosition(int userId) {
		if(segmentTwo(userId)){
			return true;
		}
		return false;
	}

	@Override
	protected boolean validateFinalPosition(int userId) {
		if(segmentThree(userId))
			return true;
		return false;
	}
	
	@Override
	public boolean recognize(int userId) {
		return gestureRecognized(userId);
	}
}
