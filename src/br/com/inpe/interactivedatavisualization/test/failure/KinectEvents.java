package br.com.inpe.interactivedatavisualization.test.failure;

import java.awt.AWTException;
import java.awt.Robot;

import SimpleOpenNI.SimpleOpenNI;
import processing.core.PApplet;
import processing.core.PVector;

/**
 * This class management the SimpleOpenNi events.
 * 
 * @author Heitor Guerra Carneiro.
 * @version 1.0
 * @since February 2015.
 * 
 */
public class KinectEvents extends PApplet {

	// Joints variable
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
	
	private Robot robot;
	private SimpleOpenNI kinect;
	
	KeyControl key = new KeyControl();
	Button button = new Button();
	AdvancedButton advancedButton = new AdvancedButton();
	MotionControl motion = new MotionControl();
	ChangeControl changeControl = new ChangeControl();
	
	
	public void setup() {

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

		textFont(createFont("Arial", 36));
		/*
		try {
			robot = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
	}

	public void draw() {
		// black background
		background(0);
		// update the cam
		kinect.update();

		int[] userList = kinect.getUsers();
		for (int i = 0; i < userList.length; i++) {
			if (kinect.isTrackingSkeleton(userList[i])) {
				// ----------------------------
				// Color of skeleton stroke
				strokeWeight(2);
				stroke(0, 255, 255);
				// SkeletonJoints (userList[i])
				drawSkeleton(userList[i]);
				drawHands(userList[i]);
				drawHead(userList[i]);
				drawCenterMass(userList[i]);
				drawElbow(userList[i]);
				drawShoulder(userList[i]);
				//------------------------
				changeControl.changeControl(50);
				motion.motionControl(changeControl.getTurnMotion());
				//key.optionButton((int) headY + 50, 40, "LEFT", changeControl.getTurnMotion());
				//key.zoomNext((int) rightShoulderY + 40, 30, "RIGHT",key.getTurnZoom());
				//key.joystickControl("RIGHT", (int) rightShoulderY, 30,key.getTurnJoystick());
			}
		}
	}

	// draw the skeleton with the selected joints
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
		//fill(255, 0, 0);
		//ellipse(projectiveCenterMass.x, projectiveCenterMass.y, 20, 20);
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
		fill(255, 0, 0);
		ellipse(projectiveRightElbow.x, projectiveRightElbow.y, 10, 10);

		rightElbowX = projectiveRightElbow.x;
		rightElbowY = projectiveRightElbow.y;

		PVector realLeftElbow = new PVector();

		kinect.getJointPositionSkeleton(userId, SimpleOpenNI.SKEL_LEFT_ELBOW,
				realLeftElbow);

		PVector projectiveLeftElbow = new PVector();

		kinect.convertRealWorldToProjective(realLeftElbow, projectiveLeftElbow);
		fill(255, 0, 0);
		ellipse(projectiveLeftElbow.x, projectiveLeftElbow.y, 10, 10);

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
		fill(255, 0, 0);
		ellipse(projectiveRightShoulder.x, projectiveRightShoulder.y, 10, 10);

		rightShoulderX = projectiveRightShoulder.x;
		rightShoulderY = projectiveRightShoulder.y;

		PVector realLeftShoulder = new PVector();

		kinect.getJointPositionSkeleton(userId,
				SimpleOpenNI.SKEL_LEFT_SHOULDER, realLeftShoulder);

		PVector projectiveLeftShoulder = new PVector();

		kinect.convertRealWorldToProjective(realLeftShoulder,
				projectiveLeftShoulder);
		fill(255, 0, 0);
		ellipse(projectiveLeftShoulder.x, projectiveLeftShoulder.y, 10, 10);

		leftShoulderX = projectiveLeftShoulder.x;
		leftShoulderY = projectiveLeftShoulder.y;
	}

	// SimpleOpenNI events

	public void onNewUser(SimpleOpenNI curContext, int userId) {
		println("onNewUser - userId: " + userId);
		println("\tstart tracking skeleton");

		kinect.startTrackingSkeleton(userId);
	}

	public void onLostUser(SimpleOpenNI curContext, int userId) {
		println("onLostUser - userId: " + userId);
	}

	public void onVisibleUser(SimpleOpenNI curContext, int userId) {
		// println("onVisibleUser - userId: " + userId);
	}
	
	//Gets and sets

	public double getHeadY() {
		return headY;
	}

	public void setHeadY(double headY) {
		this.headY = headY;
	}

	public double getHeadX() {
		return headX;
	}

	public void setHeadX(double headX) {
		this.headX = headX;
	}

	public double getRightHandX() {
		return rightHandX;
	}

	public void setRightHandX(double rightHandX) {
		this.rightHandX = rightHandX;
	}

	public double getRightHandY() {
		return rightHandY;
	}

	public void setRightHandY(double rightHandY) {
		this.rightHandY = rightHandY;
	}

	public double getLeftHandX() {
		return leftHandX;
	}

	public void setLeftHandX(double leftHandX) {
		this.leftHandX = leftHandX;
	}

	public double getLeftHandY() {
		return leftHandY;
	}

	public void setLeftHandY(double leftHandY) {
		this.leftHandY = leftHandY;
	}

	public double getCenterMassX() {
		return centerMassX;
	}

	public void setCenterMassX(double centerMassX) {
		this.centerMassX = centerMassX;
	}

	public double getCenterMassY() {
		return centerMassY;
	}

	public void setCenterMassY(double centerMassY) {
		this.centerMassY = centerMassY;
	}

	public double getRightElbowX() {
		return rightElbowX;
	}

	public void setRightElbowX(double rightElbowX) {
		this.rightElbowX = rightElbowX;
	}

	public double getRightElbowY() {
		return rightElbowY;
	}

	public void setRightElbowY(double rightElbowY) {
		this.rightElbowY = rightElbowY;
	}

	public double getLeftElbowX() {
		return leftElbowX;
	}

	public void setLeftElbowX(double leftElbowX) {
		this.leftElbowX = leftElbowX;
	}

	public double getLeftElbowY() {
		return leftElbowY;
	}

	public void setLeftElbowY(double leftElbowY) {
		this.leftElbowY = leftElbowY;
	}

	public double getRightShoulderX() {
		return rightShoulderX;
	}

	public void setRightShoulderX(double rightShoulderX) {
		this.rightShoulderX = rightShoulderX;
	}

	public double getRightShoulderY() {
		return rightShoulderY;
	}

	public void setRightShoulderY(double rightShoulderY) {
		this.rightShoulderY = rightShoulderY;
	}

	public double getLeftShoulderX() {
		return leftShoulderX;
	}

	public void setLeftShoulderX(double leftShoulderX) {
		this.leftShoulderX = leftShoulderX;
	}

	public double getLeftShoulderY() {
		return leftShoulderY;
	}

	public void setLeftShoulderY(double leftShoulderY) {
		this.leftShoulderY = leftShoulderY;
	}
	public Robot getRobot() {
		return robot;
	}
	public void setRobot(Robot robot) {
		this.robot = robot;
	}
	
}
