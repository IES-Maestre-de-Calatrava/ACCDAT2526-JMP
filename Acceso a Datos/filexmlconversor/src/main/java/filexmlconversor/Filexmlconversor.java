/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package filexmlconversor;

import javax.xml.transform.TransformerException;
import modelo.Conversor;

/**
 *
 * @author Javier Molina-Prados
 */
public class Filexmlconversor {

    public static void main(String[] args) throws TransformerException {
        
        /*
        String ruta = ".\\archivos_pruebas\\";
        String origenDatos = ruta + "alumnos.xml";
        String plantilla = ruta + "alumnosPlantilla.xsl";
        String salida = ruta + "alumnos.html";
        */
        
        String ruta = ".\\archivos_pruebas\\";
        String origenDatos = ruta + "empleados.xml";
        String plantilla = ruta + "empleadosPlantilla.xsl";
        String salida = ruta + "empleados.html";

        Conversor conv = new Conversor (origenDatos, plantilla, salida);
        
        conv.ConvertirAHTML();
    }
}
