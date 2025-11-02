/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import modelo.Opositor;
import org.w3c.dom.Element;

/**
 *
 * @author Javier Molina-Prados
 */
public class Examen {
    
    
    // FASE 1: CREAR FICHEROS CON DATOS DE LOS EXAMENES

   public void correccionExamenes(String rutaRespuestas, String rutaPlantilla) throws FileNotFoundException, IOException{
       
       Opositor opositor;
       String plantilla = leerPlantillaCorrectora(rutaPlantilla);
       
       BufferedReader brOpositores = new BufferedReader(new FileReader(".\\estructura_carpetas\\archivos_pruebas\\archivos_suministrados\\opositores.txt"));
       BufferedReader brRespuestas = new BufferedReader(new FileReader(rutaRespuestas));
       
       String lineaOpositor, lineaRespuesta;
       
       while( (lineaOpositor = brOpositores.readLine()) != null){
           opositor = new Opositor();
           
           String [] datos = lineaOpositor.split(";");
           opositor.setDni(datos[0]);
           opositor.setApellidos(datos[1]);
           opositor.setNombre(datos[2]);
           
           lineaRespuesta = brRespuestas.readLine();
           String [] datosRespuestas = lineaRespuesta.split(";");
           opositor.setRespuestas(datosRespuestas.length == 2 ? datosRespuestas[1] : " ");
           
           int nota = Opositor.calcularNota(opositor.getRespuestas(), plantilla);
           opositor.setNota(nota);
           
           
           escribirOpositorFinalArchivo(opositor);
       }
       
       brOpositores.close();
       brRespuestas.close();
       
       generarArchivoTexto();
       
       
   }
   
   
   
