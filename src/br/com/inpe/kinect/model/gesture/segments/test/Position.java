package br.com.inpe.kinect.model.gesture.segments.test;

import SimpleOpenNI.SimpleOpenNI;
import processing.core.PVector;

public abstract class Position {
	private SimpleOpenNI context;

	public Position(SimpleOpenNI context) {
		this.context = context;
	}

	public PVector vectorJoint(int userId, int jointID) {
		PVector vectorJoint = new PVector();
		context.getJointPositionSkeleton(userId, jointID, vectorJoint);
		return vectorJoint;
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
