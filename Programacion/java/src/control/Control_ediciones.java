/**
 * @author Breixo García Canovacas
 * @author Robinson Tamayo Guerrero
 * @author Romeo Rey Alonso
 * @author Sara Cardeña Carpio 
 */
package control;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTable;

import model.Acceso_BD;
import view.Panel_citas;
import view.Panel_clientes;
import view.Panel_empleados;
import view.Panel_talleres;
import view.Panel_x;

/**
 * Clase dedicada al control de las ediciones de elementos
 */
public class Control_ediciones {

	private Acceso_BD modelo;
	private Panel_x panel_x;
	private Panel_citas panel_citas;
	private Panel_clientes panel_clientes;
	private Panel_empleados panel_empleados;
	private Panel_talleres panel_talleres;
	
	// Variables internas para guardar los IDs
	private int idCitaEditando = -1;
	private int idClienteEditando = -1;
	private int idEmpleadoEditando = -1;
	private int idTallerEditando = -1;
	private String nombreTrajeActual = null;

	public Control_ediciones(Acceso_BD modelo, Panel_x panel_x, Panel_citas panel_citas,
			Panel_clientes panel_clientes, Panel_empleados panel_empleados, Panel_talleres panel_talleres) {

		this.modelo = modelo;
		this.panel_x = panel_x;
		this.panel_citas = panel_citas;
		this.panel_clientes = panel_clientes;
		this.panel_empleados = panel_empleados;
		this.panel_talleres = panel_talleres;
	}

