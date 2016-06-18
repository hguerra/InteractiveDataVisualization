package br.inpe.app.kinect;

import br.com.kinect4j.device.DeviceConfig;
import com.primesense.nite.UserTracker;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author Heitor
 * @since 02/06/2016
 */
public class KinectJFrame extends JFrame implements Runnable {
    private KinectApplicationViewTest kinectHandler;
    private boolean isRunning = true;

    public KinectJFrame() {
        UserTracker userTracker = createUserTracker();
        this.kinectHandler = new KinectApplicationViewTest(userTracker);
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

    private void frameEvents() {
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                isRunning = false;
            }
        });
        this.getContentPane().add(kinectHandler);
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            KinectJFrame frame = new KinectJFrame();
            frame.setSize(800, 600);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            new Thread(frame).start();
            frame.setVisible(true);
        });
    }
}
