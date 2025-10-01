/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.company.filestreambytes.modelo;

import java.io.DataInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.DataOutputStream;
import java.io.FileInputStream;

/**
 *
 * @author b15-11m
 * Created on 1 oct 2025
 */
public class LecturaEscrituraArrays extends Fichero {
    public LecturaEscrituraArrays(String ruta){
        super(ruta);
    }
    
    public void escribirArrays(){
        String[] nombres = {"Ana", "Pepe"};
        int[] telefonos = {123456789, 234567891};
        boolean [] altas = {true, false};
        
        try(FileOutputStream ficheroOut = new FileOutputStream(super.getRuta());
            DataOutputStream arraysOut = new DataOutputStream(ficheroOut);){
            
            // Longitud del array
            arraysOut.writeInt(nombres.length);
            
            // Escribimos los datos
            for(int i = 0; i < nombres.length; i ++){
                arraysOut.writeUTF(nombres[i]);
                arraysOut.writeInt(telefonos[i]);
                arraysOut.writeBoolean(altas[i]);
            }
            
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    
    public String lecturaArrays(){
        StringBuffer texto = null;
        
        try(FileInputStream ficheroIn = new FileInputStream(super.getRuta());
            DataInputStream datosIn = new DataInputStream(ficheroIn);){
            
            texto = new StringBuffer();
            
            // Para saber cuantas veces repetir el bucle en cuestión
            // de los elementos que haya en el array
            int longitud = datosIn.readInt();
            
            for(int i = 0; i < longitud; i ++){
                String nombre = datosIn.readUTF();
                int telefono = datosIn.readInt();
                boolean alta = datosIn.readBoolean();
                
                
                texto.append("Nombre: ");
                texto.append(nombre);
                texto.append(". Telefono: ");
                texto.append(telefono);
                texto.append(". Estado de alta: ");
                texto.append(alta);
                if(alta==true){
                    texto.append(".  El empleado SI pertenece a la empresa");
                }else{
                    texto.append(".  El empleado NO pertenece a la empresa");
                }
                texto.append("\n");
            }
            
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
        
        return texto.toString();
    }
}
