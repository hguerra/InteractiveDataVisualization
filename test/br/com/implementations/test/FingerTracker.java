package br.com.implementations.test;

import br.com.inpe.kinect.model.gesture.posture.HandRecognize;
import processing.core.*;
import SimpleOpenNI.*;

public class FingerTracker extends PApplet {
	private static final long serialVersionUID = 1L;
	HandRecognize fingers;
	SimpleOpenNI kinect;

	int threshold = 700; // 625

	public void setup() {
		size(640, 480);

		kinect = new SimpleOpenNI(this);
		kinect.enableDepth();

		kinect.setMirror(true);

		fingers = new HandRecognize(this, 640, 480);

		fingers.setMeltFactor(100);

	}

	public void draw() {
		/**
		 * Equals KinectEvents
		 */
		kinect.update();
		PImage depthImage = kinect.depthImage();
		image(depthImage, 0, 0);
		/**
		 * Add KinectEvents
		 */
		fingers.setThreshold(threshold);
		int[] depthMap = kinect.depthMap();
		fingers.update(depthMap);
		/**
		 * Draw contour
		 */
		// drawFingerContour(fingers);
		/**
		 * draw just finger
		 */
		// drawFinger(fingers);
		/**
		 * just getnumberFingers
		 */
		//int f = fingers.getNumFingers();
		//System.out.println(f);
		/**
		 * Hand Open
		 */
		System.out.println(handOpen());
		/**
		 * threshold informations
		 */
		thresholdInformation();
	}

	public boolean handOpen() {
		return (fingers.getNumFingers() > 3 && fingers.getNumFingers() < 7) ? true
				: false;
	}

	public void drawFingerContour(HandRecognize fingers) {
		stroke(0, 255, 0);
		for (int k = 0; k < fingers.getNumContours(); k++) {
			fingers.drawContour(k);
		}
	}

	public void drawFinger(HandRecognize fingers) {
		noStroke();
		fill(255, 0, 0);
		for (int i = 0; i < fingers.getNumFingers(); i++) {
			PVector position = fingers.getFinger(i);
			ellipse(position.x, position.y, 8, 8);
		}
	}

	public void thresholdInformation() {
		fill(255, 0, 0);
		text(threshold, 10, 20);
	}

	public void keyPressed() {
		if (key == '-') {
			threshold -= 10;
		}

		if (key == '=') {
			threshold += 10;
		}
	}
}
