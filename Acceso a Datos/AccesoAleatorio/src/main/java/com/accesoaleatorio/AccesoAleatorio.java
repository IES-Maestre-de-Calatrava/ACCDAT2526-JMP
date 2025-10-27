/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.accesoaleatorio;

import controlador.Controlador;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import modelo.Empleado;
import modelo.LecturaEscritura;
import vista.InterfazVista;
import vista.VentanaConversorTexto;

/**
 *
 * @author Javier Molina-Prados
 */
public class AccesoAleatorio {

    public static void main(String[] args) throws FileNotFoundException, IOException {
       
        
        //      .\\pruebas_acceso_aleatorio
        LecturaEscritura lecturaEscritura = new LecturaEscritura(".\\pruebas_acceso_aleatorio");
        
        InterfazVista vista = new VentanaConversorTexto();
        
        Controlador control = new Controlador(vista, lecturaEscritura);
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        /*
        LecturaEscritura lec = new LecturaEscritura(".\\pruebaEmpleados.dat");
        
        
        Empleado emple1 = new Empleado(1, "Lopez", 1, 200.3);
        Empleado emple50 = new Empleado(50, "Ruiz", 10, 123.44);
        Empleado emple2 = new Empleado(2, "Sanchez", 5, 999.99);
        Empleado emple4 = new Empleado(4, "Perez", 5, 333.444);

    
        lec.escribirEmpleadoPorId(emple1);
        lec.escribirEmpleadoPorId(emple50);
        lec.escribirEmpleadoPorId(emple2);
        lec.escribirEmpleadoFinal(emple4);

        
        ArrayList <Empleado> empleados = new ArrayList<>();
        empleados.add(emple1);
        empleados.add(emple50);
        empleados.add(emple2);
        empleados.add(emple4); // este está al final, al leer empleado (segun el identificador) me va a marcar 0 en todo, pq ahi no hay nada
        
        // Aqui me lo lee bien, porque he usado el añadir por id, si uso el añadir al final
        // no me va a salir porque me va a buscar por id, entonces el id que le ponga no va 
        // a coincidir, me va a mostrar los datos de un empleado con un id en el que no hay nada
        
        for(Empleado emple: empleados){
            
            System.out.println(lec.lecturaEmpleado(emple.getIdentificador()));
        }
        
        lec.borrarEmpleado(2);
        System.out.println();
        System.out.println();

        
        for(Empleado emple: empleados){
            
            System.out.println(lec.lecturaEmpleado(emple.getIdentificador()));
        }
        
        
        System.out.println();
        System.out.println();
        
        
        System.out.println(lec.leerTodoEmpleado());
        
        lec.cambiarNombreEmpleado("martin", 1);
        System.out.println(lec.leerTodoEmpleado());

        
        
        
               

        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        /*
        
        // Escribimos en él
        fichero.writeInt(33);
        StringBuffer buffer = new StringBuffer("GARCIA");
        buffer.setLength(10); // ponemos que la longitud máxima del string sea 10, si es mayor, añade los primeros 10 caracteres
        fichero.writeChars(buffer.toString());
        fichero.writeDouble(1000.33);
        
        // Cerramos el flujo
        fichero.close();
        
        
        // Para añadir registros a partir del ultimo insertado
        long posicion = fichero.length();
        
        // esto situa el puntero en posicion = que es la ultima posicion del fichero
        fichero.seek(posicion);
        
        */
        /*
        // Abrimos el fichero
        File ficheroPrueba = new File(".\\ejemploprueba.dat");
        
        
        // Creamos el flujo de escritura
        RandomAccessFile fichero = new RandomAccessFile(ficheroPrueba, "rw");
        
        
        
        String apellido15 = "GARCIA";                         // 20 BYTES
        String apellido15Padder = padString(apellido15, 10);
        Double salario15 = 1300.0;                            // 8 BYTES
        long identificador15 = 15;                            // 8 BYTES
        int dept15 = 15;                                      // 4 BYTES
                                                              // 40 BYTES TOTALES
        
                                                            
        // Queremos añadir los registros a partir del identificador                                                     
        long posicion15 = (identificador15-1)*40;       //Usamos la formula (n-1)*NºTotalBytes
        fichero.seek(posicion15);
        fichero.writeLong(identificador15);
        fichero.writeChars(apellido15);
        fichero.writeInt(dept15);
        fichero.writeDouble(salario15);
        
        
        String apellido14= "RUIZ";                         // 20 BYTES
        String apellido14Padder = padString(apellido14, 10);
        Double salario14 = 1300.0;                         // 8 BYTES
        long identificador14 = 14;                         // 8 BYTES
        int dept14 = 14;                                   // 4 BYTES
                                                           // 40 BYTES TOTALES
                                                           
        
        // Queremos añadir los registros a partir del identificador                                                     
        long posicion14 = (identificador14-1)*40;       //Usamos la formula (n-1)*NºTotalBytes
        fichero.seek(posicion14);
        fichero.writeLong(identificador14);
        fichero.writeChars(apellido14);
        fichero.writeInt(dept14);
        fichero.writeDouble(salario14);                                                  
                                             
        
        
        // Ahora que pasaría si quisieramos modificar algun valor por ejemplo el departamento y el salario
        
        // 1º Calculamos la posicion de inicio, imaginando que vamos a cambiar los datos del registro 15
        // long posicion = (identificador15-1)*40;
        
        
        // Nos saltamos los campos que no haya que modificar en este caso el identificador (8 BYTES) y el apellido (20 BYTES)
        posicion15 = posicion15 + 8 + 20;
        
        // Colocamos el puntero y modificamos
        // DATOS NUEVOS
        
        fichero.seek(posicion15);
        fichero.writeInt(30);
        fichero.writeDouble(1500.25);
        
        
        
        
        // AHORA VAMOS A LEER LOS REGISTROS
        // PARA RECORRER LOS REGISTROS SOLO NECESITAMOS EL IDENTIFICADOR Y LA LONGITUD DEL REGISTRO
        
        // Obtenemos el identificador
        //long identificador14 = 14;
        
        // Calculamos la posicion en funcion del tamaño
        long posicion = (identificador14-1)*40;
        
        // Verificamos que la posicion no sea mayor que el archivo
        if (posicion >= fichero.length()) {
            System.out.println("ID: "+ identificador14 + " no existe empleado...");
        }
        
        
        // Colocamos el puntero y guardamos las lecturas en variables que imprimiremos después
        fichero.seek(posicion);
        long id = fichero.readLong();
        
        
        // Leemos el campo Apellido (20 BYTES). NO USAR readUTF(). Hay que usar un bucle que lea caracteres, que es como lo declare
        StringBuilder apellidoBuilder = new StringBuilder();
        // Necesitas leer exactamente 10 caracteres (10 * 2 bytes = 20 bytes)
        for (int i = 0; i < 10; i++) { 
            apellidoBuilder.append(fichero.readChar()); 
        }
        // Quitamos los posibles espacios de relleno si el apellido era más corto que 10 caracteres (ej. "RUIZ" tiene 4, se rellenó con 6 espacios).
        String apellido = apellidoBuilder.toString().trim();
            
            
        // finalmento los otros tipos
        int departamento = fichero.readInt();
        Double salario =  fichero.readDouble();  
        
        
        System.out.println("Identificador14: " + id + ", apellido14: " + apellido + ", departamento14: " + departamento + ", salario: " + salario);
        
        // Verificamos que hemos llegado al final del fichero
        if (fichero.getFilePointer() == fichero.length());
        
        // Cerramos
        fichero.close();
        
        
        
        
        
        
        
        
        
        
    }
    
    
    // Puedes añadir esto a una clase de utilidades o en la misma clase donde escribes.
    public static String padString(String s, int length) {
        // Si la cadena es más larga, la cortamos (aunque es mejor lanzar una excepción)
        if (s.length() > length) {
            return s.substring(0, length); 
        }
        // Si la cadena es más corta, la rellenamos con espacios
        StringBuilder sb = new StringBuilder(s);
        while (sb.length() < length) {
            sb.append(' '); // Añade espacios hasta la longitud requerida (10)
        }
        return sb.toString();
    
    */
     
    } 
    
}
