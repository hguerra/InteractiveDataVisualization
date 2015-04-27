package testeBDPoses;

import SimpleOpenNI.SimpleOpenNI;
/**
 * @author Heitor Guerra Carneiro.
 * @version 1.0
 * @since April 2015.
 */
public interface BasePose {
	public void constructorMethod(SimpleOpenNI context);
	public boolean verify(int userId);
}
