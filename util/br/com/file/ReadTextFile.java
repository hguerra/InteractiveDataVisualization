package br.com.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadTextFile {
	private BufferedReader bufferedReader = null;
	private String stringCurrentLine;
	private boolean toggle = false;
	private List<float[]> positions;

	public ReadTextFile(String fileName) {
		File file = new File(fileName);
		if (file.exists()) {
			positions = new ArrayList<float[]>();
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
		float[] temp = { jointX, jointY, JointZ };
		positions.add(temp);
		// print(positions);
	}

	public void print(List<float[]> p) {
		for (float[] i : p) {
			System.out.println("x: " + i[0] + " y: " + i[1] + " z: " + i[2]);
		}
	}

	public List<float[]> getPositions() {
		return positions;
	}

	public void setPositions(List<float[]> positions) {
		this.positions = positions;
	}

	public boolean isToggle() {
		return toggle;
	}

	public void setToggle(boolean toggle) {
		this.toggle = toggle;
	}
}
