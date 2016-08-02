package br.inpe.triangle.kinect.controller;

import br.com.kinect4j.controller.Controller;
import br.inpe.triangle.app.SimpleScenarioControllerImpl;

/**
 * @author Heitor
 * @since 18/06/2016
 */
public class PreviousDataController implements Controller{
    private SimpleScenarioControllerImpl controller;

    public PreviousDataController(SimpleScenarioControllerImpl controller) {
        this.controller = controller;
    }

    @Override
    public void kinectActionPerformed() {
        this.controller.previousData();
    }
}
