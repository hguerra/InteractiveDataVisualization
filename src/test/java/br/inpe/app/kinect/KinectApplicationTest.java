package br.inpe.app.kinect;

import br.com.kinect4j.device.DeviceConfig;
import com.primesense.nite.UserTracker;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class KinectApplicationTest {
	private KinectApplicationViewTest skeletonTracker;
	private JFrame frame;
	private boolean isRunning = true;

	public KinectApplicationTest(UserTracker userTracker) {
		this.skeletonTracker = new KinectApplicationViewTest(userTracker);
		frame = new JFrame("Kinect View");
		// register to closing event
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				isRunning = false;
			}
		});
		frame.setSize(800, 600);
		frame.add("Center", skeletonTracker);
		frame.setVisible(true);
	}

	void run() {
		while (isRunning) {
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.frame.dispose();
	}

	public static void main(String[] args) {
		DeviceConfig kinect = DeviceConfig.getInstance();

		if (!kinect.isDeviceConnected()) {
			JOptionPane.showMessageDialog(null, "No device is connected", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		kinect.startFirstDevice();

		UserTracker userTracker = UserTracker.create();

		KinectApplicationTest app = new KinectApplicationTest(userTracker);
		app.run();
	}
}
