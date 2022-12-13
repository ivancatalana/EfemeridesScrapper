package org.example;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.xml.sax.SAXException;
public class XMLcreator {
    public XMLcreator() throws IOException, SAXException {

    }

    public void escribirArchivoAcontecimiento(String[] acontecimiento) {
        File file = new File("src/Acontecimientos.xml");

        try {
            if (!file.exists()) {
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = factory.newDocumentBuilder();
                Document doc = dBuilder.newDocument();

                Element rootElement = doc.createElement("acontecimientos");
                doc.appendChild(rootElement);

                TransformerFactory tf = TransformerFactory.newInstance();
                Transformer t = tf.newTransformer();
                DOMSource source = new DOMSource(doc);
                StreamResult result = new StreamResult("src/Acontecimientos.xml");

                t.transform(source, result);
            } else {

                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = factory.newDocumentBuilder();
                Document doc = dBuilder.parse(file);
                // find root

                NodeList rootList = doc.getElementsByTagName("acontecimientos");
                Node root = rootList.item(0);
                Element elements = doc.createElement("acontecimiento");
                root.appendChild(elements);
                Element fecha = doc.createElement("fecha");
                fecha.setTextContent(acontecimiento[0]);
                elements.appendChild(fecha);
                Element año = doc.createElement("año");
                año.setTextContent(acontecimiento[1]);
                elements.appendChild(año);
                Element descripcion = doc.createElement("descripcion");
                año.setTextContent(acontecimiento[2]);
                elements.appendChild(descripcion);
                TransformerFactory tf = TransformerFactory.newInstance();
                Transformer t = tf.newTransformer();
                DOMSource source = new DOMSource(doc);
                StreamResult result = new StreamResult(new File("src/Acontecimientos.xml"));
                t.transform(source, result);

            }


        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (TransformerConfigurationException e) {
            throw new RuntimeException(e);
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }
    }

    public void escribirArchivoNacimiento(String[] nacimiento) {
        File file = new File("src/Nacimientos.xml");

        try {
            if (!file.exists()) {
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = factory.newDocumentBuilder();
                Document doc = dBuilder.newDocument();

                Element rootElement = doc.createElement("nacimientos");
                doc.appendChild(rootElement);
                TransformerFactory tf = TransformerFactory.newInstance();
                Transformer t = tf.newTransformer();
                DOMSource source = new DOMSource(doc);
                StreamResult result = new StreamResult("src/Nacimientos.xml");

                t.transform(source, result);

            } else {
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = factory.newDocumentBuilder();
                Document doc = dBuilder.parse(file);
                NodeList rootList = doc.getElementsByTagName("nacimientos");
                Node root = rootList.item(0);
                Element elements = doc.createElement("nacimiento");
                root.appendChild(elements);
                Element fecha = doc.createElement("fecha");
                fecha.setTextContent(nacimiento[0]);
                elements.appendChild(fecha);
                Element año = doc.createElement("año");
                año.setTextContent(nacimiento[1]);
                elements.appendChild(año);
                Element añoDef = doc.createElement("añoDefuncion");
                año.setTextContent(nacimiento[2]);
                elements.appendChild(añoDef);
                Element nombre = doc.createElement("nombre");
                nombre.setTextContent(nacimiento[3]);
                elements.appendChild(nombre);
                Element descripcion = doc.createElement("descripcion");
                descripcion.setTextContent(nacimiento[4]);
                elements.appendChild(descripcion);
                TransformerFactory tf = TransformerFactory.newInstance();
                Transformer t = tf.newTransformer();
                DOMSource source = new DOMSource(doc);
                StreamResult result = new StreamResult("src/Nacimientos.xml");

                t.transform(source, result);
            }


        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (TransformerConfigurationException e) {
            throw new RuntimeException(e);
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }

    }

    public void escribirArchivoDefuncion(String[] defuncion) {
        File file = new File("src/Defunciones.xml");

        try {
            if (!file.exists()) {
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = factory.newDocumentBuilder();
                Document doc = dBuilder.newDocument();

                Element rootElement = doc.createElement("defunciones");
                doc.appendChild(rootElement);
                TransformerFactory tf = TransformerFactory.newInstance();
                Transformer t = tf.newTransformer();
                DOMSource source = new DOMSource(doc);
                StreamResult result = new StreamResult("src/defunciones.xml");

                t.transform(source, result);
            } else {

                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = factory.newDocumentBuilder();
                Document doc = dBuilder.parse(file);

                NodeList rootList = doc.getElementsByTagName("defunciones");
                Node root = rootList.item(0);
                Element elements = doc.createElement("defuncion");
                root.appendChild(elements);
                Element fecha = doc.createElement("fecha");
                fecha.setTextContent(defuncion[0]);
                elements.appendChild(fecha);
                Element año = doc.createElement("año");
                año.setTextContent(defuncion[1]);
                elements.appendChild(año);
                Element añoNac = doc.createElement("añoNacimiento");
                año.setTextContent(defuncion[2]);
                elements.appendChild(añoNac);
                Element nombre = doc.createElement("nombre");
                nombre.setTextContent(defuncion[3]);
                elements.appendChild(nombre);
                Element descripcion = doc.createElement("descripcion");
                descripcion.setTextContent(defuncion[4]);
                elements.appendChild(descripcion);

                TransformerFactory tf = TransformerFactory.newInstance();
                Transformer t = tf.newTransformer();
                DOMSource source = new DOMSource(doc);
                StreamResult result = new StreamResult("src/Defunciones.xml");

                t.transform(source, result);
            }


        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (TransformerConfigurationException e) {
            throw new RuntimeException(e);
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } catch (SAXException ex) {
            throw new RuntimeException(ex);
        }


    }
}
