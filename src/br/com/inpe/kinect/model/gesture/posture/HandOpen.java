package br.com.inpe.kinect.model.gesture.posture;
import SimpleOpenNI.SimpleOpenNI;
import br.com.inpe.kinect.model.Subject;
import br.com.inpe.kinect.model.gesture.detector.EGestureType;
import br.com.inpe.kinect.model.gesture.detector.Position;
import br.com.inpe.kinect.view.KinectEvents;
import br.com.inpe.kinect.view.Observer;

public class HandOpen extends Position implements Subject{
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
			boolean result = (fingers.getNumFingers() > 3 && fingers
					.getNumFingers() < 7) ? true : false;
			EGestureType type = result ? EGestureType.HAND_OPEN : EGestureType.HAND_CLOSED;
			notifyObserverGesture(type);
	}
	@Override
	public void registerObserver(Observer observer) {
		this.observer = observer;
		
	}

	@Override
	public void notifyObserverGesture(EGestureType type) {
		observer.update(type);
	}

}
