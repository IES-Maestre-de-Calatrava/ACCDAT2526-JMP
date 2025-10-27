/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.util.List;
import modelo.Empleado;
import modelo.GestionContenidoDOM;
import org.w3c.dom.Element;

/**
 *
 * @author belen
 */
public class Control {
    private GestionContenidoDOM gestor;
    
    public Control(String nombreRaiz){
        gestor = new GestionContenidoDOM(nombreRaiz);
    }
    
    /**
     * Agregamos un nodo empleado
     * @param emple             Empleado a añadir
     */
    public void agregarEmpleado(Empleado emple){
        Element nodoEmple = gestor.addNodo("Empleado");
        
        gestor.addNodoYTexto("identificador", String.valueOf(emple.getIdentificador()), nodoEmple);
        gestor.addNodoYTexto("apellido", emple.getApellido(), nodoEmple);
        gestor.addNodoYTexto("departamento", String.valueOf(emple.getDepartamento()), nodoEmple);
        gestor.addNodoYTexto("salario", String.valueOf(emple.getSalario()), nodoEmple);
    }
    
    /**
     * Guarda un archivo en una ruta dada
     * @param ruta                  Ruta donde se guardará el archivo
     * @param indent                Indentación
     */
    public void guardarArchivo(String ruta, String indent) {
        gestor.generarArchivodelDOM(ruta, indent);
    }
    
    /**
     * Muestra el archivo por pantalla
     * @param indent            Indentación
     */
    public void mostrarArchivo(String indent){
        gestor.mostrarPorPantalla(indent);
    }
    
    /**
     * Carga un archivo desde una ruta dada
     * @param ruta              Ruta dada
     */
    public void cargarArchivo(String ruta){
        gestor.cargarArchivoEnMemoria(ruta);
    }
    
    /**
     * Devuelve una lista con los empleados
     * @return 
     */
    public List<Empleado> obtenerEmpleados(){
        return gestor.getEmpleados();
    }
    
    /**
     * Modifica el salario de un empleado
     * @param id                    ID del empleado
     * @param nuevoSalario          Nuevo salario del empleado
     */
    public void modificarSalario(int id, double nuevoSalario){
        gestor.modificarSalario(id, String.valueOf(nuevoSalario));
    }
    
    /**
     * Elimina un elemento hijo del elemento padre
     * @param padre                 Elemento padre
     * @param hijo                  Elemento hijo a eleminar
     */
    public void eleminarCampo(String padre, String hijo){
        gestor.eliminarElementEmpleados(padre, hijo);
    }
    
    /**
     * Añade un cargo por defecto a un empleado
     */
    public void añadirCargoPorDefecto(){
        gestor.addCargoEmpleado();
    }
}
