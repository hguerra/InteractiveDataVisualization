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
	public static final String FILE_PATH_BRAZIL_REGIOES = "shapefiles/brasil/regioes_2010/regioes_2010.shp"; // UFEBRASIL
	public static final String FILE_PATH_BRAZIL_ESTADOS = "shapefiles/brasil/estados_2010/estados_2010.shp";
	public static final String FILE_PATH_BRAZIL_MUNICIPIOS = "shapefiles/brasil/municipios_2010/municipios_2010.shp";
	public static final String FILE_PATH_USA = "shapefiles/usa/state_bounds.shp";
	public static final String FILE_PATH_WORLD = "shapefiles/world/TM_WORLD_BORDERS_SIMPL-0.2.shp";
	private WorldWindowGLCanvas wwd;
	private ShapefilesLayer shapefile;
	private List<Layer> layers;

	public ShapefilesLayerTest() {
		wwd = new WorldWindowGLCanvas();
		wwd.setPreferredSize(new java.awt.Dimension(1000, 800));
		this.getContentPane().add(wwd, java.awt.BorderLayout.CENTER);
		wwd.setModel(new BasicModel());

		shapefile = new ShapefilesLayer();

		layers = shapefile.makeShapefileLayers(FILE_PATH_WORLD);

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
