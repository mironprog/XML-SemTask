import java.io.File;
import java.io.IOException;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class DOMModifyF4XQRO {
    public static void main(String[] args) {
        try {
            // XML dokumentum betöltése
            File inputFile = new File("XMLF4XQRO.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            // 1. Módosítás: Egy adott "kaptar" elem hőmérsékletének frissítése
            NodeList kaptarList = doc.getElementsByTagName("kaptar");
            if (kaptarList.getLength() > 0) {
                Element firstKaptar = (Element) kaptarList.item(0);
                firstKaptar.getElementsByTagName("homerseklet").item(0).setTextContent("35");
                System.out.println("1. Módosítás: Az első kaptár hőmérséklete 35-re módosítva.");
            }

            // 2. Módosítás: Új "kaptar" elem hozzáadása
            Element newKaptar = doc.createElement("kaptar");
            newKaptar.setAttribute("kapkod", "kap4");

            Element newHomerseklet = doc.createElement("homerseklet");
            newHomerseklet.setTextContent("30");
            newKaptar.appendChild(newHomerseklet);

            Element newLokacio = doc.createElement("lokacio");
            Element newVaros = doc.createElement("varos");
            newVaros.setTextContent("Debrecen");
            Element newUtca = doc.createElement("utca");
            newUtca.setTextContent("Fő");
            Element newHazszam = doc.createElement("hazszam");
            newHazszam.setTextContent("10");
            newLokacio.appendChild(newVaros);
            newLokacio.appendChild(newUtca);
            newLokacio.appendChild(newHazszam);
            newKaptar.appendChild(newLokacio);

            Element newKeretszam = doc.createElement("keretszam");
            newKeretszam.setTextContent("12");
            newKaptar.appendChild(newKeretszam);

            doc.getDocumentElement().appendChild(newKaptar);
            System.out.println("2. Módosítás: Új kaptár hozzáadva Debrecen városában.");

            // 3. Módosítás: Egy "kiralyno" életkorának növelése
            NodeList kiralynoList = doc.getElementsByTagName("kiralyno");
            if (kiralynoList.getLength() > 0) {
                Element firstKiralyno = (Element) kiralynoList.item(0);
                String currentAge = firstKiralyno.getElementsByTagName("eletkor").item(0).getTextContent();
                int newAge = Integer.parseInt(currentAge) + 1;
                firstKiralyno.getElementsByTagName("eletkor").item(0).setTextContent(String.valueOf(newAge));
                System.out.println("3. Módosítás: Az első királynő életkora növelve: " + newAge + " év.");
            }

            // 4. Módosítás: Egy adott "dolgozo" elem kedvenc virágának megváltoztatása
            NodeList dolgozoList = doc.getElementsByTagName("dolgozo");
            if (dolgozoList.getLength() > 0) {
                Element firstDolgozo = (Element) dolgozoList.item(0);
                firstDolgozo.getElementsByTagName("kedvenccirag").item(0).setTextContent("Tulipán");
                System.out.println("4. Módosítás: Az első dolgozó kedvenc virága Tulipánra változtatva.");
            }

            // Az XML dokumentum mentése
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("XMLF4XQROModified.xml"));
            transformer.transform(source, result);

            System.out.println("\nAz XML sikeresen mentve a 'XMLF4XQROModified.xml' fájlba.");

        } catch (IOException | IllegalArgumentException | ParserConfigurationException | TransformerException | DOMException | SAXException e) {
        }
    }
}
