package br.inpe.triangle.data;

import br.inpe.triangle.wwj.layer.ShapefileController;
import gov.nasa.worldwind.formats.shapefile.Shapefile;
import gov.nasa.worldwind.formats.shapefile.ShapefileRecord;
import org.apache.commons.math3.stat.Frequency;

/**
 * @author Heitor
 * @since 06/05/2016
 */
public class SimpleDataAnalysis implements DataAnalysis {
    @Override
    public Frequency createFrequency(Data data) {
        try {
            return getFrequency(createShapefileFromData(data), data.getColumn());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Frequency getFrequency(Shapefile shapefile, String column) {
        Frequency frequency = new Frequency();
        while (shapefile.hasNext()) {
            ShapefileRecord record = shapefile.nextRecord();
            record.getAttributes().getEntries().parallelStream()
                    .filter(filterByColumn -> filterByColumn.getKey().equals(column))
                    .forEach(entry -> frequency.addValue((Comparable<?>) entry.getValue()));
        }
        return frequency;
    }

    private Shapefile createShapefileFromData(Data data) throws Exception {
        return ShapefileController.createShapefile(data.getFilepath());
    }


}
