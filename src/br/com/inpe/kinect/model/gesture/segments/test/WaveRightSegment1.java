package br.com.inpe.kinect.model.gesture.segments.test;

import SimpleOpenNI.SimpleOpenNI;
import br.com.inpe.kinect.model.gesture.detector.EGestureResult;
import br.com.inpe.kinect.model.gesture.detector.IGestureSegment;
import br.com.inpe.kinect.model.gesture.detector.JointID;
import br.com.inpe.kinect.model.gesture.detector.SkeletonPoints;

/**
 * @author Heitor Guerra Carneiro.
 * @version 1.0
 * @since April 2015.
 */
public class WaveRightSegment1 extends SkeletonPoints implements IGestureSegment {

	public WaveRightSegment1(SimpleOpenNI context) {
		super(context);
		}

		@Override
		public EGestureResult checkGesture(int userId) {
			// hand above elbow
			if(getY(userId, JointID.RIGHT_HAND) > getY(userId, JointID.RIGHT_ELBOW)){
				// hand right of elbow
				if(getX(userId, JointID.RIGHT_HAND) > getX(userId, JointID.RIGHT_ELBOW)){
					return EGestureResult.SUCCEED;
				}
				return EGestureResult.PAUSING;
			}
			return EGestureResult.FAIL;
		}

	}