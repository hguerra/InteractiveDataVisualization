package br.com.inpe.interactivedatavisualization.test.app;
import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import br.com.inpe.interactivedatavisualization.worldwind.NasaFrameControl;

public class ViewControl extends JFrame {
	private JDesktopPane DPane;
	private NasaFrameControl nasa;
	private KinectFrameControl kinect;
	
	public ViewControl() {
		InternalFrameControl();
	}

	public void InternalFrameControl() {
		Container framePane = getContentPane();
		framePane.setLayout(new BorderLayout());

		// ----------------------------------------------
		nasa = new NasaFrameControl();
		// Get the titlebar and set it to null
		BasicInternalFrameUI nasaTitlePane = (BasicInternalFrameUI) nasa
				.getUI();
		nasaTitlePane.setNorthPane(null);
		nasa.setBorder(null);
		nasa.getContentPane();
		nasa.setBounds(0, 0, 1300, 760);
		nasa.setVisible(true);

		// ----------------------------------------------
		kinect = new KinectFrameControl();
		// Get the titlebar and set it to null
		BasicInternalFrameUI kinectTitlePane = (BasicInternalFrameUI) kinect
				.getUI();
		kinectTitlePane.setNorthPane(null);
		kinect.getContentPane();
		kinect.setBounds(5, 400, 300, 220);
		kinect.setVisible(true);
		// ----------------------------------------------
		
		DPane = new JDesktopPane();
		DPane.add(kinect);
		DPane.add(nasa);
		DPane.setLayer(kinect, JLayeredPane.DEFAULT_LAYER);
		DPane.setLayer(kinect, JLayeredPane.DRAG_LAYER);
		framePane.add(BorderLayout.CENTER, DPane);
	}


	public static void main(String[] args) {
		ViewControl appViewControl = new ViewControl();
		appViewControl.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		appViewControl.setSize(1300, 760);
		appViewControl.setResizable(false);
		appViewControl.setVisible(true);
	}
}