package br.inpe.triangle.data;

import br.inpe.triangle.defaultproperties.DefaultDataSource;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Map;

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
     * @param name
     * @return
     */
    public Data getData(String name) {
        return this.sessionDataSource.getDataSet().get(name);
    }

    /**
     * Remove Data
     */
    public Data removeData(Data data) {
        return this.sessionDataSource.removeData(data);
    }

    /**
     * @return
     */
    public Map<String, DataSource> getDataSourceGroup() {
        return dataSourceGroup;
    }

    /**
     * @param group
     * @return
     */
    public DataSource addDataSource(String group, DataSource dataSource) {
        if (!getDataSourceGroup().containsKey(group))
            return getDataSourceGroup().put(group, dataSource);

        DataSource dataSourceFound = getDataSourceGroup().get(group);

        Map<String, Data> actualData = dataSourceFound.getDataSet();
        Map<String, Data> newData = dataSource.getDataSet();

        actualData.putAll(newData);
        dataSourceFound.setDataSet(actualData);
        return dataSourceFound;
    }

    /**
     * @param group
     * @return
     */
    public DataSource getDataSourceFromGroup(String group) {
        return getDataSourceGroup().get(group);
    }

    /**
     * @return
     */
    public ObservableList<String> getTitleFromDataSourceGroup() {
        ObservableList<String> result = FXCollections.observableArrayList();
        getDataSourceGroup().forEach((k, v) -> result.add(k));
        return result;
    }

    /**
     * @param group
     * @return
     */
    public ObservableList<String> getTitleFromDataSourceGroup(String group) {
        ObservableList<String> result = FXCollections.observableArrayList();
        getDataSourceFromGroup(group).getDataSet().forEach((k, v) -> result.add(k));
        return result;
    }

}
