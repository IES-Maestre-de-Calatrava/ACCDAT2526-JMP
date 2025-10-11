/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package filestreambytes;

import java.util.ArrayList;
import modelo.LecturaEscritura;
import modelo.LecturaEscrituraArrays;
import modelo.LecturaEscrituraDatosPrimitivos;
import modelo.LecturaEscrituraObjetos;
import modelo.objetos.Departamento;
import modelo.objetos.Empleado;

/**
 *
 * @author Javier Molina-Prados
 */
public class Filestreambytes {

    public static void main(String[] args) {
       LecturaEscrituraObjetos lecturaEscrituraObjetos = new LecturaEscrituraObjetos(".\\archivos_prueba\\archivo_con_objetos.dat");
       
       
       
       Departamento departamento1= new Departamento("Cormecial",1);
       lecturaEscrituraObjetos.escribirObjetos(departamento1);
       
       Departamento departamento2= new Departamento("Marketing",2);
       lecturaEscrituraObjetos.escribirObjetos(departamento2);

       
       
        Empleado emple = new Empleado("Luis", "García", 33, 3000, departamento1);
        lecturaEscrituraObjetos.escribirObjetos(emple);
       
        emple = new Empleado("Ana", "Robles", 30, 3100, departamento2);
        lecturaEscrituraObjetos.escribirObjetos(emple);
       
       
         ArrayList<Departamento> departamentos = lecturaEscrituraObjetos.convertirArrayList(lecturaEscrituraObjetos.lecturaObjetosUniversal(),Departamento.class);

        for(Departamento depart: departamentos) {
            System.out.println(depart.toString());            
        }
       
        
       
        ArrayList<Empleado> empleados = lecturaEscrituraObjetos.convertirArrayList(lecturaEscrituraObjetos.lecturaObjetosUniversal(),Empleado.class);
       
        for(Empleado empleado : empleados){
            if(empleado.getNombre().equals("Luis")){
                System.out.println(empleado.toString());
            }    
        }
       
    }
}
    

