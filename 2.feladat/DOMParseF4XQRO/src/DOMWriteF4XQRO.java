import java.io.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class DOMWriteF4XQRO {
    public static void main(String[] args) {
        try {
            // XML fájl betöltése
            File inputFile = new File("XMLF4XQRO.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            // Fa struktúra kiírása konzolra
            System.out.println("XML Fa struktúra:");
            printNode(doc.getDocumentElement(), 0);

            // Új fájlba írás
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("XMLMeheszet1.xml"));
            transformer.transform(source, result);

            System.out.println("Az XML fájl sikeresen mentve a 'XMLMeheszet1.xml' fájlba.");
        } catch (IOException | IllegalArgumentException | ParserConfigurationException | TransformerException | SAXException e) {
        }
    }

    // Rekurzív függvény a fa struktúra kiírására
    private static void printNode(Node node, int indent) {
        String indentation = " ".repeat(indent * 4); // szóközök minden szinten
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            System.out.println(indentation + "Elem: " + node.getNodeName());

            // Attribútumok kiírása
            NamedNodeMap attributes = node.getAttributes();
            for (int i = 0; i < attributes.getLength(); i++) {
                Node attr = attributes.item(i);
                System.out.println(indentation + "  Attribútum: " + attr.getNodeName() + " = " + attr.getNodeValue());
            }

            // Gyerekek bejárása
            NodeList children = node.getChildNodes();
            for (int i = 0; i < children.getLength(); i++) {
                printNode(children.item(i), indent + 1);
            }
        } else if (node.getNodeType() == Node.TEXT_NODE) {
            String text = node.getTextContent().trim();
            if (!text.isEmpty()) {
                System.out.println(indentation + "  Szöveg: " + text);
            }
        }
    }
}
