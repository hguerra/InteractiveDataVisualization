package br.com.inpe.kinect.model.gesture.segments.test;

import SimpleOpenNI.SimpleOpenNI;
import br.com.inpe.kinect.model.gesture.detector.EGestureResult;
import br.com.inpe.kinect.model.gesture.detector.IGestureSegment;
import br.com.inpe.kinect.model.gesture.detector.JointID;
import br.com.inpe.kinect.model.gesture.detector.SkeletonPoints;

public class TranslateLeftSegments3 extends SkeletonPoints implements IGestureSegment{

	public TranslateLeftSegments3(SimpleOpenNI context) {
		super(context);
	}

	@Override
	public EGestureResult checkGesture(int userId) {
		// Right and left hand in front of the Elbow
		if(getZ(userId, JointID.LEFT_HAND) < getZ(userId, JointID.LEFT_ELBOW) && getZ(userId, JointID.RIGHT_HAND) < getZ(userId, JointID.RIGHT_ELBOW)){
			// Hands between shoulder and hip
			if(getY(userId, JointID.RIGHT_HAND) < getY(userId, JointID.HEAD) && getY(userId, JointID.RIGHT_HAND) > getY(userId, JointID.CENTER_HIP)&&
					getY(userId, JointID.LEFT_HAND) < getY(userId, JointID.HEAD) && getY(userId, JointID.LEFT_HAND) > getY(userId, JointID.CENTER_HIP)){
				// Hands on ShoulderCenter left 
				if(getX(userId, JointID.LEFT_HAND)< getX(userId, JointID.CENTER_SHOULDER) && getX(userId, JointID.RIGHT_HAND) < getX(userId, JointID.CENTER_SHOULDER)){
					 // Hands very close  the distance threshold can be changed
					if(Math.abs(getX(userId, JointID.RIGHT_HAND)-getX(userId, JointID.LEFT_HAND)) < 0){
						return EGestureResult.SUCCEED;
					}
					return EGestureResult.PAUSING;
				}
				return EGestureResult.FAIL;
			}
			return EGestureResult.FAIL;
		}
		return EGestureResult.FAIL;
	}

}

