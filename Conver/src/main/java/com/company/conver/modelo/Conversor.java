/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.company.conver.modelo;

/**
 *
 * @author Javier Molina-Prados
 * Created on 29 sept 2025
 */
public class Conversor {
    
    // Convierte la cantidad dada en euros a una moneda especifica
    
    private final double cambio;
    
    /**
     * Contructor de la clase conversor
     * @param cambio cantidad a la que equivale un euro
     */
    
    public Conversor(double valor) {
        this.cambio = valor;
    }
    
    /**
     * Convierte los euros a una moneda
     * @param cantidad
     * @return 
     */
    public double eurosAmoneda(double cantidad){
        return cantidad * cambio;
    }
    
    
    /**
     * Convierte la moneda a euros
     * @param cantidad
     * @return 
     */
    public double monedaAeuros(double cantidad){
        return cantidad / cambio;
    }
    
    

}
