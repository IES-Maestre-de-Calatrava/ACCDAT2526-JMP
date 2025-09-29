/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.company.conver.controlador;

import com.company.conver.modelo.ConversorEurosPesetas;
import com.company.conver.vista.InterfazVista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Javier Molina-Prados
 * Created on 29 sept 2025
 */
public class ControlConversor implements ActionListener {
   
    private final InterfazVista vista;
    private final ConversorEurosPesetas modelo;
    
   /**
    * Iniciamos el controlador 
    * @param vista      vista de la aplicación
    * @param modelo     modelo de la aplicación
    */
    public ControlConversor(InterfazVista vista, ConversorEurosPesetas modelo) {
        this.vista = vista;
        this.modelo = modelo;
        
        //
        this.vista.setControlador(this);
        this.vista.arranca();
    
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        double cantidad = vista.getCantidad();
        switch(evento.getActionCommand()){
            case InterfazVista.AEUROS   -> vista.escribeCambio(cantidad + " pesetas son: " + modelo.pesetasAeuros(cantidad) + " euros");
            case InterfazVista.APESETAS -> vista.escribeCambio(cantidad + " euros son : " + modelo.eurosApesetas(cantidad) + " pesetas");
        }
    }
    
    
    
    

}
