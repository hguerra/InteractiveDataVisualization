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
public class DataSource {
	@Expose
	private Map<String, Data> dataSet;
	private Map<String, List<Layer>> layers;
	private ShapefileProperties shpProperties;

	public DataSource() {
		this.dataSet = new HashMap<>();
		this.layers = new HashMap<>();
		this.shpProperties = new ShapefileProperties();
	}

	public Data addData(String name, Data data) {
		addLayers(name, data);
		return dataSet.put(name, data);
	}

	public Data addData(String name, GeoFormat format, String filepath, Map<Double, String> colors) {
		Data data = new Data();
		data.setFormat(format);
		data.setFilepath(filepath);
		data.setColors(colors);
		addLayers(name, data);
		return addData(name, data);
	}

	public Data removeData(Data data) {
		return dataSet.remove(data);
	}

	public Map<String, List<Layer>> getLayers() {
		if (!layers.isEmpty())
			return this.layers;
		dataSet.forEach((k, v) -> {
			List<Layer> value = createLayers(v);
			if (!value.isEmpty())
				this.layers.put(k, value);
		});
		return this.layers;
	}

	public List<Layer> addLayers(String name, Data data) {
		List<Layer> value = createLayers(data);
		if (!value.isEmpty())
			return this.layers.put(name, value);
		return null;
	}

	public List<Layer> removeLayers(List<Layer> l) {
		return this.layers.remove(l);
	}

	public List<Layer> createLayers(Data data) {
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

	public Map<String, Data> getDataSet() {
		return dataSet;
	}

	@XmlElement
	public void setDataSet(Map<String, Data> dataSet) {
		this.dataSet = dataSet;
	}
}