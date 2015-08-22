package br.com.neural;

import java.util.List;

import br.com.app.test.PathName;
import br.com.file.OpenFile;
import br.com.file.ReadTextFile;
import br.com.file.SaveFile;

public class Saidas {
	public static void main(String[] args) {
		/**
		 * Salvando
		 */
		
		ReadTextFile read = new ReadTextFile(PathName.READ);
		read.startRead();
		Valores v = new Valores();

		List<float[]> positions = read.getPositions();
		v.setSaidas(positions);

		SaveFile save = new SaveFile();
		try {
			save.save(PathName.SAIDAS, v);
			System.out.println("Valores gravado com sucesso");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/**
		 * Recuperando
		 */
		OpenFile open = new OpenFile();
		
		try {
			Valores recuperando = (Valores) open.recover(PathName.SAIDAS);
			print(recuperando.getSaidas());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void print(List<float[]> p) {
		for (float[] i : p) {
			System.out.println("x: " + i[0] + " y: " + i[1] + " z: " + i[2]);
		}
	}
}
