/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package clasenio;


import controlador.ControlConversor;
import modelo.Carpeta;
import java.io.File;
import java.io.IOException;
import modelo.Archivo;
import vista.InterfazVista;
import vista.VentanaConversorTexto;
import java.nio.file.Path;
import java.nio.file.Files;

/**
 *
 * @author Javier Molina-Prados
 * Created on 11 oct 2025
 */
public class ClaseNio {
    
    public static void main(String [] args) throws IOException{
        
        /*
        "C:\Users\Javier\Documents\GitHub\ACCDAT2526-JMP\ClaseFile"
        "C:\Users\Javier\Documents\GitHub\ACCDAT2526-JMP\ClaseFile\Fisica"
      
        Archivo prueba = new Archivo("C:\\Users\\Javier\\Documents\\GitHub\\ACCDAT2526-JMP\\ClaseFile\\Matematicas");
        File Directorio = new File(prueba.getRuta());
        prueba.crearArchivo(Directorio,"operaciones.txt");
        */ 
        
        
        Carpeta modelo = new Carpeta("C:\\Users\\Javier\\Documents\\GitHub\\ACCDAT2526-JMP\\ClaseFile");
        
        Archivo modelo2 = new Archivo("C:\\Users\\Javier\\Documents\\GitHub\\ACCDAT2526-JMP\\ClaseFile");
        
        InterfazVista vista = new VentanaConversorTexto();
        
        ControlConversor control = new ControlConversor(vista,modelo,modelo2);
        
    }

}
