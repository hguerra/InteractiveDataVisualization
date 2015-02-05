package br.com.inpe.interactivedatavisualization.kinect;

import javax.swing.JInternalFrame;

import br.com.inpe.interactivedatavisualization.test.failure.KinectEvents;

/**
 * This class convert the PApplet to JInternalFrame.
 * 
 * @author Heitor Guerra Carneiro.
 * @version 1.0
 * @since February 2015.
 * 
 */
public class KinectFrame extends JInternalFrame{
	private KinectEvent kinect;
	
	public KinectFrame(){
		kinect = new KinectEvent();
		getContentPane().add(kinect);
		kinect.init();
		setVisible(true);
	}
}
