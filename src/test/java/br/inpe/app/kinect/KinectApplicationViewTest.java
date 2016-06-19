package br.inpe.app.kinect;

import br.com.kinect4j.controller.*;
import br.com.kinect4j.controller.movements.HandsUp;
import br.com.kinect4j.engine.core.GestureController;
import br.com.kinect4j.engine.core.PoseController;
import br.com.kinect4j.view.Kinect4jView;
import br.com.kinect4j.view.UserTrackingConfig;
import br.inpe.app.triangle.WWJSceneController;
import br.inpe.app.triangle.controllers.*;
import br.inpe.kinect4j.engine.Pan;
import br.inpe.kinect4j.movements.PoseT;
import br.inpe.triangle.conf.Data;
import br.inpe.triangle.conf.DataSource;
import br.inpe.triangle.defaultproperties.DefaultDataSource;
import br.inpe.util.status.SkeletonInfoPrinter;
import com.primesense.nite.UserData;
import com.primesense.nite.UserTracker;
import gov.nasa.worldwind.awt.WorldWindowGLCanvas;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
     * Nasa World Wind Interactions
     */
    private PanController pan;
    private Map<DefaultGestureName, Controller> gestureControllers;

    private WorldWindowGLCanvas canvas;

    public void setCanvas(WorldWindowGLCanvas canvas) {
        this.canvas = canvas;
        WWJSceneController canvasController = new WWJSceneController(canvas);

        DataSource datasource = DefaultDataSource.getInstance().createDefaultDataSource();
        List<Data> dataset = datasource.getDataSet().entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList());
        Collections.sort(dataset);

        for (Data d : dataset) {
            canvasController.addData(d);
        }
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
     * @param tracker : UserTracker
     */
    public KinectApplicationViewTest(UserTracker tracker) {
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
        //if (gestureToggleButton) {
        //gestureDetector.updateAllGestures(user);
        //}
        gestureDetector.updateAllGestures(user);

//        if (canvas != null) {
//            pan.moveMap(user);
//            canvas.redraw();
//        }
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
