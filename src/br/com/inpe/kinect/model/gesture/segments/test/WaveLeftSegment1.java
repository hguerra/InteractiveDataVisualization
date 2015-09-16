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
public class WaveLeftSegment1 extends SkeletonPoints implements IGestureSegment{
	
	public WaveLeftSegment1(SimpleOpenNI context) {
		super(context);
	}

	@Override
	public EGestureResult checkGesture(int userId) {
		// hand above elbow
		if(getY(userId, JointID.LEFT_HAND) > getY(userId, JointID.LEFT_ELBOW)){
			// hand right of elbow
			if(getX(userId, JointID.LEFT_HAND) > getX(userId, JointID.LEFT_ELBOW)){
				return EGestureResult.SUCCEED;
			}
			return EGestureResult.PAUSING;
		}
		return EGestureResult.FAIL;
	}

}
