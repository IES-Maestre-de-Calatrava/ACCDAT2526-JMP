/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Javier Molina-Prados
 * Created on 11 oct 2025
 */
public class Archivo {
     
    private String ruta;
    
    /**
     * Constructor de la clase
     * @param ruta   ruta del archivo
     */
    public Archivo(String ruta){
        this.ruta = ruta;
    }
    
    /**
     * Constructor vacío de la clase
     */
    public Archivo(){
        
    }

    
    /**
     * Getter del atributo ruta
     * @return      devuelve la ruta
     */
    public String getRuta() {
        return ruta;
    }

    
    /**
     * Setter del atributo ruta
     * @param ruta  ruta de nuestro archivo
     */
    public void setRuta(String ruta) {
        this.ruta = ruta;
    }
    
    
    /**
     * Devuelve en el Path la ruta 
     * @return  Path con el archivo
     */
    public Path getPathDeRuta(){
        Path directorioRaiz = Paths.get(ruta);
        return directorioRaiz;
    }
    
    
    
    public void crearArchivoInterno(String ruta) throws IOException{
    // Le indicamos la ruta completa al directorio o archivo
    Path p = Paths.get(ruta);
    
        if (Files.exists(p)){
            System.out.print("El archivo ya existe");
        } else{
            // creamos el archivo en esta clase
            try{
                Path creado = Files.createFile(p);
                System.out.println("Archivo creado en "+creado.toAbsolutePath());
            }catch(IOException ex){
                Logger.getLogger(Carpeta.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    
    }
    
    /**
     * Crea un archivo en la ruta indicando el directorio padre y el nombre del archivo nuevo
     * @param nombreDirectorio      Nombre del directorio padre
     * @param nombreArchivo         Nomrbre del archivo 
     * @throws IOException 
     */
    public void crearArchivo(Path nombreDirectorio, String nombreArchivo) throws IOException{
        crearArchivoInterno(nombreDirectorio.toString()+"\\"+nombreArchivo);
        
    }
    
    public void cambiarNombre(String ruta, String nombreArchivo, String nombreNuevo){
        File archivoOriginal = new File(ruta + File.separator + nombreArchivo);        
        File archivoNuevo = new File(ruta + File.separator + nombreNuevo);
        
       boolean exito = archivoOriginal.renameTo(archivoNuevo);

        if (exito) {
            System.out.println("Archivo renombrado exitosamente a: " + archivoNuevo.getName());
        } else {
            System.out.println("No se pudo cambiar el nombre del archivo.");
        }
    }
    
    
    public void copiarArchivo(String rutaOrigen, String rutaDestino){
        
        // primero nos guardamos las paths 
        Path origen = Paths.get(rutaOrigen);
        Path destino = Paths.get(rutaDestino);
        
        
        try {
            // usamos el copy de la clase Files, pasando el origen, destino, y el modo: copia estandar remplazando si el archivo existe
            Files.copy(origen, destino, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Archivo copiado de " + origen + " a " + destino);

        } catch (IOException e) {
            System.out.println("Error al copiar el archivo: " + e.getMessage());
        } 
    }
    
    
    public void moverArchivo(String rutaOrigen, String rutaDestino){
        
        /*
        ESTA FORMA ES LA CLASICA SIN USAR Files
        
        copiarArchivo(rutaOrigen, rutaDestino);
        eliminarRutaCompleta(rutaOrigen);
        */
        
        // primero nos guardamos las paths 
        Path origen = Paths.get(rutaOrigen);
        Path destino = Paths.get(rutaDestino);
        
        
        try {
            // usamos el copy de la clase Files, pasando el origen, destino, y el modo: copia estandar remplazando si el archivo existe
            Files.move(origen, destino, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Archivo copiado de " + origen + " a " + destino);

        } catch (IOException e) {
            System.out.println("Error al copiar el archivo: " + e.getMessage());
        } 
    }
    

    
    public void eliminarRutaCompleta(String ruta){
        Path dir = Paths.get(ruta);
        try{
            if (Files.isDirectory(dir)){
                try(DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
                    for (Path entry : stream) {
                        eliminarRutaCompleta(entry.toString());
                    }
                }
            }
            
            Files.delete(dir);
            System.out.println("Eliminado: " + dir);
        }catch(IOException ex){
             System.out.println("Error al eliminar: " + ex.getMessage());
        }
           
   }
}
