/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.break4learning.examen.modelo.Objetos;

/**
 *
 * @author Break4Learning by Javier García-Retamero Redondo
 * @version 1.0
 * Created on 20 oct 2025
 */
public class Vehiculo {
    
    private long id;
    private String matricula;
    private int telefono;
    private String nombreContacto;
    private String diagnostico;
    private String reparacion;
    private long coste;
    private String fecha_reparacion;
    
    
    // Tamaño de datos en bytes
    private static final int LONGITUD_LONG = 8;
    private static final int LONGITUD_DOUBLE = 8;
    private static final int LONGITUD_INT = 4;
    private static final int LONGITUD_CHAR = 2;
    
    // Tamaño fijado para las cadenas de caracteres
    private static final int CARACTERES_MATRICULA = 7;
    private static final int CARACTERES_NOMBRECONTACTO = 100;
    private static final int CARACTERES_DIAGNOSTICO = 300;
    private static final int CARACTERES_REPARACION = 300;
    private static final int CARACTERES_FECHA_REPARACION = 10;

    
    // Tamaño de cada uno de los campos que forman el registro del opositor
    private static final int LONGITUD_IDENTIFICADOR = LONGITUD_LONG; // Long
    private static final int LONGITUD_MATRICULA_EN_BYTES = LONGITUD_CHAR * CARACTERES_MATRICULA; 
    private static final int LONGITUD_TELEFONO = LONGITUD_INT; 
    private static final int LONGITUD_NOMBRECONTACTO_EN_BYTES = LONGITUD_CHAR * CARACTERES_NOMBRECONTACTO;
    private static final int LONGITUD_DIAGNOSTICO_EN_BYTES = LONGITUD_CHAR * CARACTERES_DIAGNOSTICO;
    private static final int LONGITUD_REPARACION_EN_BYTES = LONGITUD_CHAR * CARACTERES_REPARACION; 
    private static final int LONGITUD_COSTE = LONGITUD_LONG; 
    private static final int LONGITUD_FECHA_REPARACION = LONGITUD_CHAR * CARACTERES_FECHA_REPARACION; 
    
    
    // Tamaño total del registro
    private static final int LONGITUD_TOTAL = LONGITUD_IDENTIFICADOR + LONGITUD_MATRICULA_EN_BYTES + LONGITUD_TELEFONO + 
            LONGITUD_NOMBRECONTACTO_EN_BYTES + LONGITUD_DIAGNOSTICO_EN_BYTES + LONGITUD_REPARACION_EN_BYTES +
            LONGITUD_COSTE + LONGITUD_FECHA_REPARACION;

    public Vehiculo() {
    }

    public Vehiculo(String matricula, int telefono, String nombreContacto, String diagnostico, String reparacion, long coste, String fecha_reparacion) {
        this.matricula = matricula;
        this.telefono = telefono;
        this.nombreContacto = nombreContacto;
        this.diagnostico = diagnostico;
        this.reparacion = reparacion;
        this.coste = coste;
        this.fecha_reparacion = fecha_reparacion;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getNombreContacto() {
        return nombreContacto;
    }

    public void setNombreContacto(String nombreContacto) {
        this.nombreContacto = nombreContacto;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getReparacion() {
        return reparacion;
    }

    public void setReparacion(String reparacion) {
        this.reparacion = reparacion;
    }

    public long getCoste() {
        return coste;
    }

    public void setCoste(long coste) {
        this.coste = coste;
    }

    public String getFecha_reparacion() {
        return fecha_reparacion;
    }

    public void setFecha_reparacion(String fecha_reparacion) {
        this.fecha_reparacion = fecha_reparacion;
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

    public static int getCARACTERES_NOMBRECONTACTO() {
        return CARACTERES_NOMBRECONTACTO;
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

    public static int getLONGITUD_IDENTIFICADOR() {
        return LONGITUD_IDENTIFICADOR;
    }

    public static int getLONGITUD_MATRICULA_EN_BYTES() {
        return LONGITUD_MATRICULA_EN_BYTES;
    }

    public static int getLONGITUD_TELEFONO() {
        return LONGITUD_TELEFONO;
    }

    public static int getLONGITUD_NOMBRECONTACTO_EN_BYTES() {
        return LONGITUD_NOMBRECONTACTO_EN_BYTES;
    }

    public static int getLONGITUD_DIAGNOSTICO_EN_BYTES() {
        return LONGITUD_DIAGNOSTICO_EN_BYTES;
    }

    public static int getLONGITUD_REPARACION_EN_BYTES() {
        return LONGITUD_REPARACION_EN_BYTES;
    }

    public static int getLONGITUD_COSTE() {
        return LONGITUD_COSTE;
    }

    public static int getLONGITUD_FECHA_REPARACION() {
        return LONGITUD_FECHA_REPARACION;
    }

    public static int getLONGITUD_TOTAL() {
        return LONGITUD_TOTAL;
    }

    @Override
    public String toString() {
        return "Vehiculo{" + "id=" + id + ", matricula=" + matricula + ", telefono=" + telefono + ", nombreContacto=" + nombreContacto + ", diagnostico=" + diagnostico + ", reparacion=" + reparacion + ", coste=" + coste + ", fecha_reparacion=" + fecha_reparacion + '}';
    }
}
