package resources;

import java.net.URL;

/**
 * @author Heitor
 * @since 27/04/2016
 */
public class Resource {

	private static URL getURL(String filename) {
		return Resource.class.getResource(filename);
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

	public static URL getPaneSetupKinectFXML() {
		return getURL("pane-setup-kinect-fxml.fxml");
	}

	public static URL getColorPickerFXML() {
		return getURL("pane-setup-colorpicker-fxml.fxml");
	}

	public static URL getStyleDataFXML() {
		return getURL("pane-style-data.fxml");
	}

	public static URL getPaneViewLayerFXML() {
		return getURL("layer-view-fxml.fxml");
	}

	public static URL getPaneViewBasicFXML() {
		return getURL("basic-view-fxml.fxml");
	}

	public static URL getPaneViewKinectFXML() {
		return getURL("kinect-view-fxml.fxml");
	}
	
	public static String getLogo() {
		return getURL("ccst.png").getPath();
	}
	
	public static String getGorillaHead() {
		return getURL("gorilla.png").getPath();
	}
}
