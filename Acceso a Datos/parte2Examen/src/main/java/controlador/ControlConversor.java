/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package controlador;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.Scanner;
import javax.xml.transform.TransformerException;
import modelo.Conversor;
import modelo.Examen;
import modelo.GestionContenidoDOM;
import modelo.Objeto;
import vista.InterfazVista;

/**
 *
 * @author Javier Molina-Prados
 * Created on 11 oct 2025
 */
public class ControlConversor {
    
    // AQUI VAN LAS RUTAS DEL CONVERSOR
    private final String ruta = ".\\archivos_pruebas\\xml_html\\"; // RUTAS GENERALES
    private final String origenDatos = ruta + "datos_objetos.xml"; // EL XML
    private final String plantilla = ruta + "objetosPlantilla.xsl"; // LA XSL
    private final String salida = ruta + "objetos.html";  // EL HTML

    private final InterfazVista vista;
    private final Conversor conversor;
    private final Examen examen;

    public ControlConversor(InterfazVista vista, Conversor conversor, Examen examen) {
        this.vista = vista;
        this.conversor = new Conversor(origenDatos, plantilla, salida);
        this.examen = examen;

        this.vista.setControlador(this);
        this.vista.arranca();
    }
    
    public void ActionPerformed(ActionEvent e) throws IOException, TransformerException{
        
        switch(e.getActionCommand()){
            case InterfazVista.CREAR_ARBOLDOM ->{
                examen.crearArbolDOM();
            }
            
            case InterfazVista.COVERTIR_A_HMTL -> {
                conversor.ConvertirAHTML();
            }
            
            default -> {    
            }
        }
        vista.arranca();
    }
    
    

}
