package inpe.app.worldwind;

import br.inpe.triangle.conf.Data;
import br.inpe.triangle.conf.JSONBuilder;
import br.inpe.triangle.defaultproperties.DefaultColors;
import br.inpe.triangle.defaultproperties.DefaultFilePath;
import br.inpe.worldwind.controller.GeoJSONController;
import br.inpe.worldwind.controller.LayerController;
import br.inpe.worldwind.controller.ShapefileController;
import br.inpe.worldwind.dao.GeometryRecord;
import br.inpe.worldwind.dao.JDBCDao;
import br.inpe.worldwind.dao.model.vegtype_2000;
import br.inpe.worldwind.defaultcontroller.*;
import gov.nasa.worldwind.BasicModel;
import gov.nasa.worldwind.awt.WorldWindowGLCanvas;
import gov.nasa.worldwind.formats.shapefile.Shapefile;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.util.measure.MeasureTool;
import inpe.triangle.conf.MockConf;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

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

    /**
     * User config
     */

    private JSONBuilder jsonBuilder;

    public WorldWindControllersTest() {
        worldWindConfig();
        controllersConfig();
    }

    private final void controllersConfig() {
        // ScreenAnnotationLayer
        screenAnnotationController();

        // Polygon2D
        //polygon2DController();

        // ShapefileController
        shapefileController();

        // CommentLayer
        // commentController();

        // LineLayer
        // lineController();

        // Poygon3D
        // polygon3DController();

        // Point
        // pointController();

        // GeoJSONController
        //geoJSONController();

        // Create using DataSource
        //createFromDataSource();
    }

    /**
     * Conf
     */
    private void createFromDataSource() {
        jsonBuilder = JSONBuilder.getInstance();
        Data data = jsonBuilder.readJSON(Data.class, MockConf.FILE_PATH + "data.json");
        if (data == null)
            return;
        shapefileController(data);
    }

    private void shapefileController(Data data) {
        shpController = new ShapefileLayer(wwd);

        try {
            Shapefile shp = ShapefileController.createShapefile(data.getFilepath());

            shpController.addShapefile(data.getTitle(), data.getColumn(), shp, data.getAwtColors());

            shpController.asyncDraw();
        } catch (Exception e) {
            e.printStackTrace();
        }

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
        shpController = new ShapefileLayer(wwd);
        shpController.addShapefile(DefaultFilePath.VEGTYPE_2000, "attr", DefaultColors.getOriginalColors1());
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
        /*
		 * boolean addGeoJSON(String filepath, String layerName, Map<Double,
		 * Color> colors)
		 */
        jsonController.addGeoJSON("Veg2000", DefaultFilePath.VEGTYPE_2000_GDAL_GEOJSON,
                DefaultColors.getDefaultColors());

        // end test
        jsonController.asyncDraw();
    }

    /**
     * WorldWind config
     */
    private final void worldWindConfig() {
        wwd = new WorldWindowGLCanvas();
        wwd.setPreferredSize(new Dimension(1200, 800));
        this.getContentPane().add(wwd, BorderLayout.CENTER);
        wwd.setModel(new BasicModel());
    }

    public void run() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new WorldWindControllersTest();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        new WorldWindControllersTest().run();
    }
}