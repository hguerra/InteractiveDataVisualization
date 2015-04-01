package br.com.inpe.interactivedatavisualization.kinect.model.posecheck;
import java.util.LinkedList;
import java.util.List;

import br.com.inpe.interactivedatavisualization.kinect.view.Processing;
import SimpleOpenNI.SimpleOpenNI;

/**
 * @author Heitor Guerra Carneiro.
 * @version 1.0
 * @since March 2015.
 */
public class SkeletonPoser extends Processing {
	private SimpleOpenNI context;
	private List<PoseRule> rules;

	public SkeletonPoser(SimpleOpenNI context) {
		this.context = context;
		rules = new LinkedList<PoseRule>();
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