package sax;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class Ejercicio13Amp {
    public static void main(String[] args) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            ManejadorSAX manejador = new ManejadorSAX();
            saxParser.parse("Ejercicio13.xml", manejador);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
