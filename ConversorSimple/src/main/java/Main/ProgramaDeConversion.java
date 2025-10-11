/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Main;

import Control.ControlConversor;
import modelo.ConversorPesetas;
import vista.InterfazVista;
import vista.VentanaConversorGrafica;
import vista.VentanaConversorTexto;

/**
 *
 * @author Javier Molina-Prados
 * Created on 9 oct 2025
 */
public class ProgramaDeConversion {
    
    public static void main(String [] args){
        
        // La vista
        InterfazVista vista = new VentanaConversorGrafica();
        InterfazVista vistaTexto = new VentanaConversorTexto();
        
        // El modelo
        ConversorPesetas modelo = new ConversorPesetas();
        
        // El control: le pasamos la vista y el modelo
        ControlConversor control = new ControlConversor(vistaTexto, modelo);
    }

}
