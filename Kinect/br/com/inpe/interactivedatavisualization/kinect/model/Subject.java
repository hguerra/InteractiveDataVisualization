package br.com.inpe.interactivedatavisualization.kinect.model;

import br.com.inpe.interactivedatavisualization.kinect.view.Observer;
/**
 * @author Heitor Guerra Carneiro.
 * @version 1.0
 * @since March 2015.
 */
public interface Subject {
	public void registerObserver(Observer observer);
	public void notifyObserversPoseCheck();
	public void notifyTest(Integer i);
}
