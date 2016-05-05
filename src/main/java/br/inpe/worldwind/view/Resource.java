package br.inpe.worldwind.view;

import java.net.URL;

/**
 * @author Heitor
 * @since 27/04/2016
 */
public class Resource {

    private static URL getURL(String filename) {
        return Resource.class.getResource("/".concat(filename));
    }

    public static URL getHomeDefaultFXML() {
        return getURL("home-default-fxml.fxml");
    }

    public static URL getPaneSetupBasicFXML() {
        return getURL("pane-setup-basic-fxml.fxml");
    }

    public static URL getPaneSetupLayerFXML() {
        return getURL("pane-setup-layer-fxml.fxml");
    }

    public static URL getPaneSetupDatabaseFXML() {
        return getURL("pane-setup-database-fxml.fxml");
    }

    public static URL getPaneSetupKinectFXML() {
        return getURL("pane-setup-kinect-fxml.fxml");
    }

    public static URL getPaneSetupProfileFXML() {
        return getURL("pane-setup-profile-fxml.fxml");
    }

    public static URL getColorPickerFXML() {
        return getURL("pane-setup-colorpicker-fxml.fxml");
    }

    public static URL getStyleDataFXML() {
        return getURL("pane-style-data.fxml");
    }

    public static URL getPaneViewDatabaseFXML() {
        return getURL("database-view-fxml.fxml");
    }

}
