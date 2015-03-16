package br.com.inpe.interactivedatavisualization.test.success;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import SimpleOpenNI.*;

/**
 * 
 * @author Heitor Guerra Carneiro.
 * @version 1.0
 * @since February 2015.
 */
public class Main extends PApplet {
	/**
	 * Create a new SimpleOpenNi, for communicate with the Kinect device.
	 */
	private Long temp1 = (long) 0;
	private Long temp2 = (long) 0;
	private Integer counter = 1;
	SimpleOpenNI kinect;
	Timer t = new Timer();
	
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
		
		t.startCounter();
	}

	public void draw() {
		// Set background.
		background(0);
		// Update the camera
		kinect.update();

		int[] users = kinect.getUsers();

		ellipseMode(CENTER);
		buttonEllipse(50, 200, 50,0);
		
		// iterate through users
		for (int i = 0; i < users.length; i++) {
			int uid = users[i];

			// You can play with the skeleton colors!
			// Skeleton stroke
			strokeWeight(2);
			// Skeleton color
			stroke(255, 255, 255);
			drawSkeleton(uid);

			// Draw center of mass of the user
			PVector realCoM = new PVector();
			kinect.getCoM(uid, realCoM);
			PVector projCoM = new PVector();

			// Convert realworld coordinates to projective
			kinect.convertRealWorldToProjective(realCoM, projCoM);
			// Set to red color.
			fill(255, 0, 0);
			ellipse(projCoM.x, projCoM.y, 10, 10);

			// check if user has a skeleton
			if (kinect.isTrackingSkeleton(uid)) {
				//======================================
				// draw head
				PVector realHead = new PVector();

				kinect.getJointPositionSkeleton(uid, SimpleOpenNI.SKEL_HEAD,
						realHead);
				PVector projHead = new PVector();
				kinect.convertRealWorldToProjective(realHead, projHead);
				fill(0, 153, 153);
				ellipse(projHead.x, projHead.y, 50, 50);
				//=======================================
				// draw left hand
				PVector realLHand = new PVector();
				kinect.getJointPositionSkeleton(uid,
						SimpleOpenNI.SKEL_LEFT_HAND, realLHand);
				PVector projLHand = new PVector();
				kinect.convertRealWorldToProjective(realLHand, projLHand);
				fill(255, 255, 0); 
				ellipse(projLHand.x, projLHand.y, 30, 30);
				
				if(compareHandPosition(projLHand.x, projLHand.y, 50, 200)){
					
					System.out.println("Tempo 1: " + getTime());
					System.out.println("Tempo 2: " + getTime2());	
					System.out.println("Tempo 3: " + getTime3());	
					
				}
				
				
				
			}

		}
	}
	public Long getTime(){
		return t.endCounter();
	}
	public Long getTime2(){
		return t.endCounter();
	}
	public Long getTime3(){
		return t.endCounter();
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
	public double getPointDistance(double x1, double y1, double x2, double y2) {
		double d = (Math.pow((x2 - x1), 2)) + (Math.pow((y2 - y1), 2));
		return Math.sqrt(d);
	}
	
	public boolean compareHandPosition(float handX, float handY, float width, float height) {
		final Integer VARIATION = 20;
		if (handX < width + VARIATION && handX > width - VARIATION
				&& handY < height + VARIATION
				&& handY > height - VARIATION){
			return true;
		}
		return false;
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

	// is called everytime a new user appears, SimpleOpenNI events.
	public void onNewUser(SimpleOpenNI curContext, int userId) {
		println("onNewUser - userId: " + userId);
		curContext.startTrackingSkeleton(userId);
	}

	// is called everytime a user disappears
	public void onLostUser(SimpleOpenNI curContext, int userId) {
		println("onLostUser - userId: " + userId);

	}
	// END
}