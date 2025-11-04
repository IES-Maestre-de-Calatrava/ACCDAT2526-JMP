/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mavenproject1;

import controlador.ControlConversor;
import java.io.IOException;
import modelo.Modelo;
import vista.InterfazVista;
import vista.VentanaConversorTexto;

/**
 *
 * @author Javier Molina-Prados
 */
public class Mavenproject1 {

    public static void main(String[] args) throws IOException {
        
        /*
        Modelo modelo = new Modelo();
        modelo.altaVehiculo(1);
        
        modelo.cargarVehiculo(1);
        
        modelo.guardarReparacion(1);
        */
        
        
        InterfazVista vista = new VentanaConversorTexto();
        
        Modelo modelo = new Modelo();
        
        ControlConversor controlador = new ControlConversor(vista, modelo);
        
    }
}
