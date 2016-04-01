package br.inpe.triangle.defaultproperties;

import java.awt.Color;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.inpe.gdal.transform.GeoFormat;
import br.inpe.triangle.conf.Data;
import br.inpe.triangle.conf.DataSource;
import br.inpe.worldwind.controller.ShapefileController;
import br.inpe.worldwind.engine.ShapefileProperties;
import gov.nasa.worldwind.formats.shapefile.Shapefile;
import gov.nasa.worldwind.layers.Layer;

public class DefaultDataSource {
	private static DefaultDataSource uniqueInstance;
	private List<String> filepath;
	private Map<Object, String> colors;
	private ShapefileProperties prop;

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
				Data data = new Data();
				data.setFilepath(DefaultFilePath.FILE_PATH_MODELOS + veg);
				data.setFormat(GeoFormat.SHAPEFILE);
				data.setColors(colors);
				dataSet.put(veg, data);
			} catch (Exception e) {
				System.err.println(e);
			}
		});
		DataSource dataSource = new DataSource();
		dataSource.setDataSet(dataSet);
		return dataSource;
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
