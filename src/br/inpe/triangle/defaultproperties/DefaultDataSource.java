package br.inpe.triangle.defaultproperties;

import java.awt.Color;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import com.google.common.base.Splitter;

import br.inpe.triangle.data.Data;
import br.inpe.triangle.data.DataSource;
import br.inpe.triangle.gdal.GeoFormat;
import br.inpe.triangle.utils.ColorMath;
import br.inpe.triangle.wwj.dataaccess.ShapefileProperties;
import br.inpe.triangle.wwj.layer.ShapefileController;

public class DefaultDataSource {
	private static final String VEG_COLUMN_NAME = "attr";
	private static DefaultDataSource uniqueInstance;
	private List<String> filepath;
	private Map<Object, String> vegetationColors;
	private Pattern pattern = Pattern.compile("[_./]", Pattern.CASE_INSENSITIVE);

	private DefaultDataSource() {
		filepath = Arrays.asList("vegtype_2000.shp", "vegtype_2005.shp", "vegtype_2010.shp", "vegtype_2015.shp",
				"vegtype_2020.shp", "vegtype_2025.shp", "vegtype_2030.shp", "vegtype_2035.shp", "vegtype_2040.shp",
				"vegtype_2045.shp", "vegtype_2050.shp");
		this.vegetationColors = new HashMap<>();
		vegetationColors.put(1.0, "#006401");
		vegetationColors.put(2.0, "#388237");
		vegetationColors.put(9.0, "#F07A00");
		vegetationColors.put(10.0, "#D15400");
		vegetationColors.put(11.0, "#B23000");
		vegetationColors.put(12.0, "#930900");
		vegetationColors.put(3.0, "#719F71");
		vegetationColors.put(-127.0, "#000000");
	}

	public static DefaultDataSource getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new DefaultDataSource();
		}
		return uniqueInstance;
	}

	public DataSource createVegetationDataSource() {
		Map<String, Data> dataSet = new HashMap<>();
		filepath.forEach(veg -> {
			try {
				List<String> datasetGroupIterator = Splitter.on(pattern).trimResults().omitEmptyStrings()
						.splitToList(veg);

				if (datasetGroupIterator.size() < 2)
					return;

				String year = datasetGroupIterator.get(1); // year

				Data data = new Data();
				data.setTitle(DefaultDataReferences.vegetationScenarioTitle);
				data.setReference(DefaultDataReferences.vegetationScenarioReference);
				data.setFormat(GeoFormat.SHAPEFILE);
				data.setFilepath(DefaultFilePath.FILE_PATH_MODELOS + veg);
				data.setColors(vegetationColors);
				data.setColumn(VEG_COLUMN_NAME);
				data.setDate(year);
				dataSet.put(year, data);
			} catch (Exception e) {
				System.err.println(e);
			}
		});
		DataSource dataSource = new DataSource();
		dataSource.setDataSet(dataSet);
		return dataSource;
	}

	public DataSource createBrasilDataSource() throws Exception {
		Map<String, Data> dataSet = new HashMap<>();
		String column = "nome";
		String reference = "IBGE, 2010";

		Data regioes = new Data();
		regioes.setTitle(DefaultDataReferences.vegetationScenarioTitle);
		regioes.setReference(reference);
		regioes.setFormat(GeoFormat.SHAPEFILE);
		regioes.setFilepath(DefaultFilePath.REGIOES);

		Set<Object> regioesAttrs = ShapefileProperties
				.getShapefileUniqueAttributes(ShapefileController.createShapefile(regioes.getFilepath()), column);

		regioes.setColors(classifyUsingGeotools(regioesAttrs));
		regioes.setColumn(column);
		regioes.setDate("2010");
		dataSet.put("regioes_2010", regioes);

		Data estados = new Data();
		estados.setTitle(DefaultDataReferences.vegetationScenarioTitle);
		estados.setReference(reference);
		estados.setFormat(GeoFormat.SHAPEFILE);
		estados.setFilepath(DefaultFilePath.ESTADOS);

		Set<Object> estadosAttrs = ShapefileProperties
				.getShapefileUniqueAttributes(ShapefileController.createShapefile(estados.getFilepath()), column);

		estados.setColors(classifyUsingGeotools(estadosAttrs));
		estados.setColumn(column);
		estados.setDate("2010");
		dataSet.put("estados_2010", estados);

		DataSource dataSource = new DataSource();
		dataSource.setDataSet(dataSet);
		return dataSource;
	}

	private Map<Object, String> classifyUsingGeotools(Set<Object> uniqueAttrs) {
		Map<Object, String> result = new HashMap<>();
		Color[] colors = org.geotools.brewer.color.ColorBrewer.instance().getPalette("Spectral").getColors();

		Iterator<Object> attrsIterator = uniqueAttrs.iterator();
		for (Color color : colors) {
			if (attrsIterator.hasNext()) {
				Object unique = attrsIterator.next();
				result.put(unique, ColorMath.toHex(color));
			}
		}
		return result;
	}

	public Map<String, DataSource> createDataSourceGroup() throws Exception {
		Map<String, DataSource> dataSourceGroup = new HashMap<>();
		dataSourceGroup.put(DefaultDataReferences.vegetationScenarioTitle, createVegetationDataSource());
		dataSourceGroup.put("Brasil", createBrasilDataSource());
		return dataSourceGroup;
	}
}
