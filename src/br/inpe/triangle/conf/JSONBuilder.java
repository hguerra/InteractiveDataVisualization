package br.inpe.triangle.conf;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JSONBuilder {
	private static JSONBuilder uniqueInstance;
	private Gson gson;

	private JSONBuilder() {
		this.gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
	}

	public static JSONBuilder getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new JSONBuilder();
		}
		return uniqueInstance;
	}

	public <T> String getJSON(T object) {
		return gson.toJson(object);
	}

	public synchronized boolean writeJSON(String json, File file) {
		try {
			FileWriter writer = new FileWriter(file);
			writer.write(json);
			writer.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public synchronized boolean writeJSON(String json, String filepath) {
		return writeJSON(json, new File(filepath));
	}

	public synchronized <T> T readJSON(Class<?> typeOfClass, File file) {
		if (!file.exists())
			return null;
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			// convert the json string back to object
			T obj = gson.fromJson(br, typeOfClass);
			return obj;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	public synchronized <T> T readJSON(Class<?> typeOfClass, String filepath) {
		return readJSON(typeOfClass, new File(filepath));
	}

}
