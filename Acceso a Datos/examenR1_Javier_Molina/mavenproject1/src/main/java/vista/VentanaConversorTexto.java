/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package vista;

import controlador.ControlConversor;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
        System.out.println("--RECEPCION--");
        System.out.println(ALTA_VEHICULO);
        System.out.println("--TALLER--");
        System.out.println(CARGAR_VEHICULO);
        System.out.println(REPARACION);
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
    
    public void realizarOperacion() throws IOException, TransformerException{
        int operacion;
        escribirMenu();
        operacion = leerOpcion();
        
        switch(operacion){
            case 1 -> controlador.ActionPerformed(new ActionEvent(this, operacion, ALTA_VEHICULO));
            case 2 -> controlador.ActionPerformed(new ActionEvent(this, operacion, CARGAR_VEHICULO));
            case 3 -> controlador.ActionPerformed(new ActionEvent(this, operacion, REPARACION));
            
            case 0 -> {
                System.out.println("Adios");
                System.exit(0);
            }
        }
    }
    
    
    public void operacionIncorrecta() throws IOException, TransformerException{
        System.out.println("Operacion Incorrecta");
        realizarOperacion();
    }
    
    public long pedirId(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce el id del coche: ");
        long id = scanner.nextLong();
        return id;
    }
    
}
