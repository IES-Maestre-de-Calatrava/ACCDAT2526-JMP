/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package modelo;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.stream.Collectors;
import modelo.objetos.MiObjectOutputStream;

/**
 *
 * @author Javier Molina-Prados
 * Created on 6 oct 2025
 */
public class LecturaEscrituraObjetos extends Fichero{

    public LecturaEscrituraObjetos(String ruta) {
        super(ruta);
    }
    
    public void escribirObjetos(Object objeto) {
        FileOutputStream ficheroOut = null;
        ObjectOutputStream datosOut = null;

        try {
            if (existeFichero()) {
                ficheroOut = new FileOutputStream(super.getRuta(), true);
                datosOut = new MiObjectOutputStream(ficheroOut);
            } else {
                ficheroOut = new FileOutputStream(super.getRuta());
                datosOut = new ObjectOutputStream(ficheroOut);
            }

            datosOut.writeObject(objeto);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (datosOut != null) datosOut.close();
                if (ficheroOut != null) ficheroOut.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        }

    public ArrayList<Object> lecturaObjetosUniversal(){
        ArrayList<Object> retornoList = new ArrayList();
        
        try (
            FileInputStream ficheroIn = new FileInputStream(super.getRuta());
            ObjectInputStream datosIn = new ObjectInputStream(ficheroIn);)
            {
              while (true){
                  Object retorno = datosIn.readObject();
                  retornoList.add(retorno);
              }  
              
        }catch (FileNotFoundException e){
            e.printStackTrace();
            
        }catch (EOFException ex){
            // AQUI NO ES ERROR, PQ ES LA FORMA DE SABER QUE HEMOS LLEGADO AL FINAL DEL ARCHIVO
            System.out.println("Fin de lectura de objetos.");
        
        }catch(IOException | ClassNotFoundException ex){
            System.getLogger(LecturaEscrituraObjetos.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return retornoList;
    }
    
    
    public static <T> ArrayList<T> convertirArrayList(ArrayList<Object> listaOriginal, Class<T> claseDestino){
        return listaOriginal.stream()
                .filter(claseDestino::isInstance)
                .map(claseDestino::cast)
                .collect(Collectors.toCollection(ArrayList::new));
    }

}
