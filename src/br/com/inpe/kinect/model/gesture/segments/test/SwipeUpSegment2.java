package br.com.inpe.kinect.model.gesture.segments.test;

import SimpleOpenNI.SimpleOpenNI;
import br.com.inpe.kinect.model.gesture.detector.EGestureResult;
import br.com.inpe.kinect.model.gesture.detector.IGestureSegment;
import br.com.inpe.kinect.model.gesture.detector.JointID;
import br.com.inpe.kinect.model.gesture.detector.Position;

public class SwipeUpSegment2 extends Position implements IGestureSegment{

	public SwipeUpSegment2(SimpleOpenNI context) {
		super(context);
	}

	@Override
	public EGestureResult checkGesture(int userId) {
		 // right hand in front of right shoulder
		if(getZ(userId, JointID.RIGHT_HAND) < getZ(userId, JointID.RIGHT_SHOULDER)){
			// right hand up the head
			if(getY(userId, JointID.RIGHT_HAND)>getY(userId, JointID.HEAD)){
				// right eblow right of right shoulder and right hand
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
