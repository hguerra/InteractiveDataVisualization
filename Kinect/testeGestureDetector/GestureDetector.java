package testeGestureDetector;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Heitor Guerra Carneiro.
 * @version 1.0
 * @since April 2015.
 */
public class GestureDetector {
	private List<Gesture> gestures;
	
	public GestureDetector(){
		gestures = new LinkedList<Gesture>();
	}
	
	public void updateAllGestures(int userId){
		for(Gesture gesture: this.gestures){
			gesture.updateGesture(userId);
		}
	}
	
	public void addGesture(EGestureType type, IGestureSegment[] gestureParts){
		Gesture gesture = new Gesture(gestureParts, type);
		this.gestures.add(gesture);
	}
	
	public void removeGesture(Gesture gesture){
		gestures.remove(gesture);
	}

	public void resetAllGestures(){
		for(Gesture g: this.gestures){
			g.reset();
		}
	}
	
}
