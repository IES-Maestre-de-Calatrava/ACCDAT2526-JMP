/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.company.conver.vista;

import com.company.conver.controlador.ControlConversor;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Javier Molina-Prados
 * Created on 29 sept 2025
 */
public class VentanaConversorTexto implements InterfazVista {
    
    private ControlConversor controlador;
    private final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    
    
    @Override
    public void setControlador(ControlConversor c) {
        this.controlador =  c;
    }

    @Override
    public void arranca() {
        procesaNuevaOperacion();
    }

    @Override
    public double getCantidad() {
        System.out.println("Cantidad a convertir (formato 99.99): ");
        return leerCantidad();
    }

    @Override
    public void escribeCambio(String s) {
        System.out.println(s);
        System.out.println();
        
        procesaNuevaOperacion();
    }
    
    /**
     * Menú de operaciones
     */
    public void solicitaOperacion(){
        System.out.println("Indica que quiere hacer:");
        System.out.println("1. Euros a pesetas");
        System.out.println("2. Pesetas a euros");
        System.out.println("0. Salir");

    }
    
    
    /**
     * Método que lee la opcion elegida del menu
     * @return     el valor elegido o un mensaje de error
     */ 
    private int leerOpcion(){
        String s = null;
        try{
            s = in.readLine();
            return Integer.parseInt(s);
        }catch(IOException | NumberFormatException e){
            System.out.println("Operacion incorrecta:");
            return 0;
        }
             
    }
    
    
    /**
     * Metodo que procesa las operaciones con el controlador
     */
    private void procesaNuevaOperacion(){
        int operacion;
        solicitaOperacion();
        operacion = leerOpcion();
        
        switch (operacion) {
            case 0 -> {
                System.out.println("BYE");
                System.exit(0);
            }
            case 1 -> controlador.actionPerformed(new ActionEvent(this, operacion, InterfazVista.APESETAS));
            case 2 -> controlador.actionPerformed(new ActionEvent(this, operacion, InterfazVista.AEUROS));     
        }
    }
    
    /**
     * Devuelve el valor 
     * @return 
     */
    private double leerCantidad(){
         String s = null;
           try {
            s = in.readLine();
            return Integer.parseInt(s);
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error en formato del número, tiene que ser 99.99: ");
            return leerCantidad();
        }
         
     }
}
