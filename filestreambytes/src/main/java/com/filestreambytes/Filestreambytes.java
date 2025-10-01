/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.filestreambytes;

import com.company.filestreambytes.modelo.CopiarArchivo;
import com.company.filestreambytes.modelo.LecturaEscritura;
import com.company.filestreambytes.modelo.LecturaEscrituraArrays;
import com.company.filestreambytes.modelo.LecturaEscrituraDatosPrimitivos;

/**
 *
 * @author Javier Molina-Prados
 */
public class Filestreambytes {

    public static void main(String[] args) {
        LecturaEscritura lecturaEscritura = new LecturaEscritura(".\\archivos_prueba\\ejemploprueba.dat");
        
        //lecturaEscritura.escribeByte('A', true);
        //lecturaEscritura.escribeByte('B', true);
        
        //lecturaEscritura.escribeCadena("Prueba", false);
        
        //System.out.println(lecturaEscritura.leerByteAByte());
        
        //System.out.println(lecturaEscritura.leerArrayBytes());
    
        //LecturaEscrituraDatosPrimitivos lecturaEscrituraDatosPrimitivos = new LecturaEscrituraDatosPrimitivos(".\\archivos_prueba\\ejemplopruebaDatosPrimitivos.dat");

        //lecturaEscrituraDatosPrimitivos.escribirDatosPrimitivos();
        //System.out.println(lecturaEscrituraDatosPrimitivos.lecturaDatosPrimitivos());
        
        /*
        LecturaEscrituraArrays lecturaEscrituraArrays = new LecturaEscrituraArrays(".\\archivos_prueba\\ejemplopruebaDatosArrrays.dat");
        
        lecturaEscrituraArrays.escribirArrays();
        System.out.println(lecturaEscrituraArrays.lecturaArrays());
        */
        
        CopiarArchivo copiar = new CopiarArchivo();
        
        copiar.copiarArchivo(".\\archivos_prueba\\VisualStudioSetup.exe", ".\\archivos_prueba_copia\\VisualStudioSetupCopia.exe");
    }
}
