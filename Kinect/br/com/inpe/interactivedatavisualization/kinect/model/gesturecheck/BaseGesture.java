package br.com.inpe.interactivedatavisualization.kinect.model.gesturecheck;

public abstract class BaseGesture {
	private boolean tracking = false;
	private int currentFrame = 0;

	protected boolean gestureRecognized(int userId) {
		if (tracking == false) {
			// check if initial position
			if (checkStartCondition(userId)) {
				tracking = true;
				currentFrame = 0;
			}
		} else {
			// check if final position
			if (currentFrame == getNumberOfFrames()) {
				tracking = false;
				if (validateFinalPosition(userId)) {
					return true;
				}
			}

			currentFrame++;

			// check if position is still valid
			if (!stillValidPosition(userId)) {
				tracking = false;
			}
		}

		return false;
	}

	protected int getNumberOfFrames() {
		return 10;
	}

	protected boolean checkStartCondition(int userId) {
		return false;
	}

	protected boolean validateFinalPosition(int userId) {
		return false;
	}

	protected boolean stillValidPosition(int userId) {
		return false;
	}

	protected boolean addTraceData(int userId) {
		return false;
	}

}
