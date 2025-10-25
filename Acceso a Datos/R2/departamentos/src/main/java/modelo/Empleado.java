/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package modelo;

import java.sql.Date;

/**
 *
 * @author b15-11m
 * Created on 21 oct 2025
 */
public class Empleado {
    
    private int emp_no;
    private String apellido;
    private String oficio;
    private int dir;
    private Date fecha_alt;
    private double salario;
    private double comision;
    private int dept_no;

    public Empleado(){
        
    }
    
    public Empleado(int emp_no, String apellido, String oficio, int dir, String fecha_alt, double salario, double comision, int dept_no) {
        this.emp_no = emp_no;
        this.apellido = apellido;
        this.oficio = oficio;
        this.dir = dir;      
        this.salario = salario;
        this.comision = comision;
        this.dept_no = dept_no;
        
        //this.fecha_alt = convertirFecha(fecha_alt);
    }
    
    public int getEmp_no() {
        return emp_no;
    }

    public String getApellido() {
        return apellido;
    }

    public String getOficio() {
        return oficio;
    }

    public int getDir() {
        return dir;
    }

    public Date getFecha_alt() {
        return fecha_alt;
    }

    public double getSalario() {
        return salario;
    }

    public double getComision() {
        return comision;
    }

    public int getDept_no() {
        return dept_no;
    }

    public void setEmp_no(int emp_no) {
        this.emp_no = emp_no;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setOficio(String oficio) {
        this.oficio = oficio;
    }

    public void setDir(int dir) {
        this.dir = dir;
    }

    public void setFecha_alt(String fecha_alt) {
        //this.fecha_alt = convertirFecha(fecha_alt);
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public void setComision(double comision) {
        this.comision = comision;
    }

    public void setDept_no(int dept_no) {
        this.dept_no = dept_no;
    }
    
    @Override
    public String toString() {
        return "Empleado{" + "emp_no=" + emp_no + ", apellido=" + apellido + ", oficio=" + oficio + ", dir=" + dir + ", fecha_alt=" + fecha_alt + ", salario=" + salario + ", comision=" + comision + ", dept_no=" + dept_no + '}';
    }    
}
