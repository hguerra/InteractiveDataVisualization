package br.com.implementations.test;

import java.util.Comparator;

import processing.core.PVector;

public class PVectorTransform implements Comparable<PVectorTransform>{
	private Float x;
	private Float y;
	private Float z;

	public PVectorTransform(PVector vector) {
		this.x = vector.x;
		this.y = vector.y;
		this.z = vector.z;
	}

	public Float getX() {
		return x;
	}

	public void setX(Float x) {
		this.x = x;
	}

	public Float getY() {
		return y;
	}

	public void setY(Float y) {
		this.y = y;
	}

	public Float getZ() {
		return z;
	}

	public void setZ(Float z) {
		this.z = z;
	}

	@Override
	public int compareTo(PVectorTransform o) {
		// TODO Auto-generated method stub
		return 0;
	}

}

class CompareX implements Comparator<PVectorTransform> {
	@Override
	public int compare(PVectorTransform o1, PVectorTransform o2) {
		return o1.getX().compareTo(o2.getX());
	}
}

class CompareY implements Comparator<PVectorTransform> {

	@Override
	public int compare(PVectorTransform o1, PVectorTransform o2) {
		return o1.getY().compareTo(o2.getY());
	}
}

class CompareZ implements Comparator<PVectorTransform> {
	@Override
	public int compare(PVectorTransform o1, PVectorTransform o2) {
		return o1.getZ().compareTo(o2.getZ());
	}
}
