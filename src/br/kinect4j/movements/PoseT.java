package br.kinect4j.movements;

import com.primesense.nite.JointType;
import com.primesense.nite.UserData;

import br.com.kinect4j.engine.core.GestureResult;
import br.com.kinect4j.engine.core.Pose;
import br.com.kinect4j.engine.core.Skeleton;
import br.com.kinect4j.engine.core.Skeleton.Operator;

public class PoseT implements Pose {
	private Skeleton skeleton;

	public PoseT(Skeleton skeleton) {
		this.skeleton = skeleton;
	}

	@Override
	public GestureResult checkPose(UserData user) {
		/**
		 * Right hand position
		 */
		// compareZ right hand equals neck
		//boolean rightHandZ = skeleton.comparePositionZ(user, JointType.RIGHT_HAND, Operator.EQUALS, JointType.NECK);
		// compareY right hand equals neck
		boolean rightHandY = skeleton.comparePositionY(user, JointType.RIGHT_HAND, Operator.EQUALS, JointType.NECK);
		// compareX right hand greater than than right elbow
		boolean rightHandX = skeleton.comparePositionX(user, JointType.RIGHT_HAND, Operator.GREATER_THAN,
				JointType.RIGHT_ELBOW);

		/**
		 * Right and Left elbow
		 */

		// compareY right elbow equals neck
		//boolean rightElbowY = skeleton.comparePositionY(user, JointType.RIGHT_ELBOW, Operator.EQUALS, JointType.NECK);

		// compareY left elbow equals neck
		//boolean leftElbowY = skeleton.comparePositionY(user, JointType.LEFT_ELBOW, Operator.EQUALS, JointType.NECK);

		/**
		 * Left hand postion
		 */
		// compareZ left hand equals neck
		//boolean leftHandZ = skeleton.comparePositionZ(user, JointType.LEFT_HAND, Operator.EQUALS, JointType.NECK);

		// compareY left hand equals neck
		boolean leftHandY = skeleton.comparePositionY(user, JointType.LEFT_HAND, Operator.EQUALS, JointType.NECK);

		// compareX left hand less than right elbow
		boolean leftHandX = skeleton.comparePositionX(user, JointType.LEFT_HAND, Operator.LESS_THAN,
				JointType.LEFT_HAND);

		boolean result = (rightHandY && rightHandX && leftHandY && leftHandX);
		
		
		return result ? GestureResult.SUCCEED : GestureResult.FAIL;
	}

}
