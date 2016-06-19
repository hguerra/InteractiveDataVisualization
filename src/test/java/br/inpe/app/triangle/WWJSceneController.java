package br.inpe.app.triangle;

import br.inpe.triangle.conf.Data;
import br.inpe.worldwind.controller.LayerController;
import br.inpe.worldwind.controller.ShapefileController;
import br.inpe.worldwind.defaultcontroller.ShapefileLayer;
import br.inpe.worldwind.engine.ShapefileProperties;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.awt.WorldWindowGLCanvas;
import gov.nasa.worldwind.formats.shapefile.Shapefile;
import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.LatLon;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.globes.Globe;
import gov.nasa.worldwind.layers.Layer;
import gov.nasa.worldwind.view.orbit.OrbitView;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.TreeMap;

/**
 * @author Heitor
 * @since 01/06/2016
 */
public class WWJSceneController {
    private static final int WIDTH_SCREEN_ANNOTATION = 780;
    private static final int HEIGHT_SCREEN_ANNOTATION = 530;
    private static final String IMAGES_CCST = "images/ccst-novo2.png";
    private WorldWindowGLCanvas canvas;
    private OrbitView view;

    private String actualGroup = "";
    private List<Layer> oldData;

    private TreeMap<String, CircularArrayList<List<Layer>>> dataset;
    private ShapefileController shpController;
    private ShapefileProperties shapefileProperties;

    public WWJSceneController(WorldWindowGLCanvas canvas) {
        this.canvas = canvas;
        this.view = (OrbitView) canvas.getView();
        this.shpController = new ShapefileLayer(canvas);
        this.dataset = new TreeMap<>();
        this.shapefileProperties = new ShapefileProperties();
    }

    public void draw() {
        List<Layer> actualData = dataset.get(actualGroup).getActual();
        if (oldData != null) {
            LayerController.removeBeforeCompass(canvas, oldData);
        }
        oldData = actualData;
        LayerController.insertBeforeCompass(canvas, actualData);
    }

