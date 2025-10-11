/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.ConversorPesetas;
import vista.InterfazVista;

/**
 *
 * @author Javier Molina-Prados
 * Created on 9 oct 2025
 */
public class ControlConversor implements ActionListener {
    
    private final InterfazVista vista;
    private final ConversorPesetas modelo;

    public ControlConversor(InterfazVista vista, ConversorPesetas modelo) {
        this.vista = vista;
        this.modelo = modelo;
        this.vista.setControlador(this); // Se le asigna el controlador a la vista
        this.vista.arranca();            // Se arranca el control
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        double cantidad = vista.getCantidad();
        // recogemos la cantidad pasada EN VISTA y en el switch hacemos cosas dependiendo del getActionCommand que saquemos del ActionEvent e
        switch(e.getActionCommand()){
            
            case InterfazVista.AEUROS -> vista.escribeCambio(cantidad+" pesetas son: "+ modelo.pesetasAEuros(cantidad)+" euros tras restarle la siguiente comision: "+modelo.getComision());
            // primero llamamos al metodo de la vista que escriba el cambio y luego el modelo cancula la cantidad con su metodo
            case InterfazVista.APESETAS -> vista.escribeCambio(cantidad+" euros son: "+modelo.eurosAPesetas(cantidad)+" pesetas tras restarle la siguiente comision: "+modelo.getComision());
            
            default-> vista.escribeCambio("ERROR");
        }
    }
    
    

    
    
    
    
    
    
    
    
}
