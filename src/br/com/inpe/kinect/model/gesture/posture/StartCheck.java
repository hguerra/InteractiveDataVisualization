package br.com.inpe.kinect.model.gesture.posture;

import br.com.inpe.kinect.model.gesture.detector.EGestureResult;
import br.com.inpe.kinect.model.gesture.detector.IGestureSegment;
import br.com.inpe.kinect.model.gesture.detector.JointID;
import br.com.inpe.kinect.model.gesture.detector.JointRelation;
import br.com.inpe.kinect.model.gesture.detector.SegmentCheck;
import SimpleOpenNI.SimpleOpenNI;

public class StartCheck implements IGestureSegment {
	private SegmentCheck pose;

	public StartCheck(SimpleOpenNI context) {
		pose = new SegmentCheck(context);
	}

	@Override
	public EGestureResult checkGesture(int userId) {
		if (pose.check(JointID.LEFT_HAND, JointRelation.ABOVE,
				JointID.LEFT_ELBOW, userId)
				&& pose.check(JointID.LEFT_ELBOW, JointRelation.ABOVE,
						JointID.LEFT_SHOULDER, userId))
			return EGestureResult.SUCCEED;
		return EGestureResult.FAIL;
	}

}
