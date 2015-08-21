package br.com.app.test;

import br.com.file.ReadTextFile;

public class MainTest {

	public static void main(String[] args) {
		String pathName = "C:\\Users\\Heitor\\Documents\\trainning.txt";
		ReadTextFile read = new ReadTextFile(pathName);
		read.startRead();
	}

}
