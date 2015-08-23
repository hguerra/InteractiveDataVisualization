package br.com.inpe.kinect.model.gesture.segments.test;

import SimpleOpenNI.SimpleOpenNI;
import br.com.inpe.kinect.model.gesture.detector.EGestureResult;
import br.com.inpe.kinect.model.gesture.detector.IGestureSegment;
import br.com.inpe.kinect.model.gesture.detector.Position;

public class RotateAntiClockSegments2 extends Position implements IGestureSegment{

	public RotateAntiClockSegments2(SimpleOpenNI context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public EGestureResult checkGesture(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
