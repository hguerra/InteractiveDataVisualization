package br.com.inpe.worldwind.view;

import gov.nasa.worldwind.BasicModel;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.awt.WorldWindowGLCanvas;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.AnnotationLayer;
import gov.nasa.worldwind.layers.CompassLayer;
import gov.nasa.worldwind.layers.Layer;
import gov.nasa.worldwind.layers.LayerList;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.BasicShapeAttributes;
import gov.nasa.worldwind.render.GlobeAnnotation;
import gov.nasa.worldwind.render.Polygon;
import gov.nasa.worldwind.render.Polyline;
import gov.nasa.worldwind.render.ScreenAnnotation;
import gov.nasa.worldwind.render.ShapeAttributes;
import gov.nasa.worldwind.terrain.ZeroElevationModel;
import gov.nasa.worldwind.util.UnitsFormat;
import gov.nasa.worldwind.util.measure.MeasureTool;
import gov.nasa.worldwind.util.measure.MeasureToolController;
import gov.nasa.worldwindx.examples.util.PowerOfTwoPaddedImage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

import br.com.inpe.worldwind.controller.IAnnotation;
import br.com.inpe.worldwind.controller.IDraw;
import br.com.inpe.worldwind.controller.ILine;
import br.com.inpe.worldwind.database.GeometryRecord;

/**
 * @author Heitor Guerra Carneiro
 * @since May 16, 2015
 * @version 1.0
 */
public class WorldWindView extends JFrame implements Observer {
	private WorldWindowGLCanvas wwd;
	private final int HEIGHT = 600;
	private final int WIDTH = 800;
	public static final double INITIAL_ZOOM = 2.3e7;
	public static final Position PARA_POS = Position.fromDegrees(-4.72826,
			-52.302247, 7000000);

	private JLayeredPane layeredPane;

	private IDraw iDraw;
	private IAnnotation annotation;
	private ILine line;

	public WorldWindView() {

		wwd = new WorldWindowGLCanvas();
		wwd.setPreferredSize(new java.awt.Dimension(WIDTH, HEIGHT));
		wwd.setModel(new BasicModel());
		this.wwd.getModel().getGlobe()
				.setElevationModel(new ZeroElevationModel());
		wwd.setBounds(0, 0, WIDTH + 1, HEIGHT + 1); // +1 because without it
													// the camera image isnt
													// shown

		layeredPane = new JLayeredPane();
		layeredPane.setPreferredSize(new java.awt.Dimension(WIDTH, HEIGHT));

		layeredPane.add(wwd, java.awt.BorderLayout.CENTER);

		layeredPane.setBounds(0, 0, WIDTH, HEIGHT);

		layeredPane.doLayout();

		frameEvents();
	}

	public void setIDraw(IDraw d, int key, long area) {
		this.iDraw = d;
		switch (key) {
		case 1:
			d.drawShapeGreaterMunicipalityArea(area);
			break;
		case 2:
			d.drawShapeEqualsMunicipalityArea(area);
			break;
		case 3:
			d.drawShapeLessMunicipalityArea(area);
			break;
		}

	}

	public void setIAnnotation(IAnnotation a) {
		this.annotation = a;
		a.addAnnotation();
	}

	public void setIline(ILine l, String displayName, List<Position> positions) {
		this.line = l;
		l.drawLine(displayName, positions);
	}

	@Override
	public void updateGeometryRecordLayer(List<GeometryRecord> geometryRecord) {
		if (!geometryRecord.isEmpty()) {
			RenderableLayer layer = getLayer(geometryRecord);
			insertBeforeCompass(this.wwd, layer);
		}
	}

	public void printLogo() {
		try {
			PowerOfTwoPaddedImage pic = PowerOfTwoPaddedImage
					.fromPath("images/logo.png");
			AnnotationLayer annLayer = new AnnotationLayer();

			ScreenAnnotation logoDWIH = new ScreenAnnotation("",
					new Point(0, 0));

			logoDWIH.getAttributes().setImageSource(pic.getPowerOfTwoImage());
			logoDWIH.getAttributes().setImageRepeat(AVKey.REPEAT_NONE);
			logoDWIH.getAttributes().setAdjustWidthToText(AVKey.SIZE_FIXED);
			logoDWIH.getAttributes().setDrawOffset(new Point(100, 0));
			logoDWIH.getAttributes().setHighlightScale(1);

			logoDWIH.getAttributes().setInsets(new Insets(0, 40, 0, 0));
			logoDWIH.getAttributes().setSize(new Dimension(120, 100));

			logoDWIH.getAttributes().setImageScale(0.22);
			logoDWIH.getAttributes().setImageOffset(new Point(10, 10));

			annLayer.addAnnotation(logoDWIH);
			insertBeforeCompass(this.wwd, annLayer);
		} catch (Exception e) {
			System.out.println("Error to add Image!");
		}
	}

