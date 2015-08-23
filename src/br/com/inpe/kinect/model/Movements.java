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
import br.com.inpe.kinect.model.gesture.segments.test.Zoom1;
import br.com.inpe.kinect.model.gesture.segments.test.Zoom2;
import br.com.inpe.kinect.model.gesture.segments.test.Zoom3;
import br.com.inpe.kinect.view.Observer;
import br.com.system.info.SystemInfo;
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
	
	private IGestureSegment wR1;
	private IGestureSegment wR2;
	private IGestureSegment[] waveRightParts;
	private IGestureSegment wL1;
	private IGestureSegment wL2;
	private IGestureSegment[] waveLeftParts;
	private GestureDetector detector;
	// startCheck
	private IGestureSegment startCheck;
	// leftClick
	private IGestureSegment leftClick;
	
	private IGestureSegment z;
	private IGestureSegment z2;
	private IGestureSegment z3;
	private IGestureSegment[] zoomParts;
	
	public Movements(SimpleOpenNI context) {
		this.context = context;
		listObservers = new LinkedList<Observer>();

		wR1 = new WaveRightSegment1(context);
		wR2 = new WaveRightSegment2(context);
		waveRightParts = new IGestureSegment[] { wR1, wR2 };

		wL1 = new WaveLeftSegment1(context);
		wL2 = new WaveLeftSegment2(context);
		waveLeftParts = new IGestureSegment[] { wL1, wL2 };

		z = new Zoom1(context);
		z2 = new Zoom2(context);
		z3 = new Zoom3(context);
		zoomParts = new IGestureSegment[]{z, z2, z3};
		
		detector = new GestureDetector();
		//detector.addGesture(EGestureType.WAVERIGHT, waveRightParts);
		//detector.addGesture(EGestureType.WAVELEFT, waveLeftParts);
		detector.addGesture(EGestureType.ZOOM, zoomParts);
		
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
		 * Method to start and stop gestures recognition
		 */
		/*
		if (startCheck.checkGesture(userId).equals(EGestureResult.SUCCEED)) {
			detector.updateAllGestures(userId);
		}
		if (leftClick.checkGesture(userId).equals(EGestureResult.SUCCEED)) {
			System.out.println("Left Click!");
		}
		*/
		detector.updateAllGestures(userId);
		/**
		 * Testando com as posicoes sepadaras!
		 */
		/*
		if(z.checkGesture(userId).equals(EGestureResult.SUCCEED)){
			System.out.println("pose 1 reconhecida");
		}
		if(z2.checkGesture(userId).equals(EGestureResult.SUCCEED)){
			System.out.println("pose 2 reconhecida");
		}
		if(z3.checkGesture(userId).equals(EGestureResult.SUCCEED)){
			System.out.println("pose 3 reconhecida");
		}*/
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