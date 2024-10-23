package Ejercicios;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Ejercicio5 {

	public final static String RUTA = System.getProperty("user.dir" + System.getProperty("file.separator") + "src"
			+ System.getProperty("file.separator") + "data" + System.getProperty("file.separator"));
	public final static String DOCTRABAJO_IN = "numeritos.txt";

	public static void main(String[] args) throws IOException {
		File f = new File("numeritos.txt");
		RandomAccessFile raf = new RandomAccessFile(f, "rw");
		int contador = 1;
		escribir(raf, contador);
		consultar(raf, 3);
		leer(raf);
		anadirFinal(raf,1989);
		leer(raf);
		modificarValor(raf,2,3);
		leer(raf);

	}
	
	public static void escribir(RandomAccessFile raf, int contador) throws IOException {
		while (contador <= 100) {
			raf.writeInt(contador);
			contador++;
		}
		contador = 0;
		raf.seek(0);

	}

	public static void leer(RandomAccessFile raf) throws IOException {
		while ((raf.getFilePointer() != raf.length())) {
			System.out.println(raf.readInt());
		}
		raf.seek(0);

	}

	public static void consultar(RandomAccessFile raf, int posicion) throws IOException {
		long mostrar = ((posicion - 1) * 4);
		// Comprobar que la posicion sea corecta no superior al tamaño del fichero ni
		// inferior a 0
		if (mostrar >= raf.length() || mostrar < 0) {
			System.out.println("La posicion no es correcta");
		} else {
			raf.seek(mostrar);
			System.out.println(raf.readInt());
			raf.seek(0);
		}

	}
	public static void anadirFinal(RandomAccessFile raf, int valor) throws IOException {
		raf.seek(raf.length());
		raf.writeInt(valor);
		raf.seek(0);
	}
	public static void modificarValor(RandomAccessFile raf, int posicion, int valor) throws IOException {
		long mostrar = ((posicion - 1) * 4);
		// Comprobar que la posicion sea corecta no superior al tamaño del fichero ni
		// inferior a 0
		if (mostrar >= raf.length() || mostrar < 0) {
			System.out.println("La posicion no es correcta");
		} else {
			raf.seek(mostrar);
			raf.writeInt(valor);
			raf.seek(0);
		}
	}

}

/**
 * posicion -1 * tamaño bloque
 **/