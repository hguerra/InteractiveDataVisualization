package testeGestureDetector;

import SimpleOpenNI.SimpleOpenNI;

public enum EJointType {
	HEAD, NECK, LEFT_SHOULDER, LEFT_ELBOW, LEFT_HAND, RIGHT_SHOULDER, RIGHT_ELBOW, RIGHT_HAND, TORSO, LEFT_HIP, LEFT_KNEE, LEFT_FOOT, RIGHT_HIP, RIGHT_KNEE, RIGHT_FOOT;
	public int getJoint() {
		switch (this) {
		case HEAD:
			return SimpleOpenNI.SKEL_HEAD;
		case NECK:
			return SimpleOpenNI.SKEL_NECK;
		case LEFT_SHOULDER:
			return SimpleOpenNI.SKEL_LEFT_SHOULDER;
		case LEFT_ELBOW:
			return SimpleOpenNI.SKEL_LEFT_ELBOW;
		case LEFT_HAND:
			return SimpleOpenNI.SKEL_LEFT_HAND;
		case RIGHT_SHOULDER:
			return SimpleOpenNI.SKEL_RIGHT_SHOULDER;
		case RIGHT_ELBOW:
			return SimpleOpenNI.SKEL_RIGHT_ELBOW;
		case RIGHT_HAND:
			return SimpleOpenNI.SKEL_RIGHT_HAND;
		case TORSO:
			return SimpleOpenNI.SKEL_TORSO;
		case LEFT_HIP:
			return SimpleOpenNI.SKEL_LEFT_HIP;
		case LEFT_KNEE:
			return SimpleOpenNI.SKEL_LEFT_KNEE;
		case LEFT_FOOT:
			return SimpleOpenNI.SKEL_LEFT_FOOT;
		case RIGHT_HIP:
			return SimpleOpenNI.SKEL_RIGHT_HIP;
		case RIGHT_KNEE:
			return SimpleOpenNI.SKEL_RIGHT_KNEE;
		case RIGHT_FOOT:
			return SimpleOpenNI.SKEL_RIGHT_FOOT;
		default:
			return 0;
		}
	}

}
