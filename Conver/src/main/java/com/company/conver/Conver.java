/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.company.conver;

import com.company.conver.controlador.ControlConversor;
import com.company.conver.modelo.ConversorEurosPesetas;
import com.company.conver.vista.InterfazVista;
import com.company.conver.vista.VentanaConversor;
import com.company.conver.vista.VentanaConversorTexto;
//import com.mycompany.conver.vista.VentanaConversorTexto;

/**
 *
 * @author Javier Molina-Prados
 */
public class Conver {

    public static void main(String[] args) {
        
        /*
            Esto es la UI del usuario
        */
        // InterfazVista vista = new VentanaConversor();               // -> interfaz gráfica
        InterfazVista vista = new VentanaConversorTexto();        // -> interfac de texto
        
        /*
            Parte lógica del modelo
        */
        ConversorEurosPesetas modelo = new ConversorEurosPesetas();
        
        /*
            Parte del controlador, no olvidar que hay que pasarle la vista y el controlador
        */
        ControlConversor control = new ControlConversor(vista, modelo);
    }
}
