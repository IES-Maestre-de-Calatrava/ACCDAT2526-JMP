/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package controlador;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.Scanner;
import modelo.Archivo;
import modelo.Carpeta;
import vista.InterfazVista;

/**
 *
 * @author Javier Molina-Prados
 * Created on 11 oct 2025
 */
public class ControlConversor {
    
    private final InterfazVista vista;
    private final Carpeta modelo;
    private final Archivo modelo2;

    public ControlConversor(InterfazVista vista, Carpeta modelo, Archivo modelo2) {
        this.vista = vista;
        this.modelo = modelo;
        this.modelo2 = modelo2;
        this.vista.setControlador(this);
        this.vista.arranca();
    }
    
    public void ActionPerformed(ActionEvent e) throws IOException{
        String ruta = vista.getRuta();
        modelo.setRuta(ruta);
        modelo2.setRuta(ruta);
        
        switch(e.getActionCommand()){
            case InterfazVista.CREAR_CARPETA_EN_RUTA -> 
                modelo.crearCarpeta();
                
            case InterfazVista.CREAR_CARPETA_EN_RUTA_DADO_EL_NOMBRE -> {
                String nombre = vista.getNombre();
                modelo.crearCarpeta(nombre);
            }
            
            case InterfazVista.CREAR_CARPETA_DADO_DIRECTORIO_RAIZ ->{
                String nombre = vista.getNombre();
                modelo.crearCarpeta(modelo.getFileDeRuta(), nombre);
            }
            
            case InterfazVista.CREAR_ARCHIVO_EN_DIRECTORIO -> {
                String nombre = vista.getNombre();
                modelo2.crearArchivo(modelo2.getFileDeRuta(), nombre);
            }
            
            case InterfazVista.VER_CONTENIDO_DIRECTORIO-> {
                modelo.verContenidoDir(ruta);
            }
            
            case InterfazVista.BORRAR-> {
                modelo.eliminarRutaCompleta(ruta);
            }
            
            case InterfazVista.CAMBIAR_NOMBRE_ARCHIVO-> {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Escribe el nombre del archivo actual: ");
                String nombreActual = scanner.nextLine();
                System.out.println("Escribe el nombre del nuevo archivo: ");
                String nombreNuevo = scanner.nextLine();

                modelo2.cambiarNombre(ruta, nombreActual, nombreNuevo);
            }
            
            case InterfazVista.COPIAR_ARCHIVO-> {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Direccion de la ruta a la que quieres copiar el archivo: ");
                String rutaNueva = scanner.nextLine();

                modelo2.copiarArchivo(ruta, rutaNueva);
            }
            
            case InterfazVista.MOVER_ARCHIVO-> {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Direccion de la ruta a la que quieres mover el archivo: ");
                String rutaNueva = scanner.nextLine();

                modelo2.moverArchivo(ruta, rutaNueva);
            }
            
            default -> {    
            }
        }
        vista.arranca();
    }
    
    

}
