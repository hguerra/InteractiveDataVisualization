package br.inpe.worldwind.view.resources;

import java.net.URL;

public class Resource {
	/**
	 * Parent parent = FXMLLoader.load(getClass().getResource(RESOURCE_PATH));
	 */
	public static final String RESOURCE_PATH = "/br/inpe/worldwind/view/resources/";
	
	public static URL getURL(String filename){
		return Resource.class.getResource(filename);
	}
	
	public static URL getLoginFXML(){
		return getURL("login-fxml.fxml");
	}
	
	public static URL getHomeFXML(){
		return getURL("home-fxml.fxml");
	}
	public static URL getHomeDefaultFXML(){
		return getURL("home-default-fxml.fxml");
	}
	
	public static String getStylesheet(String stylesheet){
		return getURL(stylesheet).toExternalForm();
	}
}
