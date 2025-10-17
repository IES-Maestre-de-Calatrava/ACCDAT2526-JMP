/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package modelo.objetos;

import java.io.Serializable;
import java.util.Objects;

/**
 * Representa a un Empleado de la empresa
 * 
 * @author Break4Learning by Javier García-Retamero Redondo
 * @version 1.0
 * Created on 3 oct 2024
 */

public class Departamento implements Serializable, Comparable<Departamento>{

    private static final long serialVersionUID = -2873344211410398458L;

    //Atributos

    /**
     * Nombre del empleado
     */
    protected String nombre;
    /**
     * Apellido del empleado
     */
    protected int num_dept;

    public Departamento() {
    }

    public Departamento(String nombre, int num_dept) {
        this.nombre = nombre;
        this.num_dept = num_dept;
    }

    
    
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNum_dept() {
        return num_dept;
    }

    public void setNum_dept(int num_dept) {
        this.num_dept = num_dept;
    }

       
    public boolean equals (Departamento d){

        if(d.getNombre().equals(nombre) && d.getNum_dept() == num_dept){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public int compareTo(Departamento d){
        int estado=-1;
        if(this.num_dept==d.getNum_dept()){
            //Los objetos son iguales
            estado=0;
        }else if(this.num_dept>d.getNum_dept()){
            //El objeto 1 es mayor que la pasada por parametro
            estado=1;
        }
        return estado;

    }
    @Override
    public String toString (){
        String mensaje="El departamento "+nombre+" tiene el número de departamento "+num_dept;
        return mensaje;
    }

    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Departamento other = (Departamento) obj;
        if (this.num_dept != other.num_dept) {
            return false;
        }
        return Objects.equals(this.nombre, other.nombre);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.nombre);
        hash = 29 * hash + this.num_dept;
        return hash;
    }
}
