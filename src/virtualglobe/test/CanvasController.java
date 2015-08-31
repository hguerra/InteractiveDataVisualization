package virtualglobe.test;

import br.com.inpe.worldwind.view.AppFrameController;

public abstract class CanvasController {
	private AppFrameController controller;

	public CanvasController(AppFrameController appFrame) {
		this.controller = appFrame;
	}

	public AppFrameController getController() {
		return controller;
	}

}
