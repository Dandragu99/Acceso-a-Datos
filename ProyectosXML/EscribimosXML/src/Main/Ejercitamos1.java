package Main;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;


public class Ejercitamos1 {
    public static void main(String[] args) {

        try {
            // 1. Hacemos el DocumentBuilderFactory para construir el terreno o factoría conla que trabajaremos
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            // 2. Con la factoría implementada ya podemos crear el Builder
            DocumentBuilder db = dbFactory.newDocumentBuilder();
            // 3. Con el builder ya podemos crear el documento
            Document document = (Document) db.newDocument();
            // 4. Creamos un elemento raíz dentro del archivo
            Element habitacion = document.createElement("objetos");
            habitacion.setAttribute("tipo1", "salon");
            habitacion.setAttribute("tipo2", "cocina");
            habitacion.setAttribute("tipo3", "baño");

            // 4. Añadir un nodo hijo
            Element nombreHabitacion = document.createElement("salon");
            nombreHabitacion.setTextContent("Televisión, sofá, mesa");
            habitacion.appendChild(nombreHabitacion);

            // 5. Añadir el nodo raíz al documento
            document.appendChild(habitacion);

            // 6. Preparar para guardar el documento como archivo XML
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            // 7. Indicar origen y destino
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File("habitaciones.xml"));

            // 8. Transformar (guardar) el documento
            transformer.transform(source, result);

            // 9. Confirmación por consola
            System.out.println("Archivo XML creado correctamente.");

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
