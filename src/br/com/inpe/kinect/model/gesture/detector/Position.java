package br.com.inpe.kinect.model.gesture.detector;

import SimpleOpenNI.SimpleOpenNI;
import processing.core.PVector;

public abstract class Position {
	private SimpleOpenNI context;
	private PVector vector;

	public Position(SimpleOpenNI context) {
		this.context = context;
		vector = new PVector();
	}

	public PVector vectorJoint(int userId, int jointID) {
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
