/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package modelo;

/**
 *
 * @author b15-11m
 */

public class Empleado {
    private long identificador;
    private String apellido;
    private int departamento;
    private double salario;

    /**
     * Constructor de empleado con 4 atributos
     * @param identificador             Identificador del empleado
     * @param apellido                  Apellido del empleado
     * @param departamento              Departamento del empleado
     * @param salario                   Salario del empleado
     */
    public Empleado(long identificador, String apellido, int departamento, double salario) {
        this.identificador = identificador;
        this.apellido = apellido;
        this.departamento = departamento;
        this.salario = salario;
    }

    /**
     * Constructor de empleado con 3 atributos
     * @param apellido                  Apellido del empleado
     * @param departamento              Departamento del empleado
     * @param salario                   Salario del empleado
     */
    public Empleado(String apellido, int departamento, double salario) {
        this.apellido = apellido;
        this.departamento = departamento;
        this.salario = salario;
    }
    
    /**
     * Constructor vacío
     */
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
