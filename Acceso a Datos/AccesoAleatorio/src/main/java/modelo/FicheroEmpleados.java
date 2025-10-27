/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package modelo;

import java.io.File;

/**
 * Clase que trabaja con ficheros de empleados
 * 
 * @author Break4Learning by Javier García-Retamero Redondo
 * @version 1.0
 * Created on 24 sept 2024
 */
public class FicheroEmpleados {
    
    // Tamaño de datos en bytes
    private final int LONGITUD_LONG = 8;
    private final int LONGITUD_DOUBLE = 8;
    private final int LONGITUD_INT = 4;
    private final int LONGITUD_CHAR = 2;
    
    // Tamaño fijado para la cadena de caracteres
    private final int CARACTERES_APELLIDO = 10;
    
    // Tamaño de cada uno de los campos que forman el registro del empleado
    private final int LONGITUD_IDENTIFICADOR = LONGITUD_LONG; // Long
    private final int LONGITUD_APELLIDO_EN_BYTES = LONGITUD_CHAR * CARACTERES_APELLIDO; // Cada carácter por el número de bytes que ocupa 
    private final int LONGITUD_DEPARTAMENTO = LONGITUD_INT; // Double
    private final int LONGITUD_SALARIO = LONGITUD_DOUBLE; // Double
    
    // Tamaño total del registro
    private final int LONGITUD_TOTAL = LONGITUD_IDENTIFICADOR + LONGITUD_APELLIDO_EN_BYTES + LONGITUD_DEPARTAMENTO + LONGITUD_SALARIO;
    
    // Ruta para elmacenar el archivo
    private File ruta;

    public FicheroEmpleados(String ruta) {
        this.ruta = new File(ruta);
    }
    
    public int getLONGITUD_APELLIDO_EN_BYTES() {
        return LONGITUD_APELLIDO_EN_BYTES;
    } 
    
    public int getLONGITUD_TOTAL() {
        return LONGITUD_TOTAL;
    }

    public int getCARACTERES_APELLIDO() {
        return CARACTERES_APELLIDO;
    }
    
    public File getRuta() {
        return ruta;
    }
    
    public void setRuta(String ruta) {
        this.ruta = new File(ruta);
    }
    
    public int getLONGITUD_IDENTIFICADOR() {
        return LONGITUD_IDENTIFICADOR;
    }
       
}
