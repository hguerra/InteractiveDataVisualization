package br.com.inpe.kinect.model.posture;

import SimpleOpenNI.SimpleOpenNI;
import br.com.inpe.kinect.model.gesture.detector.JointID;
import br.com.inpe.kinect.model.gesture.detector.JointRelation;
import br.com.inpe.kinect.model.gesture.detector.SegmentCheck;

public class Start extends SegmentCheck implements IPosture {

	public Start(SimpleOpenNI context) {
		super(context);
	}

	@Override
	public EPostureType updatePosture(int userId) {
		if (check(JointID.LEFT_HAND, JointRelation.ABOVE, JointID.LEFT_ELBOW,
				userId)
				&& check(JointID.LEFT_ELBOW, JointRelation.ABOVE,
						JointID.LEFT_SHOULDER, userId))
			return EPostureType.STATUS_CHANGE;
		return EPostureType.UNDEFINED;
	}

}
