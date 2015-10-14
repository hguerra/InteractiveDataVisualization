package br.com.inpe.kinect.model.posture;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import processing.core.PVector;

public class HandPosition {
	/* Limit of arraylist */
	private static final int MAX_POINTS = 4;
	private List<PVector> rightHandCoords;
	private List<PVector> leftHandCoords;
	/**
	 * Test
	 */
	private Collection<String> collectionRightHandCoords;

	public HandPosition() {
		rightHandCoords = new ArrayList<PVector>(MAX_POINTS);
		leftHandCoords = new ArrayList<PVector>(MAX_POINTS);
		
		collectionRightHandCoords = new LinkedHashSet<String>();
	}
/*
	public synchronized void addRightHandPoint(PVector realtPoints) {
		rightHandCoords.add(realtPoints);

		if (rightHandCoords.size() > MAX_POINTS) {
			rightHandCoords.remove(0);
		}
	}

	public synchronized void addLeftHandPoint(PVector realtPoints) {
		leftHandCoords.add(realtPoints);
		if (leftHandCoords.size() > MAX_POINTS) {
			leftHandCoords.remove(0);
		}
	}

	public PVector getLeftHandCoords() {
		if (leftHandCoords.size() >= MAX_POINTS) {
			return leftHandCoords.get(MAX_POINTS - 1);
		} else {
			return leftHandCoords.get(0);
		}
	}

	public PVector getLeftHandPreviousCoords() {
		return leftHandCoords.get(0);
	}

	public PVector getRightHandPreviousCoords() {
		return rightHandCoords.get(0);
	}

	public PVector getRightHandCoords() {
		if (rightHandCoords.size() >= MAX_POINTS) {
			return rightHandCoords.get(MAX_POINTS - 1);
		} else {
			return rightHandCoords.get(0);
		}
	}
*/
	public boolean isGreaterThanMaxPoints(){
		return collectionRightHandCoords.size()>MAX_POINTS;
	}
	public boolean isGreaterEqualsMaxPoints(){
		return collectionRightHandCoords.size()>=MAX_POINTS;
	}
	
	public void removeFirst(){
		for (Iterator<String> iterator = collectionRightHandCoords.iterator(); iterator
				.hasNext();) {
			collectionRightHandCoords.remove(iterator.next());
			break;
		}
	}
	
	public String getFirst(){
		String firstElement = "";
		for (Iterator<String> iterator = collectionRightHandCoords.iterator(); iterator
				.hasNext();) {
			firstElement = iterator.next();
			break;
		}
		return firstElement;
	}
	
	public String getLast(){
		String str = (String) collectionRightHandCoords.toArray()[collectionRightHandCoords.size() - 1];
		return !collectionRightHandCoords.isEmpty() ? str : "";
	}
	
	public String PVectorToString(PVector realPoints){
		StringBuffer sb = new StringBuffer();
		sb.append(realPoints.y);
		sb.append(";");
		sb.append(realPoints.x);
		return sb.toString();
	}
	
	public synchronized void addRightHandPoint(PVector realPoints) {
		if(isGreaterThanMaxPoints())
			removeFirst();
		collectionRightHandCoords.add(PVectorToString(realPoints));
	}

	public String getRightHandPreviousCoords() {
		return getFirst();
	}

	public String getRightHandCoords() {
		if (isGreaterEqualsMaxPoints()) {
			return getLast();
		} else {
			return getFirst();
		}
	}
	
	public String[] getValuesDeltaX(){
		String[] coords = getRightHandCoords().split(";");
		String[] previous = getRightHandPreviousCoords().split(";");
		String[] result = {coords[1], previous[1]};
		return result;
	}
	
	public String[] getValuesDeltaY(){
		String[] coords = getRightHandCoords().split(";");
		String[] previous = getRightHandPreviousCoords().split(";");
		String[] result = {coords[0], previous[0]};
		return result;
	}
	
	public double getRightDeltaX() {
		Double coords = Double.parseDouble(getValuesDeltaX()[0]);
		Double previous = Double.parseDouble(getValuesDeltaX()[1]);
		return coords - previous;
	}

	public double getRightDeltaY() {
		Double coords = Double.parseDouble(getValuesDeltaY()[0]);
		Double previous = Double.parseDouble(getValuesDeltaY()[1]);
		return coords - previous;
	}

}
