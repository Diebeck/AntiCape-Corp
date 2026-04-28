/**
 * @author Breixo García Canovacas
 * @author Robinson Tamayo Guerrero
 * @author Romeo Rey Alonso
 * @author Sara Cardeña Carpio 
 */
package control;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import model.*;
import view.Confirmaciones;
import view.Panel_citas;
import view.Panel_clientes;
import view.Panel_empleados;
import view.Panel_talleres;

/**
 * Clase dedicada a el control de las creaciones de elementos
 */
public class Control_creacion {

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
	private ObtencionID ids;
	private Confirmaciones confirm;
	

	public Control_creacion(Panel_clientes clientes, Panel_empleados empleados, Panel_citas citas,
			Panel_talleres talleres) {

		this.modelo = Acceso_BD.instancia();
		this.panel_cliente = clientes;
		this.panel_cita = citas;
		this.panel_empleados = empleados;
		this.panel_taller = talleres;
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
		ArrayList<Empleado> asistentes = consultas_empleado.mostrarEmpleados();

		// Limpiado de los combos para evitar duplicados
		panel_cita.getCbCliente().removeAllItems();
		panel_cita.getCbEncargado().removeAllItems();
		panel_cita.getCbTaller().removeAllItems();
		panel_cita.getCbTrajes().removeAllItems();
		panel_cita.getCbAyudante1().removeAllItems();
		panel_cita.getCbAyudante2().removeAllItems();
		panel_cita.getTpHora().setText("");
		panel_cita.getDpFecha().setText("");

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
		
		//llenado combo de los asistentes de la cita
		if (asistentes != null) {
			// caso de no ayudante
			panel_cita.getCbAyudante1().addItem("Sin asistente");
			panel_cita.getCbAyudante2().addItem("Sin asistente");
			for(Empleado n : asistentes) {
				if(n.getCategoria().toLowerCase().equals("aprendiz")) {
					panel_cita.getCbAyudante1().addItem(n.getNombre());
					panel_cita.getCbAyudante2().addItem(n.getNombre());
				}
			}
			
		}

		// Llenado combo de talleres
		if (talleres != null) {
			for (Taller n : talleres) {
				panel_cita.getCbTaller().addItem(n.getNombre_sala());
			}
		}

		// Cargar los trajes del primer cliente seleccionado
		cargarTrajesPorCliente();

		// Añadir listener para cuando cambie el cliente seleccionado 
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
			String fecha = (String) panel_cita.getDpFecha().getDateStringOrEmptyString();
			String hora = (String) panel_cita.getTpHora().getTimeStringOrSuppliedString("n/a");
			String asistenteUno = (String) panel_cita.getCbAyudante1().getSelectedItem();
			String asistenteDos = (String) panel_cita.getCbAyudante2().getSelectedItem();
			
			// Obtener duracion de la cita
			int duracion = (int) panel_cita.getSpDuracion().getValue();

			// Validar que no haya campos vacíos
			if (cliente == null || taller == null || traje == null || encargado == null || fecha == null || "n/a".equals(hora)) {
				String error = "No se pudo realizar la creacion de la cita" + "\n" +
			"Verifique que todos los campos se hayan rellenado";
				
				confirm.mostrarError("Error", error);
				System.out.println("ERROR: Fallo al crear la cita en la base de datos");
				System.out.println("ERROR: Campos vacíos en el formulario");
				return;
			}

			// Validar que no haya seleccionado "Sin trajes disponibles"
			if (traje.equals("Sin trajes disponibles")) {
				System.out.println("ERROR: El cliente no tiene trajes disponibles");
				return;
			}
			
			// Llamar al metodo crearCita del modelo que devuelve el id de la cita
			int exito = consultas_cita.crearCita(fecha, hora, duracion, cliente, encargado, taller, traje);
			if (exito > 0) {
				
				//bloques de añadido de asistenetes a la cita
				if ("Sin ayudante".equals(asistenteUno)) {
					int asistenteId = ids.obtenerIdEmpleado(asistenteUno);
					
					//validacion de existencia del asistente
					if(asistenteId > 0) {
						boolean asistencia = consultas_empleado.asignacion(exito, asistenteId);
					if(asistencia) {
						System.out.println("Asistente añadido correctamente");
					} else {
						System.out.println("Fallo en añadido de la asistencia");
					}
				}
				}
				
				
				if (!"Sin ayudante".equals(asistenteDos) && !asistenteDos.equals(asistenteUno)) {
					int asistenteId = ids.obtenerIdEmpleado(asistenteDos);
					
					if (asistenteId > 0) {
						boolean asistencia = consultas_empleado.asignacion(exito, asistenteId);
						if(asistencia) {
							System.out.println("Asistente añadido correctamente");
						} else {
							System.out.println("Fallo en añadido de la asistencia");
						}
					}
				}
				
				//String con el mensaje que se mostrara al crear la cita
				String mensaje = "Cita creada exitosamente" + "\n" +
				"Encargado: " + encargado + "\n" +
				"Fecha: " + fecha + "\n" +
				"Hora: " + hora + "\n" +
				"Cliente: " + cliente;
				
				//invocacion del formato
				confirm.mostrarExito("Cita creada", mensaje);
				
				Utilidades.limpiarFormularioCitas(panel_cita);
				cargarTrajesPorCliente(); // Recargar trajes después de limpiar
				System.out.println("EXITO: Cita creada correctamente");
			} else {
				String error = "No se pudo realizar la creacion de la cita" + "\n" +
			"Verifique que todos los campos se hayan rellenado";
				
				confirm.mostrarError("Error", error);
				System.out.println("ERROR: Fallo al crear la cita en la base de datos");
			}

		} catch (Exception e) {
			System.out.println("ERROR: Excepción al crear cita - " + e.getMessage());
			e.printStackTrace();
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
			String alineacion = "";
			
			if (panel_cliente.getRdbtnHeroe().isSelected()) {
				alineacion = "Heroe";
			} else if (panel_cliente.getRdbtnVillano().isSelected()) {
				alineacion = "Villano";
			}

			if (nombre.isEmpty() || colores.isEmpty() || superpoder.isEmpty() || nomTraje.isEmpty() || alineacion.isBlank()) {
				String mensaje = "Error al crear el cliente \n Asegurese de que todos los campos esten rellenos";
				confirm.mostrarError("Error", mensaje);
				return;
			}

			if (panel_cliente.getRdbtnDiseno().isSelected()) {
				estado = "diseño";
			} else if (panel_cliente.getRdbtnCostura().isSelected()) {
				estado = "costura";
			} else if (panel_cliente.getRdbtnTaller().isSelected()) {
				estado = "taller";
			}

			boolean exitoCliente = consultas_cliente.crearCliente(nombre, colores, superpoder, alineacion);

			if (exitoCliente) {
				boolean exitoTraje = consultas_traje.crearTraje(nombre, nomTraje, estado);

				if (exitoTraje) {
					String mensaje = "Cliente " + nombre + "creado exitosamente \n" +
				    "Traje asignado: " + nomTraje;
					
					confirm.mostrarExito("Exito", mensaje);
					
					Utilidades.limpiarFormularioCliente(panel_cliente);
				} else {
					
					String mensaje = "Error al crear el cliente \n Asegurese de que todos los campos esten rellenos";
					confirm.mostrarError("Error", mensaje);
					System.out.println("Fallo al crear el traje");
					return;
				}
			} else {
				String mensaje = "Error al crear el cliente \n Asegurese de que todos los campos esten rellenos";
				confirm.mostrarError("Error", mensaje);
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
	        	String mensaje = "Error al crear un taller \n Verifique que todos los campos estan rellenos";
	        	confirm.mostrarError("Error", mensaje);
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
	        	String mensaje = "Taller " + nombre + " creado exitosamente \n" +
	        "Tipo de sala: " + tipo;
	        	confirm.mostrarExito("Exito", mensaje);
	            System.out.println("Creacion de taller exitosa");
	            Utilidades.limpiarFormularioTaller(panel_taller);
	        } else {
	        	String mensaje = "Error al crear un taller \n Verifique que todos los campos estan rellenos";
	        	confirm.mostrarError("Error", mensaje);
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
				String mensaje = "Empleado " + nombre +" creado exitosamente \n" + 
			"Usuario: " + usuario +  "\n" +
			"Categoria: " + categoria ;
				
				confirm.mostrarExito("Exito", mensaje);
				Utilidades.limpiarFormularioEmpleado(panel_empleados);
			} else {
				String mensaje = "Error al crear el empleado \n Asegurese que todos los campos esten rellenos";
				confirm.mostrarError("Error", mensaje);
				System.out.println("fallo en la creacion del empleado");
			}
			
		} catch (Exception w) {
			w.printStackTrace();
		}
	}
	
	
	/*
	 * Método para ventana emergente al crear cliente 
	 * dentro de la creación de citas
	 */
	@SuppressWarnings("unchecked")
	public void crearClienteVentana() {
		
		JTextField nombre = new JTextField();
	    JTextField colores = new JTextField();
	    JTextField superPoder = new JTextField();
	    
	    //Grupo de botones para el estado de la alineacion
		ButtonGroup bg = new ButtonGroup();
		JRadioButton rdbtnHeroe = new JRadioButton("Heroe ");
		rdbtnHeroe.setFont(new Font("Century Schoolbook", Font.PLAIN, 18));
		rdbtnHeroe.setBounds(469, 149, 102, 20);
		bg.add(rdbtnHeroe);
		
		JRadioButton rdbtnVillano = new JRadioButton("Villano");
		rdbtnVillano.setFont(new Font("Century Schoolbook", Font.PLAIN, 18));
		rdbtnVillano.setBounds(573, 149, 102, 20);
		bg.add(rdbtnVillano);
	    
	    
	    Object[] diseñoFormulario = {
	            "Nombre:", nombre,
	            "Colores:", colores,
	            "Superpoder:", superPoder,
	            rdbtnHeroe, rdbtnVillano
	        };	   
	    
	    Object[] botones = {"Cancelar", "Aceptar"}; // Cancelar a la izquierda
	    int botonPulsado = JOptionPane.showOptionDialog(
	        null,
	        diseñoFormulario,
	        "Datos del nuevo cliente",
	        JOptionPane.OK_CANCEL_OPTION,
	        JOptionPane.INFORMATION_MESSAGE,
	        null,
	        botones,
	        botones[1] // botón por defecto al pulsar Enter
	    );
	    
	    if (botonPulsado == 1) {
	    	
	    	// Almacenado de los datos
	    	String nombreGuardado = nombre.getText().trim();
	    	String coloresGuardados = colores.getText().trim();
	    	String superGuardado = superPoder.getText().trim();
	    	String alineacion = null;

	    	if (rdbtnVillano.isSelected()) {
	    	    alineacion = "Villano";
	    	} else if (rdbtnHeroe.isSelected()) {
	    	    alineacion = "Heroe";
	    	}

	    	// Validaciones de los datos
	    	if (nombreGuardado.isEmpty()) {
	    	    JOptionPane.showMessageDialog(null, "El nombre no puede estar vacio", "Error", JOptionPane.ERROR_MESSAGE);
	    	    nombre.requestFocus();
	    	    return;
	    	}

	    	if (coloresGuardados.isEmpty()) {
	    	    JOptionPane.showMessageDialog(null, "Los colores no pueden estar vacios", "Error", JOptionPane.ERROR_MESSAGE);
	    	    colores.requestFocus();
	    	    return;
	    	}

	    	if (superGuardado.isEmpty()) {
	    	    JOptionPane.showMessageDialog(null, "El superpoder no puede estar vacio", "Error", JOptionPane.ERROR_MESSAGE);
	    	    superPoder.requestFocus();
	    	    return;
	    	}

	    	if (alineacion == null) {
	    	    JOptionPane.showMessageDialog(null, "Debe seleccionar una alineacion (Héroe o Villano)", "Error", JOptionPane.ERROR_MESSAGE);
	    	    return;
	    	}

	    	// Crear cliente sin confirmación adicional
	    	boolean exito = consultas_cliente.crearCliente(nombreGuardado, coloresGuardados, superGuardado, alineacion);
	    	if (exito) {
	    	    JOptionPane.showMessageDialog(null, "Cliente '" + nombreGuardado + "' creado exitosamente", "Exito", JOptionPane.INFORMATION_MESSAGE);
	    	    // Limpiar formulario
	    	    nombre.setText("");
	    	    colores.setText("");
	    	    superPoder.setText("");
	    	    bg.clearSelection();
	    	} else {
	    	    JOptionPane.showMessageDialog(null, "Error al crear el cliente. Verifique que no exista un cliente con el mismo nombre", "Error", JOptionPane.ERROR_MESSAGE);
	    	}
	      
	        
	        // Recarga los clientes para que aparezca el nuevo cliente, y lo selecciona
	        ArrayList<Cliente> clientes = consultas_cliente.mostrarClientes();
	        panel_cita.getCbCliente().removeAllItems();
	        if (clientes != null) {
				for (Cliente n : clientes) {
					panel_cita.getCbCliente().addItem(n.getNombre());
				}
				panel_cita.getCbCliente().setSelectedItem(nombreGuardado);
			}
	        
	    } else {
	    	System.out.println("El usuario canceló la creación del cliente.");
	    }
	    
	}
	
	/*
	 * Método para ventana emergente al crear un nuevo traje
	 * dentro de la creación de citas
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void crearTrajeVentana() {
		
		JComboBox cliente = new JComboBox();
	    JTextField nombre = new JTextField();
	    
	    // Llenado combo de clientes
	    ArrayList<Cliente> clientes = consultas_cliente.mostrarClientes();
 		if (clientes != null) {
 			for (Cliente n : clientes) {
 				cliente.addItem(n.getNombre());
 			}
 		}
	    
	    Object[] diseñoFormulario = {
	            "Cliente:", cliente,
	            "Nombre:", nombre
	        };	   
	    
	    Object[] botones = {"Cancelar", "Aceptar"}; // Cancelar a la izquierda
	    int botonPulsado = JOptionPane.showOptionDialog(
	        null,
	        diseñoFormulario,
	        "Datos del nuevo traje",
	        JOptionPane.OK_CANCEL_OPTION,
	        JOptionPane.INFORMATION_MESSAGE,
	        null,
	        botones,
	        botones[1] // botón por defecto al pulsar Enter
	    );
	    
	    	    
	    if (botonPulsado == 1) {
	    	
	    	String clienteGuardado = (String) cliente.getSelectedItem();
		    String nombreGuardado = nombre.getText();
	        
		    System.out.println("Cliente: " + clienteGuardado);
	        System.out.println("Nombre: " + nombreGuardado);
	        boolean creacion = false;
	        
	        if (!nombreGuardado.isEmpty()) {
	        	creacion = consultas_traje.crearTraje(clienteGuardado, nombreGuardado, "diseño");
	        } else {
	        	JOptionPane.showMessageDialog(null, "El nombre no puede estar vacio", "Error", JOptionPane.ERROR_MESSAGE);
	        }
	        
	        
	        if (creacion) {
	        	//mensaje de confirmacion
	        	JOptionPane.showMessageDialog(null, "Traje " + nombreGuardado + " añadido exitosamente", "Exito", JOptionPane.INFORMATION_MESSAGE);
	        } 
	        
	        // Recarga los trajes del cliente para que aparezca el traje, y lo selecciona
	        cargarTrajesPorCliente();
	        panel_cita.getCbTrajes().setSelectedItem(nombreGuardado);
	        
	    } else {
	    	System.out.println("El usuario canceló la creación del traje.");
	    }
	    
	}
	
}
