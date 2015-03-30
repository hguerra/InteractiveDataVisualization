package br.com.inpe.interactivedatavisualization.kinect.model.posecheck;

import java.util.LinkedList;
import java.util.List;
import SimpleOpenNI.SimpleOpenNI;

/**
 * @author Heitor Guerra Carneiro.
 * @version 1.0
 * @since March 2015.
 */
public class PositionCheck {
	private SimpleOpenNI context;
	private List<Rules> rules;

	public PositionCheck(SimpleOpenNI context) {
		this.context = context;
		rules = new LinkedList<Rules>();
	}

	public void addRule(int fromJoint, int jointRelation, int toJoint) {
		Rules rule = new Rules(context, fromJoint, jointRelation, toJoint);
		rules.add(rule);
	}

	public boolean check(int userID) {
		boolean result = true;
		for (int i = 0; i < rules.size(); i++) {
			Rules rule = (Rules) rules.get(i);
			result = result && rule.check(userID);
		}
		return result;
	}

}
