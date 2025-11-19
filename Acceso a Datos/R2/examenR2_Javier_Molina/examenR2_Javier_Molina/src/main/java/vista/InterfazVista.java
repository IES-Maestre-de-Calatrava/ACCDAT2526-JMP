/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package vista;

import com.examenr2_javier_molina.modelo.Viajes;
import controlador.ControlConversor;
import java.sql.Date;

/**
 *
 * @author Javier Molina-Prados
 */
public interface InterfazVista {
    
    // especifica el controlador que se va a encargar de procesar
    //las acciones realizadas en la vista
    void setControlador (ControlConversor c);
    
    // comienza la visualización
    void arranca();
    
    // Obtener la ruta del directorio
    String getRuta();
    
    // Obtener el nombre del directorio
    String getNombre();
    
     // Constantes para las operaciones:
    static final String MOSTRAR_VIAJES = "1. Mostrar todos los viajes de un Destino y un Origen(mostrarTodosDatos)";
    static final String MODIFICAR_FECHAS_VIAJE = "2. Modificar fechas de un viaje(modificaFila)";
    static final String RETRASO_FECHAS = "3. Restraso Fechas(llamarProcedimiento)";
  
    
    @Override
    public String toString();

    @Override
    public boolean equals(Object obj);

    @Override
    public int hashCode();
    
    public String pedirIdCrucero();
    
    public int pedirRetraso();
    
    public Viajes pedirViaje();
    
    public Date pedirSalida();
    
    public Date pedirLlegada();
    
    public String pedirOrigen();
    
    public String pedirDestino();
    
}
