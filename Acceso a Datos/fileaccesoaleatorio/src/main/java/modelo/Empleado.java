/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package modelo;

/**
 * Clase que representa a un empleado
 * 
 * @author Break4Learning by Javier García-Retamero Redondo
 * @version 1.0
 * Created on 5 oct 2024
 */
public class Empleado {
    private long identificador;
    private String apellido;
    private int departamento;
    private double salario;

    public Empleado(long identificador, String apellido, int departamento, double salario) {
        this.identificador = identificador;
        this.apellido = apellido;
        this.departamento = departamento;
        this.salario = salario;
    }

    public Empleado(String apellido, int departamento, double salario) {
        this.apellido = apellido;
        this.departamento = departamento;
        this.salario = salario;
    }
    
    public Empleado() {
    }

    public long getIdentificador() {
        return identificador;
    }

    public void setIdentificador(long identificador) {
        this.identificador = identificador;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getDepartamento() {
        return departamento;
    }

    public void setDepartamento(int departamento) {
        this.departamento = departamento;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    @Override
    public String toString() {
        return "Empleado{" + "identificador=" + identificador + ", apellido=" + apellido + ", departamento=" + departamento + ", salario=" + salario + '}';
    }
    
}
