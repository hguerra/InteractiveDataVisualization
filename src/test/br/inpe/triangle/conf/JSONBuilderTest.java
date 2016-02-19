package test.br.inpe.triangle.conf;

import java.awt.Color;
import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.inpe.triangle.conf.DataSource;
import br.inpe.triangle.conf.JSONBuilder;

public class JSONBuilderTest {
	private JSONBuilder builder;
	private DataSource data;

	@Before
	public void init() {
		builder = JSONBuilder.getInstance();
		data = MockConf.createDefaultDataSource();
	}

	@Test
	public void testDataSourceWriteJSON() {
		String json = builder.getJSON(data);
		Boolean expected = true;
		Boolean actual = builder.writeJSON(json, MockConf.FILE_PATH + "datasource.json");
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testDataSourceFromJSON() {
		String expected = "ESRI Shapefile - data/vegtype-inland/shapefile/vegtype_2000.shp - {1.0=#006401, 2.0=#388237, 9.0=#F07A00, 10.0=#D15400, 11.0=#B23000, 12.0=#930900, 3.0=#719F71, -127.0=#000000}";
		DataSource actual = builder.readJSON(DataSource.class, MockConf.FILE_PATH + "datasource.json");
		Assert.assertEquals(expected, actual.toString());
	}

	@Test
	public void testDataSourceColorFromJSON() {
		DataSource object = builder.readJSON(DataSource.class, MockConf.FILE_PATH + "datasource.json");
		Map<Double, Color> expected = data.getAwtColors();
		Map<Double, Color> actual = object.getAwtColors();
		Assert.assertEquals(expected, actual);
	}

	@After
	public void after() {
		builder = null;
		data = null;
	}
}
