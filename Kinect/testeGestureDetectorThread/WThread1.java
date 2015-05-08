package testeGestureDetectorThread;

import SimpleOpenNI.SimpleOpenNI;
import br.com.inpe.interactivedatavisualization.kinect.model.Movements;
import br.com.inpe.interactivedatavisualization.kinect.model.Subject;
import br.com.inpe.interactivedatavisualization.kinect.model.gesturecheck.GestureRecognize;
import br.com.inpe.interactivedatavisualization.kinect.model.gesturecheck.PoseRecognize;

public class WThread1 implements ITestNewPose, Runnable {
	private SimpleOpenNI context;
	private GestureRecognize poseOne;
	private Movements s;
	private int userId;

	public WThread1(SimpleOpenNI context, int userId, Movements s) {
		constructorMethod(context);
		this.userId = userId;
		this.s = s;
	}

	@Override
	public void constructorMethod(SimpleOpenNI context) {
		this.context = context;
		poseOne = new GestureRecognize(context);

	}

	@Override
	public boolean segmentCheck(GestureRecognize pose) {
		return pose.check(userId);
	}

	@Override
	public boolean recognizeSegment() {
		// 1
		poseOne.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRecognize.ABOVE,
				SimpleOpenNI.SKEL_HEAD);
		poseOne.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRecognize.ABOVE,
				SimpleOpenNI.SKEL_RIGHT_SHOULDER);
		poseOne.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRecognize.ABOVE,
				SimpleOpenNI.SKEL_RIGHT_ELBOW);
		poseOne.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRecognize.LEFT_OF,
				SimpleOpenNI.SKEL_RIGHT_ELBOW);
		return segmentCheck(poseOne);
	}

	@Override
	public void run() {
		if (recognizeSegment()) {
			s.setC(true);
			
		}
	}

}
