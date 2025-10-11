/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package modelo;



import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import java.io.*;

/**
 *
 * @author Javier Molina-Prados
 * Created on 8 oct 2025
 */
public class GestionContenidoDOM {
    
    // Creamos nuestro documento y nuestra fabrica y constructos de XML para usarlos mas tarde
    Document documento; // creamos el documento aqui 
    DocumentBuilderFactory factory;
    DocumentBuilder builder;
        
    public GestionContenidoDOM(String nombre){
            
       try {
        // Crea una fábrica para construir analizadores DOM (trabajar con XML)
        // Para trabajar con XML debemos utilizar un parser.
        this.factory = DocumentBuilderFactory.newInstance();

        // Obtiene un constructor de documentos XML
        this.builder = factory.newDocumentBuilder();

        // Creamos un documento vacío con un nodo principal:
        DOMImplementation implementation = builder.getDOMImplementation();
        this.documento = implementation.createDocument(null, nombre, null); // aqui esta distinto a las diapositivas, porque crea el document en las propiedades

        // Define la versión del XML
        documento.setXmlVersion("1.0");

        } catch (ParserConfigurationException ex) {
            // Registra el error si hay un problema al configurar el parser
            System.getLogger(GestionContenidoDOM.class.getName())
                  .log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
        
    public Element addNodo(String nombreNodo){
        // Creamos el nodo 
        Element nodoNuevo = this.documento.createElement(nombreNodo);
        
        // Y ahora lo pegamos en nuestro documento
        this.documento.getDocumentElement().appendChild(nodoNuevo);
        
        return nodoNuevo;
                
    }

    public Element addNodo(String nombreNodo, Element elementoPadre){
        
        // Creamos el hijo “id” y lo añadimos: acabamos de añadir el elemento id(sin valor) de empleado
        Element dato = this.documento.createElement(nombreNodo);
        elementoPadre.appendChild(dato);
        
        return dato;
    }
    
    public void addNodoYTexto(String nombreNodo, String texto, Element elementoPadre){
        
        // Creamos un element para nombreNodo (id)
        Element dato = this.documento.createElement(nombreNodo);
        
        // Asignamos un valor al hijo y lo añadimos:
        Text textoDato = this.documento.createTextNode(texto);
        
        dato.appendChild(textoDato);
        
        elementoPadre.appendChild(dato); // no olvidar cerrar la etiqueta </id>, mira las diapositivas si te lias
        
    }

    private Transformer preProcess (String indent){
        
        Transformer transformer = null;
        
        try {
            // Obtenemos un transformer, para pasar el documento de memoria a disco (o archivo o por consola tambien, este metodo es comun)
            transformer = TransformerFactory.newInstance().newTransformer();
        } catch (TransformerConfigurationException ex) {
            System.getLogger(GestionContenidoDOM.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        
        // configuramos una propiedad del transformer dependiendo del valor que le estamos pasando: Y/N (formateado o no, todo en una linea o cada nodo en una)
        transformer.setOutputProperty(OutputKeys.INDENT, indent);
        
        return transformer;
    }
    
    
    public void generarArchivo(String nombreArchivo, String indent){
        
        try {
            // Indicamos el origen
            Source source = new DOMSource(this.documento);
            // Indicamos la salida
            Result salida = new StreamResult(new File(nombreArchivo));
            // aqui obtenemos el transformer directamente del metodo preProcess y lanzamos la conversion
            preProcess(indent).transform(source, salida);
            
        } catch (TransformerException ex) {
            System.getLogger(GestionContenidoDOM.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
    
    public void mostrarPantalla(String indent){
        /* El metodo es exactamente igual que el generarArchivo pero en vez de poner la salida a un new File("mombreFile");
        lo redirecciona al System.out para mostrar por la consola
        */
         try {
            // Indicamos el origen
            Source source = new DOMSource(this.documento);
            // Indicamos la salida
            Result salida = new StreamResult(System.out);
            // aqui obtenemos el transformer directamente del metodo preProcess y lanzamos la conversion
            preProcess(indent).transform(source, salida);
            
        } catch (TransformerException ex) {
            System.getLogger(GestionContenidoDOM.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
}

