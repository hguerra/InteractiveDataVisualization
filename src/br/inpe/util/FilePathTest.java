package br.inpe.util;

import java.util.Arrays;
import java.util.List;

public class FilePathTest {

	// Ana dados
	public static final String FILE_PATH_ERROR_SHAPEFILE = "C:\\Users\\Heitor\\Desktop\\INPE\\Dados\\teste\\PLANO_D_SceB_total_pol.shp"; // UFEBRASIL
	public static final String FILE_PATH_DADOS_ANA_PART_1 = "data/LuccME/Triangle_SceB_novaproj_part1.shp";
	public static final String FILE_PATH_DADOS_ANA_PART_2 = "data/LuccME/Triangle_SceB_novaproj_part2.shp";
	// Manuel dados
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
	// Gdal convert
	public static final String FILE_PATH_GDAL_TRANSFORM = "data/gdal/";
	public static final String VEGTYPE_2000_GDAL = FILE_PATH_GDAL_TRANSFORM + "fromTiff.shp";

	public static final List<String> vegtypesSample = Arrays.asList(VEGTYPE_2000, VEGTYPE_2005, VEGTYPE_2010,
			VEGTYPE_2015, VEGTYPE_2020, VEGTYPE_2025, VEGTYPE_2030, VEGTYPE_2035, VEGTYPE_2040, VEGTYPE_2045,
			VEGTYPE_2050);

}
