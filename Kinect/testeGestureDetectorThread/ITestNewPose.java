package testeGestureDetectorThread;

import SimpleOpenNI.SimpleOpenNI;
import br.com.inpe.interactivedatavisualization.kinect.model.gesturecheck.GestureRecognize;

public interface ITestNewPose {
	public void constructorMethod(SimpleOpenNI context);
	public boolean segmentCheck(GestureRecognize pose);
	public boolean recognizeSegment();
}
