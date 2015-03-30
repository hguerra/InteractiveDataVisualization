package br.com.inpe.interactivedatavisualization.kinect.model.posecheck;

import processing.core.PVector;
import SimpleOpenNI.SimpleOpenNI;

/**
 * @author Heitor Guerra Carneiro.
 * @version 1.0
 * @since March 2015.
 */
public class PoseRule {
	int fromJoint;
	int toJoint;
	private PVector fromJointVector;
	private PVector toJointVector;
	private SimpleOpenNI context;

	private int jointRelation; // one of:
	//Constant
	public static final int ABOVE = 1;
	public static final int BELOW = 2;
	public static final int LEFT_OF = 3;
	public static final int RIGHT_OF = 4;

	public PoseRule(SimpleOpenNI context, int fromJoint, int jointRelation,
			int toJoint) {
		this.context = context;
		this.fromJoint = fromJoint;
		this.toJoint = toJoint;
		this.jointRelation = jointRelation;

		fromJointVector = new PVector();
		toJointVector = new PVector();
	}

	public boolean check(int userID) {

		// populate the joint vectors for the user we're checking
		context.getJointPositionSkeleton(userID, fromJoint, fromJointVector);
		context.getJointPositionSkeleton(userID, toJoint, toJointVector);

		boolean result = false;

		switch (jointRelation) {
		case ABOVE:
			result = (fromJointVector.y > toJointVector.y);
			break;
		case BELOW:
			result = (fromJointVector.y < toJointVector.y);
			break;
		case LEFT_OF:
			result = (fromJointVector.x < toJointVector.x);
			break;
		case RIGHT_OF:
			result = (fromJointVector.x > toJointVector.x);
			break;
		}

		return result;
	}
}
