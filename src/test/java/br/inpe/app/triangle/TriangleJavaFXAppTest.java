package br.inpe.app.triangle;

import br.com.kinect4j.device.DeviceConfig;
import br.inpe.app.kinect.KinectApplicationViewTest;
import br.inpe.worldwind.view.ApplicationFX;
import com.primesense.nite.UserTracker;
import javafx.embed.swing.JFXPanel;
import javafx.embed.swing.SwingNode;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author Heitor
 * @since 01/06/2016
 */
public class TriangleJavaFXAppTest extends ApplicationFX {
    private Scene scene;
    private StackPane stackPane;
    private JFXPanel kinectPanel;
    private SwingNode kinectNode;
    private KinectApplicationViewTest skeletonTracker;

    @Override
    public Scene getScene() {
        return scene;
    }

    @Override
    protected String getSceneTitle() {
        return null;
    }

    @Override
    protected double getDefaultComponentSize() {
        return 0;
    }

    @Override
    protected void initComponents() {
        DeviceConfig kinect = DeviceConfig.getInstance();
        if (!kinect.isDeviceConnected()) {
            JOptionPane.showMessageDialog(null, "No device is connected", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        kinect.startFirstDevice();
        UserTracker userTracker = UserTracker.create();

        this.skeletonTracker = new KinectApplicationViewTest(userTracker);
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.add("Center", skeletonTracker);
        frame.setVisible(true);

        kinectPanel = new JFXPanel();
        kinectPanel.add(frame);

        this.kinectNode = new SwingNode();
        kinectNode.setContent(kinectPanel);

        stackPane = new StackPane();
        stackPane.getChildren().addAll(kinectNode);
        scene = new Scene(stackPane, 800, 600);
    }

    @Override
    protected void initListeners() {

    }

    @Override
    protected void initLayout() {

    }

    @Override
    protected boolean exitOnCloseRequest() {
        return true;
    }
}
