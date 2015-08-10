package br.com.inpe.kinect.model;

import java.util.LinkedList;
import java.util.List;

import br.com.inpe.kinect.model.gesture.detector.EGestureResult;
import br.com.inpe.kinect.model.gesture.detector.EGestureType;
import br.com.inpe.kinect.model.gesture.detector.GestureDetector;
import br.com.inpe.kinect.model.gesture.detector.IGestureSegment;
import br.com.inpe.kinect.model.gesture.segments.LeftClick;
import br.com.inpe.kinect.model.gesture.segments.StartCheck;
import br.com.inpe.kinect.model.gesture.segments.SwipeLeftSegment1;
import br.com.inpe.kinect.model.gesture.segments.SwipeLeftSegment2;
import br.com.inpe.kinect.model.gesture.segments.SwipeLeftSegment3;
import br.com.inpe.kinect.model.gesture.segments.SwipeRightSegment1;
import br.com.inpe.kinect.model.gesture.segments.SwipeRightSegment2;
import br.com.inpe.kinect.model.gesture.segments.SwipeRightSegment3;
import br.com.inpe.kinect.model.gesture.segments.WaveLeftSegment1;
import br.com.inpe.kinect.model.gesture.segments.WaveLeftSegment2;
import br.com.inpe.kinect.model.gesture.segments.WaveRightSegment1;
import br.com.inpe.kinect.model.gesture.segments.WaveRightSegment2;
import br.com.inpe.kinect.view.Observer;
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
	// startCheck
	private IGestureSegment startCheck;
	// leftClick
	private IGestureSegment leftClick;

	public Movements(SimpleOpenNI context) {
		this.context = context;
		listObservers = new LinkedList<Observer>();
		wR1 = new WaveRightSegment1(context);
		wR2 = new WaveRightSegment2(context);
		waveRightParts = new IGestureSegment[] { wR1, wR2 };
		wL1 = new WaveLeftSegment1(context);
		wL2 = new WaveLeftSegment2(context);
		waveLeftParts = new IGestureSegment[] { wL1, wL2 };
		sR1 = new SwipeRightSegment1(context);
		sR2 = new SwipeRightSegment2(context);
		sR3 = new SwipeRightSegment3(context);
		swipeRightParts = new IGestureSegment[] { sR1, sR2, sR3 };
		sL1 = new SwipeLeftSegment1(context);
		sL2 = new SwipeLeftSegment2(context);
		sL3 = new SwipeLeftSegment3(context);
		swipeLeftParts = new IGestureSegment[] { sL1, sL2, sL3 };
		detector = new GestureDetector();
		detector.addGesture(EGestureType.WAVERIGHT, waveRightParts);
		detector.addGesture(EGestureType.WAVELEFT, waveLeftParts);
		detector.addGesture(EGestureType.RIGHTSWIPE, swipeRightParts);
		detector.addGesture(EGestureType.LEFTSWIPE, swipeLeftParts);
		// StartCheck
		startCheck = new StartCheck(context);
		// leftClick
		leftClick = new LeftClick(context);
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
		 * Method to start and stop gestures of recognition
		 */
		if (startCheck.checkGesture(userId).equals(EGestureResult.SUCCEED)) {
			detector.updateAllGestures(userId);
		}
		if (leftClick.checkGesture(userId).equals(EGestureResult.SUCCEED)) {
			System.out.println("Left Click!");
		}

	}

	public void setMovement(Integer type) {
		this.movement = type;
		notifyObserversPoseCheck();
	}

	@Override
	public void notifyObserversPoseCheck(Integer i) {
		for (Observer k : listObservers)
			k.update(i);
	}
}