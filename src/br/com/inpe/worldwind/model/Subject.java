package br.com.inpe.worldwind.model;

import java.util.List;
import br.com.inpe.worldwind.database.GeometryRecord;
import br.com.inpe.worldwind.view.Observer;
/**
 * @author Heitor Guerra Carneiro
 * @since May 16, 2015
 * @version 1.0
 */
public interface Subject {
	public void registerObserver(Observer observer);
	public void notifyObserverGeometryRecord(List<GeometryRecord> geometryRecord);
}
