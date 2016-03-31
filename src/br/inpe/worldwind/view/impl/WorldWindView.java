package br.inpe.worldwind.view.impl;

import java.util.List;

import javax.swing.JFrame;

import br.inpe.triangle.conf.Data;
import br.inpe.worldwind.controller.ShapefileController;
import br.inpe.worldwind.defaultcontroller.ShapefileLayer;
import br.inpe.worldwind.view.controllers.ManagerSetupController;
import gov.nasa.worldwind.BasicModel;
import gov.nasa.worldwind.awt.WorldWindowGLCanvas;
import gov.nasa.worldwind.formats.shapefile.Shapefile;

public class WorldWindView extends JFrame {
	private static final ManagerSetupController MANAGER = ManagerSetupController.getInstance();
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
		dataset.forEach(data -> {
			draw(data);
		});
		shpController.asyncDraw();
	}

	private void draw(Data data) {
		try {
			Shapefile shp = ShapefileController.createShapefile(data.getFilepath());
			if (shp == null)
				return;
			shpController.addShapefile(data.getTitle(), shp, data.getAwtColors());
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
