package br.inpe.worldwind.view.impl;

import br.inpe.triangle.conf.Data;
import br.inpe.worldwind.controller.ShapefileController;
import br.inpe.worldwind.defaultcontroller.ShapefileLayer;
import gov.nasa.worldwind.BasicModel;
import gov.nasa.worldwind.awt.WorldWindowGLCanvas;
import gov.nasa.worldwind.formats.shapefile.Shapefile;

import javax.swing.*;
import java.util.List;

public class WorldWindView extends JFrame {
    /**
     * World Wind basic
     */
    private static final long serialVersionUID = 1L;
    private WorldWindowGLCanvas wwd;
    private ShapefileController shpController;
    private List<Data> dataset;

    public WorldWindView(List<Data> dataset) {
        this.dataset = dataset;
        worldWindConfig();
        controllersConfig();
    }

    private final void controllersConfig() {
        this.shpController = new ShapefileLayer(wwd);
        drawViewDataset();
    }

    private void drawViewDataset() {
        dataset.forEach(this::draw);
        shpController.asyncDraw();
    }

    private void draw(Data data) {
        try {
            Shapefile shp = ShapefileController.createShapefile(data.getFilepath());
            if (shp == null)
                return;
            shpController.addShapefile(data.getTitle(), data.getColumn(), shp, data.getAwtColors());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * WorldWind config
     */
    private final void worldWindConfig() {
        wwd = new WorldWindowGLCanvas();
        wwd.setPreferredSize(new java.awt.Dimension(1200, 800));
        this.getContentPane().add(wwd, java.awt.BorderLayout.CENTER);
        wwd.setModel(new BasicModel());
    }

    public static void run(List<Data> dataset) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new WorldWindView(dataset);
                frame.pack();
                frame.setVisible(true);
            }
        });
    }

    /**
     * Setters
     *
     * @param dataset
     */
    public void setDataset(List<Data> dataset) {
        // TODO Auto-generated method stub

    }
}
