package sax;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class Ejercicio13Amp {
    public static void main(String[] args) {
        try {
            // Crear la fábrica de parsers SAX
            SAXParserFactory factory = SAXParserFactory.newInstance();
            
            // Crear un nuevo parser SAX
            SAXParser saxParser = factory.newSAXParser();
            
            // Crear una instancia del manejador personalizado
            ManejadorSAX manejador = new ManejadorSAX();
            
            // Procesar el archivo XML
            saxParser.parse("Ejercicio13.xml", manejador);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
