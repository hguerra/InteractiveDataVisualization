package br.com.inpe.app;

import br.com.inpe.worldwind.view.AppFrameController;
import br.com.inpe.worldwind.view.WorldWindView;

public class RegisterVirtualGlobe {
	/**
	 * WorldWind
	 */
	private static AppFrameController frameController;
	private static WorldWindView view;

	public static void startFrameController(WorldWindView view,
			AppFrameController controller) {
		setView(view);
		setFrameController(controller);
	}

	private static void setFrameController(AppFrameController controller) {
		RegisterVirtualGlobe.frameController = controller;
	}

	public static AppFrameController getFrameController() {
		return frameController;
	}

	private static void setView(WorldWindView view) {
		RegisterVirtualGlobe.view = view;
	}
	public static WorldWindView getView() {
		return view;
	}
}
