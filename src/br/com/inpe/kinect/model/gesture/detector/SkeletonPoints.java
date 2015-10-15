package br.com.inpe.kinect.model.gesture.detector;

import SimpleOpenNI.SimpleOpenNI;
import processing.core.PVector;

public abstract class SkeletonPoints {
	private SimpleOpenNI context;

	public SkeletonPoints(SimpleOpenNI context) {
		this.context = context;
	}

	public PVector vectorJoint(int userId, int jointID) {
		PVector vector = new PVector();
		context.getJointPositionSkeleton(userId, jointID, vector);
		return vector;
	}

	public float getX(int userId, int jointID) {
		return vectorJoint(userId, jointID).x;
	}

	public float getY(int userId, int jointID) {
		return vectorJoint(userId, jointID).y;
	}

	public float getZ(int userId, int jointID) {
		return vectorJoint(userId, jointID).z;
	}

}
