/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.break4learning.examen.vista;

import com.break4learning.examen.controlador.Controlador;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Interfaz en modo texto de la aplicación
 *
 * @author Break4Learning by Javier García-Retamero Redondo
 * @version 1.0
 * Created on 20 oct 2025
 */
public class VistaTexto implements InterfazVista {

    private Controlador controlador;
    private final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public void setControlador(Controlador c) {
        this.controlador = c;
    }

    @Override
    public void arranca() {
        procesaNuevaOperacion();    
    }

    @Override
    public void cierraVista(){
        System.exit(0);
    }

    /**
     * Solicita una opción al usuario
     * 
     * @return  Devuelve un entero con la opción elegida por el usuario
     * @exception IOException           Si se produce un error en la entrada/salida
     * @exception NumberFormatException Si el formato del número no es correcto
     */
    private int leeOpcion() {
        String s = null;
        try {
            s = in.readLine();
            return Integer.parseInt(s);
        } catch (IOException | NumberFormatException e) {
            operacionIncorrecta();
            return 0;
        }
    }


    /**
     * Muestra el menú con las opciones
     */
    private void solicitaOperacion() {
        System.out.println("==============================");
        System.out.println(InterfazVista.SALIR);
        System.out.println("==============================");
        System.out.println(InterfazVista.OPCION1);
        System.out.println("--TALLER--");
        System.out.println(InterfazVista.OPCION2);
        System.out.println(InterfazVista.OPCION3);
        System.out.println("--HISTÓRICO REPARACIONES--");
        System.out.println(InterfazVista.OPCION4);
        System.out.println(InterfazVista.OPCION5);
        System.out.print("Indica la operación que quieres realizar:");
        
    }

    /**
     * Procesa la opción elegida por el usuario
     */
    private void procesaNuevaOperacion() {
        int operacion;
        solicitaOperacion();
        operacion = leeOpcion();
        switch (operacion) {
            case 0 -> {
                controlador.actionPerformed(new ActionEvent(this, operacion, InterfazVista.SALIR));
            }
            case 1 -> {
                controlador.actionPerformed(new ActionEvent(this, operacion, InterfazVista.OPCION1));
            }
            case 2 -> {
                controlador.actionPerformed(new ActionEvent(this, operacion, InterfazVista.OPCION2));
            }
            case 3 -> {
                controlador.actionPerformed(new ActionEvent(this, operacion, InterfazVista.OPCION3));
            }
            case 4 -> {
                controlador.actionPerformed(new ActionEvent(this, operacion, InterfazVista.OPCION4));
            }
            case 5 -> {
                controlador.actionPerformed(new ActionEvent(this, operacion, InterfazVista.OPCION5));
            }
        }
    }
    
    /**
     * Procesa el caso de que introduzcamos una opción que no sea una de las indicadas
     */
    private void operacionIncorrecta() {
        System.out.print("Operación incorrecta. ");
        procesaNuevaOperacion();
    }
    
    
    public long pedirId(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce el id del coche: ");
        long id = scanner.nextLong();
        return id;
    }
}
