package br.com.inpe.interactivedatavisualization.test.failure;

public class Timer {
	private final Integer CONSTANT = 1000000000;
	private final Integer VARIATION = 20;
	private Long initialTime;
	
	public Timer(){
		startCounter();
	}
	
	// Begin
	public void startCounter() {
		long t1 = System.nanoTime();
		setInitialTime(t1);
	}

	// End
	public long finalCounter(long startCounter) {
		long t2 = System.nanoTime();
		long elapsedTimeInSeconds = (t2 - startCounter) / CONSTANT;
		return elapsedTimeInSeconds;
	}

	// End
	public boolean finalCounter(long startCounter, int seconds) {
		long t2 = System.nanoTime();
		long elapsedTimeInSeconds = (t2 - startCounter) / CONSTANT;
		if (elapsedTimeInSeconds > seconds)
			return true;
		return false;
	}
	
	public Boolean waitForCounter(int seconds, float handX, float handY, float width, float height){
		int exit = 0;
		long t1 = System.nanoTime();
		while(exit == 0){
			long t2 = System.nanoTime();
			long elapsedTimeInSeconds = (t2 - t1) / CONSTANT;
			if(elapsedTimeInSeconds > seconds){
				if (handX < width + VARIATION && handX > width - VARIATION
						&& handY < height + VARIATION
						&& handY > height - VARIATION){
					return true;
				}
				else
					exit = 1;
			}
		}
		return false;
	}

	public Long getInitialTime() {
		return initialTime;
	}

	public void setInitialTime(Long initialTime) {
		this.initialTime = initialTime;
	}
}
