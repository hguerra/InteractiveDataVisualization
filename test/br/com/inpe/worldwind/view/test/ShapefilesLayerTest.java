package br.com.inpe.worldwind.view.test;

import gov.nasa.worldwind.BasicModel;
import gov.nasa.worldwind.awt.WorldWindowGLCanvas;
import gov.nasa.worldwind.layers.Layer;
import gov.nasa.worldwindx.examples.ApplicationTemplate;

import java.util.List;

import javax.swing.JFrame;

import br.com.inpe.worldwind.model.ShapefilesLayer;

public class ShapefilesLayerTest extends JFrame {
	private static final long serialVersionUID = 1L;
	public static final String FILE_PATH = "shapefiles/state_bounds.shp";
	private WorldWindowGLCanvas wwd;
	private ShapefilesLayer shapefile;
	private List<Layer> layers;

	public ShapefilesLayerTest() {
		wwd = new WorldWindowGLCanvas();
		wwd.setPreferredSize(new java.awt.Dimension(1000, 800));
		this.getContentPane().add(wwd, java.awt.BorderLayout.CENTER);
		wwd.setModel(new BasicModel());

		shapefile = new ShapefilesLayer();

		layers = shapefile.makeShapefileLayers(FILE_PATH);

		for (Layer l : layers) {
			ApplicationTemplate.insertBeforePlacenames(wwd, l);
		}
		wwd.redraw();
	}

	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new ShapefilesLayerTest();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.pack();
				frame.setVisible(true);
			}
		});
	}
}
