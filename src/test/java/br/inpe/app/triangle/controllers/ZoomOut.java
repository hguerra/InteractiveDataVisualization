package br.inpe.app.triangle.controllers;

import br.com.kinect4j.controller.Controller;
import br.inpe.app.triangle.WWJSceneController;

/**
 * @author Heitor
 * @since 18/06/2016
 */
public class ZoomOut implements Controller{
    private WWJSceneController controller;

    public ZoomOut(WWJSceneController controller) {
        this.controller = controller;
    }

    @Override
    public void kinectActionPerformed() {
        System.out.println("ZOOM_OUT");
        this.controller.zoom(1.2);
    }
}
