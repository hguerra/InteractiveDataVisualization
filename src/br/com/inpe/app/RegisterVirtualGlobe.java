package br.com.inpe.app;

import br.com.inpe.worldwind.view.AppFrameController;
import br.com.inpe.worldwind.view.WorldWindView;

public class RegisterVirtualGlobe {
	public static AppFrameController controller;
	public static WorldWindView view;

	public static void startFrameController(WorldWindView view,
			AppFrameController controller) {
		setView(view);
		setController(controller);
	}

	private static void setController(AppFrameController controller) {
		RegisterVirtualGlobe.controller = controller;
	}

	public static AppFrameController getController() {
		return controller;
	}

	private static void setView(WorldWindView view) {
		RegisterVirtualGlobe.view = view;
	}

	public static WorldWindView getView() {
		return view;
	}

}
