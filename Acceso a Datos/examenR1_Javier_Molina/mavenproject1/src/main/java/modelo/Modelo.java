/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package modelo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

/**
 *
 * @author Javier Molina-Prados
 * Created on 3 nov 2025
 */
public class Modelo {
        
    public void altaVehiculo(long id){
        long posicion = 0;
        try(
                RandomAccessFile raf = new RandomAccessFile(".\\archivos_pruebas\\datos_coches.dat", "rw")
            ){
            
            // 1º Colocamos el puntero en el id del coche 
            posicion = (id-1)*Coche.getLONGITUD_TOTAL();
            
            // 2º Comprobamos que existe el coche
            if (posicion >= raf.length() || posicion < 0) {
                System.out.println("0");
                
                 // Colocamos el puntero y creamos el coche
                raf.seek(posicion);
                Coche coche = new Coche(); 
                
                long inicioRespuestas = posicion+ 
                    Coche.getLONGITUD_IDENTIFICADOR_EN_BYTES() + 
                    Coche.getLONGITUD_MATRICULA_EN_BYTES() +
                    Coche.getLONGITUD_TELEFONO_EN_BYTES() + 
                    Coche.getLONGITUD_NOMBRE_CONTACTO_EN_BYTES() + 
                    Coche.getLONGITUD_DIAGNOSTICO_EN_BYTES() +
                    Coche.getLONGITUD_REPARACION_EN_BYTES() +
                    Coche.getLONGITUD_COSTE_EN_BYTES() +
                    Coche.getLONGITUD_FECHA_REPARACION_EN_BYTES();
                
                raf.seek(inicioRespuestas);
                
                String matricula = pedirMatricula();
                String nombreContacto = pedirNombreContacto();
                int telefono = pedirTelefono();
                String diagnostico = pedirDiagnostico();
                
                
                raf.writeLong(id);
                coche.setId(id);
                escribirTexto(raf, matricula, Coche.getCARACTERES_MATRICULA());
                coche.setMatricula(matricula);
                raf.writeInt(telefono);
                coche.setTelefono(telefono);
                escribirTexto(raf, nombreContacto, Coche.getCARACTERES_NOMBRE_CONTACTO());
                coche.setNombreContacto(nombreContacto);
                escribirTexto(raf, diagnostico, Coche.getCARACTERES_DIAGNOSTICO());
                coche.setDiagnostico(diagnostico);
                escribirTexto(raf, " ", Coche.getCARACTERES_REPARACION());
                coche.setReparacion(" ");
                raf.writeLong(0);
                coche.setCoste(0);
                escribirTexto(raf, " ", Coche.getCARACTERES_FECHA_REPARACION()); 
                coche.setFechaReparacion(" ");
                
                
                
                //System.out.println("Coche añadido!  id: "+coche.getId()+", Matricula: "+coche.getMatricula()+", Nombre Contacto: "+coche.getNombreContacto()+", Diagnostico: "+coche.getDiagnostico()+", Reparacion: "+coche.getReparacion()+", Coste: "+coche.getCoste()+", Fecha Reparacion: "+coche.getFechaReparacion());

            
            }else{
                
                 //3º Colocamos el puntero y leemos el registro del coche
                raf.seek(posicion);
                Coche coche = leerCoche(raf); // Funcion que me lee todo el registro del coche
   
                System.out.println("ID: "+id+ ", Matricula: "+coche.getMatricula());
                
            }
                        
        }catch(FileNotFoundException ex){
            System.getLogger(Modelo.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }catch(IOException ex){
            System.getLogger(Modelo.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);

        }
    }
    
    
    public void escribirTexto(RandomAccessFile raf, String cadena, int longitud) throws IOException{
        StringBuilder strbr = new StringBuilder(cadena);
        strbr.setLength(longitud);
        
        raf.writeChars(strbr.toString());
    }
    
    
    public String leerCampoTexto(RandomAccessFile raf, int longitud) throws IOException{
       byte[] buffer = new byte[longitud];
        raf.read(buffer);
        return new String(buffer, "UTF-16").trim();
    }

     private Coche leerCoche(RandomAccessFile raf) throws IOException {
    // Nota: El método readLong() moverá el puntero.
        long id = raf.readLong(); 

        // Los métodos leerCampoTexto y las constantes ya deben estar definidos
        String matricula = leerCampoTexto(raf, Coche.getLONGITUD_MATRICULA_EN_BYTES());
        int telefono = raf.readInt();
        String nombreContacto = leerCampoTexto(raf, Coche.getLONGITUD_NOMBRE_CONTACTO_EN_BYTES());
        String diagnostico = leerCampoTexto(raf, Coche.getLONGITUD_DIAGNOSTICO_EN_BYTES());
        String reparacion = leerCampoTexto(raf, Coche.getLONGITUD_REPARACION_EN_BYTES());
        long coste = raf.readLong();
        String fechaReparacion = leerCampoTexto(raf, Coche.getLONGITUD_FECHA_REPARACION_EN_BYTES());

        // Creamos y llenamos el objeto
        Coche coche = new Coche();
        coche.setId(id); 
        coche.setMatricula(matricula);
        coche.setTelefono(telefono);
        coche.setNombreContacto(nombreContacto);
        coche.setDiagnostico(diagnostico);
        coche.setReparacion(reparacion);
        coche.setCoste(coste);
        coche.setFechaReparacion(fechaReparacion);

        return coche;
    }
     
     
     public String pedirMatricula(){
         Scanner scanner = new Scanner(System.in);
         System.out.println("Introduce la matricula: ");
         String matricula = scanner.nextLine();
         return matricula;
     }
     
     
      public String pedirNombreContacto(){
         Scanner scanner = new Scanner(System.in);
         System.out.println("Introduce el nombre de contacto: ");
         String nombreContacto = scanner.nextLine();
         return nombreContacto;
     }
      
     public String pedirDiagnostico(){
         Scanner scanner = new Scanner(System.in);
         System.out.println("Introduce el diagnostico: ");
         String diagnostico = scanner.nextLine();
         return diagnostico;
     }
     
      public int pedirTelefono(){
         Scanner scanner = new Scanner(System.in);
         System.out.println("Introduce el telefono: ");
         int telefono = scanner.nextInt();
         return telefono;
     }
      
      public long pedirId(){
         Scanner scanner = new Scanner(System.in);
         System.out.println("Introduce el id del vehiculo: ");
         long id = scanner.nextLong();
         return id;
     }
      
      
      
    public void cargarVehiculo(long idCargarVehiculo) throws FileNotFoundException, IOException{
        long posicion = 0;
        try(
                RandomAccessFile raf = new RandomAccessFile(".\\archivos_pruebas\\datos_coches.dat", "r")
            ){
            
            // 1º Colocamos el puntero en el id del coche 
            posicion = (idCargarVehiculo-1)*Coche.getLONGITUD_TOTAL();
            
            // 2º Comprobamos que existe el coche
            if (posicion >= raf.length() || posicion < 0) {
                System.out.println("No existe el coche con Id:" + idCargarVehiculo);
           
            }else{
                
                 //3º Colocamos el puntero y leemos el registro del coche
                raf.seek(posicion);
                Coche coche = leerCoche(raf); // Funcion que me lee todo el registro del coche
   
                System.out.println("Coche con id: "+idCargarVehiculo+" Matricula: "+coche.getMatricula()+", Nombre Contacto: "+coche.getNombreContacto()+", Diagnostico: "+coche.getDiagnostico()+", Reparacion: "+coche.getReparacion()+", Coste: "+coche.getCoste()+", Fecha Reparacion: "+coche.getFechaReparacion());
                
            }
        }
    }
    
    
    public void guardarReparacion(long idCargarVehiculo){
        long posicion = 0;
        try(
                RandomAccessFile raf = new RandomAccessFile(".\\archivos_pruebas\\datos_coches.dat", "rw")
            ){
            
            // 1º Colocamos el puntero en el id del coche 
            posicion = (idCargarVehiculo-1)*Coche.getLONGITUD_TOTAL();
            
            // 2º Comprobamos que existe el coche
            if (posicion >= raf.length() || posicion < 0) {
                System.out.println("No existe ese id");
            
            }else{
                
                raf.seek(posicion); 
                
                long inicioRespuestas = posicion+ 
                    Coche.getLONGITUD_IDENTIFICADOR_EN_BYTES() + 
                    Coche.getLONGITUD_MATRICULA_EN_BYTES() +
                    Coche.getLONGITUD_TELEFONO_EN_BYTES() + 
                    Coche.getLONGITUD_NOMBRE_CONTACTO_EN_BYTES() + 
                    Coche.getLONGITUD_DIAGNOSTICO_EN_BYTES() + 
                    Coche.getLONGITUD_REPARACION_EN_BYTES();
                    
                
                raf.seek(inicioRespuestas);
                Coche coche = leerCoche(raf);
                
                String reparacion = pedirReparacion();
                long coste = pedirCoste();
                String fecha = pedirFecha();

                
                
                escribirTexto(raf, reparacion, Coche.getCARACTERES_REPARACION());
                coche.setReparacion(reparacion);
                raf.writeLong(coste);
                coche.setCoste(coste);
                escribirTexto(raf, fecha, Coche.getCARACTERES_FECHA_REPARACION());
                coche.setFechaReparacion(fecha);
                
                System.out.println("Reparacion guardada: reparacion: "+coche.getReparacion()+", Coste: "+coche.getCoste()+", Fecha Reparacion: "+coche.getFechaReparacion());

            
            }
                
                        
        }catch(FileNotFoundException ex){
            System.getLogger(Modelo.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }catch(IOException ex){
            System.getLogger(Modelo.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);

        }
    
    }
    
    public String pedirReparacion(){
         Scanner scanner = new Scanner(System.in);
         System.out.println("Introduce la reparacion : ");
         String reparacion = scanner.nextLine();
         return reparacion;
    }
    
    
    public long pedirCoste(){
         Scanner scanner = new Scanner(System.in);
         System.out.println("Introduce el coste: ");
         long coste = scanner.nextLong();
         return coste;
     }
      
    
     public String pedirFecha(){
         Scanner scanner = new Scanner(System.in);
         System.out.println("Introduce la fecha : ");
         String reparacion = scanner.nextLine();
         return reparacion;
    }
      


    

}
