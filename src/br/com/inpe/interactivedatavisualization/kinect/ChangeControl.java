package br.com.inpe.interactivedatavisualization.kinect;

/**
 * This class changes the control of the movement and virtual buttons.
 * 
 * @author Heitor Guerra Carneiro.
 * @version 1.0
 * @since February 2015.
 * 
 */
public class ChangeControl {
	KinectEvents kinect = new KinectEvents();
	AdvancedButton advancedButton = new AdvancedButton();
	MotionControl motion = new MotionControl();
	
	private Integer turnMotionPressed = 1;
	private Boolean turnMotion;
	
	public ChangeControl(){
		
	}
	
	public void changeControl(int size) {
		int option;
		int widthLeft, widthRight, height;
		
		widthLeft = (int) (kinect.getHeadX()- advancedButton.getDistance());
		widthRight = (int) (kinect.getHeadX() + advancedButton.getDistance());
		height = (int) (kinect.getHeadY() - advancedButton.getDistance() / 2);

		// left
		advancedButton.buttonEllipse(widthLeft, height, size, 0);

		// right
		advancedButton.buttonEllipse(widthRight, height, size, 0);

		if (motion.compareHandPosition(widthLeft, height, "LEFT")
				&& motion.compareHandPosition(widthRight, height, "RIGHT"))
			turnMotionPressed += 1;

		if (turnMotionPressed % 2 == 0)
			option = 1;
		else
			option = 0;

		switch (option) {
		case 0:
			setTurnMotion(false);
			break;
		case 1:
			advancedButton.buttonEllipse(widthLeft, height, size, 0);
			advancedButton.buttonEllipse(widthLeft, height, size+10, 0);
			
			advancedButton.buttonEllipse(widthRight, height, size, 0);
			advancedButton.buttonEllipse(widthRight, height, size+10, 0);
			setTurnMotion(true);
			break;
		}
	}
	//Gets and Sets
	public Boolean getTurnMotion() {
		return turnMotion;
	}

	public void setTurnMotion(Boolean turnMotion) {
		this.turnMotion = turnMotion;
	}
	
}
