package br.com.inpe.worldwind.controller;

import gov.nasa.worldwind.geom.Position;

import java.util.List;

/**
 * @author Heitor Guerra Carneiro
 * @since May 16, 2015
 * @version 1.0
 */
public interface ILine {
	public void drawLine(String displayName, List<Position> positions);
}
