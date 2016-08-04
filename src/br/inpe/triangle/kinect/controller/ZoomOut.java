package br.inpe.triangle.kinect.controller;

import br.com.kinect4j.controller.Controller;
import br.inpe.triangle.wwj.layer.WorldWindController;

/**
 * @author Heitor
 * @since 18/06/2016
 */
public class ZoomOut implements Controller {
	private WorldWindController controller;

	public ZoomOut(WorldWindController controller) {
		this.controller = controller;
	}

	@Override
	public void kinectActionPerformed() {
		System.out.println("ZOOM_OUT");
		this.controller.zoom(1.2);
	}
}
