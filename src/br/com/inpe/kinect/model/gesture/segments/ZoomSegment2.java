package br.com.inpe.kinect.model.gesture.segments;

import br.com.inpe.kinect.model.gesture.detector.EGestureResult;
import br.com.inpe.kinect.model.gesture.detector.IGestureSegment;
import br.com.inpe.kinect.model.gesture.detector.JointID;
import br.com.inpe.kinect.model.gesture.detector.Position;
import SimpleOpenNI.SimpleOpenNI;

public class ZoomSegment2 extends Position implements IGestureSegment{

	public ZoomSegment2(SimpleOpenNI context) {
		super(context);
	}

	@Override
	public EGestureResult checkGesture(int userId) {
		 // Right and Left Hand in front of Shoulders
		if(getZ(userId, JointID.LEFT_HAND) < getZ(userId, JointID.LEFT_ELBOW) && getZ(userId, JointID.RIGHT_HAND) < getZ(userId, JointID.RIGHT_ELBOW)){
			// Hands between head and hip
			if(getY(userId, JointID.RIGHT_HAND)<getY(userId, JointID.HEAD) && getY(userId, JointID.RIGHT_HAND) > getY(userId, JointID.CENTER_HIP) &&
			getY(userId, JointID.LEFT_HAND) < getY(userId, JointID.HEAD) && getY(userId, JointID.LEFT_HAND) > getY(userId, JointID.CENTER_HIP)){
				 // Hands outside shoulders
				if(getX(userId, JointID.RIGHT_HAND)>getX(userId, JointID.RIGHT_SHOULDER) &&
					getX(userId, JointID.LEFT_HAND)<getX(userId, JointID.LEFT_SHOULDER)){
					return EGestureResult.SUCCEED;
				}
				return EGestureResult.PAUSING;
			}
			return EGestureResult.FAIL;
		}
		return EGestureResult.FAIL;
	}

}
