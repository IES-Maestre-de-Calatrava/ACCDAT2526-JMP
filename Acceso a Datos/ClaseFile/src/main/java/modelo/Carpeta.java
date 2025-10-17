/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package modelo;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 *
 * @author Javier Molina-Prados
 */
public class Carpeta {
    
    private String ruta;
    
    public Carpeta(String ruta){
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
    
    public void crearCarpeta() throws IOException{
    // Le indicamos la ruta completa al directorio o archivo
    File directorioNuevo = new File(ruta);
    directorioNuevo.mkdir(); // Creamos el directorio
    
    /*
    File ficheroPrueba = new File(directorioNuevo+"\\ejemploprueba.txt");
    ficheroPrueba.createNewFile(); // Creamos un archivo
    */
    }
    
    public void crearCarpeta(String nombreDirectorio) throws IOException{
    // Le indicamos la carpeta contenedora y el archivo en un segundo parámetro
    File directorioNuevo = new File(ruta,nombreDirectorio);
    directorioNuevo.mkdir(); // Creamos el directorio
    
    /*
    File ficheroPrueba2 = new File(directorioNuevo,"ejemploprueba2.txt");
    ficheroPrueba2.createNewFile(); // Crea el archivo
    */
    }
    
    public void crearCarpeta(File directorioRaiz, String nombreDirectorio) throws IOException{
    // Le indicamos un File con la carpeta contenedora y el archivo en un segundo parámetro
    File directorioNuevo = new File(directorioRaiz,nombreDirectorio);
    directorioNuevo.mkdir();
    
    /*
    File ficheroPrueba3 = new File(directorioNuevo,"ejemploprueba3.txt");
    ficheroPrueba3.createNewFile();
    */
    }
    
    public void verContenidoDir(String rutaDirectorio){
        File f = new File(rutaDirectorio);
        
        if(f.isDirectory()){
            File[] archivos = f.listFiles();
            
            if(archivos != null){
                System.out.println("Contenido del directorio: "+f.getAbsolutePath());
                for (File file : archivos){
                    if (file.isFile()){
                        System.out.println(" [ARCHIVO] "+file.getName());
                        System.out.println("    Ruta: " + file.getAbsolutePath());
                        System.out.println("    Siza: " + file.length() + " bytes");
                        System.out.println("    Ultima modificacion: " + new Date(file.lastModified()));
                        System.out.println("    Permisos -> Leer: " + file.canRead() + 
                                       " | Escribir: " + file.canWrite() + 
                                       " | Ejecutar: " + file.canExecute());
                    }
                    else if (file.isDirectory()){
                        System.out.println(" [DIRECTORIO] "+file.getName());
                        // Llamada recursiva para ver si hay cosas en los subdirectorios
                        verContenidoDir(file.getAbsolutePath());
                    }
                }
            }else{
                System.out.println("No se pudo acceder al contenido del directorio:");
            }
        }else{
            System.out.println(rutaDirectorio + " no es un directorio válido.");
        }

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

