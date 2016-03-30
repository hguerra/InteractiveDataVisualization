package test.app;

import com.primesense.nite.UserData;
import com.primesense.nite.UserTracker;

import br.com.kinect4j.controller.DefaultGestureController;
import br.com.kinect4j.controller.DefaultGestureName;
import br.com.kinect4j.controller.DefaultPoseController;
import br.com.kinect4j.controller.DefaultPoseName;
import br.com.kinect4j.controller.movements.HandsUp;
import br.com.kinect4j.engine.core.GestureController;
import br.com.kinect4j.engine.core.PoseController;
import br.com.kinect4j.view.Kinect4jView;
import br.com.kinect4j.view.UserTrackingConfig;
import br.inpe.kinect4j.movements.PoseT;
import br.inpe.util.status.SkeletonInfoPrinter;

public class KinectApplicationViewTest extends Kinect4jView {
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
	 * Constructor
	 * 
	 * @param UserTracke
	 *            tracker
	 */
	public KinectApplicationViewTest(UserTracker tracker) {
		super(tracker);
	}

	@Override
	public void setup(UserTracker tracker) {
		/**
		 * Pose Controller
		 */
		poseDetector = new DefaultPoseController<DefaultPoseName>();
		poseDetector.addPose(DefaultPoseName.HANDS_UP, 100, new HandsUp(skeleton), this);
		poseDetector.addPose(DefaultPoseName.T, 100, new PoseT(skeleton), this);
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
		SkeletonInfoPrinter.printPercentage(DefaultPoseName.HANDS_UP, poseDetector.getProgressPercentage(DefaultPoseName.HANDS_UP));
		SkeletonInfoPrinter.printPercentage(DefaultPoseName.T, poseDetector.getProgressPercentage(DefaultPoseName.T));
		/**
		 * GestureDetector
		 */

		if (gestureToggleButton) {
			gestureDetector.updateAllGestures(user);
		}
	}

	@Override
	public <T extends Enum<T>> void updateGestureName(T name) {
		System.out.println(name);
	}

	@Override
	public <T extends Enum<T>> void updatePoseName(T name) {
		System.out.println(name);

	}

	public void switchGestureTraking() {
		this.gestureToggleButton = !gestureToggleButton;

	}
}
