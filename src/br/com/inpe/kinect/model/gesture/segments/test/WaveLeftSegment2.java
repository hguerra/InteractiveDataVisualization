package br.com.inpe.kinect.model.gesture.segments.test;

import SimpleOpenNI.SimpleOpenNI;
import br.com.inpe.kinect.model.gesture.detector.EGestureResult;
import br.com.inpe.kinect.model.gesture.detector.IGestureSegment;
import br.com.inpe.kinect.model.gesture.detector.JointID;
import br.com.inpe.kinect.model.gesture.detector.Position;
/**
 * @author Heitor Guerra Carneiro
 * @since May 2015
 * @version 1.0
 */
public class WaveLeftSegment2 extends Position implements IGestureSegment{
	
	public WaveLeftSegment2(SimpleOpenNI context) {
		super(context);
	}

	@Override
	public EGestureResult checkGesture(int userId) {
		// hand above elbow
		if(getY(userId, JointID.LEFT_HAND) > getY(userId, JointID.LEFT_ELBOW)){
			// hand left of elbow
			if(getX(userId, JointID.LEFT_HAND) < getX(userId, JointID.LEFT_ELBOW)){
				return EGestureResult.SUCCEED;
			}
			return EGestureResult.PAUSING;
		}
		return EGestureResult.FAIL;
	}

}
