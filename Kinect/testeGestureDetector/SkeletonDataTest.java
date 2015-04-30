package testeGestureDetector;

import java.util.LinkedList;
import java.util.List;

import SimpleOpenNI.SimpleOpenNI;

public class SkeletonDataTest {
	private List<SkeletonData> poses;
	private SegmentCheck rules;

	public SkeletonDataTest(SimpleOpenNI context) {
		poses = new LinkedList<SkeletonData>();
		rules = new SegmentCheck(context);
	}
	public void addPose(SkeletonData data){
		poses.add(data);
	}
	

}
