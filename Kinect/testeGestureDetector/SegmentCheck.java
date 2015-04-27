package testeGestureDetector;

import processing.core.PVector;
import SimpleOpenNI.SimpleOpenNI;

/**
 * @author Heitor Guerra Carneiro.
 * @version 1.0
 * @since April 2015.
 */
public class SegmentCheck {
	// Constant
	// X AND Y
	public static final int ABOVE = 1;
	public static final int BELOW = 2;
	public static final int LEFT_OF = 3;
	public static final int RIGHT_OF = 4;
	// Z
	public static final int CLOSER_OF = 5;
	public static final int FARTHER_OF = 6;

	private PVector fromJointVector;
	private PVector toJointVector;
	private SimpleOpenNI context;

	public SegmentCheck(SimpleOpenNI context) {
		this.context = context;

		fromJointVector = new PVector();
		toJointVector = new PVector();
	}

	public boolean check(int fromJoint, int jointRelation,
			int toJoint, int userId) {

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
