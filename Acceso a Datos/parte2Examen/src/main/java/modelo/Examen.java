/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import modelo.Objeto;
import org.w3c.dom.Element;

/**
 *
 * @author Javier Molina-Prados
 */
public class Examen {
     
    private Objeto leerObjeto(RandomAccessFile raf) throws IOException {
        long id = raf.readLong(); 

        String dni = leerCampoTexto(raf, Objeto.getLONGITUD_DNI_EN_BYTES());
        String apellidos = leerCampoTexto(raf, Objeto.getLONGITUD_APELLIDOS_EN_BYTES());
        String nombre = leerCampoTexto(raf, Objeto.getLONGITUD_NOMBRE_EN_BYTES());
        String respuestas = leerCampoTexto(raf, Objeto.getLONGITUD_RESPUESTAS_EN_BYTES());
        int nota = raf.readInt();

        Objeto opositor = new Objeto();
        opositor.setId(id); 
        opositor.setDni(dni);
        opositor.setApellidos(apellidos);
        opositor.setNombre(nombre);
        opositor.setRespuestas(respuestas);
        opositor.setNota(nota);

        return opositor;
    }
    
    
     public void escribirTexto(RandomAccessFile raf, String cadena, int longitud) throws IOException{
        StringBuilder strbr = new StringBuilder(cadena);
        strbr.setLength(longitud);
        
        raf.writeChars(strbr.toString());
    }
    
    
    public String leerCampoTexto(RandomAccessFile raf, int longitud) throws IOException{
       byte[] buffer = new byte[longitud];
        raf.read(buffer);
        return new String(buffer, "UTF-16").trim();
    }
    
    
    /**
     * Lee todos los registros del archivo binario y los devuelve como una lista de Opositores.
     * @return Lista de objetos Objeto.
     * @throws IOException 
     */
    public List<Objeto> obtenerObjetosDelBinario() throws IOException {
        List<Objeto> objetos = new ArrayList<>();

        // Ruta del archivo binario
        String rutaArchivo = ".\\archivos_pruebas\\binario_acceso_aleatorio\\datos_finales.dat";

        try (RandomAccessFile raf = new RandomAccessFile(rutaArchivo, "r")) {

            while (raf.getFilePointer() < raf.length()) {
                // Reutiliza la lógica de lectura completa
                Objeto objeto = leerObjeto(raf); 
                objetos.add(objeto);
            }

        } catch (FileNotFoundException ex) {
            System.getLogger(Examen.class.getName()).log(System.Logger.Level.ERROR, "Archivo binario no encontrado.", ex);
        } catch (IOException ex) {
            System.getLogger(Examen.class.getName()).log(System.Logger.Level.ERROR, "Error de I/O al leer el archivo binario.", ex);
            throw ex; // Relanzar la excepción para que el main la maneje
        }
        return objetos;
    }
    
    /**
     * Lee todos los registros del archivo binario, construye un árbol DOM (XML) 
     * con esos datos y lo guarda en un archivo.
     */
    public void crearArbolDOM() {

        // Aquí inicializamos el objeto que contiene la lógica DOM,
        // asumiendo que está en el mismo paquete o importado.
        GestionContenidoDOM gestorDOM = new GestionContenidoDOM("Objetos");

        try {
            // 1. Obtener la lista de opositores del archivo binario
            List<Objeto> objetos = this.obtenerObjetosDelBinario();

            // 2. Iterar y construir el árbol DOM
            for (Objeto objeto : objetos) {
                
                if(objeto.getNota() >= 5){

                // a) Agregar el nodo padre <objeto>
                Element nodoObjeto = gestorDOM.addNodo("objeto"); 

                // b) Agregar los campos como nodos hijo de <opositor>
                // Usamos .trim() para limpiar los caracteres nulos del relleno de campo fijo

                gestorDOM.addNodoYTexto("id", String.valueOf(objeto.getId()), nodoObjeto); 
                gestorDOM.addNodoYTexto("dni", objeto.getDni().trim(), nodoObjeto); 
                gestorDOM.addNodoYTexto("apellidos", objeto.getApellidos().trim(), nodoObjeto); 
                gestorDOM.addNodoYTexto("nombre", objeto.getNombre().trim(), nodoObjeto);
                gestorDOM.addNodoYTexto("respuestas", objeto.getRespuestas().trim(), nodoObjeto);
                gestorDOM.addNodoYTexto("nota", String.valueOf(objeto.getNota()), nodoObjeto); 
                
                }
            }

            // 3. Generar el archivo XML con indentación
            String rutaSalida = ".\\archivos_pruebas\\xml_html\\datos_objetos.xml";
            gestorDOM.generarArchivodelDOM(rutaSalida, "yes");

            System.out.println("Árbol DOM creado y guardado en: " + rutaSalida);

            // (Opcional) Mostrar el XML por consola
            System.out.println("\n--- Contenido XML ---");
            gestorDOM.mostrarPorPantalla("yes");

        } catch (IOException e) {
            System.err.println("Error al procesar archivos binarios: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error inesperado al construir el DOM: " + e.getMessage());
        }
    }
    
    
    
    

}
