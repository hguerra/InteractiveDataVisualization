package testeGestureDetector;

import processing.core.PVector;
import SimpleOpenNI.SimpleOpenNI;

/**
 * @author Heitor Guerra Carneiro.
 * @version 1.0
 * @since April 2015.
 */
public class SegmentCheck {
	private PVector fromJointVector;
	private PVector toJointVector;
	private SimpleOpenNI context;

	public SegmentCheck(SimpleOpenNI context) {
		this.context = context;

		fromJointVector = new PVector();
		toJointVector = new PVector();
	}

	public boolean check(int fromJoint, int jointRelation, int toJoint,
			int userId) {

		// populate the joint vectors for the user we're checking
		context.getJointPositionSkeleton(userId, fromJoint, fromJointVector);
		context.getJointPositionSkeleton(userId, toJoint, toJointVector);

		boolean result = false;

		switch (jointRelation) {
		case JointRelation.ABOVE:
			result = (fromJointVector.y > toJointVector.y);
			break;
		case JointRelation.BELOW:
			result = (fromJointVector.y < toJointVector.y);
			break;
		case JointRelation.LEFT_OF:
			result = (fromJointVector.x < toJointVector.x);
			break;
		case JointRelation.RIGHT_OF:
			result = (fromJointVector.x > toJointVector.x);
			break;
		case JointRelation.CLOSER_OF:
			result = (fromJointVector.z < toJointVector.z);
			break;
		case JointRelation.FARTHER_OF:
			result = (fromJointVector.z > toJointVector.z);
			break;
		}

		return result;
	}
}
