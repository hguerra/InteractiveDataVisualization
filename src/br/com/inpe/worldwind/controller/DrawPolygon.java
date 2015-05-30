package br.com.inpe.worldwind.controller;
/**
 * @author Heitor Guerra Carneiro
 * @since May 16, 2015
 * @version 1.0
 */
import br.com.inpe.worldwind.model.WorldWindModel;
import br.com.inpe.worldwind.view.WorldWindView;

public class DrawPolygon implements IDraw {
	private WorldWindModel model;
	private WorldWindView view;

	public DrawPolygon(WorldWindModel model, WorldWindView view) {
		super();
		this.model = model;
		this.view = view;
	}

	@Override
	public void drawShapeGreaterMunicipalityArea(long area) {
		model.createShapeGreaterMunicipalityArea(area);
		
	}

	@Override
	public void drawShapeEqualsMunicipalityArea(long area) {
		model.createShapeEqualsMunicipalityArea(area);
		
	}

	@Override
	public void drawShapeLessMunicipalityArea(long area) {
		model.createShapeLessMunicipalityArea(area);
		
	}
}
