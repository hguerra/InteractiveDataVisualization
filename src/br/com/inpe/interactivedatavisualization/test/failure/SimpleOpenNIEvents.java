package br.com.inpe.interactivedatavisualization.test.failure;

import processing.core.PImage;
import processing.core.PVector;
import SimpleOpenNI.SimpleOpenNI;

public class SimpleOpenNIEvents extends Processing {

	private SimpleOpenNI kinect;
	private Model model;

	public void setup() {
		// Size of window application
		size(640, 480);

		// Initialize the SimpleOpenNi variable.
		kinect = new SimpleOpenNI(this);

		// Initialize and start depth sensor's data
		kinect.enableDepth();

		// Initialize and start receiving User data
		kinect.enableUser();

		// Flips the sensor's data horizontally
		// Enable mirroring
		kinect.setMirror(true);

	}

	public void draw() {
		// Update the camera
		kinect.update();

		PImage depth = kinect.depthImage();
		if (depth != null) {
			image(depth, 0, 0);
		}
		int[] userList = kinect.getUsers();
		for (int i = 0; i < userList.length; i++) {
			if (kinect.isTrackingSkeleton(userList[i])) {
				// ----------------------------
				// Color of skeleton stroke
				strokeWeight(2);
				stroke(0, 255, 255);
				// Skeleton
				drawSkeleton(userList[i]);
			}
		}
	}

	public void drawSkeleton(int userId) {
		stroke(0);
		strokeWeight(5);
		kinect.drawLimb(userId, SimpleOpenNI.SKEL_HEAD, SimpleOpenNI.SKEL_NECK);
		kinect.drawLimb(userId, SimpleOpenNI.SKEL_NECK,
				SimpleOpenNI.SKEL_LEFT_SHOULDER);
		kinect.drawLimb(userId, SimpleOpenNI.SKEL_LEFT_SHOULDER,
				SimpleOpenNI.SKEL_LEFT_ELBOW);
		kinect.drawLimb(userId, SimpleOpenNI.SKEL_LEFT_ELBOW,
				SimpleOpenNI.SKEL_LEFT_HAND);
		kinect.drawLimb(userId, SimpleOpenNI.SKEL_NECK,
				SimpleOpenNI.SKEL_RIGHT_SHOULDER);
		kinect.drawLimb(userId, SimpleOpenNI.SKEL_RIGHT_SHOULDER,
				SimpleOpenNI.SKEL_RIGHT_ELBOW);
		kinect.drawLimb(userId, SimpleOpenNI.SKEL_RIGHT_ELBOW,
				SimpleOpenNI.SKEL_RIGHT_HAND);
		kinect.drawLimb(userId, SimpleOpenNI.SKEL_LEFT_SHOULDER,
				SimpleOpenNI.SKEL_TORSO);
		kinect.drawLimb(userId, SimpleOpenNI.SKEL_RIGHT_SHOULDER,
				SimpleOpenNI.SKEL_TORSO);
		kinect.drawLimb(userId, SimpleOpenNI.SKEL_TORSO,
				SimpleOpenNI.SKEL_LEFT_HIP);
		kinect.drawLimb(userId, SimpleOpenNI.SKEL_LEFT_HIP,
				SimpleOpenNI.SKEL_LEFT_KNEE);
		kinect.drawLimb(userId, SimpleOpenNI.SKEL_LEFT_KNEE,
				SimpleOpenNI.SKEL_LEFT_FOOT);
		kinect.drawLimb(userId, SimpleOpenNI.SKEL_TORSO,
				SimpleOpenNI.SKEL_RIGHT_HIP);
		kinect.drawLimb(userId, SimpleOpenNI.SKEL_RIGHT_HIP,
				SimpleOpenNI.SKEL_RIGHT_KNEE);
		kinect.drawLimb(userId, SimpleOpenNI.SKEL_RIGHT_KNEE,
				SimpleOpenNI.SKEL_RIGHT_FOOT);
		kinect.drawLimb(userId, SimpleOpenNI.SKEL_RIGHT_HIP,
				SimpleOpenNI.SKEL_LEFT_HIP);
		noStroke();
		fill(255, 0, 0);
		drawJoint(userId, SimpleOpenNI.SKEL_HEAD);
		drawJoint(userId, SimpleOpenNI.SKEL_NECK);
		drawJoint(userId, SimpleOpenNI.SKEL_LEFT_SHOULDER);
		drawJoint(userId, SimpleOpenNI.SKEL_LEFT_ELBOW);
		drawJoint(userId, SimpleOpenNI.SKEL_NECK);
		drawJoint(userId, SimpleOpenNI.SKEL_RIGHT_SHOULDER);
		drawJoint(userId, SimpleOpenNI.SKEL_RIGHT_ELBOW);
		drawJoint(userId, SimpleOpenNI.SKEL_TORSO);
		drawJoint(userId, SimpleOpenNI.SKEL_LEFT_HIP);
		drawJoint(userId, SimpleOpenNI.SKEL_LEFT_KNEE);
		drawJoint(userId, SimpleOpenNI.SKEL_RIGHT_HIP);
		drawJoint(userId, SimpleOpenNI.SKEL_LEFT_FOOT);
		drawJoint(userId, SimpleOpenNI.SKEL_RIGHT_KNEE);
		drawJoint(userId, SimpleOpenNI.SKEL_LEFT_HIP);
		drawJoint(userId, SimpleOpenNI.SKEL_RIGHT_FOOT);
		drawJoint(userId, SimpleOpenNI.SKEL_RIGHT_HAND);
		drawJoint(userId, SimpleOpenNI.SKEL_LEFT_HAND);
	}

	public void drawJoint(int userId, int jointID) {

		PVector joint = new PVector();

		float confidence = kinect.getJointPositionSkeleton(userId, jointID,
				joint);
		if (confidence < 0.5) {
			return;
		}
		PVector convertedJoint = new PVector();
		kinect.convertRealWorldToProjective(joint, convertedJoint);
		ellipse(convertedJoint.x, convertedJoint.y, 5, 5);
	}

	// user-tracking callbacks!
	public void onNewUser(SimpleOpenNI curContext, int userId) {
		println("onNewUser - userId: " + userId);
		println("\tstart tracking skeleton");

		curContext.startTrackingSkeleton(userId);
	}

	public void onLostUser(SimpleOpenNI curContext, int userId) {
		println("onLostUser - userId: " + userId);
	}

	public void onVisibleUser(SimpleOpenNI curContext, int userId) {
		// println("onVisibleUser - userId: " + userId);
	}
}// END Class
