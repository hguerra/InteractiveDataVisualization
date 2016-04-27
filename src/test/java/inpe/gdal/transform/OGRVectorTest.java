package inpe.gdal.transform;

import br.inpe.gdal.transform.GeoFormat;
import br.inpe.gdal.transform.OGRVector;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

public class OGRVectorTest {
	public static final String FILE_PATH_GDAL_TRANSFORM = "data/gdal/";
	public static final String FILE_PATH_MODELOS = "data/vegtype-inland/shapefile/";
	public static final String VEGTYPE_2000 = FILE_PATH_MODELOS + "vegtype_2000.shp";
	public static final String OUT_PUT = FILE_PATH_GDAL_TRANSFORM + "vegtype-gdal";
	private OGRVector org;

	@Before
	public void init() {
		org = new OGRVector();
	}

	@Test
	public void shpToKml() {
		org.transform(GeoFormat.KML, OUT_PUT, VEGTYPE_2000);
		Assert.assertEquals(true, foundFile(GeoFormat.KML,OUT_PUT));
	}

	@Test
	public void shpToGeoJson() {
		org.transform(GeoFormat.GEO_JSON, OUT_PUT, VEGTYPE_2000);
		Assert.assertEquals(true, foundFile(GeoFormat.GEO_JSON,OUT_PUT));
	}
	
	@Test
	public void kmlToShp() {
		String kml = FILE_PATH_GDAL_TRANSFORM + "vegtype-gdal.kml";
		org.transform(GeoFormat.SHAPEFILE, OUT_PUT, kml);
		Assert.assertEquals(true, foundFile(GeoFormat.SHAPEFILE,OUT_PUT));
	}

	@Test
	public void reprojectTest() {
		String reproject = FILE_PATH_GDAL_TRANSFORM + "vegtype-gdal-reproject";
		org.reproject(GeoFormat.SHAPEFILE, "4236", reproject, VEGTYPE_2000);
	}
	
	@Test
	public void rasterToShp() {
		String input = "data/vegtype-inland/raster/vegtype_2000.tif";
		String output = "data/gdal/fromTiff.shp";
		org.rasterToShapefile(output, input);
	}
	
	
	@After
	public void destroy() {
		org = null;
	}

	private boolean foundFile(GeoFormat format, String pathname) {
		File file = new File(pathname+format.getExtension());

		if (file.exists() && !file.isDirectory())
			return true;

		return false;
	}

}
