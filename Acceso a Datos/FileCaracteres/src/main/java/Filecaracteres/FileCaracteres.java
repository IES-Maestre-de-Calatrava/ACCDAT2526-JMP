/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package Filecaracteres;

import controlador.Controlador;
import java.io.File;
import modelo.LecturaEscrituraCaracteres;
import vista.InterfazVista;
import vista.VentanaConversorTexto;

/**
 *
 * @author Javier Molina-Prados
 */
public class FileCaracteres {

    public static void main(String[] args) {
        
        /*
        File file = new File("prueba.txt");
        
        LecturaEscrituraCaracteres fichero = new LecturaEscrituraCaracteres(file);
        
        fichero.leerFichero(); // Lee caracter a caracter
        System.out.println();
        System.out.println();

        fichero.leerFichero2(); // Lee con bloques de caracteres
        System.out.println();
        System.out.println();
        
        fichero.leerFichero3(); // Lee linea a linea
        System.out.println();
        System.out.println();
        
        
        fichero.escribirFichero("A");
        fichero.escribirFichero("B");
        fichero.escribirFichero("B");
        fichero.leerFichero3();
        System.out.println();
        System.out.println();
        
        fichero.escribirFichero(" Esto inserta una linea al final del fichero sin salto de linea.");
        fichero.escribirFichero(" Como podemos comprobar se pone justo despues de la linea anterior;");
        fichero.leerFichero3();
        System.out.println();
        System.out.println();
        
        fichero.escribirFicheroLinea(" Esto inserta una linea al final del fichero con salto de linea.");
        fichero.escribirFicheroLinea("Como podemos comprobar tabula con respecto a la linea anterior");
        fichero.escribirFicheroLinea("Ultima linea insertada :)");
        fichero.leerFichero3();
        
        
        fichero.copiaEncriptada();
        fichero.desencriptarCopia();
        fichero.copia();
        */
        
        LecturaEscrituraCaracteres modelo = new LecturaEscrituraCaracteres(".\\prueba.txt");
           
        InterfazVista vista = new VentanaConversorTexto();
        
        Controlador controlador = new Controlador(vista,modelo);
        
    }
}
