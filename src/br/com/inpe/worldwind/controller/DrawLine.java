package br.com.inpe.worldwind.controller;

import gov.nasa.worldwind.geom.Position;

import java.util.LinkedList;
import java.util.List;

import br.com.inpe.worldwind.model.WorldWindModel;
import br.com.inpe.worldwind.view.WorldWindView;

/**
 * @author Heitor Guerra Carneiro
 * @since May 16, 2015
 * @version 1.0
 */
public class DrawLine implements ILine{
	private WorldWindView view;
	private WorldWindModel model;

	

	public DrawLine(WorldWindView view, WorldWindModel model) {
		super();
		this.view = view;
		this.model = model;
	}

	public void drawLine(String displayName, List<Position> positions){
		view.drawLine(displayName, positions);
	}
	
	
	
}
