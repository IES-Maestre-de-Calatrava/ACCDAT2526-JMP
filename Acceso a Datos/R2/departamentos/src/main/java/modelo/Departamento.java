/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

/**
 *
 * @author b15-11m
 * Created on 21 oct 2025
 */
public class Departamento {
    private int dept_no;
    private String dnombre;
    private String loc;

    public Departamento() {
    }

    public Departamento(int dept_no, String dnombre, String loc) {
        this.dept_no = dept_no;
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
            rs = bbdd.select("SELECT d.* FROM departamentos d");
        } catch (SQLException ex) {
            System.getLogger(Departamento.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        
        return rs;
    }
    
    public void SelectById(OperacionesBBDD bbdd, int dept_no){
        Optional<ResultSet> rs = null;
        
        try {
            rs = bbdd.select("SELECT d.* FROM departamentos d WHERE d.dept_no = ?", dept_no); 
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
    
    // Esto debería de ir en la vista
    public static void MostrarResultado(Optional<ResultSet> rs){
        
        try {
            if(rs.isPresent()){
                while (rs.get().next()){
                    System.out.println("Número departamento: "+rs.get().getInt("dept_no")+
                            " Nombre: "+rs.get().getString("dnombre")+
                            " Localidad: "+rs.get().getString("loc"));
                }
            }
            
        } catch (SQLException ex) {
            System.getLogger(Departamento.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
    
    public void Insertar(OperacionesBBDD bbdd){
        try {
            bbdd.insert("insert into departamentos values (?,?,?)", this.dept_no, this.dnombre this.loc);
        } catch (SQLException ex) {
            System.getLogger(Departamento.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
    
}
