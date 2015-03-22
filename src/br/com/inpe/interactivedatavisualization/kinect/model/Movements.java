package br.com.inpe.interactivedatavisualization.kinect.model;

import java.util.LinkedList;
import java.util.List;

import br.com.inpe.interactivedatavisualization.kinect.view.Observer;
import SimpleOpenNI.SimpleOpenNI;

/**
 * @author Heitor Guerra Carneiro.
 * @version 1.0
 * @since March 2015.
 */
public class Movements implements Subject {
	private Integer movement = 0;
	private SimpleOpenNI context;
	private List<Observer> listObservers;
	private NewPose seventies;

	public Movements(SimpleOpenNI context) {
		this.context = context;
		listObservers = new LinkedList<Observer>();
		seventies = new seventiesPose(context);
	}

	@Override
	public void registerObserver(Observer observer) {
		listObservers.add(observer);
	}

	@Override
	public void notifyObserversPoseCheck() {
		for (Observer i : listObservers)
			i.update(movement);
	}

	public void poseCheck(int userId) {
		if(seventies.verify(userId)){
			System.out.println("Inside Movement Class");
			setMovement(GestureName.SEVENTIES.getValue());
		}
			
	}
	
	public void setMovement(Integer type) {
		this.movement = type;
		notifyObserversPoseCheck();
	}

}