/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.break4learning.examen;

import com.break4learning.examen.controlador.Controlador;
import com.break4learning.examen.modelo.Modelo;
import com.break4learning.examen.vista.InterfazVista;
import com.break4learning.examen.vista.VistaTexto;

/**
 *
 * @author Break4Learning by Javier García-Retamero Redondo
 * @version 1.0
 * Created on 20 oct 2025
 */
public class Examen {
          
    public static void main(String[] args) {
        
        /* 
            La vista
         */
        InterfazVista vista = new VistaTexto();  //-- Modo texto

        /* 
            El modelo
         */
        Modelo modelo = new Modelo();
        
        /*
            Creamos el controlador pasándole la vista y el modelo
         */
        Controlador control = new Controlador(vista, modelo);
        
                                                      
    }
}
