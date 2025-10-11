/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package filedomxml;

import modelo.GestionContenidoDOM;
import org.w3c.dom.Element;

/**
 *
 * @author Javier Molina-Prados
 */
public class Filedomxml {

    public static void main(String[] args) {
        GestionContenidoDOM arbol = new GestionContenidoDOM("Empleados");
        
        // Crear el nodo empleado
        Element elem = arbol.addNodo("Empleado");
        
        // le ponemos un id vacio
        // Element elemhijo = arbol.addNodo("id", elem);
        
        
        // aqui ya le metemos valores
        arbol.addNodoYTexto("id", "1", elem);
        
        arbol.addNodoYTexto("nombre","Pedro", elem);
        
        //arbol.generarArchivo(".\\archivos_pruebas\\empleados.xml", "yes");
        
        // muestro por pantalla sin tabular
        arbol.mostrarPantalla("no");
        System.out.println();
        System.out.println();
 
        // muestro por pantalla tabulado
        arbol.mostrarPantalla("yes");

    }
}
