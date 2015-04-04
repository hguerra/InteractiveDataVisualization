package br.com.inpe.interactivedatavisualization.kinect.model.pose;


import br.com.inpe.interactivedatavisualization.kinect.model.gesturecheck.BaseGesture;
import br.com.inpe.interactivedatavisualization.kinect.model.gesturecheck.GestureRecognize;
import br.com.inpe.interactivedatavisualization.kinect.model.gesturecheck.NewPose;
import br.com.inpe.interactivedatavisualization.kinect.model.gesturecheck.PoseRecognize;
import SimpleOpenNI.SimpleOpenNI;

/**
 * @author Heitor Guerra Carneiro.
 * @version 1.0
 * @since March 2015.
 */
public class ZoomInPose extends BaseGesture implements NewPose{
	private SimpleOpenNI context;
	private GestureRecognize poseOne;
	private GestureRecognize poseTwo;
	private GestureRecognize poseThree;

	public ZoomInPose(SimpleOpenNI context) {
		constructorMethod(context);
	}

	@Override
	public void constructorMethod(SimpleOpenNI context) {
		this.context = context;
		poseOne = new GestureRecognize(context);
		poseTwo = new GestureRecognize(context);
		poseThree = new GestureRecognize(context);
	}


	@Override
	public boolean segmentOne(int userId) {
		// 1
		
		return segmentCheck(userId, poseOne);
	}

	@Override
	public boolean segmentTwo(int userId) {
		// 2
		
		return segmentCheck(userId, poseTwo);

	}

	@Override
	public boolean segmentThree(int userId) {
		
		return segmentCheck(userId, poseThree);
	}

	@Override
	public boolean segmentCheck(int userId, GestureRecognize pose) {
		return pose.check(userId);
	}
	
	@Override
	protected boolean checkStartCondition(int userId) {
		if(segmentOne(userId))
			return true;
		return false;
	}

	@Override
	protected boolean stillValidPosition(int userId) {
		if(segmentTwo(userId)){
			return true;
		}
		return false;
	}

	@Override
	protected boolean validateFinalPosition(int userId) {
		if(segmentThree(userId))
			return true;
		return false;
	}
	
	@Override
	public boolean recognize(int userId) {
		return gestureRecognized(userId);
	}


}
