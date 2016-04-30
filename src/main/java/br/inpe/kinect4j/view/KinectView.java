package br.inpe.kinect4j.view;

import java.util.HashMap;
import java.util.Map;

import com.primesense.nite.UserData;
import com.primesense.nite.UserTracker;

import br.com.kinect4j.controller.Controller;
import br.com.kinect4j.controller.DefaultGestureController;
import br.com.kinect4j.controller.DefaultGestureName;
import br.com.kinect4j.controller.DefaultPoseController;
import br.com.kinect4j.controller.DefaultPoseName;
import br.com.kinect4j.controller.movements.HandsUp;
import br.com.kinect4j.engine.core.GestureController;
import br.com.kinect4j.engine.core.PoseController;
import br.com.kinect4j.view.Kinect4jView;
import br.com.kinect4j.view.UserTrackingConfig;
import br.inpe.kinect4j.controller.HandsUpController;
import br.inpe.kinect4j.controller.SwipeLeftToRightController;
import br.inpe.kinect4j.controller.SwipeRightToLeftController;
import br.inpe.kinect4j.controller.WaveLeftController;
import br.inpe.kinect4j.controller.WaveRightController;
import br.inpe.kinect4j.controller.ZoomInController;
import br.inpe.kinect4j.controller.ZoomOutController;
import br.inpe.kinect4j.engine.Pan;
import br.inpe.util.status.SkeletonInfoPrinter;
import br.inpe.worldwind.controller.ScenarioController;
import br.inpe.worldwind.layer.triangle.VegetationScenarioLayer.VegetationScenarioApp;
import gov.nasa.worldwind.awt.WorldWindowGLCanvas;

public class KinectView extends Kinect4jView {
	private static final long serialVersionUID = 1L;
	/*
	 * Pose Controller
	 */

	private PoseController poseDetector;
	/**
	 * GestureController
	 */

	private GestureController gestureDetector;

	/**
	 * MVC Controllers
	 */
	private Map<DefaultPoseName, Controller> poseControllers;
	private Map<DefaultGestureName, Controller> gestureControllers;

	/**
	 * Toggle button to disable gesture detector
	 */

	private boolean gestureToggleButton = false;
	/**
	 * NASA World Wind
	 */
	private Pan pan;
	private WorldWindowGLCanvas canvas;
	private VegetationScenarioApp virtualGlobe;
	private ScenarioController globeController;

	/**
	 * Constructor
	 * 
	 * @param UserTracke
	 *            tracker
	 */
	public KinectView(UserTracker tracker, VegetationScenarioApp vegetationScenarioApp) {
		super(tracker);
		/**
		 * NASA World Wind
		 */
		this.virtualGlobe = vegetationScenarioApp;
		this.globeController = virtualGlobe.getController();
		this.canvas = virtualGlobe.getWwd();
	}

	@Override
	public void setup(UserTracker tracker) {
		/**
		 * Pose Controller
		 */
		poseDetector = new DefaultPoseController<DefaultPoseName>();
		poseDetector.addPose(DefaultPoseName.HANDS_UP, 100, new HandsUp(skeleton), this);
		/**
		 * Gesture Controller
		 */
		gestureDetector = new DefaultGestureController<DefaultGestureName>(skeleton, this);

		this.poseControllers = createPoseControllers();
		this.gestureControllers = createGestureControllers();
		/**
		 * NASA events
		 */
		this.pan = new Pan(skeleton, this);
	}

	@Override
	public UserTrackingConfig getUserTrackingConfig() {
		return UserTrackingConfig.ASYNCHRONOUS;
	}

	@Override
	public void userTracking(UserData user) {
		/**
		 * Advanced poseCheck
		 */
		poseDetector.updateAllPoses(user);
		// info
		SkeletonInfoPrinter.printPercentage(poseDetector.getProgressPercentage(DefaultPoseName.HANDS_UP));
		/**
		 * GestureDetector
		 */
		if (gestureToggleButton) {
			gestureDetector.updateAllGestures(user);
		} else {
			pan.moveMap(user);
		}
	}

	@Override
	public <T extends Enum<T>> void updateGestureName(T name) {
		gestureControllers.get(name).kinectActionPerformed();

	}

	@Override
	public <T extends Enum<T>> void updatePoseName(T name) {
		poseControllers.get(name).kinectActionPerformed();

	}

	public void switchGestureTraking() {
		this.gestureToggleButton = !gestureToggleButton;

	}

	public Map<DefaultPoseName, Controller> createPoseControllers() {
		Map<DefaultPoseName, Controller> poses = new HashMap<>();

		Controller handsUp = new HandsUpController(this);

		poses.put(DefaultPoseName.HANDS_UP, handsUp);

		return poses;
	}

	public Map<DefaultGestureName, Controller> createGestureControllers() {
		Map<DefaultGestureName, Controller> gestures = new HashMap<>();

		Controller swipeLeftToRight = new SwipeLeftToRightController(this);
		Controller swipeRightToLeft = new SwipeRightToLeftController(this);
		Controller waveLeft = new WaveLeftController(this);
		Controller waveRight = new WaveRightController(this);
		Controller zoomIn = new ZoomInController(this);
		Controller zoomOut = new ZoomOutController(this);

		gestures.put(DefaultGestureName.SWIPE_LEFT_TO_RIGHT, swipeLeftToRight);
		gestures.put(DefaultGestureName.SWIPE_RIGHT_TO_LEFT, swipeRightToLeft);
		gestures.put(DefaultGestureName.WAVE_LEFT, waveLeft);
		gestures.put(DefaultGestureName.WAVE_RIGHT, waveRight);
		gestures.put(DefaultGestureName.ZOOM_IN, zoomIn);
		gestures.put(DefaultGestureName.ZOOM_OUT, zoomOut);

		return gestures;
	}

	public void redrawWorldWind() {
		this.canvas.redraw();
	}

	public ScenarioController getGlobeController() {
		return globeController;
	}

}