package br.com.inpe.interactivedatavisualization.kinect.model.gesture;

import br.com.inpe.interactivedatavisualization.kinect.model.gesturecheck.BaseGesture;
import br.com.inpe.interactivedatavisualization.kinect.model.gesturecheck.GestureRecognize;
import br.com.inpe.interactivedatavisualization.kinect.model.gesturecheck.NewPose;
import br.com.inpe.interactivedatavisualization.kinect.model.gesturecheck.PoseRecognize;
import SimpleOpenNI.SimpleOpenNI;

/**
 * @author Heitor Guerra Carneiro.
 * @version 1.0
 * @since March 2015.
 */
public class TimeUpPose extends BaseGesture implements NewPose {
	private SimpleOpenNI context;
	private GestureRecognize poseOne;
	private GestureRecognize poseTwo;
	private GestureRecognize poseThree;

	public TimeUpPose(SimpleOpenNI context) {
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
		// LEFT_HAND
		poseOne.addRule(SimpleOpenNI.SKEL_LEFT_HAND, PoseRecognize.LEFT_OF,
				SimpleOpenNI.SKEL_LEFT_ELBOW);
		poseOne.addRule(SimpleOpenNI.SKEL_LEFT_HAND, PoseRecognize.BELOW,
				SimpleOpenNI.SKEL_LEFT_SHOULDER);

		//HEAD
		poseOne.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRecognize.BELOW,
				SimpleOpenNI.SKEL_HEAD);
		
		// RIGHT_HAND
		poseOne.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRecognize.LEFT_OF,
				SimpleOpenNI.SKEL_LEFT_SHOULDER);

		return segmentCheck(userId, poseOne);
	}

	@Override
	public boolean segmentTwo(int userId) {
		// LEFT_HAND
		poseTwo.addRule(SimpleOpenNI.SKEL_LEFT_HAND, PoseRecognize.LEFT_OF,
				SimpleOpenNI.SKEL_LEFT_ELBOW);
		poseTwo.addRule(SimpleOpenNI.SKEL_LEFT_HAND, PoseRecognize.BELOW,
				SimpleOpenNI.SKEL_LEFT_SHOULDER);

		//HEAD
		poseTwo.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRecognize.BELOW,
				SimpleOpenNI.SKEL_HEAD);
		// RIGHT_HAND
		poseTwo.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRecognize.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_SHOULDER);
		poseTwo.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRecognize.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_ELBOW);
		return segmentCheck(userId, poseTwo);

	}

	@Override
	public boolean segmentThree(int userId) {
		// LEFT_HAND
		poseThree.addRule(SimpleOpenNI.SKEL_LEFT_HAND, PoseRecognize.LEFT_OF,
				SimpleOpenNI.SKEL_LEFT_ELBOW);
		poseThree.addRule(SimpleOpenNI.SKEL_LEFT_HAND, PoseRecognize.BELOW,
				SimpleOpenNI.SKEL_LEFT_SHOULDER);

		//HEAD
		poseThree.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRecognize.BELOW,
				SimpleOpenNI.SKEL_HEAD);
		// RIGHT_HAND
		poseThree.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRecognize.RIGHT_OF,
				SimpleOpenNI.SKEL_RIGHT_SHOULDER);

		return segmentCheck(userId, poseThree);
	}

	@Override
	public boolean segmentCheck(int userId, GestureRecognize pose) {
		return pose.check(userId);
	}

	@Override
	protected boolean checkStartCondition(int userId) {
		if (segmentOne(userId))
			return true;
		return false;
	}

	@Override
	protected boolean stillValidPosition(int userId) {
		if (segmentTwo(userId)) {
			return true;
		}
		return false;
	}

	@Override
	protected boolean validateFinalPosition(int userId) {
		if (segmentThree(userId))
			return true;
		return false;
	}

	@Override
	public boolean recognize(int userId) {
		return gestureRecognized(userId);
	}

}