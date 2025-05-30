package Main;
import java.io.FileOutputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
public class ActualizarXML {
    // Copiamos y comentamos código de clase
    public static void main(String[] args) {
        try {
            // Parseamos el archivo XML existente
            String nombreFichero = "curso"; // Nombre del archivo XML


            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument(); // Creamos un nuevo documento
            // Creamos la raíz del documento y añadimos atributos
            Element root = doc.createElement("curso");
            root.setAttribute("nivel", "2");
            root.setAttribute("ciclo", "DAM");
            doc.appendChild(root);
            // Creamos un nuevo módulo y lo añadimos al curso
            Element modulo = doc.createElement("modulo");
            root.appendChild(modulo);
            // Añadimos las características del módulo
            Element nombreModulo = doc.createElement("nombre");
            nombreModulo.appendChild(doc.createTextNode("Programación de servicios y procesos"));
            nombreModulo.setAttribute("curso", "2");
            modulo.appendChild(nombreModulo);
            // Transformamos y escribimos los cambios en el archivo XML
            TransformerFactory transformerFactory = TransformerFactory.
                        newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new
                    FileOutputStream(nombreFichero + ".xml"));
            transformer.transform(source, result);
            System.out.println("Datos actualizados en el archivo XML.");
            } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
