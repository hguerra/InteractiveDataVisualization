package br.com.inpe.kinect.model;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import br.com.inpe.kinect.model.gesture.detector.EPostureType;

public class PostureFilter {
	private static final int MAX_POINTS = 2;
	private Set<EPostureType> postures;

	public PostureFilter() {
		postures = new LinkedHashSet<EPostureType>(MAX_POINTS);

	}

	public void addPostures(EPostureType type) {
		if (isGreaterThanMaxPoints()) {
			removeFirst();
		}
		postures.add(type);
	}

	public EPostureType getFirst() {
		EPostureType firstElement = EPostureType.UNDEFINED;
		for (Iterator<EPostureType> iterator = postures.iterator(); iterator
				.hasNext();) {
			firstElement = iterator.next();
			break;
		}
		return firstElement;
	}

	public EPostureType getLast() {
		EPostureType lastElement = (EPostureType) postures.toArray()[postures
				.size() - 1];
		return !postures.isEmpty() ? lastElement : EPostureType.UNDEFINED;
	}

	public void removeFirst() {
		for (Iterator<EPostureType> iterator = postures.iterator(); iterator
				.hasNext();) {
			postures.remove(iterator.next());
			break;
		}
	}

	public boolean isGreaterThanMaxPoints() {
		return postures.size() > MAX_POINTS;
	}

	public boolean isGreaterEqualsMaxPoints() {
		return postures.size() >= MAX_POINTS;
	}

}
