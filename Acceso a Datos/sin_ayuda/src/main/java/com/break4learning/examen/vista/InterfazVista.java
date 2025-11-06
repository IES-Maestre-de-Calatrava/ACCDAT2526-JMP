/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.break4learning.examen.vista;

import com.break4learning.examen.controlador.Controlador;


/**
 * Interfaz con los métodos que deben implementar las vistas que se generen para la aplicación
 *
 * @author Break4Learning by Javier García-Retamero Redondo
 * @version 1.0
 * Created on 20 oct 2025
 */
public interface InterfazVista {

    /**
     * Especifica el controlador que se va a encargar de procesar las acciones realizadas en la vista.
     * 
     * @param c     Controlador que procesa las acciones
     */
    void setControlador(Controlador c);

    /** 
     * Comienza la visualización de la vista
     */
    void arranca();

    /**
     * Sale de la aplicación
     */
    void cierraVista();

     /** 
     *   Constantes para las operaciones:
     */
    static final String OPCION1 = "1. ALTA VEHÍCULO";
    static final String OPCION2 = "2. CARGAR VEHÍCULO";
    static final String OPCION3 = "3. REPARACIÓN";
    static final String OPCION4 = "4. AÑADIR A HISTÓRICO";
    static final String OPCION5 = "5. ACTUALIZAR PANEL";
    static final String SALIR = "0. Salir";   
    
    
    public long pedirId();
}
