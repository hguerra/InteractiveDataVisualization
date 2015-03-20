package br.com.inpe.interactivedatavisualization.kinect.model;

import SimpleOpenNI.SimpleOpenNI;

public interface NewPose {
	public void constructorMethod(SimpleOpenNI context);
	public void addPose();
	public boolean verify(int userId);
}
