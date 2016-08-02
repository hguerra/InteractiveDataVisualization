package br.inpe.triangle.kinect.controller;

import br.com.kinect4j.controller.Controller;
import br.inpe.triangle.app.SimpleScenarioControllerImpl;

/**
 * @author Heitor
 * @since 18/06/2016
 */
public class RewingTimeController implements Controller {
    private SimpleScenarioControllerImpl controller;

    public RewingTimeController(SimpleScenarioControllerImpl controller) {
        this.controller = controller;
    }

    @Override
    public void kinectActionPerformed() {
        this.controller.yearBackward();
    }
}
