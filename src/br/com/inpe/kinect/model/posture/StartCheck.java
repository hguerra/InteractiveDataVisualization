package br.com.inpe.kinect.model.posture;

import br.com.inpe.kinect.model.gesture.detector.EGestureResult;
import br.com.inpe.kinect.model.gesture.detector.IGestureSegment;
import br.com.inpe.kinect.model.gesture.detector.JointID;
import br.com.inpe.kinect.model.gesture.detector.JointRelation;
import br.com.inpe.kinect.model.gesture.detector.SegmentCheck;
import SimpleOpenNI.SimpleOpenNI;

public class StartCheck extends SegmentCheck implements IGestureSegment {

	public StartCheck(SimpleOpenNI context) {
		super(context);
	}
	
	@Override
	public EGestureResult checkGesture(int userId) {
		if (check(JointID.LEFT_HAND, JointRelation.ABOVE,
				JointID.LEFT_ELBOW, userId)
				&& check(JointID.LEFT_ELBOW, JointRelation.ABOVE,
						JointID.LEFT_SHOULDER, userId))
			return EGestureResult.SUCCEED;
		return EGestureResult.FAIL;
	}

}
