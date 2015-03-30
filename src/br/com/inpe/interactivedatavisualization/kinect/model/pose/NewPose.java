package br.com.inpe.interactivedatavisualization.kinect.model.pose;

import SimpleOpenNI.SimpleOpenNI;
/**
 * @author Heitor Guerra Carneiro.
 * @version 1.0
 * @since March 2015.
 */
public interface NewPose {
	public void constructorMethod(SimpleOpenNI context);
	public void addPose();
	public boolean verify(int userId);
}
