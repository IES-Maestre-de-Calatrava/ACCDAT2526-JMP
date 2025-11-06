/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package modelo;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Optional;

/**
 *
 * @author Javier Molina
 * Created on 21 oct 2025
 */
public class Departamento {
    private int dept_no;
    private String dnombre;
    private String loc;
    
    
    private final static String SELECTALL = "SELECT d.* FROM departamentos d";
    private final static String SELECTID = "SELECT d.* FROM departamentos d WHERE d.dept_no = ?";
    private final static String INSERT = "insert into Departamentos values (?,?,?)";
    private final static String INSERTAUTO = "insert into Departamentos (dnombre, loc) values (?,?)";
    private final static String UPDATE = "update Departamentos set dnombre=?, loc=? where dept_no=?";
    private final static String DELETE = "delete from Departamentos where dept_no=?";

    public Departamento() {
    }

    public Departamento(int dept_no, String dnombre, String loc) {
        this.dept_no = dept_no;
        this.dnombre = dnombre;
        this.loc = loc;
    }
    
    public Departamento(String dnombre, String loc){
        this.dnombre = dnombre;
        this.loc = loc;
    }

    public int getDept_no() {
        return dept_no;
    }

    public String getDnombre() {
        return dnombre;
    }

    public String getLoc() {
        return loc;
    }

    public void setDept_no(int dept_no) {
        this.dept_no = dept_no;
    }

    public void setDnombre(String dnombre) {
        this.dnombre = dnombre;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    @Override
    public String toString() {
        return "Departamento{" + "dept_no=" + dept_no + ", dnombre=" + dnombre + ", loc=" + loc + '}';
    }
    
    public static Optional<ResultSet> selectAll(OperacionesBBDD bbdd){
        Optional<ResultSet> rs = null;
        
        try {
            rs = bbdd.select(SELECTALL);
        } catch (SQLException ex) {
            System.getLogger(Departamento.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        
        return rs;
    }
    
    public void SelectById(OperacionesBBDD bbdd, int dept_no){
        Optional<ResultSet> rs = null;
        
        try {
            rs = bbdd.select(SELECTID, dept_no); 
            if(rs.isPresent()){
                while (rs.get().next()) {
                    this.setDept_no(rs.get().getInt("dept_no"));
                    this.setDnombre(rs.get().getString("dnombre"));
                    this.setLoc(rs.get().getString("loc"));
                }
            }
        } catch (SQLException ex) {
            System.getLogger(Departamento.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        
    }
    
    public static void mostrarResultado(Optional<ResultSet> rs){
        
        try {
            if(rs.isPresent()){
                while (rs.get().next()){
                    System.out.println("Numero departamento: " + rs.get().getInt("dept_no") +
                        ". Nombre: " + rs.get().getString("dnombre") + ". Localidad: " + rs.get().getString("loc"));
                }
            }
        } catch (SQLException ex) {
            System.getLogger(Departamento.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
    
    public void insertar(OperacionesBBDD bbdd){
        try {
            bbdd.insert(INSERT, this.dept_no, this.dnombre, this.loc);
        } catch (SQLException ex) {
            System.getLogger(Departamento.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
    
    public String insertarAuto(OperacionesBBDD bbdd){
        String rowid = null;
        
        try {
            Optional<ResultSet> rs = bbdd.insert(INSERTAUTO, this.dnombre, this.loc);
            
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
        
        i = bbdd.updateDeleteQuery(UPDATE, this.dnombre, this.loc, this.dept_no);
        
        return i;
    }
    
    public int delete(OperacionesBBDD bbdd){
        int i;
        
        i = bbdd.updateDeleteQuery(DELETE, this.dept_no);
        
        return i;
    }

    public static String pNombreDepart(OperacionesBBDD bbdd, int dept_no){
        CallableStatement llamada;
        String dnombre = null;
        String sql = "{call p_nombre_depart (?,?)}";
        
        try {
            llamada = bbdd.getConexion().prepareCall(sql);
            
            llamada.setInt(1, dept_no); // en ese primer paramatro va el dept_no
            
            llamada.registerOutParameter(2, Types.VARCHAR); // el segundo parametro es de salida
            
            llamada.executeUpdate();  // LO ejecuto
            
            dnombre = llamada.getString(2); // recupero el nombre de la salida con getString()
            
        } catch (SQLException ex) {
            System.getLogger(Departamento.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        
        return dnombre;
    }
    
    public static String fNombredepart(OperacionesBBDD bbdd, int dept_no){
        CallableStatement llamada;
        String dnombre = null;
        String sql = "{? = call f_nombre_depart (?)}";
        
        try {
            llamada = bbdd.getConexion().prepareCall(sql);
            
            llamada.registerOutParameter(1, Types.VARCHAR); // el primer parametro es de salida
            
            llamada.setInt(2, dept_no); // en ese segundo paramatro va el dept_no, parametro entrada
                       
            llamada.executeUpdate();  // Lo ejecuto
            
            dnombre = llamada.getString(1); // recupero el nombre de la salida con getString()
            
        } catch (SQLException ex) {
            System.getLogger(Departamento.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        
        return dnombre;
    }
    

}
