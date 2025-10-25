
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Optional;
import modelo.Departamento;
import modelo.OperacionesBBDD;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license



/**
 *
 * @author b15-11m
 */
public class Conexionoracle {

    private static OperacionesBBDD bbdd = OperacionesBBDD.getInstancia();
    private static Optional<ResultSet> rs;
    
    public static void main(String[] args) {
        
        
        /*
        Departamento departamento = new Departamento();
        departamento.SelectById(bbdd, 20);
        System.out.println(departamento);
        */
        
        // Ejemplo mostrar todos los registros
        
        Departamento.MostrarResultado(Departamento.selectAll(bbdd));
        
    }
}
