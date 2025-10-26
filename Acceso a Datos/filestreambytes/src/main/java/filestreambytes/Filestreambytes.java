/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package filestreambytes;

import controlador.Controlador;
import java.util.ArrayList;
import modelo.LecturaEscritura;
import modelo.LecturaEscrituraArrays;
import modelo.LecturaEscrituraDatosPrimitivos;
import modelo.LecturaEscrituraObjetos;
import modelo.objetos.Departamento;
import modelo.objetos.Empleado;
import vista.InterfazVista;
import vista.VentanaConversorTexto;

/**
 *
 * @author Javier Molina-Prados
 */
public class Filestreambytes {

    public static void main(String[] args) {
        
       /*
       LecturaEscrituraObjetos lecturaEscrituraObjetos = new LecturaEscrituraObjetos(".\\archivos_prueba\\archivo_con_objetos.dat");
       
       
       
       Departamento departamento1= new Departamento("Cormecial",1);
       lecturaEscrituraObjetos.escribirObjetos(departamento1);
       
       Departamento departamento2= new Departamento("Marketing",2);
       lecturaEscrituraObjetos.escribirObjetos(departamento2);

       
       
        Empleado emple = new Empleado("Luis", "García", 33, 3000, departamento1);
        lecturaEscrituraObjetos.escribirObjetos(emple);
       
        emple = new Empleado("Ana", "Robles", 30, 3100, departamento2);
        lecturaEscrituraObjetos.escribirObjetos(emple);
       
       
        // Leer solo una vez
        ArrayList<Object> objetosLeidos = lecturaEscrituraObjetos.lecturaObjetosUniversal();

        // Convierto según tipo
        ArrayList<Departamento> departamentos =
            LecturaEscrituraObjetos.convertirArrayList(objetosLeidos, Departamento.class);

        ArrayList<Empleado> empleados =
            LecturaEscrituraObjetos.convertirArrayList(objetosLeidos, Empleado.class);

        // Mostrar resultados por tipo
        // Departamentos
        for (Departamento depart : departamentos) {
            System.out.println(depart);
        }
        
        // Empleados
        for (Empleado empleado : empleados) {
            System.out.println(empleado);
        }
        */
       
        LecturaEscritura                    lecturaEscritura                = new LecturaEscritura(".\\ejemploprueba.dat");
        LecturaEscrituraArrays              lecturaEscrituraArrays          = new LecturaEscrituraArrays(".\\ejemplopruebaDatosArrays.dat");
        LecturaEscrituraDatosPrimitivos     lecturaEscrituraDatosPrimitivos = new LecturaEscrituraDatosPrimitivos(".\\ejemplopruebaDatosPrimitivos.dat"); 
        LecturaEscrituraObjetos             lecturaEscrituraObjetos         = new LecturaEscrituraObjetos(".\\archivo_con_objetos.dat");
        
        InterfazVista vista = new VentanaConversorTexto();
        
        Controlador controlador = new Controlador(vista,lecturaEscritura,lecturaEscrituraArrays,lecturaEscrituraDatosPrimitivos,lecturaEscrituraObjetos);
    }
}

    /*
    .\\archivos_prueba\\ejemploprueba.dat
    .\\archivos_prueba\\ejemplopruebaDatosArrays.dat
    .\\archivos_prueba\\ejemplopruebaDatosPrimitivos.dat
    .\\archivos_prueba\\archivo_con_objetos.dat
    */

