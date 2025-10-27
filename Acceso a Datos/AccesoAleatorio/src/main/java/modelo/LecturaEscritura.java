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
 * Created on 27 oct 2025
 */
public class LecturaEscritura extends FicheroEmpleados {

    public LecturaEscritura(String ruta) {
        super(ruta);
    }
    
    /**
     * Este metodo escribe empleados al final del archivo (ignorando el identificador)
     * @param emple     empleado que quiero escribir
     */
    public void escribirEmpleadoFinal(Empleado emple){
        
        // Abro el flujo de mi randomFile pasandole la ruta
        try(RandomAccessFile randomFile = new RandomAccessFile(super.getRuta(), "rw");){
            long posicion = randomFile.length(); // posición final del fichero
            randomFile.seek(posicion);
            
            // añado mi identificador            
            randomFile.writeLong(emple.getIdentificador());            
            
            // me creo un buffer para escribir el String del apellido con la longitud fija
            StringBuffer buffer = new StringBuffer (emple.getApellido());
            buffer.setLength(super.getCARACTERES_APELLIDO());
            randomFile.writeChars(buffer.toString());
            
            randomFile.writeInt(emple.getDepartamento());
            
            randomFile.writeDouble(emple.getSalario());
             
        }catch(FileNotFoundException ex){
            System.getLogger(LecturaEscritura.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }catch(IOException ex){
            System.getLogger(LecturaEscritura.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);

        }
    }
    
    
    
    /**
     * Este metodo escribe a mi empleado en la posición de su identificador
     * @param emple     el empleado que quiero escribir
     */
    public void escribirEmpleadoPorId(Empleado emple) {
        long posicion = 0;
        
        try (RandomAccessFile randomFile = new RandomAccessFile(super.getRuta(), "rw")) {

            // Calcular la posición exacta donde empieza este empleado
            posicion = (emple.getIdentificador() - 1) * super.getLONGITUD_TOTAL();

            // Mover el puntero a esa posición
            randomFile.seek(posicion);

            // Escribir los datos
            randomFile.writeLong(emple.getIdentificador());

            StringBuffer buffer = new StringBuffer(emple.getApellido());
            buffer.setLength(super.getCARACTERES_APELLIDO());
            randomFile.writeChars(buffer.toString());

            randomFile.writeInt(emple.getDepartamento());
            randomFile.writeDouble(emple.getSalario());

        } catch (FileNotFoundException ex) {
            System.getLogger(LecturaEscritura.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (IOException ex) {
            System.getLogger(LecturaEscritura.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    
    
    /**
     * Este metodo devuelve un empleado que queremos leer dependiendo del identificador
     * @param identificador     identificador del empleado que estamos buscando
     * @return                  devuelve al empleado
     */
    public Empleado lecturaEmpleado(long identificador){
        long posicion = 0;
        
        Empleado emple = new Empleado();
        
        byte [] cadena = new byte [super.getLONGITUD_APELLIDO_EN_BYTES()];
        
        try(RandomAccessFile randomFile = new RandomAccessFile(super.getRuta(), "rw");){
            
            posicion = (identificador - 1)* super.getLONGITUD_TOTAL();
            
            if (posicion < randomFile.length()){
                
                randomFile.seek(posicion);
                
                emple.setIdentificador(randomFile.readLong());
                
                randomFile.read(cadena);
                emple.setApellido(new String (cadena));
          
                emple.setDepartamento(randomFile.readInt());
                
                emple.setSalario(randomFile.readDouble());
            }
            
        }catch(FileNotFoundException ex){
            System.getLogger(LecturaEscritura.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }catch(IOException ex){
            System.getLogger(LecturaEscritura.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);

        }
        
        return emple;
    }
    
    
    public void borrarEmpleado(long identificador){
        long posicion;
                
        try (RandomAccessFile randomFile = new RandomAccessFile(super.getRuta(), "rw")) {

            // Calcular la posición exacta donde empieza este empleado
            posicion = (identificador - 1) * super.getLONGITUD_TOTAL();
            
            if (posicion < randomFile.length()){            
                // Mover el puntero a esa posición
                randomFile.seek(posicion);

                // Escribir un cero en el identificador
                randomFile.writeLong(0);
            

            // Rellenamos el resto del registro con ceros
                for (int i = 0; i < getLONGITUD_TOTAL() - Long.BYTES; i++) {
                    randomFile.writeByte(0);
                }
            }

        } catch (FileNotFoundException ex) {
            System.getLogger(LecturaEscritura.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (IOException ex) {
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
