package br.inpe.triangle.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.inpe.worldwind.dao.ConnectionFactory;
import gov.nasa.worldwind.layers.Layer;

public class Profile implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private ConnectionFactory dao;
	private Map<String, List<Layer>> layers;

	public Profile() {
		this.layers = new HashMap<>();
	}

	public List<Layer> addLayer(String name, List<Layer> layer) {
		return this.layers.put(name, layer);
	}

	public List<Layer> removeLayer(String name) {
		return this.layers.remove(name);
	}

	public List<Layer> getLayer(String name) {
		return this.layers.get(name);
	}

	/**
	 * Getters and Setters
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ConnectionFactory getDao() {
		return dao;
	}

	public void setDao(ConnectionFactory dao) {
		this.dao = dao;
	}
	
	public Map<String, List<Layer>> getLayers() {
		return layers;
	}

}
