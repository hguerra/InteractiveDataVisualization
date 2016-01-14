package br.com.util;

import gov.nasa.worldwind.formats.shapefile.Shapefile;
import gov.nasa.worldwind.formats.shapefile.ShapefileRecord;
import gov.nasa.worldwind.geom.LatLon;
import gov.nasa.worldwind.util.VecBuffer;

import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class ShapefileInfo {
	public static void printShapefileAttributs(ShapefileRecord r) {
		for (Map.Entry<String, Object> a : r.getAttributes().getEntries()) {
			if (a.getKey() != null)
				System.out.printf(", %s", a.getKey());
			if (a.getValue() != null)
				System.out.printf(", %s", a.getValue());

		}
	}

	public static void printEntries(Set<Entry<String, Object>> entries) {
		for (Map.Entry<String, Object> a : entries) {
			if (a.getKey() != null)
				System.out.printf(", %s", a.getKey());
			if (a.getValue() != null)
				System.out.printf(", %s", a.getValue());

		}
	}

	public static void printShapefileAttributs(Shapefile shapefile) {
		while (shapefile.hasNext()) {
			ShapefileRecord r = shapefile.nextRecord();
			printShapefileAttributs(r);
		}
	}

	public static void printUniqueShapefileAttributs(Shapefile shapefile,
			String attributeName) {
		Set set = getShapefileUniqueAttributs(shapefile, attributeName);
		for (Object s : set) {
			System.out.print(s + ", ");
		}
	}

	public static Set getShapefileUniqueAttributs(Shapefile shapefile,
			String attributeName) {
		Set values = new HashSet();
		while (shapefile.hasNext()) {
			ShapefileRecord r = shapefile.nextRecord();
			for (Map.Entry<String, Object> a : r.getAttributes().getEntries()) {
				if (a.getKey().equals(attributeName)) {
					if (a.getValue() != null) {
						values.add(a.getValue());
					}
				}
			}
		}
		return values;
	}

	public static void printShapefileInfo(ShapefileRecord r) {
		System.out.printf("%d, %s: %d parts, %d points", r.getRecordNumber(),
				r.getShapeType(), r.getNumberOfParts(), r.getNumberOfPoints());
		for (Map.Entry<String, Object> a : r.getAttributes().getEntries()) {
			if (a.getKey() != null)
				System.out.printf(", %s", a.getKey());
			if (a.getValue() != null)
				System.out.printf(", %s", a.getValue());
		}
		System.out.println();

		System.out.print("\tAttributes: ");
		for (Map.Entry<String, Object> entry : r.getAttributes().getEntries()) {
			System.out.printf("%s = %s, ", entry.getKey(), entry.getValue());
		}
		System.out.println();

		VecBuffer vb = r.getPointBuffer(0);
		for (LatLon ll : vb.getLocations()) {
			System.out.printf("\t%f, %f\n", ll.getLatitude().degrees,
					ll.getLongitude().degrees);
		}
	}

	public static void printShapefileInfo(Shapefile shapefile) {
		while (shapefile.hasNext()) {
			ShapefileRecord r = shapefile.nextRecord();
			printShapefileInfo(r);
		}
	}
}
