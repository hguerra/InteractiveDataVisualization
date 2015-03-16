package br.com.inpe.interactivedatavisualization.kinect.model;
import SimpleOpenNI.SimpleOpenNI;
/**
 * @author Heitor Guerra Carneiro.
 * @version 1.0
 * @since March 2015.
 */
public class Model {
	private SimpleOpenNI context;
	private SkeletonPoser pose;

	public Model(SimpleOpenNI context) {
		pose = new SkeletonPoser(context);
		this.context = context;
		addPose();
	}

	public void addPose() {
		seventiesPose();
	}

	public void seventiesPose() {
		// rules for the right arm
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRule.ABOVE,
				SimpleOpenNI.SKEL_RIGHT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_RIGHT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_ELBOW, PoseRule.ABOVE,
				SimpleOpenNI.SKEL_RIGHT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_ELBOW, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_RIGHT_SHOULDER);
		// rules for the left arm
		pose.addRule(SimpleOpenNI.SKEL_LEFT_ELBOW, PoseRule.BELOW,
				SimpleOpenNI.SKEL_LEFT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_ELBOW, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_LEFT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_HAND, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_LEFT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_HAND, PoseRule.BELOW,
				SimpleOpenNI.SKEL_LEFT_ELBOW);
		// rules for the right leg
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_KNEE, PoseRule.BELOW,
				SimpleOpenNI.SKEL_RIGHT_HIP);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_KNEE, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_RIGHT_HIP);
		// rules for the left leg
		pose.addRule(SimpleOpenNI.SKEL_LEFT_KNEE, PoseRule.BELOW,
				SimpleOpenNI.SKEL_LEFT_HIP);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_KNEE, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_LEFT_HIP);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_FOOT, PoseRule.BELOW,
				SimpleOpenNI.SKEL_LEFT_KNEE);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_FOOT, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_LEFT_KNEE);
	}

	public void poseCheck(int userId) {
		// check to see if the user
		// is in the pose
		if (pose.check(userId)) {
			// if they are, set the color white
			// stroke(255);
			System.out.println("Skeleton Poser OK!");
		}
	}

}
