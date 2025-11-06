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
public class Coche {
    
    private long id;
    private String matricula;
    private int telefono;
    private String nombreContacto;
    private String diagnostico;
    private String reparacion;
    private long coste;
    private String fechaReparacion;
   
    // Tamaño de datos en bytes
    private static final int LONGITUD_LONG = 8;
    private static final int LONGITUD_DOUBLE = 8;
    private static final int LONGITUD_INT = 4;
    private static final int LONGITUD_CHAR = 2;
    
    // Tamaño fijado para las cadenas de caracteres
    private static final int CARACTERES_MATRICULA = 7;
    private static final int CARACTERES_NOMBRE_CONTACTO = 100;
    private static final int CARACTERES_DIAGNOSTICO = 300;
    private static final int CARACTERES_REPARACION = 300;
    private static final int CARACTERES_FECHA_REPARACION = 10;


    
    // Tamaño de cada uno de los campos que forman el registro del coche
    
    private static final int LONGITUD_IDENTIFICADOR_EN_BYTES = LONGITUD_LONG;
    private static final int LONGITUD_MATRICULA_EN_BYTES = LONGITUD_CHAR * CARACTERES_MATRICULA;
    private static final int LONGITUD_TELEFONO_EN_BYTES = LONGITUD_INT; 
    private static final int LONGITUD_NOMBRE_CONTACTO_EN_BYTES = LONGITUD_CHAR * CARACTERES_NOMBRE_CONTACTO; 
    private static final int LONGITUD_DIAGNOSTICO_EN_BYTES = LONGITUD_CHAR * CARACTERES_DIAGNOSTICO;
    private static final int LONGITUD_REPARACION_EN_BYTES = LONGITUD_CHAR * CARACTERES_REPARACION;
    private static final int LONGITUD_COSTE_EN_BYTES = LONGITUD_LONG; 
    private static final int LONGITUD_FECHA_REPARACION_EN_BYTES = LONGITUD_CHAR * CARACTERES_FECHA_REPARACION; 
    
    // Tamaño total del registro
    private final static int LONGITUD_TOTAL = LONGITUD_IDENTIFICADOR_EN_BYTES +
                                              LONGITUD_MATRICULA_EN_BYTES + 
                                              LONGITUD_TELEFONO_EN_BYTES + 
                                              LONGITUD_NOMBRE_CONTACTO_EN_BYTES + 
                                              LONGITUD_DIAGNOSTICO_EN_BYTES + 
                                              LONGITUD_REPARACION_EN_BYTES + 
                                              LONGITUD_COSTE_EN_BYTES + 
                                              LONGITUD_FECHA_REPARACION_EN_BYTES;
   

    public Coche() {
    
    }

    public Coche(String matricula, int telefono, String nombreContacto, String diagnostico, String reparacion, long coste, String fechaReparacion) {
        this.matricula = matricula;
        this.telefono = telefono;
        this.nombreContacto = nombreContacto;
        this.diagnostico = diagnostico;
        this.reparacion = reparacion;
        this.coste = coste;
        this.fechaReparacion = fechaReparacion;
    }

    public String getMatricula() {
        return matricula;
    }

    public int getTelefono() {
        return telefono;
    }

    public String getNombreContacto() {
        return nombreContacto;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public String getReparacion() {
        return reparacion;
    }

    public long getCoste() {
        return coste;
    }

    public String getFechaReparacion() {
        return fechaReparacion;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public void setNombreContacto(String nombreContacto) {
        this.nombreContacto = nombreContacto;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public void setReparacion(String reparacion) {
        this.reparacion = reparacion;
    }

    public void setCoste(long coste) {
        this.coste = coste;
    }

    public void setFechaReparacion(String fechaReparacion) {
        this.fechaReparacion = fechaReparacion;
    }

    public static int getLONGITUD_LONG() {
        return LONGITUD_LONG;
    }

    public static int getLONGITUD_DOUBLE() {
        return LONGITUD_DOUBLE;
    }

    public static int getLONGITUD_INT() {
        return LONGITUD_INT;
    }

    public static int getLONGITUD_CHAR() {
        return LONGITUD_CHAR;
    }

    public static int getCARACTERES_MATRICULA() {
        return CARACTERES_MATRICULA;
    }

    public static int getCARACTERES_NOMBRE_CONTACTO() {
        return CARACTERES_NOMBRE_CONTACTO;
    }

    public static int getCARACTERES_DIAGNOSTICO() {
        return CARACTERES_DIAGNOSTICO;
    }

    public static int getCARACTERES_REPARACION() {
        return CARACTERES_REPARACION;
    }

    public static int getCARACTERES_FECHA_REPARACION() {
        return CARACTERES_FECHA_REPARACION;
    }

    public static int getLONGITUD_MATRICULA_EN_BYTES() {
        return LONGITUD_MATRICULA_EN_BYTES;
    }

    public static int getLONGITUD_TELEFONO_EN_BYTES() {
        return LONGITUD_TELEFONO_EN_BYTES;
    }

    public static int getLONGITUD_NOMBRE_CONTACTO_EN_BYTES() {
        return LONGITUD_NOMBRE_CONTACTO_EN_BYTES;
    }

    public static int getLONGITUD_DIAGNOSTICO_EN_BYTES() {
        return LONGITUD_DIAGNOSTICO_EN_BYTES;
    }

    public static int getLONGITUD_REPARACION_EN_BYTES() {
        return LONGITUD_REPARACION_EN_BYTES;
    }

    public static int getLONGITUD_COSTE_EN_BYTES() {
        return LONGITUD_COSTE_EN_BYTES;
    }

    public static int getLONGITUD_FECHA_REPARACION_EN_BYTES() {
        return LONGITUD_FECHA_REPARACION_EN_BYTES;
    }

    public static int getLONGITUD_TOTAL() {
        return LONGITUD_TOTAL;
    }

    public long getId() {
        return id;
    }

    public static int getLONGITUD_IDENTIFICADOR_EN_BYTES() {
        return LONGITUD_IDENTIFICADOR_EN_BYTES;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    
    

   
}
    
    
    
    
    