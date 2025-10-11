/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package modelo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

/**
 *
 * @author Javier Molina-Prados
 * Created on 7 oct 2025
 */
public class LecturaEscritura extends FicheroEmpleados {

    public LecturaEscritura(String ruta) {
        super(ruta);
    }
    
    public void escribirEmpleadoFinalArchivo(Empleado empleado){
        
        long posicion = 0;
        StringBuffer buffer = null;
        
        try (RandomAccessFile randomFile = new RandomAccessFile(super.getRuta(),"rw");){
            
            if(randomFile.length() != 0){
                posicion = randomFile.length();
            }
            
            randomFile.seek(posicion);
            
            randomFile.writeLong((posicion/super.getLONGITUD_TOTAL())+1);
            
            buffer = new StringBuffer(empleado.getApellido());
            buffer.setLength(super.getCARACTERES_APELLIDO());
            randomFile.writeChars(buffer.toString());
            
            randomFile.writeInt(empleado.getDepartamento());
            
            randomFile.writeDouble(empleado.getSalario());
            
        }catch(FileNotFoundException ex){
            System.getLogger(LecturaEscritura.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }catch(IOException ex){
            System.getLogger(LecturaEscritura.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);

        }
    }
    
    public Empleado lecturaEmpleado(long identificador){
        
        long posicion = 0;
        
        Empleado empleado = new Empleado();
        
        byte [] cadena = new byte[super.getLONGITUD_APELLIDO_EN_BYTES()];
        
        try(RandomAccessFile randomFile = new RandomAccessFile(super.getRuta(),"rw");){
            
            posicion = (identificador - 1)*super.getLONGITUD_TOTAL();
            
            if (posicion < randomFile.length()){
                randomFile.seek(posicion);
                
                // Identificador
                empleado.setIdentificador(randomFile.readLong());
                
                // Apellido
                randomFile.read(cadena);
                empleado.setApellido(new String(cadena));
                
                // Departamento
                empleado.setDepartamento(randomFile.readInt());
                
                // Salario
                empleado.setSalario(randomFile.readDouble());
                            
            }
       }catch(FileNotFoundException ex){
            System.getLogger(LecturaEscritura.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
       }catch(IOException ex){
            System.getLogger(LecturaEscritura.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
       }
       
       return empleado;
    }
    
    
     public void escribirEmpleado(long identificador, Empleado empleado){
        
        long posicion = identificador;
        StringBuffer buffer = null;
        
        try (RandomAccessFile randomFile = new RandomAccessFile(super.getRuta(),"rw");){
            
            posicion = (identificador - 1)*super.getLONGITUD_TOTAL();
            
            randomFile.seek(posicion);
            
            randomFile.writeLong((posicion/super.getLONGITUD_TOTAL())+1);
            
            buffer = new StringBuffer(empleado.getApellido());
            buffer.setLength(super.getCARACTERES_APELLIDO());
            randomFile.writeChars(buffer.toString());
            
            randomFile.writeInt(empleado.getDepartamento());
            
            randomFile.writeDouble(empleado.getSalario());
            
        }catch(FileNotFoundException ex){
            System.getLogger(LecturaEscritura.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }catch(IOException ex){
            System.getLogger(LecturaEscritura.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);

        }
    }
     
     
    public String leerTodoEmpleado() {
    ArrayList<Empleado> empleados = new ArrayList<>();
    Empleado empleado;
    
    try (RandomAccessFile randomFile = new RandomAccessFile(super.getRuta(), "r")) {
        for (int i = 0; i < randomFile.length() / getLONGITUD_TOTAL(); i++) {
            empleado = lecturaEmpleado(i + 1);
            
            // Solo agregamos empleados válidos
            if (empleado.getIdentificador() != 0) {
                empleados.add(empleado);
            }
        }
    } catch (FileNotFoundException ex) {
        System.getLogger(LecturaEscritura.class.getName())
              .log(System.Logger.Level.ERROR, (String) null, ex);
    } catch (IOException ex) {
        System.getLogger(LecturaEscritura.class.getName())
              .log(System.Logger.Level.ERROR, (String) null, ex);
    }

    // Construimos una salida legible
    StringBuilder sb = new StringBuilder();
    for (Empleado e : empleados) {
        sb.append(e.toString()).append("\n");
    }
    return sb.toString();
}
     
     public void borrarEmpleado(long identificador){
         long posicion;
         
        try (RandomAccessFile randomFile = new RandomAccessFile(super.getRuta(),"rw");){
            
            posicion = (identificador - 1)*super.getLONGITUD_TOTAL();
            
            if (posicion < randomFile.length()){
                randomFile.seek(posicion);
                
              // Borramos el identificador(poniendolo a 0)
                randomFile.writeLong(0);

                // Rellenamos el resto del registro con ceros
                for (int i = 0; i < getLONGITUD_TOTAL() - Long.BYTES; i++) {
                    randomFile.writeByte(0);
                }
            }
        }catch(FileNotFoundException ex){
            System.getLogger(LecturaEscritura.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }catch(IOException ex){
            System.getLogger(LecturaEscritura.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }   
    }
     
    public void cambiarNombreEmpleado(String apellido, long identificador){
        long posicion = 0;
        StringBuffer bufferStr;
        try (RandomAccessFile randomFile = new RandomAccessFile(super.getRuta(),"rw");){
            
           posicion = (identificador - 1)*super.getLONGITUD_TOTAL()+super.getLONGITUD_IDENTIFICADOR();
            
           randomFile.seek(posicion);
           
           bufferStr = new StringBuffer(apellido);
           bufferStr.setLength(super.getCARACTERES_APELLIDO());
           randomFile.writeChars(bufferStr.toString());
    
        }catch(FileNotFoundException ex){
            System.getLogger(LecturaEscritura.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }catch(IOException ex){
            System.getLogger(LecturaEscritura.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }  
    }
}
