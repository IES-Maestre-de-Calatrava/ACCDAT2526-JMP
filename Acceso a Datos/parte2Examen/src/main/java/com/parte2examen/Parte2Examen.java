/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.parte2examen;

import controlador.ControlConversor;
import modelo.Conversor;
import modelo.Examen;
import vista.InterfazVista;
import vista.VentanaConversorTexto;

/**
 *
 * @author Javier Molina-Prados
 */
public class Parte2Examen {

    public static void main(String[] args) {
        
        Examen examen = new Examen();
        Conversor conversor = new Conversor();
        
        InterfazVista vista = new VentanaConversorTexto();
        
        ControlConversor controlador = new ControlConversor(vista, conversor, examen);

    }
}
