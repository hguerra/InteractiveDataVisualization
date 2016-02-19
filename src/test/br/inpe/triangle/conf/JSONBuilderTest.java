package test.br.inpe.triangle.conf;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.inpe.triangle.conf.DataSource;

public class JSONBuilderTest {
	private Gson gson;
	private DataSource data;

	@Before
	public void init() {
		gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		data = MockConf.createDefaultDataSource();
	}

	@Test
	public void testDataSourceWriteJSON() {
		String json = gson.toJson(data);
		Boolean expected = true;
		Boolean actual = writerJSON(json, MockConf.FILE_PATH + "datasource");
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testDataSourceFromJSON() {
		String expected = "ESRI Shapefile - data/xml/datasource.xml - {1.0=#006401, 2.0=#388237}";
		DataSource actual = readJSON(DataSource.class, MockConf.FILE_PATH + "datasource.json");
		Assert.assertEquals(expected, actual.toString());
	}

	@Test
	public void testDataSourceColorFromJSON() {
		DataSource object = readJSON(DataSource.class, MockConf.FILE_PATH + "datasource.json");
		Map<Double, Color> expected = data.getAwtColors();
		Map<Double, Color> actual = object.getAwtColors();
		Assert.assertEquals(expected, actual);
	}

	@After
	public void after() {
		gson = null;
		data = null;
	}

	// write and read
	private boolean writerJSON(String json, String filepath) {
		boolean success = true;
		try {
			FileWriter writer = new FileWriter(filepath + ".json");
			writer.write(json);
			writer.close();
		} catch (IOException e) {
			success = false;
			e.printStackTrace();
		}
		return success;
	}

	private <T> T readJSON(Class<?> typeOfClass, String filepath) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(filepath));
			// convert the json string back to object
			T obj = gson.fromJson(br, typeOfClass);
			return obj;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
