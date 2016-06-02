package br.inpe.app.triangle;

import br.com.kinect4j.controller.Controller;
import br.com.kinect4j.controller.DefaultGestureName;
import br.com.kinect4j.device.DeviceConfig;
import br.inpe.app.kinect.KinectApplicationViewTest;
import com.primesense.nite.UserTracker;
import gov.nasa.worldwind.BasicModel;
import gov.nasa.worldwind.awt.WorldWindowGLCanvas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author Heitor
 * @since 31/05/2016
 */
public class TriangleJFrameAppTest extends JFrame implements TriangleObservable {
    private final int SCREEN_HEIGHT = 768; // 768
    private final int SCREEN_WIDTH = 1366; //1024

    private JLayeredPane layeredPane;
    private WorldWindowGLCanvas canvas;
    private KinectApplicationViewTest kinectHandler;

    public TriangleJFrameAppTest() {
        new Thread(this::initKinectHandler).start();
        initWorldWind();
        initLayeredPane();
        frameEvent();
    }

    private void initLayeredPane() {
        layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        layeredPane.add(canvas, java.awt.BorderLayout.CENTER);
        layeredPane.setBounds(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        if (kinectHandler != null) {
            addKinectControllers();
            layeredPane.add(kinectHandler, new Integer(JLayeredPane.DEFAULT_LAYER + 1));
        }
        layeredPane.doLayout();
    }

    private void initWorldWind() {
        canvas = new WorldWindowGLCanvas();
        canvas.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        canvas.setModel(new BasicModel());
        canvas.setBounds(0, 0, SCREEN_WIDTH + 1, SCREEN_HEIGHT + 1); // +1 because without it
    }

    private void addKinectControllers() {
        Controller controller = new WWJController(canvas);
        kinectHandler.addController(DefaultGestureName.ROTATE_CLOCK, controller);
    }

    private void initKinectHandler() {
        DeviceConfig kinect = DeviceConfig.getInstance();
        if (!kinect.isDeviceConnected()) {
            JOptionPane.showMessageDialog(null, "No device is connected", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        kinect.startFirstDevice();
        UserTracker userTracker = UserTracker.create();
        kinectHandler = new KinectApplicationViewTest(userTracker);
        kinectHandler.setBounds(15, 585, 224, 168);
    }

    private void frameEvent() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                event.getWindow().setVisible(false);
                event.getWindow().dispose();
                System.exit(0);
            }
        });
        this.setUndecorated(false);//default true
        this.getContentPane().add(layeredPane, BorderLayout.CENTER);
        this.pack();
        this.setBounds(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
    }

    public static void main(String[] args) {
        TriangleJFrameAppTest app = new TriangleJFrameAppTest();
        app.setVisible(true);
    }

    @Override
    public void pan(double moveY, double moveX) {
        System.out.println("PAN!!");
    }
}
