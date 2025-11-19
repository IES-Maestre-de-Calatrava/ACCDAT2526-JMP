/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package modelo;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

/**
 *
 * @author Javier Molina-Prados
 * Created on 17 nov 2025
 */
public class Viajes {
    
    private String idCrucero;
    private String origen;
    private String destino;
    private Date fechaSalida;
    private Date fechaLlegada;
    private double precio;
    
    private final static String CONSULTA_SELECT_ALL = "SELECT v.* FROM Viajes v";
    private final static String CONSULTA_SELECT_BY_ID = "SELECT v.* FROM Viajes e WHERE v.id_crucero = ?";
    
    
    private final static String CONSULTA_SELECT_BY_DEPARTAMENTO = "SELECT e.*, d.dnombre "
                                                                + "FROM Empleados e "
                                                                + "JOIN Departamentos d ON e.dept_no = d.dept_no "
                                                                + "WHERE e.dept_no = ?";
    
    private final static String CONSULTA_SELECT_ORDER_BY_DEPT = "SELECT v.* FROM Viajes e ORDER BY v.id_crucero";
    
    private final static String INSERT = "insert into Viajes values (?,?,?,?,?,?)";
    private final static String UPDATE = "update Viajes set id_crucero=?, origen=?, destino=? where precio=?";
    private final static String DELETE = "delete from Viajes where id_crucero=?";

    
    public Viajes(){
        
    }

    public Viajes(String idCrucero, String origen, String destino, String fechaSalida, String fechaLlegada, double precio) {
        this.idCrucero = idCrucero;
        this.origen = origen;
        this.destino = destino;
        this.fechaSalida = convertirFecha(fechaSalida);
        this.fechaLlegada = convertirFecha(fechaLlegada);
        this.precio = precio;
    }

    public String getIdCrucero() {
        return idCrucero;
    }

    public String getOrigen() {
        return origen;
    }

    public String getDestino() {
        return destino;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public Date getFechaLlegada() {
        return fechaLlegada;
    }

    public double getPrecio() {
        return precio;
    }

    public void setIdCrucero(String idCrucero) {
        this.idCrucero = idCrucero;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public void setFechaLlegada(Date fechaLlegada) {
        this.fechaLlegada = fechaLlegada;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    //Por si lo necesito mas adelante
    private Date convertirFecha(String fecha) {
        try {
        // 1. Definir el formateador para "DD/MM/YYYY"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        // 2. Parsear el String a un objeto de solo fecha (LocalDate)
        LocalDate localDate = LocalDate.parse(fecha, formatter);
        
        // 3. Convertir el LocalDate a java.sql.Date.
        // ESTO ES CLAVE: Date.valueOf(LocalDate) crea un sql.Date sin problemas de hora/zona horaria.
        return java.sql.Date.valueOf(localDate); 
        
        } catch (java.time.format.DateTimeParseException e) {
            throw new IllegalArgumentException("Error de formato de fecha: " + fecha, e);
        }
    }
    
    
    public static Optional<ResultSet> selectAll(OperacionesBBDD bbdd){
        Optional<ResultSet> rs = Optional.empty();
        
        try {
            rs = bbdd.select(CONSULTA_SELECT_ALL);
        } catch (SQLException ex) {
            System.getLogger(Viajes.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        
        return rs;
    }
    
    
    
    public static void mostrarResultado(Optional<ResultSet> rsOptional){
        
        if(!rsOptional.isPresent()){
            System.out.println("No se obtuvieron resultados o hubo un error en la BBDD");
            return;
        }
        
        // Desempaquetar solo la UNA vez
        ResultSet rs = rsOptional.get();
        try {
            while (rs.next()){
                    System.out.println("Numero empleado: " + rs.getInt("emp_no") +
                        ". Apellido: " + rs.getString("apellido") + 
                        ". Oficio: " + rs.getString("oficio") + 
                        ". Dir: " + rs.getInt("dir") + 
                        ". Fecha_alt: " + rs.getString("fecha_alt") + 
                        ". Salario: " + rs.getDouble("salario") + 
                        ". Comision: " + rs.getDouble("comision") + 
                        ". Numero de departamento: " + rs.getInt("dept_no"));
                }
            
        } catch (SQLException ex) {
            System.getLogger(Viajes.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
    
    
    public String insertar(OperacionesBBDD bbdd, Viajes viaje){
        StringBuilder errores = new StringBuilder();
        
        if (this.precio < 0) {
            errores.append("ERROR: El precio debe ser una cantidad positiva (mayor que cero)\n");
        }
        
        if(viaje.getOrigen() == null || viaje.getDestino() == null){
            errores.append("ERROR: El orgien y destino, no pueden ser nulos\n");
        }
        
        if (errores.length() > 0) {
        return "Errores:\n" + errores.toString();
    }
        
        try {
            bbdd.insert(INSERT, viaje.getIdCrucero(), viaje.getOrigen(), viaje.getDestino(), viaje.getFechaSalida(), viaje.getFechaLlegada(), viaje.getPrecio());            
            return "Insercion completada correctamente: IdCrucero: "+viaje.getIdCrucero()+", Origen: "+viaje.getOrigen()+", Destino: "+viaje.getDestino()+", Fecha_Salida: "+viaje.getFechaSalida()+", Fecha_Llegada: "+viaje.getFechaLlegada()+", Precio: "+viaje.getPrecio();
        
        } catch (SQLException ex) {
            return comprobarExcepcion(ex);
        }
    }
    
    
    
    public String comprobarExcepcion(SQLException ex){
        String mensaje = "";
        
            if(ex.getErrorCode() == 1){
                mensaje = "ERROR: El idCrucero ya existe en la base de datos (Clave duplicada)";
            }
            return mensaje;
    }

}
