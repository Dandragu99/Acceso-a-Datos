/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package escrituraxml;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author Dragu
 */
public class EscrituraXML {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = factory.newDocumentBuilder();
    Document doc = builder.newDocument();

    Element root = doc.createElement("biblioteca");
    doc.appendChild(root);

    // Primer libro
    Element libro1 = doc.createElement("libro");
    Element titulo1 = doc.createElement("titulo");
    titulo1.setTextContent("El Cansino");
    Element autor1 = doc.createElement("autor");
    autor1.setTextContent("Pedro Nieto");
    Element año1 = doc.createElement("año");
    año1.setTextContent("2015");
    Element categoria1 = doc.createElement("categoria");
    categoria1.setTextContent("Acción");

    libro1.appendChild(titulo1);
    libro1.appendChild(autor1);
    libro1.appendChild(año1);
    libro1.appendChild(categoria1);
    root.appendChild(libro1);

    // Segundo libro
    Element libro2 = doc.createElement("libro");
    Element titulo2 = doc.createElement("titulo");
    titulo2.setTextContent("El Paquito");
    Element autor2 = doc.createElement("autor");
    autor2.setTextContent("Pedro Alfonso");
    Element año2 = doc.createElement("año");
    año2.setTextContent("2012");
    Element categoria2 = doc.createElement("categoria");
    categoria2.setTextContent("Aventura");

    libro2.appendChild(titulo2);
    libro2.appendChild(autor2);
    libro2.appendChild(año2);
    libro2.appendChild(categoria2);
    root.appendChild(libro2);

    // Guardar en XML
    Transformer transformer = TransformerFactory.newInstance().newTransformer();
    DOMSource source = new DOMSource(doc);
    StreamResult result = new StreamResult(new File("biblioteca.xml"));
    transformer.transform(source, result);
    }
}


