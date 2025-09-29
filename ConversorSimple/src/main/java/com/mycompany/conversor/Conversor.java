/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.conversor;
import java.util.Scanner;
/**
 *
 * @author Javier Molina-Prados 
 * Created on 9 sept 2025
 */
public class Conversor {
    
    /**
    * Método que convierte una cantidad entre euros y pesetas, según el tipo indicado por el usuario.
    *
    * @param cantidad                   La cantidad a convertir.
    * @return res                       El resultado de la conversión.
    * @throws IllegalArgumentException  Si el tipo de moneda introducido no es válido.
    */
    public static double conversor (double cantidad){
        // Cuerpo del método
        double res = cantidad;
        Scanner scanner = new Scanner(System.in);

        System.out.println("A que quiere convertir: euro, pesetas");
        String moneda = scanner.nextLine();
        
        if (moneda.equals("euro")){
            res = res/166.286;
        } else if (moneda.equals("pesetas")){
            res = res*166.386;
        } else {
            throw new IllegalArgumentException("ERROR: tipo de dato invalido");
        }
        
        return res;
    }
    
    public static void main (String[]args){
        double cantidad = 45;
        System.out.println(conversor(cantidad));
    }
}

