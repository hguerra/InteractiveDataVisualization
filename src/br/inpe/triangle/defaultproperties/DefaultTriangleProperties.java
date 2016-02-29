package br.inpe.triangle.defaultproperties;

import java.awt.Color;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.inpe.triangle.model.Profile;
import br.inpe.worldwind.controller.ShapefileController;
import br.inpe.worldwind.dao.ConnectionFactory;
import br.inpe.worldwind.dao.SGBD;
import br.inpe.worldwind.engine.ShapefileProperties;
import gov.nasa.worldwind.formats.shapefile.Shapefile;
import gov.nasa.worldwind.layers.Layer;

public class DefaultTriangleProperties {
	// default database
	private static final SGBD JDBC_DRIVER = SGBD.POSTGRESQL;
	private static final String IP = "localhost";
	private static final String DATA_BASE = "vegetation_scenario";
	private static final String USER = "postgres";
	private static final String PASSWORD = "11";
	// Singleton
	private static DefaultTriangleProperties uniqueInstance;
	// profile
	private ShapefileProperties shpProperties;
	private Profile profile;
	private boolean kinectEnable = true;

	private DefaultTriangleProperties() {
		this.shpProperties = new ShapefileProperties();
		initProfile();
	}

	public static DefaultTriangleProperties getInstance() {
		if (uniqueInstance == null)
			uniqueInstance = new DefaultTriangleProperties();
		return uniqueInstance;
	}

	public Connection getSession() {
		return this.profile.getDao().getSession();
	}

	public void shutdown(Connection session) {
		this.profile.getDao().shutdown(session);
	}

	public List<Layer> getLayers(String name) {
		return this.profile.getLayer(name);
	}

	public Profile getProfile() {
		return profile;
	}

	public void setKinectEnable(boolean selected) {
		this.kinectEnable = selected;
	}

	public boolean isKinectEnable() {
		return kinectEnable;
	}

	public List<String> getDefaultKeys() {
		return Arrays.asList("vegtype_2000.shp", "vegtype_2005.shp", "vegtype_2010.shp", "vegtype_2015.shp",
				"vegtype_2020.shp", "vegtype_2025.shp", "vegtype_2030.shp", "vegtype_2035.shp", "vegtype_2040.shp",
				"vegtype_2045.shp", "vegtype_2050.shp");
	}

	public List<Layer> addLayers(String name, List<Layer> layers) {
		return this.profile.addLayer(name, layers);
	}

	public void addLayers(List<String> filePath, Map<Double, Color> colors) {
		filePath.forEach(veg -> {
			try {
				String displayName = ShapefileController.getDisplayName(veg);
				Shapefile shp = ShapefileController.createShapefile(veg);
				List<Layer> layers = shapefile2Layers(displayName, shp, colors);
				addLayers(displayName, layers);
			} catch (Exception e) {
				System.err.println(e);
			}
		});
	}

	public void addLayers(List<String> filePath, String attributeName, Color... color) {
		filePath.forEach(veg -> {
			addLayers(veg, attributeName, color);
		});
	}

	public void addLayers(String filePath, String attributeName, Color... color) {
		try {
			String displayName = ShapefileController.getDisplayName(filePath);
			Shapefile shpColors = ShapefileController.createShapefile(filePath);
			Shapefile shapefile = ShapefileController.createShapefile(filePath);
			Map<Double, Color> mapColors = shpProperties.createPolygonColors(shpColors, attributeName, color);
			List<Layer> layers = shapefile2Layers(displayName, shapefile, mapColors);
			addLayers(displayName, layers);
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	public Map<String, List<Layer>> layerFactory(List<String> filePath, String attributeName, Color... color) {
		Map<String, List<Layer>> result = new HashMap<>();
		filePath.forEach(veg -> {
			try {
				String displayName = ShapefileController.getDisplayName(veg);
				Shapefile shpColors = ShapefileController.createShapefile(veg);
				Shapefile shapefile = ShapefileController.createShapefile(veg);
				Map<Double, Color> mapColors = shpProperties.createPolygonColors(shpColors, attributeName, color);
				List<Layer> layers = shapefile2Layers(displayName, shapefile, mapColors);
				result.put(displayName, layers);
			} catch (Exception e) {
				System.err.println(e);
			}
		});
		return result;
	}

	public Map<String, List<Layer>> layerFactory(List<String> filePath, Map<Double, Color> colors) {
		Map<String, List<Layer>> result = new HashMap<>();
		filePath.forEach(veg -> {
			try {
				String displayName = ShapefileController.getDisplayName(veg);
				Shapefile shp = ShapefileController.createShapefile(veg);
				List<Layer> layers = shapefile2Layers(displayName, shp, colors);
				result.put(displayName, layers);
			} catch (Exception e) {
				System.err.println(e);
			}
		});
		return result;
	}

	public List<Layer> shapefile2Layers(String layerName, Shapefile shapefile, Map<Double, Color> colors) {
		List<Layer> layers = new ArrayList<>();
		shpProperties.addRenderablesForPolygon(shapefile, layerName, layers, colors);
		return layers;
	}

	private void initProfile() {
		profile = new Profile();
		profile.setName("DefaultProfile");
		profile.setDao(new ConnectionFactory(JDBC_DRIVER, IP, DATA_BASE, USER, PASSWORD));
		addLayers(DefaultFilePath.vegtypesSequence, DefaultColors.getDefaultColors());
	}
}