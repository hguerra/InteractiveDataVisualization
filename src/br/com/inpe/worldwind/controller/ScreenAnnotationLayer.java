package br.com.inpe.worldwind.controller;

import br.com.inpe.worldwind.model.WorldWindModel;
import br.com.inpe.worldwind.view.WorldWindView;

/**
 * @author Heitor Guerra Carneiro
 * @since May 16, 2015
 * @version 1.0
 */
public class ScreenAnnotationLayer implements IAnnotation {
	private WorldWindView view;
	private WorldWindModel model;

	public ScreenAnnotationLayer(WorldWindView view, WorldWindModel model) {
		super();
		this.view = view;
		this.model = model;
	}

	@Override
	public void addAnnotation() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				view.printLogo();
			}
		}).start();
	}
}
