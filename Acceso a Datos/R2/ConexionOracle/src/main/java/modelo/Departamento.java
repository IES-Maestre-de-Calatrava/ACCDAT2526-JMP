/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package modelo;

import com.mycompany.conexionoracle.Conexionoracle;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
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
    
    
    private final static String SELECTALL = "SELECT d.* FROM departamentos d ORDER BY dnombre";
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
    
    public static void insertSobreResultSet(OperacionesBBDD bbdd, Departamento departamento) throws SQLException, InterruptedException{
        try{
            Optional<ResultSet> rs = departamento.selectAll(bbdd);

            System.out.println("======================MOSTRAR ANTES DEL INSERT================================");
            departamento.mostrarResultado(rs);
            
            
            int numFila = rs.get().getRow();
            rs.get().moveToInsertRow();
            
            rs.get().updateInt(1, 60);
            rs.get().updateString(2, "DISEÑO");
            rs.get().updateString(3, "VALENCIA");
            
            rs.get().insertRow();
            rs.get().moveToCurrentRow();
            numFila = rs.get().getRow();

        
            System.out.println("\n=========================MOSTRAR DESPUES DEL INSERT==================================");
            rs.get().beforeFirst();
            departamento.mostrarResultado(rs);
            System.out.println("NumFilas: "+numFila);
        
        } catch (SQLException ex) {
            System.getLogger(Conexionoracle.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            throw ex;
        }
    }
    
    public static void insertSobreResultSet2(OperacionesBBDD bbdd, Departamento departamento) throws SQLException, InterruptedException{
        try{
            Optional<ResultSet> rs = departamento.selectAll(bbdd);

            System.out.println("======================MOSTRAR ANTES DEL INSERT================================");
            departamento.mostrarResultado(rs);
            
            
            int numFila = rs.get().getRow();
            rs.get().moveToInsertRow();
            
            rs.get().updateInt(1, departamento.getDept_no());
            rs.get().updateString(2, departamento.getDnombre());
            rs.get().updateString(3, departamento.getLoc());
            
            rs.get().insertRow();
            rs.get().moveToCurrentRow();
            numFila = rs.get().getRow();

        
            System.out.println("\n=========================MOSTRAR DESPUES DEL INSERT==================================");
            rs.get().beforeFirst();
            departamento.mostrarResultado(rs);
            System.out.println("NumFilas: "+numFila);
        
        } catch (SQLException ex) {
            System.getLogger(Conexionoracle.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            throw ex;
        }
    }

    public static void deleteSobreResultSetPorCampo(OperacionesBBDD bbdd, int dept_no)throws SQLException, InterruptedException{
        try{
            int deptNoAEliminar = dept_no;
            Departamento departamento  = new Departamento();
            Optional<ResultSet> rs = departamento.selectAll(bbdd);

            System.out.println("======================MOSTRAR ANTES DEL DELETE================================");
            departamento.mostrarResultado(rs);
            
            rs.get().beforeFirst();
            boolean encontrado = false;
        
            while (rs.get().next() && encontrado == false) {
                if (rs.get().getInt(1) == deptNoAEliminar) {
                    rs.get().deleteRow(); 
                    encontrado = true;
                }
            }

            if (!encontrado) {
                System.out.println("No se encontró el departamento");
            }
        
            System.out.println("\n=========================MOSTRAR DESPUES DEL DELETE==================================");
            rs.get().beforeFirst();
            departamento.mostrarResultado(rs);
            System.out.println("Fila con DEPT_NO " + deptNoAEliminar + " eliminada correctamente");

        
        } catch (SQLException ex) {
            System.getLogger(Conexionoracle.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            throw ex;
        }
    }
    
    public static void deleteSobreResultSetPorObjeto(OperacionesBBDD bbdd, Departamento dept)throws SQLException, InterruptedException{
        try{
            int dept_no = dept.getDept_no();
            String nombreDepartamento = dept.getDnombre();
            String localidad = dept.getLoc();
            
            Optional<ResultSet> rs = dept.selectAll(bbdd);

            System.out.println("======================MOSTRAR ANTES DEL DELETE================================");
            dept.mostrarResultado(rs);
            
            rs.get().beforeFirst();
            boolean encontrado = false;
        
            while (rs.get().next() && encontrado == false) {
                if ((rs.get().getInt(1) == dept_no) && (rs.get().getString(2).equals(nombreDepartamento)) && (rs.get().getString(3).equals(localidad))) {
                    rs.get().deleteRow(); 
                    encontrado = true;   
                }
            }

            if (!encontrado) {
                System.out.println("No se encontró el departamento");
            }
        
            System.out.println("\n=========================MOSTRAR DESPUES DEL DELETE==================================");
            rs.get().beforeFirst();
            dept.mostrarResultado(rs);
            System.out.println("Departamento eliminado correctamente");

        
        } catch (SQLException ex) {
            System.getLogger(Conexionoracle.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            throw ex;
        }
    }

    public static void updateSobreResultSetPorCampo(OperacionesBBDD bbdd, String nombreDepartamento) throws SQLException, InterruptedException{
        try{
            Departamento dept = new Departamento();
            Optional<ResultSet> rs = dept.selectAll(bbdd);

            System.out.println("======================MOSTRAR ANTES DEL UPDATE================================");
            dept.mostrarResultado(rs);
            
            
            rs.get().beforeFirst();
            while(rs.get().next()){
                rs.get().updateString("dnombre", nombreDepartamento);
                rs.get().updateRow();
            }
        
            System.out.println("\n=========================MOSTRAR DESPUES DEL UPDATE==================================");
            rs.get().beforeFirst();
            dept.mostrarResultado(rs);
        
        } catch (SQLException ex) {
            System.getLogger(Conexionoracle.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            throw ex;
        }
    }
     
     
     


}
