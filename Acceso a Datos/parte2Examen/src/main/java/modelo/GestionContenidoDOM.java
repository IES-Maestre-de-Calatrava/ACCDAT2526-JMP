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
import java.util.ArrayList;
import java.util.List;
import org.xml.sax.SAXException;

/**
 *
 * @author b15-11m
 */
public class GestionContenidoDOM {
    // Para trabajar con XML debemos utilizar un parser
    DocumentBuilderFactory factory;
    // Creamos un constructor de documenttos
    DocumentBuilder builder;
    
    Document documento;
    
    /**
     * Inicializa un documento xml vacío con un nodo raíz
     * @param nombre                Nodo raíz llamado "nombre"
     */
    public GestionContenidoDOM(String nombre){
        try{
            this.factory = DocumentBuilderFactory.newInstance();
            this.builder = factory.newDocumentBuilder();
        
            // Creamos un documento vacion con un nodo principal
            DOMImplementation implementation = builder.getDOMImplementation();
           this.documento = implementation.createDocument(null, nombre, null);
            
            // Asignamos la versión del XML
            documento.setXmlVersion("1.0");
        
        } catch(ParserConfigurationException e){
            e.printStackTrace();
        }        
    }
    
    /**
     * Añade un nodo hijo a un nodo raíz
     * @param nombreNodo            Nombre del nodo hijo
     * @return                      Devuelve el nodo creado
     */
    public Element addNodo(String nombreNodo){
        // Creamos un nodo
        Element nodoPrincipal = this.documento.createElement(nombreNodo);
        
        // Y lo pegamos en el documento
        this.documento.getDocumentElement().appendChild(nodoPrincipal);
        
        return nodoPrincipal;
    }
    
    /**
     * Añade un nodo hijo a un nodo padre específico
     * @param nombreNodo            Nodo hijo que se añadirá al padre
     * @param elementoPadre         Nodo padre al que añadiremos el hijo
     * @return                      Devuelve el nodo creado
     */
    public Element addNodo(String nombreNodo, Element elementoPadre){
        // Creamos el elemento hijo y lo añadimos
        Element dato  = this.documento.createElement(nombreNodo);
        elementoPadre.appendChild(dato);
        
        return dato;
    }
    
    /**
     * Añade un nodo con texto como hijo de otro nodo
     * @param nombreNodo            Nodo hijo que se añadirá al padre
     * @param valor                 Valor del elemento a añadir
     * @param elementoPadre         Nodo padre al que añadiremos el hijo
     */
    public void addNodoYTexto(String nombreNodo, String valor, Element elementoPadre){
        // Creamos el elemento dato con un valor
        Element dato = this.documento.createElement(nombreNodo);
        Text textoDato = this.documento.createTextNode(valor);
        
        dato.appendChild(textoDato);
        
        // Añadimos el elemento dato al elementoPadre
        elementoPadre.appendChild(dato);
    }
    
    /**
     * Configura el Transformer para exportar el xml con indentación
     * @param indent                Indentación
     * @return                      Archivo xml list para usar
     */
    private Transformer preProcess(String indent){
        Transformer transformer = null;
        try {
            // Obtenemos un transformer y lanzamos la conversion
            transformer = TransformerFactory.newInstance().newTransformer();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        }
        
        transformer.setOutputProperty(OutputKeys.INDENT, indent);
        
        return transformer;
    }
    
