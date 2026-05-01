/**
 * @author Breixo García Canovacas
 * @author Robinson Tamayo Guerrero
 * @author Romeo Rey Alonso
 * @author Sara Cardeña Carpio 
 */
package control;

import java.awt.FlowLayout;
import java.awt.Font;
import java.sql.Connection;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;

import model.Acceso_BD;
import model.Cliente;
import model.ConsultasCita;
import model.ConsultasCliente;
import model.ConsultasEmpleado;
import model.ConsultasTaller;
import model.ConsultasTraje;
import model.ObtencionID;
import model.Traje;
import view.Confirmaciones;
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
	private Confirmaciones confirm;

	// Variables internas para guardar los IDs
	private int idCitaEditando = -1;
	private int idClienteEditando = -1;
	private int idEmpleadoEditando = -1;
	private int idTallerEditando = -1;
	private String nombreTrajeActual = null;

	public Control_ediciones(Panel_x panel_x, Panel_citas panel_citas, Panel_clientes panel_clientes,
			Panel_empleados panel_empleados, Panel_talleres panel_talleres) {

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
		this.confirm = new Confirmaciones();
	}

	/**
	 * Metodo que devuelve un array con los elementos de la fila seleccionada en una
	 * tabla
	 * 
	 * @return
	 */
	public Object[] getFilaSeleccionada() {
		JTable tabla = panel_x.getTable();
		// obtener señal de la fila en forma de int
		int filaSeleccionada = tabla.getSelectedRow();

		// si hay fila seleccionada (diferente a -1 que se interpreta como no seleccion)
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
	 * Edicion de cita manteniendo los asistentes que no han cambiado
	 */
	public boolean editarCita() {
		try {

			/*
			 * si en el listener principal se cambia el estado del id de la cita
			 * seleccionada esta clausula if no se activa
			 */
			if (idCitaEditando == -1) {
				System.out.println("No hay cita seleccionada para editar");
				return false;
			}

			// Obtencion datos del formulario
			String cliente = (String) panel_citas.getCbCliente().getSelectedItem();
			String taller = (String) panel_citas.getCbTaller().getSelectedItem();
			String traje = (String) panel_citas.getCbTrajes().getSelectedItem();
			String encargado = (String) panel_citas.getCbEncargado().getSelectedItem();
			String fecha = panel_citas.getDpFecha().getDateStringOrEmptyString();
			String hora = panel_citas.getTpHora().getTimeStringOrEmptyString();
			int duracion = (int) panel_citas.getSpDuracion().getValue();
			String asistenteUno = (String) panel_citas.getCbAyudante1().getSelectedItem();
			String asistenteDos = (String) panel_citas.getCbAyudante2().getSelectedItem();

			// Validacion de los datos
			if (cliente == null || taller == null || traje == null || encargado == null || fecha.isEmpty()
					|| hora.isEmpty()) {
				System.out.println("Campos vacíos en el formulario");
				return false;
			}

			// Obtener asistentes actuales
			ArrayList<Integer> asistentesActuales = ids.idsAsignados(idCitaEditando);

			// Obtener nuevos asistentes
			ArrayList<Integer> nuevosAsistentes = new ArrayList<>();
			if (asistenteUno != null && !"Sin ayudante".equals(asistenteUno)) {
				int id = ids.obtenerIdEmpleado(asistenteUno);
				if (id != -1)
					nuevosAsistentes.add(id);
			}
			if (asistenteDos != null && !"Sin ayudante".equals(asistenteDos)) {
				int id = ids.obtenerIdEmpleado(asistenteDos);
				if (id != -1)
					nuevosAsistentes.add(id);
			}

			// Modificacion de la cita
			boolean exito = consultas_cita.modificarCita(idCitaEditando, fecha, hora, duracion, cliente, encargado,
					traje, taller);

			if (exito) {
				// Actualizacion asistentes

				// Eliminar asistentes que ya no están
				for (int idActual : asistentesActuales) {
					if (!nuevosAsistentes.contains(idActual)) {
						consultas_empleado.eliminarAsistencia(idCitaEditando, idActual);
					}
				}

				// Añadir nuevos asistentes
				for (int idNuevo : nuevosAsistentes) {
					if (!asistentesActuales.contains(idNuevo)) {
						consultas_empleado.asignacion(idCitaEditando, idNuevo);
					}
				}

				// String con el mensaje que se mostrara al crear la cita
				String mensaje = "Cita modificada exitosamente" + "\n" + "Fecha: " + fecha + "\n" + "Hora: " + hora
						+ "\n" + "Duración: " + duracion + "\n" + "Cliente: " + cliente + "\n" + "Encargado: "
						+ encargado + "\n" + "Traje: " + traje + "\n" + "Taller: " + taller + "\n";

				// invocacion del formato
				confirm.mostrarExito("Cita modificada", mensaje);

				System.out.println("Cita modificada correctamente");
				idCitaEditando = -1;
				Utilidades.limpiarFormularioCitas(panel_citas);
				return true;
			} else {
				// String con el mensaje que se mostrara al crear la cita
				String mensaje = "Error al modificar la cita \n Verifique que todos los campos estás rellenos";

				// invocacion del formato
				confirm.mostrarError("Error", mensaje);

				System.out.println("Fallo al modificar la cita");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Metodo de edicion de un cliente con sus traje
	 * 
	 * @see Acceso_BD#modificarCliente(int, String, String, String)
	 */
	public boolean editarCliente() {
		try {
			if (idClienteEditando == -1) {
				System.out.println("No hay cliente seleccionado para editar");
				return false;
			}

			String nombre = panel_clientes.getTfNombre().getText();
			String colores = panel_clientes.getTfColores().getText();
			String superpoder = panel_clientes.getTfSuperpoder().getText();
			String alineacion = "";

			if (panel_clientes.getRdbtnVillano().isSelected()) {
				alineacion = "Villano";
			} else if (panel_clientes.getRdbtnHeroe().isSelected()) {
				alineacion = "Heroe";
			}

			if (nombre.isEmpty() || colores.isEmpty() || superpoder.isEmpty() || alineacion.isBlank()) {
				System.out.println("Campos vacios en el formulario de cliente");
				return false;
			}

			// invocacion de la modificacion
			boolean exito = consultas_cliente.modificarCliente(idClienteEditando, nombre, colores, superpoder);

			if (exito) {
				// String con el mensaje que se mostrara al crear la cita
				String mensaje = "Cliente modificado exitosamente" + "\n" + "Nombre: " + nombre + "\n" + "Colores: "
						+ colores + "\n" + "Superpoderes: " + superpoder;

				// invocacion del formato
				confirm.mostrarExito("Cliente modificado", mensaje);

				System.out.println("Cliente modificado correctamente");
				idClienteEditando = -1;
				nombreTrajeActual = null;
				Utilidades.limpiarFormularioCliente(panel_clientes);
				return true;
			} else {
				// String con el mensaje que se mostrara al crear la cita
				String mensaje = "Error al modificar el cliente \n Verifique que todos los campos estás rellenos.";

				// invocacion del formato
				confirm.mostrarError("Error", mensaje);

				System.out.println("Fallo al modificar el cliente");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Metodo de edicion de un empleado con los datos del formulario
	 * 
	 * @see Acceso_BD#modificarEmpleado(int, String, String, String, String, String)
	 */
	public boolean editarEmpleado() {
		try {
			if (idEmpleadoEditando == -1) {
				System.out.println("No hay empleado seleccionado para editar");
				return false;
			}

			String nombre = panel_empleados.getTfNombre().getText();
			String apellidos = panel_empleados.getTfApellidos().getText();
			String usuario = panel_empleados.getTfUsuario().getText();
			String contrasena = panel_empleados.getTfContrasena().getText();
			String categoria = "";

			if (nombre.isEmpty() || apellidos.isEmpty() || usuario.isEmpty() || contrasena.isEmpty()) {
				System.out.println("Campos vacios en el formulario de empleado");
				return false;
			}

			if (panel_empleados.getRdbtnAprendiz().isSelected()) {
				categoria = "aprendiz";
			} else if (panel_empleados.getRdbtnOficial().isSelected()) {
				categoria = "oficial";
			} else if (panel_empleados.getRdbtnMaestro().isSelected()) {
				categoria = "maestro";
			}

			boolean exito = consultas_empleado.modificarEmpleado(idEmpleadoEditando, nombre, apellidos, usuario,
					categoria, contrasena);

			if (exito) {
				// String con el mensaje que se mostrara al crear la cita
				String mensaje = "Empleado modificado exitosamente" + "\n" + "Nombre: " + nombre + "\n" + "Usuario: "
						+ usuario + "\n" + "Categoría: " + categoria;

				// invocacion del formato
				confirm.mostrarExito("Empleado modificado", mensaje);

				System.out.println("Empleado modificado correctamente");
				idEmpleadoEditando = -1;
				Utilidades.limpiarFormularioEmpleado(panel_empleados);
				return true;
			} else {
				// String con el mensaje que se mostrara al crear la cita
				String mensaje = "Error al modificar el empleado \n Verifique que todos los campos estás rellenos.";

				// invocacion del formato
				confirm.mostrarError("Error", mensaje);

				System.out.println("Fallo al modificar el empleado");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Metodo de modificaddo de un taller con los datos del formulario
	 * 
	 * @see ConsultasTaller#modificarTaller(int, String, String)
	 */
	public boolean editarTaller() {
		try {
			if (idTallerEditando == -1) {
				System.out.println("ERROR: No hay taller seleccionado para editar");
				return false;
			}

			String tipo = "";
			String nombre = panel_talleres.getTxtNombre().getText();

			if (nombre.isEmpty()) {
				System.out.println("ERROR: El nombre del taller no puede estar vacío");
				return false;
			}

			// obtencion datos del radio button
			if (panel_talleres.getRdbtnCostura().isSelected()) {
				tipo = "costura";
			} else if (panel_talleres.getRdbtnDiseno().isSelected()) {
				tipo = "diseño";
			} else if (panel_talleres.getRdbtnPruebas().isSelected()) {
				tipo = "pruebas";
			}

			// invocacion de la modificacion del taller
			boolean exito = consultas_taller.modificarTaller(idTallerEditando, tipo, nombre);

			if (exito) {
				// String con el mensaje que se mostrara al crear la cita
				String mensaje = "Taller modificado exitosamente" + "\n" + "Nombre: " + nombre + "\n"
						+ "Tipo de Taller: " + tipo;

				// invocacion del formato
				confirm.mostrarExito("Taller modificado", mensaje);

				System.out.println("Taller modificado correctamente");
				idTallerEditando = -1;
				Utilidades.limpiarFormularioTaller(panel_talleres);
				return true;
			} else {
				// String con el mensaje que se mostrara al crear la cita
				String mensaje = "Error al modificar el taller \n Verifique que todos los campos estás rellenos.";

				// invocacion del formato
				confirm.mostrarError("Error", mensaje);

				System.out.println("Fallo al modificar el taller");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Metodo que carga en la modificacion los datos seleccionados en la tabla
	 * 
	 * @param datosCita Array de objetos genericos que contiene los datos de la fila
	 *                  seleccionada
	 */
	public void cargarCitaParaEditar(Object[] datosCita) {
		// datosCita tiene: [ Encargado, fecha, hora, Taller, Cliente, Traje]

		idCitaEditando = ids.obtenerIdCita((String) datosCita[0], (String) datosCita[1], (String) datosCita[2]);

		// obtencion de los ids de los empleados asignados a la cita
		ArrayList<Integer> asignaciones = ids.idsAsignados(idCitaEditando);

		// Asignacion del encargado
		String nombreEncargado = (String) datosCita[0];
		for (int i = 0; i < panel_citas.getCbEncargado().getItemCount(); i++) {
			if (panel_citas.getCbEncargado().getItemAt(i).equals(nombreEncargado)) {
				panel_citas.getCbEncargado().setSelectedIndex(i);
				break;
			}
		}

		// Asignacion de la fecha con su conversion de formato
		String fecha = (String) datosCita[1];
		try {
			// String fechaDate = (String) dtf.format(fecha);
			panel_citas.getDpFecha().setText(fecha);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Asignacion de la duracioon
		String hora = (String) datosCita[2];
		// hago el split del String ejem: "1 H" y paso a int el char del indice 0
		panel_citas.getTpHora().setText(hora);

		// Asignacion del encargado
		String nombreTaller = (String) datosCita[3];
		for (int i = 0; i < panel_citas.getCbTaller().getItemCount(); i++) {
			if (panel_citas.getCbTaller().getItemAt(i).equals(nombreTaller)) {
				panel_citas.getCbTaller().setSelectedIndex(i);
				break;
			}
		}

		// Asignacion del cliente
		String nombreCliente = (String) datosCita[4];
		for (int i = 0; i < panel_citas.getCbCliente().getItemCount(); i++) {
			if (panel_citas.getCbCliente().getItemAt(i).equals(nombreCliente)) {
				panel_citas.getCbCliente().setSelectedIndex(i);
				break;
			}
		}

		// Asignacion del nombre del traje
		String nombreTraje = (String) datosCita[5];
		for (int i = 0; i < panel_citas.getCbTrajes().getItemCount(); i++) {
			if (panel_citas.getCbTrajes().getItemAt(i).equals(nombreTraje)) {
				panel_citas.getCbTrajes().setSelectedIndex(i);
				break;
			}
		}

		// Cargar datos de los asistentes
		if (asignaciones != null && !asignaciones.isEmpty()) {

			// Para el primer asistente
			String nombreAsignado1 = ids.obtenerNombreEmpleado(asignaciones.get(0));
			panel_citas.getCbAyudante1().setSelectedItem(nombreAsignado1);

			// Para el segundo asistente
			if (asignaciones.size() >= 2) {
				String nombreAsignado2 = ids.obtenerNombreEmpleado(asignaciones.get(1));
				panel_citas.getCbAyudante2().setSelectedItem(nombreAsignado2);
			} else {
				// Si no hay segundo asistente, seleccionar "Sin ayudante"
				panel_citas.getCbAyudante2().setSelectedItem("Sin ayudante");
			}

		} else {
			panel_citas.getCbAyudante1().setSelectedItem("Sin ayudante");
			panel_citas.getCbAyudante2().setSelectedItem("Sin ayudante");
		}

		// print de debug
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

		String alineacion = (String) datosCliente[0];
		// obtener la opcion de alineacion y asignarla
		if ("Heroe".equals(alineacion)) {
			panel_clientes.getRdbtnHeroe().setSelected(true);
		} else if ("Villano".equals(alineacion)) {
			panel_clientes.getRdbtnVillano().setSelected(true);
		}

		// Cargar datos en el formulario
		panel_clientes.getTfNombre().setText((String) datosCliente[1]);
		panel_clientes.getTfColores().setText((String) datosCliente[2]);
		panel_clientes.getTfSuperpoder().setText((String) datosCliente[3]);
		
		cargarTrajes((String) datosCliente[1]);
		
		// print de debug
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

		idEmpleadoEditando = ids.obtenerIdEmpleado((String) datosEmpleado[0]);

		panel_empleados.getTfNombre().setText((String) datosEmpleado[0]);
		panel_empleados.getTfApellidos().setText((String) datosEmpleado[1]);
		panel_empleados.getTfUsuario().setText((String) datosEmpleado[2]);

		// set de la seleccion en el radioButton
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
	 * Metodo que carga los datos de un taller en el formulario para modificar
	 * 
	 */
	public void cargarTallerParaEditar(Object[] datosTaller) {
		// datosTaller: [Nombre, Tipo sala]

		// ontencion del id del taller con los datos de la tabla
		idTallerEditando = ids.obtenerIdTaller((String) datosTaller[0]);

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
	
	
	/**
	 * Metodo para rellenar el comboBox con los trajes relacionados a un cliente
	 * 
	 * @param nombreCliente nombre del cliente que se buscaran los trajes
	 */
	@SuppressWarnings("unchecked")
	private void cargarTrajes(String nombreCliente) {
		// limpiado del comboBox para evitar duplicados
				panel_clientes.getComboTrajes().removeAllItems();
				// invocacion de la lista de trajes
				ArrayList<Traje> trajes = consultas_traje.mostrarTrajes((nombreCliente));

				// rellenado del combobox
				if (trajes != null) {
					for (Traje n : trajes) {
						panel_clientes.getComboTrajes().addItem(n.getNombre());
					}
				} else {
					panel_clientes.getComboTrajes().addItem("Sin trajes disponibles");
				}
	}

	
	/**
	 * Metodo de la ventana emergente de editar el estado de un traje
	 */
	public void modificarTrajeVentana(Panel_clientes panel_clientes, Object[] datosCliente) {
		
	    // Obtencion del nombre del traje seleccionado actualmente en el combo box
	    String nombreTrajeActual = (String) panel_clientes.getComboTrajes().getSelectedItem();
	    
		//almacenado del id del cliente antes de cualquier cambio
	    int idClienteRelacionado = ids.obtenerIdCliente((String) datosCliente[1]);
	    String estadoAnterior = ids.obtenerEstado((String) datosCliente [1], nombreTrajeActual);

	    
	    JTextField nombre = new JTextField();
	    nombre.setText((String) panel_clientes.getComboTrajes().getSelectedItem());
	    nombre.setColumns(15);
	    
	    // Radio buttons con diseño vertical
	    JRadioButton rdbtnDiseño = new JRadioButton("Diseño");
	    rdbtnDiseño.setFont(new Font("Century Schoolbook", Font.PLAIN, 14));
	    JRadioButton rdbtnCostura = new JRadioButton("Costura");
	    rdbtnCostura.setFont(new Font("Century Schoolbook", Font.PLAIN, 14));
	    JRadioButton rdbtnTaller = new JRadioButton("Taller");
	    rdbtnTaller.setFont(new Font("Century Schoolbook", Font.PLAIN, 14));
	    
	    ButtonGroup bg = new ButtonGroup();
	    bg.add(rdbtnDiseño);
	    bg.add(rdbtnCostura);
	    bg.add(rdbtnTaller);
	    
	    // Panel para los radio buttons
	    JPanel panelRadio = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
	    panelRadio.add(rdbtnDiseño);
	    panelRadio.add(rdbtnCostura);
	    panelRadio.add(rdbtnTaller);
	    
	    //Casos del estado anterior del traje
	    switch (estadoAnterior.toLowerCase()){
	    case "diseño":
	    	rdbtnDiseño.setSelected(true);
	    	break;
	    case "costura":
	    	rdbtnCostura.setSelected(true);
	    	break;
	    case "taller":
	    	rdbtnTaller.setSelected(true);
	    }
	    
	    Object[] diseñoFormulario = {
	        "Nombre:", nombre,
	        "Estado:", panelRadio
	    };
	    
	    Object[] botones = {"Cancelar", "Aceptar"}; // Cancelar a la izquierda
	    int botonPulsado = JOptionPane.showOptionDialog(
	        null, 
	        diseñoFormulario, 
	        "Editar traje", 
	        JOptionPane.YES_NO_OPTION,
	        JOptionPane.INFORMATION_MESSAGE,
	        null,
	        botones,
	        botones[1]
	    );
	    
	    if (botonPulsado == 1) {
	        String estado = "";
	        if (rdbtnDiseño.isSelected()) estado = "diseño";
	        else if (rdbtnCostura.isSelected()) estado = "costura";
	        else if (rdbtnTaller.isSelected()) estado = "taller";
	        
	        String nombreGuardado = nombre.getText().trim();
	        
	        if (nombreGuardado.isEmpty() || estado.isEmpty()) {
	            JOptionPane.showMessageDialog(null, "Complete todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
	            return;
	        }
	        
	        boolean exito = consultas_traje.modificarTraje(
	            idClienteRelacionado, 
	            nombreTrajeActual, 
	            nombreGuardado, 
	            estado
	        );
	        
	        if (exito) {
	            JOptionPane.showMessageDialog(null, "Traje editado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
	            cargarTrajes((String) datosCliente[1]);
	            panel_clientes.getComboTrajes().setSelectedItem(nombreGuardado);
	        }
	    }
	}
}