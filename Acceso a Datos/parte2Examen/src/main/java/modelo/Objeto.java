/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package modelo;

/**
 * Clase que trabaja con ficheros de empleados
 * 
 * @author Break4Learning by Javier García-Retamero Redondo
 * @version 1.0
 * Created on 24 sept 2024
 */
public class Objeto {
    
    private long id;
    private String dni;
    private String apellidos;
    private String nombre;
    private String respuestas;
    private int nota;
    
    // Tamaño de datos en bytes
    private static final int LONGITUD_LONG = 8;
    private static final int LONGITUD_DOUBLE = 8;
    private static final int LONGITUD_INT = 4;
    private static final int LONGITUD_CHAR = 2;
    
    // Tamaño fijado para las cadenas de caracteres
    private static final int CARACTERES_DNI = 9;
    private static final int CARACTERES_APELLIDOS = 20;
    private static final int CARACTERES_NOMBRE = 10;
    private static final int CARACTERES_RESPUESTAS = 10;


    
    // Tamaño de cada uno de los campos que forman el registro del opositor
    private static final int LONGITUD_IDENTIFICADOR = LONGITUD_LONG; // Long
    private static final int LONGITUD_DNI_EN_BYTES = LONGITUD_CHAR * CARACTERES_DNI; 
    private static final int LONGITUD_APELLIDOS_EN_BYTES = LONGITUD_CHAR * CARACTERES_APELLIDOS;
    private static final int LONGITUD_NOMBRE_EN_BYTES = LONGITUD_CHAR * CARACTERES_NOMBRE;
    private static final int LONGITUD_RESPUESTAS_EN_BYTES = LONGITUD_CHAR * CARACTERES_RESPUESTAS; 
    private static final int LONGITUD_NOTA = LONGITUD_INT; 
    
    // Tamaño total del registro
    private final static int LONGITUD_TOTAL = LONGITUD_IDENTIFICADOR + LONGITUD_DNI_EN_BYTES + LONGITUD_APELLIDOS_EN_BYTES + LONGITUD_NOMBRE_EN_BYTES + LONGITUD_RESPUESTAS_EN_BYTES + LONGITUD_NOTA;
   

    public Objeto() {
    
    }

    public Objeto(long id, String dni, String apellidos, String nombre, String respuestas, int nota) {
        this.id = id;
        this.dni = dni;
        this.apellidos = apellidos;
        this.nombre = nombre;
        this.respuestas = respuestas;
        this.nota = nota;
    }

    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }
    
    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getApellidos() {
        return apellidos;
    }
    
    
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRespuestas() {
        return respuestas;
    }
    
    public void setRespuestas(String respuestas) {
        this.respuestas = respuestas;
    }

    public int getNota() {
        return nota;
    }
    
    public void setNota(int nota) {
        this.nota = nota;
    }  

    public static int getCARACTERES_DNI() {
        return CARACTERES_DNI;
    }

    public static int getCARACTERES_APELLIDOS() {
        return CARACTERES_APELLIDOS;
    }

    public static int getCARACTERES_NOMBRE() {
        return CARACTERES_NOMBRE;
    }

    public static int getCARACTERES_RESPUESTAS() {
        return CARACTERES_RESPUESTAS;
    }

    public static int getLONGITUD_IDENTIFICADOR() {
        return LONGITUD_IDENTIFICADOR;
    }

    public static int getLONGITUD_DNI_EN_BYTES() {
        return LONGITUD_DNI_EN_BYTES;
    }

    public static int getLONGITUD_APELLIDOS_EN_BYTES() {
        return LONGITUD_APELLIDOS_EN_BYTES;
    }

    public static int getLONGITUD_NOMBRE_EN_BYTES() {
        return LONGITUD_NOMBRE_EN_BYTES;
    }

    public static int getLONGITUD_RESPUESTAS_EN_BYTES() {
        return LONGITUD_RESPUESTAS_EN_BYTES;
    }

    public static int getLONGITUD_NOTA() {
        return LONGITUD_NOTA;
    }

    public static int getLONGITUD_TOTAL() {
        return LONGITUD_TOTAL;
    }
    
    public static int calcularNota(String respuestas, String plantilla){
        int nota = 0;
        
        char respuestaCaracter;
        char plantillaCaracter;
        
        for(int i = 0; i < 10; i++){
            respuestaCaracter = respuestas.charAt(i);
            plantillaCaracter = plantilla.charAt(i);
            
            if(respuestaCaracter == plantillaCaracter){
                nota++;
            }
        }
        return nota;
    }
}
    
    
    
    
    