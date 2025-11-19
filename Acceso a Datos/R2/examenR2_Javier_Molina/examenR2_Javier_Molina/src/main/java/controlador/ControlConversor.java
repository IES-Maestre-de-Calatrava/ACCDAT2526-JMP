/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package controlador;

import com.examenr2_javier_molina.modelo.OperacionesBBDD;
import com.examenr2_javier_molina.modelo.Viajes;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import javax.xml.transform.TransformerException;
import vista.InterfazVista;

/**
 *
 * @author Javier Molina-Prados
 * Created on 11 oct 2025
 */
public class ControlConversor {
    
    private final InterfazVista vista;
    private final Viajes modelo;
    private final OperacionesBBDD bbdd;

    public ControlConversor(InterfazVista vista, Viajes modelo, OperacionesBBDD bbdd) {
        this.vista = vista;
        this.modelo = modelo;
        this.bbdd = bbdd;

        this.vista.setControlador(this);
        this.vista.arranca();
    }
    
    public void ActionPerformed(ActionEvent e) throws IOException, TransformerException, SQLException, InterruptedException{
        
        switch(e.getActionCommand()){
            case InterfazVista.MOSTRAR_VIAJES -> {
                String p_origen = vista.pedirOrigen();
                String p_destino = vista.pedirDestino();
                modelo.mostrarTodosDatos(bbdd, p_origen, p_destino);
                
            }
            
            case InterfazVista.MODIFICAR_FECHAS_VIAJE -> { 
                String id = vista.pedirIdCrucero();
                Date fechaSalida = vista.pedirSalida();
                Date fechaLlegada = vista.pedirLlegada();
                
                modelo.modificaFila(bbdd, id, fechaSalida, fechaLlegada);
            }
            
            case InterfazVista.RETRASO_FECHAS -> {
                String id = vista.pedirIdCrucero();
                int dias = vista.pedirRetraso();
                System.out.println(modelo.llamarProcedimiento(bbdd, id, dias));
            }
            
        }
        vista.arranca();
    }
    
}
