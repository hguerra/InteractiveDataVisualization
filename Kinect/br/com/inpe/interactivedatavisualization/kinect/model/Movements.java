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
import br.com.inpe.interactivedatavisualization.kinect.model.gesturecheck.NewPose;
import br.com.inpe.interactivedatavisualization.kinect.view.Observer;
import SimpleOpenNI.SimpleOpenNI;
import TEST.Check;
import TEST.Timer;
import TEST.WaveTimer;

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
	private WaveTimer test;
	private Timer t;
	
	
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
		test = new WaveTimer(context);
		t = new Timer();
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

		if (wave.recognize(userId))
			setMovement(GestureName.TIMEUP.getValue());
			*/
		/*
		 * teste
		 */
		/*
		if(waveTESTE.recognize(userId)){
			System.out.println("OK");
		}*/
		//Wave
		if(test.segmentOne(userId)){
			t.startCounter();
		}
		if(test.segmentTwo(userId) && t.getInitialTime() != null){
			if(t.endCounterMillis()>=30 && t.endCounterMillis() <= 40){
				System.out.println("Wave");
				t.setInitialTime(null);
			}
		}
		/*
		if(test.segmentTwo(userId)){
			if(t.endCounterMillis()>=30 && t.endCounterMillis() <= 40){
				System.out.println("Wave");
				
			}
		}
		*/
		System.gc();
		//End Wave
	}

	public void setMovement(Integer type) {
		this.movement = type;
		notifyObserversPoseCheck();
	}
}