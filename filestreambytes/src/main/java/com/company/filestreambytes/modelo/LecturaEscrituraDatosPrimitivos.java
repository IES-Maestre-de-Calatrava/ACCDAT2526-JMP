/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.company.filestreambytes.modelo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author Javier Molina-Prados
 * Created on 30 sept 2025
 */
public class LecturaEscrituraDatosPrimitivos extends Fichero{

    public LecturaEscrituraDatosPrimitivos(String ruta) {
        super(ruta);
    }
    
    public void escribirDatosPrimitivos(){
        try (FileOutputStream ficheroOut = new FileOutputStream(super.getRuta());
            DataOutputStream datosOut = new DataOutputStream(ficheroOut);){
            
            
            datosOut.writeByte((byte) 123);
            datosOut.writeShort((short) 1234);
            //datosOut.writeInt(1234567);
            datosOut.writeLong(123456789123456L);
            datosOut.writeFloat((float) Math.E);
            datosOut.writeDouble(Math.PI);
            datosOut.writeChar('A');
            datosOut.writeUTF("Esto es una cadena");
            
             datosOut.close();
             ficheroOut.flush();
             ficheroOut.close();
        } catch (FileNotFoundException ex) {
            System.getLogger(LecturaEscrituraDatosPrimitivos.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (IOException ex){
            System.getLogger(LecturaEscrituraDatosPrimitivos.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        
    }
    
    public String lecturaDatosPrimitivos(){
        StringBuilder texto = null;
        
        try (FileInputStream ficheroIn = new FileInputStream(super.getRuta());
             DataInputStream datosIn = new DataInputStream(ficheroIn);){
            
            texto = new StringBuilder();
            texto.append(datosIn.readByte());
            texto.append(" ");
            texto.append(datosIn.readShort());
            texto.append(" ");
            texto.append(datosIn.readLong());
            texto.append(" ");
            //texto.append(datosIn.readInt());
            texto.append(datosIn.readFloat());
            texto.append(" ");
            texto.append(datosIn.readDouble());
            texto.append(" ");
            texto.append(datosIn.readChar());
            texto.append(datosIn.readUTF());
            texto.append(" ");
            
            ficheroIn.close();
        } catch (FileNotFoundException ex) {
            System.getLogger(LecturaEscrituraDatosPrimitivos.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (IOException ex){
            System.getLogger(LecturaEscrituraDatosPrimitivos.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return texto.toString();
        
    }
}
