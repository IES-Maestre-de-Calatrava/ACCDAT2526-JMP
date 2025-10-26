/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package modelo;

import java.io.DataInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;

/**
 *
 * @author Javier Molina
 * Created on 1 oct 2025
 */
public class LecturaEscrituraArrays extends Fichero {
    public LecturaEscrituraArrays(String ruta){
        super(ruta);
    }
    
    public void escribirArrays(String [] nombres, int [] telefonos, boolean [] altas){
        try(FileOutputStream ficheroOut = new FileOutputStream(super.getRuta());
            DataOutputStream arraysOut = new DataOutputStream(ficheroOut);){
                        
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
        StringBuffer texto = new StringBuffer();
        
        try(FileInputStream ficheroIn = new FileInputStream(super.getRuta());
            DataInputStream datosIn = new DataInputStream(ficheroIn);){
                        
            // Para saber cuantas veces repetir el bucle en cuestión
            // de los elementos que haya en el array            
            try{
                while (true) {

                    String nombre = datosIn.readUTF();
                    int telefono = datosIn.readInt();
                    boolean alta = datosIn.readBoolean();


                    texto.append("Nombre: ").append(nombre);
                    texto.append(". Telefono: ").append(telefono);
                    texto.append(". Estado de alta: ").append(alta);

                    if(alta){
                        texto.append(".  El empleado SI pertenece a la empresa");
                    }else{
                        texto.append(".  El empleado NO pertenece a la empresa");
                    }
                    texto.append("\n");
                }
            }catch(EOFException ex){
                System.out.println("Fin de la lectura de arrays. Proceso completado.");
            }
            
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
        
        return texto.toString();
    }
    
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
