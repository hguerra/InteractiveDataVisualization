package br.inpe.triangle.app;

import br.com.kinect4j.device.DeviceConfig;
import br.inpe.triangle.wwj.layer.LayerController;
import br.inpe.triangle.wwj.layer.impl.ScreenAnnotationLayer;

import com.primesense.nite.UserTracker;
import gov.nasa.worldwind.BasicModel;
import gov.nasa.worldwind.awt.WorldWindowGLCanvas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author Heitor
 * @since 02/06/2016
 */
public class TriangleLayeredPane extends JLayeredPane implements Runnable {
    public static final int SCREEN_HEIGHT = 768; // 768 /768
    public static final int SCREEN_WIDTH = 1366; //1024 /1366
    private WorldWindowGLCanvas canvas;
    private KinectApplicationView kinectHandler;
    private boolean isRunning = true;

    public TriangleLayeredPane() {
        this.canvas = new WorldWindowGLCanvas();
        this.kinectHandler = new KinectApplicationView(createUserTracker());
    }

    private UserTracker createUserTracker() {
        DeviceConfig kinect = DeviceConfig.getInstance();
        if (!kinect.isDeviceConnected()) {
            throw new RuntimeException("No device is connected");
        }
        kinect.startFirstDevice();
        return UserTracker.create();
    }

    private void doJLayeredPane() {
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                canvas.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
                canvas.setModel(new BasicModel());
                canvas.setBounds(0, 0, SCREEN_WIDTH + 1, SCREEN_HEIGHT + 1);

                LayerController screenAnnotation = new ScreenAnnotationLayer(canvas, 780, 530, "images/ccst-novo2.png", new Insets(0, 40, 0, 0),
                        new Dimension(265, 200));
                screenAnnotation.asyncDraw();

                add(canvas, java.awt.BorderLayout.CENTER);
                canvas.redraw();
                return null;
            }

            @Override
            protected void done() {
                kinectHandler.setBounds(15, 585, 224, 168);
                kinectHandler.setCanvas(canvas);
                add(kinectHandler, new Integer(JLayeredPane.DEFAULT_LAYER + 1));
                setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
                setBounds(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
                canvas.redraw();
            }
        };
        worker.execute();
    }

    @Override
    public void run() {
        doJLayeredPane();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TriangleLayeredPane pane = new TriangleLayeredPane();
            new Thread(pane).start();

            JFrame frame = new JFrame();
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent event) {
                    event.getWindow().setVisible(false);
                    event.getWindow().dispose();
                    System.exit(0);
                }
            });
            frame.getContentPane().add(pane, BorderLayout.CENTER);
            frame.pack();
            frame.setBounds(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);

            frame.setVisible(true);
        });
    }
}
