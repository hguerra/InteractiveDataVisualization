package br.com.inpe.kinect.view;

import javax.swing.JInternalFrame;

/**
 * This class convert the PApplet to JInternalFrame.
 * 
 * @author Heitor Guerra Carneiro.
 * @version 1.0
 * @since February 2015.
 * 
 */
public class KinectInternalFrame extends JInternalFrame {
	private KinectEvents kinect;

	public KinectInternalFrame() {
		kinect = new KinectEvents();
		getContentPane().add(kinect);
		kinect.init();
		pack();
		setVisible(true);
	}
}
