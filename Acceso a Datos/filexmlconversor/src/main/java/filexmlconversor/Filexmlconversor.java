/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package filexmlconversor;

import javax.xml.transform.TransformerException;
// Importamos el nuevo controlador
import controlador.ConversorControlador;

/**
 * Clase principal de la aplicación (la "A" de MVC).
 *
 * @author Javier Molina-Prados
 */
public class Filexmlconversor {

    public static void main(String[] args) { 
        
        // --- Configuración de rutas (puede ser estática o provenir de entrada de usuario) ---
        String ruta = ".\\archivos_pruebas\\";
        String origenDatos = ruta + "empleados.xml";
        String plantilla = ruta + "empleadosPlantilla.xsl";
        String salida = ruta + "empleados.html";

        // 1. Instanciar el Controlador, pasándole las rutas.
        ConversorControlador controlador = new ConversorControlador (origenDatos, plantilla, salida);
        
        // 2. Iniciar el proceso de conversión.
        controlador.ejecutarConversor();
    }
}