package br.inpe.triangle.kinect.controller;

import br.com.kinect4j.controller.Controller;
import br.inpe.triangle.app.SimpleScenarioControllerImpl;

/**
 * @author Heitor
 * @since 18/06/2016
 */
public class ZoomOut implements Controller{
    private SimpleScenarioControllerImpl controller;

    public ZoomOut(SimpleScenarioControllerImpl controller) {
        this.controller = controller;
    }

    @Override
    public void kinectActionPerformed() {
        System.out.println("ZOOM_OUT");
        this.controller.zoom(1.2);
    }
}
