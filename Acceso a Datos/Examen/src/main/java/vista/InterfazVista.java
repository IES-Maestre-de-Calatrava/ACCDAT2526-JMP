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
    static final String CORRECCION_EXAMENES = "1. Corregir y crear los archivos de los examenes corregidos en un archivo binario y txt";
    static final String RECLAMACION = "2. Corregir los datos de un opositor que ha hecho una reclamacion";
    static final String CREAR_ARBOLDOM = "3. Crea un arbolDOM de opositores con nota >= 5 y genera un xml";
    static final String COVERTIR_A_HMTL = "4. Convierte un xml a html";
    static final String COPIA_SEGURIDAD = "5. Crea una copia de seguridad de todos los archivos";

    
    @Override
    public String toString();

    @Override
    public boolean equals(Object obj);

    @Override
    public int hashCode();
    
    public long pedirId();
    
    public String pedirRespuestas();
    
}
