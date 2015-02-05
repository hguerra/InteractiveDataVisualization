package br.com.inpe.interactivedatavisualization.kinect;

/**
 * RUN this class like Java Applet.
 * 
 * @author Heitor Guerra Carneiro.
 * @version 1.0
 * @since February 2015.
 * 
 */
public class AppKinect extends KinectEvent {

	public AppKinect() {
		super();
	}

	public void setup() {
		simpleOpenNiSetup();
	}

	public void draw() {

		simpleOpenNiDraw();

	}

}
