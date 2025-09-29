/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.company.conver.vista;

import com.company.conver.controlador.ControlConversor;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Javier Molina-Prados
 * Created on 29 sept 2025
 */
public class VentanaConversor extends JFrame implements InterfazVista {
    
    private final JButton convertirApesetas;
    private final JButton convertirAeuros;
    private final JTextField cantidad;
    private final JLabel resultado;
    
    /**
     * Genera la interfaz gráfica
     */
    public VentanaConversor(){
        super("Conversor de euros a pesetas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout(10,10));
              
        
        // Campo donde escribir la cantidad
        cantidad = new JTextField(8);
        JPanel panelauxiliar = new JPanel();
        panelauxiliar.add(cantidad);
        panelPrincipal.add(panelauxiliar, BorderLayout.NORTH);
        
        // Campo donde saldrá el resultado de la opercion
        resultado = new JLabel("Indique la cantidad y pulse uno de los botones");
        JPanel panelauxiliar2 = new JPanel();
        panelauxiliar2.add(resultado);
        panelPrincipal.add(panelauxiliar2, BorderLayout.CENTER);
        
        // Botones de accion
        convertirApesetas = new JButton("A pesetas");
        convertirApesetas.setActionCommand(APESETAS); // A los botones hay que setearles el action command
        
        convertirAeuros   = new JButton("A euros");
        convertirAeuros.setActionCommand(AEUROS);

        
        JPanel botonera = new JPanel();
        botonera.add(convertirApesetas);
        botonera.add(convertirAeuros);
        panelPrincipal.add(botonera, BorderLayout.SOUTH);
        getContentPane().add(panelPrincipal); // Devuelve el contenido del Panel   
    }

        
   
    @Override
    public void arranca() {
        pack();// coloca los componentes
        setLocationRelativeTo(null);// centra la ventana en la pantalla
        setVisible(true);// visualiza la ventana
    }

    @Override
    public double getCantidad() {
        try {
            return Double.parseDouble(cantidad.getText()); // pasamos el string que devuelve getText() a double
        }catch (NumberFormatException e){
            return 0.0D;
        }
    }

    @Override
    public void escribeCambio(String s) {
        resultado.setText(s);
    }
    
     @Override
    public void setControlador(ControlConversor c) {
        /* Le indicamos al botón la clase que se va a encargar de procesar las
           operaciones de los botones. Deberá implementar los métodos indicados en la
           interfaz. */
        
        convertirApesetas.addActionListener((ActionListener) c);
        convertirAeuros.addActionListener((ActionListener) c);
    }


}
