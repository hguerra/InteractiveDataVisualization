package br.com.inpe.interactivedatavisualization.test.failure;

import javax.swing.JInternalFrame;

/**
 * This class convert the PApplet to JInternalFrame.
 * 
 * @author Heitor Guerra Carneiro.
 * @version 1.0
 * @since February 2015.
 * 
 */
public class KinectFrame extends JInternalFrame{
	private KinectEvents kinect;
	
	public KinectFrame(){
		kinect = new KinectEvents();
		getContentPane().add(kinect);
		kinect.init();
		setVisible(true);
	}
}
