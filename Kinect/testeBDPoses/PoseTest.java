package testeBDPoses;
import SimpleOpenNI.SimpleOpenNI;
/**
 * @author Heitor Guerra Carneiro.
 * @version 1.0
 * @since April 2015.
 */
public class PoseTest implements BasePose{
	private SimpleOpenNI context;
	private BDPose pose;

	public PoseTest(SimpleOpenNI context) {
		constructorMethod(context);
	}

	@Override
	public void constructorMethod(SimpleOpenNI context) {
		this.context = context;
		pose = new BDPose(context);
	}

	@Override
	public boolean verify(int userId) {
		return pose.check(userId);
	}

}
