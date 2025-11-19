package com.mycompany.conexionoracle;


import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import modelo.Departamento;
import modelo.Empleado;
import modelo.OperacionesBBDD;
import modelo.Viajes;

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
    public static void main(String[] args) throws SQLException, InterruptedException {
        
        
        Viajes viaje = new Viajes("11223", "Valencia", "Islas Baleares", "11/01/2026", "20/01/2026", 1500.75);
        viaje.insertar(bbdd, viaje);
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
        
        // PRUEBA DE LAS EXCEPCIONES
        //Departamento dept = new Departamento(15, "INFORMATICA", "MADRID");
        //dept.insertar(bbdd);
        
       
        
        //System.out.println(em.insertar(bbdd));
        
        //em.update(bbdd);
        //em.delete(bbdd);
        
        //em.SelectEmpleadosYSalarioByDepartamento(bbdd);
       
        
        /*
        Optional<ResultSet> rsOpt = emple.selectAll(bbdd);
        Empleado.motrarApellidoDirComisionSalario(bbdd, rsOpt);
        */
        
        //probarMovimientoEnResultSet();
        
        
        
        // MODIFICACIONES DEL RESULTSET
        //probarUpdateSobreResultSet();
        
        // probarInsertSobreResultSet();
       
        //probarDeleteSobreResultSet(60);
          
        /*
        Empleado em = new Empleado(7777, "MOLINA", "EMPLEADO", 7566, "11/11/2025", 1800.75, 2, 15);
        Empleado emp = new Empleado(8888, "CANDELAS", "EMPLEADO", 7566, "11/11/2025", 1700.75, 1, 15);
        System.out.println(em.insertar(bbdd));
        System.out.println(emp.insertar(bbdd));
        */
                
        /*
        Empleado em = new Empleado();
        //em.subirSalarioPorDepartamento(bbdd, 100, 15);
        em.subirPorcentajeDeSalarioPorDepartamento(bbdd, 50, 15);
        */
                
    }
    
    public static void probarMovimientoEnResultSet() throws SQLException {
        Departamento departamento  = new Departamento();
        Optional<ResultSet> rs = departamento.selectAll(bbdd);
        
        System.out.println("======================MOSTRAR 1================================");
        departamento.mostrarResultado(rs);
        
        System.out.println("===============================================================");
        System.out.println("Total departamentos: "+OperacionesBBDD.numeroFilasResultSet(rs.get()));
        
        try {
            rs.get().beforeFirst();
        } catch (SQLException ex) {
            System.getLogger(Conexionoracle.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            throw ex;
        }
        
        System.out.println("\n=========================MOSTRAR 2==================================");
        departamento.mostrarResultado(rs);
        System.out.println("===============================================================");
        System.out.println("Total departamentos: "+OperacionesBBDD.numeroFilasResultSet(rs.get()));
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
