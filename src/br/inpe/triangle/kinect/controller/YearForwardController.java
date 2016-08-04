package br.inpe.triangle.kinect.controller;

import br.com.kinect4j.controller.Controller;
import br.inpe.triangle.app2.DatasetController;

/**
 * @author Heitor
 * @since 18/06/2016
 */
public class YearForwardController implements Controller {
	private DatasetController controller = DatasetController.getInstance();

	@Override
	public void kinectActionPerformed() {
		controller.yearForward();
	}
}
