package testeGestureDetector;

/**
 * @author Heitor Guerra Carneiro.
 * @version 1.0
 * @since April 2015.
 */
public class SkeletonData {
	private int userId;
	private int fromJoint;
	private int jointRelation;
	private int toJoint;

	public SkeletonData(int userId, int fromJoint, int jointRelation,
			int toJoint) {
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
