/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package vista;

import controlador.Controlador;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import modelo.Empleado;

/**
 *
 * @author Javier Molina-Prados
 * Created on 11 oct 2025
 */
public class VentanaConversorTexto implements InterfazVista {
    
    private Controlador controlador;
    private BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    private Scanner sc = new Scanner(System.in);

    
    @Override
    public void setControlador(Controlador c) {
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
        System.out.println("1. Escribir un empleado al final del archivo");
        System.out.println("2. Escribir un empleado en el fichero según el identificador");
        System.out.println("3. Lee un empleado en el fichero según el identificador");
        System.out.println("4. Borrar un empleado según el identificador");
        System.out.println("5. Leer todos los registros del fichero");
        System.out.println("6. Modificar el nombre de un empleado del fichero");
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
            case 1 -> controlador.ActionPerformed(new ActionEvent(this, operacion, ESCRIBE_EMPLEADO_AL_FINAL));
            case 2 -> controlador.ActionPerformed(new ActionEvent(this, operacion, ESCRIBE_EMPLEADO_POR_ID));
            case 3 -> controlador.ActionPerformed(new ActionEvent(this, operacion, LEE_EMPLEADO_POR_ID));
            case 4 -> controlador.ActionPerformed(new ActionEvent(this, operacion, BORRAR));
            case 5 -> controlador.ActionPerformed(new ActionEvent(this, operacion, LEER_FICHERO));
            case 6 -> controlador.ActionPerformed(new ActionEvent(this, operacion, CAMBIAR_NOMBRE));
           

            
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
    

    // Método para pedir los nombres
    public String[] obtenerApellido() {
        System.out.print("¿Cuántas personas quieres ingresar?: ");
        int cantidad = sc.nextInt();
        sc.nextLine(); // limpiar salto de línea

        String[] nombres = new String[cantidad];

        for (int i = 0; i < cantidad; i++) {
            System.out.print("Nombre de la persona " + (i + 1) + ": ");
            nombres[i] = sc.nextLine();
        }

        return nombres;
    }
    
    
    public Empleado pedirEmpleado() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Introduce un identificador: ");
        long identificador = scanner.nextLong();
        scanner.nextLine(); // limpiar salto de línea

        System.out.println("Introduce un apellido: ");
        String apellido = scanner.nextLine();

        System.out.println("Introduce un departamento: ");
        int departamento = scanner.nextInt();
        scanner.nextLine(); // limpiar salto de línea

        System.out.println("Introduce un salario: ");
        double salario = scanner.nextDouble();

        Empleado empleado = new Empleado(identificador, apellido, departamento, salario);
        return empleado;
    }

    
    public long pedirIdentificador(){
        Scanner scanner = new Scanner (System.in);
        
        System.out.println("Introduce un identificador: ");
        long identificador = scanner.nextLong();
        
        return identificador;
    }
    
    
    public String pedirApellido(){
        Scanner scanner = new Scanner (System.in);
        
        System.out.println("Introduce un apellido: ");
        String apellido = scanner.nextLine();
        
        return apellido;
    }

    
    public void mostrarEmpleado(Empleado emple) {
    System.out.println("===== EMPLEADO =====");
    System.out.println("Identificador: " + emple.getIdentificador());
    System.out.println("Apellido: " + emple.getApellido());
    System.out.println("Departamento: " + emple.getDepartamento());
    System.out.println("Salario: " + emple.getSalario());
    System.out.println("====================");
    }
    
    public void mostrarMensaje(String mensaje) {
    System.out.println(mensaje);
}
    
}




    


