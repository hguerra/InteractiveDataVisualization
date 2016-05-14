package br.inpe.app.geotools;

import br.inpe.gdal.transform.OGRVectorTest;
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
public class CRS {
    public static void main(String[] args) throws IOException {
        File sourceFile = new File(OGRVectorTest.VEGTYPE_2000);
        FileDataStore store = FileDataStoreFinder.getDataStore(sourceFile);
        SimpleFeatureType schema = store.getSchema();

        CoordinateReferenceSystem crs = schema.getCoordinateReferenceSystem();

        System.out.println(schema.getName());
        System.out.println(crs);
    }
}
