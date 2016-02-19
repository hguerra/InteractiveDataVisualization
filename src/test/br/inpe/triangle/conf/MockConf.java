package test.br.inpe.triangle.conf;

import java.util.HashMap;
import java.util.Map;

import br.inpe.gdal.transform.GeoFormat;
import br.inpe.triangle.conf.DataSource;
import br.inpe.triangle.defaultproperties.DefaultFilePath;

public class MockConf {
	public static final String FILE_PATH = "data/xml/";
	public static final String DATA_SOURCE_XML = FILE_PATH + "datasource.xml";
	public static final String PROPERTIES_XML = FILE_PATH + "properties.xml";
	public static final String DATA_SOURCE_PROPERTIES = FILE_PATH + "datasource.properties";

	public static DataSource createDefaultDataSource() {
		DataSource data = new DataSource();
		
		data.setFormat(GeoFormat.SHAPEFILE);
		data.setFilepath(DefaultFilePath.VEGTYPE_2000);

		Map<Double, String> colors = new HashMap<>();
		colors.put(1.0, "#006401");
		colors.put(2.0, "#388237");
		colors.put(9.0, "#F07A00");
		colors.put(10.0, "#D15400");
		colors.put(11.0, "#B23000");
		colors.put(12.0, "#930900");
		colors.put(3.0, "#719F71");
		colors.put(-127.0, "#000000");
	
		data.setColors(colors);

		return data;
	}
}
