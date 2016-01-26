package test.app;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;

import br.inpe.message.properties.DefaultColors;
import br.inpe.util.FilePathTest;
import br.inpe.worldwind.controller.GeoJSONController;
import br.inpe.worldwind.controller.LayerController;
import br.inpe.worldwind.controller.ShapefileController;
import br.inpe.worldwind.dao.GeometryRecord;
import br.inpe.worldwind.dao.JDBCDao;
import br.inpe.worldwind.dao.model.vegtype_2000;
import br.inpe.worldwind.defaultcontroller.CommentLayer;
import br.inpe.worldwind.defaultcontroller.GeoJSONLayer;
import br.inpe.worldwind.defaultcontroller.LineLayer;
import br.inpe.worldwind.defaultcontroller.PointLayer;
import br.inpe.worldwind.defaultcontroller.Polygon2DLayer;
import br.inpe.worldwind.defaultcontroller.Polygon3DLayer;
import br.inpe.worldwind.defaultcontroller.ScreenAnnotationLayer;
import br.inpe.worldwind.defaultcontroller.ShapefileLayer;
import gov.nasa.worldwind.BasicModel;
import gov.nasa.worldwind.awt.WorldWindowGLCanvas;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.util.measure.MeasureTool;

public class WorldWindControllersTest extends JFrame {
	/**
	 * World Wind basic
	 */
	private static final long serialVersionUID = 1L;
	private WorldWindowGLCanvas wwd;
	/**
	 * Controllers Test
	 */
	private LayerController screenAnnotation;
	private LayerController polygon2d;
	private ShapefileController shpController;
	private LayerController comment;
	private LayerController line;
	private LayerController polygon3d;
	private LayerController points;
	private GeoJSONController jsonController;

	public WorldWindControllersTest() {
		worldWindConfig();
		controllersConfig();
	}

	private final void controllersConfig() {
		// ScreenAnnotationLayer
		screenAnnotationController();

		// Polygon2D
		// polygon2DController();

		// ShapefileController
		// shapefileController();

		// CommentLayer
		// commentController();

		// LineLayer
		// lineController();

		// Poygon3D
		// polygon3DController();

		// Point
		// pointController();

		// GeoJSONController
		geoJSONController();

	}

	/**
	 * Individual controllers
	 */

	private void screenAnnotationController() {
		screenAnnotation = new ScreenAnnotationLayer(wwd, 780, 530, "images/ccst-novo2.png", new Insets(0, 40, 0, 0),
				new Dimension(265, 200));
		screenAnnotation.asyncDraw();
	}

	private void polygon2DController() {
		JDBCDao<vegtype_2000> dao = new JDBCDao<>();
		List<vegtype_2000> vegtype2000 = dao.getAll(vegtype_2000.class);
		List<GeometryRecord> geometryRecords = new ArrayList<>();
		geometryRecords.addAll(vegtype2000);
		polygon2d = new Polygon2DLayer(wwd, geometryRecords);
		polygon2d.asyncDraw();
	}

	private void shapefileController() {
		shpController = new ShapefileLayer(wwd, "attr");
		shpController.addShapefile(FilePathTest.VEGTYPE_2000, DefaultColors.getOriginalColors1());
		shpController.asyncDraw();
	}

	private void commentController() {
		comment = new CommentLayer(wwd, new Position(Position.fromDegrees(-23, -45), 0.1), new Dimension(480, 145),
				"\n\n\n\n\n Interactive Data Visualization", "images/ccst-novo2.png", 0.2, Color.white, Color.white);
		comment.asyncDraw();
	}

	private void lineController() {
		List<Position> positions = new ArrayList<Position>();
		positions.add(Position.fromDegrees(-23, -45));
		positions.add(Position.fromDegrees(-8.5, -37));
		String displayName = "Distance Test";
		MeasureTool measureTool = new MeasureTool(wwd);
		line = new LineLayer(wwd, 3, positions, displayName, Color.ORANGE, measureTool);
		line.asyncDraw();
	}

	private void polygon3DController() {
		JDBCDao<vegtype_2000> dao = new JDBCDao<>();
		List<vegtype_2000> vegtype2000 = dao.getAll(vegtype_2000.class);
		List<GeometryRecord> geometryRecords = new ArrayList<>();
		geometryRecords.addAll(vegtype2000);
		polygon3d = new Polygon3DLayer(wwd, geometryRecords);
		polygon3d.asyncDraw();
	}

	private void pointController() {
		Map<String, List<Position>> positions = new HashMap<>();
		Position pointPosition = Position.fromDegrees(50, 7.5);
		Position pointPosition2 = Position.fromDegrees(51, 7.5);
		Position pointPosition3 = Position.fromDegrees(52, 7.5);

		positions.put("Point Mark", Arrays.asList(pointPosition, pointPosition2, pointPosition3));

		points = new PointLayer(wwd, positions);

		points.asyncDraw();
	}

	private void geoJSONController() {
		jsonController = new GeoJSONLayer(wwd, "attr");

		/**
		 * addGeoJSON(String layerName, GeoJSONObject json, Map
		 * <Double,Color> colors)
		 */
		// GeoJSONProperties p = new GeoJSONProperties("attr");
		// List<GeoJSONObject> json =
		// p.createGeoJSONObjectFromSource(FilePathTest.VEGTYPE_2000_GDAL_GEOJSON);
		// jsonController.addGeoJSON("Veg2000", json.get(0),
		// DefaultColors.getDefaultColors());

		/*
		 * boolean addGeoJSON(String filepath, String layerName, Map<Double,
		 * Color> colors)
		 */
		jsonController.addGeoJSON("Veg2000", FilePathTest.VEGTYPE_2000_GDAL_GEOJSON, DefaultColors.getDefaultColors());

		// end test
		jsonController.asyncDraw();
	}

	/**
	 * WorldWind config
	 */
	private final void worldWindConfig() {
		wwd = new WorldWindowGLCanvas();
		wwd.setPreferredSize(new java.awt.Dimension(1000, 800));
		this.getContentPane().add(wwd, java.awt.BorderLayout.CENTER);
		wwd.setModel(new BasicModel());
	}

	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new WorldWindControllersTest();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.pack();
				frame.setVisible(true);
			}
		});
	}
}
