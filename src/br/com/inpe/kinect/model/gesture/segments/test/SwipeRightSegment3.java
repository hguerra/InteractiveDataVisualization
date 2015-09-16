package br.com.inpe.kinect.model.gesture.segments.test;

import SimpleOpenNI.SimpleOpenNI;
import br.com.inpe.kinect.model.gesture.detector.EGestureResult;
import br.com.inpe.kinect.model.gesture.detector.IGestureSegment;
import br.com.inpe.kinect.model.gesture.detector.JointID;
import br.com.inpe.kinect.model.gesture.detector.SkeletonPoints;
/**
 * @author Heitor Guerra Carneiro
 * @since May 2015
 * @version 1.0
 */
public class SwipeRightSegment3 extends SkeletonPoints implements IGestureSegment{

	public SwipeRightSegment3(SimpleOpenNI context) {
		super(context);

	}

	@Override
	public EGestureResult checkGesture(int userId) {
		// right hand in front of right shoulder
		if(getZ(userId, JointID.RIGHT_HAND) < getZ(userId, JointID.RIGHT_ELBOW) && getY(userId, JointID.LEFT_HAND) < getY(userId, JointID.CENTER_SHOULDER)){
			// right hand below shoulder height but above hip height
			if(getY(userId, JointID.RIGHT_HAND) < getY(userId, JointID.HEAD) && getY(userId, JointID.RIGHT_HAND)>getY(userId, JointID.CENTER_HIP)){
				// right hand right of right shoulder
				if(getX(userId, JointID.RIGHT_HAND)>getX(userId, JointID.RIGHT_SHOULDER)){
					return EGestureResult.SUCCEED;
				}
				return EGestureResult.PAUSING;
			}
			return EGestureResult.FAIL;
		}
		return EGestureResult.FAIL;
	}
}
