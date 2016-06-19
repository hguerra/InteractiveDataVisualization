package br.inpe.app.triangle.controllers;

import br.com.kinect4j.controller.Controller;
import br.inpe.app.triangle.WWJSceneController;

/**
 * @author Heitor
 * @since 18/06/2016
 */
public class NextDataController implements Controller {
    private WWJSceneController controller;

    public NextDataController(WWJSceneController controller) {
        this.controller = controller;
    }

    @Override
    public void kinectActionPerformed() {
        this.controller.nextData();
    }
}
