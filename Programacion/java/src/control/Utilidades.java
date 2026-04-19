/**
 * @author Breixo García Canovacas
 * @author Robinson Tamayo Guerrero
 * @author Romeo Rey Alonso
 * @author Sara Cardeña Carpio 
 */
package control;

import java.util.Date;
import view.Panel_citas;
import view.Panel_clientes;
import view.Panel_empleados;
import view.Panel_talleres;

/**
 * Clase utilitaria para limpiar formularios
 */
public class Utilidades {

	/**
	 * Metodo para limpiar el formulario de citas después de crear una
	 * 
	 * @param panel_cita panel de citas a limpiar
	 */
	public static void limpiarFormularioCitas(Panel_citas panel_cita) {
		System.out.println("Limpiando formulario de citas");
		
		// limpiado de los comboBox
		panel_cita.getCbCliente().setSelectedIndex(0);
		panel_cita.getCbEncargado().setSelectedIndex(0);
		panel_cita.getCbTaller().setSelectedIndex(0);

		// limpiado de los spinner
		panel_cita.getSpFecha().setValue(new Date());
		panel_cita.getSpDuracion().setValue(1);

		// Recargar los trajes del primer cliente (esto necesita el método cargarTrajesPorCliente)
		// Se debe llamar desde el controlador
	}
	
	/**
	 * Metodo para limpiar el formulario de cliente
	 * 
	 * @param panel_cliente panel de clientes a limpiar
	 */
	public static void limpiarFormularioCliente(Panel_clientes panel_cliente) {
		System.out.println("Limpiando formulario de cliente");
		
		panel_cliente.getTfNombre().setText("");
		panel_cliente.getTfColores().setText("");
		panel_cliente.getTfSuperpoder().setText("");
		panel_cliente.getTfNombreT().setText("");
		panel_cliente.getRdbtnDiseno().setSelected(true); // seleccionar diseño por defecto
	}
	
	/**
	 * Metodo para limpiar el formulario de taller
	 * 
	 * @param panel_taller panel de talleres a limpiar
	 */
	public static void limpiarFormularioTaller(Panel_talleres panel_taller) {
		System.out.println("Limpiando formulario de taller");
		
		panel_taller.getTxtNombre().setText("");
		panel_taller.getRdbtnDiseno().setSelected(true);
	}
	
	/**
	 * Metodo para limpiar el formulario de empleado
	 * 
	 * @param panel_empleados panel de empleados a limpiar
	 */
	public static void limpiarFormularioEmpleado(Panel_empleados panel_empleados) {
		System.out.println("Limpiando formulario de empleado");
		
		panel_empleados.getTfNombre().setText("");
		panel_empleados.getTfApellidos().setText("");
		panel_empleados.getTfContrasena().setText("");
		panel_empleados.getTfUsuario().setText("");
		panel_empleados.getRdbtnAprendiz().setSelected(true);
	}
}