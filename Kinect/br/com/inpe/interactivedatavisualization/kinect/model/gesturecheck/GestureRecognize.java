package br.com.inpe.interactivedatavisualization.kinect.model.gesturecheck;

import java.util.LinkedList;
import java.util.List;

import SimpleOpenNI.SimpleOpenNI;
import br.com.inpe.interactivedatavisualization.kinect.view.Processing;

public class GestureRecognize extends Processing {

	private SimpleOpenNI context;
	private List<PoseRecognize> rules;

	public GestureRecognize(SimpleOpenNI context) {
		this.context = context;
		rules = new LinkedList<PoseRecognize>();
	}

	public void addRule(int fromJoint, int jointRelation, int toJoint) {
		PoseRecognize rule = new PoseRecognize(context, fromJoint,
				jointRelation, toJoint);
		rules.add(rule);
	}

	public boolean check(int userID) {
		boolean result = true;
		for (int i = 0; i < rules.size(); i++) {
			PoseRecognize rule = (PoseRecognize) rules.get(i);
			result = result && rule.check(userID);
		}
		return result;
	}
}
