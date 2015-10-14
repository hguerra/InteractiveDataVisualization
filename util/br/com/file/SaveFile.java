package br.com.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * 
 * @author Heitor Guerra Carneiro
 *
 */
public class SaveFile {
	private static JFileChooser fileChooser = null;

	public void save(String path, Object obj) throws Exception {
		FileOutputStream outFile = new FileOutputStream(path);
		ObjectOutputStream s = new ObjectOutputStream(outFile);
		s.writeObject(obj);
		s.close();
	}

	public void saveChooser(Object obj) {
		fileChooser = new JFileChooser();
		if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
			File newFile = new File(fileChooser.getSelectedFile().toString()
					+ "." + "gesture");
			try {
				FileOutputStream outFile = new FileOutputStream(newFile);
				ObjectOutputStream s = new ObjectOutputStream(outFile);
				s.writeObject(obj);
				s.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

}
