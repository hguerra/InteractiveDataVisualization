package br.inpe.triangle.postgis;

import java.util.List;

import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.render.ShapeAttributes;

public interface GeometryRecord {
	String getDisplayName();

	ShapeAttributes getSideAttributes();

	List<Position> getBorderPositions();

	void setDisplayName(String displayName);

	void setSideAttributes(ShapeAttributes sideAttributes);

	void setBorderPositions(List<Position> borderPositions);
}
