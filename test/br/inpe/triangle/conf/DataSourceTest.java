package br.inpe.triangle.conf;

import br.inpe.triangle.data.Data;
import br.inpe.triangle.data.DataSource;
import br.inpe.triangle.data.JSONBuilder;
import br.inpe.triangle.data.XMLBuilder;
import br.inpe.triangle.defaultproperties.DefaultDataSource;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Map;

public class DataSourceTest {
	private DataSource triangle;
	private JSONBuilder jsonBuilder;
	private XMLBuilder xmlBuilder;

	@Before
	public void init() {
		triangle = DefaultDataSource.getInstance().createVegetationDataSource();
		jsonBuilder = JSONBuilder.getInstance();
		xmlBuilder = XMLBuilder.getInstance();
	}

	@Test
	public void testAddLayersJSON() {
		String json = jsonBuilder.getJSON(triangle);
		File file = new File(MockConf.FILE_PATH + "triangle.json");
		Boolean expected = true;
		Boolean actual = jsonBuilder.writeJSON(json, file);
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testAddLayersXML() {
		Boolean expected = true;
		Boolean actual = xmlBuilder.buildXML(triangle, MockConf.FILE_PATH + "triangle.xml", false);
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testGetLayersXML() {
		DataSource t = xmlBuilder.buildObjectFromXML(DataSource.class, MockConf.FILE_PATH + "triangle.xml");
		Map<String, Data> actual = t.getDataSet();
		Assert.assertEquals(triangle.getDataSet().get("vegtype_2035.shp").toString(),
				actual.get("vegtype_2035.shp").toString());
	}

	@Test
	public void testGetLayersJSON() {
		File file = new File(MockConf.FILE_PATH + "triangle.json");
		DataSource t = jsonBuilder.readJSON(DataSource.class, file);
		Map<String, Data> actual = t.getDataSet();
		Assert.assertEquals(triangle.getDataSet().get("vegtype_2035.shp").toString(),
				actual.get("vegtype_2035.shp").toString());
	}

	@After
	public void after() {
		jsonBuilder = null;
		triangle = null;
	}

}
