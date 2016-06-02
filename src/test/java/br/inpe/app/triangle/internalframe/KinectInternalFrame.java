package br.inpe.app.triangle.internalframe;

import br.com.kinect4j.controller.Controller;
import br.com.kinect4j.controller.DefaultGestureName;
import br.com.kinect4j.device.DeviceConfig;
import br.inpe.app.kinect.KinectApplicationViewTest;
import br.inpe.app.triangle.WWJController;
import com.primesense.nite.UserTracker;
import gov.nasa.worldwind.awt.WorldWindowGLCanvas;

import javax.swing.*;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

/**
 * @author Heitor
 * @since 01/06/2016
 */
public class KinectInternalFrame extends JInternalFrame implements Runnable {
    private KinectApplicationViewTest kinectHandler;
    private boolean isRunning = true;

    public KinectInternalFrame(WorldWindowGLCanvas canvas) {
        UserTracker userTracker = createUserTracker();
        kinectHandler = new KinectApplicationViewTest(userTracker);
        kinectHandler.setCanvas(canvas);
        getContentPane().add(kinectHandler);
        frameEvents();
    }

    private UserTracker createUserTracker() {
        DeviceConfig kinect = DeviceConfig.getInstance();
        if (!kinect.isDeviceConnected()) {
            throw new RuntimeException("No device is connected");
        }
        kinect.startFirstDevice();
        return UserTracker.create();
    }

    private void frameEvents(){
        addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameClosing(InternalFrameEvent e) {
                isRunning = false;
            }
        });
    }
    @Override
    public void run() {
        while (isRunning) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.dispose();
    }
}
