package br.inpe.worldwind.view.resources;

import java.net.URL;

public class Resource {
	/**
	 * Parent parent = FXMLLoader.load(getClass().getResource(RESOURCE_PATH));
	 */
	public static final String RESOURCE_PATH = "/br/inpe/worldwind/view/resources/";

	public static URL getURL(String filename) {
		return Resource.class.getResource(filename);
	}

	public static URL getLoginFXML() {
		return getURL("login-fxml.fxml");
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

	public static String getStylesheet(String stylesheet) {
		return getURL(stylesheet).toExternalForm();
	}
}
