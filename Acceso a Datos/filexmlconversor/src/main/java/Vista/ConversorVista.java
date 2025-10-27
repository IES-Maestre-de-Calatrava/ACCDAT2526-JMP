package vista;

/**
 * Clase que gestiona la presentación de mensajes al usuario (la Vista).
 *
 * @author Javier Molina-Prados
 */
public class ConversorVista {

    /**
     * Muestra un mensaje de inicio de la conversión.
     */
    public void mostrarInicioConversor() {
        System.out.println("🚀 Iniciando la conversión de XML + XSLT a HTML...");
    }

    /**
     * Muestra un mensaje de éxito.
     * @param rutaSalida La ruta donde se guardó el archivo HTML.
     */
    public void mostrarExito(String rutaSalida) {
        System.out.println("✅ Conversión completada con éxito.");
        System.out.println("Archivo HTML generado en: " + rutaSalida);
    }

    /**
     * Muestra un mensaje de error.
     * @param mensajeError El mensaje de la excepción o el error.
     */
    public void mostrarError(String mensajeError) {
        System.err.println("❌ ERROR durante la conversión.");
        System.err.println("Detalles: " + mensajeError);
    }
}