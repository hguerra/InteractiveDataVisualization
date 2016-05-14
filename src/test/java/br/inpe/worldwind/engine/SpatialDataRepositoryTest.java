package br.inpe.worldwind.engine;

import br.inpe.worldwind.dao.SpatialDataRepository;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.fail;

/**
 * @author Heitor
 * @since 06/05/2016
 */
public class SpatialDataRepositoryTest {
    private SpatialDataRepository spatialDataRepository;

    @BeforeClass
    public void runBeforeClass() {
        this.spatialDataRepository = new SpatialDataRepository();
    }

    @AfterClass
    public void runAfterClass() {
        this.spatialDataRepository = null;

    }

    @Test
    public void exportShapefileFromPostGISTest() {
        fail("TODO");
    }

}
