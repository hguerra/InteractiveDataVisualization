package br.com.inpe.kinect.controller;

import br.com.inpe.kinect.model.SwitchTracker;

/**
 * @author Heitor Guerra Carneiro.
 * @version 1.0
 * @since March 2015.
 */
public class TimeDown implements Time{

	@Override
	public void setTime() {
		System.out.println("TIME DOWN!");
		SwitchTracker.allTurnOn();
	}

}
