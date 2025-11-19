/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package vista;

import com.examenr2_javier_molina.modelo.Viajes;
import controlador.ControlConversor;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Scanner;
import javax.xml.transform.TransformerException;

/**
 *
 * @author Javier Molina-Prados
 * Created on 11 oct 2025
 */
public class VentanaConversorTexto implements InterfazVista {
    
    private ControlConversor controlador;
    private BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    
    
    @Override
    public void setControlador(ControlConversor c) {
        controlador = c;
    }

    @Override
    public void arranca() {
        try {
            realizarOperacion();
        } catch (IOException ex) {
            System.getLogger(VentanaConversorTexto.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (TransformerException ex) {
            System.getLogger(VentanaConversorTexto.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (SQLException ex) {
            System.getLogger(VentanaConversorTexto.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (InterruptedException ex) {
            System.getLogger(VentanaConversorTexto.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    @Override
    public String getRuta() {
        System.out.println("Direccion de la ruta: ");
        return leerRuta(); 
    }

    @Override
    public String getNombre() {
        System.out.println("Nombre de la carpeta:");
        return leerRuta();     
    }
    
    public void escribirMenu(){
        System.out.println("=========================================================");
        System.out.println("0. SALIR");
        System.out.println(MOSTRAR_VIAJES);
        System.out.println(MODIFICAR_FECHAS_VIAJE);
        System.out.println(RETRASO_FECHAS); 
        System.out.println("==========================================================");
    }
    
    public int leerOpcion(){
        String s = null;
        try{
            s = buffer.readLine();
            return Integer.parseInt(s);
        }catch(Exception e){
            System.out.println("Opcion incorrecta");
            return 0;
        }
    }
    
    public String leerRuta(){
        String s = null;
        try{
            s = buffer.readLine();
            return s;
        }catch(IOException e){
            System.out.println("Error en la cadena introducida");
            return leerRuta();
        }
    }
    
    public void realizarOperacion() throws IOException, TransformerException, SQLException, InterruptedException{
        int operacion;
        escribirMenu();
        operacion = leerOpcion();
        
        switch(operacion){
            case 1 -> controlador.ActionPerformed(new ActionEvent(this, operacion, MOSTRAR_VIAJES));
            case 2 -> controlador.ActionPerformed(new ActionEvent(this, operacion, MODIFICAR_FECHAS_VIAJE));
            case 3 -> controlador.ActionPerformed(new ActionEvent(this, operacion, RETRASO_FECHAS));
            
            case 0 -> {
                System.out.println("Adios");
                System.exit(0);
            }
        }
    }
    
    
    public void operacionIncorrecta() throws IOException, TransformerException, SQLException, InterruptedException{
        System.out.println("Operacion Incorrecta");
        realizarOperacion();
    }
    
    public String pedirIdCrucero(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce el id del crucero: ");
        String id = scanner.nextLine();
        return id;
    }
    
    public Viajes pedirViaje(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce el id del crucero: ");
        String id = scanner.nextLine();
        
        System.out.println("Introduce el origen: ");
        String origen = scanner.nextLine();

        System.out.println("Introduce el destino: ");
        String destino = scanner.nextLine();

        System.out.println("Introduce la fecha de salida: ");
        String fechaSalida = scanner.nextLine();
        
        System.out.println("Introduce la fecha de llegada:  ");
        String fechaLlegada = scanner.nextLine();
        
        System.out.println("Introduce el precio:  ");
        int precio = scanner.nextInt();
        
        Viajes viaje = new Viajes(id, origen, destino, fechaSalida, fechaLlegada, precio);
        
        return viaje;
    }
    
    public int pedirRetraso(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce los dias de retraso: ");
        int dias = scanner.nextInt();
        return dias;
    }

    @Override
    public Date pedirSalida() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce la nueva fecha de salida: ");
        String fechaSalida = scanner.nextLine();
        
        Viajes v = new Viajes();
        return v.convertirFecha(fechaSalida);
    }

    @Override
    public Date pedirLlegada() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce la nueva fecha de llegada: ");
        String fechaLlegada = scanner.nextLine();
        
        Viajes v = new Viajes();
        return v.convertirFecha(fechaLlegada);
    }
    
    public String pedirOrigen(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce el origen: ");
        String origen = scanner.nextLine();
        return origen;
    }
    
    public String pedirDestino(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce el destino: ");
        String destino = scanner.nextLine();
        return destino;
    }

    
    
    
}
