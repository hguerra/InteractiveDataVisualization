package br.inpe.triangle.app2;

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
import br.inpe.triangle.kinect.controller.DataBackwardController;
import br.inpe.triangle.kinect.controller.DataForwardController;
import br.inpe.triangle.kinect.controller.PanController;
import br.inpe.triangle.kinect.controller.YearBackwardController;
import br.inpe.triangle.kinect.controller.YearForwardController;
import br.inpe.triangle.kinect.controller.ZoomIn;
import br.inpe.triangle.kinect.controller.ZoomOut;
import br.inpe.triangle.utils.SkeletonInfoPrinter;
import br.inpe.triangle.wwj.layer.WorldWindController;

public class Kinect4jViewImpl extends Kinect4jView {
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
	 * Toggle button to disable gesture detector
	 */
	private boolean gestureToggleButton = false;

	/**
	 * Nasa World Wind Interactions
	 */
	private PanController pan;
	private Map<DefaultGestureName, Controller> gestureControllers;
	private WorldWindController worldWindController;
	private boolean worldWindActive = false;

	/**
	 * Constructor
	 *
	 * @param tracker
	 *            : UserTracker
	 */
	public Kinect4jViewImpl(UserTracker tracker) {
		super(tracker);
		this.gestureControllers = new HashMap<>();
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
		SkeletonInfoPrinter.printPercentage(DefaultPoseName.HANDS_UP,
				poseDetector.getProgressPercentage(DefaultPoseName.HANDS_UP));
		/**
		 * GestureDetector
		 */
		if (gestureToggleButton) {
			gestureDetector.updateAllGestures(user);
		}
		if (worldWindActive) {
			pan.moveMap(user);
			worldWindController.redraw();
		}
	}

	@Override
	public <T extends Enum<T>> void updateGestureName(T name) {
		if (gestureControllers.containsKey(name) && worldWindActive) {
			gestureControllers.get(name).kinectActionPerformed();
			worldWindController.redraw();
		} else
			System.err.println(name);
	}

	@Override
	public <T extends Enum<T>> void updatePoseName(T name) {
		System.err.println(name);
	}

	public void switchGestureTraking() {
		this.gestureToggleButton = !gestureToggleButton;
	}

	public void addController(DefaultGestureName name, Controller controller) {
		this.gestureControllers.put(name, controller);
	}

	public void setWorldWindController(WorldWindController worldWindController) {
		this.worldWindController = worldWindController;
		pan = new PanController(skeleton, worldWindController);
		Controller zoomInController = new ZoomIn(worldWindController);
		Controller zoomOutController = new ZoomOut(worldWindController);
		Controller forwarTimeController = new YearForwardController();
		Controller rewingTimeController = new YearBackwardController();
		Controller nextDataController = new DataForwardController();
		Controller previousDataController = new DataBackwardController();
		gestureControllers.put(DefaultGestureName.ZOOM_IN, zoomInController);
		gestureControllers.put(DefaultGestureName.ZOOM_OUT, zoomOutController);
		gestureControllers.put(DefaultGestureName.SWIPE_LEFT_TO_RIGHT, forwarTimeController);
		gestureControllers.put(DefaultGestureName.SWIPE_RIGHT_TO_LEFT, rewingTimeController);
		gestureControllers.put(DefaultGestureName.SWIPE_UP, nextDataController);
		gestureControllers.put(DefaultGestureName.SWIPE_DOWN, previousDataController);
		this.worldWindActive = true;
	}
}
