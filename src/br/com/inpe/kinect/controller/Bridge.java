package br.com.inpe.kinect.controller;

import br.com.inpe.kinect.view.Observer;
import SimpleOpenNI.SimpleOpenNI;
/**
 * @author Heitor Guerra Carneiro.
 * @version 1.0
 * @since March 2015.
 */
public interface Bridge {
	public void initModel(SimpleOpenNI context);
	public void initPoseCheck(int userId);
	public void initRegisterObserver(Observer kinect);
}