	/**
	 * Obtiene la fila seleccionada de la tabla actual
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
		System.out.println("ERROR: No hay ninguna fila seleccionada");
		return null;
	}

	/**
	 * Edita una cita con los datos del formulario
	 */
	public void editarCita() {
		try {
			if (idCitaEditando == -1) {
				System.out.println("ERROR: No hay cita seleccionada para editar");
				return;
			}

			String cliente = (String) panel_citas.getCbCliente().getSelectedItem();
			String taller = (String) panel_citas.getCbTaller().getSelectedItem();
			String traje = (String) panel_citas.getCbTrajes().getSelectedItem();
			String encargado = (String) panel_citas.getCbEncargado().getSelectedItem();

			Date date = (Date) panel_citas.getSpFecha().getValue();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String fecha = sdf.format(date);

			int duracion = (int) panel_citas.getSpDuracion().getValue();

			if (cliente == null || taller == null || traje == null || encargado == null) {
				System.out.println("ERROR: Campos vacíos en el formulario");
				return;
			}

			if (traje.equals("Sin trajes disponibles")) {
				System.out.println("ERROR: El cliente no tiene trajes disponibles");
				return;
			}

			boolean exito = modelo.modificarCita(idCitaEditando, cliente, taller, fecha, duracion, traje, encargado);

			if (exito) {
				System.out.println("EXITO: Cita modificada correctamente");
				idCitaEditando = -1;
				Utilidades.limpiarFormularioCitas(panel_citas);
			} else {
				System.out.println("ERROR: Fallo al modificar la cita");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Edita un cliente con su traje
	 */
	public void editarCliente() {
		try {
			if (idClienteEditando == -1) {
				System.out.println("ERROR: No hay cliente seleccionado para editar");
				return;
			}

			String nombre = panel_clientes.getTfNombre().getText();
			String colores = panel_clientes.getTfColores().getText();
			String superpoder = panel_clientes.getTfSuperpoder().getText();
			String nomTraje = panel_clientes.getTfNombreT().getText();
			String estado = "";

			if (nombre.isEmpty() || colores.isEmpty() || superpoder.isEmpty() || nomTraje.isEmpty()) {
				System.out.println("ERROR: Campos vacios en el formulario de cliente");
				return;
			}

			if (panel_clientes.getRdbtnDiseno().isSelected()) {
				estado = "diseño";
			} else if (panel_clientes.getRdbtnCostura().isSelected()) {
				estado = "costura";
			} else if (panel_clientes.getRdbtnTaller().isSelected()) {
				estado = "taller";
			}

			boolean exito = modelo.modificarClienteConTraje(idClienteEditando, nombre, colores, superpoder,
					nombreTrajeActual, nomTraje, estado);

			if (exito) {
				System.out.println("EXITO: Cliente modificado correctamente");
				idClienteEditando = -1;
				nombreTrajeActual = null;
				Utilidades.limpiarFormularioCliente(panel_clientes);
			} else {
				System.out.println("ERROR: Fallo al modificar el cliente");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Edita un empleado
	 */
	public void editarEmpleado() {
		try {
			if (idEmpleadoEditando == -1) {
				System.out.println("ERROR: No hay empleado seleccionado para editar");
				return;
			}

			String nombre = panel_empleados.getTfNombre().getText();
			String apellidos = panel_empleados.getTfApellidos().getText();
			String usuario = panel_empleados.getTfUsuario().getText();
			String contrasena = panel_empleados.getTfContrasena().getText();
			String categoria = "";

			if (nombre.isEmpty() || apellidos.isEmpty() || usuario.isEmpty() || contrasena.isEmpty()) {
				System.out.println("ERROR: Campos vacios en el formulario de empleado");
				return;
			}

			if (panel_empleados.getRdbtnAprendiz().isSelected()) {
				categoria = "aprendiz";
			} else if (panel_empleados.getRdbtnOficial().isSelected()) {
				categoria = "oficial";
			} else if (panel_empleados.getRdbtnMaestro().isSelected()) {
				categoria = "maestro";
			}

			boolean exito = modelo.modificarEmpleado(idEmpleadoEditando, nombre, apellidos, usuario, categoria, contrasena);

			if (exito) {
				System.out.println("EXITO: Empleado modificado correctamente");
				idEmpleadoEditando = -1;
				Utilidades.limpiarFormularioEmpleado(panel_empleados);
			} else {
				System.out.println("ERROR: Fallo al modificar el empleado");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Edita un taller
	 */
	public void editarTaller() {
		try {
			if (idTallerEditando == -1) {
				System.out.println("ERROR: No hay taller seleccionado para editar");
				return;
			}

			String tipo = "";
			String nombre = panel_talleres.getTxtNombre().getText();

			if (nombre.isEmpty()) {
				System.out.println("ERROR: El nombre del taller no puede estar vacío");
				return;
			}

			if (panel_talleres.getRdbtnCostura().isSelected()) {
				tipo = "costura";
			} else if (panel_talleres.getRdbtnDiseno().isSelected()) {
				tipo = "diseño";
			} else if (panel_talleres.getRdbtnPruebas().isSelected()) {
				tipo = "pruebas";
			}

			boolean exito = modelo.modificarTaller(idTallerEditando, tipo, nombre);

			if (exito) {
				System.out.println("EXITO: Taller modificado correctamente");
				idTallerEditando = -1;
				Utilidades.limpiarFormularioTaller(panel_talleres);
			} else {
				System.out.println("ERROR: Fallo al modificar el taller");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Carga los datos de una cita en el formulario para editar
	 */
	public void cargarCitaParaEditar(Object[] datosCita) {
		// datosCita: [ID, Fecha, Duración, Cliente, Encargado, Taller, Traje]
		
		// Obtener ID
		if (datosCita[0] instanceof Integer) {
			idCitaEditando = (int) datosCita[0];
		} else {
			idCitaEditando = Integer.parseInt((String) datosCita[0]);
		}

		// Cliente
		String nombreCliente = (String) datosCita[3];
		for (int i = 0; i < panel_citas.getCbCliente().getItemCount(); i++) {
			if (panel_citas.getCbCliente().getItemAt(i).equals(nombreCliente)) {
				panel_citas.getCbCliente().setSelectedIndex(i);
				break;
			}
		}

		// Encargado
		String nombreEncargado = (String) datosCita[4];
		for (int i = 0; i < panel_citas.getCbEncargado().getItemCount(); i++) {
			if (panel_citas.getCbEncargado().getItemAt(i).equals(nombreEncargado)) {
				panel_citas.getCbEncargado().setSelectedIndex(i);
				break;
			}
		}

		// Taller
		String nombreTaller = (String) datosCita[5];
		for (int i = 0; i < panel_citas.getCbTaller().getItemCount(); i++) {
			if (panel_citas.getCbTaller().getItemAt(i).equals(nombreTaller)) {
				panel_citas.getCbTaller().setSelectedIndex(i);
				break;
			}
		}

		// Fecha
		String fecha = (String) datosCita[1];
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date fechaDate = sdf.parse(fecha);
			panel_citas.getSpFecha().setValue(fechaDate);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Duracion
		String duracionStr = (String) datosCita[2];
		int duracion = Integer.parseInt(duracionStr.split(" ")[0]);
		panel_citas.getSpDuracion().setValue(duracion);

		// Traje
		String nombreTraje = (String) datosCita[6];
		for (int i = 0; i < panel_citas.getCbTrajes().getItemCount(); i++) {
			if (panel_citas.getCbTrajes().getItemAt(i).equals(nombreTraje)) {
				panel_citas.getCbTrajes().setSelectedIndex(i);
				break;
			}
		}

		System.out.println("Cargada cita con ID: " + idCitaEditando + " para editar");
	}

	/**
	 * Carga los datos de un cliente en el formulario para editar
	 */
	public void cargarClienteParaEditar(Object[] datosCliente) {
		// datosCliente: [ID, Nombre, Colores, Superpoder]
		
		// Obtener ID
		if (datosCliente[0] instanceof Integer) {
			idClienteEditando = (int) datosCliente[0];
		} else {
			idClienteEditando = Integer.parseInt((String) datosCliente[0]);
		}
		
		// Obtener el nombre del traje actual desde la base de datos
		nombreTrajeActual = modelo.obtenerNombreTrajePorCliente(idClienteEditando);

		// Cargar datos en el formulario
		panel_clientes.getTfNombre().setText((String) datosCliente[1]);
		panel_clientes.getTfColores().setText((String) datosCliente[2]);
		panel_clientes.getTfSuperpoder().setText((String) datosCliente[3]);
		
		// Cargar el nombre del traje actual si existe
		if (nombreTrajeActual != null && !nombreTrajeActual.isEmpty()) {
			panel_clientes.getTfNombreT().setText(nombreTrajeActual);
		}

		System.out.println("Cargado cliente con ID: " + idClienteEditando + " para editar");
		System.out.println("Traje actual: " + nombreTrajeActual);
	}

	/**
	 * Carga los datos de un empleado en el formulario para editar
	 */
	public void cargarEmpleadoParaEditar(Object[] datosEmpleado) {
		// datosEmpleado: [ID, Nombre, Apellidos, Apodo, Categoria]
		
		if (datosEmpleado[0] instanceof Integer) {
			idEmpleadoEditando = (int) datosEmpleado[0];
		} else {
			idEmpleadoEditando = Integer.parseInt((String) datosEmpleado[0]);
		}

		panel_empleados.getTfNombre().setText((String) datosEmpleado[1]);
		panel_empleados.getTfApellidos().setText((String) datosEmpleado[2]);
		panel_empleados.getTfUsuario().setText((String) datosEmpleado[3]);

		String categoria = (String) datosEmpleado[4];
		if (categoria.equalsIgnoreCase("aprendiz")) {
			panel_empleados.getRdbtnAprendiz().setSelected(true);
		} else if (categoria.equalsIgnoreCase("oficial")) {
			panel_empleados.getRdbtnOficial().setSelected(true);
		} else if (categoria.equalsIgnoreCase("maestro")) {
			panel_empleados.getRdbtnMaestro().setSelected(true);
		}

		System.out.println("Cargado empleado con ID: " + idEmpleadoEditando + " para editar");
	}

	/**
	 * Carga los datos de un taller en el formulario para editar
	 */
	public void cargarTallerParaEditar(Object[] datosTaller) {
		// datosTaller: [ID, Nombre, Tipo sala]
		
		if (datosTaller[0] instanceof Integer) {
			idTallerEditando = (int) datosTaller[0];
		} else {
			idTallerEditando = Integer.parseInt((String) datosTaller[0]);
		}

		panel_talleres.getTxtNombre().setText((String) datosTaller[1]);

		String tipo = (String) datosTaller[2];
		if (tipo.equalsIgnoreCase("diseño")) {
			panel_talleres.getRdbtnDiseno().setSelected(true);
		} else if (tipo.equalsIgnoreCase("costura")) {
			panel_talleres.getRdbtnCostura().setSelected(true);
		} else if (tipo.equalsIgnoreCase("pruebas")) {
			panel_talleres.getRdbtnPruebas().setSelected(true);
		}

		System.out.println("Cargado taller con ID: " + idTallerEditando + " para editar");
	}
}