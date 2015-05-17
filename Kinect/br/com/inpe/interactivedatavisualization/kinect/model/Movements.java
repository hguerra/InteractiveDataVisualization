package br.com.inpe.interactivedatavisualization.kinect.model;

import java.util.LinkedList;
import java.util.List;

import testeGestureDetector.EGestureResult;
import testeGestureDetector.EGestureType;
import testeGestureDetector.Gesture;
import testeGestureDetector.GestureDetector;
import testeGestureDetector.IGestureSegment;
import testeGestureDetectorThread.WThread1;
import testeGestureDetectorThread.WThread2;
import testeGestureDetectorTimer.Check;
import testeGestureDetectorTimer.Timer;
import testeGestureDetectorTimer.WaveTimer;
import testeGestureParts.SwipeLeftSegment1;
import testeGestureParts.SwipeLeftSegment2;
import testeGestureParts.SwipeLeftSegment3;
import testeGestureParts.SwipeRightSegment1;
import testeGestureParts.SwipeRightSegment2;
import testeGestureParts.SwipeRightSegment3;
import testeGestureParts.WaveLeftSegment1;
import testeGestureParts.WaveLeftSegment2;
import testeGestureParts.WaveRightSegment1;
import testeGestureParts.WaveRightSegment2;
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
 * @version 2.0
 * @since May 2015.
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
	 * TEST
	 */
	private WaveTimer test;
	private Timer t;
	private boolean c = false;
	private WaveRightSegment1 wR1;
	private WaveRightSegment2 wR2;
	private IGestureSegment[] waveRightParts;
	private WaveLeftSegment1 wL1;
	private WaveLeftSegment2 wL2;
	private IGestureSegment[] waveLeftParts;
	private SwipeRightSegment1 sR1;
	private SwipeRightSegment2 sR2;
	private SwipeRightSegment3 sR3;
	private IGestureSegment[] swipeRightParts;
	private SwipeLeftSegment1 sL1;
	private SwipeLeftSegment2 sL2;
	private SwipeLeftSegment3 sL3;
	private IGestureSegment[] swipeLeftParts;
	private GestureDetector detector;
	
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
		 * TEST
		 */
		test = new WaveTimer(context);
		t = new Timer();
		wR1 = new WaveRightSegment1(context);
		wR2 = new WaveRightSegment2(context);
		waveRightParts = new IGestureSegment[] {wR1, wR2};
		wL1 = new WaveLeftSegment1(context);
		wL2 = new WaveLeftSegment2(context);
		waveLeftParts = new IGestureSegment[] {wL1, wL2};
		sR1 = new SwipeRightSegment1(context);
		sR2 = new SwipeRightSegment2(context);
		sR3 = new SwipeRightSegment3(context);
		swipeRightParts = new IGestureSegment[] {sR1, sR2, sR3};
		sL1 = new SwipeLeftSegment1(context);
		sL2 = new SwipeLeftSegment2(context);
		sL3 = new SwipeLeftSegment3(context);
		swipeLeftParts = new IGestureSegment[] {sL1, sL2, sL3};
		detector = new GestureDetector();
		detector.addGesture(EGestureType.WAVERIGHT, waveRightParts);
		detector.addGesture(EGestureType.WAVELEFT, waveLeftParts);
		detector.addGesture(EGestureType.RIGHTSWIPE, swipeRightParts);
		detector.addGesture(EGestureType.LEFTSWIPE, swipeLeftParts);
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
		 * TEST
		 */
		
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
		//Wave with Gesture Detector
	
		detector.updateAllGestures(userId);
		
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