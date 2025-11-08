/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package modelo;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

/**
 *
 * @author Javier Molina
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
    
    private final static String CONSULTA_SELECT_ALL = "SELECT e.* FROM Empleados e";
    private final static String CONSULTA_SELECT_BY_ID = "SELECT e.* FROM Empleados e WHERE e.emp_no = ?";
    private final static String INSERT = "insert into Empleados values (?,?,?,?,?,?,?,?)";
    private final static String INSERTAUTO = "insert into Empleados (emp_no, apellido, oficio) values (?,?,?)";
    private final static String UPDATE = "update Empleados set apellido=?, oficio=? where dept_no=?";
    private final static String DELETE = "delete from Empleados where dept_no=?";

    
    
    public Empleado(){
        
    }
    
    public Empleado(int emp_no, String apellido, String oficio) {
        this.emp_no = emp_no;
        this.apellido = apellido;
        this.oficio = oficio;
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
        //this.fecha_alt = OperacionesBBDD.convertirFecha(fecha_alt);
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
    
    
    public static Optional<ResultSet> selectAll(OperacionesBBDD bbdd){
        Optional<ResultSet> rs = Optional.empty();
        
        try {
            rs = bbdd.select(CONSULTA_SELECT_ALL);
        } catch (SQLException ex) {
            System.getLogger(Departamento.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        
        return rs;
    }
    
    public void SelectById(OperacionesBBDD bbdd, int dept_no){
        Optional<ResultSet> rs = null;
        
        try {
            rs = bbdd.select(CONSULTA_SELECT_BY_ID, emp_no); 
            if(rs.isPresent()){
                while (rs.get().next()) {
                    this.setEmp_no(rs.get().getInt("emp_no"));
                    this.setApellido(rs.get().getString("apellido"));
                    this.setOficio(rs.get().getString("oficio"));
                    this.setDir(rs.get().getInt("dir"));
                    this.setFecha_alt(rs.get().getString("fecha_alt"));
                    this.setSalario(rs.get().getDouble("salario"));
                    this.setComision(rs.get().getDouble("comision"));
                    this.setDept_no(rs.get().getInt("salario"));

                }
            }
        } catch (SQLException ex) {
            System.getLogger(Departamento.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        
    }
    
    public static void motrarResultado(Optional<ResultSet> rsOptional){
        
        if(!rsOptional.isPresent()){
            System.out.println("No se obtuvieron resultados o hubo un error en la BBDD");
            return;
        }
        
        // Desempaquetar solo la UNA vez
        ResultSet rs = rsOptional.get();
        try {
            while (rs.next()){
                    System.out.println("Numero empleado: " + rs.getInt("emp_no") +
                        ". Apellido: " + rs.getString("apellido") + 
                        ". Oficio: " + rs.getString("oficio") + 
                        ". Dir: " + rs.getInt("dir") + 
                        ". Fecha_alt: " + rs.getString("fecha_alt") + 
                        ". Salario: " + rs.getDouble("salario") + 
                        ". Comision: " + rs.getDouble("comision") + 
                        ". Numero de departamento: " + rs.getInt("dept_no"));
                }
            
        } catch (SQLException ex) {
            System.getLogger(Departamento.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
    
    
    public static void motrarApellidoDirComisionSalario(OperacionesBBDD bbdd, Optional<ResultSet> rsOptional){
        
        if(!rsOptional.isPresent()){
            System.out.println("No se obtuvieron resultados o hubo un error en la BBDD");
            return;
        }
        
        // Desempaquetar solo la UNA vez
        ResultSet rs = rsOptional.get();
        try {
            while (rs.next()){
                    System.out.println(
                        "Apellido: " + rs.getString("apellido") + 
                        ". Salario: " + rs.getDouble("salario") + 
                        ". Comision: " + rs.getDouble("comision")+ 
                        ". Nomina tras aplicar irpf 20%: " + fNomina(bbdd, rs.getDouble("salario"), rs.getDouble("comision"), 20));
                }
            
        } catch (SQLException ex) {
            System.getLogger(Departamento.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
    
    public void insertar(OperacionesBBDD bbdd){
        try {
            bbdd.insert(INSERT, this.emp_no, this.apellido, this.oficio, this.dir, this.fecha_alt, this.salario, this.comision, this.dept_no);
        } catch (SQLException ex) {
            System.getLogger(Departamento.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
    
    public String insertarAuto(OperacionesBBDD bbdd){
        String rowid = null;
        
        try {
            Optional<ResultSet> rs = bbdd.insert(INSERTAUTO, this.emp_no, this.apellido, this.oficio);
            
            if(rs.isPresent()){
                rs.get().next();
                rowid = rs.get().getString(1);
                
            }
            
            return rowid;
        } catch (SQLException ex) {
            System.getLogger(Departamento.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        
        return rowid;
    }
    
    public int update(OperacionesBBDD bbdd){
        int i;
        
        i = bbdd.updateDeleteQuery(UPDATE, this.apellido, this.oficio, this.dept_no);
        
        return i;
    }
    
    public int delete(OperacionesBBDD bbdd){
        int i;
        
        i = bbdd.updateDeleteQuery(DELETE, this.dept_no);
        
        return i;
    }
    
    
    public static void pSubidaSalario(OperacionesBBDD bbdd, int dept_no, int cantidad){
        CallableStatement llamada;
        String sql = "{call p_subida_sal (?,?)}";
        
        try {
            llamada = bbdd.getConexion().prepareCall(sql);
            
            llamada.setInt(1, dept_no); // en ese primer paramatro va el dept_no
            
            llamada.setInt(2, cantidad); // el segundo parametro es la cantidad
            
            llamada.executeUpdate();  // Lo ejecuto
            
            
        } catch (SQLException ex) {
            System.getLogger(Departamento.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        
    }
    
    
    public static int fEmpleadosDepartamento(OperacionesBBDD bbdd, int dept_no){
        CallableStatement llamada;
        int numEmpleados = 0;
        String sql = "{? = call f_n_empleado (?)}";
        
        try {
            llamada = bbdd.getConexion().prepareCall(sql);
            
            llamada.registerOutParameter(1, Types.INTEGER); // el primer parametro es de salida
            
            llamada.setInt(2, dept_no); // en ese segundo paramatro va el dept_no, parametro entrada
                       
            llamada.executeUpdate();  // Lo ejecuto
            
            numEmpleados = llamada.getInt(1); // recupero el nombre de la salida con getString()
            
        } catch (SQLException ex) {
            System.getLogger(Departamento.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        
        return numEmpleados;
    }
    
    
    public static double fNomina(OperacionesBBDD bbdd, double salario, double comision, int irpf){
        CallableStatement llamada;
        double salarioFinal = 0;
        String sql = "{? = call f_nomina (?,?,?)}";
        
        try {
            llamada = bbdd.getConexion().prepareCall(sql);
            
            llamada.registerOutParameter(1, Types.DOUBLE); // el primer parametro es de salida
            
            llamada.setDouble(2, salario); 
                       
            llamada.setDouble(3, comision);
            
            llamada.setInt(4, irpf);
            
            llamada.executeUpdate();  // Lo ejecuto
            
            salarioFinal = llamada.getDouble(1); // recupero el nombre de la salida con get[tipo_dato]()
            
        } catch (SQLException ex) {
            System.getLogger(Departamento.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        
        return salarioFinal;
    }
    
    
    
}
