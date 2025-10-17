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
    static final String CREAR_CARPETA_EN_RUTA = "Crear carpeta dada la ruta";
    static final String CREAR_CARPETA_EN_RUTA_DADO_EL_NOMBRE = "Crear carpeta en la ruta dado el nombre";
    static final String CREAR_CARPETA_DADO_DIRECTORIO_RAIZ = "Crear carpeta dado el directorio raiz";
    static final String CREAR_ARCHIVO_EN_DIRECTORIO = "Crear archivo en directorio determinado";
    static final String VER_CONTENIDO_DIRECTORIO = "Ver contenido de un directorio";
    static final String BORRAR = "Borrar";
    static final String CAMBIAR_NOMBRE_ARCHIVO = "Cambiar nombre al archivo";
    static final String COPIAR_ARCHIVO = "Copiar archivo a otro directorio";
    static final String MOVER_ARCHIVO = "Mover archivo a otro directorio";

    
    @Override
    public String toString();

    @Override
    public boolean equals(Object obj);

    @Override
    public int hashCode();
    
}
