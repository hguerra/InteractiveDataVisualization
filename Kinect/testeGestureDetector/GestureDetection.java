package testeGestureDetector;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Heitor Guerra Carneiro.
 * @version 1.0
 * @since April 2015.
 */
public class GestureDetection {
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
	private EGestureType type;
	/**
	 * The parts that make up this gesture
	 */
	private IGestureSegment[] gestureParts;
	/**
	 * List of Gesture Recognized
	 */
	private List<EGestureType> gestureRecognised;

	/**
	 * Initializes a new instance of the
	 * 
	 * @param gestureParts
	 * @param type
	 */
	public GestureDetection(IGestureSegment[] gestureParts, EGestureType type) {
		this.gestureParts = gestureParts;
		this.type = type;
		gestureRecognised = new LinkedList<EGestureType>();
	}

	/**
	 * Updates the gesture
	 * 
	 * @param data
	 */
	public void updateGesture(int userId) {
		if (this.paused) {
			if (this.frameCount == this.pausedFrameCount) {
				this.paused = false;
			}

			this.frameCount++;
		}

		EGestureResult result = this.gestureParts[this.currentGesturePart]
				.checkGesture(userId);

		if (result == EGestureResult.SUCCEED) {
			if (this.currentGesturePart + 1 < this.gestureParts.length) {
				this.currentGesturePart++;
				this.frameCount = 0;
				this.pausedFrameCount = 10;
				this.paused = true;
			} else {
				if (!this.gestureRecognised.isEmpty()) {
					this.gestureRecognised.add(this.type);
					this.reset();
				}
			}
		} else if (result == EGestureResult.FAIL || this.frameCount == 50) {
			this.reset();
		} else {
			this.frameCount++;
			this.pausedFrameCount = 5;
			this.paused = true;
		}
	}

	/**
	 * Reset this instance
	 */
	public void reset() {
		this.currentGesturePart = 0;
		this.frameCount = 0;
		this.pausedFrameCount = 5;
		this.paused = true;
		System.gc();
	}

}
