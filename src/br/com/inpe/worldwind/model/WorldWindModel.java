package br.com.inpe.worldwind.model;


import java.util.LinkedList;
import java.util.List;
import br.com.inpe.worldwind.database.DataLoaderScience;
import br.com.inpe.worldwind.database.GeometryRecord;
import br.com.inpe.worldwind.view.Observer;
/**
 * @author Heitor Guerra Carneiro
 * @since May 16, 2015
 * @version 1.0
 */
public class WorldWindModel implements Subject {
	List<GeometryRecord> geometryRecord;
	private List<Observer> observers;
	static DataLoaderScience data;

	public WorldWindModel() {
		data = DataLoaderScience.getInstance();
		geometryRecord = new LinkedList<GeometryRecord>();
		observers = new LinkedList<Observer>();
	}

	@Override
	public void registerObserver(Observer observer) {
		observers.add(observer);
	}

	@Override
	public void notifyObserverGeometryRecord(List<GeometryRecord> geometryRecord) {
		for (Observer o : observers) {
			o.updateGeometryRecordLayer(geometryRecord);
		}
		geometryRecord.clear();
	}

	public void createShapeGreaterMunicipalityArea(long area) {
		final long parameter = area;
		new Thread(new Runnable() {
			@Override
			public void run() {
				geometryRecord = data
						.SearchDataGreaterMunicipalityArea(parameter);
				notifyObserverGeometryRecord(geometryRecord);
			}
		}).start();
	}

	public void createShapeEqualsMunicipalityArea(long area) {
		final long parameter = area;
		new Thread(new Runnable() {
			@Override
			public void run() {
				geometryRecord = data.SearchDataEqualsMunicipalityArea(parameter);
				notifyObserverGeometryRecord(geometryRecord);
			}
		}).start();
	}

	public void createShapeLessMunicipalityArea(long area) {
		final long parameter = area;
		new Thread(new Runnable() {
			@Override
			public void run() {
				geometryRecord = data
						.SearchDataLessMunicipalityArea(parameter);
				notifyObserverGeometryRecord(geometryRecord);
			}
		}).start();
	}
}
