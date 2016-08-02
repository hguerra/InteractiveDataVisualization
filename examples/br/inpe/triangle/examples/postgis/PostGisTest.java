package br.inpe.triangle.examples.postgis;

import org.postgis.Geometry;
import org.postgis.PGgeometry;
import org.postgis.Polygon;
import org.postgresql.util.PGobject;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * 
 * @author Heitor
 *
 *
 *         --SELECT ST_AsText(geom) FROM vegtype_2000; -- return
 *         "MULTIPOLYGON(((-61 5,-60.5 5,-60.5 4.5,-61 4.5,-61 5)))"
 * 
 *         --SELECT st_x(st_centroid(geom)),st_y(st_centroid(geom)),
 *         ST_AsText(geom) FROM vegtype_2000; -- return -60.75, 4.75,
 *         "MULTIPOLYGON(((-61 5,-60.5 5,-60.5 4.5,-61 4.5,-61 5)))"
 * 
 *         --SELECT ST_AsText( (ST_Dump(geom)).geom ) FROM vegtype_2000;
 *         --return "POLYGON((-61 5,-60.5 5,-60.5 4.5,-61 4.5,-61 5))"
 *
 *
 *
 *
 */
public class PostGisTest {
	public static void main(String[] args) {
		java.sql.Connection conn;
		try {
			/*
			 * Load the JDBC driver and establish a connection.
			 */
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://localhost:5432/vegetation_scenario";
			conn = DriverManager.getConnection(url, "postgres", "postgres");
			/*
			 * Add the geometry types to the connection. Deve converter a
			 * conex�o com a conex�o espec�fica pgsql implementation before
			 * calling the addDataType() method.
			 */
			((org.postgresql.PGConnection) conn).addDataType("geometry", (Class<? extends PGobject>) Class.forName("org.postgis.PGgeometry"));
			((org.postgresql.PGConnection) conn).addDataType("box3d", (Class<? extends PGobject>) Class.forName("org.postgis.PGbox3d"));
			/*
			 * Create a statement and execute a select query.
			 */
			Statement s = conn.createStatement();

			ResultSet r = s.executeQuery("SELECT (ST_Dump(geom)).geom, attr FROM vegtype_2000");
			while (r.next()) {
				/*
				 * Retrieve the geometry as an object then cast it to the
				 * geometry type. Print things out.
				 */

				/**
				 * select geom,attr from vegtype_2000
				 */
				// PGgeometry geom = (PGgeometry) r.getObject(1);

				// int attr = r.getInt(2);

				// System.out.println("Attr " + attr + ":");
				// System.out.println(geom.toString());
				// System.out.println(geom.getGeometry().getType());
				
				/**
				 * SELECT ST_AsText((ST_Dump(geom)).geom) FROM vegtype_2000
				 */
				// String area = r.getString("ST_AsText");
				// System.out.println(area);

				/**
				 * SELECT (ST_Dump(geom)).geom, attr FROM vegtype_2000
				 */
				PGgeometry geom = (PGgeometry)r.getObject("geom");
				int attr = r.getInt("attr");
			
				if (geom.getGeoType() == Geometry.POLYGON) {
					Polygon polygon = (Polygon) geom.getGeometry();
					for (int i = 0; i < polygon.numPoints(); i++) {
						System.out.println("-->" + polygon.getPoint(i).y + ", " + polygon.getPoint(i).x);
					}
				}


			}
			s.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
