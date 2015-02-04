package br.com.inpe.interactivedatavisualization.kinect;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

/**
 * This class create the virtual buttons control.
 * 
 * @author Heitor Guerra Carneiro.
 * @version 1.0
 * @since February 2015.
 * 
 */
public class KeyControl {
	KinectEvents kinect = new KinectEvents();
	AdvancedButton advancedButton = new AdvancedButton();
	MotionControl motion = new MotionControl();

	private Integer buttonPressed = 1;
	private Boolean turnJoystick;
	private Boolean turnZoom;

	// Mouse
	private Robot robot;
	private int xx = 0, yy = 0;
	private int stageWidth;
	private int stageHeight;
	private int prW, prH;
	private int stageScale = 5 / 3;

	public KeyControl() {

	}

	public void initRobot() {
		try {
			robot = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void screenSize() {
		Dimension dimensao = Toolkit.getDefaultToolkit().getScreenSize();
		stageWidth = (int) (dimensao.getWidth() - 20);
		stageHeight = (int) (dimensao.getHeight() - 20);

		prW = stageWidth / kinect.getWidth();
		prH = stageHeight / kinect.getHeight();
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

	public void mouseDinamicControl(int height, int size, String side) {
		int option = 0;
		/**
		 * The Height is variable. Choice some point Y in parts of body.
		 * Exemple: headY, leftShoulderY, rightShoulderY, centerMassY.
		 */
		int width;
		if (side == "LEFT")
			width = (int) (kinect.getHeadX() - 2 * advancedButton.getDistance());
		else
			width = (int) (kinect.getHeadX() * advancedButton.getDistance());

		xx = (((int) (kinect.getRightHandX()) * stageScale) * prW);
		yy = (((int) (kinect.getRightHandX()) * stageScale) * prH);

		if (motion.compareHandPosition(width, height, side))
			buttonPressed += 1;

		if (buttonPressed % 2 == 0)
			option = 1;
		else
			option = 0;

		switch (option) {
		case 0:
			advancedButton.createNextButton(0, height, size, side);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			break;
		case 1:
			advancedButton.buttonEllipse(width, height, size, 1);
			robot.mouseMove(xx, yy);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			break;
		}
	}

	public void mouseStaticControl(int width, int height, int size, String side) {
		int option = 0;

		xx = (((int) (kinect.getRightHandX()) * stageScale) * prW);
		yy = (((int) (kinect.getRightHandX()) * stageScale) * prH);

		int compareWidth = width + size / 2;
		int compareHeight = height + size / 3;

		if (motion.compareHandPosition(compareWidth, compareHeight, side))
			buttonPressed += 1;

		if (buttonPressed % 2 == 0)
			option = 1;
		else
			option = 0;

		switch (option) {
		case 0:
			// robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			break;
		case 1:
			advancedButton.buttonRectangle(width, height, size, 1);
			robot.mouseMove(xx, yy);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			break;
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
			if (side == "LEFT")
				width = (int) (kinect.getHeadX() - 2 * advancedButton
						.getDistance());
			else
				width = (int) (kinect.getHeadX() * advancedButton.getDistance());

			if (motion.compareHandPosition(width, (height - 3 * size), side)) {
				option = 1;
			}
			if (motion.compareHandPosition(width, (height - 2 * size) + 20,
					side)) {
				option = 2;
			}
			if (motion.compareHandPosition(width, (height + 2 * size) - 20,
					side)) {
				option = 3;
			}
			switch (option) {
			case 0:
				// Reset, Up and Down
				advancedButton.createJoystick(width, height, size, 3);
				break;

			case 1:
				// Reset
				advancedButton.createJoystick(width, height, size, 3);
				advancedButton.buttonEllipse(width, height - 3 * size, size, 1);
				keysControl(6);
				break;

			case 2:
				// up
				advancedButton.createJoystick(width, height, size, 3);
				advancedButton.buttonTriangle(width, height - 2 * size, width
						+ 2 * size / 2, height - size / 2,
						width - 2 * size / 2, height - size / 2, 1);
				keysControl(4);
				break;
			case 3:
				// down
				advancedButton.createJoystick(width, height, size, 3);
				advancedButton.buttonTriangle(width, height + 2 * size, width
						- 2 * size / 2, height + size / 2,
						width + 2 * size / 2, height + size / 2, 1);
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
			if (side == "LEFT")
				width = (int) (kinect.getHeadX() - 2 * advancedButton
						.getDistance());
			else
				width = (int) (kinect.getHeadX() * advancedButton.getDistance());

			// center
			if (motion.compareHandPosition(width, height, side))
				option = 2;
			// up
			if (motion.compareHandPosition(width, (height - 2 * size) + 10,
					side))
				option = 3;

			// down
			if (motion.compareHandPosition(width, (height + 2 * size) - 10,
					side))
				option = 4;

			// right
			if (motion.compareHandPosition((width + 2 * size) - 20, height,
					side))
				option = 5;

			// left
			if (motion.compareHandPosition((width - 2 * size) + 10, height,
					side))
				option = 6;

			switch (option) {
			case 0:
				// Center, Up and Down
				advancedButton.createJoystick(width, height, size, 0);
				break;
			case 2:
				// Center
				advancedButton.createJoystick(width, height, size, 0);
				advancedButton.buttonEllipse(width, height, size, 1);
				break;
			case 3:
				// up
				advancedButton.createJoystick(width, height, size, 0);
				advancedButton.buttonTriangle(width, height - 2 * size, width
						+ size / 2, height - size, width - size / 2, height
						- size, 1);
				keysControl(1);
				break;
			case 4:
				// down
				advancedButton.createJoystick(width, height, size, 0);
				advancedButton.buttonTriangle(width, height + 2 * size, width
						- size / 2, height + size, width + size / 2, height
						+ size, 1);
				keysControl(2);
				break;
			case 5:
				// right
				advancedButton.createJoystick(width, height, size, 0);
				advancedButton.buttonTriangle(width + 2 * size, height, width
						+ size, height - size / 2, width + size, height + size
						/ 2, 1);
				keysControl(0);
				break;
			case 6:
				// left
				advancedButton.createJoystick(width, height, size, 0);
				advancedButton.buttonTriangle(width - 2 * size, height, width
						- size, height - size / 2, width - size, height + size
						/ 2, 1);
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
			width = (int) (kinect.getHeadX() - advancedButton.getDistance() - 40);
			// Joystick
			if (motion.compareHandPosition(width, height, side))
				option = 1;

			// Zoom
			if (motion.compareHandPosition(width, height + 2 * size, side))
				option = 2;

			// Mouse Pressed
			if (motion.compareHandPosition(width, height + 4 * size, side))
				option = 3;

			switch (option) {
			case 0:
				advancedButton.createNextButton(5, height, size, side);
				break;
			case 1:
				// Joystick
				advancedButton.createNextButton(5, height, size, side);
				advancedButton.buttonEllipse(width, height, size, 1);
				if (turnJoystick)
					turnJoystick = false;
				else
					turnJoystick = true;
				break;
			case 2:
				// zoom
				advancedButton.createNextButton(5, height, size, side);
				advancedButton.buttonEllipse(width, height + 2 * size, size, 1);
				if (turnZoom)
					turnZoom = false;
				else
					turnZoom = true;
				break;
			case 3:
				// mouse pressed
				advancedButton.createNextButton(5, height, size, side);
				advancedButton.buttonEllipse(width, height + 4 * size, size, 1);
				break;
			}
		}
	}

	// Gets and Sets
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

}
