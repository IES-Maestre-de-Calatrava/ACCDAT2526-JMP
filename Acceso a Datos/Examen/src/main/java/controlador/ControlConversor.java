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
import modelo.Opositor;
import vista.InterfazVista;

/**
 *
 * @author Javier Molina-Prados
 * Created on 11 oct 2025
 */
public class ControlConversor {
    
    private final String rutaRespuestas = ".\\estructura_carpetas\\archivos_pruebas\\archivos_suministrados\\respuestas.txt";
    private final String rutaPlantilla = ".\\estructura_carpetas\\archivos_pruebas\\archivos_suministrados\\plantilla_correctora.txt";
    private final String ruta = ".\\estructura_carpetas\\archivos_pruebas\\xml_html\\";
    private final String origenDatos = ruta + "datos_opositores.xml";
    private final String plantilla = ruta + "opositoresPlantilla.xsl";
    private final String salida = ruta + "opositores.html";

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
            case InterfazVista.CORRECCION_EXAMENES -> 
                examen.correccionExamenes(rutaRespuestas, rutaPlantilla);
                
            case InterfazVista.RECLAMACION -> {
                String respuestas = vista.pedirRespuestas();
                long id = vista.pedirId();
                examen.reclamacion(id, respuestas);
            }
            
            case InterfazVista.CREAR_ARBOLDOM ->{
                examen.crearArbolDOM();
            }
            
            case InterfazVista.COVERTIR_A_HMTL -> {
                conversor.ConvertirAHTML();
            }
            
            case InterfazVista.COPIA_SEGURIDAD-> {
                examen.copiaSeguridad();
            }
            
            default -> {    
            }
        }
        vista.arranca();
    }
    
    

}
