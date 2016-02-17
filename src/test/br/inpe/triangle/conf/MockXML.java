package test.br.inpe.triangle.conf;

import java.util.HashMap;
import java.util.Map;

import br.inpe.gdal.transform.GeoFormat;
import br.inpe.triangle.conf.DataSource;

public class MockXML {
	public static final String FILE_PATH = "data/xml/";
	public static final String DATA_SOURCE_XML = FILE_PATH + "datasource.xml";
	public static final String PROPERTIES_XML = FILE_PATH + "properties.xml";
	public static final String DATA_SOURCE_PROPERTIES = FILE_PATH + "datasource.properties";
	
	public static DataSource createDefaultDataSource() {
		DataSource data = new DataSource();
		data.setFormat(GeoFormat.SHAPEFILE);
		data.setFilepath(DATA_SOURCE_XML);
		
		Map<Double, String> colors = new HashMap<>();
		colors.put(1.0, "#006401");
		colors.put(2.0, "#388237");
		
		data.setColors(colors);
		
		return data;
	}
}
