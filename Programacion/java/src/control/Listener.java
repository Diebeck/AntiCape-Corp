package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Acceso_BD;
import model.Empleado;
import view.*;

public class Listener implements ActionListener {
	

	private Acceso_BD modelo = new Acceso_BD();
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
	
	private Control_tablas ControladorTablas = new Control_tablas(modelo, panel_x, panel_home);
	private Control_creacion Controlador_creacion = new Control_creacion(modelo, panel_clientes, panel_empleados, panel_citas, panel_talleres);
	private Control_ediciones Controlador_ediciones = new Control_ediciones(modelo, panel_x, panel_citas, panel_clientes, panel_empleados, panel_talleres);
	
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
		ControladorTablas.citasRecientes();
		ControladorTablas.cargarOcupacionTalleres();
	}
	
	private void iniciarOficial() {
		vent.cambiarCajaPrimario(panel_x);
		vent.cambiarCajaNav(panel_nav_oficial);
	}
	
	private void iniciarAprendiz() {
		vent.cambiarCajaPrimario(panel_prim_aprendiz);
		vent.cambiarCajaNav(panel_nav_aprendiz);
		panel_cuenta.esconderHome();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		
		System.out.println("===\nBoton presionado: "+ cmd);
		
		if (cmd.equals("Login")) {
			sesion = modelo.login(panel_login.getTextField_usuario().getText(), panel_login.getPasswordField_contrasena());
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
			
		} else if (e.getSource()== panel_cuenta.getBtn_logout()) {
			vent.cambiarCajaPrimario(panel_login);
			vent.cambiarCajaNav(panel_logo);
			vent.cambiarCajaCuenta(null);
			
		} else if (cmd.equals("Citas")) {
		    vent.cambiarCajaPrimario(panel_x);
		    ControladorTablas.cargarCitas();
			
		} else if (cmd.equals("Clientes")) {
			vent.cambiarCajaPrimario(panel_x);
			ControladorTablas.cargarClientes();
		
		} else if (cmd.equals("Empleados")) {
			 vent.cambiarCajaPrimario(panel_x);
			 ControladorTablas.cargarEmpleados();
			
		} else if (cmd.equals("Talleres")) {
			 vent.cambiarCajaPrimario(panel_x);
			 ControladorTablas.cargarTalleres();
			
		} else if (cmd.equals("Crear")) {
			// Solo creación
			if (panel_x.getEstado().equals("citas")) {
				vent.cambiarCajaPrimario(panel_citas);
				Controlador_creacion.formularioCitas();
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
			
		} else if (cmd.equals("Modificar")) {
			// Solo edición
			if (panel_x.getEstado().equals("citas")) {
				Object[] fila = Controlador_ediciones.getFilaSeleccionada();
				if (fila != null) {
					vent.cambiarCajaPrimario(panel_citas);
					Controlador_creacion.formularioCitas(); // Para llenar combos
					Controlador_ediciones.cargarCitaParaEditar(fila);
					panel_citas.setModo("Modificar");
				} else {
					System.out.println("ERROR: Seleccione una fila para modificar");
				}
			} else if (panel_x.getEstado().equals("clientes")) {
				Object[] fila = Controlador_ediciones.getFilaSeleccionada();
				if (fila != null) {
					vent.cambiarCajaPrimario(panel_clientes);
					Controlador_ediciones.cargarClienteParaEditar(fila);
					panel_clientes.setModo("Modificar");
				} else {
					System.out.println("ERROR: Seleccione una fila para modificar");
				}
			} else if (panel_x.getEstado().equals("talleres")) {
				Object[] fila = Controlador_ediciones.getFilaSeleccionada();
				if (fila != null) {
					vent.cambiarCajaPrimario(panel_talleres);
					Controlador_ediciones.cargarTallerParaEditar(fila);
					panel_talleres.setModo("Modificar");
				} else {
					System.out.println("ERROR: Seleccione una fila para modificar");
				}
			} else if (panel_x.getEstado().equals("empleados")) {
				Object[] fila = Controlador_ediciones.getFilaSeleccionada();
				if (fila != null) {
					vent.cambiarCajaPrimario(panel_empleados);
					Controlador_ediciones.cargarEmpleadoParaEditar(fila);
					panel_empleados.setModo("Modificar");
				} else {
					System.out.println("ERROR: Seleccione una fila para modificar");
				}
			}

		} else if (e.getSource() == panel_cuenta.getBotonHome()) {
			vent.cambiarCajaPrimario(panel_home);
		
		} else if (e.getSource() == panel_talleres.getBtn_homeTaller()) {
			vent.cambiarCajaPrimario(panel_x);
			if (panel_x.getEstado().equals("talleres")) {
				ControladorTablas.cargarTalleres();
			}
			
		} else if (e.getSource() == panel_clientes.getBtn_homeClientes()) {
			vent.cambiarCajaPrimario(panel_x);
			if (panel_x.getEstado().equals("clientes")) {
				ControladorTablas.cargarClientes();
			}
			
		} else if (e.getSource() == panel_empleados.getBtn_homeEmpleado()) {
			vent.cambiarCajaPrimario(panel_x);
			if (panel_x.getEstado().equals("empleados")) {
				ControladorTablas.cargarEmpleados();
			}
			
		} else if (e.getSource() == panel_citas.getBtn_homeCitas()) {
			vent.cambiarCajaPrimario(panel_x);
			if (panel_x.getEstado().equals("citas")) {
				ControladorTablas.cargarCitas();
			}
			
		} else if (e.getSource() == panel_citas.getBtn_cCita()) {
			// Boton confirmar en citas
			if (panel_citas.getModo().equals("Crear")) {
				Controlador_creacion.crearCita();
			} else {
				Controlador_ediciones.editarCita();
			}
			// Recargar tablas
			ControladorTablas.cargarCitas();
			ControladorTablas.citasRecientes();
			ControladorTablas.cargarOcupacionTalleres();
			vent.cambiarCajaPrimario(panel_x);
			
		} else if (e.getSource() == panel_clientes.getBtn_cCliente()) {
			// Boton confirmar en clientes
			if (panel_clientes.getModo().equals("Crear")) {
				Controlador_creacion.crearCliente();
			} else {
				Controlador_ediciones.editarCliente();
			}
			// Recargar tablas
			ControladorTablas.cargarClientes();
			vent.cambiarCajaPrimario(panel_x);
			
		} else if (e.getSource() == panel_talleres.getBtn_cTaller()) {
			// Boton confirmar en talleres
			if (panel_talleres.getModo().equals("Crear")) {
				Controlador_creacion.crearTaller();
			} else {
				Controlador_ediciones.editarTaller();
			}
			// Recargar tablas
			ControladorTablas.cargarTalleres();
			vent.cambiarCajaPrimario(panel_x);
			
		} else if (e.getSource() == panel_empleados.getBtn_cEmpleado()) {
			// Boton confirmar en empleados
			if (panel_empleados.getModo().equals("Crear")) {
				Controlador_creacion.crearEmpleado();
			} else {
				Controlador_ediciones.editarEmpleado();
			}
			// Recargar tablas
			ControladorTablas.cargarEmpleados();
			vent.cambiarCajaPrimario(panel_x);
		}
	}
}