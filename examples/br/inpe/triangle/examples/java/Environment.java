package br.inpe.triangle.examples.java;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;

public class Environment {
	public static void main(String[] args) throws Exception {
		ProcessBuilder pb = new ProcessBuilder("CMD.exe", "/C", "SET"); // SET
																		// prints
																		// out
																		// the
																		// environment
																		// variables
		pb.redirectErrorStream(true);
		Map<String, String> env = pb.environment();
		String path = env.get("Path") + ";C:\\naved\\bin";
		env.put("Path", path);
		Process process = pb.start();
		BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String line;
		while ((line = in.readLine()) != null) {
			System.out.println(line);
		}
	}
}