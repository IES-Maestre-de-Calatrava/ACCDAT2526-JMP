/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package fileaccesoaleatorio;

import modelo.Empleado;
import modelo.LecturaEscritura;

/**
 *
 * @author Javier Molina-Prados
 */
public class Fileaccesoaleatorio {

    public static void main(String[] args) {
        
        //Empleado emple = new Empleado("Ruiz",1,3000);
        //Empleado emple = new Empleado("Garcia",2,2000);
        Empleado emple = new Empleado("Lopez",3,4000);
        
        LecturaEscritura le = new LecturaEscritura(".\\archivos_pruebas\\aleatorio.dat");
        
       // le.escribirEmpleadoFinalArchivo(emple);
       //Empleado emple = le.lecturaEmpleado(1);
       //System.out.println(emple.toString());
       
       le.escribirEmpleado(3, emple);
       le.leerTodoEmpleado();
    }
}
