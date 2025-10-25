/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Properties;

/**
 *
 * @author b15-11m
 * Created on 20 oct 2025
 */
public class OperacionesBBDD {
    private final String driver = "oracle.jdbc.driver.OracleDriver";
    private final String urlconnection = "jdbc:oracle:thin:@localhost:1521/FREEPDB1";
    
    private Connection conexion = null;
    private Properties propiedades = null;
    
    private PreparedStatement preparedStatement;
    
    // Instancia Singleton
    private static OperacionesBBDD instance;
    
    // Método para obtener la instancia única
    public static OperacionesBBDD getInstancia() {
        try {
            if (instance == null || instance.getConexion().isClosed()) {
                instance = new OperacionesBBDD();
            }
        } catch (SQLException ex) {
            System.getLogger(OperacionesBBDD.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return instance;
    }
    
    // Método para abrir la conexión
    private OperacionesBBDD() {
        if (this.conexion == null) {
            try {
                this.propiedades = new Properties();
                this.propiedades.setProperty("user", "dam2");
                this.propiedades.setProperty("password", "dam2");
                
                Class.forName(driver);
                
                this.conexion = DriverManager.getConnection(urlconnection, propiedades);
            } catch (ClassNotFoundException ex) {
                System.getLogger(OperacionesBBDD.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            } catch (SQLException ex) {
                System.getLogger(OperacionesBBDD.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        }
    }
    
    // Método para cerrar la conexión
    public void cierraConexion() {
        if (this.conexion != null) {
            try {
                this.conexion.close();
                this.conexion = null;  // Para permitir volver a conectar luego
            } catch (SQLException ex) {
                System.getLogger(OperacionesBBDD.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        }
    }
    
    // Getter para obtener la conexión actual
    public Connection getConexion() {
        return this.conexion;
    }
    
    /*
    public void selectDept(int dep, Connection conn){
        
        try {
            String sql = "SELECT * FROM departamentos WHERE dept_no=?";
        
            PreparedStatement sentencia = conn.prepareStatement(sql);

            sentencia.setInt(1, dep);
            ResultSet rs = sentencia.executeQuery();
            
            rs.close();
            sentencia.close();
        } catch (SQLException ex) {
            System.getLogger(OperacionesBBDD.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
    */
    
    private ResultSet executeQuery(String querySQL, Object ... params) throws SQLException{
        
        preparedStatement = conexion.prepareStatement(querySQL);
       
        for(int i=0; i < params.length; i++){
            preparedStatement.setObject(i+1, params[i]);
        }
       
        return preparedStatement.executeQuery();
       
    }
   
    public Optional<ResultSet> select (String querySQL, Object ... params) throws SQLException{
        return Optional.of(executeQuery(querySQL, params));
    }
    
    
    public void insert(String insertSQL, Object ... params) throws SQLException{
        
        preparedStatement = conexion.prepareStatement(insertSQL, PreparedStatement.RETURN_GENERATED_KEYS);
        
        for(int i=0; i < params.length; i++){
            preparedStatement.setObject(i+1, params[i]);
        }
       
        preparedStatement.executeUpdate();
    }


}
