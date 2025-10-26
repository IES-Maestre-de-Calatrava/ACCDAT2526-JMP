/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package vista;

import controlador.Controlador;
import modelo.objetos.Departamento;
import modelo.objetos.Empleado;

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
    static final String ESCRIBE_BYTE = "escribe un byte en un fichero";
    static final String ESCRIBE_CADENA = "escribe una cadena en un fichero";
    static final String LEE_BYTE_A_BYTE = "Lee un fichero byte a byte";
    static final String LEE_ARRAY_DE_BYTES = "Lee un fichero en bloques de arrays de 5 bytes";
    
    // LecturaEscrituraArrays
    static final String ESCRIBIR_ARRAY = "Escribe en un fichero un array";
    static final String LEER_ARRAY = "Lee un fichero de arrays";
    static final String COPIAR_ARCHIVO_DESTINO_ORIGEN = "Copia el archivo origen en una ruta destino";
    
    // LecturaEscrituraDatosPrimitivos
    static final String ESCRIBIR_DATOS_PRIMITIVOS = "Escribe datos primitivos en un archivo";
    static final String LEER_DATOS_PRIMITIVOS = "Lee datos primitivos de un fichero";
    
    // LecturaEscrituraObjetos
    static final String ESCRIBIR_OBJETOS = "Escribe un objeto en un fichero";
    static final String LEER_OBJETOS_UNIVERSAL = "Lee los objetos de un fichero y los devuelve en un ArrayList";
    static final String CONVERTIR_ARRAY_A_CLASE = "Convierte un ArrayList en la clase de objeto determinada";


    
    @Override
    public String toString();

    @Override
    public boolean equals(Object obj);

    @Override
    public int hashCode();
    
    public String[] obtenerNombres();
    
    public int[] obtenerTelefonos();
    
    public boolean[] obtenerAltas();
    
    public Object obtenerObjeto();
    
    public Departamento obtenerDepartamento();
    
    public Empleado obtenerEmpleado();
    
    public Class<?> obtenerClaseParaConversion();
    
}
