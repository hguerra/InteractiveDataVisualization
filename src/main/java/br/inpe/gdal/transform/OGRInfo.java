package br.inpe.gdal.transform;

import org.geotools.data.FileDataStore;
import org.geotools.data.FileDataStoreFinder;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

import java.io.File;
import java.io.IOException;

/**
 * @author Heitor
 * @since 14/05/2016
 */
public class OGRInfo {
    public String info(String inputFile) throws IOException {
        File sourceFile = new File(inputFile);
        FileDataStore store = FileDataStoreFinder.getDataStore(sourceFile);
        SimpleFeatureType schema = store.getSchema();
        CoordinateReferenceSystem crs = schema.getCoordinateReferenceSystem();

        return String.valueOf(schema.getName()) + "\n" + crs;
    }
}
