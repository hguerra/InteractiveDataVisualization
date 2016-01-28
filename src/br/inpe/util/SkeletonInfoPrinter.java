package br.inpe.util;

import com.primesense.nite.Point3D;

import br.com.kinect4j.engine.core.listener.Tracking;

public class SkeletonInfoPrinter {
	public static void printPercentage(Tracking tracking) {
		int percentage = tracking.getProgressPercentage();
		if (percentage != 0 && percentage % 10 == 0)
			System.out.println("Percentage:" + percentage + "%");
	}
	
	public static void printPercentage(int percentage) {
		if (percentage != 0 && percentage % 10 == 0)
			System.out.println("Percentage:" + percentage + "%");
	}
	
	public static <T extends Enum<T>> void printPercentage(T name, int percentage) {
		if (percentage != 0 && percentage % 10 == 0)
			System.out.println(name+" :" + percentage + "%");
	}

	public static void printCoords(Point3D<Float> previous, Point3D<Float> actual) {
		StringBuffer json = new StringBuffer();
		json.append("P:");
		json.append("{");
		json.append("x:").append(previous.getX());
		json.append(" y:").append(previous.getY());
		json.append(" z:").append(previous.getZ());
		json.append("}");
		json.append("\n");
		json.append("A:");
		json.append("{");
		json.append("x:").append(actual.getX());
		json.append(" y:").append(actual.getY());
		json.append(" z:").append(actual.getZ());
		json.append("}");
		System.out.println(json.toString());
	}
}
