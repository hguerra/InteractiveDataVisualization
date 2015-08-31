package virtualglobe.test;

import br.com.inpe.kinect.controller.Zoom;
import br.com.inpe.worldwind.view.AppFrameController;

public class ZoomAppFrame extends CanvasController implements Zoom {
	/**
	 * zoom 0.97 / 1.03
	 */
	public ZoomAppFrame(AppFrameController appFrame) {
		super(appFrame);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setZoom() {
		System.out.println("ZOOM IN!");
		getController().zoom(0.90);

	}

}
