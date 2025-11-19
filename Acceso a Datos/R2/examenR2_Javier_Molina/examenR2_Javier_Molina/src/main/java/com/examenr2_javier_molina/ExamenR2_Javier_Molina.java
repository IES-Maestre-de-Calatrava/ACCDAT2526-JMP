/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.examenr2_javier_molina;



import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

import com.examenr2_javier_molina.modelo.OperacionesBBDD;
import com.examenr2_javier_molina.modelo.Viajes;
import controlador.ControlConversor;
import vista.InterfazVista;
import vista.VentanaConversorTexto;

/**
 *
 * @author Javier Molina-Prados
 */
public class ExamenR2_Javier_Molina {
    
    private static OperacionesBBDD bbdd = OperacionesBBDD.getInstancia();

    public static void main(String[] args) throws SQLException, InterruptedException {
        
        Viajes viaje = new Viajes();
        InterfazVista vista = new VentanaConversorTexto();
        ControlConversor controlador = new ControlConversor(vista, viaje, bbdd);
        
    }
}
