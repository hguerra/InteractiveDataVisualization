package br.com.inpe.interactivedatavisualization.kinect.model;

import java.util.LinkedList;
import java.util.List;

import com.db4o.config.annotations.UpdatedDepth;

import testeGestureDetector.EGestureResult;
import testeGestureDetector.EGestureType;
import testeGestureDetector.Gesture;
import testeGestureDetector.IGestureSegment;
import testeGestureDetector.WaveRightSegment1;
import testeGestureDetector.WaveRightSegment2;
import testeGestureDetectorThread.WThread1;
import testeGestureDetectorThread.WThread2;
import testeGestureDetectorTimer.Check;
import testeGestureDetectorTimer.Timer;
import testeGestureDetectorTimer.WaveTimer;
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
	private boolean c = false;
	private Gesture g;
	WaveRightSegment1 w1;
	WaveRightSegment2 w2;
	IGestureSegment[] gestureParts;
	
	
	
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
		w1 = new WaveRightSegment1(context);
		w2 = new WaveRightSegment2(context);
		gestureParts = new IGestureSegment[] {w1, w2};
		g = new Gesture(gestureParts, EGestureType.WAVERIGHT);
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
		
		//Wave with Timer
		
		/*
		if(test.segmentOne(userId)){
			t.startCounter();
		}
		if(test.segmentTwo(userId) && t.getInitialTime() != null){
			if(t.endCounterMillis()>=30 && t.endCounterMillis() <= 40){
				System.out.println("Wave");
				t.setInitialTime(null);
			}
		}
		*/
		/*
		if(test.segmentTwo(userId)){
			if(t.endCounterMillis()>=30 && t.endCounterMillis() <= 40){
				System.out.println("Wave");
				
			}
		}
		*/
		
		//Wave with Thread
		
		/*
		Thread t = new Thread(new WThread1(context, userId, this));
		t.start();
		if(isC()){
			Thread t2 = new Thread(new WThread2(context, userId, this));
			try {
				t2.sleep(30);
				t2.start();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		*/
		g.updateGesture(userId);
		
		//System.gc();
		//End Wave
	}

	public void setMovement(Integer type) {
		this.movement = type;
		notifyObserversPoseCheck();
	}
	@Override
	public void notifyTest(Integer i) {
		for (Observer k : listObservers)
			k.update(i);
		
	}

	public boolean isC() {
		return c;
	}

	public void setC(boolean c) {
		this.c = c;
	}
}