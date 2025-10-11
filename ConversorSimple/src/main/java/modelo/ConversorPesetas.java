/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package modelo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Javier Molina-Prados
 * Created on 9 oct 2025
 */
public class ConversorPesetas extends Conversor{
    private BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));

    
    public ConversorPesetas() {
        super();
    }
    
    public double eurosAPesetas(double cantidad){
        return eurosAMonedas(cantidad);
    }
    
    public double pesetasAEuros(double cantidad){
        return monedasAEuros(cantidad);
    }
    
}
