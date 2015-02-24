package br.com.inpe.interactivedatavisualization.kinect;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import processing.core.PApplet;
import processing.core.PVector;
import SimpleOpenNI.SimpleOpenNI;

/**
 * This class create all SimpleOpenNi and Processing Events.
 * 
 * @author Heitor Guerra Carneiro.
 * @version 1.0
 * @since February 2015.
 * 
 */
public class KinectEvent extends VisualObjects {
	// Constant
	private static final Integer VARIATION = 20;
	// Mouse
	private Integer xx = 0;
	private Integer yy = 0;
	private Integer stageWidth;
	private Integer stageHeight;
	private Integer prW, prH;
	private Integer stageScale = 5 / 3;
	// Functions
	private Integer buttonPressed = 1;
	private Boolean turnJoystick = false;
	private Boolean turnZoom = false;
	private Integer turnMotionPressed = 1;
	private Boolean turnMotion = false;
	// Joints
	private double headY;
	private double headX;
	private double rightHandX;
	private double rightHandY;
	private double leftHandX;
	private double leftHandY;
	private double centerMassX;
	private double centerMassY;
	private double rightElbowX;
	private double rightElbowY;
	private double leftElbowX;
	private double leftElbowY;
	private double rightShoulderX;
	private double rightShoulderY;
	private double leftShoulderX;
	private double leftShoulderY;
	// Instance
	private SimpleOpenNI kinect;
	private Robot robot;
	private SkeletonPoser pose;

	public KinectEvent() {

	}

