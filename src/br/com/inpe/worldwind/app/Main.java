package br.com.inpe.worldwind.app;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import gov.nasa.worldwind.Configuration;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.Position;
import br.com.inpe.app.RegisterVirtualGlobe;
import br.com.inpe.worldwind.controller.Comment;
import br.com.inpe.worldwind.controller.DrawLine;
import br.com.inpe.worldwind.controller.DrawPolygon;
import br.com.inpe.worldwind.controller.IAnnotation;
import br.com.inpe.worldwind.controller.IDraw;
import br.com.inpe.worldwind.controller.ILine;
import br.com.inpe.worldwind.controller.ScreenAnnotationLayer;
import br.com.inpe.worldwind.model.WorldWindModel;
import br.com.inpe.worldwind.view.AppFrameController;
import br.com.inpe.worldwind.view.WorldWindView;

/**
 * @author Heitor Guerra Carneiro
 * @since May 16, 2015
 * @version 1.0
 */

public class Main {
	public static void main(String[] args) throws FileNotFoundException {
		Configuration.setValue(AVKey.INITIAL_LATITUDE, -8.5);
		Configuration.setValue(AVKey.INITIAL_LONGITUDE, -37);
		Configuration.setValue(AVKey.INITIAL_ALTITUDE, 120e4);

		WorldWindView view = new WorldWindView();
		WorldWindModel model = new WorldWindModel();

		model.registerObserver(view);

		AppFrameController controller = new AppFrameController(view);

		RegisterVirtualGlobe.startFrameController(view, controller);
		view.setVisible(true);
			
		/**
		 * Y = 0 and X = 0.001 right 
		 * Y = 0 and X = -0.001 left 
		 * Y = -0.001 and X = 0 up 
		 * Y = 0.001 and X = 0 down
		 * 
		 */
		/**
		 * proj.4 pontGis 
		 * conceito de projeto 
		 * TerraLib 
		 * postgre (PostGIS)
		 * //Draw Polygon
		 * 
		 * IDraw polygonBorderPositions = new DrawPolygon(model, view);
		 * view.setIDraw(polygonBorderPositions, 1, 0);
		 * 
		 * 
		 * //Screen Annotations
		 * 
		 * IAnnotation annotation = new ScreenAnnotationLayer(view, model);
		 * annotation.addAnnotation(); view.setIAnnotation(annotation);
		 * 
		 * 
		 * //Comment IAnnotation comment = new Comment(view, model);
		 * annotation.addAnnotation(); view.setIAnnotation(comment);
		 * 
		 * 
		 * 
		 * List<Position> positions = new LinkedList<Position>();
		 * positions.add(Position.fromDegrees(-23, -45));
		 * positions.add(Position.fromDegrees(-8.5, -37)); ILine line = new
		 * DrawLine(view, model); view.setIline(line, "Teste Distancia",
		 * positions);
		 */
	}

}
