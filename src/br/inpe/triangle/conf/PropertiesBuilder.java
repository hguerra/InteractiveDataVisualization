package br.inpe.triangle.conf;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

public class PropertiesBuilder {
	public static void propertiesToXML(Properties props, String comment, String filepath) throws IOException {
		OutputStream os = new FileOutputStream(filepath);
		props.storeToXML(os, comment, "UTF-8");
	}
	
	public static Properties XMLToProperties(String filepath) throws InvalidPropertiesFormatException, IOException{
		Properties props = new Properties();
		InputStream is = new FileInputStream(filepath);
		// load the xml file into properties format
		props.loadFromXML(is);
		return props;
	}
}