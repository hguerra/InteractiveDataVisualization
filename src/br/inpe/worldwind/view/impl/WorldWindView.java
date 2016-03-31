package br.inpe.worldwind.view.impl;

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

	public WorldWindView() {
		worldWindConfig();
		controllersConfig();
	}
	
	private final void controllersConfig(){
		Data data = MANAGER.getData("vegtype-gdal.shp");
		shapefileController(data);
	}
	
	private void shapefileController(Data data) {
		shpController = new ShapefileLayer(wwd);

		try {
			String filepath = data.getFilepath();

			Shapefile shp = ShapefileController.createShapefile(filepath);
			
			shpController.addShapefile(ShapefileController.getDisplayName(filepath), shp, data.getAwtColors());
			
			shpController.asyncDraw();
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

	public void run() {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new WorldWindView();
				frame.pack();
				frame.setVisible(true);
			}
		});
	}
}
