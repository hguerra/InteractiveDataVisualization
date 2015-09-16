package br.com.inpe.kinect.model.gesture.posture;

import processing.core.PVector;
import SimpleOpenNI.SimpleOpenNI;
import br.com.inpe.app.RegisterVirtualGlobe;
import br.com.inpe.kinect.model.gesture.detector.JointID;
import br.com.inpe.kinect.model.gesture.detector.SkeletonPoints;

public class MoveMap extends SkeletonPoints {
	private final static double PERCENTAGE = 0.3;
	private final static int ARM_LENGTH = 400; 
	private HandPosition hand;
	public MoveMap(SimpleOpenNI context) {
		super(context);
		hand = new HandPosition();
	}

/*
	public void move(int userId) {
		addHandPoints(userId);
		if (armLength(userId) > ARM_LENGTH) {
			System.out.println("Ta no if");
			RegisterVirtualGlobe.getController().pan(
					hand.getRightDeltaY() * PERCENTAGE,
					hand.getRightDeltaX() * PERCENTAGE);
		}
	}
	*/

	public void move(int userId){
		addHandPoints(userId);
		RegisterVirtualGlobe.getFrameController().pan(
				hand.getRightDeltaY() * PERCENTAGE,
				hand.getRightDeltaX() * PERCENTAGE);
	}

	public void addHandPoints(int userId) {
		PVector rightPoints = vectorJoint(userId, JointID.RIGHT_HAND);
		//PVector leftPoints = vectorJoint(userId, JointID.LEFT_HAND);
		hand.addRightHandPoint(rightPoints);
		//hand.addLeftHandPoint(leftPoints);
	}

	/**
	 * calculate armLength (JointID.RIGHT_HAND, JointID.RIGHT_SHOULDER)
	 */
	public float armLength(int userId) {
		return jointsLength(userId, JointID.RIGHT_SHOULDER, JointID.RIGHT_HAND);
	}

	public float jointsLength(int userId, int joint1, int joint2) {
		PVector p1 = vectorJoint(userId, joint1);
		PVector p2 = vectorJoint(userId, joint2);
		return euclidianDistance(p1, p2);
	}

	public float euclidianDistance(PVector p1, PVector p2) {
		float distance = (float) Math
				.sqrt((p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y)
						* (p1.y - p2.y) + (p1.z - p2.z) * (p1.z - p2.z));
		return distance;
	}
}
