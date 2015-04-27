package testeGestureDetector;

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
		/*
		 * Possible bug segment.ABOVE or JointID
		 */
		if (segment.check(JointID.RIGHT_HAND, segment.ABOVE,
				JointID.RIGHT_ELBOW, userId)) {
			if (segment.check(JointID.RIGHT_HAND, segment.RIGHT_OF,
					JointID.RIGHT_ELBOW, userId)) {
				return EGestureResult.SUCCEED;
			}
			return EGestureResult.PAUSING;
		}

		return EGestureResult.FAIL;
	}

}