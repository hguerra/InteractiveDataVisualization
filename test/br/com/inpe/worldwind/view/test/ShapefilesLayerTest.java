package br.com.inpe.worldwind.view.test;

import gov.nasa.worldwind.BasicModel;
import gov.nasa.worldwind.awt.WorldWindowGLCanvas;

import java.awt.Color;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

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
	private List<String> files = Arrays.asList(FILE_PATH_BRAZIL_REGIOES,
			FILE_PATH_BRAZIL_ESTADOS, FILE_PATH_BRAZIL_MUNICIPIOS,
			FILE_PATH_USA, FILE_PATH_WORLD);
	private int iteratorInsertLayer = 0;
	private int iteratorRemoveLayer = files.size() > 0 ? files.size() - 1 : 0;

	public ShapefilesLayerTest() {
		wwd = new WorldWindowGLCanvas();
		wwd.setPreferredSize(new java.awt.Dimension(1000, 800));
		this.getContentPane().add(wwd, java.awt.BorderLayout.CENTER);
		wwd.setModel(new BasicModel());
		shapefile = new ShapefilesLayer(wwd);
		/**
		 * start Timer
		 */
		//shapefile.insertLayerBeforeCompass(FILE_PATH_BRAZIL_ESTADOS);
		//shapefile.insertRenderableLayer(FILE_PATH_BRAZIL_ESTADOS, Color.RED, Color.BLUE);
		//shapefile.insertRenderableLayerBeforeCompass(FILE_PATH_BRAZIL_ESTADOS);
		timerInsertLayer();
	}

	public void timerInsertLayer() {
		final long initialDelay = 1000;
		final Timer t = new Timer();
		t.schedule(new TimerTask() {
			@Override
			public void run() {
				if (iteratorInsertLayer < files.size()) {
					executeInsertLayer(iteratorInsertLayer);
					iteratorInsertLayer++;
				} else {
					/**
					 * After cancel task
					 */
					t.cancel();
					timerRemoveLayer();
				}
			}
		}, initialDelay, 5000);
	}

	public void timerRemoveLayer() {
		final long initialDelay = 1000;
		final Timer t = new Timer();
		t.schedule(new TimerTask() {
			@Override
			public void run() {
				if (iteratorRemoveLayer >= 0) {
					executeRemoveLayer(iteratorRemoveLayer);
					iteratorRemoveLayer--;
				} else {
					/**
					 * After cancel task
					 */
					t.cancel();
				}
			}
		}, initialDelay, 5000);
	}

	/**
	 * Run in other Threads
	 * 
	 * @param i
	 */
	public void executeInsertLayer(int i) {
		// File name
		System.out.println(files.get(i));
		// Insert layer with default name, for remove this, need remove all
		// elements

		// shapefile.insertLayer(files.get(i));
		// Insert layer with specific name, can remove by name
		shapefile.insertLayerBeforeCompass(files.get(i), "arquivo-" + i, true);
	}

	public void executeRemoveLayer(int i) {
		System.out.println(files.get(i));
		// remove all layers add
		// removeLayer();
		// remove specific layer by name
		shapefile.removeLayer("arquivo-" + i);
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
