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
public class ConversorEurosPesetas extends Conversor{
    
    /**
     * Constructor de la clase, establecemos equivalencia entre 1 euro y peseta
     */
    public ConversorEurosPesetas() {
        super(166.386D);
    }
    
    /**
     * Convierte los euros a pesetas usando el metodo de la clase padre
     * @param valor     Cantidad de euros que queremos pasar a pesetas
     * @return          Pesetas tras convertirlo
     */
    public double eurosApesetas(double valor){
        return eurosAmoneda(valor);
    }
    
    
    /**
     * Convierte las pesetas a euros usando el metodo de la clase padre
     * @param valor     Cantidad de pesetas que queremos pasar a euros
     * @return          Euros tras convertirlo
     */
    public double pesetasAeuros(double valor){
        return monedaAeuros(valor);
    }
    
    
    
    

}
