package br.inpe.app.triangle.internalframe;

import gov.nasa.worldwind.awt.WorldWindowGLCanvas;

import javax.swing.*;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import java.awt.*;

/**
 * @author Heitor
 * @since 01/06/2016
 */
public class TriangleInternalFrame extends JFrame {
    private JDesktopPane DPane;
    private WorldWindowGLCanvas canvas;
    private WWJInternalFrame nasa;
    private KinectInternalFrame kinect;

    public TriangleInternalFrame() {
        Container framePane = getContentPane();
        framePane.setLayout(new BorderLayout());

        nasa = new WWJInternalFrame();
        BasicInternalFrameUI nasaTitlePane = (BasicInternalFrameUI) nasa
                .getUI();
        nasaTitlePane.setNorthPane(null);
        nasa.setBorder(null);
        nasa.getContentPane();
        nasa.setBounds(0, 0, 1300, 760);
        nasa.setVisible(true);

        canvas = nasa.getCanvas();

        kinect = new KinectInternalFrame(canvas);
        BasicInternalFrameUI kinectTitlePane = (BasicInternalFrameUI) kinect
                .getUI();
        kinectTitlePane.setNorthPane(null);
        kinect.getContentPane();
        kinect.setBounds(5, 400, 300, 220);
        kinect.setVisible(true);

        new Thread(kinect).start();

        SwingUtilities.invokeLater(()->{
            DPane = new JDesktopPane();
            DPane.add(kinect);
            DPane.add(nasa);
            DPane.setLayer(kinect, JLayeredPane.DEFAULT_LAYER);
            DPane.setLayer(kinect, JLayeredPane.DRAG_LAYER);
            framePane.add(BorderLayout.CENTER, DPane);
        });

    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TriangleInternalFrame app = new TriangleInternalFrame();
            app.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            app.setSize(1300, 760);
            app.setResizable(false);
            app.setVisible(true);
        });
    }

}
