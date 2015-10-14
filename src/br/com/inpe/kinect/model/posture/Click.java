package br.com.inpe.kinect.model.posture;

import SimpleOpenNI.SimpleOpenNI;
import br.com.inpe.kinect.model.gesture.detector.JointID;
import br.com.inpe.kinect.model.gesture.detector.JointRelation;
import br.com.inpe.kinect.model.gesture.detector.SegmentCheck;

public class Click extends SegmentCheck implements IPosture {

	public Click(SimpleOpenNI context) {
		super(context);
	}

	@Override
	public EPostureType updatePosture(int userId) {
		if (check(JointID.RIGHT_HAND, JointRelation.CLOSER_OF,
				JointID.RIGHT_ELBOW, userId)
				&& check(JointID.RIGHT_ELBOW, JointRelation.CLOSER_OF,
						JointID.RIGHT_SHOULDER, userId)
				&& check(JointID.RIGHT_ELBOW, JointRelation.RIGHT_OF,
						JointID.CENTER_SHOULDER, userId)
				&& !check(JointID.RIGHT_ELBOW, JointRelation.RIGHT_OF,
						JointID.RIGHT_SHOULDER, userId))// end method
			return EPostureType.CLICK;
		return EPostureType.UNDEFINED;
	}

}
