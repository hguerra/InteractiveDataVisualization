package br.inpe.triangle.conf;

import java.util.List;
import java.util.Map;

import br.inpe.triangle.defaultproperties.DefaultDataSource;
import gov.nasa.worldwind.layers.Layer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DataSourceGroup {
	private DataSource sessionDataSource;
	private Map<String, DataSource> dataSourceGroup;

	public DataSourceGroup() {
		this.sessionDataSource = new DataSource();
		this.dataSourceGroup = DefaultDataSource.getInstance().createDataSourceGroup();
	}

	/**
	 * Data Source
	 */
	/**
	 * add data in DataSource
	 * 
	 * @param name
	 * @param data
	 * @return
	 */
	public Data addData(String name, Data data) {
		return this.sessionDataSource.addData(name, data);
	}

	/**
	 * remove Data from memory
	 * 
	 * @param name
	 * @return
	 */
	public Data removeData(String name) {
		return this.sessionDataSource.removeData(name);
	}

	/**
	 * 
	 * @param name
	 * @return
	 */
	public Data getData(String name) {
		return this.sessionDataSource.getDataSet().get(name);
	}

	/**
	 * Get specific list of layers
	 * 
	 * @param title
	 * @return
	 */
	public List<Layer> getLayerFromDataSource(String title) {
		return this.getLayersFromDataSource().get(title);
	}

	/**
	 * Get all layers in memory
	 * 
	 * @return
	 */
	public Map<String, List<Layer>> getLayersFromDataSource() {
		return this.sessionDataSource.getLayers();
	}

	/**
	 * Get Layer Title from DataSource
	 * 
	 * @return
	 */
	public ObservableList<String> getTitleFromDataSource() {
		ObservableList<String> result = FXCollections.observableArrayList();
		this.sessionDataSource.getDataSet().forEach((k, v) -> result.add(k));
		return result;
	}

	/**
	 * DefaultData
	 */

	/**
	 * 
	 * @return
	 */
	public Map<String, DataSource> getDataSourceGroup() {
		return dataSourceGroup;
	}

	/**
	 * 
	 * @param group
	 * @return
	 */
	public DataSource addDataSource(String group, DataSource dataSource) {
		return this.getDataSourceGroup().put(group, dataSource);
	}

	/**
	 * 
	 * @param group
	 * @return
	 */
	public DataSource removeDataSource(String group) {
		return this.getDataSourceGroup().remove(group);
	}

	/**
	 * 
	 * @param group
	 * @return
	 */
	public DataSource getDataSourceFromGroup(String group) {
		return getDataSourceGroup().get(group);
	}

	/**
	 * 
	 * @return
	 */
	public ObservableList<String> getTitleFromDataSourceGroup() {
		ObservableList<String> result = FXCollections.observableArrayList();
		getDataSourceGroup().forEach((k, v) -> result.add(k));
		return result;
	}

	/**
	 * 
	 * @param group
	 * @return
	 */
	public ObservableList<String> getTitleFromDataSourceGroup(String group) {
		ObservableList<String> result = FXCollections.observableArrayList();
		getDataSourceFromGroup(group).getDataSet().forEach((k, v) -> result.add(k));
		return result;
	}

}
