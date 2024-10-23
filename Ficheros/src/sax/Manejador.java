package sax;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Manejador extends DefaultHandler {
	public Manejador() {
		
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		super.startElement(uri, localName, qName, attributes);
		System.out.print("<" + qName);
		if(attributes.getLength()>0) {
			for (int i = 0; i < attributes.getLength(); i++) {
				System.out.print(" " + attributes.getLocalName(i) + "=" + attributes.getValue(i));
				
			}
		}
		System.out.println(">");
	}
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		
		super.endElement(uri, localName, qName);
		System.out.println("</" + qName + ">");
	}
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		
		super.characters(ch, start, length);
		System.out.println(new String(ch, start, length));
	}

}

