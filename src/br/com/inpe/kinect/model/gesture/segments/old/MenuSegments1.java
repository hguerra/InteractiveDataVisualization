package br.com.inpe.kinect.model.gesture.segments.old;

import br.com.inpe.kinect.model.gesture.detector.EGestureResult;
import br.com.inpe.kinect.model.gesture.detector.IGestureSegment;
import br.com.inpe.kinect.model.gesture.detector.JointID;
import br.com.inpe.kinect.model.gesture.detector.JointRelation;
import br.com.inpe.kinect.model.gesture.detector.SegmentCheck;
import SimpleOpenNI.SimpleOpenNI;
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
