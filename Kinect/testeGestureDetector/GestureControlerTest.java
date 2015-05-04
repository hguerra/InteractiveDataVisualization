package testeGestureDetector;

import SimpleOpenNI.SimpleOpenNI;

public class GestureControlerTest {
	private GestureControler g;
	private WaveRightSegment1 one;
	private WaveRightSegment2 two;
	private IGestureSegment[] lista = {one,two}; 
	
	public GestureControlerTest(SimpleOpenNI context) {
		g = new GestureControler();
		one = new WaveRightSegment1(context);
		two = new WaveRightSegment2(context);
		g.addGesture(EGestureType.WAVERIGHT,lista);
	}
	public void start(int userId){
		g.updateAllGestures(userId);
	}
	

}
