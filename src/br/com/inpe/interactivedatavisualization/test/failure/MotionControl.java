package br.com.inpe.interactivedatavisualization.test.failure;

/**
 * This class create the movement control.
 * 
 * @author Heitor Guerra Carneiro.
 * @version 1.0
 * @since February 2015.
 * 
 */
public class MotionControl {
	KinectEvents kinect = new KinectEvents();
	//KeyControl key = new KeyControl();
	
	public MotionControl(){
		
	}
	public boolean compareHandPosition(int width, int height, String side) {
		if (side == "LEFT") {
			if (kinect.getLeftHandX() < width + 10 && kinect.getLeftHandX() > width - 10
					&& kinect.getLeftHandY()  < height + 10 && kinect.getLeftHandY() > height - 10)
				return true;
		} else if (side == "RIGHT") {
			if (kinect.getRightHandX() < width + 10 && kinect.getRightHandX() > width - 10
					&& kinect.getRightHandY()  < height + 10 && kinect.getRightHandY() > height - 10)
				return true;
		} else {
			if (kinect.getLeftHandX() < width + 10 && kinect.getLeftHandX() > width - 10
					&& kinect.getLeftHandY()  < height + 10 && kinect.getLeftHandY() > height - 10
					&& kinect.getRightHandX() < width + 10 && kinect.getRightHandX() > width - 10
					&& kinect.getRightHandY()  < height + 10 && kinect.getRightHandY() > height - 10)
				return true;
		}
		return false;
	}
	

	public void motionControl(boolean can_move) {
		if (!can_move) {
			// right
			if (kinect.getLeftHandY() < kinect.getCenterMassY() + 20 && kinect.getLeftHandY() > kinect.getCenterMassY() - 20
					&& kinect.getLeftHandX() < kinect.getCenterMassX() + 20
					&& kinect.getLeftHandX() > kinect.getCenterMassX() - 20
					&& kinect.getRightElbowX() > kinect.getRightShoulderX() && kinect.getRightHandX() > kinect.getRightElbowX()) {
				//key.keysControl(0);
			}

			// Up
			if (kinect.getLeftHandY() < kinect.getCenterMassY() + 20 && kinect.getLeftHandY() > kinect.getCenterMassY() - 20
					&& kinect.getLeftHandX() < kinect.getCenterMassX() + 20
					&& kinect.getLeftHandX() > kinect.getCenterMassX() - 20 && kinect.getRightHandY() < kinect.getHeadY()
					&& kinect.getRightHandY() < kinect.getRightElbowY()) {
				//key.keysControl(1);

			}
			// Down
			if (kinect.getLeftHandY() < kinect.getCenterMassY() + 20 && kinect.getLeftHandY() > kinect.getCenterMassY() - 20
					&& kinect.getLeftHandX() < kinect.getCenterMassX() + 20
					&& kinect.getLeftHandX() > kinect.getCenterMassX() - 20 && kinect.getRightHandY() > kinect.getCenterMassY()
					&& kinect.getRightHandY() > kinect.getRightElbowY()) {
				//key.keysControl(2);

			}
			// ---------------
			// left
			if (kinect.getRightHandY() < kinect.getCenterMassY() + 20 && kinect.getRightHandY() > kinect.getCenterMassY() - 20
					&& kinect.getRightHandX() < kinect.getCenterMassX() + 20
					&& kinect.getRightHandX() > kinect.getCenterMassX() - 20
					&& kinect.getLeftElbowX() < kinect.getLeftShoulderX() && kinect.getLeftHandX() < kinect.getLeftElbowX()) {
				//key.keysControl(3);
			}
			// Zoom+
			if (kinect.getRightHandY() < kinect.getCenterMassY() + 20 && kinect.getRightHandY() > kinect.getCenterMassY() - 20
					&& kinect.getRightHandX() < kinect.getCenterMassX() + 20
					&& kinect.getRightHandX() > kinect.getCenterMassX() - 20 && kinect.getLeftHandY() < kinect.getHeadY()
					&& kinect.getLeftHandY() < kinect.getLeftElbowY()) {
				//key.keysControl(4);
			}

			// Zoom-
			if (kinect.getRightHandY() < kinect.getCenterMassY() + 20 && kinect.getRightHandY() > kinect.getCenterMassY() - 20
					&& kinect.getRightHandX() < kinect.getCenterMassX() + 20
					&& kinect.getRightHandX() > kinect.getCenterMassX() - 20 && kinect.getLeftHandY() > kinect.getCenterMassY()
					&& kinect.getLeftHandY() > kinect.getLeftElbowY()) {
				//key.keysControl(5);
			}
		}
	}
}
