package br.com.inpe.interactivedatavisualization.kinect.model;

import SimpleOpenNI.SimpleOpenNI;

/**
 * @author Heitor Guerra Carneiro.
 * @version 1.0
 * @since March 2015.
 */
public class ZoomOutPose implements NewPose {
	private SimpleOpenNI context;
	private SkeletonPoser pose;

	public ZoomOutPose(SimpleOpenNI context) {
		constructorMethod(context);
	}

	@Override
	public void constructorMethod(SimpleOpenNI context) {
		this.context = context;
		pose = new SkeletonPoser(context);
		addPose();

	}

	@Override
	public void addPose() {
		pose.addRule(SimpleOpenNI.SKEL_LEFT_HAND, PoseRule.ABOVE,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_HAND, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_LEFT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_HAND, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_ELBOW);

	}

	@Override
	public boolean verify(int userId) {
		return pose.check(userId);
	}

}
