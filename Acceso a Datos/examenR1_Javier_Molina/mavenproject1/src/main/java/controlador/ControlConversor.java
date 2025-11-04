/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package controlador;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.Scanner;
import javax.xml.transform.TransformerException;
import modelo.Modelo;
import vista.InterfazVista;

/**
 *
 * @author Javier Molina-Prados
 * Created on 11 oct 2025
 */
public class ControlConversor {
    
    private final InterfazVista vista;
    private final Modelo modelo;

    public ControlConversor(InterfazVista vista, Modelo modelo) {
        this.vista = vista;
        this.modelo = modelo;

        this.vista.setControlador(this);
        this.vista.arranca();
    }
    
    public void ActionPerformed(ActionEvent e) throws IOException, TransformerException{
        
        switch(e.getActionCommand()){
            case InterfazVista.ALTA_VEHICULO -> {
                long id = vista.pedirId();
                modelo.altaVehiculo(id);
                
            }
            case InterfazVista.CARGAR_VEHICULO -> {
                long id = vista.pedirId();
                modelo.cargarVehiculo(id);
            }
            
            case InterfazVista.REPARACION ->{
                long id = vista.pedirId();
                modelo.guardarReparacion(0);

            }
            
            default -> {    
            }
        }
        vista.arranca();
    }
    
    

}
