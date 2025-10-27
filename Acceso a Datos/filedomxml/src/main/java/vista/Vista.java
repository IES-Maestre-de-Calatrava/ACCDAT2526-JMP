/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import controlador.Control;
import java.util.List;
import java.util.Scanner;
import modelo.Empleado;

/**
 *
 * @author belen
 */
public class Vista {
    private Control control;
    private Scanner sc;
    
    public Vista(String nombreRaiz){
        control = new Control(nombreRaiz);
        sc = new Scanner(System.in);
    }
    
    /**
     * Muestra el menu para elegir la opcion
     */
    private void mostrarMenu(){
        System.out.println("Menu DOM XML");
        System.out.println("1. Añadir empleado");
        System.out.println("2. Mostrar empleados");
        System.out.println("3. Guardar archivo");
        System.out.println("4. Cargar archivo");
        System.out.println("5. Modificar salario");
        System.out.println("6. Eleminar campo de empleados");
        System.out.println("7. Añadir campo 'Cargo' por defecto");
        System.out.println("0. Salir");
        System.out.println("Selecciona una opcion: ");
    }
    
    /**
     * Agrega a un empleado
     */
    private void agregarEmpleado(){
        System.out.print("ID: ");
        int id = Integer.parseInt(sc.nextLine());
        
        System.out.print("Apellido: ");
        String apellido = sc.nextLine();
        
        System.out.print("Departamento: ");
        int departamento = Integer.parseInt(sc.nextLine());
        
        System.out.print("Salario: ");
        double salario = Double.parseDouble(sc.nextLine());
        
        control.agregarEmpleado(new Empleado(id, apellido, departamento, salario));
    }
    
    /**
     * Muestra los empleados
     */
    private void mostrarEmpleados(){
        List<Empleado> lista = control.obtenerEmpleados();
        if(lista.isEmpty()){
            System.out.println("No hay empleados cargados");
        } else{
            lista.forEach(System.out::println);
        }
    }
    
    /**
     * Guarda un archivo en la ruta que den
     */
    private void guardarArchivo(){
        System.out.print("Ruta del archivo XML: ");
        String ruta = sc.nextLine();
        control.guardarArchivo(ruta, "yes");
    }
    
    /**
     * Carga un archivo de una ruta
     */
    private void cargarArchivo(){
        control.cargarArchivo(".\\archivos_prueba\\empleados.xml");
    }
    
    /**
     * Modifica el salario de un empleado dado por ID
     */
    private void modificarSalario(){
        System.out.print("ID del empleado: ");
        int id = Integer.parseInt(sc.nextLine());
        
        System.out.print("Nuevo salario: ");
        double salario = Double.parseDouble(sc.nextLine());
        
        control.modificarSalario(id, salario);
    }
    
    /**
     * Elimina el campo hijo de un campo padre dado
     */
    private void eliminarCampo(){
        System.out.print("Elemento padre: ");
        String padre = sc.nextLine();
        
        System.out.print("Elemento hijo: ");
        String hijo = sc.nextLine();
        
        control.eleminarCampo(padre, hijo);
    }
    
    /**
     * Añade el cargo a los empleados
     */
    private void añadirCargo(){
        control.añadirCargoPorDefecto();
        System.out.println("Campo 'Cargo' añadido a todos los empleados");
    }
    
    public void iniciar(){
        int op;
        do{
            mostrarMenu();
            op = Integer.parseInt(sc.nextLine());
            
            switch(op){
                case 1 -> agregarEmpleado();
                case 2 -> mostrarEmpleados();
                case 3 -> guardarArchivo();
                case 4 -> cargarArchivo();
                case 5 -> modificarSalario();
                case 6 -> eliminarCampo();
                case 7 -> añadirCargo();
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opcion no valida");
            }
        } while(op != 0);
    }
}
