package testeGestureDetector;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Heitor Guerra Carneiro.
 * @version 1.0
 * @since April 2015.
 */
public class GestureControler {
	private List<GestureDetection> gestures;
	
	public GestureControler(){
		gestures = new LinkedList<GestureDetection>();
	}
	
	public void updateAllGestures(int userId){
		for(GestureDetection gesture: this.gestures){
			gesture.updateGesture(userId);
		}
	}
	
	public void addGesture(EGestureType type, IGestureSegment[] gestureParts){
		GestureDetection gesture = new GestureDetection(gestureParts, type);
		this.gestures.add(gesture);
	}
	
	public void removeGesture(GestureDetection gesture){
		gestures.remove(gesture);
	}
	
	public void clearGestures(){
		for(GestureDetection g: this.gestures){
			g.reset();
		}
	}
}
