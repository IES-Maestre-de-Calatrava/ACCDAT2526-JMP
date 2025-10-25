/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package vista;

import controlador.Controlador;

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
    static final String LEER_CARACTER_A_CARACTER = "Lee un fichero caracter a caracter";
    static final String LEER_BLOQUE_DE_CARACTERES = "Lee un fichero por medio de bloque de caracteres";
    static final String LEER_POR_LINEAS = "Lee un fichero linea a linea";
    static final String ESCRIBIR_CARACTER = "Escribe en un fichero un caracter dado";
    static final String ESRIBIR_BLOQUE_DE_CARACTERES = "Escribe en un fichero una Cadena de Caracteres (sin salto de línea)";
    static final String ESCRIBIR_LINEA = "escribe una LÍNEA de caracteres en un fichero (incluyendo el salto de línea)";
    static final String COPIAR_ARCHIVO = "Copia el archivo en otro";
    static final String COPIAR_ARCHIVO_ENCRIPTADO = "Copiar archivo a otro encriptandolo";
    static final String DESENCRIPTAR_ARCHIVO = "Desencripta el archivo creado en caso de haberlo";

    
    @Override
    public String toString();

    @Override
    public boolean equals(Object obj);

    @Override
    public int hashCode();
    
}
