package testeGestureDetector;

import java.util.List;

import br.com.inpe.interactivedatavisualization.kinect.model.Subject;
import br.com.inpe.interactivedatavisualization.kinect.view.Observer;
/**
 * @author Heitor Guerra Carneiro.
 * @version 1.0
 * @since April 2015.
 */
public class GestureDetection implements Subject {
	/**
	 * The current gesture part that we are matching against
	 */
	private int currentGesturePart = 0;
	/**
	 * The number of frames to pause for when a pause is initiated
	 */
	private int pausedFrameCount = 10;
	/**
	 * The current frame that we are on
	 */
	private int frameCount = 0;
	/**
	 * Are we paused?
	 */
	private boolean paused = false;
	/**
	 * The type of gesture that this is
	 */
	private EnumGestureType type;
	/**
	 * The parts that make up this gesture
	 */
	private IGestureSegment[] gestureParts;
	/**
	 * List of Observers
	 */
	private List<Observer> listObservers;

	/**
	 * Initializes a new instance of the
	 * 
	 * @param gestureParts
	 * @param type
	 */
	public GestureDetection(IGestureSegment[] gestureParts, EnumGestureType type) {
		this.gestureParts = gestureParts;
		this.type = type;
	}

	/**
	 * Updates the gesture
	 * 
	 * @param data
	 */
	public void updateGesture(SkeletonData data) {
		if (this.paused) {
			if (this.frameCount == this.pausedFrameCount) {
				this.paused = false;
			}

			this.frameCount++;
		}

		EnumGestureResult result = this.gestureParts[this.currentGesturePart]
				.checkGesture(data);

		if (result == EnumGestureResult.SUCCEED) {
			if (this.currentGesturePart + 1 < this.gestureParts.length) {
				this.currentGesturePart++;
				this.frameCount = 0;
				this.pausedFrameCount = 10;
				this.paused = true;
			} else {
				// TO DO
			}
		} else if (result == EnumGestureResult.FAIL || this.frameCount == 50) {
			this.currentGesturePart = 0;
			this.frameCount = 0;
			this.pausedFrameCount = 5;
			this.paused = true;
		} else {
			this.frameCount++;
			this.pausedFrameCount = 5;
			this.paused = true;
		}
	}

	/**
	 * Resets this instance
	 */
	public void reset() {
		this.currentGesturePart = 0;
		this.frameCount = 0;
		this.pausedFrameCount = 5;
		this.paused = true;
	}

	@Override
	public void registerObserver(Observer observer) {
		listObservers.add(observer);
	}

	@Override
	public void notifyObserversPoseCheck() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void notifyObserversGestureRecognised(int movement) {
		for (Observer i : listObservers)
			i.update(movement);
	}
}
