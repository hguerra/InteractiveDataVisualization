package testeGestureParts;

import testeGestureDetector.EGestureResult;
import testeGestureDetector.IGestureSegment;
import testeGestureDetector.JointID;
import testeGestureDetector.JointRelation;
import testeGestureDetector.SegmentCheck;
import SimpleOpenNI.SimpleOpenNI;

/**
 * @author Heitor Guerra Carneiro.
 * @version 1.0
 * @since April 2015.
 */
public class WaveRightSegment1 implements IGestureSegment {

	private SegmentCheck segment;

	public WaveRightSegment1(SimpleOpenNI context) {
		segment = new SegmentCheck(context);
	}

	@Override
	public EGestureResult checkGesture(int userId) {
		if (segment.check(JointID.RIGHT_HAND, JointRelation.ABOVE,
				JointID.RIGHT_ELBOW, userId)) {
			if (segment.check(JointID.RIGHT_HAND, JointRelation.RIGHT_OF,
					JointID.RIGHT_ELBOW, userId)) {
				return EGestureResult.SUCCEED;
			}
			return EGestureResult.PAUSING;
		}

		return EGestureResult.FAIL;
	}

}
