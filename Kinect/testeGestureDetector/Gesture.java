package testeGestureDetector;

import java.util.LinkedList;
import java.util.List;

import br.com.inpe.interactivedatavisualization.kinect.model.Subject;
import br.com.inpe.interactivedatavisualization.kinect.view.Observer;

/**
 * @author Heitor Guerra Carneiro.
 * @version 1.0
 * @since April 2015.
 */
public class Gesture {
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
	
	private EGestureType resultType;
	/**
	 * Initializes a new instance of the
	 */
	public Gesture(IGestureSegment[] gestureParts, EGestureType type) {
		this.gestureParts = gestureParts;
		this.type = type;
	}

	/**
	 * Updates the gesture
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
				stillMoving();
			} else {
				notifyType(this.type);
				this.reset();
			}
		} else if (result == EGestureResult.FAIL || this.frameCount == 50) {
			this.reset();
		} else {
			limit();
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

	public void stillMoving() {
		this.currentGesturePart++;
		this.frameCount = 0;
		this.pausedFrameCount = 10;
		this.paused = true;
	}

	public void limit() {
		this.frameCount++;
		this.pausedFrameCount = 5;
		this.paused = true;
	}
	
	public void notifyType(EGestureType type){
		setResultType(type);
		System.out.println(type);
	}

	public EGestureType getResultType() {
		return resultType;
	}

	public void setResultType(EGestureType resultType) {
		this.resultType = resultType;
	}
}
