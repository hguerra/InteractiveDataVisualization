package test.app;

import java.util.ArrayList;
import java.util.List;

import br.inpe.triangle.defaultproperties.DefaultFilePath;
import br.inpe.util.status.ShapefileInfo;
import br.inpe.worldwind.engine.GeoJSONProperties;
import gov.nasa.worldwind.formats.geojson.GeoJSONObject;
import gov.nasa.worldwind.layers.Layer;
import gov.nasa.worldwind.layers.RenderableLayer;

public class JavaTest {
	public static void main(String[] args) {
		// System.out.println(GenericFactory.getSimpleName(GeometryRecord.class));
		//
		// GeometryRecord g = GenericFactory.getInstance(vegtype_2000.class);
		//
		// g.setDisplayName("Test getInstance");
		//
		// System.out.println(g);
		//
		// System.out.println(g.getDisplayName());

		// JDBCDao<vegtype_2000> dao = new JDBCDao<>();
		//
		// List<vegtype_2000> all = dao.getAll(vegtype_2000.class);
		//
		// for(vegtype_2000 v: all){
		// System.out.println(v.getDisplayName());
		// }
		
		 GeoJSONProperties geo = new GeoJSONProperties("attr");
		
		 List<Layer> layers = new ArrayList<>();
		
		 // transform all GeoJSONObject in layers
		 List<GeoJSONObject> geos =
		 geo.createGeoJSONObjectFromSource(DefaultFilePath.VEGTYPE_2000_GDAL_GEOJSON);
		 geos.forEach(g -> {
		 RenderableLayer layer = new RenderableLayer();
		 geo.addGeoJSONGeometryToLayer(g, layer);
		 layers.add(layer);
		 });
		
		 // layers
		 layers.forEach(l -> {
		 ShapefileInfo.printEntries(l.getEntries());
		 });

		// GeoJson Analysis

//		GeoJsonProperties geo = new GeoJsonProperties("attr");
//		// transform all GeoJSONObject in layers
//		List<GeoJSONObject> geos = geo.createGeoJSONObjectFromSource(FilePathTest.VEGTYPE_2000_GDAL_GEOJSON);
//		geos.forEach(g -> {
//			GeoJSONFeatureCollection c = g.asFeatureCollection();
//			GeoJSONFeature[] features = c.getFeatures();
//
//			for (GeoJSONFeature f : features) {
//				AVList properties = f.getProperties();
//				ShapefileInfo.printEntries(properties.getEntries());
//			}
//		});

	}

}
