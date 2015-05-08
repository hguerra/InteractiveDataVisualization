package testeGestureDetectorTimer;

import java.util.concurrent.TimeUnit;

public class Timer {
	private Long initialTime;

	// Begin
	public void startCounter() {
		long t1 = System.nanoTime();
		setInitialTime(t1);
	}

	// End
	public Long endCounterSeconds() {
		Long t2 = System.nanoTime();
		Long elapsedTime = (t2 - getInitialTime());
		Long seconds = TimeUnit.SECONDS.convert(elapsedTime,
				TimeUnit.NANOSECONDS);
		return seconds;
	}

	// End
	public boolean endCounterSeconds(int s) {
		Long t2 = System.nanoTime();
		Long elapsedTime = (t2 - getInitialTime());
		Long seconds = TimeUnit.SECONDS.convert(elapsedTime,
				TimeUnit.NANOSECONDS);
		if (seconds > s)
			return true;
		return false;
	}
	
	// End
		public Long endCounterMillis() {
			Long t2 = System.nanoTime();
			Long elapsedTime = (t2 - getInitialTime());
			Long millis = TimeUnit.MILLISECONDS.convert(elapsedTime,
					TimeUnit.NANOSECONDS);
			return millis;
		}

		// End
		public boolean endCounterMillis(int m) {
			Long t2 = System.nanoTime();
			Long elapsedTime = (t2 - getInitialTime());
			Long millis = TimeUnit.MILLISECONDS.convert(elapsedTime,
					TimeUnit.NANOSECONDS);
			if (millis > m)
				return true;
			return false;
		}
	
	public Long getInitialTime() {
		return initialTime;
	}

	public void setInitialTime(Long initialTime) {
		this.initialTime = initialTime;
	}
}
