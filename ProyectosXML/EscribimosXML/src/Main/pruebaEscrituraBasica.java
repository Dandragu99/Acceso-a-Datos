package Main;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class pruebaEscrituraBasica {
    public static <ParserConfigurationException> void main(String[] args) {
        try {
            // Creamos el documento XML
            // Lo primero: dbFactory
            DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance();
            // Segundo: DocumentBuilder
            DocumentBuilder builder = dbfactory.newDocumentBuilder();
            // Tercero: el document
            Document doc = builder.newDocument();

            // Crear elementos
            Element root = doc.createElement("personas");
            doc.appendChild(root);

            Element persona = doc.createElement("persona");
            root.appendChild(persona);

            Element nombre = doc.createElement("nombre");
            nombre.setTextContent("Juan");
            persona.appendChild(nombre);

            Element edad = doc.createElement("edad");
            edad.setTextContent("30");
            persona.appendChild(edad);

            // Guardar como archivo XML
            var transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);

            StreamResult result = new StreamResult(new File("personas.xml"));
            transformer.transform(source, result);

            System.out.println("Archivo XML generado correctamente.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
