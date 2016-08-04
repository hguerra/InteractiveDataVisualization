package br.inpe.triangle.app2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gov.nasa.worldwind.layers.AnnotationLayer;
import gov.nasa.worldwind.layers.RenderableLayer;

public class DatasetController {
	private static DatasetController uniqueInstance = new DatasetController();
	private int acualGroup;
	private int actualYear;
	private int maxYear;
	private Dataset actualDataset;

	private List<String> groups;
	private Map<String, Dataset> dataset;

	private DatasetController() {
		this.acualGroup = 0;
		this.actualYear = 0;
		this.maxYear = 0;
		this.groups = new ArrayList<>();
		this.dataset = new HashMap<>();
	}

	public static DatasetController getInstance() {
		return uniqueInstance;
	}

	public Dataset addDataset(Dataset set) {
		groups.add(set.getGroup());
		actualDataset = set;
		maxYear = actualDataset.getLayers().size() - 1;
		return dataset.put(set.getGroup(), set);
	}

	public RenderableLayer getLayer() {
		return actualDataset.getLayers().get(actualYear);
	}

	public AnnotationLayer getAnnotation() {
		return actualDataset.getAnnotations().get(actualYear);
	}

	public void dataBackward() {
		acualGroup -= 1;
		if (acualGroup < 0)
			acualGroup = groups.size() - 1;
		resestYear();
	}

	public void dataForward() {
		acualGroup += 1;
		if (acualGroup > groups.size() - 1)
			acualGroup = 0;
		resestYear();
	}

	public void yearBackward() {
		actualYear -= 1;
		if (actualYear < 0)
			actualYear = maxYear;
	}

	public void yearForward() {
		actualYear += 1;
		if (actualYear > maxYear)
			actualYear = 0;
	}

	private void resestYear() {
		actualYear = 0;
		actualDataset = dataset.get(groups.get(acualGroup));
		maxYear = actualDataset.getLayers().size() - 1;
	}

}
