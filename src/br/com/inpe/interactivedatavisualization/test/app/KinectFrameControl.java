package br.com.inpe.interactivedatavisualization.test.app;

import javax.swing.JInternalFrame;

public class KinectFrameControl extends JInternalFrame{

	private KinectController kinect;
	
	public KinectFrameControl(){
		kinect = new KinectController();
		getContentPane().add(kinect);
		
		kinect.init();
		//pack();
		setVisible(true);
	}
}