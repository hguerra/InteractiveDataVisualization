package br.com.inpe.interactivedatavisualization.worldwind;

import gov.nasa.worldwind.BasicModel;
import gov.nasa.worldwind.awt.WorldWindowGLCanvas;

import java.awt.Dimension;

import java.awt.Toolkit;


import javax.swing.JInternalFrame;

public class NasaFrameControl extends JInternalFrame {
	
	Dimension dimensao = Toolkit.getDefaultToolkit().getScreenSize();
	int width = (int) (dimensao.getWidth() - 20);
	int height = (int) (dimensao.getHeight()- 20);
    public NasaFrameControl()
    {
        WorldWindowGLCanvas wwd = new WorldWindowGLCanvas();
        wwd.setPreferredSize(new java.awt.Dimension(width, height));
        this.getContentPane().add(wwd, java.awt.BorderLayout.CENTER);
        wwd.setModel(new BasicModel());
    }
    
}
