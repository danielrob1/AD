package Ejercicios;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Ejercicio5Au {

	public static void main(String[] args) throws IOException {
		RandomAccessFile raf = new RandomAccessFile(new File("numeritos.txt"),"rw");
		for (int i = 1; i <=100; i++) {
			raf.writeInt(i);
		}
		raf.close();
		RandomAccessFile rf = new RandomAccessFile(new File("numeritos.txt"),"rw");
		while(rf.getFilePointer()!=rf.length()) {
			System.out.println(rf.readInt());
		}
		rf.close();

	}

}
