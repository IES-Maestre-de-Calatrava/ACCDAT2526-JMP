/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package vista;

import controlador.ControlConversor;

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
    
    // Obtener la ruta del directorio
    String getRuta();
    
    // Obtener el nombre del directorio
    String getNombre();
    
     // Constantes para las operaciones:
    static final String CREAR_ARBOLDOM = "1. Crea un arbolDOM a partir de un archivo binario de acceso aleatorio y genera un xml";
    static final String COVERTIR_A_HMTL = "2. Convierte un xml a html";

    
    @Override
    public String toString();

    @Override
    public boolean equals(Object obj);

    @Override
    public int hashCode();
    
    public long pedirId();
    
    public String pedirRespuestas();
    
    public String pedirRuta();
    
}
