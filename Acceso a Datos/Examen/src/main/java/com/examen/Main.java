/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.examen;

import controlador.ControlConversor;
import java.io.IOException;
import javax.xml.transform.TransformerException;
import modelo.Conversor;
import modelo.Examen;
import vista.InterfazVista;
import vista.VentanaConversorTexto;

/**
 *
 * @author Javier Molina-Prados
 * Created on 2 nov 2025
 */
public class Main {
    
    public static void main (String [] args) throws IOException, TransformerException{
        
        /*
        String rutaPlantilla = ".\\estructura_carpetas\\archivos_pruebas\\archivos_suministrados\\plantilla_correctora.txt";
        String rutaRespuestas = ".\\estructura_carpetas\\archivos_pruebas\\archivos_suministrados\\respuestas.txt";
        
        Examen ex = new Examen();
        //ex.correccionExamenes(rutaRespuestas, rutaPlantilla);
        
        //ex.reclamacion(4, "badbacdbbd");
        
        //ex.crearArbolDOM();
                
        // --- Configuración de rutas (puede ser estática o provenir de entrada de usuario) ---
        
        
        String ruta = ".\\estructura_carpetas\\archivos_pruebas\\xml_html\\";
        String origenDatos = ruta + "datos_opositores.xml";
        String plantilla = ruta + "opositoresPlantilla.xsl";
        String salida = ruta + "opositores.html";

        Conversor conversor = new Conversor(origenDatos, plantilla, salida);
        conversor.ConvertirAHTML();
        
        
        ex.copiaSeguridad();
        
        */
        
        Examen examen = new Examen();
        Conversor conversor = new Conversor();
        
        InterfazVista vista = new VentanaConversorTexto();
        
        ControlConversor controlador = new ControlConversor(vista, conversor, examen);
        
    }
}
