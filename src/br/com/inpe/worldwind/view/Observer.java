package br.com.inpe.worldwind.view;

import java.util.List;
import br.com.inpe.worldwind.database.GeometryRecord;
/**
 * @author Heitor Guerra Carneiro
 * @since May 16, 2015
 * @version 1.0
 */
public interface Observer {
	public void updateGeometryRecordLayer(List<GeometryRecord> geometryRecord);
}
