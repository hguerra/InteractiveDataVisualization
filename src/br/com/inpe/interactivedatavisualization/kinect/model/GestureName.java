package br.com.inpe.interactivedatavisualization.kinect.model;

public enum GestureName {
	ZOOMIN(1), ZOOMOUT(2), TIMEUP(3), TIMEDOWN(4), DATAUP(5), DATADOWN(6), SEVENTIES(70);
	
	private final int value;
	
	GestureName(int valueOption){
		value = valueOption;
	}

	public int getValue() {
		return value;
	}
}