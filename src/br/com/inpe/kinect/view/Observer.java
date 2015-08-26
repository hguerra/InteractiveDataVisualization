package br.com.inpe.kinect.view;

import br.com.inpe.kinect.model.gesture.detector.EGestureType;

/**
 * @author Heitor Guerra Carneiro.
 * @version 1.0
 * @since March 2015.
 */
public interface Observer {
	public void update(EGestureType type);
}
