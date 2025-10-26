/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package modelo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author Javier Molina
 * Created on 30 sept 2025
 */
public class LecturaEscritura extends Fichero {
    public LecturaEscritura(String ruta){
        super(ruta);
    }
    
    public void escribeByte(char character, boolean sobreescribir){
        FileOutputStream fileout = null;
        
        try{
            fileout = new FileOutputStream(super.getRuta(), sobreescribir);
            fileout.write(character);
            fileout.flush();
            fileout.close();
        } catch(FileNotFoundException e){
            e.printStackTrace();
        } catch(IOException e){
           e.printStackTrace(); 
        }
    }
    
    public void escribeCadena(String cadena, boolean sobreescribir){
        try{
            FileOutputStream fileout = new FileOutputStream(super.getRuta(), sobreescribir);
            byte[] contenidoEnBytes = cadena.getBytes();
            fileout.write(contenidoEnBytes);
            fileout.flush();
            fileout.close();
        } catch(FileNotFoundException e){
            e.printStackTrace();
        } catch(IOException e){
           e.printStackTrace(); 
        }
    }
    
    public String leerByteAByte(){
        StringBuilder texto = new StringBuilder();
        
        try(FileInputStream ficheroIn = new FileInputStream(super.getRuta())){
            
            int byteLeido;
            while((byteLeido = ficheroIn.read()) != -1){
                texto.append((char) byteLeido);
            }
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
        
        return texto.toString();
    }
    
    
    /*  Abre un archivo con FileInputStream                 Lectura binaria
	Lee bloques de 5 bytes                              Usa un buffer fijo
	Convierte cada bloque a String y concatena          
        Devuelve el texto completo
    */
    public String leerArrayBytes(){
        StringBuilder texto = new StringBuilder();        
        byte[] cadena = new byte[5];
        
        try (FileInputStream ficheroIn = new FileInputStream(super.getRuta())){
            int byteLeido;
            while((byteLeido = ficheroIn.read(cadena)) != -1){
                texto.append(new String(cadena, 0, byteLeido));
            }
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
        
        return texto.toString();
    }
}
