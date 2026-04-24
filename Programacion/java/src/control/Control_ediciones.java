/**
 * @author Breixo García Canovacas
 * @author Robinson Tamayo Guerrero
 * @author Romeo Rey Alonso
 * @author Sara Cardeña Carpio 
 */
package control;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTable;

import model.Acceso_BD;
import model.ConsultasCita;
import model.ConsultasCliente;
import model.ConsultasEmpleado;
import model.ConsultasTaller;
import model.ConsultasTraje;
import model.ObtencionID;
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
	private ConsultasCliente consultas_cliente;
	private ConsultasCita consultas_cita;
	private ConsultasTaller consultas_taller;
	private ConsultasEmpleado consultas_empleado;
	private ConsultasTraje consultas_traje;
	private ObtencionID ids;
	
	// Variables internas para guardar los IDs
	private int idCitaEditando = -1;
	private int idClienteEditando = -1;
	private int idEmpleadoEditando = -1;
	private int idTallerEditando = -1;
	private String nombreTrajeActual = null;

	public Control_ediciones(Panel_x panel_x, Panel_citas panel_citas,
			Panel_clientes panel_clientes, Panel_empleados panel_empleados, Panel_talleres panel_talleres) {

		this.modelo = Acceso_BD.instancia();
		this.panel_x = panel_x;
		this.panel_citas = panel_citas;
		this.panel_clientes = panel_clientes;
		this.panel_empleados = panel_empleados;
		this.panel_talleres = panel_talleres;
		Connection conexion = modelo.getConexion();
		this.consultas_cliente = new ConsultasCliente(conexion);
		this.consultas_cita = new ConsultasCita(conexion);
		this.consultas_taller = new ConsultasTaller(conexion);
		this.consultas_empleado = new ConsultasEmpleado(conexion);
		this.consultas_traje = new ConsultasTraje(conexion);
		this.ids = new ObtencionID(conexion);
	}

	/**
	 * Metodo que devuelve un array con los elementos 
	 * de la fila seleccionada en una tabla
	 * @return
	 */
	public Object[] getFilaSeleccionada() {
		JTable tabla = panel_x.getTable();
		//obtener señal de la fila en forma de int
		int filaSeleccionada = tabla.getSelectedRow();

		// si hay fila seleccionada (diferente a -1 que se interpreta como no seleccion)
		if (filaSeleccionada != -1) {
			Object[] fila = new Object[tabla.getColumnCount()];
			for (int i = 0; i < tabla.getColumnCount(); i++) {
				fila[i] = tabla.getValueAt(filaSeleccionada, i);
			}
			return fila;
		}
		System.out.println("No hay ninguna fila seleccionada");
		return null;
	}

	/**
	 * Metodo de edicion de una cita con los datos del formulario
	 * 
	 * @see Acceso_BD#modificarCita(int, String, String, String, int, String, String)
	 */
	public void editarCita() {
		try {
			/*
			 * si en el listener principal se cambia el estado del id de la cita seleccionada
			 * esta clausula if no se activa 
			 */
			if (idCitaEditando == -1) {
				System.out.println("No hay cita seleccionada para editar");
				return;
			}
			
			//guardado de los elementos del formulario
			String cliente = (String) panel_citas.getCbCliente().getSelectedItem();
			String taller = (String) panel_citas.getCbTaller().getSelectedItem();
			String traje = (String) panel_citas.getCbTrajes().getSelectedItem();
			String encargado = (String) panel_citas.getCbEncargado().getSelectedItem();
			
			//Guardado de la fecha 
			Date date = (Date) panel_citas.getSpFecha().getValue();
			//formateo de la fecha segun el formato que esta en el spinner
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String fecha = sdf.format(date);

			int duracion = (int) panel_citas.getSpDuracion().getValue();
			
			// si algun dato esta vacio se lanza un error
			if (cliente == null || taller == null || traje == null || encargado == null) {
				System.out.println("Campos vacíos en el formulario");
				return;
			}
			
			// clausula if exclusiva de trajes debido a su manejo como string 
			if (traje.equals("Sin trajes disponibles")) {
				System.out.println("ERROR: El cliente no tiene trajes disponibles");
				return;
			}
			
			//edicion de la ciita 
			boolean exito = consultas_cita.modificarCita(idCitaEditando, cliente, taller, fecha, duracion, traje, encargado);

			if (exito) {
				System.out.println("Cita modificada correctamente");
				/**
				 * reestablecimiento de la variable del ID para evitar 
				 * llenados del formulario repetidos
				 */
				idCitaEditando = -1;
				Utilidades.limpiarFormularioCitas(panel_citas);
			} else {
				System.out.println("Fallo al modificar la cita");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Metodo de edicion de un cliente con sus traje
	 * 
	 * @see Acceso_BD#modificarCliente(int, String, String, String)
	 */
	public void editarCliente() {
		try {
			if (idClienteEditando == -1) {
				System.out.println("No hay cliente seleccionado para editar");
				return;
			}

			String nombre = panel_clientes.getTfNombre().getText();
			String colores = panel_clientes.getTfColores().getText();
			String superpoder = panel_clientes.getTfSuperpoder().getText();
			String nomTraje = panel_clientes.getTfNombreT().getText();
			String estado = "";
			String alineacion = "";
			
			if(panel_clientes.getRdbtnVillano().isSelected()) {
				alineacion = "Villano";
			} else if(panel_clientes.getRdbtnHeroe().isSelected()) {
				alineacion = "Heroe";
			}

			if (nombre.isEmpty() || colores.isEmpty() || superpoder.isEmpty() || nomTraje.isEmpty() || alineacion.isBlank()) {
				System.out.println("Campos vacios en el formulario de cliente");
				return;
			}			

			//guardado del estado del traje 
			if (panel_clientes.getRdbtnDiseno().isSelected()) {
				estado = "diseño";
			} else if (panel_clientes.getRdbtnCostura().isSelected()) {
				estado = "costura";
			} else if (panel_clientes.getRdbtnTaller().isSelected()) {
				estado = "taller";
			}
			
			//invocacion de la modificacion
			boolean exito = consultas_cliente.modificarClienteConTraje(idClienteEditando, nombre, colores, superpoder,
					nombreTrajeActual, nomTraje, estado);

			if (exito) {
				System.out.println("Cliente modificado correctamente");
				idClienteEditando = -1;
				nombreTrajeActual = null;
				Utilidades.limpiarFormularioCliente(panel_clientes);
			} else {
				System.out.println("Fallo al modificar el cliente");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Metodo de edicion de un empleado con los datos 
	 * del formulario
	 * 
	 * @see Acceso_BD#modificarEmpleado(int, String, String, String, String, String)
	 */
	public void editarEmpleado() {
		try {
			if (idEmpleadoEditando == -1) {
				System.out.println("No hay empleado seleccionado para editar");
				return;
			}

			String nombre = panel_empleados.getTfNombre().getText();
			String apellidos = panel_empleados.getTfApellidos().getText();
			String usuario = panel_empleados.getTfUsuario().getText();
			String contrasena = panel_empleados.getTfContrasena().getText();
			String categoria = "";

			if (nombre.isEmpty() || apellidos.isEmpty() || usuario.isEmpty() || contrasena.isEmpty()) {
				System.out.println("Campos vacios en el formulario de empleado");
				return;
			}

			if (panel_empleados.getRdbtnAprendiz().isSelected()) {
				categoria = "aprendiz";
			} else if (panel_empleados.getRdbtnOficial().isSelected()) {
				categoria = "oficial";
			} else if (panel_empleados.getRdbtnMaestro().isSelected()) {
				categoria = "maestro";
			}

			boolean exito = consultas_empleado.modificarEmpleado(idEmpleadoEditando, nombre, apellidos, usuario, categoria, contrasena);

			if (exito) {
				System.out.println("Empleado modificado correctamente");
				idEmpleadoEditando = -1;
				Utilidades.limpiarFormularioEmpleado(panel_empleados);
			} else {
				System.out.println("Fallo al modificar el empleado");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Metodo de modificaddo de un taller con los datos del formulario
	 * 
	 * @see ConsultasTaller#modificarTaller(int, String, String)
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
			
			// obtencion datos del radio button
			if (panel_talleres.getRdbtnCostura().isSelected()) {
				tipo = "costura";
			} else if (panel_talleres.getRdbtnDiseno().isSelected()) {
				tipo = "diseño";
			} else if (panel_talleres.getRdbtnPruebas().isSelected()) {
				tipo = "pruebas";
			}
			
			//invocacion de la modificacion del taller 
			boolean exito = consultas_taller.modificarTaller(idTallerEditando, tipo, nombre);

			if (exito) {
				System.out.println("Taller modificado correctamente");
				idTallerEditando = -1;
				Utilidades.limpiarFormularioTaller(panel_talleres);
			} else {
				System.out.println("Fallo al modificar el taller");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Metodo que carga en la modificacion los datos seleccionados en la tabla 
	 * 
	 * @param datosCita Array de objetos genericos que contiene los datos de la fila seleccionada
	 */
	public void cargarCitaParaEditar(Object[] datosCita) {
	    // datosCita tiene: [ID, Fecha, Duración, Cliente, Encargado, Taller, Traje]
	    
	    idCitaEditando = (int) datosCita[0];
	    
	    // Asignacion del cliente
	    String nombreCliente = (String) datosCita[3];
	    for (int i = 0; i < panel_citas.getCbCliente().getItemCount(); i++) {
	        if (panel_citas.getCbCliente().getItemAt(i).equals(nombreCliente)) {
	            panel_citas.getCbCliente().setSelectedIndex(i);
	            break;
	        }
	    }
	    
	    // Asignacion del encargado
	    String nombreEncargado = (String) datosCita[4];
	    for (int i = 0; i < panel_citas.getCbEncargado().getItemCount(); i++) {
	        if (panel_citas.getCbEncargado().getItemAt(i).equals(nombreEncargado)) {
	            panel_citas.getCbEncargado().setSelectedIndex(i);
	            break;
	        }
	    }
	    
	    // Asignacion del encargado
	    String nombreTaller = (String) datosCita[5];
	    for (int i = 0; i < panel_citas.getCbTaller().getItemCount(); i++) {
	        if (panel_citas.getCbTaller().getItemAt(i).equals(nombreTaller)) {
	            panel_citas.getCbTaller().setSelectedIndex(i);
	            break;
	        }
	    }
	    
	    //Asignacion de la fecha con su conversion de formato 
	    String fecha = (String) datosCita[1];
	    try {
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	        Date fechaDate = sdf.parse(fecha);
	        panel_citas.getSpFecha().setValue(fechaDate);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    // Asignacion de la duracioon
	    String duracionStr = (String) datosCita[2];
	    //hago el split del String ejem: "1 H" y paso a int el char del indice 0
	    int duracion = Integer.parseInt(duracionStr.split(" ")[0]);
	    panel_citas.getSpDuracion().setValue(duracion);
	    
	    // Asignacion del nombre del traje
	    String nombreTraje = (String) datosCita[6];
	    for (int i = 0; i < panel_citas.getCbTrajes().getItemCount(); i++) {
	        if (panel_citas.getCbTrajes().getItemAt(i).equals(nombreTraje)) {
	            panel_citas.getCbTrajes().setSelectedIndex(i);
	            break;
	        }
	    }
	    
	    //print de debug
	    System.out.println("Cargada cita con ID: " + idCitaEditando + " para editar");
	}

	/**
	 * Metodo que carga los datos de un cliente en el formulario para editar
	 * 
	 * @param datosCliente Array de objetos genericos con los datos del cliente
	 * @see Acceso_BD#obtenerNombreTrajePorCliente(int)
	 */
	public void cargarClienteParaEditar(Object[] datosCliente) {
		// datosCliente: [Alineacion, Nombre, Colores, Superpoder]
		
		// Obtener ID
		idClienteEditando = ids.obtenerIdCliente((String) datosCliente[1]);
		
		// Obtener el nombre del traje actual desde la base de datos
		nombreTrajeActual = consultas_traje.obtenerNombreTrajePorCliente(idClienteEditando);
		
		String alineacion = "";
		//obtener la opcion de alineacion y asignarla
		if("Heroe".equals(alineacion)) {
			panel_clientes.getRdbtnHeroe().setSelected(true);
		} else if("Villano".equals(alineacion)) {
			panel_clientes.getRdbtnVillano().setSelected(true);
		}

		// Cargar datos en el formulario
		panel_clientes.getTfNombre().setText((String) datosCliente[1]);
		panel_clientes.getTfColores().setText((String) datosCliente[2]);
		panel_clientes.getTfSuperpoder().setText((String) datosCliente[3]);
		
		// Cargar el nombre del traje actual si existe
		if (nombreTrajeActual != null && !nombreTrajeActual.isEmpty()) {
			panel_clientes.getTfNombreT().setText(nombreTrajeActual);
		}
		
		//print de debug
		System.out.println("Cargado cliente con ID: " + idClienteEditando + " para editar");
		System.out.println("Traje actual: " + nombreTrajeActual);
	}

	/**
	 * Metodo que carga en el formulario los datos de un empleado a modificar
	 * 
	 * @param datosEmpleado Array de objetos genericos con los datos del empleado
	 */
	public void cargarEmpleadoParaEditar(Object[] datosEmpleado) {
		// datosEmpleado: [ID, Nombre, Apellidos, Apodo, Categoria]
		
		idEmpleadoEditando = ids.obtenerIdEmpleado((String)datosEmpleado[0]);

		panel_empleados.getTfNombre().setText((String) datosEmpleado[0]);
		panel_empleados.getTfApellidos().setText((String) datosEmpleado[1]);
		panel_empleados.getTfUsuario().setText((String) datosEmpleado[2]);
		
		//set de la seleccion en el radioButton
		String categoria = (String) datosEmpleado[3];
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
	 *Metodo que carga los datos de un taller en el formulario para modificar
	 * 
	 */
	public void cargarTallerParaEditar(Object[] datosTaller) {
		// datosTaller: [Nombre, Tipo sala]
		
		//ontencion del id del taller con los datos de la tabla
		idTallerEditando = ids.obtenerIdTaller((String) datosTaller[0],(String) datosTaller[1]);

		panel_talleres.getTxtNombre().setText((String) datosTaller[0]);

		String tipo = (String) datosTaller[1];
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