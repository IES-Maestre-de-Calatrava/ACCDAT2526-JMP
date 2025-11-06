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
    
    // Campo para guardar el id del coche que se ha cargado o está en uso
    private long idCocheActivo = 0;
    
    public long getIdCocheActivo(){
        return idCocheActivo;
    }
        
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
    
    

    /**
     * Pide una matrícula y busca el coche asociado en el archivo.
     * Si lo encuentra, muestra su ID. 
     * Si no, pide el resto de datos y añade el nuevo vehículo.
     */
    public void altaVehiculoPorMatricula() {
        String matriculaBuscada = pedirMatricula();
        Coche cocheEncontrado = null;
        long posicionRegistro = 0;
        long nuevoId = 1; // Primer ID por defecto

        try (RandomAccessFile raf = new RandomAccessFile(".\\archivos_pruebas\\datos_coches.dat", "rw")) {

            // 1. Buscamos la matrícula recorriendo el archivo
            while (raf.getFilePointer() < raf.length()) {

                posicionRegistro = raf.getFilePointer(); // Guardamos el inicio del registro actual

                // Leemos solo el ID y la matrícula para verificar
                long idActual = raf.readLong(); 
                String matriculaActual = leerCampoTexto(raf, Coche.getLONGITUD_MATRICULA_EN_BYTES());

                // Si hay coincidencia, guardamos el coche y salimos del bucle
                if (matriculaActual.trim().equalsIgnoreCase(matriculaBuscada.trim())) {

                    // Volvemos al inicio del registro para leer el coche completo
                    raf.seek(posicionRegistro);
                    cocheEncontrado = leerCoche(raf); 
                    System.out.println("✅ MATRÍCULA ENCONTRADA. ID del vehículo: " + cocheEncontrado.getId());
                    System.out.println("Datos: Matricula: " + cocheEncontrado.getMatricula() + ", Contacto: " + cocheEncontrado.getNombreContacto());
                    return; // Salimos del método si se encontró
                } else {
                    // Si no hay coincidencia, saltamos al siguiente registro

                    // Calculamos el resto de bytes que quedan del registro actual:
                    long bytesRestantes = Coche.getLONGITUD_TOTAL() - 
                                          Coche.getLONGITUD_IDENTIFICADOR_EN_BYTES() - 
                                          Coche.getLONGITUD_MATRICULA_EN_BYTES();

                    // Movemos el puntero al inicio del siguiente registro
                    raf.seek(raf.getFilePointer() + bytesRestantes);

                    // Llevamos la cuenta del ID para el nuevo coche
                    nuevoId = idActual + 1;
                }
            }

            // 2. Si llegamos aquí, la matrícula NO se encontró. Procedemos al ALTA.

            // Nos posicionamos al final del archivo para escribir el nuevo registro
            raf.seek(raf.length());
            posicionRegistro = raf.length();

            System.out.println("❌ MATRÍCULA NO ENCONTRADA. Procediendo a dar de ALTA el vehículo con ID: " + nuevoId);

            // Pedimos el resto de datos que faltan
            String nombreContacto = pedirNombreContacto();
            int telefono = pedirTelefono();
            String diagnostico = pedirDiagnostico();

            // Escribimos el nuevo registro
            raf.writeLong(nuevoId); // 1. ID
            escribirTexto(raf, matriculaBuscada, Coche.getCARACTERES_MATRICULA()); // 2. Matrícula
            raf.writeInt(telefono); // 3. Teléfono
            escribirTexto(raf, nombreContacto, Coche.getCARACTERES_NOMBRE_CONTACTO()); // 4. Nombre Contacto
            escribirTexto(raf, diagnostico, Coche.getCARACTERES_DIAGNOSTICO()); // 5. Diagnóstico
            escribirTexto(raf, " ", Coche.getCARACTERES_REPARACION()); // 6. Reparacion
            raf.writeLong(0); // 7. Coste
            escribirTexto(raf, " ", Coche.getCARACTERES_FECHA_REPARACION()); // 8. Fecha Reparación

            System.out.println("Vehículo añadido con éxito con ID: " + nuevoId);

        } catch (FileNotFoundException ex) {
            System.getLogger(Modelo.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (IOException ex) {
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
                this.idCocheActivo = 0; // Si falla, resetamos
            }else{
                
                 //3º Colocamos el puntero y leemos el registro del coche
                raf.seek(posicion);
                Coche coche = leerCoche(raf); // Funcion que me lee todo el registro del coche
   
                System.out.println("Coche con id: "+idCargarVehiculo+" Matricula: "+coche.getMatricula()+", Nombre Contacto: "+coche.getNombreContacto()+", Diagnostico: "+coche.getDiagnostico()+", Reparacion: "+coche.getReparacion()+", Coste: "+coche.getCoste()+", Fecha Reparacion: "+coche.getFechaReparacion());
                
                this.idCocheActivo = idCargarVehiculo; // Almaceno el ID!
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
                                
                long inicioRespuestas = posicion+ 
                    Coche.getLONGITUD_IDENTIFICADOR_EN_BYTES() + 
                    Coche.getLONGITUD_MATRICULA_EN_BYTES() +
                    Coche.getLONGITUD_TELEFONO_EN_BYTES() + 
                    Coche.getLONGITUD_NOMBRE_CONTACTO_EN_BYTES() + 
                    Coche.getLONGITUD_DIAGNOSTICO_EN_BYTES();                    
                
                raf.seek(inicioRespuestas);
                Coche coche = new Coche();
                
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
