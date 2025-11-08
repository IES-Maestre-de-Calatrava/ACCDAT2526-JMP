package com.mycompany.conexionoracle;


import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Optional;
import modelo.Departamento;
import modelo.Empleado;
import modelo.OperacionesBBDD;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license



/**
 *
 * @author Javier Molina
 */
public class Conexionoracle {

    private static OperacionesBBDD bbdd = OperacionesBBDD.getInstancia();
    /*
    private static Optional<ResultSet> rs;
    */
    public static void main(String[] args) {
        
        /*
        Departamento departamento = new Departamento();
        Optional<ResultSet> rsOpt = departamento.selectAll(bbdd);
        Departamento.mostrarResultado(rsOpt);
        */
        
        // Departamento.MostrarResultado(Departamento.selectAll(bbdd));
        
        // Ejemplo de insertar
        
        /*
        Departamento departamento = new Departamento(60, "TRANSPORTE", "MADRID");
        departamento.insertar(bbdd);
        */
        
        // Probamos el método insert
        /*
        Departamento departamento = new Departamento("LOGISTICA", "SEVILLA");
        System.out.println("El departamento insertado tiene un RowID que es: " + departamento.insertarAuto(bbdd));
        */
        
        
        // Probramos el metodo update
        /*
        Departamento departamento = new Departamento(40, "PRODUCCION", "DAIMIEL");
        
        int i = departamento.update(bbdd);
        String mensaje = "Error en la operacion";
        
        if(i > 0){
            System.out.println("Operacion correcta");
        } else{
            System.out.println(mensaje);
        }
        */
        
        /*
        // Probamos el metodo delete
        Departamento departamento = new Departamento();
        departamento.setDept_no(60);
        
        int i = departamento.delete(bbdd);
        String mensaje = "Error en la operacion";
        
        if(i > 0){
            System.out.println("Operacion correcta");
        } else{
            System.out.println(mensaje);
        }
        */
        
        
        
        // EJEMPLO DE INSERTAR EL EMPLEADO
        
        /*
        Empleado empleado = new Empleado(7999, "MOLINA", "ESTUDIANTE", 7777, "27/10/2025", 2250, 400, 40);
        empleado.insertar(bbdd);
        */
        
        
        /*
        // EJEMPLO DE DELETE EMPLEADO
        Empleado empleado = new Empleado();
        empleado.setDept_no(40);
        
        int i = empleado.delete(bbdd);
        String mensaje = "Error en la operacion";
        
        if(i > 0){
            System.out.println("Operacion correcta");
        } else{
            System.out.println(mensaje);
        }
        */
        
        
        //probarLlamadaProcedimiento();
        
        //probarLlamadaFuncion();
        
        //probarSubidaSueldo();
        
        //probarEmpledosPorDepartamento();
        
        //probarCalcularSalario();
        
        
        Empleado emple = new Empleado();
        Optional<ResultSet> rsOpt = emple.selectAll(bbdd);
        Empleado.motrarApellidoDirComisionSalario(bbdd, rsOpt);        
        
    }
    
    public static void probarLlamadaProcedimiento(){
        System.out.println("Nombre de departamento:" + Departamento.pNombreDepart(bbdd, 10));
    }
    
    public static void probarLlamadaFuncion(){
        System.out.println("Nombre de departamento:" + Departamento.fNombredepart(bbdd, 10));
    }
    
    public static void probarSubidaSueldo(){
        System.out.println("Subida de salario realizada: ");
        Empleado.pSubidaSalario(bbdd, 10, 300);
    }
    
    public static void  probarEmpledosPorDepartamento(){
        System.out.println("Numero de empleados del departamento: " + Empleado.fEmpleadosDepartamento(bbdd, 10));
    }
    
    public static void probarCalcularSalario(){
        System.out.println("Salario final tras aplicar irpf: " + Empleado.fNomina(bbdd, 1000, 500, 20));
    }
    
    
}
