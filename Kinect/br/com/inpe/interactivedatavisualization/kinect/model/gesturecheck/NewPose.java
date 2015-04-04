package br.com.inpe.interactivedatavisualization.kinect.model.gesturecheck;


import SimpleOpenNI.SimpleOpenNI;

public interface NewPose {
	public void constructorMethod(SimpleOpenNI context);
	public boolean segmentOne(int userId);
	public boolean segmentTwo(int userId);
	public boolean segmentThree(int userId);
	public boolean segmentCheck(int userId, GestureRecognize pose);
	public boolean recognize(int userId);
}
