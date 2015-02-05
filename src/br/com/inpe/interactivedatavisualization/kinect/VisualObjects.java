package br.com.inpe.interactivedatavisualization.kinect;

import processing.core.PApplet;

/**
 * This class create the all objects on screen.
 * 
 * @author Heitor Guerra Carneiro.
 * @version 1.0
 * @since February 2015.
 * 
 */
public class VisualObjects extends PApplet {
	public VisualObjects() {

	}

	public void staticObjects() {
		buttonEllipse(50, 50, 50, 0);
	}

	public void updateObjects(float w, float h, int size, int option) {
		buttonEllipse(w, h, size, option);
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

}
