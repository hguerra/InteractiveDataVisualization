package br.com.inpe.interactivedatavisualization.kinect.model;
import java.util.LinkedList;
import java.util.List;

import br.com.inpe.interactivedatavisualization.kinect.view.Observer;
import SimpleOpenNI.SimpleOpenNI;
/**
 * @author Heitor Guerra Carneiro.
 * @version 1.0
 * @since March 2015.
 */
public class Movements implements Subject{
	private SimpleOpenNI context;
	private SkeletonPoser pose;
	private List<Observer> listObservers;
	
	public Movements(SimpleOpenNI context) {
		listObservers = new LinkedList<Observer>();
		pose = new SkeletonPoser(context);
		this.context = context;
		addPose();
	}

	public void addPose() {
		seventiesPose();
	}

	public void seventiesPose() {
		// rules for the right arm
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRule.ABOVE,
				SimpleOpenNI.SKEL_RIGHT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_HAND, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_RIGHT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_ELBOW, PoseRule.ABOVE,
				SimpleOpenNI.SKEL_RIGHT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_ELBOW, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_RIGHT_SHOULDER);
		// rules for the left arm
		pose.addRule(SimpleOpenNI.SKEL_LEFT_ELBOW, PoseRule.BELOW,
				SimpleOpenNI.SKEL_LEFT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_ELBOW, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_LEFT_SHOULDER);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_HAND, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_LEFT_ELBOW);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_HAND, PoseRule.BELOW,
				SimpleOpenNI.SKEL_LEFT_ELBOW);
		// rules for the right leg
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_KNEE, PoseRule.BELOW,
				SimpleOpenNI.SKEL_RIGHT_HIP);
		pose.addRule(SimpleOpenNI.SKEL_RIGHT_KNEE, PoseRule.RIGHT_OF,
				SimpleOpenNI.SKEL_RIGHT_HIP);
		// rules for the left leg
		pose.addRule(SimpleOpenNI.SKEL_LEFT_KNEE, PoseRule.BELOW,
				SimpleOpenNI.SKEL_LEFT_HIP);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_KNEE, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_LEFT_HIP);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_FOOT, PoseRule.BELOW,
				SimpleOpenNI.SKEL_LEFT_KNEE);
		pose.addRule(SimpleOpenNI.SKEL_LEFT_FOOT, PoseRule.LEFT_OF,
				SimpleOpenNI.SKEL_LEFT_KNEE);
	}

	public void poseCheck(int userId) {
		// check to see if the user
		// is in the pose
		if (pose.check(userId)) {
			// if they are, set the color white
			// stroke(255);
			System.out.println("Recognize a movement!");
		}
	}

	@Override
	public void registerObserver(Observer observer) {
		listObservers.add(observer);
	}

	@Override
	public void notifyObserversPoseCheck() {
		for(Observer i: listObservers)
			/*
			 * Passar um parametro para o update, informando a posição realizada
			 */
			i.update();
	}

}