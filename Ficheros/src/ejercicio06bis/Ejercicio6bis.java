package ejercicio06bis;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;

/**
 * Para poder trabajar con el fichero serializado de Personas y copiar esas
 * Personas a un nuevo fichero "datosPeronasv2" con Personas de la clase
 * "Persona_v2" con serialUID 7, se va a realizar el siguiente procedimiento,
 * Primero, en el programa trabajaremos con objetos de tipo Persona, con
 * serialUid 1, se obtendrán todos sus atributos para crear nuevos objetos de
 * tipo PersonaV2. Finalmente, las PersonasV2 se guardaran en el fichero
 * datosPeronasv2
 */
public class Ejercicio6bis {
	// Se decñara el fichero de entrada, con Personas de serial uid 1
	private final static String DOCTRABAJO_IN = "FicheroPersonasSerializado.dat";
	// Se declara el fichero de salida, con Personasv2 de serial Uid 7
	private final static String DOCTRABAJO_OUT = "datosPersonasv2.dat";
	private static ObjectOutputStream oOS;
	private static ObjectInputStream oIS;
	// Variable de control para indicar que se ha acabado de leer todas las Personas
	private static boolean acabado = false;
	private static Scanner sc = new Scanner(System.in);

	/**
	 * Se inicializa el nuevo fichero de salida
	 */
	public static void inicializar() {
		try {
			File file = new File(DOCTRABAJO_OUT);
			oIS = new ObjectInputStream(new FileInputStream(new File(DOCTRABAJO_IN)));
			if (file.exists() && file.length() > 0) {
				oOS = new MyObjectOutputStream(new FileOutputStream(file, true));
			} else {
				oOS = new ObjectOutputStream(new FileOutputStream(file, true));
			}
		} catch (IOException e) {
			System.out.println("Error al abrir el fichero");
			e.printStackTrace();
		}
	}

	/**
	 * Se toman los datos de la Persona con uid 1 y se asignan a la nueva Persona_v2
	 * con uid7
	 * 
	 * @param persona
	 * @return Se devuelve la Persona_v2, que es una copia de la Persona con uid 1
	 */
	public static Persona_v2 obtenerDatosv2(Persona persona) {
		Persona_v2 persona2 = new Persona_v2();
		persona2.setNombre(persona.getNombre());
		persona2.setApellido1(persona.getApellido1());
		persona2.setApellido2(persona.getApellido2());
		persona2.setNacimiento(persona.getNacimiento());
		return persona2;
	}

	/**
	 * Pide por teclado los datos de la persona para añadirla al nuevo fichero de
	 * Personas_v2
	 * 
	 * @return un objeto de la clase Persona
	 */
	public static Persona_v2 anadirPersona() {
		Persona_v2 persona = new Persona_v2();
		System.out.println("DATOS DEL USUARIO\n\tNombre: ");
		persona.setNombre(new StringBuilder(sc.nextLine()));
		System.out.println("\tPrimer Apellido: ");
		persona.setApellido1(new StringBuilder(sc.nextLine()));
		System.out.println("\tSegundo Apellido: ");
		persona.setApellido2(new StringBuilder(sc.nextLine()));
		System.out.println("\tFecha de Nacimiento (dd-MM-yyyy): ");
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			persona.setNacimiento(formatter.parse(sc.nextLine()));
		} catch (ParseException e) {
			System.out.println("Error en el formato de la fecha");
			e.printStackTrace();
		}
		return persona;
	}

	/**
	 * Escribe un objeto de la clase Persona en un stream de salida
	 * 
	 * @param persona objeto a escribir
	 */
	public static void escribirObjeto(Persona_v2 persona2) {
		try {
			oOS.writeObject(persona2);
			System.out.println("Se ha copiado la persona con la nueva serialUID: ");
			System.out.println(persona2);
		} catch (IOException e) {
			System.out.println("Error al abrir el fichero para escritura");
			e.printStackTrace();
		}
	}

	public static Persona leerPersona2() {
		Persona persona = new Persona();
		try {
			persona = (Persona) oIS.readObject();
		} catch (ClassNotFoundException e) {
			System.out.println("Error de lectura de clase");
			e.printStackTrace();
		} catch (EOFException e) {
			// Cuando se llega al final del fichero, no quedan mas personas para leer, se
			// cambia la variable de control
			acabado = true;
		} catch (IOException e) {
			System.out.println("Error al abrir el fichero para lectura");
			e.printStackTrace();
		}
		return persona;

	}

	/**
	 * Metodo para mostrar los objetos copiados, se usa para comprobar que las
	 * personas con serial UID 7 se han copiado correctamente en el fichero
	 */
	public static void mostrarObjetos() {
		try {
			ObjectInputStream oISMostrar = new ObjectInputStream(new FileInputStream(new File(DOCTRABAJO_OUT)));
			try {
				Persona_v2 personaComprobar = new Persona_v2();
				while (true) {
					personaComprobar = (Persona_v2) oISMostrar.readObject();
					System.out.println(personaComprobar);
				}
			} catch (ClassNotFoundException e) {
				System.out.println("Error de lectura");
				e.printStackTrace();
			} catch (EOFException e) {

			} finally {
				oIS.close();
			}
		} catch (IOException e) {
			System.out.println("Error al abrir el fichero para lectura");
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		inicializar();
		/**
		 * Mientras que haya personas en el fichero del ejrcicio1, se crea un objeto
		 * Persona con serialUID=1, despues se crea una Persona_v2 con los atributos de
		 * la persona leida y finalmente se escribe en el nuevo fichero
		 */
		while (!acabado) {
			Persona persona1leida = leerPersona2();
			// if que se añade para evitar que se copie en el fichero una persona con datos
			// nulos
			if (!acabado) {
				Persona_v2 persona2 = obtenerDatosv2(persona1leida);
				escribirObjeto(persona2);
			} else
				break;
		}
		System.out.println("Ahora añade una nueva persona al fichero: ");
		escribirObjeto(anadirPersona());
		try {
			oOS.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Actualmente, el fichero contiene a las siguientes personas: ");
		mostrarObjetos();
	}
}
