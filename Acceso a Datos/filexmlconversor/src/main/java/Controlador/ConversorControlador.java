package controlador;

import javax.xml.transform.TransformerException;
import modelo.Conversor;
import vista.ConversorVista;

/**
 * Clase que actúa como Controlador en el patrón MVC.
 * Gestiona la lógica de la aplicación y la interacción entre el Modelo y la Vista.
 *
 * @author Javier Molina-Prados
 */
public class ConversorControlador {

    private final Conversor modelo;
    private final ConversorVista vista;
    
    // Parámetros de la conversión (pueden ser dinámicos, pero aquí usamos los que tenías)
    private final String origenDatos;
    private final String plantilla;
    private final String salida;

    /**
     * Constructor del controlador.
     * @param origenDatos Ruta del archivo XML de origen.
     * @param plantilla Ruta de la hoja de estilos XSLT.
     * @param salida Ruta donde se guardará el archivo HTML.
     */
    public ConversorControlador(String origenDatos, String plantilla, String salida) {
        this.origenDatos = origenDatos;
        this.plantilla = plantilla;
        this.salida = salida;
        // Inicializar la Vista
        this.vista = new ConversorVista();
        // Inicializar el Modelo
        this.modelo = new Conversor(this.origenDatos, this.plantilla, this.salida);
    }

    /**
     * Método principal para ejecutar la conversión.
     */
    public void ejecutarConversor() {
        
        vista.mostrarInicioConversor();

        try {
            // Llama a la lógica de negocio (el Modelo)
            modelo.ConvertirAHTML();
            
            // Informa a la Vista del éxito
            vista.mostrarExito(this.salida);
            
        } catch (TransformerException ex) {
            // Informa a la Vista del error si algo falla
            vista.mostrarError(ex.getMessage());
        }
    }
}