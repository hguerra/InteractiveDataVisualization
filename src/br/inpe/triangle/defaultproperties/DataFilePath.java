package br.inpe.triangle.defaultproperties;

import java.util.Arrays;
import java.util.List;

public class DataFilePath {
	private static final boolean BUILD = false;
	private static final String BUILD_PATH = "C:\\Program Files (x86)\\Triangle\\data\\";
	private static final String DEV_PATH = "data/";
	public static final String VEGTYPE_2000 = "vegtype_2000.shp";
	public static final String VEGTYPE_2005 = "vegtype_2005.shp";
	public static final String VEGTYPE_2010 = "vegtype_2010.shp";
	public static final String VEGTYPE_2015 = "vegtype_2015.shp";
	public static final String VEGTYPE_2020 = "vegtype_2020.shp";
	public static final String VEGTYPE_2025 = "vegtype_2025.shp";
	public static final String VEGTYPE_2030 = "vegtype_2030.shp";
	public static final String VEGTYPE_2035 = "vegtype_2035.shp";
	public static final String VEGTYPE_2040 = "vegtype_2040.shp";
	public static final String VEGTYPE_2045 = "vegtype_2045.shp";
	public static final String VEGTYPE_2050 = "vegtype_2050.shp";

	public static final String ESTADOS = getFilePath("estados_2010.shp");
	public static final String REGIOES = getFilePath("regioes_2010.shp");
	public static final String MUNICIPIOS = getFilePath("municipios_2010.shp");

	public static final List<String> vegtypesSequence = Arrays.asList(VEGTYPE_2000, VEGTYPE_2005, VEGTYPE_2010,
			VEGTYPE_2015, VEGTYPE_2020, VEGTYPE_2025, VEGTYPE_2030, VEGTYPE_2035, VEGTYPE_2040, VEGTYPE_2045,
			VEGTYPE_2050);

	public static String getFilePath(String filename) {
		if(BUILD) return BUILD_PATH.concat(filename);
		return DEV_PATH.concat(filename);
	}
}
