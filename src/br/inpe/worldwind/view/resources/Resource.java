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
}
