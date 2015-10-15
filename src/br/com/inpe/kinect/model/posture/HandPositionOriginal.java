package br.com.inpe.kinect.model.posture;

import java.util.ArrayList;
import java.util.List;

import processing.core.PVector;

public class HandPositionOriginal {
	/* Limit of arraylist */
	private static final int MAX_POINTS = 4;
	private List<PVector> rightHandCoords;
	private List<PVector> leftHandCoords;

	public HandPositionOriginal() {
		rightHandCoords = new ArrayList<PVector>(MAX_POINTS);
		leftHandCoords = new ArrayList<PVector>(MAX_POINTS);
	}

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

}
