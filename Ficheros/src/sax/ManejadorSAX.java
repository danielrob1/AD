package sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.util.Scanner;

public class ManejadorSAX extends DefaultHandler {
    private String recetaBuscada;
    private boolean esRecetaCorrecta = false;
    private String ingredientes = "";

    public ManejadorSAX() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduce el nombre de la receta: ");
        recetaBuscada = scanner.nextLine();
    }
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase("receta")) {
            String nombreReceta = attributes.getValue("nombre");
            if (recetaBuscada.equalsIgnoreCase(nombreReceta)) {
                esRecetaCorrecta = true;
            }
        }
        if (esRecetaCorrecta && qName.equalsIgnoreCase("ingrediente")) {
            String cantidad = attributes.getValue("cantidad");
            String unidad = attributes.getValue("unidad");
            String nombre = attributes.getValue("nombre");

            ingredientes += "Ingrediente: " + nombre;
            if (cantidad != null && !cantidad.isEmpty()) {
                ingredientes += ", Cantidad: " + cantidad;
            }
            if (unidad != null && !unidad.isEmpty()) {
                ingredientes += " " + unidad;
            }
            ingredientes += "\n";
        }
    }
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("receta")) {
            if (esRecetaCorrecta) {
                esRecetaCorrecta = false;
                System.out.println("Ingredientes de la receta '" + recetaBuscada + "':\n" + ingredientes);
            }
        }
    }
}
