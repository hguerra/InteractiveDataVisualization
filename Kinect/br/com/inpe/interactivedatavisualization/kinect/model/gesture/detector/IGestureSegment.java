package br.com.inpe.interactivedatavisualization.kinect.model.gesture.detector;
/**
 * @author Heitor Guerra Carneiro.
 * @version 1.0
 * @since April 2015.
 */
public interface IGestureSegment {
	public EGestureResult checkGesture(int userId);
}
