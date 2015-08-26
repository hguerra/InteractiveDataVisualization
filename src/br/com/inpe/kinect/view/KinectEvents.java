package br.com.inpe.kinect.view;

import br.com.inpe.kinect.controller.Bridge;
import br.com.inpe.kinect.controller.Data;
import br.com.inpe.kinect.controller.DataDown;
import br.com.inpe.kinect.controller.DataUp;
import br.com.inpe.kinect.controller.StartTracking;
import br.com.inpe.kinect.controller.Time;
import br.com.inpe.kinect.controller.TimeDown;
import br.com.inpe.kinect.controller.TimeUp;
import br.com.inpe.kinect.controller.Zoom;
import br.com.inpe.kinect.controller.ZoomIn;
import br.com.inpe.kinect.controller.ZoomOut;
import br.com.inpe.kinect.model.gesture.detector.EGestureType;
import br.com.system.info.SystemInfo;
import processing.core.PImage;
import processing.core.PVector;
import SimpleOpenNI.SimpleOpenNI;

/**
 * @author Heitor Guerra Carneiro.
 * @version 1.0
 * @since March 2015.
 */
public class KinectEvents extends Processing implements Observer {
	private final static int WIDTH = 640;
	private final static int HEIGHT = 480;
	private SimpleOpenNI kinect;
	private Bridge bridge;
	private Zoom zoomIn;
	private Zoom zoomOut;
	private Data dataUp;
	private Data dataDown;
	private Time timeUp;
	private Time timeDown;

	public void setup() {
		// Size of window application
		size(WIDTH, HEIGHT);

		// Initialize the SimpleOpenNi variable.
		kinect = new SimpleOpenNI(this);

		// Initialize and start depth sensor's data
		kinect.enableDepth();

		// Initialize and start receiving User data
		kinect.enableUser();

		// Flips the sensor's data horizontally
		// Enable mirroring
		kinect.setMirror(true);

		/*
		 * The Processing applications has two required methods: setup() and
		 * draw ()
		 * 
		 * load the model methods
		 * 
		 * Associacao view -> model
		 * 
		 * 
		 * (controller -> model) (view -> controller)
		 */
		bridge = new StartTracking(kinect);

		/*
		 * registerObserver
		 * 
		 * Associacao model -> view
		 * 
		 * (controller -> view) (model -> controller)
		 */
		bridge.initRegisterObserver(this);

		/*
		 * setZoom
		 * 
		 * Associacao view -> Controller
		 */
		zoomIn = new ZoomIn();
		zoomOut = new ZoomOut();
		dataUp = new DataUp();
		dataDown = new DataDown();
		timeUp = new TimeUp();
		timeDown = new TimeDown();

	}

	public void draw() {
		// Update the camera
		kinect.update();

		PImage depth = kinect.depthImage();
		if (depth != null) {
			image(depth, 0, 0);
		}
		int[] userList = kinect.getUsers();
		// i++
		for (int i = 0; i < userList.length; i++) {
			if (kinect.isTrackingSkeleton(userList[i])) {
				// Skeleton
				drawSkeleton(userList[i]);
				/*
				 * Analysis of body position, model method
				 */
				bridge.initPoseCheck(userList[i]);

				/*
				 * Analysis Update method
				 */
				kinectDebug(false);

			}
		}
	}

	@Override
	public void update(EGestureType type) {
		switch (type) {
		case ZOOM: {
			zoomIn.setZoom();
			break;
		}
		case SWIPE_LEFT: {
			System.out.println("OTHER");
			break;
		}
		case SWIPE_RIGHT: {
			System.out.println("OTHER");
			break;
		}
		case SWIPE_DOWN: {
			System.out.println("OTHER");
			break;
		}
		case SWIPE_UP: {
			System.out.println("OTHER");
			break;
		}

		}

	}

	public void drawSkeleton(int userId) {
		// Color of skeleton stroke
		stroke(0, 0, 255);
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
		smooth();
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
		// # 22 GitHub0
		if (userId > 1)
			curContext.stopTrackingSkeleton(userId);
	}

	public void onVisibleUser(SimpleOpenNI curContext, int userId) {
		// required method
		// println("onVisibleUser - userId: " + userId);
	}

	public void kinectDebug(boolean memoryInfo) {
		if (memoryInfo) {
			SystemInfo info = new SystemInfo();
			message(5, 20, 15, info.MemInfo());
		}
	}

	public void message(float widthScreen, float heightScreen, int sizeFont,
			String message) {
		textSize(sizeFont);
		fill(255);
		text(message, widthScreen, heightScreen);
	}

}// END Class