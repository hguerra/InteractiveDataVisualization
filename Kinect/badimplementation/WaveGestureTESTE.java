package badimplementation;

import br.com.inpe.interactivedatavisualization.kinect.model.gesturecheck.BaseGesture;
import br.com.inpe.interactivedatavisualization.kinect.model.gesturecheck.PoseRecognize;

import SimpleOpenNI.SimpleOpenNI;

public class WaveGestureTESTE extends BaseGesture {
	private SimpleOpenNI context;

	public WaveGestureTESTE(SimpleOpenNI context) {
		this.context = context;
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