/**
 * @author Breixo García Canovacas
 * @author Robinson Tamayo Guerrero
 * @author Romeo Rey Alonso
 * @author Sara Cardeña Carpio 
 */
package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import model.*;
import view.Panel_citas;
import view.Panel_clientes;
import view.Panel_empleados;
import view.Panel_talleres;

/**
 * Clase dedicada a el control de las creaciones de elementos
 */
public class Control_creacion {

	@SuppressWarnings("unused")
	private Acceso_BD modelo;
	private Panel_clientes panel_cliente;
	private Panel_empleados panel_empleados;
	private Panel_citas panel_cita;
	private Panel_talleres panel_taller;
	private ConsultasCliente consultas_cliente;
	private ConsultasCita consultas_cita;
	private ConsultasTaller consultas_taller;
	private ConsultasEmpleado consultas_empleado;
	private ConsultasTraje consultas_traje;
	

	public Control_creacion(Acceso_BD modelo, Panel_clientes clientes, Panel_empleados empleados, Panel_citas citas,
			Panel_talleres talleres) {

		this.modelo = modelo;
		this.panel_cliente = clientes;
		this.panel_cita = citas;
		this.panel_empleados = empleados;
		this.panel_taller = talleres;
		this.consultas_cliente = new ConsultasCliente(modelo.getConexion());
		this.consultas_cita = new ConsultasCita(modelo.getConexion());
		this.consultas_taller = new ConsultasTaller(modelo.getConexion());
		this.consultas_empleado = new ConsultasEmpleado(modelo.getConexion());
		this.consultas_traje = new ConsultasTraje(modelo.getConexion());
	}

