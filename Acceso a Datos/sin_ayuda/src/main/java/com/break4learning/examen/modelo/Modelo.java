/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.break4learning.examen.modelo;

import com.break4learning.examen.modelo.Objetos.Vehiculo;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Element;

/**
 *
 * @author Break4Learning by Javier García-Retamero Redondo
 * @version 1.0
 * Created on 20 oct 2025
 */



public class Modelo {
    
    private static final String CARPETA_PRINCIPAL = "./pruebas";   
    private static final String ARCHIVO_DAT =  CARPETA_PRINCIPAL + "/acceso_aleatorio/vehiculos.dat";
    
    
    public Vehiculo leerCoche(long id) throws IOException {
    // Nota: El método readLong() moverá el puntero.
        RandomAccessFile raf = new RandomAccessFile(ARCHIVO_DAT, "rw");
        long posicion = (id-1)*Vehiculo.getLONGITUD_TOTAL();
        raf.seek(posicion);
        
        long identificador = raf.readLong(); 

        // Los métodos leerCampoTexto y las constantes ya deben estar definidos
        String matricula = leerCampoTexto(raf, Vehiculo.getLONGITUD_MATRICULA_EN_BYTES());
        int telefono = raf.readInt();
        String nombreContacto = leerCampoTexto(raf, Vehiculo.getLONGITUD_NOMBRECONTACTO_EN_BYTES());
        String diagnostico = leerCampoTexto(raf, Vehiculo.getLONGITUD_DIAGNOSTICO_EN_BYTES());
        String reparacion = leerCampoTexto(raf, Vehiculo.getLONGITUD_REPARACION_EN_BYTES());
        long coste = raf.readLong();
        String fechaReparacion = leerCampoTexto(raf, Vehiculo.getLONGITUD_FECHA_REPARACION());

        // Creamos y llenamos el objeto
        Vehiculo coche = new Vehiculo();
        coche.setId(identificador); 
        coche.setMatricula(matricula);
        coche.setTelefono(telefono);
        coche.setNombreContacto(nombreContacto);
        coche.setDiagnostico(diagnostico);
        coche.setReparacion(reparacion);
        coche.setCoste(coste);
        coche.setFecha_reparacion(fechaReparacion);
        
        if (coche.getCoste() != 0){
            generarPartedeTrabajo(coche);
        }

        return coche;
    }
           
    
    public String leerCampoTexto(RandomAccessFile raf, int longitud) throws IOException{
       byte[] buffer = new byte[longitud];
        raf.read(buffer);
        return new String(buffer, "UTF-16").trim();
    }
     
     
    public void generarPartedeTrabajo(Vehiculo coche) throws FileNotFoundException, IOException{
                                                                                   
                    File fichero = new File("./pruebas/archivos_txt/"+coche.getMatricula()+"/"+coche.getMatricula()+".txt");
                        
                    if(!fichero.exists()){
                        BufferedWriter bw = new BufferedWriter(new FileWriter(fichero));

                        String matricula = coche.getMatricula();
                        int telefono = coche.getTelefono();
                        String nombreContacto = coche.getNombreContacto();
                        String diagnostico = coche.getDiagnostico();
                        String reparacion = coche.getReparacion();
                        long coste = coche.getCoste();
                        String fechaReparacion = coche.getFecha_reparacion();
                        

                        bw.write("Matricula: "+matricula+"\n, Telefono: "+telefono+"\n, Nombre de contacto: "+nombreContacto+"\n, Fecha de la reparacion: "+fechaReparacion+"\n Diagnostico: "+diagnostico+"\n, Reparacion: "+reparacion+"\n, Coste: "+coste);
                        bw.newLine(); // o bw.write("\n")
                    
                    
                    }else{
                        
                        BufferedWriter bw = new BufferedWriter(new FileWriter(fichero, true));
                        
                        String diagnostico = coche.getDiagnostico();
                        String reparacion = coche.getReparacion();
                        long coste = coche.getCoste();
                        String fechaReparacion = coche.getFecha_reparacion();
                        
                        bw.write(" Fecha de la reparacion: "+fechaReparacion+"\n Diagnostico: "+diagnostico+"\n, Reparacion: "+reparacion+"\n, Coste: "+coste);
                        bw.newLine(); // o bw.write("\n")
                        
                    }  
                    
                    
    }
    
    
    
    
    // =====================================================================================================
    
    
    /**
     * Lee todos los registros del archivo binario y los devuelve como una lista de Opositores.
     * @return Lista de objetos Objeto.
     * @throws IOException 
     */
    public List<Vehiculo> obtenerVehiculosDelBinario() throws IOException {
        List<Vehiculo> coches = new ArrayList<>();

        // Ruta del archivo binario
        String rutaArchivo = ARCHIVO_DAT;

        try (RandomAccessFile raf = new RandomAccessFile(rutaArchivo, "r")) {

            while (raf.getFilePointer() < raf.length()) {
                Vehiculo coche = leerCochesTodos(raf); 
                coches.add(coche);
            }

        } catch (FileNotFoundException ex) {
            System.getLogger(Modelo.class.getName()).log(System.Logger.Level.ERROR, "Archivo binario no encontrado.", ex);
        } catch (IOException ex) {
            System.getLogger(Modelo.class.getName()).log(System.Logger.Level.ERROR, "Error de I/O al leer el archivo binario.", ex);
            throw ex; 
        }
        return coches;
    }
    
    
    private Vehiculo leerCochesTodos(RandomAccessFile raf) throws IOException {
                
       long identificador = raf.readLong(); 

        String matricula = leerCampoTexto(raf, Vehiculo.getLONGITUD_MATRICULA_EN_BYTES());
        int telefono = raf.readInt();
        String nombreContacto = leerCampoTexto(raf, Vehiculo.getLONGITUD_NOMBRECONTACTO_EN_BYTES());
        String diagnostico = leerCampoTexto(raf, Vehiculo.getLONGITUD_DIAGNOSTICO_EN_BYTES());
        String reparacion = leerCampoTexto(raf, Vehiculo.getLONGITUD_REPARACION_EN_BYTES());
        long coste = raf.readLong();
        String fechaReparacion = leerCampoTexto(raf, Vehiculo.getLONGITUD_FECHA_REPARACION());

       // Creamos y llenamos el objeto
        Vehiculo coche = new Vehiculo();
        coche.setId(identificador); 
        coche.setMatricula(matricula);
        coche.setTelefono(telefono);
        coche.setNombreContacto(nombreContacto);
        coche.setDiagnostico(diagnostico);
        coche.setReparacion(reparacion);
        coche.setCoste(coste);
        coche.setFecha_reparacion(fechaReparacion);

        return coche;
    }
    
    
    /**
     * Lee todos los registros del archivo binario, construye un árbol DOM (XML) 
     * con esos datos y lo guarda en un archivo.
     */
    public void crearArbolDOM() {

        // Aquí inicializamos el objeto que contiene la lógica DOM,
        // asumiendo que está en el mismo paquete o importado.
        GestionContenidoDOM gestorDOM = new GestionContenidoDOM("vehiculos");

        try {
            List<Vehiculo> coches = this.obtenerVehiculosDelBinario();

            for (Vehiculo coche : coches) {
                
                if(coche.getCoste() != 0){

                Element nodoObjeto = gestorDOM.addNodo("vehiculo"); 

                // Usamos .trim() para limpiar los caracteres nulos del relleno de campo fijo

                gestorDOM.addNodoYTexto("matricula", coche.getMatricula().trim(), nodoObjeto); 
                gestorDOM.addNodoYTexto("mensaje", "Ya pueden pasar a recogerlo", nodoObjeto); 
                
                }
            }

            // 3. Generar el archivo XML con indentación
            String rutaSalida = ".\\pruebas\\archivos_web\\panel.xml";
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

