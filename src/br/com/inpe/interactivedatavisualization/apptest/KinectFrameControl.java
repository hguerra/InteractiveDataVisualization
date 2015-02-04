package br.com.inpe.interactivedatavisualization.apptest;

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