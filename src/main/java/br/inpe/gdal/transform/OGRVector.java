package br.inpe.gdal.transform;

import org.gdal.gdal.Band;
import org.gdal.gdal.Dataset;
import org.gdal.gdal.gdal;
import org.gdal.gdalconst.gdalconstConstants;
import org.gdal.ogr.DataSource;
import org.gdal.ogr.FieldDefn;
import org.gdal.ogr.Layer;
import org.gdal.ogr.ogr;
import org.gdal.osr.SpatialReference;

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
	 * ogr2ogr -f "ESRI Shapefile" original.shp wgs84.shp -s_srs EPSG:27700
	 * -t_srs EPSG:4326
	 * 
	 * @param EPSG_CODE
	 * @param outputFile
	 * @param inputFile
	 */
	public void reproject(GeoFormat format, String EPSG_CODE, String outputFile, String inputFile) {
		String[] command = { REPROJECT_SRS, "EPSG:" + EPSG_CODE, "-f", format.toString(),
				outputFile + EPSG_CODE + format.getExtension(), inputFile };
		ogr2ogr.main(command);
	}

	/**
	 * Clip raster to SHP / NoData for pixels beyond polygon boundary
	 * 
	 * gdalwarp -dstnodata <nodata_value> -cutline input_polygon.shp input.tif
	 * clipped_output.tif Crop raster dimensions to vector bounding box
	 * 
	 * gdalwarp -cutline cropper.shp -crop_to_cutline input.tif
	 * cropped_output.tif
	 */

	public void rasterToShapefile(String outputFile, String inpuFile) {
        String[] temp = {inpuFile};
        gdalPolygonize(temp, outputFile);
	}

	private void gdalPolygonize(String[] args, String outputFile) {
		gdal.AllRegister();
		ogr.RegisterAll();
		args = gdal.GeneralCmdLineProcessor(args);

		// Open source file
		Dataset hDataset = gdal.Open(args[0], gdalconstConstants.GA_ReadOnly);
		Band rasterBand = hDataset.GetRasterBand(1);
		Band maskBand = rasterBand.GetMaskBand();

		org.gdal.ogr.Driver driver = ogr.GetDriverByName("ESRI Shapefile");
		DataSource dataSource = driver.CreateDataSource(outputFile);

		SpatialReference srs = null;
		if (!hDataset.GetProjectionRef().isEmpty())
			srs = new SpatialReference(hDataset.GetProjectionRef());

		Layer outputLayer = dataSource.CreateLayer("destination", srs);
		FieldDefn field_def = new FieldDefn("attr", ogr.OFTInteger);
		outputLayer.CreateField(field_def);
		gdal.Polygonize(rasterBand, maskBand, outputLayer, 0);
	}

}
