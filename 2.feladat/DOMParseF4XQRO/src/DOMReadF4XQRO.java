import java.io.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class DOMReadF4XQRO {
    public static void main(String[] args) {
        try {
            // XML fájl betöltése
            File inputFile = new File("XMLF4XQRO.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            // Root elem kiírása
            System.out.println("Root element: " + doc.getDocumentElement().getNodeName());

            // Adatok feldolgozása
            NodeList nodeList = doc.getElementsByTagName("here"); // "here" elemek feldolgozása
            
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element elem = (Element) node;
                    String neve = elem.getElementsByTagName("nev").item(0).getTextContent();
                    String kor = elem.getElementsByTagName("eletkor").item(0).getTextContent();
                    String szarnyseb = elem.getElementsByTagName("szarnysebesseg").item(0).getTextContent();

                    // Kiíratás blokk formátumban
                    String hallgatoInfo = String.format(
                            "Here neve: %s\nKor: %s\nSzárnysebessége :%s\n", neve, kor, szarnyseb);
                    System.out.println(hallgatoInfo);
                }
            }
        } catch (IOException | ParserConfigurationException | DOMException | SAXException e) {
        }
    }
}