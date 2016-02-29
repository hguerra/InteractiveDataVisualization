package test.br.inpe.triangle.conf;

import java.io.File;
import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.inpe.triangle.conf.Data;
import br.inpe.triangle.conf.JSONBuilder;
import br.inpe.triangle.conf.DataSource;
import br.inpe.triangle.conf.XMLBuilder;

public class DataSourceTest {
	private DataSource triangle;
	private JSONBuilder jsonBuilder;
	private XMLBuilder xmlBuilder;
	private Data data;

	@Before
	public void init() {
		triangle = new DataSource();
		jsonBuilder = JSONBuilder.getInstance();
		xmlBuilder = XMLBuilder.getInstance();
		data = MockConf.createDefaultData();
	}

	@Test
	public void testAddLayersJSON() {
		triangle.addData("Test1", data);
		String json = jsonBuilder.getJSON(triangle);
		File file = new File(MockConf.FILE_PATH + "triangle.json");
		Boolean expected = true;
		Boolean actual = jsonBuilder.writeJSON(json, file);
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testAddLayersXML() {
		triangle.addData("Test1", data);
		Boolean expected = true;
		Boolean actual = xmlBuilder.buildXML(triangle, MockConf.FILE_PATH + "triangle.xml", false);
		Assert.assertEquals(expected, actual);
	}


	@Test
	public void testGetLayersXML() {
		DataSource t = xmlBuilder.buildObjectFromXML(DataSource.class, MockConf.FILE_PATH + "triangle.xml");
		Map<String, Data> actual = t.getDataSet();
		String expected = "ESRI Shapefile - data/vegtype-inland/shapefile/vegtype_2000.shp - {1.0=#006401, 2.0=#388237, 9.0=#F07A00, 10.0=#D15400, 11.0=#B23000, 12.0=#930900, 3.0=#719F71, -127.0=#000000}";
		Assert.assertEquals(expected, actual.get("Test1").toString());
	}

	@Test
	public void testGetLayersJSON() {
		File file = new File(MockConf.FILE_PATH + "triangle.json");
		DataSource t = jsonBuilder.readJSON(DataSource.class, file);
		Map<String, Data> actual = t.getDataSet();
		String expected = "ESRI Shapefile - data/vegtype-inland/shapefile/vegtype_2000.shp - {1.0=#006401, 2.0=#388237, 9.0=#F07A00, 10.0=#D15400, 11.0=#B23000, 12.0=#930900, 3.0=#719F71, -127.0=#000000}";
		Assert.assertEquals(expected, actual.get("Test1").toString());
	}
	@After
	public void after() {
		jsonBuilder = null;
		data = null;
		triangle = null;
	}

}
