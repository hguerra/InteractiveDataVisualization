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
public class ZoomInPose extends BaseGesture implements NewPose {
	private SimpleOpenNI context;
	private GestureRecognize poseOne;
	private GestureRecognize poseTwo;
	private GestureRecognize poseThree;

	public ZoomInPose(SimpleOpenNI context) {
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
		// HANDS BETWEEN SHOULDERS
		poseOne.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRecognize.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_SHOULDER);
		poseOne.addRule(SimpleOpenNI.SKEL_LEFT_HAND, PoseRecognize.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_SHOULDER);

		// HANDS ABOVE HIP
		poseOne.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRecognize.ABOVE,
				SimpleOpenNI.SKEL_RIGHT_HIP);
		poseOne.addRule(SimpleOpenNI.SKEL_LEFT_HAND, PoseRecognize.ABOVE,
				SimpleOpenNI.SKEL_LEFT_HIP);

		// HANDS BELOW NECK
		poseOne.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRecognize.BELOW,
				SimpleOpenNI.SKEL_NECK);
		poseOne.addRule(SimpleOpenNI.SKEL_LEFT_HAND, PoseRecognize.BELOW,
				SimpleOpenNI.SKEL_NECK);

		return segmentCheck(userId, poseOne);
	}

	@Override
	public boolean segmentTwo(int userId) {
		// HANDS CLOSER OF SHOUDERS
		poseTwo.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRecognize.CLOSER_OF,
				SimpleOpenNI.SKEL_RIGHT_SHOULDER);
		poseTwo.addRule(SimpleOpenNI.SKEL_LEFT_HAND, PoseRecognize.CLOSER_OF,
				SimpleOpenNI.SKEL_LEFT_SHOULDER);

		// RIGHT HAND ABOVE NECK
		poseTwo.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRecognize.ABOVE,
				SimpleOpenNI.SKEL_NECK);

		// LEFT HANDS ABOVE HIP
		poseTwo.addRule(SimpleOpenNI.SKEL_LEFT_HAND, PoseRecognize.BELOW,
				SimpleOpenNI.SKEL_NECK);
		poseTwo.addRule(SimpleOpenNI.SKEL_LEFT_HAND, PoseRecognize.ABOVE,
				SimpleOpenNI.SKEL_LEFT_HIP);

		return segmentCheck(userId, poseTwo);

	}

	@Override
	public boolean segmentThree(int userId) {
		// HANDS CLOSER OF SHOUDERS
		poseThree.addRule(SimpleOpenNI.SKEL_RIGHT_HAND,
				PoseRecognize.CLOSER_OF, SimpleOpenNI.SKEL_RIGHT_SHOULDER);
		poseThree.addRule(SimpleOpenNI.SKEL_LEFT_HAND, PoseRecognize.CLOSER_OF,
				SimpleOpenNI.SKEL_LEFT_SHOULDER);

		// RIGHT HAND ABOVE HEAD
		poseThree.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRecognize.ABOVE,
				SimpleOpenNI.SKEL_HEAD);
		poseThree.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRecognize.RIGHT_OF,
				SimpleOpenNI.SKEL_RIGHT_ELBOW);

		// LEFT HANDS BELOW HIP
		poseThree.addRule(SimpleOpenNI.SKEL_LEFT_HAND, PoseRecognize.BELOW,
				SimpleOpenNI.SKEL_LEFT_HIP);
		
		poseThree.addRule(SimpleOpenNI.SKEL_LEFT_HAND, PoseRecognize.LEFT_OF,
				SimpleOpenNI.SKEL_LEFT_HIP);
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
