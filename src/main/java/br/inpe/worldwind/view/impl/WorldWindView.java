package br.inpe.worldwind.view.impl;

import br.inpe.triangle.conf.Data;
import br.inpe.worldwind.controller.LayerController;
import br.inpe.worldwind.controller.ShapefileController;
import br.inpe.worldwind.defaultcontroller.AnnotationLayer;
import br.inpe.worldwind.defaultcontroller.ScreenAnnotationLayer;
import br.inpe.worldwind.defaultcontroller.ShapefileLayer;
import gov.nasa.worldwind.BasicModel;
import gov.nasa.worldwind.awt.WorldWindowGLCanvas;
import gov.nasa.worldwind.formats.shapefile.Shapefile;

import javax.swing.*;
import java.awt.*;
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
        screenAnnotationController();
        shpControllersConfig();
    }

    private void screenAnnotationController() {
        LayerController screenAnnotation = new ScreenAnnotationLayer(wwd, 780, 530, "images/ccst-novo2.png", new Insets(0, 40, 0, 0),
                new Dimension(265, 200));
        screenAnnotation.asyncDraw();
    }

    private void shpControllersConfig() {
        this.shpController = new ShapefileLayer(wwd);
        drawViewDataset();
    }

    private AnnotationLayer createAnnotationControllerFromData(String year, Data data) {
        return new AnnotationLayer(wwd, data, year);
    }

    private void drawViewDataset() {
        dataset.forEach(this::draw);
        for (Data data : dataset) {
            draw(data);
            AnnotationLayer annotationLayer = createAnnotationControllerFromData(data.getDate(), data);
            annotationLayer.asyncDraw();
        }
        shpController.asyncDraw();
    }

    private void draw(Data data) {
        try {
            Shapefile shp = ShapefileController.createShapefile(data.getFilepath());
            shpController.addShapefile(data.getTitle(), data.getColumn(), shp, data.getAwtColors());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * WorldWind config
     */
    private void worldWindConfig() {
        wwd = new WorldWindowGLCanvas();
        wwd.setPreferredSize(new java.awt.Dimension(1200, 750));
        this.getContentPane().add(wwd, java.awt.BorderLayout.CENTER);
        wwd.setModel(new BasicModel());
    }

    public static void run(List<Data> dataset) {
        java.awt.EventQueue.invokeLater(() -> {
            JFrame frame = new WorldWindView(dataset);
            frame.pack();
            frame.setVisible(true);
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
