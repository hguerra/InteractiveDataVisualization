package br.com.inpe.kinect.model.gesture.segments.test;

import SimpleOpenNI.SimpleOpenNI;
import br.com.inpe.kinect.model.gesture.detector.EGestureResult;
import br.com.inpe.kinect.model.gesture.detector.IGestureSegment;
import br.com.inpe.kinect.model.gesture.detector.JointID;
import br.com.inpe.kinect.model.gesture.detector.Position;

public class RestoreSegment extends Position implements IGestureSegment{

	public RestoreSegment(SimpleOpenNI context) {
		super(context);
	}

	@Override
	public EGestureResult checkGesture(int userId) {
		// Left and right hands below hip left and hip right
		if(getY(userId, JointID.LEFT_HAND)<getY(userId, JointID.LEFT_HIP) && getY(userId, JointID.RIGHT_HAND)<getY(userId, JointID.RIGHT_HIP)){
			 // Left and right hands up hip left and hip right
			if(getY(userId, JointID.LEFT_HAND)>getY(userId, JointID.LEFT_KNEE) && getY(userId, JointID.RIGHT_HAND)>getY(userId, JointID.RIGHT_KNEE)){
				return EGestureResult.SUCCEED;
			}
			return EGestureResult.PAUSING;
		}
		return EGestureResult.FAIL;
	}

}
