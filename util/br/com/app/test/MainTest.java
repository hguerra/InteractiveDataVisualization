package br.com.app.test;

import br.com.file.ReadTextFile;

public class MainTest {

	public static void main(String[] args) {
		ReadTextFile read = new ReadTextFile(PathName.READ);
		read.startRead();
	}

}
