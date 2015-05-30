package br.com.inpe.kinect.model.gesture.segments;

import br.com.inpe.kinect.model.gesture.detector.EGestureResult;
import br.com.inpe.kinect.model.gesture.detector.IGestureSegment;
import br.com.inpe.kinect.model.gesture.detector.JointID;
import br.com.inpe.kinect.model.gesture.detector.JointRelation;
import br.com.inpe.kinect.model.gesture.detector.SegmentCheck;
import SimpleOpenNI.SimpleOpenNI;

/**
 * @author Heitor Guerra Carneiro.
 * @version 1.0
 * @since April 2015.
 */
public class WaveRightSegment2 implements IGestureSegment {
	private SegmentCheck segment;

	public WaveRightSegment2(SimpleOpenNI context) {
		segment = new SegmentCheck(context);
	}

	@Override
	public EGestureResult checkGesture(int userId) {
		if (segment.check(JointID.RIGHT_HAND, JointRelation.ABOVE,
				JointID.RIGHT_ELBOW, userId)) {
			if (segment.check(JointID.RIGHT_HAND, JointRelation.LEFT_OF,
					JointID.RIGHT_ELBOW, userId)) {
				return EGestureResult.SUCCEED;
			}
			return EGestureResult.PAUSING;
		}

		return EGestureResult.FAIL;
	}

}