	/**
	 * Metodo que rellena el formulario de creacion de citas
	 * 
	 * @see Acceso_BD#mostradoCitas()
	 * @see Acceso_BD#mostrarClientes()
	 * @see Acceso_BD#mostrarEmpleados()
	 * @see Acceso_BD#mostrarTalleres()
	 * @see #cargarTrajesPorCliente()
	 */
	@SuppressWarnings("unchecked")
	public void formularioCitas() {
		// cargado de los arrays con los datos de la base de datos
		ArrayList<Cliente> clientes = consultas_cliente.mostrarClientes();
		ArrayList<Empleado> empleados = consultas_empleado.mostrarEmpleados();
		ArrayList<Taller> talleres = consultas_taller.mostrarTalleres();

		// Limpiado de los combos para evitar duplicados
		panel_cita.getCbCliente().removeAllItems();
		panel_cita.getCbEncargado().removeAllItems();
		panel_cita.getCbTaller().removeAllItems();
		panel_cita.getCbTrajes().removeAllItems();

		// Llenado combo de clientes
		if (clientes != null) {
			for (Cliente n : clientes) {
				panel_cita.getCbCliente().addItem(n.getNombre());
			}
		}

		// Llenado combo de encargados por categoria
		if (empleados != null) {
			for (Empleado n : empleados) {
				if (n.getCategoria().toLowerCase().equals("maestro")
						|| n.getCategoria().toLowerCase().equals("oficial")) {
					panel_cita.getCbEncargado().addItem(n.getNombre());
				}
			}
		}

		// Llenado combo de talleres
		if (talleres != null) {
			for (Taller n : talleres) {
				panel_cita.getCbTaller().addItem(n.getTipo_sala());
			}
		}

		// Cargar los trajes del primer cliente seleccionado
		cargarTrajesPorCliente();

		// Añadir listener para cuando cambie el cliente seleccionado (sin lambda)
		panel_cita.getCbCliente().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cargarTrajesPorCliente();
			}
		});
	}

	/**
	 * Método auxiliar para cargar los trajes del cliente seleccionado en el
	 * comboBox
	 */
	@SuppressWarnings("unchecked")
	private void cargarTrajesPorCliente() {
		String seleccion = (String) panel_cita.getCbCliente().getSelectedItem();

		if (seleccion != null) {
			// llenado del array de trajes
			ArrayList<Traje> trajes = consultas_traje.mostrarTrajes(seleccion);

			// Limpiado combo de trajes
			panel_cita.getCbTrajes().removeAllItems();

			// Llenado combo de trajes
			if (trajes != null) {
				for (Traje n : trajes) {
					panel_cita.getCbTrajes().addItem(n.getNombre());
				}
				System.out.println("Trajes cargados para " + seleccion + ": " + trajes.size());
			} else {
				panel_cita.getCbTrajes().addItem("Sin trajes disponibles");
				System.out.println("No hay trajes disponibles para: " + seleccion);
			}
		}
	}

	/**
	 * Metodo que crea una nueva cita con los datos del formulario
	 * 
	 * cuenta con diversos prints de debug para implementar despues las ventanas
	 * emergentes
	 */
	public void crearCita() {
		try {
			// Obtencion valores de los combobox
			String cliente = (String) panel_cita.getCbCliente().getSelectedItem();
			String taller = (String) panel_cita.getCbTaller().getSelectedItem();
			String traje = (String) panel_cita.getCbTrajes().getSelectedItem();
			String encargado = (String) panel_cita.getCbEncargado().getSelectedItem();

			// Conversion de la fecha del spinner al formato de la base de datos
			Date date = (Date) panel_cita.getSpFecha().getValue();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String fecha = sdf.format(date);

			// Obtener duracion de la cita
			int duracion = (int) panel_cita.getSpDuracion().getValue();

			// Validar que no haya campos vacíos
			if (cliente == null || taller == null || traje == null || encargado == null) {
				System.out.println("ERROR: Campos vacíos en el formulario");
				return;
			}

			// Validar que no haya seleccionado "Sin trajes disponibles"
			if (traje.equals("Sin trajes disponibles")) {
				System.out.println("ERROR: El cliente no tiene trajes disponibles");
				return;
			}

			// Llamar al metodo crearCita del modelo
			boolean exito = consultas_cita.crearCita(cliente, taller, fecha, duracion, traje, encargado);

			if (exito) {
				Utilidades.limpiarFormularioCitas(panel_cita);
				cargarTrajesPorCliente(); // Recargar trajes después de limpiar
				System.out.println("EXITO: Cita creada correctamente");
			} else {
				System.out.println("ERROR: Fallo al crear la cita en la base de datos");
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERROR: Excepción al crear cita - " + e.getMessage());
		}
	}

	/**
	 * Metodo para crear clientes con los datos introducidos por el usuario en el
	 * formulario
	 * 
	 * @see Acceso_BD#crearCliente(String, String, String)
	 * @see Acceso_BD#crearTraje(String, String, String)
	 */
	public void crearCliente() {
		try {
			String nombre = panel_cliente.getTfNombre().getText();
			String colores = panel_cliente.getTfColores().getText();
			String superpoder = panel_cliente.getTfSuperpoder().getText();
			String nomTraje = panel_cliente.getTfNombreT().getText();
			String estado = "";

			if (nombre.isEmpty() || colores.isEmpty() || superpoder.isEmpty() || nomTraje.isEmpty()) {
				System.out.println("ERROR: Campos vacios en el formulario de cliente");
				return;
			}

			if (panel_cliente.getRdbtnDiseno().isSelected()) {
				estado = "diseño";
			} else if (panel_cliente.getRdbtnCostura().isSelected()) {
				estado = "costura";
			} else if (panel_cliente.getRdbtnTaller().isSelected()) {
				estado = "taller";
			}

			boolean exitoCliente = consultas_cliente.crearCliente(nombre, colores, superpoder);

			if (exitoCliente) {
				boolean exitoTraje = consultas_traje.crearTraje(nombre, nomTraje, estado);

				if (exitoTraje) {
					System.out.println("Traje creado correctamente");
					Utilidades.limpiarFormularioCliente(panel_cliente);
				} else {
					System.out.println("Fallo al crear el traje");
					return;
				}
			} else {
				System.out.println("Fallo al crear el cliente");
				return;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Metodo que controla la creacion de un taller desde la aplicacion 
	 */
	public void crearTaller() {
	    try {
	        boolean exito = false;
	        String tipo = "";
	        String nombre = panel_taller.getTxtNombre().getText();
	        
	        if (nombre.isEmpty()) {
	            System.out.println("El nombre del taller no puede estar vacío");
	            return;
	        }
	        
	        if (panel_taller.getRdbtnCostura().isSelected()) {
	            tipo = "costura";
	        } else if (panel_taller.getRdbtnDiseno().isSelected()) {
	            tipo = "diseño";
	        } else if (panel_taller.getRdbtnPruebas().isSelected()) {
	            tipo = "pruebas";
	        }

	        if (!tipo.isEmpty() && !nombre.isEmpty()) {
	            exito = consultas_taller.crearTaller(tipo, nombre);
	        }
	        
	        if (exito) {
	            System.out.println("Creacion de taller exitosa");
	            Utilidades.limpiarFormularioTaller(panel_taller);
	        } else {
	            System.out.println("Fallo en la creacion de taller");
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	/**
	 * Metodo para crear un empleado en base a los datos del formulario
	 * en la aplicacion 
	 */
	public void crearEmpleado() {
		try {
			boolean exito = false;
			String nombre = panel_empleados.getTfNombre().getText();
			String apellido = panel_empleados.getTfApellidos().getText();
			String usuario = panel_empleados.getTfUsuario().getText();
			String contraseña = panel_empleados.getTfContrasena().getText();
			String categoria = null;
			
			if (panel_empleados.getRdbtnAprendiz().isSelected()) {
				categoria = "Aprendiz";
			} else if (panel_empleados.getRdbtnOficial().isSelected()) {
				categoria = "Oficial";
			} else if (panel_empleados.getRdbtnMaestro().isSelected()) {
				categoria = "Maestro";
			}
			
			if (!nombre.isEmpty() && !apellido.isEmpty() && !usuario.isEmpty() && !contraseña.isEmpty() && contraseña != null) {
				exito = consultas_empleado.crearEmpleado(nombre, apellido, usuario, categoria, contraseña);
			}
			
			if (exito) {
				System.out.println("empleado creado con exito");
				Utilidades.limpiarFormularioEmpleado(panel_empleados);
			} else {
				System.out.println("fallo en la creacion del empleado");
			}
			
		} catch (Exception w) {
			w.printStackTrace();
		}
	}
}
