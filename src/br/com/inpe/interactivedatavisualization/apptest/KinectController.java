package br.com.inpe.interactivedatavisualization.apptest;

import processing.core.PApplet;
import processing.core.PVector;
import SimpleOpenNI.*;
import java.awt.AWTException;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class KinectController extends PApplet {

	// Mouse

	private Robot robot;
	private int xx = 0, yy = 0;
	private int stageWidth = 1366;
	private int stageHeight = 768;
	private int prW, prH;
	private int stageScale = 5 / 3;

	private int button_pressed = 1;
	private int turn_motion_pressed = 1;
	private boolean turn_motion = false;
	private boolean turn_zoom = false;
	private boolean turn_joystick = false;

	// Hands
	private double rightHandX = 0;
	private double rightHandY = 0;
	private double leftHandX = 0;
	private double leftHandY = 0;

	// Center Mass
	private double centerMassX = 0;
	private double centerMassY = 0;

	// Elbow
	private double rightElbowX = 0;
	private double rightElbowY = 0;
	private double leftElbowX = 0;
	private double leftElbowY = 0;

	// Head
	private double headY = 0;
	private double headX = 0;

	// Shoulder
	private double rightShoulderX = 0;
	private double rightShoulderY = 0;
	private double leftShoulderX = 0;
	private double leftShoulderY = 0;

	// -------------------- Kinect init -------------------------
	private SimpleOpenNI context;

	public void setup() {

		prW = stageWidth / width;
		prH = stageHeight / height;

		context = new SimpleOpenNI(this);
		if (context.isInit() == false) {
			println("Can't init SimpleOpenNI, maybe the camera is not connected!");
			exit();
		}

		// enable depthMap generation
		context.enableDepth();

		// enable skeleton generation for all joints
		context.enableUser();

		// Size automatic
		size(context.depthWidth(), context.depthHeight());
		// size(300,220);

		textFont(createFont("Arial", 36));

		try {
			robot = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// ------------------------------------------------------

	public void draw() {

		background(0);
		// update the cam
		context.update();
		// -----------------------------------------------
		// Static objects

		// mouseStaticControl
		// buttonRectangle(50, 50,100, 0);
		// Mouse Position
		// showMousePosition();
		// ---------------------------------------------------------

		// Update in real time

		int[] userList = context.getUsers();
		for (int i = 0; i < userList.length; i++) {
			if (context.isTrackingSkeleton(userList[i])) {
				// ----------------------------
				strokeWeight(2);
				stroke(0, 255, 255);

				drawSkeleton(userList[i]);
				drawHands(userList[i]);
				drawHead(userList[i]);
				drawCenterMass(userList[i]);
				drawElbow(userList[i]);
				drawShoulder(userList[i]);

				// -----------------------------
				changeControl(50);
				motionControl(turn_motion);
				optionButton((int) headY + 50, 40, "LEFT", turn_motion);
				zoomNext((int) rightShoulderY + 40, 30, "RIGHT", turn_zoom);
				joystickControl("RIGHT", (int) rightShoulderY, 30,
						turn_joystick);
			}
		}
	}

	// ----------------------------------------------------------------------------
	// draw the skeleton with the selected joints
	public void drawSkeleton(int userId) {
		// to get the 3d joint data
		/*
		 * PVector jointPos = new PVector();
		 * context.getJointPositionSkeleton(userId
		 * ,SimpleOpenNI.SKEL_NECK,jointPos); println(jointPos);
		 */

		context.drawLimb(userId, SimpleOpenNI.SKEL_HEAD, SimpleOpenNI.SKEL_NECK);

		context.drawLimb(userId, SimpleOpenNI.SKEL_NECK,
				SimpleOpenNI.SKEL_LEFT_SHOULDER);
		context.drawLimb(userId, SimpleOpenNI.SKEL_LEFT_SHOULDER,
				SimpleOpenNI.SKEL_LEFT_ELBOW);
		context.drawLimb(userId, SimpleOpenNI.SKEL_LEFT_ELBOW,
				SimpleOpenNI.SKEL_LEFT_HAND);

		context.drawLimb(userId, SimpleOpenNI.SKEL_NECK,
				SimpleOpenNI.SKEL_RIGHT_SHOULDER);
		context.drawLimb(userId, SimpleOpenNI.SKEL_RIGHT_SHOULDER,
				SimpleOpenNI.SKEL_RIGHT_ELBOW);
		context.drawLimb(userId, SimpleOpenNI.SKEL_RIGHT_ELBOW,
				SimpleOpenNI.SKEL_RIGHT_HAND);

		context.drawLimb(userId, SimpleOpenNI.SKEL_LEFT_SHOULDER,
				SimpleOpenNI.SKEL_TORSO);
		context.drawLimb(userId, SimpleOpenNI.SKEL_RIGHT_SHOULDER,
				SimpleOpenNI.SKEL_TORSO);

		context.drawLimb(userId, SimpleOpenNI.SKEL_TORSO,
				SimpleOpenNI.SKEL_LEFT_HIP);
		context.drawLimb(userId, SimpleOpenNI.SKEL_LEFT_HIP,
				SimpleOpenNI.SKEL_LEFT_KNEE);
		context.drawLimb(userId, SimpleOpenNI.SKEL_LEFT_KNEE,
				SimpleOpenNI.SKEL_LEFT_FOOT);

		context.drawLimb(userId, SimpleOpenNI.SKEL_TORSO,
				SimpleOpenNI.SKEL_RIGHT_HIP);
		context.drawLimb(userId, SimpleOpenNI.SKEL_RIGHT_HIP,
				SimpleOpenNI.SKEL_RIGHT_KNEE);
		context.drawLimb(userId, SimpleOpenNI.SKEL_RIGHT_KNEE,
				SimpleOpenNI.SKEL_RIGHT_FOOT);
	}

	// -----------------------------------------------------------------
	// SimpleOpenNI events

	public void onNewUser(SimpleOpenNI curContext, int userId) {
		println("onNewUser - userId: " + userId);
		println("\tstart tracking skeleton");

		context.startTrackingSkeleton(userId);
	}

	public void onLostUser(SimpleOpenNI curContext, int userId) {
		println("onLostUser - userId: " + userId);
	}

	public void onVisibleUser(SimpleOpenNI curContext, int userId) {
		// println("onVisibleUser - userId: " + userId);
	}

	// ---------------------------------------------------------------------
	public void drawHead(int userId) {
		PVector jointPosH = new PVector();

		context.getJointPositionSkeleton(userId, SimpleOpenNI.SKEL_HEAD,
				jointPosH);
		PVector jointPosH_Proj = new PVector();
		context.convertRealWorldToProjective(jointPosH, jointPosH_Proj);
		fill(0, 255, 255);
		stroke(0, 255, 255);
		ellipse(jointPosH_Proj.x, jointPosH_Proj.y, 50, 50);
		headX = jointPosH_Proj.x;
		headY = jointPosH_Proj.y;
	}

	public void drawHands(int userId) {
		PVector myRHand = new PVector();
		context.getJointPositionSkeleton(userId, SimpleOpenNI.SKEL_RIGHT_HAND,
				myRHand);
		PVector myRHand_Proj = new PVector();
		context.convertRealWorldToProjective(myRHand, myRHand_Proj);

		PVector myLHand = new PVector();
		context.getJointPositionSkeleton(userId, SimpleOpenNI.SKEL_LEFT_HAND,
				myLHand);
		PVector myLHand_Proj = new PVector();
		context.convertRealWorldToProjective(myLHand, myLHand_Proj);

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
		context.getCoM(userId, realCenterMass);
		PVector projectiveCenterMass = new PVector();
		context.convertRealWorldToProjective(realCenterMass,
				projectiveCenterMass);
		fill(255, 0, 0);
		ellipse(projectiveCenterMass.x, projectiveCenterMass.y, 20, 20);
		centerMassX = projectiveCenterMass.x;
		centerMassY = projectiveCenterMass.y;
	}

	public void drawElbow(int userId) {

		PVector realRightElbow = new PVector();

		context.getJointPositionSkeleton(userId, SimpleOpenNI.SKEL_RIGHT_ELBOW,
				realRightElbow);
		PVector projectiveRightElbow = new PVector();

		context.convertRealWorldToProjective(realRightElbow,
				projectiveRightElbow);
		fill(255, 0, 0);
		ellipse(projectiveRightElbow.x, projectiveRightElbow.y, 10, 10);

		rightElbowX = projectiveRightElbow.x;
		rightElbowY = projectiveRightElbow.y;

		PVector realLeftElbow = new PVector();

		context.getJointPositionSkeleton(userId, SimpleOpenNI.SKEL_LEFT_ELBOW,
				realLeftElbow);

		PVector projectiveLeftElbow = new PVector();

		context.convertRealWorldToProjective(realLeftElbow, projectiveLeftElbow);
		fill(255, 0, 0);
		ellipse(projectiveLeftElbow.x, projectiveLeftElbow.y, 10, 10);

		leftElbowX = projectiveLeftElbow.x;
		leftElbowY = projectiveLeftElbow.y;
	}

	public void drawShoulder(int userId) {
		PVector realRightShoulder = new PVector();

		context.getJointPositionSkeleton(userId,
				SimpleOpenNI.SKEL_RIGHT_SHOULDER, realRightShoulder);

		PVector projectiveRightShoulder = new PVector();

		context.convertRealWorldToProjective(realRightShoulder,
				projectiveRightShoulder);
		fill(255, 0, 0);
		ellipse(projectiveRightShoulder.x, projectiveRightShoulder.y, 10, 10);

		rightShoulderX = projectiveRightShoulder.x;
		rightShoulderY = projectiveRightShoulder.y;

		PVector realLeftShoulder = new PVector();

		context.getJointPositionSkeleton(userId,
				SimpleOpenNI.SKEL_LEFT_SHOULDER, realLeftShoulder);

		PVector projectiveLeftShoulder = new PVector();

		context.convertRealWorldToProjective(realLeftShoulder,
				projectiveLeftShoulder);
		fill(255, 0, 0);
		ellipse(projectiveLeftShoulder.x, projectiveLeftShoulder.y, 10, 10);

		leftShoulderX = projectiveLeftShoulder.x;
		leftShoulderY = projectiveLeftShoulder.y;
	}

	// --------------------------------------------------------------
	public void colorManager(String color, String where, int A, int B, int C) {
		int option = 0;
		if (color == "CUSTOM")
			option = 0;
		if (color == "WHITE")
			option = 1;
		if (color == "BLUE")
			option = 2;
		if (color == "MAGENTA")
			option = 3;
		if (color == "PURPLE")
			option = 4;
		if (color == "GREEN")
			option = 5;
		if (color == "YELLOW")
			option = 6;
		if (color == "RED")
			option = 7;

		switch (option) {
		case 0:
			if (where == "FILL") {
				noStroke();
				fill(A, B, C);
			} else {
				stroke(A, B, C);
				noFill();
			}
			break;

		case 1:
			if (where == "FILL") {
				noStroke();
				fill(255, 255, 255);
			} else {
				stroke(255, 255, 255);
				noFill();
			}
			break;

		case 2:
			if (where == "FILL") {
				noStroke();
				fill(0, 0, 255);
			} else {
				stroke(0, 0, 255);
				noFill();
			}
			break;

		case 3:
			if (where == "FILL") {
				noStroke();
				fill(255, 0, 255);
			} else {
				stroke(255, 0, 255);
				noFill();
			}
			break;

		case 4:
			if (where == "FILL") {
				noStroke();
				fill(77, 0, 153);
			} else {
				stroke(77, 0, 153);
				noFill();
			}
			break;

		case 5:
			if (where == "FILL") {
				noStroke();
				fill(0, 255, 0);
			} else {
				stroke(0, 255, 0);
				noFill();
			}
			break;

		case 6:
			if (where == "FILL") {
				noStroke();
				fill(255, 255, 0);
			} else {
				stroke(255, 255, 0);
				noFill();
			}
			break;
		case 7:
			if (where == "FILL") {
				noStroke();
				fill(255, 0, 0);
			} else {
				stroke(255, 0, 0);
				noFill();
			}
			break;
		}
	}

	public void titleString(String menssage, int sizeFont, float positionX,
			float positionY, String colorFill, int A, int B, int C) {
		textSize(sizeFont);
		colorManager(colorFill, "FILL", A, B, C);
		text(menssage, positionX, positionY);
	}

	public void buttonEllipse(float w, float h, int size, int option,
			String colorFill, String colorStroke, int A, int B, int C,
			String menssage, String colorMenssage) {
		switch (option) {
		case 0:
			// Normal
			colorManager(colorFill, "FILL", A, B, C);
			ellipse(w, h, size, size);
			colorManager(colorStroke, "STROKE", A, B, C);
			ellipse(w, h, size + 10, size + 10);
			titleString(menssage, (int) (size / 3), w - (size / 2) + 5, h,
					colorMenssage, A, B, C);
			break;
		case 1:
			// Shiny
			colorManager("YELLOW", "FILL", A, B, C);
			ellipse(w, h, size, size);
			colorManager("YELLOW", "STROKE", 0, 0, 0);
			ellipse(w, h, size + 10, size + 10);
		}
	}

	public void buttonTriangle(float wX1, float hY1, float wX2, float hY2,
			float wX3, float hY3, int option, String colorFill, int A, int B,
			int C) {
		switch (option) {
		case 0:
			// Normal
			colorManager(colorFill, "FILL", A, B, C);
			triangle(wX1, hY1, wX2, hY2, wX3, hY3);
			stroke(255, 255, 0, 80);
			break;
		case 1:
			// Shiny
			colorManager("YELLOW", "FILL", 0, 0, 0);
			triangle(wX1, hY1, wX2, hY2, wX3, hY3);
			stroke(255, 255, 0);
		}
	}

	public void buttonRectangle(float width, float height, float size,
			int option, String colorFill, int A, int B, int C, String menssage,
			String colorMenssage) {

		switch (option) {
		case 0:
			// Normal
			colorManager(colorFill, "FILL", A, B, C);
			rect(width, height, 2 * size, width);
			titleString(menssage, (int) (size / 3 + size / 2), width,
					((3 * height) / 2) + 10, colorMenssage, A, B, C);
			break;
		case 1:
			// Shiny
			colorManager("YELLOW", "FILL", 0, 0, 0);
			rect(width, height, 2 * size, width);
			titleString(menssage, (int) (size / 3 + size / 2), width,
					((3 * height) / 2) + 10, colorMenssage, A, B, C);
		}
	}

	public void buttonEllipse(float w, float h, int size, int option) {
		switch (option) {
		case 0:
			// Normal
			noStroke();
			fill(255, 0, 0);
			ellipse(w, h, size, size);
			stroke(255, 255, 0, 80);
			noFill();
			ellipse(w, h, size + 10, size + 10);
			break;
		case 1:
			// Shiny
			noStroke();
			fill(255, 255, 0);
			ellipse(w, h, size, size);
			stroke(255, 255, 0);
			noFill();
			ellipse(w, h, size + 10, size + 10);
		}
	}

	public void buttonTriangle(float wX1, float hY1, float wX2, float hY2,
			float wX3, float hY3, int option) {
		switch (option) {
		case 0:
			// Normal
			noStroke();
			fill(255, 0, 0);
			triangle(wX1, hY1, wX2, hY2, wX3, hY3);
			stroke(255, 255, 0, 80);
			break;
		case 1:
			// Shiny
			noStroke();
			fill(255, 255, 0);
			triangle(wX1, hY1, wX2, hY2, wX3, hY3);
			stroke(255, 255, 0);
		}
	}

	public void buttonRectangle(float width, float height, float size,
			int option) {

		switch (option) {
		case 0:
			// Normal
			colorManager("BLUE", "FILL", 0, 0, 0);
			rect(width, height, 2 * size, width);
			break;
		case 1:
			// Shiny
			colorManager("YELLOW", "FILL", 0, 0, 0);
			rect(width, height, 2 * size, width);

		}
	}

	public void sequentialButton(int width, int height, int size, int option) {

		switch (option) {
		case 0:
			// Circle button
			buttonEllipse(width, height, size, 0);
			buttonEllipse(width, height + 2 * size, size, 0);
			buttonEllipse(width, height + 4 * size, size, 0);
			break;
		case 1:
			// Shiny circle button
			buttonEllipse(width, height, size, 1);
			buttonEllipse(width, height + 2 * size, size, 1);
			buttonEllipse(width, height + 4 * size, size, 1);
			break;
		}
	}

	public void createJoystick(int x, int y, int size, int option) {

		switch (option) {
		case 0:
			// joystick

			// center
			buttonEllipse(x, y, size, 0);
			// up
			buttonTriangle(x, y - 2 * size, x + size / 2, y - size, x - size
					/ 2, y - size, 0);
			// down
			buttonTriangle(x, y + 2 * size, x - size / 2, y + size, x + size
					/ 2, y + size, 0);
			// right
			buttonTriangle(x + 2 * size, y, x + size, y - size / 2, x + size, y
					+ size / 2, 0);
			// left
			buttonTriangle(x - 2 * size, y, x - size, y - size / 2, x - size, y
					+ size / 2, 0);
			break;

		case 1:
			// center
			buttonEllipse(x, y, size, 0);
			// up
			buttonTriangle(x, y - 2 * size, x + size / 2, y - size, x - size
					/ 2, y - size, 0);
			// down
			buttonTriangle(x, y + 2 * size, x - size / 2, y + size, x + size
					/ 2, y + size, 0);
			break;

		case 2:
			// center
			buttonEllipse(x, y, size, 0);
			// right
			buttonTriangle(x + 2 * size, y, x + size, y - size / 2, x + size, y
					+ size / 2, 0);
			// left
			buttonTriangle(x - 2 * size, y, x - size, y - size / 2, x - size, y
					+ size / 2, 0);
			break;

		case 3:
			// zoom
			// center
			buttonEllipse(x, y - 3 * size, size, 0);
			// up
			buttonTriangle(x, y - 2 * size, x + 2 * size / 2, y - size / 2, x
					- 2 * size / 2, y - size / 2, 0);
			// down
			buttonTriangle(x, y + 2 * size, x - 2 * size / 2, y + size / 2, x
					+ 2 * size / 2, y + size / 2, 0);

		}

	}

	public double getPointDistance(double x1, double y1, double x2, double y2) {
		double d = (Math.pow((x2 - x1), 2)) + (Math.pow((y2 - y1), 2));
		return Math.sqrt(d);
	}

	public void createNextButton(int option, int height, int size, String side) {
		/**
		 * The Height is variable. Choice some point Y in parts of body.
		 * Exemple: headY, leftShoulderY, rightShoulderY, centerMassY.
		 */
		int width;
		double distance = getPointDistance(leftShoulderX, leftShoulderY,
				rightShoulderX, rightShoulderY);
		if (side == "LEFT")
			width = (int) (headX - 2 * distance);
		else
			width = (int) (headX + 2 * distance);

		switch (option) {
		case 0:
			// ellipse
			buttonEllipse(width, height, size, 0);
			break;
		case 1:
			// Full Joystick
			createJoystick(width, height, size, 0);
			break;
		case 2:
			// Center, Up and Down
			createJoystick(width, height, size, 1);
			break;
		case 3:
			// Center, Left and Right
			createJoystick(width, height, size, 2);
			break;
		case 4:
			// Rectangle
			buttonRectangle(width, height, size, 0);
			break;
		case 5:
			// Sequencial
			sequentialButton((int) (headX - distance - 40), height, size, 0);
		}
	}

	public boolean compareHandPosition(int width, int height, String side) {
		if (side == "LEFT") {
			if (leftHandX < width + 10 && leftHandX > width - 10
					&& leftHandY < height + 10 && leftHandY > height - 10)
				return true;
		} else if (side == "RIGHT") {
			if (rightHandX < width + 10 && rightHandX > width - 10
					&& rightHandY < height + 10 && rightHandY > height - 10)
				return true;
		} else {
			if (leftHandX < width + 10 && leftHandX > width - 10
					&& leftHandY < height + 10 && leftHandY > height - 10
					&& rightHandX < width + 10 && rightHandX > width - 10
					&& rightHandY < height + 10 && rightHandY > height - 10)
				return true;
		}
		return false;
	}

	public boolean delayMoviment(int seconds, int width, int height, String side) {
		long start = System.currentTimeMillis();
		do {
			long end = System.currentTimeMillis();
			if ((end - start) / 1000 >= seconds)
				return true;

		} while (compareHandPosition(width, height, side));
		return false;
	}

	public void showMousePosition() {
		Point xy = getMousePosition();
		System.out.println("Posticao x y : " + xy);
	}

	// -----------------------------------------------------------------------------
	// Control functions
	public void changeControl(int size) {
		int option;
		int widthLeft, widthRight, height;
		double distance = getPointDistance(leftShoulderX, leftShoulderY,
				rightShoulderX, rightShoulderY);

		widthLeft = (int) (headX - distance);
		widthRight = (int) (headX + distance);
		height = (int) (headY - distance / 2);

		// left
		buttonEllipse(widthLeft, height, size, 0);

		// right
		buttonEllipse(widthRight, height, size, 0);

		if (compareHandPosition(widthLeft, height, "LEFT")
				&& compareHandPosition(widthRight, height, "RIGHT"))
			turn_motion_pressed += 1;

		if (turn_motion_pressed % 2 == 0)
			option = 1;
		else
			option = 0;

		switch (option) {
		case 0:
			turn_motion = false;
			break;
		case 1:
			noStroke();
			fill(255, 255, 0);
			ellipse(widthLeft, height, size, size);
			stroke(255, 255, 0);
			noFill();
			ellipse(widthLeft, height, size + 10, size + 10);

			noStroke();
			fill(255, 255, 0);
			ellipse(widthRight, height, size, size);
			stroke(255, 255, 0);
			noFill();
			ellipse(widthRight, height, size + 10, size + 10);

			turn_motion = true;
			break;
		}
	}

	public void mouseDinamicControl(int height, int size, String side) {
		int option = 0;
		/**
		 * The Height is variable. Choice some point Y in parts of body.
		 * Exemple: headY, leftShoulderY, rightShoulderY, centerMassY.
		 */
		int width;
		double distance = getPointDistance(leftShoulderX, leftShoulderY,
				rightShoulderX, rightShoulderY);
		if (side == "LEFT")
			width = (int) (headX - 2 * distance);
		else
			width = (int) (headX + 2 * distance);

		xx = (((int) (rightHandX) * stageScale) * prW);
		yy = (((int) (rightHandY) * stageScale) * prH);

		if (compareHandPosition(width, height, side))
			button_pressed += 1;

		if (button_pressed % 2 == 0)
			option = 1;
		else
			option = 0;

		switch (option) {
		case 0:
			createNextButton(0, height, size, side);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			break;
		case 1:
			buttonEllipse(width, height, size, 1);
			robot.mouseMove(xx, yy);
			robot.delay(1000);
			/*
			 * 
			 * robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			 */
			break;
		}
	}

	public void mouseStaticControl(int width, int height, int size, String side) {
		int option = 0;
		xx = (((int) (rightHandX) * stageScale) * prW);
		yy = (((int) (rightHandY) * stageScale) * prH);

		int compareWidth = width + size / 2;
		int compareHeight = height + size / 3;

		if (compareHandPosition(compareWidth, compareHeight, side))
			button_pressed += 1;

		if (button_pressed % 2 == 0)
			option = 1;
		else
			option = 0;

		switch (option) {
		case 0:
			// robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			break;
		case 1:
			buttonRectangle(width, height, size, 1);
			/*
			 * robot.mouseMove(xx, yy); robot.delay(1000);
			 * robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			 */
			break;
		}
	}

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
			if (leftHandY < centerMassY + 20 && leftHandY > centerMassY - 20
					&& leftHandX < centerMassX + 20
					&& leftHandX > centerMassX - 20
					&& rightElbowX > rightShoulderX && rightHandX > rightElbowX) {
				keysControl(0);
			}

			// Up
			if (leftHandY < centerMassY + 20 && leftHandY > centerMassY - 20
					&& leftHandX < centerMassX + 20
					&& leftHandX > centerMassX - 20 && rightHandY < headY
					&& rightHandY < rightElbowY) {
				keysControl(1);

			}
			// Down
			if (leftHandY < centerMassY + 20 && leftHandY > centerMassY - 20
					&& leftHandX < centerMassX + 20
					&& leftHandX > centerMassX - 20 && rightHandY > centerMassY
					&& rightHandY > rightElbowY) {
				keysControl(2);

			}
			// ---------------
			// left
			if (rightHandY < centerMassY + 20 && rightHandY > centerMassY - 20
					&& rightHandX < centerMassX + 20
					&& rightHandX > centerMassX - 20
					&& leftElbowX < leftShoulderX && leftHandX < leftElbowX) {
				keysControl(3);
			}
			// Zoom+
			if (rightHandY < centerMassY + 20 && rightHandY > centerMassY - 20
					&& rightHandX < centerMassX + 20
					&& rightHandX > centerMassX - 20 && leftHandY < headY
					&& leftHandY < leftElbowY) {
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

	public void zoomNext(int height, int size, String side, boolean can_move) {
		if (can_move) {
			int option = 0;
			/**
			 * The Height is variable. Choice some point Y in parts of body.
			 * Exemple: headY, leftShoulderY, rightShoulderY, centerMassY.
			 */
			int width;
			double distance = getPointDistance(leftShoulderX, leftShoulderY,
					rightShoulderX, rightShoulderY);
			if (side == "LEFT")
				width = (int) (headX - 2 * distance);
			else
				width = (int) (headX + 2 * distance);

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

	public void joystickControl(String side, int height, int size,
			boolean can_move) {
		if (can_move) {
			int option = 0;
			/**
			 * The Height is variable. Choice some point Y in parts of body.
			 * Exemple: headY, leftShoulderY, rightShoulderY, centerMassY.
			 */
			int width;
			double distance = getPointDistance(leftShoulderX, leftShoulderY,
					rightShoulderX, rightShoulderY);
			if (side == "LEFT")
				width = (int) (headX - 2 * distance);
			else
				width = (int) (headX + 2 * distance);

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

	public void optionButton(int height, int size, String side, boolean can_move) {
		if (can_move) {
			int option = 0;
			/**
			 * The Height is variable. Choice some point Y in parts of body.
			 * Exemple: headY, leftShoulderY, rightShoulderY, centerMassY.
			 */
			int width;
			double distance = getPointDistance(leftShoulderX, leftShoulderY,
					rightShoulderX, rightShoulderY);

			width = (int) (headX - distance - 40);
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
				createNextButton(5, height, size, side);
				break;
			case 1:
				// Joystick
				createNextButton(5, height, size, side);
				buttonEllipse(width, height, size, 1);
				if (turn_joystick)
					turn_joystick = false;
				else
					turn_joystick = true;
				break;
			case 2:
				// zoom
				createNextButton(5, height, size, side);
				buttonEllipse(width, height + 2 * size, size, 1);
				if (turn_zoom)
					turn_zoom = false;
				else
					turn_zoom = true;
				break;
			case 3:
				// mouse pressed
				createNextButton(5, height, size, side);
				buttonEllipse(width, height + 4 * size, size, 1);
				break;
			}
		}
	}
	// END
}