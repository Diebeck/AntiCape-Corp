package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import model.Acceso_BD;
import model.Empleado;
import view.*;

public class Listener implements ActionListener {
	

	private Acceso_BD modelo = Acceso_BD.instancia();
	private Empleado sesion;
	
	private Ventana vent;
	
	Panel_citas panel_citas = new Panel_citas(this);
	Panel_clientes panel_clientes = new Panel_clientes(this);
	Panel_cuenta panel_cuenta = new Panel_cuenta(this);
	Panel_empleados panel_empleados = new Panel_empleados(this);
	Panel_home panel_home = new Panel_home(this);
	Panel_login panel_login = new Panel_login(this);
	Panel_talleres panel_talleres = new Panel_talleres(this);
	Panel_x panel_x = new Panel_x(this);
	Panel_nav_maestro panel_nav_maestro = new Panel_nav_maestro(this);
	Panel_logo panel_logo = new Panel_logo(this);
	Panel_nav_aprendiz panel_nav_aprendiz = new Panel_nav_aprendiz(this);
	Panel_nav_oficial panel_nav_oficial = new Panel_nav_oficial(this);
	Panel_prim_aprendiz panel_prim_aprendiz = new Panel_prim_aprendiz(this);
	
	private Control_tablas controladorTablas = new Control_tablas(panel_x, panel_home, panel_prim_aprendiz);
	private Control_creacion controladorCreacion = new Control_creacion(panel_clientes, panel_empleados, panel_citas, panel_talleres);
	private Control_ediciones controladorEdiciones = new Control_ediciones(panel_x, panel_citas, panel_clientes, panel_empleados, panel_talleres);
	private Control_eliminacion controladorEliminaciones = new Control_eliminacion(panel_x);
	
	public void setVentana(Ventana vent) {
		this.vent = vent;
		vent.cambiarCajaPrimario(panel_login);
		vent.cambiarCajaNav(panel_logo);
	}
	
	private void iniciarMaestro() {
		vent.cambiarCajaPrimario(panel_home);
		vent.cambiarCajaNav(panel_nav_maestro);
		panel_cuenta.getLbl_nombreEmpleado().setText(sesion.getNombre());
		panel_cuenta.getLbl_categoria().setText(sesion.getCategoria());
		controladorTablas.citasRecientes();
		controladorTablas.cargarOcupacionTalleres();
	}
	
	private void iniciarOficial() {
		vent.cambiarCajaPrimario(panel_x);
		vent.cambiarCajaNav(panel_nav_oficial);
		panel_cuenta.getLbl_nombreEmpleado().setText(sesion.getNombre());
		panel_cuenta.getLbl_categoria().setText(sesion.getCategoria());
		controladorTablas.cargarCitas();
		controladorTablas.citasRecientes();
		controladorTablas.cargarOcupacionTalleres();
	}
	
