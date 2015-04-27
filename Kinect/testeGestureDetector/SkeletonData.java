package testeGestureDetector;

import SimpleOpenNI.SimpleOpenNI;
/**
 * @author Heitor Guerra Carneiro.
 * @version 1.0
 * @since April 2015.
 */
public class SkeletonData {
	private SimpleOpenNI context;
	private int userId;
	private int fromJoint;
	private int jointRelation;
	private int toJoint;

	public SkeletonData(SimpleOpenNI context, int userId, int fromJoint,
			int jointRelation, int toJoint) {
		this.context = context;
		this.fromJoint = fromJoint;
		this.toJoint = toJoint;
		this.jointRelation = jointRelation;

	}

	// gets and sets

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getFromJoint() {
		return fromJoint;
	}

	public void setFromJoint(int fromJoint) {
		this.fromJoint = fromJoint;
	}

	public int getJointRelation() {
		return jointRelation;
	}

	public void setJointRelation(int jointRelation) {
		this.jointRelation = jointRelation;
	}

	public int getToJoint() {
		return toJoint;
	}

	public void setToJoint(int toJoint) {
		this.toJoint = toJoint;
	}
}
