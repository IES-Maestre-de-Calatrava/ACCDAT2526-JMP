/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package vista;

import controlador.Controlador;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 *
 * @author Javier Molina-Prados
 * Created on 11 oct 2025
 */
public class VentanaConversorTexto implements InterfazVista {
    
    private Controlador controlador;
    private BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    
    
    @Override
    public void setControlador(Controlador c) {
        controlador = c;
    }

    @Override
    public void arranca() {
        try {
            realizarOperacion();
        } catch (IOException ex) {
            System.getLogger(VentanaConversorTexto.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    
    @Override
    public String getRuta() {
        System.out.println("Direccion de la ruta: ");
        return leerRuta(); 
    }

    @Override
    public String getNombre() {
        System.out.println("Nombre de la carpeta:");
        return leerRuta();     
    }
    
    
    public void escribirMenu(){
        System.out.println("Elige una opcion:");
        System.out.println("1. Leer fichero caracter a caracter");
        System.out.println("2. Leer fichero por bloque de caracteres");
        System.out.println("3. Leer fichero linea a linea");
        System.out.println("4. Escribir un caracter en el fichero");
        System.out.println("5. Escribir un bloque de caracteres por medio de String sin salto de linea");
        System.out.println("6. Escribir una linea en el fichero con salto de linea a la siguiente");
        System.out.println("7. Copiar el fichero en otro");
        System.out.println("8. Copiar el fichero en otro encriptado");
        System.out.println("9. Desencriptar el fichero en otro nuevo en caso de que se hubiese encriptado previamente");
        System.out.println("0. Salir");
    }
    
    public int leerOpcion(){
        String s = null;
        try{
            s = buffer.readLine();
            return Integer.parseInt(s);
        }catch(Exception e){
            System.out.println("Opcion incorrecta");
            return 0;
        }
    }
    
    
    
    public String leerRuta(){
        String s = null;
        try{
            s = buffer.readLine();
            return s;
        }catch(IOException e){
            System.out.println("Error en la cadena introducida");
            return leerRuta();
        }
    }
    
    
    public void realizarOperacion() throws IOException{
        int operacion;
        escribirMenu();
        operacion = leerOpcion();
        
        switch(operacion){
            case 1 -> controlador.ActionPerformed(new ActionEvent(this, operacion, LEER_CARACTER_A_CARACTER));
            case 2 -> controlador.ActionPerformed(new ActionEvent(this, operacion, LEER_BLOQUE_DE_CARACTERES));
            case 3 -> controlador.ActionPerformed(new ActionEvent(this, operacion, LEER_POR_LINEAS));
            case 4 -> controlador.ActionPerformed(new ActionEvent(this, operacion, ESCRIBIR_CARACTER));
            case 5 -> controlador.ActionPerformed(new ActionEvent(this, operacion, ESRIBIR_BLOQUE_DE_CARACTERES));
            case 6 -> controlador.ActionPerformed(new ActionEvent(this, operacion, ESCRIBIR_LINEA));
            case 7 -> controlador.ActionPerformed(new ActionEvent(this, operacion, COPIAR_ARCHIVO));
            case 8 -> controlador.ActionPerformed(new ActionEvent(this, operacion, COPIAR_ARCHIVO_ENCRIPTADO));
            case 9 -> controlador.ActionPerformed(new ActionEvent(this, operacion, DESENCRIPTAR_ARCHIVO));

            
            case 0 -> {
                System.out.println("Adios");
                System.exit(0);
            }
        }
    }
    
    
    public void operacionIncorrecta() throws IOException{
        System.out.println("Operacion Incorrecta");
        realizarOperacion();
    }
    
    

}
