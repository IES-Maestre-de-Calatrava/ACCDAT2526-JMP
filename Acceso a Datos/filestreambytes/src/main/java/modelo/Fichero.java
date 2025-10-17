/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package modelo;

import java.io.File;

/**
 *
 * @author Javier Molina-Prados
 * Created on 30 sept 2025
 */
public class Fichero {
    
    private File ruta;
    
    public Fichero(String ruta){
        this.ruta = new File(ruta);
    }
    
    public String getPathRuta(){
        return ruta.getAbsolutePath();
    }

    public File getRuta() {
        return ruta;
    }

    public void setRuta(File ruta) {
        this.ruta = ruta;
    }

    public boolean existeFichero(){
        if (ruta.exists()){
            return true;
        }else{
            return false;
        }
    }
    
}
