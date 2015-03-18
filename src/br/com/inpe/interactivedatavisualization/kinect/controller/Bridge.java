package br.com.inpe.interactivedatavisualization.kinect.controller;

import SimpleOpenNI.SimpleOpenNI;
/**
 * @author Heitor Guerra Carneiro.
 * @version 1.0
 * @since March 2015.
 */
public interface Bridge {
	public void initModel(SimpleOpenNI context);
	public void initPoseCheck(int userId);
}
