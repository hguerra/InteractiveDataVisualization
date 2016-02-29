package br.inpe.triangle.conf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.annotations.Expose;

import br.inpe.gdal.transform.GeoFormat;
import br.inpe.worldwind.controller.ShapefileController;
import br.inpe.worldwind.engine.ShapefileProperties;
import gov.nasa.worldwind.formats.shapefile.Shapefile;
import gov.nasa.worldwind.layers.Layer;

@XmlRootElement
public class TriangleData {
	@Expose
	private Map<String, DataSource> dataSource;
	private Map<String, List<Layer>> layers;
	private ShapefileProperties shpProperties;

	public TriangleData() {
		this.dataSource = new HashMap<>();
		this.layers = new HashMap<>();
		this.shpProperties = new ShapefileProperties();
	}

	public DataSource addDataSource(String name, DataSource data) {
		addLayers(name, data);
		return dataSource.put(name, data);
	}

	public DataSource addDataSource(String name, GeoFormat format, String filepath, Map<Double, String> colors) {
		DataSource data = new DataSource();
		data.setFormat(format);
		data.setFilepath(filepath);
		data.setColors(colors);
		addLayers(name, data);
		return addDataSource(name, data);
	}

	public DataSource removeDataSource(DataSource data) {
		return dataSource.remove(data);
	}

	public Map<String, List<Layer>> getLayers() {
		if (!layers.isEmpty())
			return this.layers;
		dataSource.forEach((k, v) -> {
			List<Layer> value = createLayers(v);
			if (!value.isEmpty())
				this.layers.put(k, value);
		});
		return this.layers;
	}

	public List<Layer> addLayers(String name, DataSource data) {
		List<Layer> value = createLayers(data);
		if (!value.isEmpty())
			return this.layers.put(name, value);
		return null;
	}

	public List<Layer> removeLayers(List<Layer> l) {
		return this.layers.remove(l);
	}

	public List<Layer> createLayers(DataSource data) {
		List<Layer> layer = new ArrayList<>();
		try {
			String displayName = ShapefileController.getDisplayName(data.getFilepath());
			Shapefile shapefile = ShapefileController.createShapefile(data.getFilepath());
			layer = shpProperties.createLayers(displayName, shapefile, data.getAwtColors());
		} catch (Exception e) {
			System.err.println(e);
		}
		return layer;
	}

	public Map<String, DataSource> getDataSource() {
		return dataSource;
	}

	@XmlElement
	public void setDataSource(Map<String, DataSource> dataSource) {
		this.dataSource = dataSource;
	}
}
