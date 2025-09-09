/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.conversor;
import java.util.Scanner;
/**
 *
 * @author Gregorio 
 * Created on 9 sept 2025
 */
public class Conversor {
    
    public static double conversor (double cantidad){
        Scanner scanner = new Scanner(System.in);

        System.out.println("A que quiere convertir: 1 = euro,   2 = pesetas");
        String moneda = scanner.nextLine();
        
        /*while moneda != "euro" or moneda != "pesetas" {
            System.out.println("A que quiere convertir: 1 = euro,   2 = pesetas");
            String moneda = scanner.nextLine();
        }*/
        
        if (moneda == "1"){
            cantidad = cantidad/166.386;
        }
        
        if (moneda == "2"){
            cantidad = cantidad*166.386;
        }
        return cantidad;
    }
    
    public static void main (String[]args){
        double cantidad = 45;
        System.out.println(conversor(cantidad));
    }
}

