package br.com.inpe.kinect.controller;

import br.com.inpe.kinect.model.SwitchTracker;

/**
 * @author Heitor Guerra Carneiro.
 * @version 1.0
 * @since March 2015.
 */
public class DataDown implements Data{

	@Override
	public void setData() {
		System.out.println("DATA DOWN!");
		SwitchTracker.allTurnOn();
		
	}


}
