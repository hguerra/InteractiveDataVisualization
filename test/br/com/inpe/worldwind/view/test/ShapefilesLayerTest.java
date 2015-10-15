package br.com.inpe.worldwind.view.test;

import gov.nasa.worldwind.BasicModel;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.awt.WorldWindowGLCanvas;
import gov.nasa.worldwind.layers.CompassLayer;
import gov.nasa.worldwind.layers.Layer;
import gov.nasa.worldwind.layers.LayerList;

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
	private List<Layer> layers;
	private List<String> files = Arrays.asList(FILE_PATH_BRAZIL_REGIOES,
			FILE_PATH_BRAZIL_ESTADOS, FILE_PATH_BRAZIL_MUNICIPIOS,
			FILE_PATH_USA, FILE_PATH_WORLD);
	private int iteratorInsertLayer = 0;
	private int iteratorRemoveLayer = files.size()>0? files.size()-1:0;
	
	public ShapefilesLayerTest() {
		wwd = new WorldWindowGLCanvas();
		wwd.setPreferredSize(new java.awt.Dimension(1000, 800));
		this.getContentPane().add(wwd, java.awt.BorderLayout.CENTER);
		wwd.setModel(new BasicModel());
		shapefile = new ShapefilesLayer();
		// insertLayer(FILE_PATH_BRAZIL_REGIOES);
		timerInsertLayer();
	}

	public void insertBeforeCompass(WorldWindow wwd, Layer layer) {
		// Insert the layer into the layer list just before the compass.
		int compassPosition = 0;
		LayerList layers = wwd.getModel().getLayers();
		for (Layer l : layers) {
			if (l instanceof CompassLayer)
				compassPosition = layers.indexOf(l);
		}
		layers.add(compassPosition, layer);
	}

	public void removeCompassLayer(WorldWindow wwd) {
		int compassPosition = 0;
		LayerList toRemoveLayers = wwd.getModel().getLayers();
		for (Layer l : toRemoveLayers) {
			if (l instanceof CompassLayer)
				compassPosition = toRemoveLayers.indexOf(l);
		}
		toRemoveLayers.remove(compassPosition);
	}
	public void removeTest(){
		List<Layer> toRemoveLayers = wwd.getModel().getLayers();
		for(Layer l: layers){
			toRemoveLayers.remove(l);
		}
		wwd.redraw();
	}
	public void insertLayer(String path) {
		layers = shapefile.makeShapefileLayers(path);
		for (Layer l : layers) {
			insertBeforeCompass(wwd, l);
		}
		// to use in removeTest
		//layers = null;
		wwd.redraw();
	}

	public void executeInsertLayer(int i) {
		System.out.println(files.get(i));
		insertLayer(files.get(i));
	}

	public void executeRemoveLayer(int i) {
		System.out.println(files.get(i));
		//removeCompassLayer(wwd);
		//wwd.redraw();
		// to use in removeTest
		removeTest();
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
