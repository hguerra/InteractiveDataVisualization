package br.inpe.triangle.kinect.controller;

import br.com.kinect4j.controller.Controller;
import br.inpe.triangle.wwj.layer.WorldWindController;

/**
 * @author Heitor
 * @since 18/06/2016
 */
public class ZoomIn implements Controller {
	private WorldWindController controller;

	public ZoomIn(WorldWindController controller) {
		this.controller = controller;
	}

	@Override
	public void kinectActionPerformed() {
		System.out.println("ZOOM_IN");
		this.controller.zoom(0.8);
	}
}
