package br.com.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * 
 * @author Heitor Guerra Carneiro
 *
 */
public class OpenFile {
	private static JFileChooser fileChooser = null;

	public Object recover(String path) throws Exception {
		FileInputStream inFile = new FileInputStream(path);
		ObjectInputStream d = new ObjectInputStream(inFile);
		Object o = d.readObject();
		d.close();
		return o;
	}

	public Object recoverChooser() throws ClassNotFoundException {
		fileChooser = new JFileChooser();
		fileChooser
				.setFileFilter(new FileNameExtensionFilter("ser", "ser"));
		if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			File newFile = new File(fileChooser.getSelectedFile().toString());
			try {
				FileInputStream inFile = new FileInputStream(newFile);
				ObjectInputStream d = new ObjectInputStream(inFile);
				Object o = d.readObject();
				d.close();
				return o;
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}

}