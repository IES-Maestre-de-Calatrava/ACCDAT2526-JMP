/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author Javier Molina-Prados
 * Created on 11 oct 2025
 */
public class Archivo {
     
    private String ruta;
    
    public Archivo(String ruta){
        this.ruta = ruta;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }
    
    public File getFileDeRuta(){
        File directorioRaiz = new File(ruta);
        return directorioRaiz;
    }
    
    public void crearArchivo(File nombreDirectorio, String nombreArchivo) throws IOException{
        File ficheroPrueba = new File(nombreDirectorio,nombreArchivo);
        ficheroPrueba.createNewFile(); // Creamos un archivo
        
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
        
        try(FileInputStream in = new FileInputStream(rutaOrigen);
            FileOutputStream out = new FileOutputStream(rutaDestino);){
            
            byte [] buffer= new byte[1024];
            int bytesLeidos;
            
            while ((bytesLeidos = in.read(buffer)) != -1){
                out.write(buffer, 0, bytesLeidos);
                
            }
            System.out.println("Archivo copiado de " + rutaOrigen + " a " + rutaDestino);

        } catch (IOException e) {
            System.out.println("Error al copiar el archivo: " + e.getMessage());
        }
     
        
    }
    
    
    public void moverArchivo(String rutaOrigen, String rutaDestino){
        
        copiarArchivo(rutaOrigen, rutaDestino);
        eliminarRutaCompleta(rutaOrigen);
        
    }

    
    public void eliminarRutaCompleta(String ruta){
        File f = new File(ruta);
        if (f.isDirectory()){
            File[] recorrer = f.listFiles();
            if (recorrer != null){
                for (File file : recorrer){
                    eliminarRutaCompleta(file.getPath());
                }
            }
            f.delete();
        } else if(f.isFile()){
            f.delete();
        } else {
            System.out.println("no existe una carpeta con esas caracteristicas para borrarla. ");
        }
    }
}
