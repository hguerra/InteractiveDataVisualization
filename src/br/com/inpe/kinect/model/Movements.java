package br.com.inpe.kinect.model;

import java.util.LinkedList;
import java.util.List;

import br.com.inpe.kinect.model.gesture.detector.EGestureResult;
import br.com.inpe.kinect.model.gesture.detector.EGestureType;
import br.com.inpe.kinect.model.gesture.detector.GestureDetector;
import br.com.inpe.kinect.model.gesture.detector.IGestureSegment;
import br.com.inpe.kinect.model.gesture.posture.LeftClick;
import br.com.inpe.kinect.model.gesture.posture.MoveMap;
import br.com.inpe.kinect.model.gesture.posture.StartCheck;
import br.com.inpe.kinect.model.gesture.segments.ZoomSegment1;
import br.com.inpe.kinect.model.gesture.segments.ZoomSegment2;
import br.com.inpe.kinect.model.gesture.segments.ZoomSegment3;
import br.com.inpe.kinect.model.gesture.segments.test.ExitSegment;
import br.com.inpe.kinect.model.gesture.segments.test.RestoreSegment;
import br.com.inpe.kinect.model.gesture.segments.test.RotateAntiClockSegments1;
import br.com.inpe.kinect.model.gesture.segments.test.RotateAntiClockSegments2;
import br.com.inpe.kinect.model.gesture.segments.test.RotateAntiClockSegments3;
import br.com.inpe.kinect.model.gesture.segments.test.SwipeLeftSegment1;
import br.com.inpe.kinect.model.gesture.segments.test.SwipeLeftSegment2;
import br.com.inpe.kinect.model.gesture.segments.test.SwipeLeftSegment3;
import br.com.inpe.kinect.model.gesture.segments.test.SwipeRightSegment1;
import br.com.inpe.kinect.model.gesture.segments.test.SwipeRightSegment2;
import br.com.inpe.kinect.model.gesture.segments.test.SwipeRightSegment3;
import br.com.inpe.kinect.model.gesture.segments.test.WaveLeftSegment1;
import br.com.inpe.kinect.model.gesture.segments.test.WaveLeftSegment2;
import br.com.inpe.kinect.model.gesture.segments.test.WaveRightSegment1;
import br.com.inpe.kinect.model.gesture.segments.test.WaveRightSegment2;
import br.com.inpe.kinect.model.gesture.segments.test.ZoomOutSegment1;
import br.com.inpe.kinect.view.Observer;
import br.com.system.info.SystemInfo;
import SimpleOpenNI.SimpleOpenNI;

/**
 * @author Heitor Guerra Carneiro.
 * @version 2.0
 * @since May 2015.
 */
public class Movements implements Subject{
	private SimpleOpenNI context;
	private List<Observer> listObservers;
	/*
	 * Posture
	 */
	// startCheck
	private IGestureSegment startCheck;
	private IGestureSegment leftClick;
	// leftClick
	/*
	 * Gesture
	 */
	//Funcionando
	private IGestureSegment wR1;
	private IGestureSegment wR2;
	private IGestureSegment[] waveRightParts;
	private IGestureSegment wL1;
	private IGestureSegment wL2;
	private IGestureSegment[] waveLeftParts;
	private GestureDetector detector;
	private IGestureSegment zoomSegment1;
	private IGestureSegment zoomSegment2;
	private IGestureSegment zoomSegment3;
	private IGestureSegment[] zoomParts;
	//Teste
	private IGestureSegment exitSegment;
	private IGestureSegment restoreSegment;
	private IGestureSegment rotateAntiClockSegment1;
	private IGestureSegment rotateAntiClockSegment2;
	private IGestureSegment rotateAntiClockSegment3;
	private IGestureSegment zoomOutSegment1;
	private IGestureSegment zoomOutSegment2;
	private IGestureSegment zoomOutSegment3;
	private IGestureSegment[] zoomOutParts;
	/**
	 * Move Map
	 */
	private MoveMap moveMap;
	
	public Movements(SimpleOpenNI context) {
		this.context = context;
		listObservers = new LinkedList<Observer>();
		/**
		 * Posture
		 */
		// StartCheck
		startCheck = new StartCheck(context);
		// leftClick
		leftClick = new LeftClick(context);
		/**
		 * Gesture
		 */
		//Funcionando
		wR1 = new WaveRightSegment1(context);
		wR2 = new WaveRightSegment2(context);
		waveRightParts = new IGestureSegment[] { wR1, wR2 };

		wL1 = new WaveLeftSegment1(context);
		wL2 = new WaveLeftSegment2(context);
		waveLeftParts = new IGestureSegment[] { wL1, wL2 };

		zoomSegment1 = new ZoomSegment1(context);
		zoomSegment2 = new ZoomSegment2(context);
		zoomSegment3 = new ZoomSegment3(context);
		zoomParts = new IGestureSegment[]{zoomSegment1, zoomSegment2, zoomSegment3};
		//Teste
		exitSegment = new ExitSegment(context);
		restoreSegment = new RestoreSegment(context);
		rotateAntiClockSegment1 = new RotateAntiClockSegments1(context);
		rotateAntiClockSegment2 = new RotateAntiClockSegments2(context);
		rotateAntiClockSegment3 = new RotateAntiClockSegments3(context);
		zoomOutSegment1 = new ZoomOutSegment1(context);
		zoomOutSegment2 = new ZoomSegment1(context);
		zoomOutSegment3 = new ZoomSegment3(context);
		zoomOutParts = new IGestureSegment[]{zoomOutSegment1, zoomOutSegment2, zoomOutSegment3};
		
		detector = new GestureDetector();
		//detector.addGesture(EGestureType.WAVERIGHT, waveRightParts, this);
		//detector.addGesture(EGestureType.WAVELEFT, waveLeftParts, this);
		detector.addGesture(EGestureType.ZOOM, zoomParts, this);
		detector.addGesture(EGestureType.ZOOM_OUT, zoomOutParts, this);
		
		moveMap = new MoveMap(context);
	}

	@Override
	public void registerObserver(Observer observer) {
		listObservers.add(observer);
	}

	public void poseCheck(int userId) {
		/**
		 * position test
		 */
		//testSegment(userId, rotateAntiClockSegment1);
		/**
		 * Teste Gesto Completo	
		 */
		detector.updateAllGestures(userId);
		/*
		 * Method to start and stop gestures recognition
		 */
		/*
		if (startCheck.checkGesture(userId).equals(EGestureResult.SUCCEED)) {
			detector.updateAllGestures(userId);
		}
		
		*/
		
		/**
		 * Move map
		 */
		if (leftClick.checkGesture(userId).equals(EGestureResult.SUCCEED)) {
			System.out.println("leftClick");
			moveMap.move(userId);
		}
		
		
	}
	
	public String testSegment(int userId, IGestureSegment segment){
		String result = "";
		if(segment.checkGesture(userId).equals(EGestureResult.SUCCEED)){
			result  = "Segmento recognized";
			System.out.println(result);
		}
		return result;
	}


	@Override
	public void notifyObserverGesture(EGestureType type) {
		for(Observer i: listObservers){
			i.update(type);
		}
	}

}