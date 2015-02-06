package br.com.inpe.interactivedatavisualization.kinect;

import java.util.ArrayList;
import processing.core.PVector;
import SimpleOpenNI.SimpleOpenNI;

/**
 * This class save and compare body position.
 * 
 * @author Heitor Guerra Carneiro.
 * @version 1.0
 * @since February 2015.
 * 
 */
public class SkeletonPoser extends VisualObjects {
	
	SimpleOpenNI context;
	ArrayList<PoseRule> rules;

	public SkeletonPoser(SimpleOpenNI context) {
		this.context = context;
		rules = new ArrayList<PoseRule>();
	}

	public void addRule(int fromJoint, int jointRelation, int toJoint) {
		PoseRule rule = new PoseRule(context, fromJoint, jointRelation, toJoint);
		rules.add(rule);
	}

	public boolean check(int userID) {
		boolean result = true;
		for (int i = 0; i < rules.size(); i++) {
			PoseRule rule = (PoseRule) rules.get(i);
			result = result && rule.check(userID);
		}
		return result;
	}
}

class PoseRule {
	int fromJoint;
	int toJoint;
	PVector fromJointVector;
	PVector toJointVector;
	SimpleOpenNI context;

	int jointRelation; // one of:
	static final int ABOVE = 1;
	static final int BELOW = 2;
	static final int LEFT_OF = 3;
	static final int RIGHT_OF = 4;

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