	public void simpleOpenNiSetup() {
		kinect = new SimpleOpenNI(this);
		if (kinect.isInit() == false) {
			println("Sorry! The system cannot initialize SimpleOpenNi, check if the kinect is connected or your drivers were installed!");
			exit();
		}
		// enable depthMap generation
		kinect.enableDepth();
		// enable skeleton generation for all joints
		kinect.enableUser();
		// Size automatic
		size(kinect.depthWidth(), kinect.depthHeight());
		// Skeleton Poser
		pose = new SkeletonPoser(kinect);
		addPose();
		textFont(createFont("Arial", 36));
		try {
			robot = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		screenSize();
	}

	public void simpleOpenNiDraw() {
		// black background
		background(0);
		// update the cam
		kinect.update();
		// static objects
		staticObjects();
		int[] userList = kinect.getUsers();
		for (int i = 0; i < userList.length; i++) {
			if (kinect.isTrackingSkeleton(userList[i])) {
				// ----------------------------
				// Color of skeleton stroke
				strokeWeight(2);
				stroke(0, 255, 255);
				drawSkeleton(userList[i]);
				drawHands(userList[i]);
				drawHead(userList[i]);
				drawCenterMass(userList[i]);
				drawElbow(userList[i]);
				drawShoulder(userList[i]);
				updateObjects();
				poseCheck(userList[i]);
			}
		}
	}

	public void staticObjects() {

	}

	public void updateObjects() {
		changeControl(50);
		motionControl(turnMotion);
		optionButton(40, "LEFT", turnMotion);
		zoom(30, "RIGHT", turnZoom);
		joystickControl("RIGHT", 30, turnJoystick);
	}

	/*
	 * ------------------Control--------------------------
	 */
	public void keysControl(int option) {
		switch (option) {
		case 0:
			// right
			for (int cont = 0; cont < 200; cont++)
				robot.keyPress(KeyEvent.VK_RIGHT);
			robot.keyRelease(KeyEvent.VK_RIGHT);
			break;
		case 1:
			// up
			for (int cont = 0; cont < 200; cont++)
				robot.keyPress(KeyEvent.VK_UP);
			robot.keyRelease(KeyEvent.VK_UP);
			break;
		case 2:
			// down
			for (int cont = 0; cont < 200; cont++)
				robot.keyPress(KeyEvent.VK_DOWN);
			robot.keyRelease(KeyEvent.VK_DOWN);
			break;
		case 3:
			// left
			for (int cont = 0; cont < 200; cont++)
				robot.keyPress(KeyEvent.VK_LEFT);
			robot.keyRelease(KeyEvent.VK_LEFT);
			break;
		case 4:
			robot.mouseWheel(1);
			break;
		case 5:
			robot.mouseWheel(-1);
			break;
		case 6:
			robot.mouseWheel(0);
		}
	}

	public void motionControl(boolean can_move) {
		if (!can_move) {
			// right
			if (leftHandY < centerMassY + VARIATION
					&& leftHandY > centerMassY - VARIATION
					&& leftHandX < centerMassX + VARIATION
					&& leftHandX > centerMassX - VARIATION
					&& rightElbowX > rightShoulderX && rightHandX > rightElbowX) {
				keysControl(0);
			}

			// Up
			if (leftHandY < centerMassY + VARIATION
					&& leftHandY > centerMassY - VARIATION
					&& leftHandX < centerMassX + VARIATION
					&& leftHandX > centerMassX - VARIATION
					&& rightHandY < headY && rightHandY < rightElbowY) {
				keysControl(1);

			}
			// Down
			if (leftHandY < centerMassY + VARIATION
					&& leftHandY > centerMassY - VARIATION
					&& leftHandX < centerMassX + VARIATION
					&& leftHandX > centerMassX - VARIATION
					&& rightHandY > centerMassY && rightHandY > rightElbowY) {
				keysControl(2);

			}
			// left
			if (rightHandY < centerMassY + VARIATION
					&& rightHandY > centerMassY - VARIATION
					&& rightHandX < centerMassX + VARIATION
					&& rightHandX > centerMassX - VARIATION
					&& leftElbowX < leftShoulderX && leftHandX < leftElbowX) {
				keysControl(3);
			}
			// Zoom+
			if (rightHandY < centerMassY + VARIATION
					&& rightHandY > centerMassY - VARIATION
					&& rightHandX < centerMassX + VARIATION
					&& rightHandX > centerMassX - VARIATION
					&& leftHandY < headY && leftHandY < leftElbowY) {
				keysControl(4);
			}
			// Zoom-
			if (rightHandY < centerMassY + 20 && rightHandY > centerMassY - 20
					&& rightHandX < centerMassX + 20
					&& rightHandX > centerMassX - 20 && leftHandY > centerMassY
					&& leftHandY > leftElbowY) {
				keysControl(5);
			}
		}
	}

	public void addPose() {
		// rules for the right arm
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRule.ABOVE,
				SimpleOpenNI.SKEL_RIGHT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_RIGHT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_ELBOW, PoseRule.ABOVE,
				SimpleOpenNI.SKEL_RIGHT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_ELBOW, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_RIGHT_SHOULDER);
		// rules for the left arm
		pose.addRule(SimpleOpenNI.SKEL_LEFT_ELBOW, PoseRule.BELOW,
				SimpleOpenNI.SKEL_LEFT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_ELBOW, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_LEFT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_HAND, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_LEFT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_HAND, PoseRule.BELOW,
				SimpleOpenNI.SKEL_LEFT_ELBOW);
		// rules for the right leg
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_KNEE, PoseRule.BELOW,
				SimpleOpenNI.SKEL_RIGHT_HIP);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_KNEE, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_RIGHT_HIP);
		// rules for the left leg
		pose.addRule(SimpleOpenNI.SKEL_LEFT_KNEE, PoseRule.BELOW,
				SimpleOpenNI.SKEL_LEFT_HIP);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_KNEE, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_LEFT_HIP);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_FOOT, PoseRule.BELOW,
				SimpleOpenNI.SKEL_LEFT_KNEE);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_FOOT, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_LEFT_KNEE);
	}

	public void addwave(String side) {
		if (side.equals("RIGHT")) {
			pose.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRule.ABOVE,
					SimpleOpenNI.SKEL_HEAD);
			pose.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRule.LEFT_OF,
					SimpleOpenNI.SKEL_RIGHT_ELBOW);
			pose.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRule.RIGHT_OF,
					SimpleOpenNI.SKEL_RIGHT_ELBOW);
		}
		else{
			pose.addRule(SimpleOpenNI.SKEL_LEFT_HAND, PoseRule.ABOVE,
					SimpleOpenNI.SKEL_HEAD);
			pose.addRule(SimpleOpenNI.SKEL_LEFT_HAND, PoseRule.LEFT_OF,
					SimpleOpenNI.SKEL_LEFT_ELBOW);
			pose.addRule(SimpleOpenNI.SKEL_LEFT_HAND, PoseRule.RIGHT_OF,
					SimpleOpenNI.SKEL_LEFT_ELBOW);
		}
	}
	public void addswipe(String side){
		if (side.equals("RIGHT")) {
			pose.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRule.ABOVE,
					SimpleOpenNI.SKEL_HEAD);
			pose.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRule.LEFT_OF,
					SimpleOpenNI.SKEL_LEFT_SHOULDER);
			pose.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRule.ABOVE,
					SimpleOpenNI.SKEL_HEAD);
			pose.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRule.RIGHT_OF,
					SimpleOpenNI.SKEL_RIGHT_SHOULDER);
		}
		else{
			pose.addRule(SimpleOpenNI.SKEL_LEFT_HAND, PoseRule.ABOVE,
					SimpleOpenNI.SKEL_HEAD);
			pose.addRule(SimpleOpenNI.SKEL_LEFT_HAND, PoseRule.LEFT_OF,
					SimpleOpenNI.SKEL_LEFT_SHOULDER);
			pose.addRule(SimpleOpenNI.SKEL_LEFT_HAND, PoseRule.ABOVE,
					SimpleOpenNI.SKEL_HEAD);
			pose.addRule(SimpleOpenNI.SKEL_LEFT_HAND, PoseRule.RIGHT_OF,
					SimpleOpenNI.SKEL_RIGHT_SHOULDER);
		}
	}
	
	public void addHold(String side){
		if (side.equals("RIGHT")) {
			pose.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRule.LEFT_OF,
					SimpleOpenNI.SKEL_RIGHT_SHOULDER);
			pose.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRule.ABOVE,
					SimpleOpenNI.SKEL_HEAD);
			
			pose.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRule.RIGHT_OF,
					SimpleOpenNI.SKEL_RIGHT_SHOULDER);
		}
		else{
			pose.addRule(SimpleOpenNI.SKEL_LEFT_HAND, PoseRule.ABOVE,
					SimpleOpenNI.SKEL_HEAD);
			pose.addRule(SimpleOpenNI.SKEL_LEFT_HAND, PoseRule.LEFT_OF,
					SimpleOpenNI.SKEL_LEFT_SHOULDER);
			pose.addRule(SimpleOpenNI.SKEL_LEFT_HAND, PoseRule.ABOVE,
					SimpleOpenNI.SKEL_HEAD);
			pose.addRule(SimpleOpenNI.SKEL_LEFT_HAND, PoseRule.RIGHT_OF,
					SimpleOpenNI.SKEL_RIGHT_SHOULDER);
		}
	}
	public void poseCheck(int userId) {
		// check to see if the user
		// is in the pose
		if (pose.check(userId)) {
			// if they are, set the color white
			stroke(255);
			System.out.println("Skeleton Poser OK!");
		}
	}

	public void changeControl(int size) {
		int option;
		int widthLeft;
		int widthRight;
		int height;
		widthLeft = (int) (headX - getDistance());
		widthRight = (int) (headX + getDistance());
		height = (int) (headY - (getDistance() / 2));

		// left
		buttonEllipse(widthLeft, height, size, 0);

		// right
		buttonEllipse(widthRight, height, size, 0);

		if (compareHandPosition(widthLeft, height, "LEFT")
				&& compareHandPosition(widthRight, height, "RIGHT"))
			turnMotionPressed += 1;

		if (turnMotionPressed % 10 == 0)
			option = 1;
		else
			option = 0;
		switch (option) {
		case 0:
			setTurnMotion(false);
			break;
		case 1:
			buttonEllipse(widthLeft, height, size, 1);
			buttonEllipse(widthLeft, height, size + 10, 0);

			buttonEllipse(widthRight, height, size, 1);
			buttonEllipse(widthRight, height, size + 10, 0);
			setTurnMotion(true);
			break;
		}
	}

	public void mouseDinamicControl(int width, int height, int size, String side) {
		int option = 0;
		/**
		 * The Height is variable. Choice some point Y in parts of body.
		 * Exemple: headY, leftShoulderY, rightShoulderY, centerMassY.
		 */
		xx = (int) ((rightHandX * stageScale) * prW);
		yy = (int) ((rightHandX * stageScale) * prH);

		if (compareHandPosition(width, height, side))
			buttonPressed += 1;
		if (buttonPressed % 2 == 0)
			option = 1;
		else
			option = 0;
		switch (option) {
		case 0:
			createNextButton(0, width, height, size, side);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			break;
		case 1:
			buttonEllipse(width, height, size, 1);
			robot.mouseMove(xx, yy);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			break;
		}
	}

	public void mouseStaticControl(int width, int height, int size, String side) {
		int option = 0;
		xx = (int) ((rightHandX * stageScale) * prW);
		yy = (int) ((rightHandX * stageScale) * prH);
		int compareWidth = width + size / 2;
		int compareHeight = height + size / 3;
		if (compareHandPosition(compareWidth, compareHeight, side))
			buttonPressed += 1;
		if (buttonPressed % 2 == 0)
			option = 1;
		else
			option = 0;
		switch (option) {
		case 0:
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			break;
		case 1:
			buttonRectangle(width, height, size, 1);
			robot.mouseMove(xx, yy);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			break;
		}
	}

	public void zoom(int size, String side, boolean can_move) {
		if (can_move) {
			int option = 0;
			/**
			 * The Height is variable. Choice some point Y in parts of body.
			 * Exemple: headY, leftShoulderY, rightShoulderY, centerMassY.
			 */
			int width;
			int height;
			width = getBodyPositionX(side, headX);

			height = (int) rightShoulderY + 40;
			if (compareHandPosition(width, (height - 3 * size), side)) {
				option = 1;
			}
			if (compareHandPosition(width, (height - 2 * size) + 20, side)) {
				option = 2;
			}
			if (compareHandPosition(width, (height + 2 * size) - 20, side)) {
				option = 3;
			}
			switch (option) {
			case 0:
				// Reset, Up and Down
				createJoystick(width, height, size, 3);
				break;

			case 1:
				// Reset
				createJoystick(width, height, size, 3);
				buttonEllipse(width, height - 3 * size, size, 1);
				keysControl(6);
				break;

			case 2:
				// up
				createJoystick(width, height, size, 3);
				buttonTriangle(width, height - 2 * size, width + 2 * size / 2,
						height - size / 2, width - 2 * size / 2, height - size
								/ 2, 1);
				keysControl(4);
				break;
			case 3:
				// down
				createJoystick(width, height, size, 3);
				buttonTriangle(width, height + 2 * size, width - 2 * size / 2,
						height + size / 2, width + 2 * size / 2, height + size
								/ 2, 1);
				keysControl(5);
				break;
			}
		}

	}

	public void joystickControl(String side, int size, boolean can_move) {
		if (can_move) {
			int option = 0;
			/**
			 * The Height is variable. Choice some point Y in parts of body.
			 * Exemple: headY, leftShoulderY, rightShoulderY, centerMassY.
			 */
			int width;
			int height;
			width = getBodyPositionX(side, headX);
			height = getBodyPositionY(rightShoulderY);
			// center
			if (compareHandPosition(width, height, side))
				option = 2;
			// up
			if (compareHandPosition(width, (height - 2 * size) + 10, side))
				option = 3;

			// down
			if (compareHandPosition(width, (height + 2 * size) - 10, side))
				option = 4;

			// right
			if (compareHandPosition((width + 2 * size) - 20, height, side))
				option = 5;

			// left
			if (compareHandPosition((width - 2 * size) + 10, height, side))
				option = 6;

			switch (option) {
			case 0:
				// Center, Up and Down
				createJoystick(width, height, size, 0);
				break;
			case 2:
				// Center
				createJoystick(width, height, size, 0);
				buttonEllipse(width, height, size, 1);
				break;
			case 3:
				// up
				createJoystick(width, height, size, 0);
				buttonTriangle(width, height - 2 * size, width + size / 2,
						height - size, width - size / 2, height - size, 1);
				keysControl(1);
				break;
			case 4:
				// down
				createJoystick(width, height, size, 0);
				buttonTriangle(width, height + 2 * size, width - size / 2,
						height + size, width + size / 2, height + size, 1);
				keysControl(2);
				break;
			case 5:
				// right
				createJoystick(width, height, size, 0);
				buttonTriangle(width + 2 * size, height, width + size, height
						- size / 2, width + size, height + size / 2, 1);
				keysControl(0);
				break;
			case 6:
				// left
				createJoystick(width, height, size, 0);
				buttonTriangle(width - 2 * size, height, width - size, height
						- size / 2, width - size, height + size / 2, 1);
				keysControl(3);
				break;
			}
		}
	}

	public void optionButton(int size, String side, boolean can_move) {
		if (can_move) {
			int option = 0;
			/**
			 * The Height is variable. Choice some point Y in parts of body.
			 * Exemple: headY, leftShoulderY, rightShoulderY, centerMassY.
			 */
			int width;
			int height;
			width = (int) (headX - getDistance());
			height = (int) headY + 50;

			// Joystick
			if (compareHandPosition(width, height, side))
				option = 1;

			// Zoom
			if (compareHandPosition(width, height + 2 * size, side))
				option = 2;

			// Mouse Pressed
			if (compareHandPosition(width, height + 4 * size, side))
				option = 3;
			switch (option) {
			case 0:
				createNextButton(5, width, height, size, side);
				break;
			case 1:
				// Joystick
				createNextButton(5, width, height, size, side);
				buttonEllipse(width, height, size, 1);
				if (turnJoystick)
					turnJoystick = false;
				else
					turnJoystick = true;
				break;
			case 2:
				// zoom
				createNextButton(5, width, height, size, side);
				buttonEllipse(width, height + 2 * size, size, 1);
				if (turnZoom)
					turnZoom = false;
				else
					turnZoom = true;
				break;
			case 3:
				// mouse pressed
				createNextButton(5, width, height, size, side);
				buttonEllipse(width, height + 4 * size, size, 1);
				break;
			}
		}
	}

	/*
	 * Draw the skeleton with the selected joints
	 */
	public void drawSkeleton(int userId) {
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
	}

	public void drawHead(int userId) {
		PVector jointPosH = new PVector();
		kinect.getJointPositionSkeleton(userId, SimpleOpenNI.SKEL_HEAD,
				jointPosH);
		PVector jointPosH_Proj = new PVector();
		kinect.convertRealWorldToProjective(jointPosH, jointPosH_Proj);
		fill(0, 255, 255);
		stroke(0, 255, 255);
		ellipse(jointPosH_Proj.x, jointPosH_Proj.y, 50, 50);
		headX = jointPosH_Proj.x;
		headY = jointPosH_Proj.y;
	}

	public void drawHands(int userId) {
		PVector myRHand = new PVector();
		kinect.getJointPositionSkeleton(userId, SimpleOpenNI.SKEL_RIGHT_HAND,
				myRHand);
		PVector myRHand_Proj = new PVector();
		kinect.convertRealWorldToProjective(myRHand, myRHand_Proj);
		PVector myLHand = new PVector();
		kinect.getJointPositionSkeleton(userId, SimpleOpenNI.SKEL_LEFT_HAND,
				myLHand);
		PVector myLHand_Proj = new PVector();
		kinect.convertRealWorldToProjective(myLHand, myLHand_Proj);
		stroke(0, 255, 255);
		strokeWeight(2);
		fill(0, 255, 0);
		ellipse(myRHand_Proj.x, myRHand_Proj.y, 50, 50);
		ellipse(myLHand_Proj.x, myLHand_Proj.y, 50, 50);
		stroke(0, 255, 255);
		strokeWeight(2);
		fill(255, 0, 255);
		ellipse(myRHand_Proj.x, myRHand_Proj.y, 25, 25);
		ellipse(myLHand_Proj.x, myLHand_Proj.y, 25, 25);
		leftHandX = myLHand_Proj.x;
		rightHandX = myRHand_Proj.x;
		leftHandY = myLHand_Proj.y;
		rightHandY = myRHand_Proj.y;
	}

	public void drawCenterMass(int userId) {
		PVector realCenterMass = new PVector();
		kinect.getCoM(userId, realCenterMass);
		PVector projectiveCenterMass = new PVector();
		kinect.convertRealWorldToProjective(realCenterMass,
				projectiveCenterMass);
		centerMassX = projectiveCenterMass.x;
		centerMassY = projectiveCenterMass.y;
	}

	public void drawElbow(int userId) {
		PVector realRightElbow = new PVector();
		kinect.getJointPositionSkeleton(userId, SimpleOpenNI.SKEL_RIGHT_ELBOW,
				realRightElbow);
		PVector projectiveRightElbow = new PVector();
		kinect.convertRealWorldToProjective(realRightElbow,
				projectiveRightElbow);
		rightElbowX = projectiveRightElbow.x;
		rightElbowY = projectiveRightElbow.y;
		PVector realLeftElbow = new PVector();
		kinect.getJointPositionSkeleton(userId, SimpleOpenNI.SKEL_LEFT_ELBOW,
				realLeftElbow);
		PVector projectiveLeftElbow = new PVector();
		kinect.convertRealWorldToProjective(realLeftElbow, projectiveLeftElbow);
		leftElbowX = projectiveLeftElbow.x;
		leftElbowY = projectiveLeftElbow.y;
	}

	public void drawShoulder(int userId) {
		PVector realRightShoulder = new PVector();
		kinect.getJointPositionSkeleton(userId,
				SimpleOpenNI.SKEL_RIGHT_SHOULDER, realRightShoulder);
		PVector projectiveRightShoulder = new PVector();
		kinect.convertRealWorldToProjective(realRightShoulder,
				projectiveRightShoulder);
		rightShoulderX = projectiveRightShoulder.x;
		rightShoulderY = projectiveRightShoulder.y;
		PVector realLeftShoulder = new PVector();
		kinect.getJointPositionSkeleton(userId,
				SimpleOpenNI.SKEL_LEFT_SHOULDER, realLeftShoulder);
		PVector projectiveLeftShoulder = new PVector();
		kinect.convertRealWorldToProjective(realLeftShoulder,
				projectiveLeftShoulder);
		leftShoulderX = projectiveLeftShoulder.x;
		leftShoulderY = projectiveLeftShoulder.y;
	}

	public boolean compareHandPosition(int width, int height, String side) {
		if (side == "LEFT") {
			if (leftHandX < width + VARIATION && leftHandX > width - VARIATION
					&& leftHandY < height + VARIATION
					&& leftHandY > height - VARIATION)
				return true;
		} else if (side == "RIGHT") {
			if (rightHandX < width + VARIATION
					&& rightHandX > width - VARIATION
					&& rightHandY < height + VARIATION
					&& rightHandY > height - VARIATION)
				return true;
		} else {
			if (leftHandX < width + VARIATION && leftHandX > width - VARIATION
					&& leftHandY < height + VARIATION
					&& leftHandY > height - VARIATION
					&& rightHandX < width + VARIATION
					&& rightHandX > width - VARIATION
					&& rightHandY < height + VARIATION
					&& rightHandY > height - VARIATION)
				return true;
		}
		return false;
	}

	public double getPointDistance(double x1, double y1, double x2, double y2) {
		double d = (Math.pow((x2 - x1), 2)) + (Math.pow((y2 - y1), 2));
		return Math.sqrt(d);
	}

	public double getDistance() {
		double distance = getPointDistance(leftShoulderX, leftShoulderY,
				rightShoulderX, rightShoulderY);
		return distance;
	}

	public int getBodyPositionX(String side, double bodyPositionX) {
		int width;
		if (side == "LEFT")
			width = (int) (bodyPositionX - 2 * getDistance());
		else
			width = (int) (bodyPositionX + 2 * getDistance());
		return width;
	}

	public int getBodyPositionY(double bodyPositionY) {
		int height;
		height = (int) (bodyPositionY);
		return height;
	}

	public void screenSize() {
		Dimension dimensao = Toolkit.getDefaultToolkit().getScreenSize();
		stageWidth = (int) (dimensao.getWidth() - 20);
		stageHeight = (int) (dimensao.getHeight() - 20);
		prW = stageWidth / width;
		prH = stageHeight / height;
	}

	/*
	 * SimpleOpenNI events
	 */
	public void onNewUser(SimpleOpenNI curContext, int userId) {
		println("onNewUser - userId: " + userId);
		println("\tstart tracking skeleton");
		kinect.startTrackingSkeleton(userId);
	}

	public void onLostUser(SimpleOpenNI curContext, int userId) {
		println("onLostUser - userId: " + userId);
	}

	/*
	 * Gets and Sets
	 */
	public Integer getButtonPressed() {
		return buttonPressed;
	}

	public void setButtonPressed(Integer buttonPressed) {
		this.buttonPressed = buttonPressed;
	}

	public Boolean getTurnJoystick() {
		return turnJoystick;
	}

	public void setTurnJoystick(Boolean turnJoystick) {
		this.turnJoystick = turnJoystick;
	}

	public Boolean getTurnZoom() {
		return turnZoom;
	}

	public void setTurnZoom(Boolean turnZoom) {
		this.turnZoom = turnZoom;
	}

	public Integer getTurnMotionPressed() {
		return turnMotionPressed;
	}

	public void setTurnMotionPressed(Integer turnMotionPressed) {
		this.turnMotionPressed = turnMotionPressed;
	}

	public Boolean getTurnMotion() {
		return turnMotion;
	}

	public void setTurnMotion(Boolean turnMotion) {
		this.turnMotion = turnMotion;
	}

	public Integer getVARIATION() {
		return VARIATION;
	}
}
