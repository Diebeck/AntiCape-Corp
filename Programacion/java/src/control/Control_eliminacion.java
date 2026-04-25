/**
 * @author Breixo García Canovacas
 * @author Robinson Tamayo Guerrero
 * @author Romeo Rey Alonso
 * @author Sara Cardeña Carpio 
 */
package control;

import java.sql.Connection;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import model.Acceso_BD;
import model.ConsultasCita;
import model.ConsultasCliente;
import model.ConsultasEmpleado;
import model.ConsultasTaller;
import model.ObtencionID;
import view.Panel_x;

/**
 * Clase dedicada al control de las eliminaciones de elementos
 */
public class Control_eliminacion {

	private Acceso_BD modelo;
	private Panel_x panel_x;	
	private ConsultasCliente consultas_cliente;
	private ConsultasCita consultas_cita;
	private ConsultasTaller consultas_taller;
	private ConsultasEmpleado consultas_empleado;
	private ObtencionID ids;

	public Control_eliminacion(Panel_x panel_x) {
		this.modelo = Acceso_BD.instancia();
		this.panel_x = panel_x;
		Connection conexion = modelo.getConexion();
		this.consultas_cliente = new ConsultasCliente(conexion);
		this.consultas_cita = new ConsultasCita(conexion);
		this.consultas_taller = new ConsultasTaller(conexion);
		this.consultas_empleado = new ConsultasEmpleado(conexion);
		this.ids = new ObtencionID(conexion);
	}
	
	/**
	 * Muestra un diálogo de confirmación con estilo
	 * 
	 * descubri que podemos usar html en la configuracion del dialog :)
	 */
	private boolean mostrarConfirmacion(String titulo, String mensaje) {
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
	 * Metodo que devuelve la fila seleccionada de la tabla actual
	 */
	public Object[] getFilaSeleccionada() {
		JTable tabla = panel_x.getTable();
		int filaSeleccionada = tabla.getSelectedRow();

		if (filaSeleccionada != -1) {
			Object[] fila = new Object[tabla.getColumnCount()];
			for (int i = 0; i < tabla.getColumnCount(); i++) {
				fila[i] = tabla.getValueAt(filaSeleccionada, i);
			}
			return fila;
		}
		JOptionPane.showMessageDialog(null, "No hay ninguna fila seleccionada", "Error", JOptionPane.ERROR_MESSAGE);
		return null;
	}

	/**
	 * Metodo de eliminacion de una cita 
	 */
	public boolean eliminarCita() {
		Object[] fila = getFilaSeleccionada();
		// escapa si no hay seleccion
		if (fila == null) {
			return false;
		}
		
		//almacenado de los datos del array que arroja la fila seleccionada
		String encargado = (String) fila[0];
		String fecha = (String) fila[1];
		String hora = (String) fila[2];
		String cliente = (String) fila[4];
		
		int id = ids.obtenerIdCita(encargado, fecha, hora);
		
		//Mensaje que se le pasa al dialog y se reemplaza en la parte de mensaje.replace
		String mensaje = "¿Estás seguro de que deseas eliminar la cita?\n\n"
				+ "ID: " + id + "\n"
				+ "Fecha: " + fecha + "\n"
				+ "Cliente: " + cliente + "\n\n";
		
		if (!mostrarConfirmacion("Confirmar eliminación", mensaje)) {
			System.out.println("Eliminación cancelada por el usuario");
			return false;
		}

		System.out.println("Eliminando cita con ID: " + id);
		//invocacion del metodo de eliminacion
		boolean exito = consultas_cita.eliminarCita(id);

		if (exito) {
			JOptionPane.showMessageDialog(null, "Cita eliminada correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "Error al eliminar la cita", "Error", JOptionPane.ERROR_MESSAGE);
		}

		return exito;
	}

	/**
	 * Metodo de eliminacion de un cliente 
	 */
	public boolean eliminarCliente() {
		Object[] fila = getFilaSeleccionada();
		if (fila == null) {
			return false;
		}

		int id = ids.obtenerIdCliente((String) fila[0]);
		String nombre = (String) fila[0];
		String colores = (String) fila[1];
		
		String mensaje = "¿Estás seguro de que deseas eliminar este cliente?\n\n"
				+ "ID: " + id + "\n"
				+ "Nombre: " + nombre + "\n"
				+ "Colores: " + colores + "\n\n"
				+ "ADVERTENCIA: También se eliminará su traje asociado.\n";
		
		if (!mostrarConfirmacion("Confirmar eliminación", mensaje)) {
			System.out.println("Eliminación cancelada por el usuario");
			return false;
		}

		System.out.println("Eliminando cliente: " + nombre + " (ID: " + id + ")");
		boolean exito = consultas_cliente.eliminarClienteConTraje(id);

		if (exito) {
			JOptionPane.showMessageDialog(null, "Cliente y su traje eliminados correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "Error al eliminar el cliente", "Error", JOptionPane.ERROR_MESSAGE);
		}

		return exito;
	}

	/**
	 * Metodo de eliminacion de un empleado 
	 */
	public boolean eliminarEmpleado() {
		Object[] fila = getFilaSeleccionada();
		if (fila == null) {
			return false;
		}

		int id = ids.obtenerIdEmpleado((String) fila[0]);
		String nombre = (String) fila[0];
		String apellidos = (String) fila[1];
		String categoria = (String) fila[3];
		
		String mensaje = "¿Estás seguro de que deseas eliminar este empleado?\n\n"
				+ "ID: " + id + "\n"
				+ "Nombre: " + nombre + " " + apellidos + "\n"
				+ "Categoría: " + categoria + "\n\n";
		
		if (!mostrarConfirmacion("Confirmar eliminación", mensaje)) {
			System.out.println("Eliminación cancelada por el usuario");
			return false;
		}

		System.out.println("Eliminando empleado: " + nombre + " (ID: " + id + ")");
		boolean exito = consultas_empleado.eliminarEmpleado(id);

		if (exito) {
			JOptionPane.showMessageDialog(null, "Empleado eliminado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "No se puede eliminar un empleado con una cita asignada", "Error", JOptionPane.ERROR_MESSAGE);
		}

		return exito;
	}

	/**
	 * Metodo de eliminacion de un taller
	 */
	public boolean eliminarTaller() {
		Object[] fila = getFilaSeleccionada();
		if (fila == null) {
			return false;
		}

		int id = ids.obtenerIdTallerCompleto((String )fila[0], (String )fila[1] );
		String nombre = (String) fila[0];
		String tipo = (String) fila[1];
		
		String mensaje = "¿Estás seguro de que deseas eliminar este taller?\n\n"
				+ "ID: " + id + "\n"
				+ "Tipo: " + tipo + "\n"
				+ "Nombre: " + nombre + "\n\n";
		
		if (!mostrarConfirmacion("Confirmar eliminación", mensaje)) {
			System.out.println("Eliminación cancelada por el usuario");
			return false;
		}

		System.out.println("Eliminando taller: " + nombre + " (ID: " + id + ")");
		boolean exito = consultas_taller.eliminarTaller(id);

		if (exito) {
			JOptionPane.showMessageDialog(null, "Taller eliminado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "Error al eliminar el taller", "Error", JOptionPane.ERROR_MESSAGE);
		}

		return exito;
	}
}