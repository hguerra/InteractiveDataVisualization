package br.inpe.triangle.conf;

import br.inpe.triangle.data.Data;
import br.inpe.triangle.defaultproperties.DataFilePath;
import br.inpe.triangle.gdal.GeoFormat;

import java.util.HashMap;
import java.util.Map;

public class MockConf {
	public static final String FILE_PATH = "data/triangle/";
	public static final String DATA_XML = FILE_PATH + "data.xml";

	public static Data createDefaultData() {
		Data data = new Data();
		data.setFormat(GeoFormat.SHAPEFILE);
		data.setFilepath(DataFilePath.getFilePath(DataFilePath.VEGTYPE_2000));
		Map<Object, String> colors = new HashMap<>();
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
