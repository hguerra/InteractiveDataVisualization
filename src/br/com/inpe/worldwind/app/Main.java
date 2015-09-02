package br.com.inpe.worldwind.app;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import gov.nasa.worldwind.Configuration;
import gov.nasa.worldwind.avlist.AVKey;
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
	public static void main(String[]args) throws FileNotFoundException{
		Configuration.setValue(AVKey.INITIAL_LATITUDE, -8.5);
		Configuration.setValue(AVKey.INITIAL_LONGITUDE, -37);
		Configuration.setValue(AVKey.INITIAL_ALTITUDE, 120e4);
		
		WorldWindView view = new WorldWindView();
		WorldWindModel model = new WorldWindModel();
		
		model.registerObserver(view);
		
		AppFrameController controller = new AppFrameController(view);
		
		RegisterVirtualGlobe.startFrameController(view, controller);
		
		
		/**
		 * Keys test
		 * 
		 * 
		 * A movimentacao do Triangle e levando em consideração a distancia
		 * entre os pontos da mao e do ombro direito.
		 * 
		 * double rightDeltaX = rightPrevCoords.getX() - rightHandCoords.getX();
		 * double rightDeltaY = rightPrevCoords.getY() - rightHandCoords.getY();
		 * if (distancia > 400){
		 * 
		 * pan(rightDeltaY * 0.3, rightDeltaX * 0.3)
		 * 
		 * }
		 * 
		 * Melhor o a ligacao entre KinectEvents e WorldWindView
		 *
		 * 
		 * ideia para melhor o deslocamento do mapa
		 * 
		 * criar uma classe que tem um array de float esse array vai guardar a
		 * posicao da mao direita, Quando o evento de inicar a popular a lista e
		 * acionado os valores sao gravados ate o final do deslocamento o ombro
		 * direito e a referencia para mover o mapa, e o mapa desloca no sentido
		 * oposto do ponto (ex: se o ultimo ponto esta a direita do ombro, o
		 * mapa vai para a esquerda)
		 * 
		 * 
		 */

		int delay = 5000; // delay de 5 seg.
		int interval = 1000;// intervalo de 1 seg.
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				// zoom
				System.out.println("Zoom: " + controller.getView().getZoom());
				/**
				 * Test pan
				 */
			}
		}, delay, interval);
		
		
		
		/*
		//Draw Polygon
		
		IDraw polygonBorderPositions = new DrawPolygon(model, view);
		view.setIDraw(polygonBorderPositions, 1, 0);
		 
		
		//Screen Annotations
		
		IAnnotation annotation = new ScreenAnnotationLayer(view, model);
		annotation.addAnnotation();
		view.setIAnnotation(annotation);
		
		
		//Comment
		IAnnotation comment = new Comment(view, model);
		annotation.addAnnotation();
		view.setIAnnotation(comment);
		
		
		
		List<Position> positions = new LinkedList<Position>();
		 positions.add(Position.fromDegrees(-23, -45));
         positions.add(Position.fromDegrees(-8.5, -37));
		ILine line = new DrawLine(view, model);
		view.setIline(line, "Teste Distancia", positions);
		*/
		
		
		view.setVisible(true);

	}

}
