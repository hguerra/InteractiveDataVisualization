package br.inpe.gdal.transform;

import org.junit.BeforeClass;
import org.junit.Test;

import br.inpe.triangle.gdal.OGRInfo;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author Heitor
 * @since 14/05/2016
 */
public class ORGInfoTest {

    private static OGRInfo ogrinfo;

    @BeforeClass
    public static void runBeforeClass() {
        ogrinfo = new OGRInfo();
    }

    @Test
    public void orgInfoTest() throws Exception {
        String inputFile = OGRVectorTest.VEGTYPE_2000;
        String actual = ogrinfo.info(inputFile);
        String expected = "vegtype_2000\nGEOGCS[\"GCS_WGS_1984\", \r\n  DATUM[\"D_WGS_1984\", \r\n    SPHEROID[\"WGS_1984\", 6378137.0, 298.257223563]], \r\n  PRIMEM[\"Greenwich\", 0.0], \r\n  UNIT[\"degree\", 0.017453292519943295], \r\n  AXIS[\"Longitude\", EAST], \r\n  AXIS[\"Latitude\", NORTH]]";
        assertThat(expected, is(actual));
    }
}
