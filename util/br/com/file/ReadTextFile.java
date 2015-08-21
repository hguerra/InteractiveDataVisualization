package br.com.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ReadTextFile {
	private BufferedReader bufferedReader = null;
	private String stringCurrentLine;
	private boolean toggle = false;

	public ReadTextFile(String fileName) {
		File file = new File(fileName);
		if (file.exists()) {
			setToggle(true);
			try {
				bufferedReader = new BufferedReader(new FileReader(file));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.err.println("File not found!");
		}
	}

	public void startRead() {
		if (isToggle()) {
			try {
				while ((stringCurrentLine = bufferedReader.readLine()) != null) {
					parseJoints(stringCurrentLine);
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (bufferedReader != null)
						bufferedReader.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	private void parseJoints(String currentLine) {
		String[] values = currentLine.split(";");

		float jointX = Float.parseFloat(values[0]);
		float jointY = Float.parseFloat(values[1]);
		float JointZ = Float.parseFloat(values[2]);

		System.out.println("x: " + jointX + " y: " + jointY + " z: " + JointZ);
	}

	public boolean isToggle() {
		return toggle;
	}

	public void setToggle(boolean toggle) {
		this.toggle = toggle;
	}
}
