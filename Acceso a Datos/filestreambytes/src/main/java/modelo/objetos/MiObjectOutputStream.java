/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package modelo.objetos;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/**
 *
 * @author Javier Molina-Prados
 * Created on 6 oct 2025
 */
public class MiObjectOutputStream extends ObjectOutputStream {
    
    public MiObjectOutputStream(OutputStream out) throws IOException{
        super(out);
    }

    
    public MiObjectOutputStream() throws IOException, SecurityException{
        super();
    }
    
    protected void writeStreamHeader() throws IOException {
        reset();
    }
}
