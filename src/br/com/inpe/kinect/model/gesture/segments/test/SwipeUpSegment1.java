package br.com.inpe.kinect.model.gesture.segments.test;

import SimpleOpenNI.SimpleOpenNI;
import br.com.inpe.kinect.model.gesture.detector.EGestureResult;
import br.com.inpe.kinect.model.gesture.detector.IGestureSegment;
import br.com.inpe.kinect.model.gesture.detector.JointID;
import br.com.inpe.kinect.model.gesture.detector.SkeletonPoints;

public class SwipeUpSegment1 extends SkeletonPoints implements IGestureSegment{

	public SwipeUpSegment1(SimpleOpenNI context) {
		super(context);
		
	}

	@Override
	public EGestureResult checkGesture(int userId) {
		 // right hand in front of right shoulder
		if(getZ(userId, JointID.RIGHT_HAND) < getZ(userId, JointID.RIGHT_SHOULDER)){
			// right hand below head and up the hip center
			if(getY(userId, JointID.RIGHT_HAND)<getY(userId, JointID.HEAD) && getY(userId, JointID.RIGHT_HAND)>getY(userId, JointID.CENTER_HIP)){
				// right elbow right of right shoulder and right hand
				if(getX(userId, JointID.RIGHT_ELBOW) > getX(userId, JointID.RIGHT_SHOULDER) &&
					getX(userId, JointID.RIGHT_ELBOW) > getX(userId, JointID.RIGHT_HAND)){
					return EGestureResult.SUCCEED;
				}
				return EGestureResult.PAUSING;
			}
			return EGestureResult.FAIL;
		}
		return EGestureResult.FAIL;
	}
}