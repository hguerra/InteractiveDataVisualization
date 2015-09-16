package br.com.inpe.kinect.model.gesture.segments.test;

import SimpleOpenNI.SimpleOpenNI;
import br.com.inpe.kinect.model.gesture.detector.EGestureResult;
import br.com.inpe.kinect.model.gesture.detector.IGestureSegment;
import br.com.inpe.kinect.model.gesture.detector.JointID;
import br.com.inpe.kinect.model.gesture.detector.SkeletonPoints;

public class ZoomOutSegment3  extends SkeletonPoints implements IGestureSegment {

	public ZoomOutSegment3(SimpleOpenNI context) {
		super(context);
	}
	@Override
	public EGestureResult checkGesture(int userId) {
		 // Right and Left Hand in front of Shoulders
		if(getZ(userId, JointID.LEFT_HAND) < getZ(userId, JointID.LEFT_ELBOW) && getZ(userId, JointID.RIGHT_HAND) < getZ(userId, JointID.RIGHT_ELBOW)){
			// Hands between head and hip
			if(getY(userId, JointID.RIGHT_HAND)<getY(userId, JointID.HEAD) && getY(userId, JointID.RIGHT_HAND) > getY(userId, JointID.CENTER_HIP) &&
			getY(userId, JointID.LEFT_HAND) < getY(userId, JointID.HEAD) && getY(userId, JointID.LEFT_HAND) > getY(userId, JointID.CENTER_HIP)){
				 // Hands between shoulders
				if(getX(userId, JointID.RIGHT_HAND)<getX(userId, JointID.RIGHT_SHOULDER) && getX(userId, JointID.RIGHT_HAND)> getX(userId, JointID.LEFT_SHOULDER)&&
					getX(userId, JointID.LEFT_HAND)>getX(userId, JointID.LEFT_SHOULDER) && getX(userId, JointID.LEFT_HAND)< getX(userId, JointID.RIGHT_SHOULDER)){
					return EGestureResult.SUCCEED;
				}
				return EGestureResult.PAUSING;
			}
			return EGestureResult.FAIL;
		}
		return EGestureResult.FAIL;
	}
}
