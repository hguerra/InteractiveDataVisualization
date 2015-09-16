package br.com.inpe.kinect.model.gesture.posture;

import SimpleOpenNI.SimpleOpenNI;
import br.com.inpe.kinect.model.Subject;
import br.com.inpe.kinect.model.gesture.detector.EGestureType;
import br.com.inpe.kinect.model.gesture.detector.SkeletonPoints;
import br.com.inpe.kinect.view.KinectEvents;
import br.com.inpe.kinect.view.Observer;

public class HandOpen extends SkeletonPoints implements Subject {
	public static int MIN_FINGERS_HAND_OPEN = 3;
	public static int MAX_FINGERS_HAND_OPEN = 6;
	private HandRecognize fingers;
	private Observer observer;

	public HandOpen(SimpleOpenNI context, KinectEvents screen, int width,
			int height) {
		super(context);
		registerObserver(screen);
		fingers = new HandRecognize(screen, width, height);
		fingers.setMeltFactor(100);
	}

	public void checkHand(int[] depthMap, int threshold) {
		fingers.setThreshold(threshold);
		fingers.update(depthMap);
		boolean result = (fingers.getNumFingers() > MIN_FINGERS_HAND_OPEN && fingers
				.getNumFingers() < MAX_FINGERS_HAND_OPEN) ? true : false;
		EPostureType type = result ? EPostureType.HAND_OPEN
				: EPostureType.HAND_CLOSED;
		notifyObserverPosture(type);
	}

	@Override
	public void registerObserver(Observer observer) {
		this.observer = observer;

	}

	@Override
	public void notifyObserverPosture(EPostureType type) {
		observer.update(type);
	}

	@Override
	public void notifyObserverGesture(EGestureType type) {
	}

}
