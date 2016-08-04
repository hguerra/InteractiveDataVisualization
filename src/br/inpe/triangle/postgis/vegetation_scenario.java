package br.inpe.triangle.postgis;

import java.util.List;

import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.render.ShapeAttributes;

public abstract class vegetation_scenario implements GeometryRecord {
	private String displayName;
	private ShapeAttributes sideAttributes;
	private List<Position> borderPositions;

	@Override
	public String getDisplayName() {
		return displayName;
	}

	@Override
	public ShapeAttributes getSideAttributes() {
		return sideAttributes;
	}

	@Override
	public List<Position> getBorderPositions() {
		return borderPositions;
	}

	@Override
	public void setDisplayName(String displayName) {
		this.displayName = displayName;

	}

	@Override
	public void setSideAttributes(ShapeAttributes sideAttributes) {
		this.sideAttributes = sideAttributes;

	}

	@Override
	public void setBorderPositions(List<Position> borderPositions) {
		this.borderPositions = borderPositions;

	}
	public abstract String getData();

}
