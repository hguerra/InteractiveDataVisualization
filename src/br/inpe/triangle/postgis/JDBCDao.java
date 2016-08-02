package br.inpe.triangle.postgis;

import java.awt.Color;
import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.postgis.Geometry;
import org.postgis.PGgeometry;
import org.postgis.Polygon;

import br.inpe.triangle.defaultproperties.DefaultColors;
import br.inpe.triangle.utils.GenericFactory;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.render.BasicShapeAttributes;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.ShapeAttributes;

public class JDBCDao<T extends GeometryRecord> extends JDBCStatement implements Dao<T> {

	@Override
	public void persist(T t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(T t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void remove(T t) {
		// TODO Auto-generated method stub

	}

	@Override
	public T search(Serializable id, Class<?> clazz) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> getAll(Class<?> clazz) {
		List<T> objects = new ArrayList<>();
		try {
			//SQL
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT (ST_Dump(geom)).geom, attr FROM ").append(clazz.getSimpleName());
			//execute query
			ResultSet rs = getResultSet(sql.toString());
			
			Map<Object, Color> defaultColors = DefaultColors.getDefaultColors();
					
			while (rs.next()) {
				PGgeometry geom = (PGgeometry)rs.getObject("geom");
				Double attr = rs.getDouble("attr");
				List<Position> borderPositions = new ArrayList<>();
				
				if (geom.getGeoType() == Geometry.POLYGON) {
					Polygon polygon = (Polygon) geom.getGeometry();
		
					for (int i = 0; i < polygon.numPoints(); i++) {
						double latitude = polygon.getPoint(i).y;
						double longitude = polygon.getPoint(i).x;
						
						borderPositions.add(Position.fromDegrees(latitude,longitude));
					}
				}
				// create instance of GeometryRecord
				@SuppressWarnings("unchecked")
				T g = (T) GenericFactory.getInstance(clazz);
				
				// decorate the polygon
				ShapeAttributes sideAttributes = new BasicShapeAttributes();
				Material interior = new Material(defaultColors.get(attr));
				sideAttributes.setInteriorMaterial(interior);
				sideAttributes.setDrawOutline(false);
				
				// set attributes
				g.setBorderPositions(borderPositions);
				g.setDisplayName(attr.toString());
				g.setSideAttributes(sideAttributes);
				
				// add in the list
				objects.add(g);
			}
			rs.close();
		} catch (Exception e) {
			System.err.println(e);
		}
		return objects;
	}

	@Override
	public List<T> searchFilter(T filter) {
		// TODO Auto-generated method stub
		return null;
	}

}