   /**
    * Metodo que lee la respuesta de la plantilla correctora para devolver un String de la respuesta
    * @param rutaPlantilla      ruta de la plantilla correctoria
    * @return                   devuelve el contenido de la plantilla correctora en forma de String
    */
    private String leerPlantillaCorrectora(String rutaPlantilla) {
        
        String linea = null;
        try(BufferedReader br = new BufferedReader(new FileReader(rutaPlantilla))){
            linea = br.readLine();
            
        }catch(FileNotFoundException ex){
            System.getLogger(Examen.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }catch(IOException ex){
            System.getLogger(Examen.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);

        }
        
        return linea;

    }
    
    
    public void escribirOpositorFinalArchivo(Opositor opositor) throws FileNotFoundException{
        
        try (RandomAccessFile rmFile = new RandomAccessFile(".\\estructura_carpetas\\archivos_pruebas\\binario_acceso_aleatorio\\datos_finales.dat", "rw");){
        
            // Mover el puntero directamente al final del archivo antes de escribir.
            rmFile.seek(rmFile.length()); 

            // Ahora el ID se calcula correctamente usando la nueva longitud del archivo, esto es para calcular el id osea la posicion del id en el registro
            long id = rmFile.length() / Opositor.getLONGITUD_TOTAL() + 1;

            rmFile.writeLong(id);
                  
            escribirTexto(rmFile, opositor.getDni(), opositor.getCARACTERES_DNI());
            escribirTexto(rmFile, opositor.getApellidos(), opositor.getCARACTERES_APELLIDOS());
            escribirTexto(rmFile, opositor.getNombre(), opositor.getCARACTERES_NOMBRE());
            escribirTexto(rmFile, opositor.getRespuestas(), opositor.getCARACTERES_RESPUESTAS());
            
            rmFile.writeInt(opositor.getNota());
            
        }catch(FileNotFoundException ex){
            System.getLogger(Examen.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }catch(IOException ex){
            System.getLogger(Examen.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);

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
    
    public void generarArchivoTexto() throws FileNotFoundException, IOException{
                   
            long posicion;
            try (
                    RandomAccessFile raf = new RandomAccessFile(".\\estructura_carpetas\\archivos_pruebas\\binario_acceso_aleatorio\\datos_finales.dat", "r");
                    BufferedWriter bw = new BufferedWriter(new FileWriter(".\\estructura_carpetas\\archivos_pruebas\\binario_acceso_aleatorio\\datos_finales.txt"))
                ){
                
                while (raf.getFilePointer() < raf.length()) {
                    
                    long id = raf.readLong();
                    String dni = leerCampoTexto(raf, Opositor.getLONGITUD_DNI_EN_BYTES());
                    String apellidos = leerCampoTexto(raf, Opositor.getLONGITUD_APELLIDOS_EN_BYTES());
                    String nombre = leerCampoTexto(raf, Opositor.getLONGITUD_NOMBRE_EN_BYTES());
                    String respuestas = leerCampoTexto(raf, Opositor.getLONGITUD_RESPUESTAS_EN_BYTES());
                    int nota = raf.readInt();
                        
                    bw.write("id: "+id+", Dni: "+dni+", Apellidos: "+apellidos+", Nombre: "+nombre+", Respuestas: "+respuestas+", Nota: "+nota);
                    bw.newLine(); // o bw.write("\n")
                }
                
            }catch(FileNotFoundException ex){
            System.getLogger(Examen.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }catch(IOException ex){
                System.getLogger(Examen.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);

            }
    }
    
    
    // FASE 2: RECLAMACIONES
    
    public void reclamacion(long id, String respuestas) throws IOException{
        
        long posicion = 0;
        String plantilla = leerPlantillaCorrectora(".\\estructura_carpetas\\archivos_pruebas\\archivos_suministrados\\plantilla_correctora.txt");

        try(
                RandomAccessFile raf = new RandomAccessFile(".\\estructura_carpetas\\archivos_pruebas\\binario_acceso_aleatorio\\datos_finales.dat", "rw")
            ){
            
            // 1º Colocamos el puntero en el id del opositor 
            posicion = (id-1)*Opositor.getLONGITUD_TOTAL();
            
            // 2º Comprobamos que existe el opositor
            if (posicion >= raf.length() || posicion < 0) {
            System.out.println("El ID " + id + " no existe");
            return;
            }
            
            //3º Colocamos el puntero y leemos el registro del opositor creando un objeto opositor
            raf.seek(posicion);
            Opositor opositor = leerOpositor(raf); // Funcion que me lee todo el registro del opositor

            //4º Una vez leemos el opositor cambiamos los campos que se nos pide
            opositor.setRespuestas(respuestas);
            
            int nota = Opositor.calcularNota(respuestas, plantilla);
            opositor.setNota(nota);
            
            // 5º Por ultimo, escribimos los cambios en el fichero de datos binarios
            long inicioRespuestas = posicion+ 
                    Opositor.getLONGITUD_IDENTIFICADOR() + 
                    Opositor.getLONGITUD_DNI_EN_BYTES() + 
                    Opositor.getLONGITUD_APELLIDOS_EN_BYTES() + 
                    Opositor.getLONGITUD_NOMBRE_EN_BYTES();
                        
           
            raf.seek(inicioRespuestas);
            
            escribirTexto(raf, respuestas, opositor.getCARACTERES_RESPUESTAS());
            
            raf.writeInt(nota);

            System.out.println("id: "+opositor.getId()+", Dni: "+opositor.getDni()+", Apellidos: "+opositor.getApellidos()+", Nombre: "+opositor.getNombre()+", Respuestas: "+respuestas+", Nota: "+nota);
            
        }catch(FileNotFoundException ex){
            System.getLogger(Examen.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }catch(IOException ex){
            System.getLogger(Examen.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);

        }
        
        generarArchivoTexto();

        
    }
    
    
    private Opositor leerOpositor(RandomAccessFile raf) throws IOException {
    // Nota: El método readLong() moverá el puntero.
        long id = raf.readLong(); 

        // Los métodos leerCampoTexto y las constantes ya deben estar definidos
        String dni = leerCampoTexto(raf, Opositor.getLONGITUD_DNI_EN_BYTES());
        String apellidos = leerCampoTexto(raf, Opositor.getLONGITUD_APELLIDOS_EN_BYTES());
        String nombre = leerCampoTexto(raf, Opositor.getLONGITUD_NOMBRE_EN_BYTES());
        String respuestas = leerCampoTexto(raf, Opositor.getLONGITUD_RESPUESTAS_EN_BYTES());
        int nota = raf.readInt();

        // Creamos y llenamos el objeto
        Opositor opositor = new Opositor();
        // Asumiendo que tienes un setter para el ID (o lo pasas como parámetro si no es un atributo)
        opositor.setId(id); 
        opositor.setDni(dni);
        opositor.setApellidos(apellidos);
        opositor.setNombre(nombre);
        opositor.setRespuestas(respuestas);
        opositor.setNota(nota);

        return opositor;
    }
    
    
    
    // FASE 3: ARBOLDOM, XML, HTML
    
    /**
     * Lee todos los registros del archivo binario y los devuelve como una lista de Opositores.
     * @return Lista de objetos Opositor.
     * @throws IOException 
     */
    public List<Opositor> obtenerOpositoresDelBinario() throws IOException {
        List<Opositor> opositores = new ArrayList<>();

        // Ruta del archivo binario
        String rutaArchivo = ".\\estructura_carpetas\\archivos_pruebas\\binario_acceso_aleatorio\\datos_finales.dat";

        try (RandomAccessFile raf = new RandomAccessFile(rutaArchivo, "r")) {

            while (raf.getFilePointer() < raf.length()) {
                // Reutiliza la lógica de lectura completa
                Opositor opositor = leerOpositor(raf); 
                opositores.add(opositor);
            }

        } catch (FileNotFoundException ex) {
            System.getLogger(Examen.class.getName()).log(System.Logger.Level.ERROR, "Archivo binario no encontrado.", ex);
        } catch (IOException ex) {
            System.getLogger(Examen.class.getName()).log(System.Logger.Level.ERROR, "Error de I/O al leer el archivo binario.", ex);
            throw ex; // Relanzar la excepción para que el main la maneje
        }
        return opositores;
    }
    
    /**
     * Lee todos los registros del archivo binario, construye un árbol DOM (XML) 
     * con esos datos y lo guarda en un archivo.
     */
    public void crearArbolDOM() {

        // Aquí inicializamos el objeto que contiene la lógica DOM,
        // asumiendo que está en el mismo paquete o importado.
        GestionContenidoDOM gestorDOM = new GestionContenidoDOM("Opositores");

        try {
            // 1. Obtener la lista de opositores del archivo binario
            List<Opositor> opositores = this.obtenerOpositoresDelBinario();

            // 2. Iterar y construir el árbol DOM
            for (Opositor opositor : opositores) {
                
                if(opositor.getNota() >= 5){

                // a) Agregar el nodo padre <opositor>
                Element nodoOpositor = gestorDOM.addNodo("opositor"); 

                // b) Agregar los campos como nodos hijo de <opositor>
                // Usamos .trim() para limpiar los caracteres nulos del relleno de campo fijo

                gestorDOM.addNodoYTexto("id", String.valueOf(opositor.getId()), nodoOpositor); 
                gestorDOM.addNodoYTexto("dni", opositor.getDni().trim(), nodoOpositor); 
                gestorDOM.addNodoYTexto("apellidos", opositor.getApellidos().trim(), nodoOpositor); 
                gestorDOM.addNodoYTexto("nombre", opositor.getNombre().trim(), nodoOpositor);
                gestorDOM.addNodoYTexto("respuestas", opositor.getRespuestas().trim(), nodoOpositor);
                gestorDOM.addNodoYTexto("nota", String.valueOf(opositor.getNota()), nodoOpositor); 
                
                }
            }

            // 3. Generar el archivo XML con indentación
            String rutaSalida = ".\\estructura_carpetas\\archivos_pruebas\\xml_html\\datos_opositores.xml";
            gestorDOM.generarArchivodelDOM(rutaSalida, "yes");

            System.out.println("✅ Árbol DOM creado y guardado en: " + rutaSalida);

            // (Opcional) Mostrar el XML por consola
            System.out.println("\n--- Contenido XML ---");
            gestorDOM.mostrarPorPantalla("yes");

        } catch (IOException e) {
            System.err.println("❌ Error al procesar archivos binarios: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("❌ Error inesperado al construir el DOM: " + e.getMessage());
        }
    }
    
    
     // FASE 4: COPIA DE SEGURIDAD
    
    /* OPCIÓN 1 */
    public void copiaSeguridad(){
        
        try {     
            Path origen = Paths.get(".\\estructura_carpetas\\archivos_pruebas");
            Path destino = Paths.get(".\\estructura_carpetas\\copia_seguridad");
            
            Files.walkFileTree(origen, new SimpleFileVisitor<Path>() {
                
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    Path destinoDir = destino.resolve(origen.relativize(dir)); //Devuelve la ruta relativa desde origen hasta dir
                    if (!Files.exists(destinoDir)) {
                        Files.createDirectories(destinoDir);
                    }
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFile(Path archivo, BasicFileAttributes attrs) throws IOException {
                    Path destinoArchivo = destino.resolve(origen.relativize(archivo));
                    Files.copy(archivo, destinoArchivo, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.COPY_ATTRIBUTES);
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException ex) {
            System.getLogger(Examen.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    /* OPCIÓN 2 */    
    public void copiaSeguridadV2(){
        try {
            
            Path origen = Paths.get(".\\estructura_carpetas\\archivos_pruebas");
            Path destino = Paths.get(".\\estructura_carpetas\\copia_seguridad");
            
            copiaSeguridadV2(origen, destino);
        
        } catch (IOException ex) {
            System.getLogger(Examen.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
    
    private void copiaSeguridadV2(Path origen, Path destino) throws IOException {
        File carpetaOrigen = origen.toFile();

        if (carpetaOrigen.isDirectory()) {
            // Crear el directorio en destino si no existe
            if (!Files.exists(destino)) {
                Files.createDirectories(destino);
            }

            // Listar archivos y subdirectorios usando File[]
            File[] entradas = carpetaOrigen.listFiles();
            if (entradas != null) {
                for (File entrada : entradas) {
                    Path entradaPath = entrada.toPath();
                    Path destinoEntrada = destino.resolve(entrada.getName()); //Añade el nombre del archivo (entrada.getName()) al directorio destino (destino.resolve)
                    copiaSeguridadV2(entradaPath, destinoEntrada); // llamada recursiva
                }
            }
        } else {
            // Copiar archivo con atributos, sobrescribiendo si ya existe
            Files.copy(origen, destino, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.COPY_ATTRIBUTES);
        }
    }

}