    /**
     * Guarda el documento xml en un archivo
     * @param nombreArchivo             Nombre del archivo donde guardar el xml
     * @param indent                    Indentación
     */
    public void generarArchivodelDOM(String nombreArchivo, String indent){
        
        try {
            // Origen a convertir
            Source source = new DOMSource(this.documento);
        
            // Archivo de salida
            Result salida = new StreamResult(new File(nombreArchivo));
            preProcess(indent).transform(source, salida);
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Muesta el contenido xml por consola
     * @param indent                    Indentación
     */
    public void mostrarPorPantalla(String indent){
        try {
            // Origen a convertir
            Source source = new DOMSource(this.documento);
        
            // Archivo de salida
            Result salida = new StreamResult(System.out);
            preProcess(indent).transform(source, salida);
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Carga un archivo xml existente en memoria
     * @param nombreArchivo             Nombre del archivo a cargar
     */
    public void cargarArchivoEnMemoria(String nombreArchivo){
        try{
            this.documento = this.builder.parse(new File(nombreArchivo));
            this.documento.getDocumentElement().normalize();
        } catch(SAXException | IOException e){
            e.printStackTrace();
        }
    }
    
    /**
     * Devuelve el nombre del nodo raíz del documento
     * @return 
     */
    public String getElementPrincipal(){
        return this.documento.getDocumentElement().getNodeName();
        
    }
    
    /**
    * Extrae el valor de texto de un nodo hijo de forma segura.
    * @param tag             Nombre del nodo hijo
    * @param element         Elemento padre
    * @return               Valor del elemento extraído, o una cadena vacía ("") si no se encuentra el nodo o no tiene valor.
    */
    public String getTagValue(String tag, Element element){
       // 1. Obtener la lista de nodos con el nombre de la etiqueta
       NodeList tagList = element.getElementsByTagName(tag);

       // 2. Verificar si se encontró al menos un nodo (¡CRUCIAL!)
       if (tagList.getLength() > 0) {

           // 3. Obtener el primer elemento de la lista
           Node tagNode = tagList.item(0);

           // 4. Si el nodo del elemento existe, usar getTextContent() (es más seguro)
           if (tagNode != null) {
               // getTextContent() devuelve el valor del nodo de texto contenido en la etiqueta.
               // Devuelve una cadena vacía si el nodo existe pero no tiene contenido.
               return tagNode.getTextContent();
           }
       }

       // 5. Devolver una cadena vacía si la etiqueta no existe o no tiene valor. 
       // Esto es mejor que devolver 'null' ya que evita NullPointerException al llamar a métodos como .isEmpty() o .length()
       return "";
   }
    
    /**
     * Devuelve todos los nodos con el nombre dado
     * @param tag               Nombre de los nodos
     * @return                  Nodos extraidos con el nombre
     */
    public NodeList getNodesValue(String tag){
        NodeList empleados = documento.getElementsByTagName(tag);
        
        return empleados;
    }
    
    // ... (código previo)

    /**
    * Convierte un nodo xml en un objeto Empleado.
    * @param node               Nodo a convertir (debe ser un elemento "Empleado")
    * @return                   Nodo convertido a objeto Empleado
    */
    public Objeto getOpositor(Node node){
       Objeto opo = new Objeto();

       if(node.getNodeType() == Node.ELEMENT_NODE){
           Element element = (Element) node;

           // 1. Identificador: AHORA INCLUYE COMPROBACIÓN CONTRA CADENA VACÍA
           String idStr = getTagValue("id" , element);
           if (idStr != null && !idStr.isEmpty()) { // <--- **CORRECCIÓN CLAVE**
               try {
                   opo.setId(Long.parseLong(idStr));
               } catch (NumberFormatException e) {
                   System.err.println("Error al parsear el id: " + idStr);
               }
           }


           // 2. Strings
           opo.setDni(getTagValue("dni", element));
           opo.setApellidos(getTagValue("apellidos", element));
           opo.setNombre(getTagValue("nombre", element));

           // 3. Nota: Convertir String a INT (Ya tenía la comprobación)
           String notaStr = getTagValue("nota", element);
           if (notaStr != null && !notaStr.isEmpty()) {
               try {
                   // Conversión de String a int
                   opo.setNota(Integer.parseInt(notaStr));
               } catch (NumberFormatException e) {
                   // Manejo de error si el valor no es un número válido
                   System.err.println("Error al parsear el departamento: " + notaStr);
               }
           }
       }
       return opo;
   }
   

    
    /**
     * Devuelve un alista de todos los empleados en el documento
     * @return                  Todos los empleados en una lista
     */
    public List<Objeto> getOpositores(){
        List<Objeto> opoList = new ArrayList<Objeto>();
        NodeList nodeList = this.documento.getElementsByTagName("opositor");
        
        for(int i = 0; i < nodeList.getLength(); i ++){
            opoList.add(getOpositor(nodeList.item(i)));
        }
        return opoList;
    }
    
    
    /**
     * Elimina el nodo hijo de cada nodo padre
     * @param elemPadre             Nodo padre en el que se encuentra el nodo hijo
     * @param elemHijo              Nodo hijo que se quiere eliminar del nodo padre
     */
    public void eliminarElementOpositores(String elemPadre, String elemHijo){
        NodeList listaPadres = this.documento.getElementsByTagName(elemPadre);
        for(int i = 0; i < listaPadres.getLength(); i ++){
            Element elemOpositor = (Element) listaPadres.item(i);
            NodeList elementos = elemOpositor.getElementsByTagName(elemHijo);
            if(elementos.getLength() > 0){
                Node elem = elementos.item(0);
                elemOpositor.removeChild(elem);
            }
        }
    }
    
}

