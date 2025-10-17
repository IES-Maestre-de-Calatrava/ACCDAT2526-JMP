/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package filedomxml;

import java.util.List;
import modelo.Empleado;
import modelo.GestionContenidoDOM;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Javier Molina-Prados
 */
public class Filedomxml {

    public static void main(String[] args) {
        GestionContenidoDOM arbol = new GestionContenidoDOM("Empleados");
        
        // Crear el nodo empleado
        // Element elem = arbol.addNodo("Empleado");
        
        // le ponemos un id vacio
        // Element elemhijo = arbol.addNodo("id", elem);
        
        
        // aqui ya le metemos valores
        // arbol.addNodoYTexto("id", "1", elem);
        
        // arbol.addNodoYTexto("nombre","Pedro", elem);
        
        //arbol.generarArchivo(".\\archivos_pruebas\\empleados.xml", "yes");
        
        // muestro por pantalla sin tabular
        // arbol.mostrarPantalla("no");
        // System.out.println();
        // System.out.println();
 
        // muestro por pantalla tabulado
        // arbol.mostrarPantalla("yes");
        
        //arbol.cargarArchivoEnMemoria(".\\archivos_pruebas\\empleados.xml");
        arbol.cargarArchivoEnMemoria(".\\archivos_pruebas\\empleados_pruebas.xml");

        // arbol.mostrarPantalla("yes");
        
        // arbol.addElement();
        // arbol.addElement("Empleado", "Saldo", "1000");
        
        //arbol.generarArchivo(".\\archivos_pruebas\\empleados_pruebas.xml", "yes");
        
        arbol.eliminarElemento("Empleado", "Cargo");
        arbol.modificarEtiqueta(1,"identificador", "200");
        arbol.modificarEtiqueta(2,"identificador","1300");
        arbol.modificarEtiqueta(3,"identificador","55000");
        
        // hay que generar el archivo siempre despues de eliminar algo o cambiar algo
        arbol.generarArchivo(".\\archivos_pruebas\\empleados_pruebas.xml", "yes");
        arbol.mostrarPantalla("yes");
        
        
        /*
        NodeList empleados = arbol.getNodesValue("Empleado");
        
        int longitud = empleados.getLength();
        
        for (int i = 0; i < longitud; i++){
            Node emple = empleados.item(i);
            
            Element elem = (Element) emple;
            
            System.out.println("El nombre del empleado es: "+ arbol.getTagValue("nombre", elem));
        }
        */
        
        
        /*
        List<Empleado> empleList = arbol.getEmpleados();
        
        for (Empleado emple: empleList){
            
            System.out.println(emple.toString());
            
        }
        */
        

    }
}
