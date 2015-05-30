package br.com.inpe.worldwind.view;

import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.ShapeAttributes;
/**
 * @author Heitor Guerra Carneiro
 * @since May 16, 2015
 * @version 1.0
 */
public class InteriorMaterial {

	public static void setColorMaterial(long area, ShapeAttributes sideAttributes) {
		if (area < 300) {
			sideAttributes.setInteriorMaterial(Material.RED);
		} else if (area >= 300 && area < 500) {
			sideAttributes.setInteriorMaterial(Material.WHITE);
		} else if (area >= 500 && area < 800) {
			sideAttributes.setInteriorMaterial(Material.YELLOW);
		} else if (area >= 800 && area < 1000) {
			sideAttributes.setInteriorMaterial(Material.GREEN);
		} else if (area >= 1000) {
			sideAttributes.setInteriorMaterial(Material.BLUE);
		}
		sideAttributes.setOutlineOpacity(0.5);
		sideAttributes.setInteriorOpacity(0.5);
		sideAttributes.setOutlineWidth(2);
		sideAttributes.setDrawOutline(true);
		sideAttributes.setDrawInterior(true);
	}

	public static EMaterialColor checkSize(long area) {
		if (area < 300) {
			return EMaterialColor.RED;
		} else if (area >= 300 && area < 500) {
			return EMaterialColor.WHITE;
		} else if (area >= 500 && area < 800) {
			return EMaterialColor.YELLOW;
		} else if (area >= 800 && area < 1000) {
			return EMaterialColor.GREEN;
		} else
			return EMaterialColor.BLUE;
	}
}
