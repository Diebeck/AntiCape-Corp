package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import model.Acceso_BD;
import model.Cita;
import model.Empleado;
import model.Taller;
import model.Cliente;
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
	
	private Control_tablas ControladorTablas = new Control_tablas(modelo, panel_x);
	
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
			
			
		} else if (cmd.equals("Crear") || cmd.equals("Modificar")) {
			if (panel_x.getEstado().equals("citas")) {
				vent.cambiarCajaPrimario(panel_citas);
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
			
		} else if (cmd.equals("Confirmar")) {
			vent.cambiarCajaPrimario(panel_x);
			
		}
	}

}
