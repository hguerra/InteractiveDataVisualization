package br.com.inpe.kinect.model;

import processing.core.PVector;
import SimpleOpenNI.SimpleOpenNI;
import br.com.inpe.app.RegisterVirtualGlobe;
import br.com.inpe.kinect.model.gesture.detector.JointID;
import br.com.inpe.kinect.model.gesture.detector.SkeletonPoints;
import br.com.inpe.kinect.model.posture.HandPosition;
import br.com.inpe.kinect.model.posture.HandPositionOriginal;

public class MoveMap extends SkeletonPoints {
	private final static double PERCENTAGE = 0.3;
	private final static int ARM_LENGTH = 400;
	// private HandPosition hand;
	private HandPositionOriginal hand;
	private boolean rightHandPan = true;

	public MoveMap(SimpleOpenNI context) {
		super(context);
		// hand = new HandPosition();
		hand = new HandPositionOriginal();
	}

	// public void move(int userId){
	// addHandPoints(userId);
	// RegisterVirtualGlobe.getFrameController().pan(
	// hand.getRightDeltaY() * PERCENTAGE,
	// hand.getRightDeltaX() * PERCENTAGE);
	// }

	public void move(int userId)
	// using HandPositionOriginal
	{
		addHandPoints(userId);

		System.out.println("right:" + hand.getRightHandCoords());
		System.out.println("left:" + hand.getLeftHandCoords());
		/**
		 * method update SkeletonKinectHandler
		 */
		float currHandsDist = euclidianDistance(hand.getLeftHandCoords(),
				hand.getRightHandCoords());
		float prevHandsDist = euclidianDistance(
				hand.getLeftHandPreviousCoords(),
				hand.getRightHandPreviousCoords());
		float distLeftHandtoShoulder = euclidianDistance(
				vectorJoint(userId, JointID.LEFT_SHOULDER),
				hand.getLeftHandCoords());
		float distRightHandtoShoulder = euclidianDistance(
				vectorJoint(userId, JointID.RIGHT_SHOULDER),
				hand.getRightHandCoords());
	
		/**
		 * some code: detection if the user wants to swipe through time
		 */
		float rightDeltaX = hand.getRightHandPreviousCoords().x
				- hand.getRightHandCoords().x;
		float rightDeltaY = hand.getRightHandPreviousCoords().y
				- hand.getRightHandCoords().y;

		float leftDeltaX = hand.getLeftHandPreviousCoords().x
				- hand.getLeftHandCoords().x;
		float leftDeltaY = hand.getLeftHandPreviousCoords().y
				- hand.getLeftHandCoords().y;
		/**
		 * some code: time swipe detection
		 */
		
		if ((rightHandPan ? (distRightHandtoShoulder > ARM_LENGTH && distLeftHandtoShoulder > ARM_LENGTH) //
				: (distRightHandtoShoulder > ARM_LENGTH && distLeftHandtoShoulder > ARM_LENGTH))) {
			if (Math.abs(prevHandsDist - currHandsDist) > 30) {
				if (prevHandsDist < currHandsDist) {
					// this.controller.zoom(0.97);
					System.out.println("zomm:" + 0.97);
				} else if (prevHandsDist > currHandsDist) {
					// this.controller.zoom(1.03);
					System.out.println("zomm:" + 1.03);
				}
			}
			return;
		} else if (distRightHandtoShoulder > ARM_LENGTH) {

			// this.controller.pan(rightDeltaY * 0.3, rightDeltaX * 0.3);
			System.out.println("pan:" + rightDeltaY * 0.3 + "," + rightDeltaX
					* 0.3);
			rightHandPan = true;
		} else if (distLeftHandtoShoulder > ARM_LENGTH) {
			
			// this.controller.pan(leftDeltaY * 0.3, leftDeltaX * 0.3);
			System.out.println("pan:" + leftDeltaY * 0.3 + "," + leftDeltaX
					* 0.3);
			rightHandPan = false;
		}

	}

	public void addHandPoints(int userId) {
		PVector rightPoints = vectorJoint(userId, JointID.RIGHT_HAND);
		PVector leftPoints = vectorJoint(userId, JointID.LEFT_HAND);
		hand.addRightHandPoint(rightPoints);
		hand.addLeftHandPoint(leftPoints);
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
