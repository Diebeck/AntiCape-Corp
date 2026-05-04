/**
 * @author Breixo García Canovacas
 * @author Robinson Tamayo Guerrero
 * @author Romeo Rey Alonso
 * @author Sara Cardeña Carpio 
 */
package view;

import javax.swing.JOptionPane;

/**
 * Clase que modela las plantillas 
 * para las ventanad emergentes de confirmacion o error
 */
public class Confirmaciones {

	/**
	 * Muestra un diálogo de confirmación con estilo
	 * 
	 * descubri que podemos usar html en la configuracion del dialog :)
	 */
	public boolean mostrarEliminacion(String titulo, String mensaje) {
	    // Formato HTML con colores y fuente de la empresa
	    String mensajeHTML = "<html>"
	        + "<div style='font-family: Century Schoolbook; font-size: 14px; text-align: left;"
	        + "; display: flex; justify-content: center'>"
	        + "<p style='color: #8B0000; font-weight: bold; font-size: 14px;'>¡ATENCIÓN!</p>"
	        + "<p>" + mensaje.replace("\n", "<br>") + "</p>"
	        + "<hr style='border: 1px solid #8B0000;'>"
	        + "<p style='color: #666; font-size: 12px;'>Esta acción no se puede deshacer.</p>"
	        + "</div></html>";
	    
	    //creacion directa del dialog
	    int respuesta = JOptionPane.showConfirmDialog(
	        null, 
	        mensajeHTML, 
	        titulo, 
	        //opciones del dialog
	        JOptionPane.YES_NO_OPTION,
	        //icono del dialog
	        JOptionPane.WARNING_MESSAGE
	    );
	    return respuesta == JOptionPane.YES_OPTION;
	}
	
	/**
	 * Muestra un diálogo de éxito con solo botón OK
	 * 
	 * @param titulo Título del diálogo
	 * @param mensaje Mensaje a mostrar 
	 */
	public void mostrarExito(String titulo, String mensaje) {
	    String mensajeHTML = "<html>"
	        + "<div style='font-family: Century Schoolbook; font-size: 14px; text-align: left;'>"
	        + "<p style='color: #2E8B57; font-weight: bold; font-size: 18px;'>ÉXITO</p>"
	        + "<p>" + mensaje.replace("\n", "<br>") + "</p>"
	        + "<hr style='border: 1px solid #2E8B57;'>"
	        + "<p style='color: #666; font-size: 12px;'>Operación completada correctamente.</p>"
	        + "</div></html>";
	    
	    JOptionPane.showMessageDialog(
	        null, 
	        mensajeHTML, 
	        titulo, 
	        JOptionPane.INFORMATION_MESSAGE
	    );
	}
	
	/**
	 * Muestra un diálogo de error con solo botón OK
	 * 
	 * @param titulo Título del diálogo
	 * @param mensaje Mensaje a mostrar 
	 */
	public void mostrarError(String titulo, String mensaje) {
	    String mensajeHTML = "<html>"
	        + "<div style='font-family: Century Schoolbook; font-size: 14px; text-align: left;'>"
	        + "<p style='color: #8B0000; font-weight: bold; font-size: 18px;'>ERROR</p>"
	        + "<p>" + mensaje.replace("\n", "<br>") + "</p>"
	        + "<hr style='border: 1px solid #8B0000;'>"
	        + "<p style='color: #666; font-size: 12px;'>Consulte al administrador si el problema persiste.</p>"
	        + "</div></html>";
	    
	    JOptionPane.showMessageDialog(
	        null, 
	        mensajeHTML, 
	        titulo, 
	        JOptionPane.ERROR_MESSAGE
	    );
	}

}
