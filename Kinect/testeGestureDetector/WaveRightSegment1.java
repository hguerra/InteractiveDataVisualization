package testeGestureDetector;

import SimpleOpenNI.SimpleOpenNI;

/**
 * @author Heitor Guerra Carneiro.
 * @version 1.0
 * @since April 2015.
 */
public class WaveRightSegment1 implements IGestureSegment{
	
	private SegmentCheck segment;
	
	public WaveRightSegment1(SimpleOpenNI context) {
		segment = new SegmentCheck(context);
	}
	
	@Override
	public EGestureResult checkGesture(int userId) {
		/*
		 * Possible bug segment.ABOVE or JointID
		 */
		if(segment.check(JointID.RIGHT_HAND, segment.BELOW, JointID.RIGHT_ELBOW,userId)){
			if(segment.check(JointID.RIGHT_HAND, segment.BELOW, JointID.RIGHT_ELBOW, userId)){
				return EGestureResult.SUCCEED;
			}
			return EGestureResult.PAUSING;
		}
		
		return EGestureResult.FAIL;
	}

}
