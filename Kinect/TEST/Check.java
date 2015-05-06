package TEST;


import SimpleOpenNI.SimpleOpenNI;

public class Check {
	private WaveTimer wave;
	private SimpleOpenNI context;
	Timer t1 = new Timer();
	Timer t2 = new Timer();
	boolean a = false;
	boolean b = false;
	boolean c = false;
	
	public Check(SimpleOpenNI context) {
		this.context = context;
		wave = new WaveTimer(context);
	}
	public boolean checkPose(int userId){
		if(a == false && wave.segmentOne(userId)){
			t1.startCounter();
			a = true;
		}
		if(b == false && t1.endCounterSeconds(1) && wave.segmentTwo(userId)){
			t2.startCounter();
			b = true;
		}
		if(c == false && t2.endCounterSeconds(1) && wave.segmentThree(userId)){
			c = true;
		}
		return a && b && c;
	}
}
