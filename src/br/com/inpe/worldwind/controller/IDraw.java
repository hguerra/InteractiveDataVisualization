package br.com.inpe.worldwind.controller;
/**
 * @author Heitor Guerra Carneiro
 * @since May 16, 2015
 * @version 1.0
 */
public interface IDraw {
	public void drawShapeGreaterMunicipalityArea(long area);
	public void drawShapeEqualsMunicipalityArea(long area);
	public void drawShapeLessMunicipalityArea(long area);

}