    public void addData(Data... datas) {
        for (Data data : datas) {
            try {
                List<Layer> populateLayers = new ArrayList<Layer>();
                Shapefile shp = ShapefileController.createShapefile(data.getFilepath());
                shapefileProperties.addRenderablesForPolygon(shp, data.getTitle(), data.getColumn(), populateLayers, data.getAwtColors());
                if (dataset.containsKey(data.getTitle())) {
                    dataset.get(data.getTitle()).add(populateLayers);
                } else {
                    CircularArrayList<List<Layer>> newElements = new CircularArrayList<List<Layer>>();
                    newElements.add(populateLayers);
                    dataset.put(data.getTitle(), newElements);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        actualGroup = dataset.firstKey();
    }

    public void nextData() {
        if (!dataset.containsKey(actualGroup))
            return;
        actualGroup = dataset.higherKey(actualGroup);
        if (actualGroup == null)
            actualGroup = dataset.firstKey();
        draw();
    }

    public void previousData() {
        if (!dataset.containsKey(actualGroup))
            return;
        actualGroup = dataset.lowerKey(actualGroup);
        if (actualGroup == null)
            actualGroup = dataset.lastKey();
        draw();
    }

    public void yearForward() {
        if (!dataset.containsKey(actualGroup))
            return;
        dataset.get(actualGroup).next();
        draw();
    }

    public void yearBackward() {
        if (!dataset.containsKey(actualGroup))
            return;
        dataset.get(actualGroup).previous();
        draw();
    }

    public void pan(double moveY, double moveX) {
        double sinHeading = view.getHeading().sin();
        double cosHeading = view.getHeading().cos();
        double latFactor = (cosHeading * moveY + sinHeading * moveX) / 10.0;
        double lonFactor = (sinHeading * moveY - cosHeading * moveX) / 10.0;
        Angle latChange = computeLatOrLonChange(-latFactor, false);
        Angle lonChange = computeLatOrLonChange(-lonFactor, false);
        try {
            setCenterLatLon(
                    this.view.getCenterPosition().getLatitude().add(latChange),
                    this.view.getCenterPosition().getLongitude().add(lonChange));
        } catch (ConcurrentModificationException e) {
            System.err.println(e);
        }

    }

    public void setCenterLatLon(Angle lat, Angle lon) {
        if (this.view == null)
            return;
        setCenterPosition(new Position(lat, lon, view.getCenterPosition()
                .getElevation()));
    }

    public void setCenterPosition(Position newCenter) {
        if (this.view == null)
            return;
        Position clampedCenter = clampedCenter(newCenter);
        view.setCenterPosition(clampedCenter);
    }

    private Angle computeLatOrLonChange(double amount, boolean slow) {
        if (this.canvas == null || this.canvas.getModel() == null
                || this.canvas.getModel().getGlobe() == null
                || this.view == null || this.view.getEyePosition() == null) {
            return Angle.ZERO;
        }

        Position eyePos = this.view.getEyePosition();
        double normAlt = (eyePos.getElevation() / this.canvas.getModel()
                .getGlobe().getRadiusAt(eyePos));
        if (normAlt < 0)
            normAlt = 0;
        else if (normAlt > 1)
            normAlt = 1;

        double coeff = (0.0001 * (1 - normAlt)) + (2 * normAlt);
        if (slow)
            coeff /= 4.0;

        return Angle.fromDegrees(coeff * amount);
    }

    private static Position clampedCenter(Position unclampedCenter) {
        if (unclampedCenter == null)
            return null;

        // Clamp latitude to the range [-90, 90],
        // Normalize longitude to the range [-180, 180],
        // Don't change elevation.
        double lat = unclampedCenter.getLatitude().degrees;
        double lon = unclampedCenter.getLongitude().degrees;
        double elev = unclampedCenter.getElevation();
        lon = lon % 360;
        return Position.fromDegrees(lat > 90 ? 90 : (lat < -90 ? -90 : lat),
                lon > 180 ? lon - 360 : (lon < -180 ? 360 + lon : lon), elev);
    }

    public void zoom(double ratio) {
        if (this.view == null)
            return;

        final double zoomFactor = this.view.getZoom();

        int newzoom = (int) (zoomFactor * ratio);
        if (newzoom >= 1071941 && newzoom <= 18437542) {
            this.view.setZoom(newzoom);
            this.view.firePropertyChange(AVKey.VIEW, null, this.view);
        }
    }

    public void rotate(Angle angle) {
        if (this.view == null) {
            return;
        }

        Angle heading = view.getHeading();
        Angle newHeading = heading.add(angle);
        this.view.setHeading(newHeading);
        this.view.firePropertyChange(AVKey.VIEW, null, this.view);
    }

    public void tilt(Angle tilt) {
        if (this.view == null) {
            return;
        }

        Angle pitch = view.getPitch();
        Angle newPitch = pitch.add(tilt);

        if (newPitch.degrees < 0 || newPitch.degrees > 90) {
            return;
        }

        this.view.setPitch(newPitch);
        this.view.firePropertyChange(AVKey.VIEW, null, this.view);
    }

    public void flyToLatLon(LatLon latlon, double zoom) {
        Globe globe = canvas.getModel().getGlobe();
        Position position = new Position(latlon, globe.getElevation(
                latlon.getLatitude(), latlon.getLongitude()));

        flyToPosition(position, zoom);
    }

    public void flyToPosition(Position position, double zoom) {
        this.view.setEyePosition(position);
        view.setPitch(Angle.fromDegrees(35));
    }

    public void rotateToNorth(WorldWindowGLCanvas canvas) {
        this.view.setHeading(Angle.fromDegrees(0));
    }

    public void unTilt(WorldWindowGLCanvas canvas) {
        Angle pitch = Angle.fromDegrees(0);
        this.view.setPitch(pitch);
    }


}
