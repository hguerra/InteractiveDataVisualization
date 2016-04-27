package br.inpe.kinect4j.engine;

import com.primesense.nite.UserData;

import br.com.kinect4j.engine.core.Skeleton;
import br.com.kinect4j.engine.core.SkeletonCoordinates;
import br.com.kinect4j.engine.defaultcore.DefaultSkeletonCoordinates;
import br.inpe.kinect4j.view.KinectView;
import br.inpe.worldwind.controller.ScenarioController;

public class Pan {
	private static final double FACTOR = 0.3;
	private static final int ARM_LENGHT = 600;
	private SkeletonCoordinates coords;
	private KinectView view;

	public Pan(Skeleton skeleton, KinectView view) {
		this.view = view;
		coords = new DefaultSkeletonCoordinates(skeleton);
	}

	public void moveMap(UserData user) {
		// add
		coords.addLeftHandPoint(user);
		coords.addRightHandPoint(user);

		// do the magic now!

		double dist = coords.getDistanceRightHandtoShoulder(user);

		if (dist > ARM_LENGHT) {
			
			ScenarioController controller = view.getGlobeController();

			if (controller == null)
				return;

			double moveY = coords.getRightHandDeltaY(user) * FACTOR;

			double moveX = coords.getRightHandDeltaX(user) * FACTOR;

			controller.pan(moveY, moveX);

			view.redrawWorldWind();
		}

	}
}
