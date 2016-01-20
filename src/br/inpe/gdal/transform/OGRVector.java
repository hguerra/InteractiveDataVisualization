package br.inpe.gdal.transform;

import gdal.util.ogr2ogr;

public class OGRVector {	
	private final static String REPROJECT_SRS = "-t_srs"; //Reproject/transform to this SRS on output
	private final static String CRS = "CRS:84"; //EPSG:4326, WGS84 degrees
	
	public void transform(GeoFormat format, String outputFile,  String inputFile){
		String[] command = {REPROJECT_SRS, CRS, "-f", format.toString(), outputFile+format.getExtension(), inputFile};
		ogr2ogr.main(command);
	}

}
