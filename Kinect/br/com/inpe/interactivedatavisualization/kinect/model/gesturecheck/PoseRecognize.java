package br.com.inpe.interactivedatavisualization.kinect.model.gesturecheck;

import processing.core.PVector;
import SimpleOpenNI.SimpleOpenNI;

public class PoseRecognize {
	private int fromJoint;
	private int toJoint;
	private PVector fromJointVector;
	private PVector toJointVector;
	private SimpleOpenNI context;

	private int jointRelation; // one of:
	// Constant
	// X AND Y
	public static final int ABOVE = 1;
	public static final int BELOW = 2;
	public static final int LEFT_OF = 3;
	public static final int RIGHT_OF = 4;
	// Z
	public static final int CLOSER_OF = 5;
	public static final int FARTHER_OF = 6;

	public PoseRecognize(SimpleOpenNI context, int fromJoint,
			int jointRelation, int toJoint) {
		this.context = context;
		this.fromJoint = fromJoint;
		this.toJoint = toJoint;
		this.jointRelation = jointRelation;

		fromJointVector = new PVector();
		toJointVector = new PVector();
	}

	public boolean check(int userId) {

		// populate the joint vectors for the user we're checking
		context.getJointPositionSkeleton(userId, fromJoint, fromJointVector);
		context.getJointPositionSkeleton(userId, toJoint, toJointVector);

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
		case CLOSER_OF:
			result = (fromJointVector.z < toJointVector.z);
			break;
		case FARTHER_OF:
			result = (fromJointVector.z > toJointVector.z);
			break;
		}

		return result;
	}
}
