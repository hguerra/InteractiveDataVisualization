package br.com.inpe.interactivedatavisualization.kinect.model;

import java.util.LinkedList;
import java.util.List;

import badimplementation.WaveGestureTESTE;
import br.com.inpe.interactivedatavisualization.kinect.model.gesture.DataDownPose;
import br.com.inpe.interactivedatavisualization.kinect.model.gesture.DataUpPose;
import br.com.inpe.interactivedatavisualization.kinect.model.gesture.TimeDownPose;
import br.com.inpe.interactivedatavisualization.kinect.model.gesture.TimeUpPose;
import br.com.inpe.interactivedatavisualization.kinect.model.gesture.WaveGesture;
import br.com.inpe.interactivedatavisualization.kinect.model.gesture.ZoomInPose;
import br.com.inpe.interactivedatavisualization.kinect.model.gesture.ZoomOutPose;
import br.com.inpe.interactivedatavisualization.kinect.model.gesturecheck.BaseGesture;
import br.com.inpe.interactivedatavisualization.kinect.model.gesturecheck.GestureName;
import br.com.inpe.interactivedatavisualization.kinect.model.gesturecheck.NewPose;
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
	private NewPose dataUp;
	private NewPose dataDown;
	private NewPose zoomIn;
	private NewPose zoomOut;
	private NewPose timeUp;
	private NewPose timeDown;
	private NewPose wave;
	/*
	 * teste
	 */
	private WaveGestureTESTE waveTESTE;
	
	public Movements(SimpleOpenNI context) {
		this.context = context;
		listObservers = new LinkedList<Observer>();
		dataUp = new DataUpPose(context);
		dataDown = new DataDownPose(context);
		zoomIn = new ZoomInPose(context);
		zoomOut = new ZoomOutPose(context);
		timeUp = new TimeUpPose(context);
		timeDown = new TimeDownPose(context);
		wave = new WaveGesture(context);
		/*
		 * teste
		 */
		waveTESTE = new WaveGestureTESTE(context);
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
		/*
		if (dataUp.recognize(userId))
			setMovement(GestureName.DATAUP.getValue());
		if (dataDown.recognize(userId))
			setMovement(GestureName.DATADOWN.getValue());
		
		if (zoomIn.recognize(userId))
			setMovement(GestureName.ZOOMIN.getValue());
		if (zoomOut.recognize(userId))
			setMovement(GestureName.ZOOMOUT.getValue());
		
		if (timeUp.recognize(userId))
			setMovement(GestureName.TIMEUP.getValue());
		if (timeDown.recognize(userId))
			setMovement(GestureName.TIMEDOWN.getValue());
*/
		if (wave.recognize(userId))
			setMovement(GestureName.TIMEUP.getValue());
			
		/*
		 * teste
		 */
		/*
		if(waveTESTE.recognize(userId)){
			System.out.println("OK");
		}*/
	}

	public void setMovement(Integer type) {
		this.movement = type;
		notifyObserversPoseCheck();
	}

	@Override
	public void notifyObserversGestureRecognised(int movement) {
		// TODO Auto-generated method stub
		
	}

}