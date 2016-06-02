package br.inpe.app.triangle;

import br.com.kinect4j.controller.Controller;
import gov.nasa.worldwind.awt.WorldWindowGLCanvas;

/**
 * @author Heitor
 * @since 01/06/2016
 */
public class WWJController implements Controller {
    private WWJSceneController controller;

    public WWJController(WorldWindowGLCanvas canvas) {
        this.controller = new WWJSceneController(canvas);
    }

    @Override
    public void kinectActionPerformed() {
        controller.pan(0,0);
    }
}
