package br.com.inpe.kinect.model;

public class SwitchTracker {
	private static boolean POSTURE = true;
	private static boolean GESTURE = true;
	private static boolean PAN = true;

	public static synchronized void allTurnOn() {
		POSTURE = true;
		GESTURE = true;
		PAN = true;
	}

	public static synchronized void gestureTurnOn() {
		POSTURE = false;
		PAN = false;
	}

	public static synchronized void postureTurnOn() {
		GESTURE = false;
		PAN = false;
	}

	public static synchronized void panTurnOn() {
		GESTURE = false;
		POSTURE = false;
	}

	public static boolean isPOSTURE() {
		return POSTURE;
	}

	public static boolean isGESTURE() {
		return GESTURE;
	}

	public static boolean isPAN() {
		return PAN;
	}
}
