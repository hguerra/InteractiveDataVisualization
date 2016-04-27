package inpe.triangle.conf;

import br.inpe.triangle.conf.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.Map;

public class JSONBuilderTest {
	private JSONBuilder builder;
	private Data data;

	@Before
	public void init() {
		builder = JSONBuilder.getInstance();
		data = MockConf.createDefaultData();
	}

	@Test
	public void testDataWriteJSON() {
		String json = builder.getJSON(data);
		Boolean expected = true;
		Boolean actual = builder.writeJSON(json, MockConf.FILE_PATH + "data.json");
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testDataFromJSON() {
		String expected = "ESRI Shapefile - data/vegtype-inland/shapefile/vegtype_2000.shp - {1.0=#006401, 2.0=#388237, 9.0=#F07A00, 10.0=#D15400, 11.0=#B23000, 12.0=#930900, 3.0=#719F71, -127.0=#000000}";
		Data actual = builder.readJSON(Data.class, MockConf.FILE_PATH + "data.json");
		Assert.assertEquals(expected, actual.toString());
	}

	@Test
	public void testDataColorFromJSON() {
		Data object = builder.readJSON(Data.class, MockConf.FILE_PATH + "data.json");
		Map<Object, Color> expected = data.getAwtColors();
		Map<Object, Color> actual = object.getAwtColors();
		Assert.assertEquals(expected, actual);
	}

	@After
	public void after() {
		builder = null;
		data = null;
	}
}
