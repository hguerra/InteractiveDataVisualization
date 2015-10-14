package br.com.inpe.kinect.model;

import br.com.inpe.kinect.model.gesture.detector.EGestureType;
import br.com.inpe.kinect.model.posture.EPostureType;
import br.com.inpe.kinect.view.Observer;
/**
 * @author Heitor Guerra Carneiro.
 * @version 1.0
 * @since March 2015.
 */
public interface Subject {
	public void registerObserver(Observer observer);
	public void notifyObserverGesture(EGestureType type);
	public void notifyObserverPosture(EPostureType type);
}
