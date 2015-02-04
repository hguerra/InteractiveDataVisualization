package br.com.inpe.interactivedatavisualization.kinect;

/**
 * This class create complex virtual buttons.
 * 
 * @author Heitor Guerra Carneiro.
 * @version 1.0
 * @since February 2015.
 * 
 */
public class AdvancedButton extends Button{
	
	KinectEvents kinect = new KinectEvents();
	
	
	public AdvancedButton(){
		super();
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

	public void createNextButton(int option, int height, int size, String side) {
		/**
		 * The Height is variable. Choice some point Y in parts of body.
		 * Exemple: headY, leftShoulderY, rightShoulderY, centerMassY.
		 */
		int width;
		
		if (side == "LEFT")
			width = (int) (kinect.getHeadX() - 2 * getDistance());
		else
			width = (int) (kinect.getHeadX() * getDistance());

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
			sequentialButton((int) (kinect.getHeadX() - getDistance() - 40), height, size, 0);
		}
	}

	public double getPointDistance(double x1, double y1, double x2, double y2) {
		double d = (Math.pow((x2 - x1), 2)) + (Math.pow((y2 - y1), 2));
		return Math.sqrt(d);
	}

	//Gets and Sets
	public double getDistance() {
		double distance = getPointDistance(kinect.getLeftShoulderX(), kinect.getLeftShoulderY(),
				kinect.getRightShoulderX(), kinect.getRightShoulderY());
		return distance;
	}
}
