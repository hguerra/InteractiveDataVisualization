package br.inpe.worldwind.engine;

import java.awt.Color;
import java.util.Map;
import java.util.Set;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.inpe.triangle.defaultproperties.DataFilePath;
import br.inpe.triangle.defaultproperties.DefaultColors;
import br.inpe.triangle.wwj.dataaccess.ShapefileProperties;
import br.inpe.triangle.wwj.layer.ShapefileController;
import gov.nasa.worldwind.formats.shapefile.Shapefile;

public class ShapefilePropertiesTest {
	private ShapefileProperties prop;
	private Shapefile shp;
	private String attributeName;

	@Before
	public void init() throws Exception {
		prop = new ShapefileProperties();
		shp = ShapefileController.createShapefile(DataFilePath.getFilePath(DataFilePath.VEGTYPE_2000));
		attributeName = "attr";
	}

	@Test
	public void testCreatePolygonColorsEmptyColor() {
		// Empty
		Color[] interiorMaterial = new Color[] {};

		Map<Object, Color> colors = prop.createPolygonColors(shp, attributeName, interiorMaterial);
		Object expected = 7;
		Object actual = colors.size();

		System.out.println("Empty:" + colors);

		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testCreatePolygonColorsOneColor() {
		// One color
		Color[] interiorMaterial = new Color[] { Color.red };

		Map<Object, Color> colors = prop.createPolygonColors(shp, attributeName, interiorMaterial);
		Object expected = 7;
		Object actual = colors.size();

		System.out.println("One:" + colors);

		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testCreatePolygonColors() {
		// default color
		Color[] interiorMaterial = DefaultColors.getOriginalColors1();

		Map<Object, Color> colors = prop.createPolygonColors(shp, attributeName, interiorMaterial);
		Object expected = 7;
		Object actual = colors.size();

		System.out.println("Default:" + colors);

		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testGetDataColumns() {
		Set<String> dataColumns = ShapefileProperties.getShapefileColumns(shp);
		Object expected = 1;
		Object actual = dataColumns.size();

		Assert.assertEquals(expected, actual);
	}

	@After
	public void after() {
		prop = null;
		shp = null;
		attributeName = null;
	}
}
