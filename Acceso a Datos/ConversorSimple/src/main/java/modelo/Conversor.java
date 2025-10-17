/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package modelo;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
/**
 *
 * @author Javier Molina-Prados 
 * Created on 9 sept 2025
 */
public class Conversor {
    
    private BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    private final double cambio;
    public final double comision;

    public Conversor() {
        this.cambio = 166.386D;
        this.comision = preguntarComision();
    }
      
    public Double preguntarComision(){
        String s = null;
        Double comision;
           
        System.out.println("Escriba la comision que quiere aplicar a las operaciones, tenga en cuenta que serña el porcentaje a cobrar:");
        try{
            s = buffer.readLine();        
            comision = Double.parseDouble(s);
            if (comision < 1){
                return comision;
            }
            else{
                System.out.println("La comision no puede ser mayor o igual que 1");
                return preguntarComision();
            }
        }catch(IOException | NumberFormatException e){
            System.out.println("La comision tiene que ser del formato 99.99");
            return preguntarComision();
        }
    }
    
    public double eurosAMonedas(double cantidad){
        return (cantidad*cambio)*(1 - comision);
    } 
    
    public double monedasAEuros(double cantidad){
        return (cantidad/cambio)*(1-comision);
    }

    public double getComision() {
        return comision;
    }
    
    
}

