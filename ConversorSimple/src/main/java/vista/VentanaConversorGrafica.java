/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package vista;

import Control.ControlConversor;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Javier Molina-Prados
 * Created on 9 oct 2025
 */
public class VentanaConversorGrafica extends JFrame implements InterfazVista {
    private JButton convertirApesetas;
    private JButton convertirAeuros;
    private JTextField cantidad;
    private JLabel resultado;
    
    
    public VentanaConversorGrafica () {
        super("Conversor de Euros y Pesetas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout(10,10));
        cantidad = new JTextField(8);
        JPanel panelaux = new JPanel();
        panelaux.add(cantidad);
        panelPrincipal.add(panelaux, BorderLayout.NORTH);
        resultado = new JLabel("Indique una cantidad y pulse uno de los botones");
        JPanel panelaux2 = new JPanel();
        panelaux2.add(resultado);
        panelPrincipal.add(panelaux2, BorderLayout.CENTER);
        convertirApesetas = new JButton("A pesetas");
        convertirApesetas.setActionCommand(APESETAS);
        convertirAeuros = new JButton("A euros");
        convertirAeuros.setActionCommand(AEUROS);
        JPanel botonera = new JPanel();
        botonera.add(convertirApesetas);
        botonera.add(convertirAeuros);
        panelPrincipal.add(botonera, BorderLayout.SOUTH);
        getContentPane().add(panelPrincipal);
    }

    @Override
    public void setControlador(ControlConversor c) {
        convertirApesetas.addActionListener(c);
        convertirAeuros.addActionListener(c);
        
    }

    @Override
    public void arranca() {
        pack();// coloca los componentes
        setLocationRelativeTo(null);// centra la ventana en la pantalla
        setVisible(true);// visualiza la ventana    
    }

    
    @Override
    public void escribeCambio(String s) {
        resultado.setText(s);    
    }

    
    @Override
    public double getCantidad() {
        try {
        return Double.parseDouble(cantidad.getText());
        } catch (NumberFormatException e) {
        return 0.0D;
        }    
    }
    
}