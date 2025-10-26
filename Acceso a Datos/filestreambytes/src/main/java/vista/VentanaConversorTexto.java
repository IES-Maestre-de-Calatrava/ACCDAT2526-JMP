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
import modelo.objetos.Empleado;
import modelo.objetos.Departamento;

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
        System.out.println("1. Escribir un byte en un fichero");
        System.out.println("2. Escribir una cadena en un fichero");
        System.out.println("3. Lee un fichero byte a byte");
        System.out.println("4. Lee un fichero en bloques de arrays de 5 bytes");
        System.out.println();

        
        
        System.out.println("5. Escribe en un fichero un array");
        System.out.println("6. Lee un fichero de arrays");
        System.out.println("7. Copia el archivo origen en una ruta destino");
        System.out.println();

        
        System.out.println("8. Escribe datos primitivos en un archivo");
        System.out.println("9. Lee datos primitivos de un fichero");
        System.out.println();

        
        System.out.println("10. Escribe un objeto en un fichero");
        System.out.println("11. Lee los objetos de un fichero y los devuelve en un ArrayList");
        System.out.println("12. Convierte un ArrayList en la clase de objeto determinada");
        System.out.println();

        
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
            case 1 -> controlador.ActionPerformed(new ActionEvent(this, operacion, ESCRIBE_BYTE));
            case 2 -> controlador.ActionPerformed(new ActionEvent(this, operacion, ESCRIBE_CADENA));
            case 3 -> controlador.ActionPerformed(new ActionEvent(this, operacion, LEE_BYTE_A_BYTE));
            case 4 -> controlador.ActionPerformed(new ActionEvent(this, operacion, LEE_ARRAY_DE_BYTES));
            
            
            case 5 -> controlador.ActionPerformed(new ActionEvent(this, operacion, ESCRIBIR_ARRAY));
            case 6 -> controlador.ActionPerformed(new ActionEvent(this, operacion, LEER_ARRAY));
            case 7 -> controlador.ActionPerformed(new ActionEvent(this, operacion, COPIAR_ARCHIVO_DESTINO_ORIGEN));
            
            
            case 8 -> controlador.ActionPerformed(new ActionEvent(this, operacion, ESCRIBIR_DATOS_PRIMITIVOS));
            case 9 -> controlador.ActionPerformed(new ActionEvent(this, operacion, LEER_DATOS_PRIMITIVOS));
            
            
            case 10 -> controlador.ActionPerformed(new ActionEvent(this, operacion, ESCRIBIR_OBJETOS));
            case 11 -> controlador.ActionPerformed(new ActionEvent(this, operacion, LEER_OBJETOS_UNIVERSAL));
            case 12 -> controlador.ActionPerformed(new ActionEvent(this, operacion, CONVERTIR_ARRAY_A_CLASE));


            
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
    public String[] obtenerNombres() {
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

    public int[] obtenerTelefonos() {
    System.out.print("¿Cuántos teléfonos quieres ingresar?: ");
    int cantidad = sc.nextInt();
    sc.nextLine(); // limpiar salto de línea

    int[] telefonos = new int[cantidad];

    for (int i = 0; i < cantidad; i++) {
        boolean valido = false;
        while (!valido) {
            System.out.print("Teléfono de la persona " + (i + 1) + " (solo números): ");
            String entrada = sc.nextLine();
            try {
                telefonos[i] = Integer.parseInt(entrada);
                valido = true; // si la conversión funciona, salimos del while
            } catch (NumberFormatException e) {
                System.out.println("Error: ingresa solo números válidos.");
            }
        }
    }

    return telefonos;
}


    // Método para pedir si están dados de alta
    public boolean[] obtenerAltas() {
        System.out.print("¿Cuántos estados de alta quieres ingresar?: ");
        int cantidad = sc.nextInt();
        sc.nextLine(); // limpiar salto de línea

        boolean[] altas = new boolean[cantidad];

        for (int i = 0; i < cantidad; i++) {
            System.out.print("¿La persona " + (i + 1) + " está de alta? (true/false): ");
            altas[i] = sc.nextBoolean();
        }

        sc.nextLine(); // limpiar salto de línea final
        return altas;
    }
    


    /**
     * Pide al usuario un objeto a crear (Empleado o Departamento)
     * y devuelve el objeto creado.
     */
    public Object obtenerObjeto() {
        System.out.println("¿Qué objeto deseas crear?");
        System.out.println("1. Empleado");
        System.out.println("2. Departamento");

        int opcion = 0;
        boolean valido = false;
        while (!valido) {
            System.out.print("Elige 1 o 2: ");
            String entrada = sc.nextLine();
            try {
                opcion = Integer.parseInt(entrada);
                if (opcion == 1 || opcion == 2) {
                    valido = true;
                } else {
                    System.out.println("Opción no válida. Solo 1 o 2.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: ingresa un número válido.");
            }
        }

        if (opcion == 1) {
            return obtenerEmpleado();
        } else {
            return obtenerDepartamento();
        }
    }

    // Método para crear un Departamento
    public Departamento obtenerDepartamento() {
        System.out.print("Nombre del departamento: ");
        String nombre = sc.nextLine();

        int numDept = 0;
        boolean valido = false;
        while (!valido) {
            System.out.print("Número del departamento: ");
            String entrada = sc.nextLine();
            try {
                numDept = Integer.parseInt(entrada);
                valido = true;
            } catch (NumberFormatException e) {
                System.out.println("Error: ingresa un número válido.");
            }
        }

        return new Departamento(nombre, numDept);
    }

    // Método para crear un Empleado
    public Empleado obtenerEmpleado() {
        System.out.print("Nombre del empleado: ");
        String nombre = sc.nextLine();

        System.out.print("Apellido del empleado: ");
        String apellido = sc.nextLine();

        int edad = 0;
        boolean validoEdad = false;
        while (!validoEdad) {
            System.out.print("Edad del empleado: ");
            String entrada = sc.nextLine();
            try {
                edad = Integer.parseInt(entrada);
                validoEdad = true;
            } catch (NumberFormatException e) {
                System.out.println("Error: ingresa un número válido.");
            }
        }

        double salario = 0;
        boolean validoSalario = false;
        while (!validoSalario) {
            System.out.print("Salario del empleado: ");
            String entrada = sc.nextLine();
            try {
                salario = Double.parseDouble(entrada);
                validoSalario = true;
            } catch (NumberFormatException e) {
                System.out.println("Error: ingresa un número válido.");
            }
        }

        System.out.println("Ahora ingresa los datos del departamento del empleado:");
        Departamento dept = obtenerDepartamento();

        return new Empleado(nombre, apellido, edad, salario, dept);
    }
    
   
    /**
     * Pregunta al usuario qué clase quiere usar para convertir la lista
     * Devuelve la clase correspondiente
     */
    public Class<?> obtenerClaseParaConversion() {
        System.out.println("¿A qué clase quieres convertir la lista?");
        System.out.println("1. Empleado");
        System.out.println("2. Departamento");

        int opcion = 0;
        boolean valido = false;
        while (!valido) {
            System.out.print("Elige 1 o 2: ");
            String entrada = sc.nextLine();
            try {
                opcion = Integer.parseInt(entrada);
                if (opcion == 1 || opcion == 2) {
                    valido = true;
                } else {
                    System.out.println("Opción no válida. Solo 1 o 2.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: ingresa un número válido.");
            }
        }

        return opcion == 1 ? Empleado.class : Departamento.class;
    }
}




    


