package br.inpe.gdal.transform;

import gdal.util.ogr2ogr;

public class OGRVector {
	private final static String REPROJECT_SRS = "-t_srs"; // Reproject/transform
															// to this SRS on
															// output
	private final static String CRS = "CRS:84"; // EPSG:4326, WGS84 degrees

	/**
	 * Example Shapefile to GeoJson
	 * 
	 * ogr2ogr -f GeoJSON -t_srs crs:84 [name].geojson [name].shp
	 * 
	 * @param format
	 * @param outputFile
	 * @param inputFile
	 */
	public void transform(GeoFormat format, String outputFile, String inputFile) {
		String[] command = { REPROJECT_SRS, CRS, "-f", format.toString(), outputFile + format.getExtension(),
				inputFile };
		ogr2ogr.main(command);
	}

	/**
	 * Examples vector data:
	 * 
	 * ogr2ogr -f "ESRI Shapefile" original.shp wgs84.shp -s_srs EPSG:27700 -t_srs EPSG:4326
	 * 
	 * @param EPSG_CODE
	 * @param outputFile
	 * @param inputFile
	 */
	public void reproject(GeoFormat format, String EPSG_CODE, String outputFile, String inputFile) {
		String[] command = { REPROJECT_SRS, "EPSG:"+EPSG_CODE, "-f", format.toString(), outputFile +EPSG_CODE+format.getExtension(),
				inputFile };
		ogr2ogr.main(command);
	}

}
