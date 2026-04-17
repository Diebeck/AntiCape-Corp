/**
 * @author Breixo García Canovacas
 * @author Robinson Tamayo Guerrero
 * @author Romeo Rey Alonso
 * @author Sara Cardeña Carpio 
 */
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
	Panel_nav_maestro panel_nav_maestro = new Panel_nav_maestro(this); // Nav para maestro
	Panel_logo panel_logo = new Panel_logo(this); // Nav logo
	Panel_nav_aprendiz panel_nav_aprendiz = new Panel_nav_aprendiz(this);
	Panel_nav_oficial panel_nav_oficial = new Panel_nav_oficial(this);
	Panel_prim_aprendiz panel_prim_aprendiz = new Panel_prim_aprendiz(this);
	
	private Control_tablas controlador_tablas = new Control_tablas(modelo, panel_x, panel_home);
	private Control_creacion controlador_creacion = new Control_creacion (modelo, panel_clientes, panel_empleados, panel_citas, panel_talleres);
	
	/** Asigna la referencia a la ventana principal
	 * @param vent
	 */
	public void setVentana(Ventana vent) {
		this.vent = vent;
		vent.cambiarCajaPrimario(panel_login);
		vent.cambiarCajaNav(panel_logo);
	}
	
	private void iniciarMaestro() {
		vent.cambiarCajaPrimario(panel_home);
		vent.cambiarCajaNav(panel_nav_maestro);
		//Cambio del nombre y rol en el panel de cuenta 
		panel_cuenta.getLbl_nombreEmpleado().setText(sesion.getNombre());
		panel_cuenta.getLbl_categoria().setText(sesion.getCategoria());
		// rellenado de tablas de la home 
		controlador_tablas.citasRecientes();
		controlador_tablas.cargarOcupacionTalleres();
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
			
		} else if (e.getSource()== panel_cuenta.getBtn_logout()) { //Para que funcione el botón Logout con la imagen
			vent.cambiarCajaPrimario(panel_login);
			vent.cambiarCajaNav(panel_logo);
			vent.cambiarCajaCuenta(null);
			
		} else if (cmd.equals("Citas")) {
		    vent.cambiarCajaPrimario(panel_x);
		    controlador_tablas.cargarCitas();
			
		} else if (cmd.equals("Clientes")) {
			vent.cambiarCajaPrimario(panel_x);
			controlador_tablas.cargarClientes();
		
		} else if (cmd.equals("Empleados")) {
			 vent.cambiarCajaPrimario(panel_x);
			 controlador_tablas.cargarEmpleados();
			
		} else if (cmd.equals("Talleres")) {
			 vent.cambiarCajaPrimario(panel_x);
			 controlador_tablas.cargarTalleres();
			
			
		} else if (cmd.equals("Crear") || cmd.equals("Modificar")) {
			if (panel_x.getEstado().equals("citas")) {
				vent.cambiarCajaPrimario(panel_citas);
				controlador_creacion.formularioCitas();
			} else if (panel_x.getEstado().equals("clientes")) {
				vent.cambiarCajaPrimario(panel_clientes);
			} else if (panel_x.getEstado().equals("talleres")) {
				vent.cambiarCajaPrimario(panel_talleres);
			} else if (panel_x.getEstado().equals("empleados")) {
				vent.cambiarCajaPrimario(panel_empleados);
			}
			// Pone el modo a "Crear" o "Modificar"
			panel_citas.setModo(cmd);
			panel_clientes.setModo(cmd);
			panel_talleres.setModo(cmd);
			panel_empleados.setModo(cmd);

		} else if (e.getSource() == panel_cuenta.getBotonHome()) {
			vent.cambiarCajaPrimario(panel_home);
		
		} else if (e.getSource() == panel_talleres.getBtn_homeTaller()) {
			vent.cambiarCajaPrimario(panel_x);
			
		} else if (e.getSource() == panel_clientes.getBtn_homeClientes()) {
			vent.cambiarCajaPrimario(panel_x);
			
		} else if (e.getSource() == panel_empleados.getBtn_homeEmpleado()) {
			vent.cambiarCajaPrimario(panel_x);
			
		} else if (e.getSource() == panel_citas.getBtn_homeCitas()) {
			vent.cambiarCajaPrimario(panel_x);
			// Recargar la tabla de citas al volver
			if (panel_x.getEstado().equals("citas")) {
				controlador_tablas.cargarCitas();
			}
			
		// Listener para el boton Confirmar del panel_citas
		} else if (e.getSource() == panel_citas.getBtn_cCita()) {
			// Crear la cita
			controlador_creacion.crearCita();
			
			// Recargar todas las tablas que muestran informacion de citas
			controlador_tablas.cargarCitas();           
			controlador_tablas.citasRecientes();       
			controlador_tablas.cargarOcupacionTalleres();
			
			// Volver al panel_x
			vent.cambiarCajaPrimario(panel_x);
		} else if (e.getSource() == panel_clientes.getBtn_cCliente()) {
			controlador_creacion.crearCliente();
			controlador_tablas.cargarClientes();
			vent.cambiarCajaPrimario(panel_x);
		} else if (e.getSource() == panel_talleres.getBtn_cTaller()) {
			controlador_creacion.crearTaller();
			controlador_tablas.cargarTalleres();
			controlador_tablas.cargarOcupacionTalleres();
			vent.cambiarCajaPrimario(panel_x);
		} else if (e.getSource() == panel_empleados.getBtn_cEmpleado()) {
			controlador_creacion.crearEmpleado();
			controlador_tablas.cargarEmpleados();
			vent.cambiarCajaPrimario(panel_x);
		}
	}

}