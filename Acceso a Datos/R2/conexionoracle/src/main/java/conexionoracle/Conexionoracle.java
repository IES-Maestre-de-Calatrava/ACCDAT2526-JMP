/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package conexionoracle;

import modelo.ConexionOracle;

/**
 *
 * @author Javier Molina-Prados
 */
public class Conexionoracle {

    public static void main(String[] args) {
        
        
        ConexionOracle modelo = new ConexionOracle();
        
        modelo.conexion();
        modelo.cierraConexion();
               
    }
}
