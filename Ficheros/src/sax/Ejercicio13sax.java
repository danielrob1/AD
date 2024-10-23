package sax;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class Ejercicio13sax {
	public static final String DOCTRABAJO_IN="Ejercicio13.xml";

	public static void main(String[] args) {
		try {
			SAXParserFactory sPF= SAXParserFactory.newInstance();
			SAXParser sP= sPF.newSAXParser();
			sP.parse(new InputSource(DOCTRABAJO_IN), new Manejador());
		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
