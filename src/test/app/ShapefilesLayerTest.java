package test.app;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;

import br.inpe.message.properties.DefaultColors;
import br.inpe.worldwind.layer.ShapefilesLayer;
import gov.nasa.worldwind.BasicModel;
import gov.nasa.worldwind.awt.WorldWindowGLCanvas;

public class ShapefilesLayerTest extends JFrame {
	private static final long serialVersionUID = 1L;
	public static final String FILE_PATH_BRAZIL_REGIOES = "shapefiles/brasil/regioes_2010/regioes_2010.shp"; // UFEBRASIL
	public static final String FILE_PATH_BRAZIL_ESTADOS = "shapefiles/brasil/estados_2010/estados_2010.shp";
	public static final String FILE_PATH_BRAZIL_MUNICIPIOS = "shapefiles/brasil/municipios_2010/municipios_2010.shp";
	public static final String FILE_PATH_USA = "shapefiles/usa/state_bounds.shp";
	public static final String FILE_PATH_WORLD = "shapefiles/world/TM_WORLD_BORDERS_SIMPL-0.2.shp";
	//Modelos
	public static final String FILE_PATH_DADOS_ANA_PART_1 = "data/LuccME/Triangle_SceB_novaproj_part1.shp";
	public static final String FILE_PATH_DADOS_ANA_PART_2 = "data/LuccME/Triangle_SceB_novaproj_part2.shp";
	public static final String FILE_PATH_MODELOS = "data/vegtype-inland/shapefile/";
	public static final String VEGTYPE_2000 = FILE_PATH_MODELOS+"vegtype_2000.shp";
	public static final String VEGTYPE_2005 = FILE_PATH_MODELOS+"vegtype_2005.shp";
	public static final String VEGTYPE_2010 = FILE_PATH_MODELOS+"vegtype_2010.shp";
	public static final String VEGTYPE_2015 = FILE_PATH_MODELOS+"vegtype_2015.shp";
	public static final String VEGTYPE_2020 = FILE_PATH_MODELOS+"vegtype_2020.shp";
	public static final String VEGTYPE_2025 = FILE_PATH_MODELOS+"vegtype_2025.shp";
	public static final String VEGTYPE_2030 = FILE_PATH_MODELOS+"vegtype_2030.shp";
	public static final String VEGTYPE_2035 = FILE_PATH_MODELOS+"vegtype_2035.shp";
	public static final String VEGTYPE_2040 = FILE_PATH_MODELOS+"vegtype_2040.shp";
	public static final String VEGTYPE_2045 = FILE_PATH_MODELOS+"vegtype_2045.shp";
	public static final String VEGTYPE_2050 = FILE_PATH_MODELOS+"vegtype_2050.shp";
	// Gdal convert
	public static final String FILE_PATH_GDAL_TRANSFORM = "data/gdal/";
	public static final String VEGTYPE_2000_GDAL = FILE_PATH_GDAL_TRANSFORM+"fromTiff.shp";

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
		 * Inserindo modelos
		 */
//		shapefile.insertRenderableLayer(VEGTYPE_2000_GDAL,
//				DefaultColors.getOriginalColors1());
	
//		shapefile.insertRenderableLayer(VEGTYPE_2005,
//		DefaultColors.getOriginalColors2());

//		Map<Double, Color> colors = shapefile.getColorScale(
//				VEGTYPE_2005, DefaultColors.getOriginalColors());
//		printMapColors(colors);
		
		/**
		 * arrumar
		 */
//		shapefile.insertRenderableLayer(VEGTYPE_2000,
//				DefaultColors.getOriginalColors1(), DefaultColors.getOriginalColors2());
		
		/**
		 * Informacao modelos
		 */
		//shapefile.printShapefileInfo(FILE_PATH_DADOS_ANA_PART_1, "attr", false);
		
//		shapefile.insertRenderableLayer(FILE_PATH_DADOS_ANA_PART_1, Color.red, null);
//		shapefile.insertRenderableLayer(FILE_PATH_DADOS_ANA_PART_2, Color.red, null);
		
		shapefile.printShapefileInfo(VEGTYPE_2000_GDAL, "attr", true);
	}

	void printMapColors(Map<Double, Color> colors) {
		for (Map.Entry<Double, Color> c : colors.entrySet()) {
			System.out.println("Attr: " + c.getKey() + ":" + c.getValue());
		}
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