	public void printComment() {
		try {
			Position position = new Position(Position.fromDegrees(-23, -45),
					0.1);
			PowerOfTwoPaddedImage pic = PowerOfTwoPaddedImage
					.fromPath("images/logo.png");
			AnnotationLayer annotationlayer = new AnnotationLayer();
			GlobeAnnotation ga = new GlobeAnnotation(
					"\n\n\n\n\n Interactive Data Visualization" + " - INPE.",
					position, Font.decode("Arial-BOLD-13"));
			ga.getAttributes().setImageSource(pic.getPowerOfTwoImage());
			ga.getAttributes().setBorderColor(Color.WHITE);
			ga.getAttributes().setBackgroundColor(Color.WHITE);
			ga.getAttributes().setBorderWidth(1);
			ga.getAttributes().setImageScale(0.2);
			ga.getAttributes().setSize(new Dimension(480, 145));
			ga.setAlwaysOnTop(true);

			ga.setMaxActiveAltitude(1081941);

			annotationlayer.addAnnotation(ga);

			insertBeforeCompass(getWwd(), annotationlayer);
		} catch (Exception e) {
			System.out.println("Error to add Image!");
		}

	}

	public void drawLine(String displayName, List<Position> positions) {
		RenderableLayer layer = new RenderableLayer();
		Polyline polyline = new Polyline(positions, 3e4);
		polyline.setColor(getBackground().ORANGE);
		polyline.setLineWidth(3);
		polyline.setValue(AVKey.DISPLAY_NAME, displayName);
		layer.addRenderable(polyline);
		insertBeforeCompass(getWwd(), layer);

		MeasureTool measure = new MeasureTool(this.getWwd());
		measure.setController(new MeasureToolController());
		measure.getUnitsFormat().setLengthUnits(UnitsFormat.KILOMETERS);
		measure.setFollowTerrain(true);
		measure.setMeasureShape(new Polyline(positions));
		System.out.println(measure.getLength() / 1000 + " km ");
	}

	public void removeLayersTimer(int seconds) {
		final int t = seconds * 1000;
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(t);
					removeCompass(getWwd());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	public RenderableLayer getLayer(List<GeometryRecord> geometryRecord) {
		RenderableLayer layer = new RenderableLayer();
		for (int i = 0; i < geometryRecord.size(); i++) {
			List<Position> borderPositions = new LinkedList<Position>();
			String latlong[] = geometryRecord.get(i).getGeometry().split(";");

			long area = geometryRecord.get(i).getMunicipalityArea();

			for (String str : latlong) {
				String latlong2[] = str.split(",");
				borderPositions.add(Position.fromDegrees(
						Double.parseDouble(latlong2[1]),
						Double.parseDouble(latlong2[0]), 1e4));
			}

			Polygon polygon = new Polygon(borderPositions);

			ShapeAttributes sideAttributes = new BasicShapeAttributes();

			InteriorMaterial.setColorMaterial(area, sideAttributes);

			polygon.setAttributes(sideAttributes);
			polygon.setValue(AVKey.DISPLAY_NAME, geometryRecord.get(i)
					.getMunicipalityName()
					+ " - "
					+ geometryRecord.get(i).getMunicipalityArea() + "km");

			layer.addRenderable(polygon);
		}
		return layer;
	}

	public static void removeCompass(WorldWindow wwd) {
		// Insert the layer into the layer list just before the compass.
		int compassPosition = 0;
		LayerList layers = wwd.getModel().getLayers();
		for (Layer l : layers) {
			if (l instanceof CompassLayer)
				compassPosition = layers.indexOf(l);
		}
		layers.remove(compassPosition);
	}

	public static void insertBeforeCompass(WorldWindow wwd, Layer layer) {
		// Insert the layer into the layer list just before the compass.
		int compassPosition = 0;
		LayerList layers = wwd.getModel().getLayers();
		for (Layer l : layers) {
			if (l instanceof CompassLayer)
				compassPosition = layers.indexOf(l);
		}
		layers.add(compassPosition, layer);
	}

	public static void insertBeforeBeforeCompass(WorldWindow wwd, Layer layer) {
		// Insert the layer into the layer list just before the compass.
		int compassPosition = 0;
		LayerList layers = wwd.getModel().getLayers();
		for (Layer l : layers) {
			if (l instanceof CompassLayer)
				compassPosition = layers.indexOf(l);
		}
		layers.add(compassPosition - 1, layer);
	}

	public void frameEvents() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent event) {
				event.getWindow().setVisible(false);
				event.getWindow().dispose();
				System.exit(0);
			}
		});
		// this.setUndecorated(true);
		this.getContentPane().add(layeredPane, java.awt.BorderLayout.CENTER);
		this.pack();
		this.setBounds(0, 0, WIDTH, HEIGHT);
		removeCompass(this.getWwd());
	}

	// Gets and sets

	public WorldWindowGLCanvas getWwd() {
		return wwd;
	}

	public void setWwd(WorldWindowGLCanvas wwd) {
		this.wwd = wwd;
	}
}
