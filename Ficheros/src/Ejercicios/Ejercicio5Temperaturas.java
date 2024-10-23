package Ejercicios;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Ejercicio5Temperaturas {


	 public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);
	        String fileName = "temperaturas.txt";

	        while (true) {
	            System.out.println("\n--- Menú de opciones ---");
	            System.out.println("1. Ver temperatura de un mes");
	            System.out.println("2. Añadir un nuevo registro");
	            System.out.println("3. Salir");
	            System.out.print("Selecciona una opción: ");
	            int opcion = scanner.nextInt();

	            switch (opcion) {
	                case 1:
	                    mostrarTemperatura(fileName, scanner);
	                    break;
	                case 2:
	                    añadirRegistro(fileName, scanner);
	                    break;
	                case 3:
	                    System.out.println("Saliendo...");
	                    scanner.close();
	                    System.exit(0);
	                default:
	                    System.out.println("Opción inválida. Inténtalo de nuevo.");
	            }
	        }
	    }

	    // Método para mostrar la temperatura de un mes específico
	    public static void mostrarTemperatura(String fileName, Scanner scanner) {
	        System.out.print("Introduce el número del mes (1 para Ene, 2 para Feb, etc.): ");
	        int mes = scanner.nextInt();

	        try (RandomAccessFile raf = new RandomAccessFile(fileName, "r")) {
	            raf.seek(0); // Mover al inicio del archivo
	            boolean mesEncontrado = false;

	            for (int i = 1; i <= mes; i++) {
	                String linea = raf.readLine();

	                if (linea == null) {
	                    System.out.println("Mes no válido.");
	                    return;
	                }

	                if (i == mes) {
	                    // Dividimos la línea por espacios y comprobamos si tiene al menos 3 partes
	                    String[] datos = linea.trim().split(" ");
	                    if (datos.length >= 3) {
	                        String mesNombre = datos[0];
	                        int tempMax = Integer.parseInt(datos[1]);
	                        int tempMin = Integer.parseInt(datos[2]);

	                        System.out.println("Mes: " + mesNombre);
	                        System.out.println("Temperatura Máxima: " + tempMax);
	                        System.out.println("Temperatura Mínima: " + tempMin);
	                        mesEncontrado = true;
	                    } else {
	                        System.out.println("El formato de la línea es incorrecto.");
	                    }
	                }
	            }

	            if (!mesEncontrado) {
	                System.out.println("Mes no encontrado.");
	            }
	        } catch (IOException | NumberFormatException e) {
	            System.out.println("Error al leer el archivo o al procesar los datos: " + e.getMessage());
	        }
	    }

	    // Método para añadir un nuevo registro al final del archivo
	    public static void añadirRegistro(String fileName, Scanner scanner) {
	        System.out.print("Introduce el mes (tres letras, por ejemplo Ene): ");
	        String mes = scanner.next().toLowerCase(); // Convertir a minúsculas para estandarizar
	        
	        System.out.print("Introduce la temperatura máxima: ");
	        int tempMax = scanner.nextInt();
	        
	        System.out.print("Introduce la temperatura mínima: ");
	        int tempMin = scanner.nextInt();

	        try (RandomAccessFile raf = new RandomAccessFile(fileName, "rw")) {
	            // Mover el puntero al final del archivo
	            raf.seek(raf.length());
	            
	            // Escribir el nuevo registro en formato: "mes tempMax tempMin"
	            raf.writeBytes(mes + " " + tempMax + " " + tempMin + "\n");
	            
	            System.out.println("Registro añadido correctamente.");
	        } catch (IOException e) {
	            System.out.println("Error al escribir en el archivo: " + e.getMessage());
	        }
	    }
}

