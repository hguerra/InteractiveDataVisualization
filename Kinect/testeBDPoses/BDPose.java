package testeBDPoses;

import java.util.ArrayList;
import processing.core.PVector;
import SimpleOpenNI.SimpleOpenNI;
import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;
/**
 * @author Heitor Guerra Carneiro.
 * @version 1.0
 * @since April 2015.
 */
public class BDPose {
	private SimpleOpenNI context;
	ObjectContainer rules = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "c:/DB4O/rules.db4o");
	
	public BDPose(SimpleOpenNI context) {
		this.context = context;
	}

	public void addRule(int fromJoint, int jointRelation, int toJoint) {
		PoseRule rule = new PoseRule(context, fromJoint, jointRelation, toJoint);
		rules.store(rule);
	}
	

	public ObjectSet search(int userID){
		Query query = rules.query();
		query.constrain(userID);
	    ObjectSet result = query.execute();
		return result;
	}

	public boolean check(int userID) {
		boolean result = true;
		ObjectSet rules = search(userID);
		for(Object i: rules){
			PoseRule rule = (PoseRule)i;
			result = result && rule.check(userID);
		}
		return result;
	}
}

class PoseRule {
	int fromJoint;
	int toJoint;
	private PVector fromJointVector;
	private PVector toJointVector;
	private SimpleOpenNI context;

	protected int jointRelation; // one of:
	protected static final int ABOVE = 1;
	protected static final int BELOW = 2;
	protected static final int LEFT_OF = 3;
	protected static final int RIGHT_OF = 4;

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