package br.com.inpe.interactivedatavisualization.kinect.model.posecheck;

import processing.core.PVector;
import SimpleOpenNI.SimpleOpenNI;

public class SavePosition {

	private SimpleOpenNI context;
	PVector realHead;
	PVector realRightHand;
	PVector realLeftHand;
	PVector realCenterMass;
	PVector realRightElbow;
	PVector realLeftElbow;
	PVector realRightShoulder;
	PVector realLeftShoulder;

	public SavePosition(SimpleOpenNI context) {
		this.context = context;
		realHead = new PVector();
		realRightHand = new PVector();
		realLeftHand = new PVector();
		realCenterMass = new PVector();
		realRightElbow = new PVector();
		realLeftElbow = new PVector();
		realRightShoulder = new PVector();
		realLeftShoulder = new PVector();
	}

	/**
	 * Analisar a posicao de cada parte do corpo, como left of apartir de pontos
	 * reais
	 */
	public void convert(int userId) {
		// Head
		context.getJointPositionSkeleton(userId, SimpleOpenNI.SKEL_HEAD,
				realHead);
		// Hands
		context.getJointPositionSkeleton(userId, SimpleOpenNI.SKEL_RIGHT_HAND,
				realRightHand);
		context.getJointPositionSkeleton(userId, SimpleOpenNI.SKEL_LEFT_HAND,
				realLeftHand);
		// Center of Mass
		context.getCoM(userId, realCenterMass);
		// Elbow
		context.getJointPositionSkeleton(userId, SimpleOpenNI.SKEL_RIGHT_ELBOW,
				realRightElbow);
		context.getJointPositionSkeleton(userId, SimpleOpenNI.SKEL_LEFT_ELBOW,
				realLeftElbow);
		// Shoulder
		context.getJointPositionSkeleton(userId,
				SimpleOpenNI.SKEL_RIGHT_SHOULDER, realRightShoulder);

		context.getJointPositionSkeleton(userId,
				SimpleOpenNI.SKEL_LEFT_SHOULDER, realLeftShoulder);

	}

	/**
	 * Salvar as posicoes ja convertidas em uma lista e serializar
	 */
	public void save() {

	}

}
