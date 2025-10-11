/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package vista;

import Control.ControlConversor;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 *
 * @author Javier Molina-Prados
 * Created on 9 oct 2025
 */
public class VentanaConversorTexto implements InterfazVista {
    
    private ControlConversor controlador;
    private BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    
    
    
    public int leerOpcion(){
        String s = null;
        try{
            s = buffer.readLine();
            return Integer.parseInt(s); // El buffer es un string, hay que pasarlo a integer
        }catch(Exception e){
            operacionIncorrecta();
            return 0;
        }
    }
    
    public double leerCantidad(){
        String s = null;
        try{
            s = buffer.readLine();
            return Double.parseDouble(s); // El buffer es un string, hay que pasarlo a integer
        }catch(IOException | NumberFormatException e){
            System.out.println("Error en el formato de número, tiene que ser 99.99");
            return leerCantidad();
        }
    }
    
    public void escribirMenu(){
        System.out.println("Elige una opcion:");
        System.out.println("1. De Euros a Pesetas");
        System.out.println("2. De Pesetas a Euros");
        System.out.println("0 Salir");
    }
    
    
    private void realizarOperacion(){
        int operacion;
        escribirMenu();
        operacion = leerOpcion();
        
        switch(operacion){
            
            case 1 -> controlador.actionPerformed(new ActionEvent(this, operacion, APESETAS));
            case 2 -> controlador.actionPerformed(new ActionEvent(this, operacion, AEUROS));
            
            case 0 -> {
                System.out.println("Adios");
                System.exit(0); // con esto salimos
            }
        }
        
        operacionIncorrecta();
    }
    
    
    public void operacionIncorrecta(){
        System.out.println("Operacion Incorrecta");
        realizarOperacion();
    }
    
    
    @Override
    public void setControlador(ControlConversor c) {
        controlador = c;
        
    }

    @Override
    public void arranca() {
        realizarOperacion();
    }

    @Override
    public void escribeCambio(String s) {
        System.out.println(s);
        realizarOperacion();
    }

    @Override
    public double getCantidad() {
        System.out.println("Cantidad en formato (99.99)");
        return leerCantidad();
        
    }
    

}
