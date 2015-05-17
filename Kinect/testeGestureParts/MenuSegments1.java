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
public class MenuSegments1 implements IGestureSegment{
	private SegmentCheck segment;

	public MenuSegments1(SimpleOpenNI context) {
		segment = new SegmentCheck(context);
	}
	@Override
	public EGestureResult checkGesture(int userId) {
		if (segment.check(JointID.LEFT_HAND, JointRelation.BELOW, JointID.TORSO, userId) && segment.check(JointID.RIGHT_HAND, JointRelation.BELOW, JointID.TORSO, userId)) {
			if (segment.check(JointID.LEFT_HAND, JointRelation.LEFT_OF, JointID.TORSO, userId)) {
				if(segment.check(JointID.RIGHT_HAND, JointRelation.LEFT_OF, JointID.LEFT_ELBOW, userId)){
					return EGestureResult.SUCCEED;
				}
			}
			return EGestureResult.PAUSING;
		}
		return EGestureResult.FAIL;
	}

}
