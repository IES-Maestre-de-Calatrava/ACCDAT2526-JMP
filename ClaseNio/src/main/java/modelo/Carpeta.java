/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package modelo;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Javier Molina-Prados
 */
public class Carpeta {
    
    private String ruta;
    
    /**
     * Constructor de la clase
     * @param ruta      ruta de la carpeta
     */
    public Carpeta(String ruta){
        this.ruta = ruta;
    }
    
    /**
     * Constructor vacío de la clase
     */
    public Carpeta(){
        
    }

    
    /**
     * Getter del atributo ruta
     * @return  devuelve el contenido del atributo ruta
     */
    public String getRuta() {
        return ruta;
    }
    
    
    /**
     * Setter del atributo ruta
     * @param ruta      devuelve la ruta
     */
    public void setRuta(String ruta) {
        this.ruta = ruta;
    }
    
    
    /**
     * Devuelve en el Path la ruta 
     * @return  File con la carpeta
     */
    public Path getPathDeRuta(){
        Path directorioRaiz = Paths.get(ruta);
        return directorioRaiz;
    }
    
    
    /**
     * Clase base para crear carpetas dada la ruta
     * @param ruta
     * @throws IOException 
     */
    public void crearCarpetaInterna(String ruta) throws IOException{
    // Le indicamos la ruta completa al directorio o archivo
    Path p = Paths.get(ruta);
    
        if (Files.exists(p)){
            System.out.print("El directorio ya existe");
        } else{
            try{
                Path donePath = Files.createDirectory(p);
                System.out.println("Directorio creado en "+donePath.toAbsolutePath());
            }catch(IOException ex){
                Logger.getLogger(Carpeta.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    
    }
    
    
    /**
     * Crea un directorio en la ruta (este es al que le pasamos la ruta completa)
     */
    public void crearCarpeta() throws IOException{
        crearCarpetaInterna(ruta);
    }
    
    
    /**
     * Crea un directorio en la ruta dado el nombre del directorio
     * @param nombreDirectorio      nombre del directorio nuevo
     * @throws IOException 
     */
    public void crearCarpeta(String nombreDirectorio) throws IOException{
        crearCarpetaInterna(ruta+"\\"+nombreDirectorio);
    }
    
    
    /**
     * Crea un directorio en la ruta indicando el directorio padre y el nombre del directorio nuevo
     * @param directorioRaiz        Path que representa el padre
     * @param nombreDirectorio      Nombre del directorio nuevo
     * @throws IOException 
     */
    public void crearCarpeta(Path directorioRaiz, String nombreDirectorio) throws IOException{
        crearCarpetaInterna(directorioRaiz.toString()+"\\"+nombreDirectorio);
    }
    
    
    
    public void verContenidoDir(String rutaDirectorio){
        Path dir = Paths.get(rutaDirectorio);
        
        if(Files.isDirectory(dir)){
            System.out.println("Contenido del directorio: "+ dir.toAbsolutePath());
            
            try(DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
               for (Path entry : stream) {
                   if (Files.isRegularFile(entry)){
                       System.out.println(" [ARCHIVO] "+ entry.getFileName());
                        System.out.println("    Ruta: " + entry.toAbsolutePath());
                        System.out.println("    Siza: " + Files.size(entry) + " bytes");
                        System.out.println("    Ultima modificacion: " + new Date(Files.getLastModifiedTime(entry).toMillis()));
                        System.out.println("    Permisos -> Leer: " + Files.isReadable(entry) + 
                                       " | Escribir: " + Files.isWritable(entry) + 
                                       " | Ejecutar: " + Files.isExecutable(entry));
                    } else if (Files.isDirectory(entry)) {
                        System.out.println(" [DIRECTORIO] " + entry.getFileName());
                        // Llamada recursiva para las subcarpetas
                        verContenidoDir(entry.toString());
                    }
                   
               } 
            } catch(IOException ex){
                System.out.println("Error al acceder al directorio: " + ex.getMessage());
            }
            
        } else {
            System.out.println(rutaDirectorio + " no es un directorio válido");
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

