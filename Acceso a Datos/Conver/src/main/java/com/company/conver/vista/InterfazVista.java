/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.company.conver.vista;

import com.company.conver.controlador.ControlConversor;

/**
 *
 * @author Javier Molina-Prados
 */
public interface InterfazVista {
    
    /**
     * Especificamos le conversor que se va a encargar de realizar las acciones de la vista
     * @param c     Conversor que vamos a usar
     */
    void setControlador(ControlConversor c);
        
    /**
     * Comienza la visualización de la 
     */
    public void arranca();    
    
    /**
     * Obtiene por pantalla la cantidad a convertir
     * @return      cantidad introducida
     */
    double getCantidad();
    
    
    /**
     * Setea el valor con vertido
     * @param s     Valor que queremos escribir en la vista
     */
    void escribeCambio(String s);
        
    // CONSTANTES
    static final String AEUROS = "Pesetas a Euros";
    static final String APESETAS = "Euros a Pesetas";    
    
}
