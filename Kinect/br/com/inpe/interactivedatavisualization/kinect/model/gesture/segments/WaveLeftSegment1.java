package br.com.inpe.interactivedatavisualization.kinect.model.gesture.segments;

import br.com.inpe.interactivedatavisualization.kinect.model.gesture.detector.EGestureResult;
import br.com.inpe.interactivedatavisualization.kinect.model.gesture.detector.IGestureSegment;
import br.com.inpe.interactivedatavisualization.kinect.model.gesture.detector.JointID;
import br.com.inpe.interactivedatavisualization.kinect.model.gesture.detector.JointRelation;
import br.com.inpe.interactivedatavisualization.kinect.model.gesture.detector.SegmentCheck;
import SimpleOpenNI.SimpleOpenNI;
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
