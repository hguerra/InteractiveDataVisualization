package br.inpe.triangle.wwj.layer;

import gov.nasa.worldwind.awt.WorldWindowGLCanvas;
import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.LatLon;
import gov.nasa.worldwind.geom.Position;

public interface ScenarioController {

	void setCenterLatLon(Angle lat, Angle lon);

	void setCenterPosition(Position newCenter);

	void pan(double moveY, double moveX);

	void zoom(double ratio);

	void rotate(Angle angle);

	void tilt(Angle tilt);

	void flyToLatLon(LatLon latlon, double zoom);

	void flyToPosition(Position position, double zoom);

	void rotateToNorth(WorldWindowGLCanvas canvas);

	void unTilt(WorldWindowGLCanvas canvas);

	/**
	 * @author Umut Tas
	 */
	void changeLayer();

	void yearForward();

	void yearBackward();

}