	private void iniciarAprendiz() {
		vent.cambiarCajaPrimario(panel_prim_aprendiz);
		vent.cambiarCajaNav(panel_nav_aprendiz);
		panel_cuenta.esconderHome();
		panel_cuenta.getLbl_nombreEmpleado().setText(sesion.getNombre());
		panel_cuenta.getLbl_categoria().setText(sesion.getCategoria());
		controladorTablas.citasRecientesAprendiz();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		
		System.out.println("===\nBoton presionado: "+ cmd); // Imprime lo que ponia en el boton
		

		// Eventos para botones que necesitan getter (botones especificos)
		
		if (e.getSource()== panel_cuenta.getBtn_logout()) {
			sesion = null;
			vent.cambiarCajaPrimario(panel_login);
			vent.cambiarCajaNav(panel_logo);
			vent.cambiarCajaCuenta(null);

		
		
		} else if (e.getSource()== panel_cuenta.getBtn_logout()) {
			vent.cambiarCajaPrimario(panel_login);
			vent.cambiarCajaNav(panel_logo);
			vent.cambiarCajaCuenta(null);
			
			modelo.closeConnect();
			

			//bloque else if retroceso a home 
		} else if (e.getSource() == panel_cuenta.getBotonHome()) {
			vent.cambiarCajaPrimario(panel_home);
		
		} else if (e.getSource() == panel_talleres.getBtn_homeTaller()) {
			vent.cambiarCajaPrimario(panel_x);
			if (panel_x.getEstado().equals("talleres")) {
				controladorTablas.cargarTalleres();
			}
			
		} else if (e.getSource() == panel_clientes.getBtn_homeClientes()) {
			vent.cambiarCajaPrimario(panel_x);
			if (panel_x.getEstado().equals("clientes")) {
				controladorTablas.cargarClientes();
			}
			
		} else if (e.getSource() == panel_empleados.getBtn_homeEmpleado()) {
			vent.cambiarCajaPrimario(panel_x);
			if (panel_x.getEstado().equals("empleados")) {
				controladorTablas.cargarEmpleados();
			}
			
		} else if (e.getSource() == panel_citas.getBtn_homeCitas()) {
			vent.cambiarCajaPrimario(panel_x);
			if (panel_x.getEstado().equals("citas")) {
				controladorTablas.cargarCitas();
			}
			
			//Bloque else if creacion 
		} else if (e.getSource() == panel_citas.getBtn_cCita()) {
			// Boton confirmar en citas
			if (panel_citas.getModo().equals("Crear")) {
				controladorCreacion.crearCita();
			} else {
				controladorEdiciones.editarCita();
			}
			// Recargar tablas
			controladorTablas.cargarCitas();
			controladorTablas.citasRecientes();
			controladorTablas.cargarOcupacionTalleres();
			vent.cambiarCajaPrimario(panel_x);
			
		} else if (e.getSource() == panel_clientes.getBtn_cCliente()) {
			// Boton confirmar en clientes
			if (panel_clientes.getModo().equals("Crear")) {
				controladorCreacion.crearCliente();
			} else {
				controladorEdiciones.editarCliente();
			}
			// Recargar tablas
			controladorTablas.cargarClientes();
			vent.cambiarCajaPrimario(panel_x);
			
		} else if (e.getSource() == panel_talleres.getBtn_cTaller()) {
			// Boton confirmar en talleres
			if (panel_talleres.getModo().equals("Crear")) {
				controladorCreacion.crearTaller();
			} else {
				controladorEdiciones.editarTaller();
			}
			// Recargar tablas
			controladorTablas.cargarTalleres();
			vent.cambiarCajaPrimario(panel_x);
			
		} else if (e.getSource() == panel_empleados.getBtn_cEmpleado()) {
			// Boton confirmar en empleados
			if (panel_empleados.getModo().equals("Crear")) {
				controladorCreacion.crearEmpleado();
			} else {
				controladorEdiciones.editarEmpleado();
			}
			// Recargar tablas
			controladorTablas.cargarEmpleados();
			vent.cambiarCajaPrimario(panel_x);
			
		}
		
		// Eventos para los botones que se obtienen con un String
		switch (cmd) {
		
		case "Login":
			//almaceno las credenciales
			String usuario = panel_login.getTextField_usuario().getText();
			String contraseña = panel_login.getPasswordField_contrasena();
			
			//inicio de la sesion
			sesion = modelo.login(usuario, contraseña);
			
			if (sesion != null) {
				panel_login.getLblErrorInicio().setText("");
				String tipoCuenta = sesion.getCategoria();
				vent.cambiarCajaCuenta(panel_cuenta);
				panel_cuenta.mostrarHome();
				if (tipoCuenta.equals("Maestro")) {
					iniciarMaestro();
				} else if (tipoCuenta.equals("Oficial")) {
					iniciarOficial();
				} else if (tipoCuenta.equals("Aprendiz")){
					iniciarAprendiz();
				}
				// barrido de las credenciales para el logout 
				panel_login.getPass().setText("");
				panel_login.getTextField_usuario().setText("");
			} else {
				//mensaje de error y limpiado de los textos
				panel_login.getLblErrorInicio().setText("Usuario o contraseña invalidos, Acceso denegado");
				panel_login.getTextField_usuario().setText("");
				panel_login.getPass().setText("");
				panel_login.getTextField_usuario().requestFocus();
			}
			break;
		
		case "Citas":
		    vent.cambiarCajaPrimario(panel_x);
		    controladorTablas.cargarCitas();
		    break;
			
		case "Clientes":
			vent.cambiarCajaPrimario(panel_x);
			controladorTablas.cargarClientes();
			break;
		
		case "Empleados":
			 vent.cambiarCajaPrimario(panel_x);
			 controladorTablas.cargarEmpleados();
			 break;
			
		case "Talleres":
			 vent.cambiarCajaPrimario(panel_x);
			 controladorTablas.cargarTalleres();
			 break;
		
		case "Crear":
			// Solo creación
			if (panel_x.getEstado().equals("citas")) {
				vent.cambiarCajaPrimario(panel_citas);
				controladorCreacion.formularioCitas();
				panel_citas.setModo("Crear");
			} else if (panel_x.getEstado().equals("clientes")) {
				vent.cambiarCajaPrimario(panel_clientes);
				panel_clientes.setModo("Crear");
			} else if (panel_x.getEstado().equals("talleres")) {
				vent.cambiarCajaPrimario(panel_talleres);
				panel_talleres.setModo("Crear");
			} else if (panel_x.getEstado().equals("empleados")) {
				vent.cambiarCajaPrimario(panel_empleados);
				panel_empleados.setModo("Crear");
			}
			break;
			
		// cargado previo del formulario de eliminaciones 
		case "Modificar":
			// Solo edición
			if (panel_x.getEstado().equals("citas")) {
				Object[] fila = controladorEdiciones.getFilaSeleccionada();
				if (fila != null) {
					vent.cambiarCajaPrimario(panel_citas);
					controladorCreacion.formularioCitas(); // Para llenar combos
					controladorEdiciones.cargarCitaParaEditar(fila);
					panel_citas.setModo("Modificar");
				} else {
					//prints para actualizacion de dialogs
					System.out.println("Seleccione una fila para modificar");
				}
			} else if (panel_x.getEstado().equals("clientes")) {
				Object[] fila = controladorEdiciones.getFilaSeleccionada();
				if (fila != null) {
					vent.cambiarCajaPrimario(panel_clientes);
					controladorEdiciones.cargarClienteParaEditar(fila);
					panel_clientes.setModo("Modificar");
				} else {
					System.out.println("Seleccione una fila para modificar");
				}
			} else if (panel_x.getEstado().equals("talleres")) {
				Object[] fila = controladorEdiciones.getFilaSeleccionada();
				if (fila != null) {
					vent.cambiarCajaPrimario(panel_talleres);
					controladorEdiciones.cargarTallerParaEditar(fila);
					panel_talleres.setModo("Modificar");
				} else {
					System.out.println("Seleccione una fila para modificar");
				}
			} else if (panel_x.getEstado().equals("empleados")) {
				Object[] fila = controladorEdiciones.getFilaSeleccionada();
				if (fila != null) {
					vent.cambiarCajaPrimario(panel_empleados);
					controladorEdiciones.cargarEmpleadoParaEditar(fila);
					panel_empleados.setModo("Modificar");
				} else {
					System.out.println("Seleccione una fila para modificar");
				}
			}
			break;
		
		//bloque else if para las eliminaciones
		case "Eliminar":
			if (panel_x.getEstado().equals("citas")) {
				boolean exito = controladorEliminaciones.eliminarCita();
				if (exito) {
					controladorTablas.cargarCitas();
					controladorTablas.citasRecientes();
					controladorTablas.cargarOcupacionTalleres();
				}
			} else if (panel_x.getEstado().equals("clientes")) {
				boolean exito = controladorEliminaciones.eliminarCliente();
				if (exito) {
					controladorTablas.cargarClientes();
				}
			} else if (panel_x.getEstado().equals("empleados")) {
				boolean exito = controladorEliminaciones.eliminarEmpleado();
				if (exito) {
					controladorTablas.cargarEmpleados();
				}
			} else if (panel_x.getEstado().equals("talleres")) {
				boolean exito = controladorEliminaciones.eliminarTaller();
				if (exito) {
					controladorTablas.cargarTalleres();
				}
			}

			break;
			
		case "Nuevo Cliente":
			controladorCreacion.crearClienteVentana();
			break;
			
		case "Nuevo Traje":
			controladorCreacion.crearTrajeVentana();
			break;
			
		}
	}
	
	public void keyPressed (KeyEvent e)
	{
	     System.out.println(e);
	}
}