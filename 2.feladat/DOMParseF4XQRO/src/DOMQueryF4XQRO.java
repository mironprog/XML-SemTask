import java.io.File;
import java.io.IOException;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class DOMQueryF4XQRO {
    public static void main(String[] args) {
        try {
            // XML dokumentum betöltése és elemzése
            File inputFile = new File("XMLF4XQRO.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            // 1. lekérdezés: Összes "kaptar" elem kilistázása és az "kapkod" attribútum kiírása
            System.out.println("\n1. lekérdezés: Kaptárak és azonosítóik:");
            NodeList kaptarList = doc.getElementsByTagName("kaptar");
            for (int i = 0; i < kaptarList.getLength(); i++) {
                Node kaptarNode = kaptarList.item(i);
                if (kaptarNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element kaptarElement = (Element) kaptarNode;
                    System.out.println("Kaptár azonosító: " + kaptarElement.getAttribute("kapkod"));
                }
            }

            // 2. lekérdezés: Lokációk városainak kiírása
            System.out.println("\n2. lekérdezés: Lokációk városai:");
            NodeList lokacioList = doc.getElementsByTagName("lokacio");
            for (int i = 0; i < lokacioList.getLength(); i++) {
                Node lokacioNode = lokacioList.item(i);
                if (lokacioNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element lokacioElement = (Element) lokacioNode;
                    String varos = lokacioElement.getElementsByTagName("varos").item(0).getTextContent();
                    System.out.println("Város: " + varos);
                }
            }

            // 3. lekérdezés: Királynők nevei és életkoruk
            System.out.println("\n3. lekérdezés: Királynők nevei és életkoraik:");
            NodeList kiralynoList = doc.getElementsByTagName("kiralyno");
            for (int i = 0; i < kiralynoList.getLength(); i++) {
                Node kiralynoNode = kiralynoList.item(i);
                if (kiralynoNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element kiralynoElement = (Element) kiralynoNode;
                    String nev = kiralynoElement.getElementsByTagName("nev").item(0).getTextContent();
                    String eletkor = kiralynoElement.getElementsByTagName("eletkor").item(0).getTextContent();
                    System.out.println("Királynő neve: " + nev + ", életkor: " + eletkor);
                }
            }

            // 4. lekérdezés: Mézkészítések típusai és mennyiségeik
            System.out.println("\n4. lekérdezés: Mézkészítések típusai és mennyiségeik:");
            NodeList mezkeszitesList = doc.getElementsByTagName("mezkeszites");
            for (int i = 0; i < mezkeszitesList.getLength(); i++) {
                Node mezkeszitesNode = mezkeszitesList.item(i);
                if (mezkeszitesNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element mezkeszitesElement = (Element) mezkeszitesNode;
                    String virag = mezkeszitesElement.getElementsByTagName("virag").item(0).getTextContent();
                    String mennyiseg = mezkeszitesElement.getElementsByTagName("Mennyiség").item(0).getTextContent();
                    System.out.println("Virág: " + virag + ", Mennyiség: " + mennyiseg);
                }
            }

        } catch (IOException | ParserConfigurationException | DOMException | SAXException e) {
        }
    }
}
