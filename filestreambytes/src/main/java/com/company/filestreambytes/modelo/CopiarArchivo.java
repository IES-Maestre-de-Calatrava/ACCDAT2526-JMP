/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.company.filestreambytes.modelo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author Javier Molina-Prados
 * Created on 1 oct 2025
 */
public class CopiarArchivo{
    
    
    public static void copiarArchivo (String rutaOrigen, String rutaDestino){
        
        try(FileInputStream ficheroIn = new FileInputStream(rutaOrigen);
            FileOutputStream ficheroOut = new FileOutputStream(rutaDestino);){
            
            
            byte [] bytes = new byte [1024];
            
            int bytesLeidos;
            
            while((bytesLeidos = ficheroIn.read(bytes)) != -1){
                ficheroOut.write(bytes, 0, bytesLeidos);
            }
            
            System.out.println("Archivo copiado con exito");
         }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    

}
