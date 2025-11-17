/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package modelo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Properties;

/**
 *
 * @author Javier Molina
 * Created on 20 oct 2025
 */
public class OperacionesBBDD {
    private final String driver = "oracle.jdbc.driver.OracleDriver";
    private final String urlconnection = "jdbc:oracle:thin:@localhost:1521/FREEPDB1";
    
    private Connection conexion = null;
    private Properties propiedades = null;
    
    private PreparedStatement preparedStatement;
    
    
    // Método para abrir la conexión, constructor
    private OperacionesBBDD() {
        if (this.conexion == null) {
            try {
                this.propiedades = new Properties();
                this.propiedades.setProperty("user", "EXAMEN_VACIO"); //aqui pongo el nombre de usuario de mi conexion
                this.propiedades.setProperty("password", "examen_pass"); //aqui pongo la constraseña de mi conexion
                
                Class.forName(driver);
                
                this.conexion = DriverManager.getConnection(urlconnection, propiedades);
            } catch (ClassNotFoundException ex) {
                System.getLogger(OperacionesBBDD.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            } catch (SQLException ex) {
                System.getLogger(OperacionesBBDD.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        }
    }
    
    
    
    // Getter para obtener la conexión actual
    public Connection getConexion() {
        return this.conexion;
    }
    
    
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
    
    
    
    private ResultSet executeQuery(String querySQL, Object ... params) throws SQLException{
        
        // el preparedStatement es como que te prepara la consulta en MEMORIA (solo se meteria una vez)
        // para que le pases solo los parametros, y tenerla de base
        preparedStatement = conexion.prepareStatement(querySQL, 
                                                            ResultSet.TYPE_SCROLL_SENSITIVE,
                                                            ResultSet.CONCUR_UPDATABLE);
       
        for(int i=0; i < params.length; i++){
            preparedStatement.setObject(i+1, params[i]);
        }
       
        return preparedStatement.executeQuery();
       
    }
   
    public Optional<ResultSet> select(String querySQL, Object ... params) throws SQLException{
        return Optional.of(executeQuery(querySQL, params));
    }
    
    
    /**
     * Inserta en una tabla con la posibilidad de que devuelva el row Id unico de oracle
     * 
     * @param insertSQL
     * @param params
     * @return
     * @throws SQLException 
     */
    public Optional<ResultSet> insert(String insertSQL, Object... params) throws SQLException{
        
        
        preparedStatement = conexion.prepareStatement(insertSQL, PreparedStatement.RETURN_GENERATED_KEYS);
        
        for(int i=0; i < params.length; i++){
            preparedStatement.setObject(i+1, params[i]);
        }
        preparedStatement.executeUpdate();
        
        return Optional.of(preparedStatement.getGeneratedKeys());
    }
    
    
    
    
    public int updateDeleteQuery(String genericSQL, Object... params) throws SQLException{
            preparedStatement = conexion.prepareStatement(genericSQL);
            
            for(int i = 0; i < params.length; i ++){
                preparedStatement.setObject(i+1, params[i]);
            } 
            return preparedStatement.executeUpdate();
        
    }
    
    /**
     * Este metodo devuelve el numero de filas, pero solo sirve para resultSets estáticos
     * @param rs
     * @return
     * @throws SQLException 
     */
    public static int numeroFilasResultSet(ResultSet rs) throws SQLException{
        int rows = 0;
        
        try {
            if (rs.last()) {
                rows = rs.getRow();
                rs.beforeFirst();
            }
        } catch (SQLException ex) {
            System.getLogger(OperacionesBBDD.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            throw ex;
        }
        
        return rows;
    }
    
    
     public static boolean compararFechas(LocalDate fecha1, LocalDate fecha2){
        if (fecha1.equals(fecha2)){
            return true;
        }else{
            return false;
        }
    }
    
    
    
}
