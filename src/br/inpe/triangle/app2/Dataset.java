package br.inpe.triangle.app2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import br.inpe.triangle.data.Data;
import br.inpe.triangle.data.DataSource;
import br.inpe.triangle.wwj.dataaccess.ShapefileProperties;
import br.inpe.triangle.wwj.layer.ShapefileController;
import gov.nasa.worldwind.formats.shapefile.Shapefile;
import gov.nasa.worldwind.layers.AnnotationLayer;
import gov.nasa.worldwind.layers.Layer;
import gov.nasa.worldwind.layers.RenderableLayer;

public class Dataset {
	private String group;
	private Map<Integer, RenderableLayer> layers = new HashMap<>();
	private Map<Integer, AnnotationLayer> annotations = new HashMap<>();

	public String getGroup() {
		return group;
	}

	public Map<Integer, RenderableLayer> getLayers() {
		return layers;
	}

	public Map<Integer, AnnotationLayer> getAnnotations() {
		return annotations;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	static class Builder {
		private ShapefileProperties shapefileProperties = new ShapefileProperties();
		private Dataset dataset = new Dataset();

		public Builder group(String group) {
			dataset.setGroup(group);
			return this;
		}

		public Builder data(DataSource dataSource) {
			int key = 0;
			Map<String, Data> sortedDatasource = new TreeMap<String, Data>(dataSource.getDataSet());
			for (Entry<String, Data> entry : sortedDatasource.entrySet()) {
				try {
					Data data = entry.getValue();

					List<Layer> populateLayers = new ArrayList<Layer>();
					Shapefile shp = ShapefileController.createShapefile(data.getFilepath());
					shapefileProperties.addRenderablesForPolygon(shp, data.getTitle(), data.getColumn(), populateLayers,
							data.getAwtColors());

					RenderableLayer layer = (RenderableLayer) populateLayers.get(0);
					AnnotationLayer annotation = new br.inpe.triangle.wwj.layer.impl.AnnotationLayer(null, data,
							data.getDate()).buildAnnotation().get();

					dataset.getLayers().put(key, layer);
					dataset.getAnnotations().put(key, annotation);
					key += 1;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return this;
		}

		public Dataset get() {
			return dataset;
		}
	}

}
