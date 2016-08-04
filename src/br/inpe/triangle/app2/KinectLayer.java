package br.inpe.triangle.app2;

import com.primesense.nite.UserTracker;

public class KinectLayer implements Runnable {
	private Kinect4jViewImpl kinect;
	private boolean isRunning = true;

	public KinectLayer(UserTracker userTracker) {
		this.kinect = new Kinect4jViewImpl(userTracker);
	}

	public Kinect4jViewImpl get() {
		return kinect;
	}

	@Override
	public void run() {
		while (isRunning) {
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			kinect.repaint();
		}
	}

}
