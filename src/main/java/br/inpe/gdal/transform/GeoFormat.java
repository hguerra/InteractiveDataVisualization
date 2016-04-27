package br.inpe.gdal.transform;

public enum GeoFormat {
	SHAPEFILE, KML, GEO_JSON;
	
	public String toString() {
		switch (this) {
		case SHAPEFILE: return "ESRI Shapefile";
		case KML: return "KML";
		case GEO_JSON: return "GeoJSON";
		default: return "UNDEFINED";
		}
		
	};
	public String getExtension(){
		switch (this) {
		case SHAPEFILE: return ".shp";
		case KML: return ".kml";
		case GEO_JSON: return ".geojson";
		default: return "UNDEFINED";
		}
	};
}
