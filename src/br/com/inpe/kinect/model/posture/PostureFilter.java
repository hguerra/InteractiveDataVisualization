package br.com.inpe.kinect.model.posture;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import br.com.inpe.kinect.model.Subject;

public class PostureFilter implements IFilter {
	private static final int MAX_POINTS = 2;
	private static final int DELTA_T = 5000;// milliseconds
	private boolean toggleDeltaT = true;
	private Set<EPostureType> postures;
	private Subject kinectModel;

	public PostureFilter(Subject subject) {
		postures = new LinkedHashSet<EPostureType>(MAX_POINTS);
		this.kinectModel = subject;
	}

	public void addPostures(EPostureType type) {
		if (isGreaterThanMaxPoints()) {
			removeFirst();
		}
		postures.add(type);
	}

	@Override
	public void updatePosture(EPostureType type) {
		if(isToggleDeltaT()){
			addPostures(type);
			kinectModel.notifyObserverPosture(getFirst());// after that, deltaT
			deltaT(true);
		}
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

	/**
	 * Timer
	 */
	public void deltaT(boolean event) {
		if (event) {
			final long initialDelay = 0; // time to start counter
			setToggleDeltaT(false);
			final Timer t = new Timer();
			t.schedule(new TimerTask() {
				@Override
				public void run() {
					setToggleDeltaT(true);
					/**
					 * After cancel task
					 */
					t.cancel();
				}
			}, initialDelay, DELTA_T);
		}
	}

	public boolean isToggleDeltaT() {
		return toggleDeltaT;
	}

	public void setToggleDeltaT(boolean toggleDeltaT) {
		this.toggleDeltaT = toggleDeltaT;
	}

}
