/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package controlador;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.Scanner;
import modelo.LecturaEscrituraCaracteres;
import vista.InterfazVista;

/**
 *
 * @author Javier Molina-Prados
 * Created on 25 oct 2025
 */
public class Controlador {
    
    private final InterfazVista vista;
    private final LecturaEscrituraCaracteres modelo;

    public Controlador(InterfazVista vista, LecturaEscrituraCaracteres modelo) {
        this.vista = vista;
        this.modelo = modelo;
        this.vista.setControlador(this);
        this.vista.arranca();
    }
    
    public void ActionPerformed(ActionEvent e) throws IOException{
        String ruta = vista.getRuta();
        modelo.setFicheroPorRuta(ruta);
        
        switch(e.getActionCommand()){
            case InterfazVista.LEER_CARACTER_A_CARACTER -> 
                modelo.leerFichero();
                
            case InterfazVista.LEER_BLOQUE_DE_CARACTERES -> {
                modelo.leerFichero2();
            }
            
            case InterfazVista.LEER_POR_LINEAS ->{
                modelo.leerFichero3();
            }
            
            case InterfazVista.ESCRIBIR_CARACTER -> {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Introduce un caracter para escribir:");
                char caracter = scanner.next().charAt(0);
                modelo.escribirFichero(caracter);
            }
            
            case InterfazVista.ESRIBIR_BLOQUE_DE_CARACTERES-> {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Introduce un bloque de caracteres para escribir (String):");
                String bloque = scanner.nextLine();
                modelo.escribirFichero(bloque);
            }
            
            case InterfazVista.ESCRIBIR_LINEA-> {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Introduce una linea:");
                String linea = scanner.nextLine();
                modelo.escribirFicheroLinea(linea);
            }
            
            case InterfazVista.COPIAR_ARCHIVO-> {
                modelo.copia();
            }
            
            case InterfazVista.COPIAR_ARCHIVO_ENCRIPTADO-> {
                 modelo.copiaEncriptada();
            }
            
            case InterfazVista.DESENCRIPTAR_ARCHIVO-> {
                modelo.desencriptarCopia();
            }
            
            default -> {    
            }
        }
        vista.arranca();
    }
    
    

}

