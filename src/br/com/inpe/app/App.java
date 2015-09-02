package br.com.inpe.app;

import gov.nasa.worldwind.Configuration;
import gov.nasa.worldwind.avlist.AVKey;

import java.awt.BorderLayout;
import java.awt.Container;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import br.com.inpe.kinect.view.KinectInternalFrame;
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

public class App extends JFrame{
	private JDesktopPane DPane;
	private KinectInternalFrame kinect;
	private WorldWindView view;
	WorldWindModel model = new WorldWindModel();
	static final  int WIDTH = 1300;
	static final int HEIGHT = 720;
	private AppFrameController controller;
	
	public App() {
		InternalFrameControl();
	}
	public void worldWindLoadData(){
		model.registerObserver(view);
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
		*/
		
		/*
		List<Position> positions = new LinkedList<Position>();
		 positions.add(Position.fromDegrees(-23, -45));
         positions.add(Position.fromDegrees(-8.5, -37));
		ILine line = new DrawLine(view, model);
		
		view.setIline(line, "Teste Distancia", positions);
		*/
	}
	
	public void InternalFrameControl() {
		Container framePane = getContentPane();
		framePane.setLayout(new BorderLayout());
		// ----------------------------------------------
		view = new WorldWindView();
		worldWindLoadData();
		// Get the titlebar and set it to null
		BasicInternalFrameUI nasaTitlePane = (BasicInternalFrameUI) view
				.getUI();
		nasaTitlePane.setNorthPane(null);
		view.setBorder(null);
		view.getContentPane();
		view.setBounds(0, 0, WIDTH, HEIGHT);
		view.setVisible(true);
		
		/**
		 * Teste
		 */
		controller = new AppFrameController(view);
		RegisterVirtualGlobe.startFrameController(view, controller);
		

		// ----------------------------------------------
		kinect = new KinectInternalFrame();
		// Get the titlebar and set it to null
		BasicInternalFrameUI kinectTitlePane = (BasicInternalFrameUI) kinect
				.getUI();
		kinectTitlePane.setNorthPane(null);
		kinect.getContentPane();
		kinect.setBounds(5, 460, 300, 220);
		kinect.setVisible(true);
		// ----------------------------------------------
		
		DPane = new JDesktopPane();
		DPane.add(kinect);
		DPane.add(view);
		DPane.setLayer(kinect, JLayeredPane.DEFAULT_LAYER);
		DPane.setLayer(kinect, JLayeredPane.DRAG_LAYER);
		framePane.add(BorderLayout.CENTER, DPane);
	}
	
	public static void main(String[] args) {
		Configuration.setValue(AVKey.INITIAL_LATITUDE, -8.5);
		Configuration.setValue(AVKey.INITIAL_LONGITUDE, -37);
		Configuration.setValue(AVKey.INITIAL_ALTITUDE, 120e4);
		
		App appViewControl = new App();
		appViewControl.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		appViewControl.setSize(WIDTH, HEIGHT);
		appViewControl.setResizable(false);
		appViewControl.setVisible(true);
	}
}
