package br.com.inpe.interactivedatavisualization.test.failure;

public class Timer {
	private final Integer CONSTANT = 1000000000;
	private final Integer VARIATION = 20;
	private Boolean check = false;
	
	public Timer(){
		
		
	}
	
	// Begin
	public long startCounter() {
		long t1 = System.nanoTime();
		return t1;
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
		else
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

	public Boolean getCheck() {
		return check;
	}

	public void setCheck(Boolean check) {
		this.check = check;
	}

	
	

}
