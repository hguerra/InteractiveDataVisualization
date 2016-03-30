package br.inpe.triangle.defaultproperties;

import java.util.Arrays;
import java.util.List;

public class DefaultFilePath {
	public static final String FILE_PATH_MODELOS = "data/vegtype-inland/shapefile/";
	public static final String VEGTYPE_2000 = FILE_PATH_MODELOS + "vegtype_2000.shp";
	public static final String VEGTYPE_2005 = FILE_PATH_MODELOS + "vegtype_2005.shp";
	public static final String VEGTYPE_2010 = FILE_PATH_MODELOS + "vegtype_2010.shp";
	public static final String VEGTYPE_2015 = FILE_PATH_MODELOS + "vegtype_2015.shp";
	public static final String VEGTYPE_2020 = FILE_PATH_MODELOS + "vegtype_2020.shp";
	public static final String VEGTYPE_2025 = FILE_PATH_MODELOS + "vegtype_2025.shp";
	public static final String VEGTYPE_2030 = FILE_PATH_MODELOS + "vegtype_2030.shp";
	public static final String VEGTYPE_2035 = FILE_PATH_MODELOS + "vegtype_2035.shp";
	public static final String VEGTYPE_2040 = FILE_PATH_MODELOS + "vegtype_2040.shp";
	public static final String VEGTYPE_2045 = FILE_PATH_MODELOS + "vegtype_2045.shp";
	public static final String VEGTYPE_2050 = FILE_PATH_MODELOS + "vegtype_2050.shp";
	public static final List<String> vegtypesSequence = Arrays.asList(VEGTYPE_2000, VEGTYPE_2005, VEGTYPE_2010,
			VEGTYPE_2015, VEGTYPE_2020, VEGTYPE_2025, VEGTYPE_2030, VEGTYPE_2035, VEGTYPE_2040, VEGTYPE_2045,
			VEGTYPE_2050);
	/**
	 * Gdal converted data
	 */
	public static final String FILE_PATH_GDAL_TRANSFORM = "data/gdal/";
	public static final String VEGTYPE_2000_GDAL_GEOJSON = FILE_PATH_GDAL_TRANSFORM + "vegtype-gdal.geojson";
	/*
	 * ColorBrewer
	 */
	public static final String COLOR_BREWER_JSON = "data/triangle/colorbrewer.json";
}
