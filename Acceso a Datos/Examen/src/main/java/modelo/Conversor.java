/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package modelo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 *
 * @author Javier Molina-Prados
 * Created on 14 oct 2025
 */
public class Conversor {
    
    private Source origenDatos = null;
    private Source hojaEstilos = null;
    private FileOutputStream pagHTML = null;

    public Conversor() {
    }
    
    public Conversor(String origenDatos, String hojaEstilos, String pagHTML){
        
        try {
        this.origenDatos = new StreamSource (origenDatos);
        this.hojaEstilos = new StreamSource (hojaEstilos);
        this.pagHTML = new FileOutputStream (new File (pagHTML));
        
        } catch(FileNotFoundException ex){
          System.getLogger(Conversor.class.getCanonicalName()).log(System.Logger.Level.ERROR, (String) null, ex); 
        } 
    }
    
    public void ConvertirAHTML() throws TransformerException{
        
        try{
        Result result = new StreamResult(pagHTML);
        
        Transformer transformer = TransformerFactory.newInstance().newTransformer(this.hojaEstilos);
        
        transformer.transform(this.origenDatos, result);
       
        } catch(TransformerConfigurationException e){
            e.printStackTrace();
        } catch(TransformerException e){
            e.printStackTrace();
        } finally{
            try{
                this.pagHTML.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
