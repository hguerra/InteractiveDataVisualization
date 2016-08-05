package br.inpe.triangle.app;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import br.inpe.triangle.app.ScenarioLayer.ScenarioLayerFrame;

public class TriangleKeyListener implements KeyListener {
	private DatasetController datasetController = DatasetController.getInstance();
	private ScenarioLayerFrame scenario;

	public TriangleKeyListener(ScenarioLayerFrame scenario) {
		this.scenario = scenario;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		switch (key) {
		case KeyEvent.VK_1:
			scenario.removeActiveLayers();
			datasetController.dataBackward();
			scenario.refreshActiveLayers();
			break;
		case KeyEvent.VK_2:
			scenario.removeActiveLayers();
			datasetController.dataForward();
			scenario.refreshActiveLayers();
			break;
		case KeyEvent.VK_3:
			scenario.removeActiveLayers();
			datasetController.yearBackward();
			scenario.refreshActiveLayers();
			break;
		case KeyEvent.VK_4:
			scenario.removeActiveLayers();
			datasetController.yearForward();
			scenario.refreshActiveLayers();
			break;
		case KeyEvent.VK_ESCAPE:
			System.exit(0);
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

}
