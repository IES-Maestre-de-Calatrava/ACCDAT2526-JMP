/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package controlador;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import modelo.Empleado;
import modelo.LecturaEscritura;
import vista.InterfazVista;

/**
 *
 * @author Javier Molina-Prados
 * Created on 25 oct 2025
 */
public class Controlador {
    
    private final InterfazVista    vista;
    private final LecturaEscritura lecturaEscritura;
    

    public Controlador(InterfazVista vista, LecturaEscritura lecturaEscritura){
        
        this.vista = vista;
        this.lecturaEscritura = lecturaEscritura;
        this.vista.setControlador(this);
        this.vista.arranca();
    }
    
    public void ActionPerformed(ActionEvent e) throws IOException{
        String ruta = vista.getRuta();

        LecturaEscritura lecturaEscritura = new LecturaEscritura(ruta);

        switch(e.getActionCommand()){
            
            /* LECTURA ESCRITURA */
            case InterfazVista.ESCRIBE_EMPLEADO_AL_FINAL -> {
                Empleado empleado = vista.pedirEmpleado();
                lecturaEscritura.escribirEmpleadoFinal(empleado);
                
            }
                
            case InterfazVista.ESCRIBE_EMPLEADO_POR_ID -> {
                Empleado empleado = vista.pedirEmpleado();
                lecturaEscritura.escribirEmpleadoPorId(empleado);
            }
            
            case InterfazVista.LEE_EMPLEADO_POR_ID ->{
                long identificador = vista.pedirIdentificador();
                Empleado emple = lecturaEscritura.lecturaEmpleado(identificador);
                vista.mostrarEmpleado(emple);
            }
            
            case InterfazVista.BORRAR -> {
                long identificador = vista.pedirIdentificador();
                lecturaEscritura.borrarEmpleado(identificador);
                System.out.println("Empleado borrado correctamente");
            }
                   
            case InterfazVista.LEER_FICHERO-> {
                vista.mostrarMensaje(lecturaEscritura.leerTodoEmpleado());
            }
            
          
            case InterfazVista.CAMBIAR_NOMBRE-> {
                long identificador = vista.pedirIdentificador();
                String apellido = vista.pedirApellido();
                lecturaEscritura.cambiarNombreEmpleado(apellido, identificador);
                System.out.println("Nombre cambiado correctamente");

            }
            
            default -> {    
            }
        }
        vista.arranca();
    }
    
    

}

