package br.inpe.app.triangle.internalframe;

import gov.nasa.worldwind.BasicModel;
import gov.nasa.worldwind.awt.WorldWindowGLCanvas;

import javax.swing.*;
import java.awt.*;

/**
 * @author Heitor
 * @since 01/06/2016
 */
public class WWJInternalFrame extends JInternalFrame {
    private Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
    private int SCREEN_WIDTH = (int) (screenDimension.getWidth() - 20);
    private int SCREEN_HEIGHT = (int) (screenDimension.getHeight()- 20);
    private WorldWindowGLCanvas canvas;

    public WWJInternalFrame() {
        canvas = new WorldWindowGLCanvas();
        canvas.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        getContentPane().add(canvas, java.awt.BorderLayout.CENTER);
        canvas.setModel(new BasicModel());
    }

    public WorldWindowGLCanvas getCanvas() {
        return canvas;
    }
}
