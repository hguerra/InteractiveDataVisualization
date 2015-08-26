package br.com.inpe.kinect.model.gesture.segments.test;

import SimpleOpenNI.SimpleOpenNI;
import br.com.inpe.kinect.model.gesture.detector.EGestureResult;
import br.com.inpe.kinect.model.gesture.detector.IGestureSegment;
import br.com.inpe.kinect.model.gesture.detector.JointID;
import br.com.inpe.kinect.model.gesture.detector.Position;

public class TranslateRightSegments2 extends Position implements IGestureSegment{

	public TranslateRightSegments2(SimpleOpenNI context) {
		super(context);
	}

	@Override
	public EGestureResult checkGesture(int userId) {
		// Right and left hand in front of the Elbow
		if(getZ(userId, JointID.LEFT_HAND) < getZ(userId, JointID.LEFT_ELBOW) && getZ(userId, JointID.RIGHT_HAND) < getZ(userId, JointID.RIGHT_ELBOW)){
			// Hands between shoulder and hip
			if(getY(userId, JointID.RIGHT_HAND) < getY(userId, JointID.HEAD) && getY(userId, JointID.RIGHT_HAND) > getY(userId, JointID.CENTER_HIP)&&
					getY(userId, JointID.LEFT_HAND) < getY(userId, JointID.HEAD) && getY(userId, JointID.LEFT_HAND) > getY(userId, JointID.CENTER_HIP)){
				// Hands on ShoulderCenter right
				if(getX(userId, JointID.LEFT_HAND) > getX(userId, JointID.CENTER_SHOULDER) && getX(userId, JointID.RIGHT_HAND) > getX(userId, JointID.CENTER_SHOULDER)){
					return EGestureResult.PAUSING;
				}
				return EGestureResult.FAIL;
			}
			return EGestureResult.FAIL;
		}
		return EGestureResult.FAIL;
	}

}
