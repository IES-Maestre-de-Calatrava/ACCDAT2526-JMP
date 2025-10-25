/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author Javier Molina-Prados
 * Created on 25 oct 2025
 */
public class LecturaEscrituraCaracteres {
    
    private File fichero;

    /**
     * Constructor de la clase
     * Acepta la ruta del fichero como un String y crea internamente el objeto File.
     * @param rutaFichero la ruta del fichero que queremos leer, escribir, etc.
     */
    public LecturaEscrituraCaracteres(String rutaFichero) {
        this.fichero = new File(rutaFichero);
    }

    /**
     * Establece el fichero de trabajo a partir de una ruta (String).
     * @param nuevaRuta la nueva ruta del fichero.
     */
    public void setFicheroPorRuta(String ruta) {
        this.fichero = new File(ruta);    
    }
    
    
    /**
     * Este método lee caracter a caracter un fichero y lo muestra por consola
     */
    public void leerFichero(){
        // Declara la variable para el caracter leído (como un int)
        int caracterLeido; 
        
        // usamos FileReader para leer los caracteres uno por uno
        try (FileReader ficheroIn = new FileReader(fichero)) {
            
            // El método read() devuelve -1 cuando se llega al final del fichero.
            while ((caracterLeido = ficheroIn.read()) != -1) {
                // Se imprime el carácter haciendo un casting a char
                System.out.print((char) caracterLeido); 
            }
            System.out.println(); 

        // Se capturan las excepciones de la forma más específica (FileNotFoundException)
        // y la más general para la lectura (IOException).
        } catch (FileNotFoundException ex) {
             System.getLogger(LecturaEscrituraCaracteres.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (IOException ex) {
            System.getLogger(LecturaEscrituraCaracteres.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        
    }
    
    
    
     /**
     * Este método lee tambien el fichero pero pero leyendo un bloque de caracteres, String o array     
     */
    public void leerFichero2(){
   
        final int TAMANIO_BUFFER = 1024;
        
        char [] array = new char [TAMANIO_BUFFER]; 
        
        int caracteresLeidos;
        
        try (FileReader ficheroIn = new FileReader(fichero)) {
            
           while ((caracteresLeidos = ficheroIn.read(array)) != -1) {
                
                // Crea un String usando SÓLO la parte del array que fue llenada
                // [0] es el inicio y [caracteresLeidos] es la longitud.
                String bloqueLeido = new String(array, 0, caracteresLeidos);
                
                // Lo muestro (System.out.print para no añadir saltos de línea extra)
                System.out.print(bloqueLeido);
            }
            System.out.println(); 

        // Se capturan las excepciones de la forma más específica (FileNotFoundException)
        // y la más general para la lectura (IOException).
        } catch (FileNotFoundException ex) {
             System.getLogger(LecturaEscrituraCaracteres.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (IOException ex) {
            System.getLogger(LecturaEscrituraCaracteres.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        
    }
    
    
    /**
     * Este método lee tambien el fichero pero va linea a linea
     */
    public void leerFichero3(){
         // Declara la variable para la linea que vamos leyendo
        String linea; 
        
        // Aqui se hace uso de BufferedReader para leer linea a linea
        try (FileReader ficheroIn = new FileReader(fichero);
             BufferedReader bufferficheroIn = new BufferedReader(ficheroIn);) {
            
            // El método readLine() devuelve null cuando se llega al final del fichero, osea cuando no hay mas lineas que leer
            while ((linea = bufferficheroIn.readLine()) != null) {
                // Se imprime el carácter haciendo un casting a char
                System.out.println(linea); 
            }
            System.out.println(); 

        // Se capturan las excepciones de la forma más específica (FileNotFoundException)
        // y la más general para la lectura (IOException).
        } catch (FileNotFoundException ex) {
             System.getLogger(LecturaEscrituraCaracteres.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (IOException ex) {
            System.getLogger(LecturaEscrituraCaracteres.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        
    }
    
    
    
    
    /**
     * Método que escribe un caracter en un fichero
     * @param caracter      el caracter a escribir
     */
    public void escribirFichero(char caracter){
        
        // usamos FileWriter para escribir los caracteres uno por uno
        try (FileWriter ficheroOut = new FileWriter(fichero, true)) {  // FileWriter con 'append' en true, para permitir añadir al final 
            
            ficheroOut.write(caracter);

        // Se capturan las excepciones de la forma más específica (FileNotFoundException)
        // y la más general para la lectura (IOException).
        } catch (FileNotFoundException ex) {
             System.getLogger(LecturaEscrituraCaracteres.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (IOException ex) {
            System.getLogger(LecturaEscrituraCaracteres.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        
    }
    
    
    /**
     * Este metodo ecribe una Cadena de Caracteres (sin salto de línea)
     */ 
    public void escribirFichero(String cadena){ 

        try (FileWriter ficheroOut = new FileWriter(fichero, true)) { // FileWriter con 'append' en true, para permitir añadir al final 
        
            ficheroOut.write(cadena); 
            // No es necesario llamar a flush() o close() explícitamente gracias al try-with-resources.
            
        } catch (FileNotFoundException ex) {
              System.getLogger(LecturaEscrituraCaracteres.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (IOException ex) {
            System.getLogger(LecturaEscrituraCaracteres.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
    
    
    

    /**
     * Método que escribe una LÍNEA de caracteres en un fichero (incluyendo el salto de línea).
     * @param linea La línea que queremos escribir
     */
    public void escribirFicheroLinea(String linea){ 

        // Se recomienda usar BufferedWriter con PrintWriter para una escritura eficiente de líneas.
        try (FileWriter ficheroOut = new FileWriter(fichero, true); // FileWriter con 'append' en true, para permitir añadir al final 
             BufferedWriter bufferOut = new BufferedWriter(ficheroOut);
             PrintWriter printWriter = new PrintWriter(bufferOut)) {

            // El método println() de PrintWriter añade la cadena y el separador de línea
            printWriter.println(linea); 
            // No es necesario llamar a flush() o close() explícitamente gracias al try-with-resources.

        } catch (FileNotFoundException ex) {
              System.getLogger(LecturaEscrituraCaracteres.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (IOException ex) {
            System.getLogger(LecturaEscrituraCaracteres.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
    
    
    /**
     * Metodo que crea una copia encriptada de un fichero creado
     */
    public void copiaEncriptada(){
        
        File copiaEncriptada = new File(".\\copia_encriptada.txt");
        
        // Declara la variable para el caracter leído (como un int)
        int caracterLeido; 
                
        // usamos FileReader para leer los caracteres uno por uno
        try (FileReader ficheroIn = new FileReader(fichero);
             FileWriter ficheroOut = new FileWriter(copiaEncriptada)) {
            
            // El método read() devuelve -1 cuando se llega al final del fichero.
            while ((caracterLeido = ficheroIn.read()) != -1) {
                   
                // mi encriptacion consiste en sumarle uno al caracter en código ASCII
                int caracterEncriptado = caracterLeido + 2;
                ficheroOut.write(caracterEncriptado); 
            }
            System.out.println();         
        
        // Se capturan las excepciones de la forma más específica (FileNotFoundException)
        // y la más general para la lectura (IOException).
        } catch (FileNotFoundException ex) {
             System.getLogger(LecturaEscrituraCaracteres.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (IOException ex) {
            System.getLogger(LecturaEscrituraCaracteres.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    
    }
    
    
    /**
     * Metodo que crea un fichero con el fichero encriptado desencriptado
     */
    public void desencriptarCopia(){
        File copiaDesencriptada = new File(".\\copia_desencriptada.txt");
        
        // Declara la variable para el caracter leído (como un int)
        int caracterLeido; 
                
        // usamos FileReader para leer los caracteres uno por uno
        try (FileReader ficheroIn = new FileReader("copia_encriptada.txt");
             FileWriter ficheroOut = new FileWriter(copiaDesencriptada)) {
            
            // El método read() devuelve -1 cuando se llega al final del fichero.
            while ((caracterLeido = ficheroIn.read()) != -1) {
                   
                // mi encriptacion consiste en sumarle uno al caracter en código ASCII
                int caracterEncriptado = caracterLeido - 2;
                ficheroOut.write(caracterEncriptado); 
            }
            System.out.println();         
        
        // Se capturan las excepciones de la forma más específica (FileNotFoundException)
        // y la más general para la lectura (IOException).
        } catch (FileNotFoundException ex) {
             System.getLogger(LecturaEscrituraCaracteres.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (IOException ex) {
            System.getLogger(LecturaEscrituraCaracteres.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
    
    
    /**
     * Metodo que crea una copia de un fichero creado
     */
    public void copia(){
        
        File copiaEncriptada = new File(".\\prueba_copia.txt");
        
        // Declara la variable para el caracter leído (como un int)
        int caracterLeido; 
                
        // usamos FileReader para leer los caracteres uno por uno
        try (FileReader ficheroIn = new FileReader(fichero);
             FileWriter ficheroOut = new FileWriter(copiaEncriptada)) {
            
            // El método read() devuelve -1 cuando se llega al final del fichero.
            while ((caracterLeido = ficheroIn.read()) != -1) {
                   
                // mi encriptacion consiste en sumarle uno al caracter en código ASCII
                int caracterEncriptado = caracterLeido;
                ficheroOut.write(caracterEncriptado); 
            }
            System.out.println();         
        
        // Se capturan las excepciones de la forma más específica (FileNotFoundException)
        // y la más general para la lectura (IOException).
        } catch (FileNotFoundException ex) {
             System.getLogger(LecturaEscrituraCaracteres.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (IOException ex) {
            System.getLogger(LecturaEscrituraCaracteres.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    
    }


    
}