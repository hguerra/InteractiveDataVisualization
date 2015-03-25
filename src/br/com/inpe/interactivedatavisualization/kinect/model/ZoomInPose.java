package br.com.inpe.interactivedatavisualization.kinect.model;

import SimpleOpenNI.SimpleOpenNI;

/**
 * @author Heitor Guerra Carneiro.
 * @version 1.0
 * @since March 2015.
 */
public class ZoomInPose implements NewPose {
	private SimpleOpenNI context;
	private SkeletonPoser pose;

	public ZoomInPose(SimpleOpenNI context) {
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
		// 1
		// hand above elbow
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRule.ABOVE,
				SimpleOpenNI.SKEL_RIGHT_ELBOW);
		// hand right of elbow
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_RIGHT_ELBOW);

		// 2
		// hand above elbow
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRule.ABOVE,
				SimpleOpenNI.SKEL_RIGHT_ELBOW);
		// hand left of elbow
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_ELBOW);

		// 1

		// hand above elbow
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRule.ABOVE,
				SimpleOpenNI.SKEL_RIGHT_ELBOW);
		// hand right of elbow
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_RIGHT_ELBOW);

		// 2
		// hand above elbow
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRule.ABOVE,
				SimpleOpenNI.SKEL_RIGHT_ELBOW);
		// hand left of elbow
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_ELBOW);

		// 1

		// hand above elbow
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRule.ABOVE,
				SimpleOpenNI.SKEL_RIGHT_ELBOW);
		// hand right of elbow
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_RIGHT_ELBOW);

		// 2
		// hand above elbow
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRule.ABOVE,
				SimpleOpenNI.SKEL_RIGHT_ELBOW);
		// hand left of elbow
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_ELBOW);

	}

	@Override
	public boolean verify(int userId) {
		return pose.check(userId);
	}

}
