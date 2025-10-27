/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package vista;

import controlador.Controlador;
import modelo.Empleado;

/**
 *
 * @author Javier Molina-Prados
 */
public interface InterfazVista {
    
    // especifica el controlador que se va a encargar de procesar
    //las acciones realizadas en la vista
    void setControlador (Controlador c);
    
    // comienza la visualización
    void arranca();
    
    // Obtener la ruta del directorio
    String getRuta();
    
    // Obtener el nombre del directorio
    String getNombre();
    
    // Constantes para las operaciones:
    // LecturaEscritura
    static final String ESCRIBE_EMPLEADO_AL_FINAL = "Escribe un empleado al final del archivo";
    static final String ESCRIBE_EMPLEADO_POR_ID = "Escribe un empleado en el fichero según el identificador";
    static final String LEE_EMPLEADO_POR_ID = "Lee un empleado en el fichero según el identificador";
    static final String BORRAR = "Borra un empleado según el identificador";
    static final String LEER_FICHERO = "Lee todos los registros del fichero";
    static final String CAMBIAR_NOMBRE = "Modifica el nombre de un empleado del fichero";
    

    
    @Override
    public String toString();

    @Override
    public boolean equals(Object obj);

    @Override
    public int hashCode();
    
    public Empleado pedirEmpleado();
    
    public long pedirIdentificador();
    
    public String pedirApellido();

    public void mostrarEmpleado(Empleado emple);
    
    public void mostrarMensaje(String mensaje);
    
    
}
