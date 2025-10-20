package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Properties;

public class ConexionOracle {
    private final String driver = "oracle.jdbc.driver.OracleDriver";
    private final String urlconnection = "jdbc:oracle:thin:@localhost:1521/FREEPDB1";

    private Connection conexion = null;
    private Properties propiedades = null;

    // Creamos la instancia singleton y constructor privado
    private static ConexionOracle instance;
    public ConexionOracle() {
        // Constructor vacío
    }

    // Método para obtener la única instancia singleton
    public static ConexionOracle getInstance() {
        try {
            if (instance == null|| instance.getConexion().isClosed()) {
                instance = new ConexionOracle();
            }
            return instance;
        } catch (SQLException ex) {
            System.getLogger(ConexionOracle.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return instance;
    }

    // Método para abrir la conexión usando la instancia singleton
    public void conexionInstance() {
        if (this.conexion == null) {
            try {
                this.propiedades = new Properties();
                this.propiedades.setProperty("user", "dam2");
                this.propiedades.setProperty("password", "dam2");

                Class.forName(driver);

                this.conexion = DriverManager.getConnection(urlconnection, propiedades);
            } catch (ClassNotFoundException ex) {
                System.getLogger(ConexionOracle.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            } catch (SQLException ex) {
                System.getLogger(ConexionOracle.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        }
    }

    // Método para cerrar la conexión usando la instancia singleton
    public void cierraConexionInstance() {
        if (this.conexion != null) {
            try {
                this.conexion.close();
                this.conexion = null; // Por si se quiere volver a abrir luego
            } catch (SQLException ex) {
                System.getLogger(ConexionOracle.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        }
    }

    // Getter para la conexión actual
    public Connection getConexion() {
        return this.conexion;
    }

    // Métodos clásicos sin singleton (como en tu código original)
    public void conexion() {
        try {
            this.propiedades = new Properties();
            this.propiedades.setProperty("user", "dam2");
            this.propiedades.setProperty("password", "dam2");

            Class.forName(driver);

            this.conexion = DriverManager.getConnection(urlconnection, propiedades);
        } catch (ClassNotFoundException ex) {
            System.getLogger(ConexionOracle.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (SQLException ex) {
            System.getLogger(ConexionOracle.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    public void cierraConexion() {
        try {
            if (this.conexion != null) {
                this.conexion.close();
            }
        } catch (SQLException ex) {
            System.getLogger(ConexionOracle.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    public void selectDept(int dept){
        String sql = "SELECT * FROM departamentos WHERE dept_no=?";
 
        try{
         
            PreparedStatement sentencia=conexion.prepareStatement(sql);

            sentencia.setInt(1, dept);
            ResultSet rs = sentencia.executeQuery();
            rs.close();
            sentencia.close();
        
        }catch(SQLException ex){
            System.out.println(ConexionOracle.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            
        }
    }
    
    public void mostrarResultado(){
        
    }
}
