package br.com.inpe.kinect.model.gesture.segments;

import SimpleOpenNI.SimpleOpenNI;
import br.com.inpe.kinect.model.gesture.detector.EGestureResult;
import br.com.inpe.kinect.model.gesture.detector.IGestureSegment;
import br.com.inpe.kinect.model.gesture.detector.JointID;
import br.com.inpe.kinect.model.gesture.detector.JointRelation;
import br.com.inpe.kinect.model.gesture.detector.SegmentCheck;

public class ZoomSegment2 implements IGestureSegment {
	private SegmentCheck segment;

	public ZoomSegment2(SimpleOpenNI context) {
		segment = new SegmentCheck(context);
	}

	@Override
	public EGestureResult checkGesture(int userId) {
		// Right and Left Hand in front of Shoulders
		System.out.println("Segment2: 1");
		if (segment.check(JointID.LEFT_HAND, JointRelation.CLOSER_OF,
				JointID.LEFT_ELBOW, userId)
				&& segment.check(JointID.RIGHT_HAND, JointRelation.CLOSER_OF,
						JointID.RIGHT_ELBOW, userId)) {
			// Hands between head and hip
			System.out.println("Segment2: 1");
			if (segment.check(JointID.RIGHT_HAND, JointRelation.BELOW,
					JointID.HEAD, userId)
					&& segment.check(JointID.RIGHT_HAND, JointRelation.ABOVE,
							JointID.TORSO, userId)
					&& segment.check(JointID.LEFT_HAND, JointRelation.BELOW,
							JointID.HEAD, userId)
					&& segment.check(JointID.LEFT_HAND, JointRelation.ABOVE,
							JointID.TORSO, userId)) {
				// Hands outside shoulders
				System.out.println("Segment2: 1");
				if (segment.check(JointID.RIGHT_HAND, JointRelation.RIGHT_OF,
						JointID.RIGHT_SHOULDER, userId)
						&& segment.check(JointID.LEFT_HAND,
								JointRelation.LEFT_OF, JointID.LEFT_SHOULDER,
								userId)) {
					return EGestureResult.SUCCEED;
				}
				return EGestureResult.PAUSING;
			}
			return EGestureResult.FAIL;
		}
		return EGestureResult.FAIL;
	}

}
