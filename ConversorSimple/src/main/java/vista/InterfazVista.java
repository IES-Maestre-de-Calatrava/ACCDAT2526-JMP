/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package vista;

import Control.ControlConversor;

/**
 *
 * @author Javier Molina-Prados
 */
public interface InterfazVista {
    
    // especifica el controlador que se va a encargar de procesar
    //las acciones realizadas en la vista
    void setControlador (ControlConversor c);
    
    // comienza la visualización
    void arranca();
    
    //setea el valor convertido
    void escribeCambio(String s);
    
    // obtiene de pantalla la cantidad a convertir
    double getCantidad();
    
    // Constantes para las operaciones:
    static final String AEUROS = "Pesetas a Euros";
    static final String APESETAS = "Euros a Pesetas";

    @Override
    public String toString();

    @Override
    public boolean equals(Object obj);

    @Override
    public int hashCode();
    
}
