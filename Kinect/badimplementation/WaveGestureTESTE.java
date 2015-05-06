package badimplementation;

import java.util.LinkedList;
import java.util.List;

import br.com.inpe.interactivedatavisualization.kinect.model.gesturecheck.BaseGesture;
import br.com.inpe.interactivedatavisualization.kinect.model.gesturecheck.PoseRecognize;
import SimpleOpenNI.SimpleOpenNI;

public class WaveGestureTESTE extends BaseGesture {
	private SimpleOpenNI context;
	private List<PoseRecognize> poseOne;
	private List<PoseRecognize> poseTwo;
	private List<PoseRecognize> poseThree;

	public WaveGestureTESTE(SimpleOpenNI context) {
		this.context = context;
		poseOne = new LinkedList<PoseRecognize>();
		poseTwo = new LinkedList<PoseRecognize>();
		poseThree = new LinkedList<PoseRecognize>();
	}

	@Override
	protected boolean checkStartCondition(int userId) {
		if (segmentOne(userId))
			return true;
		return false;
	}

	@Override
	protected boolean stillValidPosition(int userId) {
		if (segmentTwo(userId)) {
			return true;
		}
		return false;
	}

	@Override
	protected boolean validateFinalPosition(int userId) {
		if (segmentThree(userId))
			return true;
		return false;
	}
	public boolean recognize(int userId) {
		return gestureRecognized(userId);
	}
	
	/*
	 public boolean recognize(int userId) {
		return gestureRecognized(userId);
	}
	public void addRule (int fromJoint, int jointRelation, int toJoint, List<PoseRecognize> rules){
		PoseRecognize rule = new PoseRecognize(context, fromJoint,
				jointRelation, toJoint);
		rules.add(rule);
	}
	
	public boolean segmentCheck(int userId, List<PoseRecognize> rules) {
		boolean result = false;
		for (int i = 0; i < rules.size(); i++) {
			if(rules.get(i).check(userId))
				result = true;
		}
		return result;
	}
	
	public boolean segmentOne(int userId) {
		// 1
		addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRecognize.ABOVE,
				SimpleOpenNI.SKEL_HEAD, poseOne);
		addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRecognize.ABOVE,
				SimpleOpenNI.SKEL_RIGHT_SHOULDER, poseOne);
		addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRecognize.ABOVE,
				SimpleOpenNI.SKEL_RIGHT_ELBOW, poseOne);
		addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRecognize.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_ELBOW, poseOne);
		return segmentCheck(userId, poseOne);
	}

	
	public boolean segmentTwo(int userId) {
		// 2
		addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRecognize.ABOVE,
				SimpleOpenNI.SKEL_HEAD, poseTwo);
		addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRecognize.ABOVE,
				SimpleOpenNI.SKEL_RIGHT_SHOULDER, poseTwo);
		addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRecognize.ABOVE,
				SimpleOpenNI.SKEL_RIGHT_ELBOW, poseTwo);
		addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRecognize.RIGHT_OF,
				SimpleOpenNI.SKEL_RIGHT_ELBOW, poseTwo);
		return segmentCheck(userId, poseTwo);

	}

	
	public boolean segmentThree(int userId) {
		addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRecognize.ABOVE,
				SimpleOpenNI.SKEL_HEAD, poseThree);
		addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRecognize.ABOVE,
				SimpleOpenNI.SKEL_RIGHT_SHOULDER, poseThree);
		addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRecognize.ABOVE,
				SimpleOpenNI.SKEL_RIGHT_ELBOW, poseThree);
		return segmentCheck(userId, poseThree);
	}
	*/


	public boolean segmentCheck(int fromJoint, int jointRelation, int toJoint,
			int userId) {
		PoseRecognize rule = new PoseRecognize(context, fromJoint,
				jointRelation, toJoint);
		return rule.check(userId);
	}

	public boolean segmentOne(int userId) {
		// 1
		if (segmentCheck(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRecognize.ABOVE,
				SimpleOpenNI.SKEL_HEAD, userId)
				&& segmentCheck(SimpleOpenNI.SKEL_RIGHT_HAND,
						PoseRecognize.ABOVE, SimpleOpenNI.SKEL_RIGHT_SHOULDER,
						userId)
				&& segmentCheck(SimpleOpenNI.SKEL_RIGHT_HAND,
						PoseRecognize.ABOVE, SimpleOpenNI.SKEL_RIGHT_ELBOW,
						userId)
				&& segmentCheck(SimpleOpenNI.SKEL_RIGHT_HAND,
						PoseRecognize.LEFT_OF, SimpleOpenNI.SKEL_RIGHT_ELBOW,
						userId)) {
			return true;
		}
		return false;
	}

	public boolean segmentTwo(int userId) {
		// 2
		if (segmentCheck(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRecognize.ABOVE,
				SimpleOpenNI.SKEL_HEAD, userId)
				&& segmentCheck(SimpleOpenNI.SKEL_RIGHT_HAND,
						PoseRecognize.ABOVE, SimpleOpenNI.SKEL_RIGHT_SHOULDER,
						userId)
				&& segmentCheck(SimpleOpenNI.SKEL_RIGHT_HAND,
						PoseRecognize.ABOVE, SimpleOpenNI.SKEL_RIGHT_ELBOW,
						userId)
				&& segmentCheck(SimpleOpenNI.SKEL_RIGHT_HAND,
						PoseRecognize.RIGHT_OF, SimpleOpenNI.SKEL_RIGHT_ELBOW,
						userId)) {
			return true;
		}
		return false;

	}

	public boolean segmentThree(int userId) {
		if (segmentCheck(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRecognize.ABOVE,
				SimpleOpenNI.SKEL_HEAD, userId)
				&& segmentCheck(SimpleOpenNI.SKEL_RIGHT_HAND,
						PoseRecognize.ABOVE, SimpleOpenNI.SKEL_RIGHT_SHOULDER,
						userId)
				&& segmentCheck(SimpleOpenNI.SKEL_RIGHT_HAND,
						PoseRecognize.ABOVE, SimpleOpenNI.SKEL_RIGHT_ELBOW,
						userId)) {
			return true;

		}
		return false;

	}

}