package testeGestureParts;

import SimpleOpenNI.SimpleOpenNI;
import testeGestureDetector.EGestureResult;
import testeGestureDetector.IGestureSegment;
import testeGestureDetector.JointID;
import testeGestureDetector.JointRelation;
import testeGestureDetector.SegmentCheck;
/**
 * @author Heitor Guerra Carneiro
 * @since May 2015
 * @version 1.0
 */
public class WaveLeftSegment1 implements IGestureSegment{
	private SegmentCheck segment;
	
	public WaveLeftSegment1(SimpleOpenNI context) {
		segment = new SegmentCheck(context);
	}
	@Override
	public EGestureResult checkGesture(int userId) {
		if (segment.check(JointID.LEFT_HAND, JointRelation.ABOVE,
				JointID.LEFT_ELBOW, userId)) {
			if (segment.check(JointID.LEFT_HAND, JointRelation.RIGHT_OF,
					JointID.LEFT_ELBOW, userId)) {
				return EGestureResult.SUCCEED;
			}
			return EGestureResult.PAUSING;
		}

		return EGestureResult.FAIL;
	}

}
