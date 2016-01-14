package test.app;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.primesense.nite.UserTracker;

import br.com.kinect4j.device.DeviceConfig;

public class Application {
	private ApplicationView skeletonTracker;
	private JFrame frame;
	private boolean isRunning = true;

	public Application(UserTracker userTracker) {
		this.skeletonTracker = new ApplicationView(userTracker);
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

		Application app = new Application(userTracker);
		app.run();
	}
}
