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
        System.out.println("Elige una opcion:");
        System.out.println("1. Crear carpeta en ruta dada");
        System.out.println("2. Crear carpeta en ruta dado el nombre");
        System.out.println("3. Crear carpeta dado el directorio Raiz y el nombre de la nueva carpeta");
        System.out.println("4. Crear un archivo en un directorio determinado");
        System.out.println("5. Ver contenido de un directorio");
        System.out.println("6. Borrar");
        System.out.println("7. Cambiar nombre a un archivo");
        System.out.println("8. Copiar un archivo de una ruta a otra");
        System.out.println("9. Mover un archivo de una ruta a otra");
        System.out.println("0. Salir");
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
    
    public void realizarOperacion() throws IOException{
        int operacion;
        escribirMenu();
        operacion = leerOpcion();
        
        switch(operacion){
            case 1 -> controlador.ActionPerformed(new ActionEvent(this, operacion, CREAR_CARPETA_EN_RUTA));
            case 2 -> controlador.ActionPerformed(new ActionEvent(this, operacion, CREAR_CARPETA_EN_RUTA_DADO_EL_NOMBRE));
            case 3 -> controlador.ActionPerformed(new ActionEvent(this, operacion, CREAR_CARPETA_DADO_DIRECTORIO_RAIZ));
            case 4 -> controlador.ActionPerformed(new ActionEvent(this, operacion, CREAR_ARCHIVO_EN_DIRECTORIO));
            case 5 -> controlador.ActionPerformed(new ActionEvent(this, operacion, VER_CONTENIDO_DIRECTORIO));
            case 6 -> controlador.ActionPerformed(new ActionEvent(this, operacion, BORRAR));
            case 7 -> controlador.ActionPerformed(new ActionEvent(this, operacion, CAMBIAR_NOMBRE_ARCHIVO));
            case 8 -> controlador.ActionPerformed(new ActionEvent(this, operacion, COPIAR_ARCHIVO));
            case 9 -> controlador.ActionPerformed(new ActionEvent(this, operacion, MOVER_ARCHIVO));

            
            case 0 -> {
                System.out.println("Adios");
                System.exit(0);
            }
        }
    }
    
    
    public void operacionIncorrecta() throws IOException{
        System.out.println("Operacion Incorrecta");
        realizarOperacion();
    }
    

}
