/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.examenr2_javier_molina.modelo;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
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
    private int precio;
    
    private final static String CONSULTA_SELECT_ALL = "SELECT v.* FROM Viajes v";
    private final static String CONSULTA_SELECT_BY_ID = "SELECT v.* FROM Viajes v WHERE v.id_crucero = ?";
    
    private final static String CONSULTA_SELECT_ORDER_BY_DEPT = "SELECT v.* FROM Viajes e ORDER BY v.id_crucero";
    
    private final static String INSERT = "insert into Viajes values (?,?,?,?,?,?)";
    private final static String UPDATE = "update Viajes set id_crucero=?, origen=?, destino=? where precio=?";
    private final static String DELETE = "delete from Viajes where id_crucero=?";

    
    public Viajes(){
        
    }

    public Viajes(String idCrucero, String origen, String destino, String fechaSalida, String fechaLlegada, int precio) {
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

    public int getPrecio() {
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

    public void setPrecio(int precio) {
        this.precio = precio;
    }
    
    //Por si lo necesito mas adelante
    public Date convertirFecha(String fecha) {
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
                    
                    System.out.println("idCrucero: " + rs.getString("id_crucero") +
                        ". Origen: " + rs.getString("origen") + 
                        ". Destino: " + rs.getString("destino") + 
                        ". FechaSalida: " + rs.getDate("fecha_salida") + 
                        ". FechaLlegada: " + rs.getDate("fecha_llegada") + 
                        ". Precio: " + rs.getInt("precio"));
                
            }
            
        } catch (SQLException ex) {
            System.getLogger(Viajes.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
    
    public String insertarFila(OperacionesBBDD bbdd, Viajes viaje){
        StringBuilder errores = new StringBuilder();
        
        if (viaje.getPrecio() < 0 || viaje.getPrecio() > 999) {
            errores.append("ERROR: El precio debe ser una cantidad positiva (mayor que cero) y de 3 digitos\n");
        }
        
        if(viaje.getOrigen() == null || viaje.getDestino() == null || viaje.getOrigen().isBlank() || viaje.getDestino().isBlank()){
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
    
    public String eliminarFila(OperacionesBBDD bbdd, String idCrucero){
        int i = 0;
        String mensaje = "";
        try{
            i = bbdd.updateDeleteQuery(DELETE, idCrucero);
        }catch(SQLException ex){
            return comprobarExcepcion(ex);
        }
        
        if (i == 0){
           mensaje =  "No existe el viaje";
        }
        else{
            mensaje = "Viaje eliminado correctamente";
        }      
        return mensaje;
    }
    
    public String comprobarExcepcion(SQLException ex){
        String mensaje = "";
        
            if(ex.getErrorCode() == 1){
                mensaje = "ERROR: El idCrucero ya existe en la base de datos (Clave duplicada)";
            }
            return mensaje;
    }
    
    public String mostrarFila(OperacionesBBDD bbdd, String idCrucero){
        Optional<ResultSet> rs = null;
        String mensaje = "";
        try {
            rs = bbdd.select(CONSULTA_SELECT_BY_ID, idCrucero); 
            if(rs.isPresent()){
                if (rs.get().next()) {
                    String id_crucero = rs.get().getString("id_crucero");
                    String origen = rs.get().getString("origen");
                    String destino = rs.get().getString("destino");
                    Date fecha_salida = rs.get().getDate("fecha_salida");
                    Date fecha_llegada = rs.get().getDate("fecha_llegada");
                    int precio = rs.get().getInt("precio");
                    
                    mensaje = "Id_crucero: "+ id_crucero+", origen: "+origen+", destino: "+destino+", fecha_salida: "+fecha_salida+", fecha_llegada: "+fecha_llegada+", precio: "+precio;  
                }
                else{
                    mensaje =  "Error: no se ha encontrado ningun viaje con ese id";
                }
            }
        } catch (SQLException ex) {
            mensaje = "ERROR de BBDD al buscar viaje: " + ex.getMessage();
        }
        return mensaje;
        
    }
    
    
    
    
    
    // Métodos de la segunda parte del examen
    
    public static String llamarProcedimiento(OperacionesBBDD bbdd, String idCrucero, int diasRetraso){
        CallableStatement llamada;
        String fechaSalida = null;
        String fechaLLegada = null;
        String sql = "{call p_retrasar_fechas (?,?,?,?)}";
        
        try {
            llamada = bbdd.getConexion().prepareCall(sql);
            
            llamada.setString(1, idCrucero); 
            
            llamada.setInt(2, diasRetraso); 
            
            llamada.registerOutParameter(3, Types.DATE);
            
            llamada.registerOutParameter(4, Types.DATE);
            
            llamada.executeUpdate();  // Lo ejecuto
            
            fechaSalida = llamada.getString(3);
            fechaLLegada = llamada.getString(4);
            
        } catch (SQLException ex) {
            System.getLogger(Viajes.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        
        return "FechaSalida: "+fechaSalida+", FechaLLegada: "+fechaLLegada;
        
    }
     
    public static void modificaFila(OperacionesBBDD bbdd, String idCrucero, Date fechaSalida, Date fechaLlegada) throws SQLException, InterruptedException{
        try{
            Viajes viaje = new Viajes();
            Optional<ResultSet> rs = viaje.selectAll(bbdd);

            System.out.println("======================MOSTRAR ANTES DEL UPDATE================================");
            viaje.mostrarResultado(rs);
            
            
            rs.get().beforeFirst();
            while(rs.get().next()){
                if(rs.get().getString(1).equals(idCrucero)){
                    rs.get().updateDate("fecha_salida", fechaSalida);
                    rs.get().updateDate("fecha_llegada", fechaLlegada);
                    rs.get().updateRow();
                }
            }
        
            System.out.println("\n=========================MOSTRAR DESPUES DEL UPDATE==================================");
            rs.get().beforeFirst();
            viaje.mostrarResultado(rs);
        
        } catch (SQLException ex) {
            System.getLogger(Viajes.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            throw ex;
        }
    }
     
    public static void mostrarTodosDatos(OperacionesBBDD bbdd, String p_origen, String p_destino) throws SQLException{
        
        Viajes viaje = new Viajes();
        Optional<ResultSet> rs = viaje.selectAll(bbdd);
        
        // Desempaquetar solo la UNA vez
        int numFilas = 0;
        int menorPrecio = 1000;
        String id = "";
        int contadorFilas = 0;
        
        rs.get().beforeFirst();

        try {
            while (rs.get().next()){
                String origen = rs.get().getString("origen");
                    if( (rs.get().getString(2).equals(p_origen)) && (rs.get().getString(3).equals(p_destino)) ){
                        
                        System.out.println("idCrucero: " + rs.get().getString("id_crucero") +
                            ". Origen: " + rs.get().getString("origen") + 
                            ". Destino: " + rs.get().getString("destino") + 
                            ". FechaSalida: " + rs.get().getDate("fecha_salida") + 
                            ". FechaLlegada: " + rs.get().getDate("fecha_llegada") + 
                            ". Precio: " + rs.get().getInt("precio"));
                        
                        int precioActual = rs.get().getInt("precio");
                        contadorFilas++;
                        
                        if(menorPrecio > precioActual ){
                            menorPrecio = precioActual;
                            id = rs.get().getString("id_crucero");
                        }
                    }      
            }
            
            if(contadorFilas == 0){
                System.out.println("No existen viajes con ese origen y destino");
            }else{
                System.out.println("Numero de filas: "+contadorFilas);
                System.out.println("Id del viaje mas barato: "+id+", precio: "+menorPrecio);
            }
            
        } catch (SQLException ex) {
            System.getLogger(Viajes.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
    
}