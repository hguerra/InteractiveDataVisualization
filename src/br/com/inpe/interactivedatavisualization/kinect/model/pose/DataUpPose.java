package br.com.inpe.interactivedatavisualization.kinect.model.pose;

import br.com.inpe.interactivedatavisualization.kinect.model.posecheck.PoseRule;
import br.com.inpe.interactivedatavisualization.kinect.model.posecheck.SkeletonPoser;
import SimpleOpenNI.SimpleOpenNI;

/**
 * @author Heitor Guerra Carneiro.
 * @version 1.0
 * @since March 2015.
 */
public class DataUpPose implements NewPose {
	private SimpleOpenNI context;
	private SkeletonPoser pose;

	public DataUpPose(SimpleOpenNI context) {
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
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_ELBOW, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_ELBOW, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_ELBOW, PoseRule.BELOW,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_ELBOW, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_RIGHT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_RIGHT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRule.BELOW,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_HEAD, PoseRule.ABOVE,
				SimpleOpenNI.SKEL_RIGHT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_HEAD, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_HEAD, PoseRule.ABOVE,
				SimpleOpenNI.SKEL_RIGHT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_HEAD, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_HEAD, PoseRule.ABOVE,
				SimpleOpenNI.SKEL_RIGHT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_HEAD, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_HEAD, PoseRule.ABOVE,
				SimpleOpenNI.SKEL_LEFT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_HEAD, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_HEAD, PoseRule.ABOVE,
				SimpleOpenNI.SKEL_LEFT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_HEAD, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_HEAD, PoseRule.ABOVE,
				SimpleOpenNI.SKEL_LEFT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_HEAD, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_SHOULDER, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_SHOULDER, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_SHOULDER, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_SHOULDER, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_SHOULDER, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_SHOULDER, PoseRule.BELOW,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_SHOULDER, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_ELBOW, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_ELBOW, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_ELBOW, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_ELBOW, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_ELBOW, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_LEFT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_ELBOW, PoseRule.BELOW,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_ELBOW, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_HAND, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_HAND, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_HAND, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_HAND, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_LEFT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_HAND, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_LEFT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_HAND, PoseRule.BELOW,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_HAND, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_SHOULDER, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_SHOULDER, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_SHOULDER, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_SHOULDER, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_SHOULDER, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_SHOULDER, PoseRule.BELOW,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_SHOULDER, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_ELBOW, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_ELBOW, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_RIGHT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_ELBOW, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_ELBOW, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_ELBOW, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_ELBOW, PoseRule.BELOW,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_ELBOW, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_RIGHT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_RIGHT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRule.BELOW,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_HEAD, PoseRule.ABOVE,
				SimpleOpenNI.SKEL_RIGHT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_HEAD, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_HEAD, PoseRule.ABOVE,
				SimpleOpenNI.SKEL_RIGHT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_HEAD, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_HEAD, PoseRule.ABOVE,
				SimpleOpenNI.SKEL_RIGHT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_HEAD, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_HEAD, PoseRule.ABOVE,
				SimpleOpenNI.SKEL_LEFT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_HEAD, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_HEAD, PoseRule.ABOVE,
				SimpleOpenNI.SKEL_LEFT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_HEAD, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_HEAD, PoseRule.ABOVE,
				SimpleOpenNI.SKEL_LEFT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_HEAD, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_SHOULDER, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_SHOULDER, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_SHOULDER, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_SHOULDER, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_SHOULDER, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_SHOULDER, PoseRule.BELOW,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_SHOULDER, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_ELBOW, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_ELBOW, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_ELBOW, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_ELBOW, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_ELBOW, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_LEFT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_ELBOW, PoseRule.BELOW,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_ELBOW, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_HAND, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_HAND, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_HAND, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_HAND, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_LEFT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_HAND, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_LEFT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_HAND, PoseRule.BELOW,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_HAND, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_SHOULDER, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_SHOULDER, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_SHOULDER, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_SHOULDER, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_SHOULDER, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_SHOULDER, PoseRule.BELOW,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_SHOULDER, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_ELBOW, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_ELBOW, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_RIGHT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_ELBOW, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_ELBOW, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_ELBOW, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_ELBOW, PoseRule.BELOW,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_ELBOW, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_RIGHT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_RIGHT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRule.BELOW,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_HEAD, PoseRule.ABOVE,
				SimpleOpenNI.SKEL_RIGHT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_HEAD, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_HEAD, PoseRule.ABOVE,
				SimpleOpenNI.SKEL_RIGHT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_HEAD, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_HEAD, PoseRule.ABOVE,
				SimpleOpenNI.SKEL_RIGHT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_HEAD, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_HEAD, PoseRule.ABOVE,
				SimpleOpenNI.SKEL_LEFT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_HEAD, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_HEAD, PoseRule.ABOVE,
				SimpleOpenNI.SKEL_LEFT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_HEAD, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_HEAD, PoseRule.ABOVE,
				SimpleOpenNI.SKEL_LEFT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_HEAD, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_SHOULDER, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_SHOULDER, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_SHOULDER, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_SHOULDER, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_SHOULDER, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_SHOULDER, PoseRule.BELOW,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_SHOULDER, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_ELBOW, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_ELBOW, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_ELBOW, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_ELBOW, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_ELBOW, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_LEFT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_ELBOW, PoseRule.BELOW,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_ELBOW, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_HAND, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_HAND, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_HAND, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_HAND, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_LEFT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_HAND, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_LEFT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_HAND, PoseRule.BELOW,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_HAND, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_SHOULDER, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_SHOULDER, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_SHOULDER, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_SHOULDER, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_SHOULDER, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_SHOULDER, PoseRule.BELOW,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_SHOULDER, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_ELBOW, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_ELBOW, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_RIGHT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_ELBOW, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_ELBOW, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_ELBOW, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_ELBOW, PoseRule.BELOW,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_ELBOW, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_RIGHT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_RIGHT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRule.BELOW,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_HEAD, PoseRule.ABOVE,
				SimpleOpenNI.SKEL_RIGHT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_HEAD, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_HEAD, PoseRule.ABOVE,
				SimpleOpenNI.SKEL_RIGHT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_HEAD, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_HEAD, PoseRule.ABOVE,
				SimpleOpenNI.SKEL_RIGHT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_HEAD, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_HEAD, PoseRule.ABOVE,
				SimpleOpenNI.SKEL_LEFT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_HEAD, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_HEAD, PoseRule.ABOVE,
				SimpleOpenNI.SKEL_LEFT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_HEAD, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_HEAD, PoseRule.ABOVE,
				SimpleOpenNI.SKEL_LEFT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_HEAD, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_SHOULDER, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_SHOULDER, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_SHOULDER, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_SHOULDER, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_SHOULDER, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_SHOULDER, PoseRule.BELOW,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_SHOULDER, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_ELBOW, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_ELBOW, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_ELBOW, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_ELBOW, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_ELBOW, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_LEFT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_ELBOW, PoseRule.BELOW,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_ELBOW, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_HAND, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_HAND, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_HAND, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_HAND, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_LEFT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_HAND, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_LEFT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_HAND, PoseRule.BELOW,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_HAND, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_SHOULDER, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_SHOULDER, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_SHOULDER, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_SHOULDER, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_SHOULDER, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_SHOULDER, PoseRule.BELOW,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_SHOULDER, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_ELBOW, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_ELBOW, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_RIGHT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_ELBOW, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_ELBOW, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_ELBOW, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_ELBOW, PoseRule.BELOW,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_ELBOW, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_RIGHT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_RIGHT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRule.BELOW,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_HEAD, PoseRule.ABOVE,
				SimpleOpenNI.SKEL_RIGHT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_HEAD, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_HEAD, PoseRule.ABOVE,
				SimpleOpenNI.SKEL_RIGHT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_HEAD, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_HEAD, PoseRule.ABOVE,
				SimpleOpenNI.SKEL_RIGHT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_HEAD, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_HEAD, PoseRule.ABOVE,
				SimpleOpenNI.SKEL_LEFT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_HEAD, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_HEAD, PoseRule.ABOVE,
				SimpleOpenNI.SKEL_LEFT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_HEAD, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_HEAD, PoseRule.ABOVE,
				SimpleOpenNI.SKEL_LEFT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_HEAD, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_SHOULDER, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_SHOULDER, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_SHOULDER, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_SHOULDER, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_SHOULDER, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_SHOULDER, PoseRule.BELOW,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_SHOULDER, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_ELBOW, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_ELBOW, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_ELBOW, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_ELBOW, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_ELBOW, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_LEFT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_ELBOW, PoseRule.BELOW,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_ELBOW, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_HAND, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_HAND, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_HAND, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_HAND, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_LEFT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_HAND, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_LEFT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_HAND, PoseRule.BELOW,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_HAND, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_SHOULDER, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_SHOULDER, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_SHOULDER, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_SHOULDER, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_SHOULDER, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_SHOULDER, PoseRule.BELOW,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_SHOULDER, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_ELBOW, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_ELBOW, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_RIGHT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_ELBOW, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_ELBOW, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_ELBOW, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_ELBOW, PoseRule.BELOW,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_ELBOW, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_RIGHT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_RIGHT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRule.BELOW,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_HEAD, PoseRule.ABOVE,
				SimpleOpenNI.SKEL_RIGHT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_HEAD, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_HEAD, PoseRule.ABOVE,
				SimpleOpenNI.SKEL_RIGHT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_HEAD, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_HEAD, PoseRule.ABOVE,
				SimpleOpenNI.SKEL_RIGHT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_HEAD, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_HEAD, PoseRule.ABOVE,
				SimpleOpenNI.SKEL_LEFT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_HEAD, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_HEAD, PoseRule.ABOVE,
				SimpleOpenNI.SKEL_LEFT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_HEAD, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_HEAD, PoseRule.ABOVE,
				SimpleOpenNI.SKEL_LEFT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_HEAD, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_SHOULDER, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_SHOULDER, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_SHOULDER, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_SHOULDER, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_SHOULDER, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_SHOULDER, PoseRule.BELOW,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_SHOULDER, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_ELBOW, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_ELBOW, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_ELBOW, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_ELBOW, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_ELBOW, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_LEFT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_ELBOW, PoseRule.BELOW,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_ELBOW, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_HAND, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_HAND, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_HAND, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_HAND, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_LEFT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_HAND, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_LEFT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_HAND, PoseRule.BELOW,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_HAND, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_SHOULDER, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_SHOULDER, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_SHOULDER, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_SHOULDER, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_SHOULDER, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_SHOULDER, PoseRule.BELOW,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_SHOULDER, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_ELBOW, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_ELBOW, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_RIGHT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_ELBOW, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_ELBOW, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_ELBOW, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_ELBOW, PoseRule.BELOW,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_ELBOW, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_RIGHT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_RIGHT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRule.BELOW,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_HEAD, PoseRule.ABOVE,
				SimpleOpenNI.SKEL_RIGHT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_HEAD, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_HEAD, PoseRule.ABOVE,
				SimpleOpenNI.SKEL_RIGHT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_HEAD, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_HEAD, PoseRule.ABOVE,
				SimpleOpenNI.SKEL_RIGHT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_HEAD, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_HEAD, PoseRule.ABOVE,
				SimpleOpenNI.SKEL_LEFT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_HEAD, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_HEAD, PoseRule.ABOVE,
				SimpleOpenNI.SKEL_LEFT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_HEAD, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_HEAD, PoseRule.ABOVE,
				SimpleOpenNI.SKEL_LEFT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_HEAD, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_SHOULDER, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_SHOULDER, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_SHOULDER, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_SHOULDER, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_SHOULDER, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_SHOULDER, PoseRule.BELOW,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_SHOULDER, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_ELBOW, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_ELBOW, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_ELBOW, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_ELBOW, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_ELBOW, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_LEFT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_ELBOW, PoseRule.BELOW,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_ELBOW, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_HAND, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_HAND, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_HAND, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_HAND, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_LEFT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_HAND, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_LEFT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_HAND, PoseRule.BELOW,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_HAND, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_SHOULDER, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_SHOULDER, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_SHOULDER, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_SHOULDER, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_SHOULDER, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_SHOULDER, PoseRule.BELOW,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_SHOULDER, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_ELBOW, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_ELBOW, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_RIGHT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_ELBOW, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_ELBOW, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_ELBOW, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_ELBOW, PoseRule.BELOW,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_ELBOW, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_RIGHT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_RIGHT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRule.BELOW,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_HEAD, PoseRule.ABOVE,
				SimpleOpenNI.SKEL_RIGHT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_HEAD, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_HEAD, PoseRule.ABOVE,
				SimpleOpenNI.SKEL_RIGHT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_HEAD, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_HEAD, PoseRule.ABOVE,
				SimpleOpenNI.SKEL_RIGHT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_HEAD, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_HEAD, PoseRule.ABOVE,
				SimpleOpenNI.SKEL_LEFT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_HEAD, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_HEAD, PoseRule.ABOVE,
				SimpleOpenNI.SKEL_LEFT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_HEAD, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_HEAD, PoseRule.ABOVE,
				SimpleOpenNI.SKEL_LEFT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_HEAD, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_SHOULDER, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_SHOULDER, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_SHOULDER, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_SHOULDER, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_SHOULDER, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_SHOULDER, PoseRule.BELOW,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_SHOULDER, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_ELBOW, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_ELBOW, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_ELBOW, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_ELBOW, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_ELBOW, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_LEFT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_ELBOW, PoseRule.BELOW,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_ELBOW, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_HAND, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_HAND, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_HAND, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_HAND, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_LEFT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_HAND, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_LEFT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_HAND, PoseRule.BELOW,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_HAND, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_SHOULDER, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_SHOULDER, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_SHOULDER, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_SHOULDER, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_SHOULDER, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_SHOULDER, PoseRule.BELOW,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_SHOULDER, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_ELBOW, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_ELBOW, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_RIGHT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_ELBOW, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_ELBOW, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_ELBOW, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_ELBOW, PoseRule.BELOW,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_ELBOW, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_RIGHT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_RIGHT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRule.BELOW,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_HEAD, PoseRule.ABOVE,
				SimpleOpenNI.SKEL_RIGHT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_HEAD, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_HEAD, PoseRule.ABOVE,
				SimpleOpenNI.SKEL_RIGHT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_HEAD, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_HEAD, PoseRule.ABOVE,
				SimpleOpenNI.SKEL_RIGHT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_HEAD, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_HEAD, PoseRule.ABOVE,
				SimpleOpenNI.SKEL_LEFT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_HEAD, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_HEAD, PoseRule.ABOVE,
				SimpleOpenNI.SKEL_LEFT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_HEAD, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_HEAD, PoseRule.ABOVE,
				SimpleOpenNI.SKEL_LEFT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_HEAD, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_SHOULDER, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_SHOULDER, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_SHOULDER, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_SHOULDER, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_SHOULDER, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_SHOULDER, PoseRule.BELOW,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_SHOULDER, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_ELBOW, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_ELBOW, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_ELBOW, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_ELBOW, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_ELBOW, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_LEFT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_ELBOW, PoseRule.BELOW,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_ELBOW, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_HAND, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_HAND, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_HAND, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_HAND, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_LEFT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_HAND, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_LEFT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_HAND, PoseRule.BELOW,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_HAND, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_SHOULDER, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_SHOULDER, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_SHOULDER, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_SHOULDER, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_SHOULDER, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_SHOULDER, PoseRule.BELOW,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_SHOULDER, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_ELBOW, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_ELBOW, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_RIGHT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_ELBOW, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_ELBOW, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_ELBOW, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_ELBOW, PoseRule.BELOW,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_ELBOW, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_RIGHT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_RIGHT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_HAND);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_LEFT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRule.BELOW,
				SimpleOpenNI.SKEL_HEAD);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_HEAD);
	}

	@Override
	public boolean verify(int userId) {
		return pose.check(userId);
	}

}