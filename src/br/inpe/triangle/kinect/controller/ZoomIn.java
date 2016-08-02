package br.inpe.triangle.kinect.controller;

import br.com.kinect4j.controller.Controller;
import br.inpe.triangle.app.SimpleScenarioControllerImpl;

/**
 * @author Heitor
 * @since 18/06/2016
 */
public class ZoomIn implements Controller{
    private SimpleScenarioControllerImpl controller;

    public ZoomIn(SimpleScenarioControllerImpl controller) {
        this.controller = controller;
    }

    @Override
    public void kinectActionPerformed() {
        System.out.println("ZOOM_IN");
        this.controller.zoom(0.8);
    }
}
