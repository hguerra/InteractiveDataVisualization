package test.app;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.JFrame;

import br.inpe.message.properties.DefaultColors;
import br.inpe.util.FilePathTest;
import br.inpe.worldwind.controller.LayerController;
import br.inpe.worldwind.controller.ShapefileController;
import br.inpe.worldwind.defaultcontroller.CommentLayer;
import br.inpe.worldwind.defaultcontroller.ScreenAnnotationLayer;
import br.inpe.worldwind.defaultcontroller.ShapefileLayer;
import gov.nasa.worldwind.BasicModel;
import gov.nasa.worldwind.awt.WorldWindowGLCanvas;
import gov.nasa.worldwind.geom.Position;

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

	public WorldWindControllersTest() {
		worldWindConfig();
		controllersConfig();
	}

	private final void controllersConfig() {
		// ScreenAnnotationLayer
		screenAnnotation = new ScreenAnnotationLayer(wwd, 780, 530, "images/ccst-novo2.png", new Insets(0, 40, 0, 0),
				new Dimension(265, 200));
		screenAnnotation.asyncDraw();

		// Polygon2D
		// JDBCDao<vegtype_2000> dao = new JDBCDao<>();
		// List<vegtype_2000> vegtype2000 = dao.getAll(vegtype_2000.class);
		// List<GeometryRecord> geometryRecords = new ArrayList<>();
		// geometryRecords.addAll(vegtype2000);
		// polygon2d = new Polygon2DLayer(wwd, geometryRecords);
		// polygon2d.asyncDraw();

		// ShapefileController
		shpController = new ShapefileLayer(wwd, "attr");
		shpController.addShapefile(FilePathTest.VEGTYPE_2000, DefaultColors.getOriginalColors1());
		shpController.asyncDraw();

		// CommentLayer
		comment = new CommentLayer(wwd, new Position(Position.fromDegrees(-23, -45), 0.1), new Dimension(480, 145),
				"\n\n\n\n\n Interactive Data Visualization", "images/ccst-novo2.png", 0.2, Color.white, Color.white);
		comment.asyncDraw();
	}

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
