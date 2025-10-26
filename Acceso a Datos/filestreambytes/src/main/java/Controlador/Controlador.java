/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package controlador;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import modelo.LecturaEscritura;
import modelo.LecturaEscrituraArrays;
import modelo.LecturaEscrituraDatosPrimitivos;
import modelo.LecturaEscrituraObjetos;
import modelo.objetos.Departamento;
import modelo.objetos.Empleado;
import vista.InterfazVista;

/**
 *
 * @author Javier Molina-Prados
 * Created on 25 oct 2025
 */
public class Controlador {
    
    private final InterfazVista                         vista;
    private final LecturaEscritura                      lecturaEscritura;
    private final LecturaEscrituraArrays                lecturaEscrituraArrays;
    private final LecturaEscrituraDatosPrimitivos       lecturaEscrituraDatosPrimitivos;
    private final LecturaEscrituraObjetos               lecturaEscrituraObjetos;
    
    // 🔹 Lista universal de objetos
    private final ArrayList<Object> listaUniversal = new ArrayList<>();


    public Controlador(InterfazVista vista, 
                       LecturaEscritura                lecturaEscritura, 
                       LecturaEscrituraArrays          lecturaEscrituraArrays,
                       LecturaEscrituraDatosPrimitivos lecturaEscrituraDatosPrimitivos,
                       LecturaEscrituraObjetos         lecturaEscrituraObjetos) {
        
        this.vista = vista;
        this.lecturaEscritura                       = lecturaEscritura;
        this.lecturaEscrituraArrays                 = lecturaEscrituraArrays;
        this.lecturaEscrituraDatosPrimitivos        = lecturaEscrituraDatosPrimitivos;
        this.lecturaEscrituraObjetos                = lecturaEscrituraObjetos;
        
        
        this.vista.setControlador(this);
        this.vista.arranca();
    }
    
    public void ActionPerformed(ActionEvent e) throws IOException{
        String ruta = vista.getRuta();

        LecturaEscritura lecturaEscritura = new LecturaEscritura(ruta);
        LecturaEscrituraArrays lecturaEscrituraArrays = new LecturaEscrituraArrays(ruta);
        LecturaEscrituraDatosPrimitivos lecturaEscrituraDatosPrimitivos = new LecturaEscrituraDatosPrimitivos(ruta);
        LecturaEscrituraObjetos lecturaEscrituraObjetos = new LecturaEscrituraObjetos(ruta);

        switch(e.getActionCommand()){
            
            /* LECTURA ESCRITURA */
            case InterfazVista.ESCRIBE_BYTE -> {
                boolean sobreescribir;
                Scanner scanner = new Scanner(System.in);
                System.out.println("Introduce un caracter para escribir:");
                char caracter = scanner.next().charAt(0);
                System.out.println("Quieres sobreescribir los datos?  1.Si 2.No");
                char num = scanner.next().charAt(0);
                if(num == 1){
                    sobreescribir = false;
                }else{
                    sobreescribir = true;
                }
       
                lecturaEscritura.escribeByte(caracter, sobreescribir);
                
            }
                
            case InterfazVista.ESCRIBE_CADENA -> {
                boolean sobreescribir;
                Scanner scanner = new Scanner(System.in);
                System.out.println("Introduce una cadena para escribir:");
                String cadena = scanner.nextLine();
                System.out.println("Quieres sobreescribir los datos?  1.Si 2.No");
                char num = scanner.next().charAt(0);
                if(num == 1){
                    sobreescribir = false;
                }else{
                    sobreescribir = true;
                }
       
                lecturaEscritura.escribeCadena(cadena, sobreescribir);
                
            }
            
            case InterfazVista.LEE_BYTE_A_BYTE ->{
                String resultado = lecturaEscritura.leerByteAByte();
                System.out.println(resultado);
            }
            
            case InterfazVista.LEE_ARRAY_DE_BYTES -> {
                String resultado = lecturaEscritura.leerArrayBytes();
                System.out.println(resultado);
            }
            
            
            
            
            
            
            
            /* LECTURA ESCRITURA ARRAYS*/
            case InterfazVista.ESCRIBIR_ARRAY-> {
                String[] nombres = vista.obtenerNombres();
                int [] telefonos = vista.obtenerTelefonos();
                boolean[] altas = vista.obtenerAltas();
                
                lecturaEscrituraArrays.escribirArrays(nombres, telefonos, altas);
            }
            
            case InterfazVista.LEER_ARRAY-> {
                String resultado = lecturaEscrituraArrays.lecturaArrays();
                System.out.println(resultado);
            }
            
            case InterfazVista.COPIAR_ARCHIVO_DESTINO_ORIGEN-> {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Introduce la ruta origen del archivo:");
                String rutaOrigen = scanner.nextLine();
                System.out.println("Introduce la ruta destino del archivo:");
                String rutaDestino = scanner.nextLine();
                lecturaEscrituraArrays.copiarArchivo(rutaOrigen, rutaDestino);
            }
            
            
            
            
            
            
            /* LECTURA ESCRITURA DATOS PRIMITIVOS */
            case InterfazVista.ESCRIBIR_DATOS_PRIMITIVOS-> {
                lecturaEscrituraDatosPrimitivos.escribirDatosPrimitivos();
            }
            
            case InterfazVista.LEER_DATOS_PRIMITIVOS-> {
                String resultado = lecturaEscrituraDatosPrimitivos.lecturaDatosPrimitivos();
                System.out.println(resultado);
            }
            
            
            
            
            
            
            /* LECTURA ESCRITURA OBJETOS */
            case InterfazVista.ESCRIBIR_OBJETOS-> {
                 Object objeto = vista.obtenerObjeto();
                 listaUniversal.add(objeto);
                 lecturaEscrituraObjetos.escribirObjetos(objeto);
            }
            
            case InterfazVista.LEER_OBJETOS_UNIVERSAL-> {
                ArrayList<Object> resultado = lecturaEscrituraObjetos.lecturaObjetosUniversal();
                System.out.println(resultado.toString());
            }
            
            case InterfazVista.CONVERTIR_ARRAY_A_CLASE-> {
                Class<?> claseDestino = vista.obtenerClaseParaConversion();

                // Llamamos al método genérico
                if (claseDestino == Empleado.class) {
                    ArrayList<Empleado> listaEmpleados = lecturaEscrituraObjetos.convertirArrayList(listaUniversal, Empleado.class);
                    listaEmpleados.forEach(System.out::println);
                } else if (claseDestino == Departamento.class) {
                    ArrayList<Departamento> listaDepartamentos = lecturaEscrituraObjetos.convertirArrayList(listaUniversal, Departamento.class);
                    listaDepartamentos.forEach(System.out::println);
                }
            }
            
            default -> {    
            }
        }
        vista.arranca();
    }
    
    

}

