package br.inpe.triangle.app;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
import br.inpe.triangle.data.Data;
import br.inpe.triangle.data.DataSource;
import br.inpe.triangle.defaultproperties.DefaultDataSource;
import br.inpe.triangle.gdal.GeoFormat;
import br.inpe.triangle.kinect.controller.ForwardTimeController;
import br.inpe.triangle.kinect.controller.NextDataController;
import br.inpe.triangle.kinect.controller.PanController;
import br.inpe.triangle.kinect.controller.PreviousDataController;
import br.inpe.triangle.kinect.controller.RewingTimeController;
import br.inpe.triangle.kinect.controller.ZoomIn;
import br.inpe.triangle.kinect.controller.ZoomOut;
import br.inpe.triangle.utils.SkeletonInfoPrinter;
import gov.nasa.worldwind.awt.WorldWindowGLCanvas;

public class KinectApplicationView extends Kinect4jView {
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

	private WorldWindowGLCanvas canvas;

	public void setCanvas(WorldWindowGLCanvas canvas) {
		this.canvas = canvas;
		SimpleScenarioControllerImpl canvasController = new SimpleScenarioControllerImpl(canvas);

		DataSource datasource = DefaultDataSource.getInstance().createDefaultDataSource();
		List<Data> dataset = datasource.getDataSet().entrySet().stream().map(Map.Entry::getValue)
				.collect(Collectors.toList());
		Collections.sort(dataset);

		for (Data d : dataset) {
			canvasController.addData(d);
		}

		Data data = new Data();
		data.setTitle("regioes");
		data.setReference("IBGE, 2010");
		data.setFormat(GeoFormat.SHAPEFILE);
		data.setFilepath("data/regioes_2010/regioes_2010.shp");
		Map<Object, String> colors = new HashMap<>();
		colors.put("SE", "#7fc97f");
		colors.put("S", "#beaed4");
		colors.put("NE", "#fdc086");
		colors.put("CO", "#ffff99");
		colors.put("N", "#386cb0");
		data.setColors(colors);
		data.setColumn("sigla");
		data.setDate("2010");
		canvasController.addData(data);

		canvasController.draw();

		pan = new PanController(skeleton, canvasController);
		Controller zoomInController = new ZoomIn(canvasController);
		Controller zoomOutController = new ZoomOut(canvasController);
		Controller forwarTimeController = new ForwardTimeController(canvasController);
		Controller rewingTimeController = new RewingTimeController(canvasController);
		Controller nextDataController = new NextDataController(canvasController);
		Controller previousDataController = new PreviousDataController(canvasController);
		gestureControllers.put(DefaultGestureName.ZOOM_IN, zoomInController);
		gestureControllers.put(DefaultGestureName.ZOOM_OUT, zoomOutController);
		gestureControllers.put(DefaultGestureName.SWIPE_LEFT_TO_RIGHT, forwarTimeController);
		gestureControllers.put(DefaultGestureName.SWIPE_RIGHT_TO_LEFT, rewingTimeController);
		gestureControllers.put(DefaultGestureName.SWIPE_UP, nextDataController);
		gestureControllers.put(DefaultGestureName.SWIPE_DOWN, previousDataController);
	}

	/**
	 * Constructor
	 *
	 * @param tracker
	 *            : UserTracker
	 */
	public KinectApplicationView(UserTracker tracker) {
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
		// if (gestureToggleButton) {
		// gestureDetector.updateAllGestures(user);
		// }
		if (canvas != null) {
			pan.moveMap(user);
			canvas.redraw();
		}
		gestureDetector.updateAllGestures(user);
	}

	@Override
	public <T extends Enum<T>> void updateGestureName(T name) {
		if (gestureControllers.containsKey(name) && canvas != null) {
			gestureControllers.get(name).kinectActionPerformed();
			canvas.redraw();
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
}
