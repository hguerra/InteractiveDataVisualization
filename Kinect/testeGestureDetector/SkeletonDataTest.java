package testeGestureDetector;

import java.util.LinkedList;
import java.util.List;

import SimpleOpenNI.SimpleOpenNI;

public class SkeletonDataTest {
	/**
	 * Constant
	 */
	// X AND Y
	public static final int ABOVE = 1;
	public static final int BELOW = 2;
	public static final int LEFT_OF = 3;
	public static final int RIGHT_OF = 4;
	// Z
	public static final int CLOSER_OF = 5;
	public static final int FARTHER_OF = 6;
	private List<SkeletonData> poses;
	private SegmentCheck rules;

	public SkeletonDataTest(SimpleOpenNI context) {
		poses = new LinkedList<SkeletonData>();
		rules = new SegmentCheck(context);
	}

	public void addPose(SkeletonData data) {
		poses.add(data);
	}

}
