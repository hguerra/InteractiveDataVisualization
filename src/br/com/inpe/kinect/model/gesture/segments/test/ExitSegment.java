package br.com.inpe.kinect.model.gesture.segments.test;

import SimpleOpenNI.SimpleOpenNI;
import br.com.inpe.kinect.model.gesture.detector.EGestureResult;
import br.com.inpe.kinect.model.gesture.detector.IGestureSegment;
import br.com.inpe.kinect.model.gesture.detector.JointID;
import br.com.inpe.kinect.model.gesture.detector.SkeletonPoints;

public class ExitSegment extends SkeletonPoints implements IGestureSegment{

	public ExitSegment(SimpleOpenNI context) {
		super(context);
	}

	@Override
	public EGestureResult checkGesture(int userId) {
		 // Left and right hands below hip
		if(getY(userId, JointID.LEFT_HAND)<getY(userId, JointID.CENTER_HIP) && getY(userId, JointID.RIGHT_HAND)<getY(userId, JointID.CENTER_HIP)){
			// left hand 0.3 to left of center hip
			if(getX(userId, JointID.LEFT_HAND)<getX(userId, JointID.CENTER_HIP)-0.3){
				// left hand 0.2 to left of left elbow or 0.1
				if(getX(userId, JointID.LEFT_HAND)<getX(userId, JointID.LEFT_ELBOW) - 0.2){
					return EGestureResult.SUCCEED;
				}
			}
			return EGestureResult.PAUSING;
		}
		return EGestureResult.FAIL;
	}
}
