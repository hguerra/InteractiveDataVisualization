package br.inpe.triangle.kinect.controller;

import static br.com.kinect4j.engine.core.Skeleton.Operator.GREATER_THAN;
import static br.com.kinect4j.engine.core.Skeleton.Operator.LESS_THAN;

import com.primesense.nite.JointType;
import com.primesense.nite.UserData;

import br.com.kinect4j.engine.core.Skeleton;
import br.com.kinect4j.engine.core.SkeletonCoordinates;
import br.com.kinect4j.engine.defaultcore.DefaultSkeletonCoordinates;
import br.inpe.triangle.wwj.layer.WorldWindController;

/**
 * @author Heitor
 * @since 01/06/2016
 */
public class PanController {
	private static final double FACTOR = 0.3;
	private static final int ARM_LENGHT = 600;
	private Skeleton skeleton;
	private SkeletonCoordinates coords;
	private WorldWindController controller;

	public PanController(Skeleton skeleton, WorldWindController controller) {
		this.skeleton = skeleton;
		this.coords = new DefaultSkeletonCoordinates(skeleton);
		this.controller = controller;
	}

	public void moveMap(UserData user) {
		// add
		coords.addLeftHandPoint(user);
		coords.addRightHandPoint(user);

		// do the magic now!
		// right hand below shoulder height but above hip height
		boolean rightHandBetweenHipShoulder = (skeleton.comparePositionY(user, JointType.RIGHT_HAND, LESS_THAN,
				JointType.HEAD))
				&& skeleton.comparePositionY(user, JointType.RIGHT_HAND, GREATER_THAN, JointType.TORSO);

		boolean leftHandBelow = skeleton.comparePositionY(user, JointType.LEFT_HAND, LESS_THAN, JointType.TORSO);

		if (!leftHandBelow && !rightHandBetweenHipShoulder)
			return;

		double dist = coords.getDistanceRightHandtoShoulder(user);

		if (dist < ARM_LENGHT)
			return;

		double moveY = coords.getRightHandDeltaY(user) * FACTOR;
		double moveX = coords.getRightHandDeltaX(user) * FACTOR;

		controller.pan(moveY, moveX);
	}
}
