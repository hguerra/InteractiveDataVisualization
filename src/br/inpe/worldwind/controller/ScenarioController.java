package br.inpe.worldwind.controller;

import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.awt.WorldWindowGLCanvas;
import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.LatLon;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.globes.Globe;
import gov.nasa.worldwind.view.orbit.OrbitView;

import java.util.ConcurrentModificationException;

import br.inpe.worldwind.layer.VegetationScenarioLayer.VegetationScenarioApp;

public class ScenarioController {

	private OrbitView view;
	private VegetationScenarioApp appFrame;
	private WorldWindowGLCanvas canvas;

	public ScenarioController(VegetationScenarioApp appFrame) {
		this.appFrame = appFrame;
		this.canvas = appFrame.getWwd();
		this.view = (OrbitView) canvas.getView();
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

	/**
	 * @author Umut Tas
	 */
	public void changeLayer() {
		appFrame.changeLayers();
	}

	public void yearForward() {
		if (appFrame.getActiveLayer() == "a") {

			if (appFrame.getLayerChanger() < 10) {
				// remove old

				appFrame.getWwd()
						.getModel()
						.getLayers()
						.remove(appFrame.getVegLayers().get(
								appFrame.getLayerChanger()));

				appFrame.getWwd()
						.getModel()
						.getLayers()
						.remove(appFrame.getAnnotationLayers().get(
								appFrame.getLayerChanger()));

				// add new

				appFrame.setLayerChanger(appFrame.getLayerChanger() + 1);

				appFrame.getWwd()
						.getModel()
						.getLayers()
						.add(appFrame.getVegLayers().get(
								appFrame.getLayerChanger()));

				appFrame.getWwd()
						.getModel()
						.getLayers()
						.add(appFrame.getAnnotationLayers().get(
								appFrame.getLayerChanger()));

				// refresh

				appFrame.refreshActiveLayers(appFrame.getVegLayers().get(
						appFrame.getLayerChanger()));

			} else {

				// remove old

				appFrame.getWwd()
						.getModel()
						.getLayers()
						.remove(appFrame.getVegLayers().get(
								appFrame.getLayerChanger()));

				appFrame.getWwd()
						.getModel()
						.getLayers()
						.remove(appFrame.getAnnotationLayers().get(
								appFrame.getLayerChanger()));
				// add new
				// reset
				appFrame.setLayerChanger(0);

				appFrame.getWwd()
						.getModel()
						.getLayers()
						.add(appFrame.getVegLayers().get(
								appFrame.getLayerChanger()));

				appFrame.getWwd()
						.getModel()
						.getLayers()
						.add(appFrame.getAnnotationLayers().get(
								appFrame.getLayerChanger()));

				// refresh

				appFrame.refreshActiveLayers(appFrame.getVegLayers().get(
						appFrame.getLayerChanger()));
			}
			// B
		}
		if (appFrame.getActiveLayer() == "b") {
			if (appFrame.getLayerChanger() < 10) {

			} else {

			}
		}

	}

	public void yearBackward() {
		if (appFrame.getActiveLayer() == "a") {

			if (appFrame.getLayerChanger() > 1) {
				// remove old

				// remove old

				appFrame.getWwd()
						.getModel()
						.getLayers()
						.remove(appFrame.getVegLayers().get(
								appFrame.getLayerChanger()));

				appFrame.getWwd()
						.getModel()
						.getLayers()
						.remove(appFrame.getAnnotationLayers().get(
								appFrame.getLayerChanger()));
				// add new

				appFrame.setLayerChanger(appFrame.getLayerChanger() - 1);

				appFrame.getWwd()
						.getModel()
						.getLayers()
						.add(appFrame.getVegLayers().get(
								appFrame.getLayerChanger()));

				appFrame.getWwd()
						.getModel()
						.getLayers()
						.add(appFrame.getAnnotationLayers().get(
								appFrame.getLayerChanger()));

				// refresh

				appFrame.refreshActiveLayers(appFrame.getVegLayers().get(
						appFrame.getLayerChanger()));

			} else {

				// remove old

				appFrame.getWwd()
						.getModel()
						.getLayers()
						.remove(appFrame.getVegLayers().get(
								appFrame.getLayerChanger()));

				appFrame.getWwd()
						.getModel()
						.getLayers()
						.remove(appFrame.getAnnotationLayers().get(
								appFrame.getLayerChanger()));
				// add new
				// reset
				appFrame.setLayerChanger(10);

				appFrame.getWwd()
						.getModel()
						.getLayers()
						.add(appFrame.getVegLayers().get(
								appFrame.getLayerChanger()));

				appFrame.getWwd()
						.getModel()
						.getLayers()
						.add(appFrame.getAnnotationLayers().get(
								appFrame.getLayerChanger()));

				// refresh

				appFrame.refreshActiveLayers(appFrame.getVegLayers().get(
						appFrame.getLayerChanger()));
			}
			// B
		} else if (appFrame.getActiveLayer() == "b") {
			if (appFrame.getLayerChanger() > 1) {

			} else {

			}
		}

	}

}
