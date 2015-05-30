package br.com.inpe.worldwind.database;

import java.util.LinkedList;
import java.util.List;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;
/**
 * @author Heitor Guerra Carneiro
 * @since May 16, 2015
 * @version 1.0
 */
public class DataLoaderScience {
	private ObjectContainer dataBase;
	// Singleton
	private static DataLoaderScience uniqueInstance;

	private DataLoaderScience() {
		dataBase = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(),
				"database/data.db4o");
	}

	// Singleton
	public static DataLoaderScience getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new DataLoaderScience();
		}
		return uniqueInstance;
	}

	public void addData(GeometryRecord geometryRecord) {
		dataBase.store(geometryRecord);
	}

	public List<GeometryRecord> SearchDataMunicipalityName(String parameter) {
		Query query = dataBase.query();
		query.constrain(GeometryRecord.class);
		ObjectSet<GeometryRecord> queryList = query.execute();
		List<GeometryRecord> result = new LinkedList<GeometryRecord>();
		for (GeometryRecord geo : queryList) {
			if (geo.matchesMunicipalityName(parameter))
				result.add(geo);
		}
		return result;
	}

	public List<GeometryRecord> SearchDataEqualsMunicipalityArea(long parameter) {
		Query query = dataBase.query();
		query.constrain(GeometryRecord.class);
		ObjectSet<GeometryRecord> queryList = query.execute();
		List<GeometryRecord> result = new LinkedList<GeometryRecord>();
		for (GeometryRecord geo : queryList) {
			if (geo.matchesEqualsMunicipalityArea(parameter))
				result.add(geo);
		}
		return result;
	}

	public List<GeometryRecord> SearchDataGreaterMunicipalityArea(long parameter) {
		Query query = dataBase.query();
		query.constrain(GeometryRecord.class);
		ObjectSet<GeometryRecord> queryList = query.execute();
		List<GeometryRecord> result = new LinkedList<GeometryRecord>();
		for (GeometryRecord geo : queryList) {
			if (geo.matchesGreaterMunicipalityArea(parameter))
				result.add(geo);
		}
		return result;
	}

	public List<GeometryRecord> SearchDataLessMunicipalityArea(long parameter) {
		Query query = dataBase.query();
		query.constrain(GeometryRecord.class);
		ObjectSet<GeometryRecord> queryList = query.execute();
		List<GeometryRecord> result = new LinkedList<GeometryRecord>();
		for (GeometryRecord geo : queryList) {
			if (geo.matchesLessMunicipalityArea(parameter))
				result.add(geo);
		}
		return result;
	}

	public List<GeometryRecord> SearchDataGeometry(String parameter) {
		Query query = dataBase.query();
		query.constrain(GeometryRecord.class);
		ObjectSet<GeometryRecord> queryList = query.execute();
		List<GeometryRecord> result = new LinkedList<GeometryRecord>();
		for (GeometryRecord geo : queryList) {
			if (geo.matchesGeometry(parameter))
				result.add(geo);
		}
		return result;
	}

	public void removeDataMunicipalityName(String parameter) {
		List<GeometryRecord> result = SearchDataMunicipalityName(parameter);
		for (GeometryRecord geo : result) {
			dataBase.delete(geo);
		}
	}

	public void removeDataMunicipalityArea(long parameter) {
		List<GeometryRecord> result = SearchDataEqualsMunicipalityArea(parameter);
		for (GeometryRecord geo : result) {
			dataBase.delete(geo);
		}
	}

	public void removeSearchDataGeometry(String parameter) {
		List<GeometryRecord> result = SearchDataGeometry(parameter);
		for (GeometryRecord geo : result) {
			dataBase.delete(geo);
		}
	}
}
