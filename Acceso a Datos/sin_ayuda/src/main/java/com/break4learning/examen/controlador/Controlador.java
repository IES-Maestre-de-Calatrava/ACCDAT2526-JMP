/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.break4learning.examen.controlador;

import com.break4learning.examen.modelo.Modelo;
import com.break4learning.examen.vista.InterfazVista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Encargada de comunicar la vista con el modelo
 * Como esta clase está interesada en procesar un evento de acción entonces debe
 * implementar la interfaz ActionListener
 *
 * @author Break4Learning by Javier García-Retamero Redondo
 * @version 1.0
 * Created on 20 oct 2025
 */
public class Controlador implements ActionListener {
    private final InterfazVista vista;
    private final Modelo modelo;
          
    /**
     * Inicia desde el controlador el proceso
     * 
     * @param vista     Vista de la aplicación
     * @param modelo    Modelo de la aplicación 
     */
    public Controlador(InterfazVista vista, Modelo modelo) {
        this.vista = vista;
        this.modelo = modelo;
    
        // Configuramos los listener de la vista indicándole que los métodos a ejecutar están en el controlador 
        this.vista.setControlador(this);
       
        // Arrancamos la interfaz gráfica (vista):
        this.vista.arranca();
    }  
    
    /**
     * Este método se ejecuta automáticamente cuando se produce el evento que provoca la acción.
     * 
     * @param evento    Evento que ha provocado que el 
     */
    @Override
    public void actionPerformed(ActionEvent evento) {
        
        switch (evento.getActionCommand()) {
 
            case InterfazVista.SALIR -> {
                vista.cierraVista();
            }
            
            case InterfazVista.OPCION4 ->{
                long id = vista.pedirId();
            try {
                modelo.leerCoche(id);
            } catch (IOException ex) {
                System.getLogger(Controlador.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
            }
            
            case InterfazVista.OPCION5 -> {
                modelo.crearArbolDOM();
            }
            
            default -> {

            }
        }
        vista.arranca();
    }
}
