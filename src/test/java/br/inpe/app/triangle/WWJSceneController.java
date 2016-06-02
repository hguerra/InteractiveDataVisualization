package br.inpe.app.triangle;

import gov.nasa.worldwind.awt.WorldWindowGLCanvas;
import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.view.orbit.OrbitView;

import java.util.ConcurrentModificationException;

/**
 * @author Heitor
 * @since 01/06/2016
 */
public class WWJSceneController {
    private WorldWindowGLCanvas canvas;
    private OrbitView view;

    public WWJSceneController(WorldWindowGLCanvas canvas) {
        this.canvas = canvas;
        this.view = (OrbitView) canvas.getView();
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
}
