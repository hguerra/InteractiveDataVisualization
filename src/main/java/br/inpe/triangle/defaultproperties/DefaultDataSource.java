package br.inpe.triangle.defaultproperties;

import br.inpe.gdal.transform.GeoFormat;
import br.inpe.triangle.conf.Data;
import br.inpe.triangle.conf.DataSource;
import br.inpe.worldwind.controller.ShapefileController;
import br.inpe.worldwind.engine.ShapefileProperties;
import com.google.common.base.Splitter;
import gov.nasa.worldwind.formats.shapefile.Shapefile;
import gov.nasa.worldwind.layers.Layer;

import java.awt.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class DefaultDataSource {
    private static final String VEG_COLUMN_NAME = "attr";
    private static DefaultDataSource uniqueInstance;
    private List<String> filepath;
    private Map<Object, String> colors;
    private ShapefileProperties prop;
    private Pattern pattern = Pattern.compile("[_./]", Pattern.CASE_INSENSITIVE);

    private DefaultDataSource() {
        filepath = Arrays.asList("vegtype_2000.shp", "vegtype_2005.shp", "vegtype_2010.shp", "vegtype_2015.shp",
                "vegtype_2020.shp", "vegtype_2025.shp", "vegtype_2030.shp", "vegtype_2035.shp", "vegtype_2040.shp",
                "vegtype_2045.shp", "vegtype_2050.shp");
        this.colors = new HashMap<>();
        colors.put(1.0, "#006401");
        colors.put(2.0, "#388237");
        colors.put(9.0, "#F07A00");
        colors.put(10.0, "#D15400");
        colors.put(11.0, "#B23000");
        colors.put(12.0, "#930900");
        colors.put(3.0, "#719F71");
        colors.put(-127.0, "#000000");
        this.prop = new ShapefileProperties();
    }

    public static DefaultDataSource getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new DefaultDataSource();
        }
        return uniqueInstance;
    }

    public DataSource createDefaultDataSource() {
        Map<String, Data> dataSet = new HashMap<>();
        filepath.forEach(veg -> {
            try {
                List<String> datasetGroupIterator = Splitter.on(pattern)
                        .trimResults()
                        .omitEmptyStrings()
                        .splitToList(veg);

                if (datasetGroupIterator.size() < 2)
                    return;
                String year = datasetGroupIterator.get(1); // year

                Data data = new Data();
                data.setTitle(DefaultDataReferences.vegetationScenarioTitle);
                data.setReference(DefaultDataReferences.vegetationScenarioReference);
                data.setFormat(GeoFormat.SHAPEFILE);
                data.setFilepath(DefaultFilePath.FILE_PATH_MODELOS + veg);
                data.setColors(colors);
                data.setColumn(VEG_COLUMN_NAME);
                dataSet.put(year, data);
            } catch (Exception e) {
                System.err.println(e);
            }
        });
        DataSource dataSource = new DataSource();
        dataSource.setDataSet(dataSet);
        return dataSource;
    }

    public Map<String, DataSource> createDataSourceGroup() {
        Map<String, DataSource> dataSourceGroup = new HashMap<>();
        dataSourceGroup.put(DefaultDataReferences.vegetationScenarioTitle, createDefaultDataSource());
        return dataSourceGroup;
    }

    public Map<String, List<Layer>> layerFactory(List<String> filePath, String attributeName, Color... color) {
        Map<String, List<Layer>> result = new HashMap<>();
        filePath.forEach(veg -> {
            try {
                String displayName = ShapefileController.getDisplayName(veg);
                Shapefile shpColors = ShapefileController.createShapefile(veg);
                Shapefile shapefile = ShapefileController.createShapefile(veg);
                Map<Object, Color> mapColors = prop.createPolygonColors(shpColors, attributeName, color);
                List<Layer> l = prop.createLayers(displayName, attributeName, shapefile, mapColors);
                result.put(displayName, l);
            } catch (Exception e) {
                System.err.println(e);
            }
        });
        return result;
    }

    public Map<String, List<Layer>> layerFactory(List<String> filePath, String attributeName,
                                                 Map<Object, Color> colors) {
        Map<String, List<Layer>> result = new HashMap<>();
        filePath.forEach(veg -> {
            try {
                String displayName = ShapefileController.getDisplayName(veg);
                Shapefile shp = ShapefileController.createShapefile(veg);
                List<Layer> l = prop.createLayers(displayName, attributeName, shp, colors);
                result.put(displayName, l);
            } catch (Exception e) {
                System.err.println(e);
            }
        });
        return result;
    }
}
