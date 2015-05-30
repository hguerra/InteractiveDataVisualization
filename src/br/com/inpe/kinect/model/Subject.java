package br.com.inpe.kinect.model;

import br.com.inpe.kinect.view.Observer;
/**
 * @author Heitor Guerra Carneiro.
 * @version 1.0
 * @since March 2015.
 */
public interface Subject {
	public void registerObserver(Observer observer);
	public void notifyObserversPoseCheck();
	public void notifyObserversPoseCheck(Integer i);
}
