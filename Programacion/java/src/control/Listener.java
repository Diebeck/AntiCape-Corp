package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.*;

public class Listener implements ActionListener {

	private Ventana vent;
	
	// Cambiar tipoCuenta cuando se obtenga el tipo de la cuenta del usuario introducido
	private String tipoCuenta = "maestro";
	
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
		vent.cambiarCajaPrimario(null);
		vent.cambiarCajaNav(panel_nav_aprendiz);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		
		System.out.println("===\nBoton presionado: "+ cmd);
		
		if (cmd.equals("Login")) {
			vent.cambiarCajaCuenta(panel_cuenta);
			if (tipoCuenta.equals("maestro")) {
				iniciarMaestro();
			} else if (tipoCuenta.equals("oficial")) {
				iniciarOficial();
			} else if (tipoCuenta.equals("aprendiz")){
				iniciarAprendiz();
			}
			
		} else if (cmd.equals("Logout")) {
			vent.cambiarCajaPrimario(panel_login);
			vent.cambiarCajaNav(panel_logo);
			vent.cambiarCajaCuenta(null);
			
		} else if (cmd.equals("Citas")) {
			vent.cambiarCajaPrimario(panel_x);
			panel_x.setEstado("citas");
			
		} else if (cmd.equals("Clientes")) {
			vent.cambiarCajaPrimario(panel_x);
			panel_x.setEstado("clientes");
			
		} else if (cmd.equals("Empleados")) {
			vent.cambiarCajaPrimario(panel_x);
			panel_x.setEstado("empleados");
			
		} else if (cmd.equals("Talleres")) {
			vent.cambiarCajaPrimario(panel_x);
			panel_x.setEstado("talleres");
			
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
		
		} else if (cmd.equals("Home")) {
			vent.cambiarCajaPrimario(panel_x);
			
		} else if (cmd.equals("Confirmar")) {
			vent.cambiarCajaPrimario(panel_x);
			
		}
		
		else if (e.getSource().equals(panel_cuenta.getBtn_logout())) { //Para que funcione el botón Logout con la imagen
			vent.cambiarCajaPrimario(panel_login);
			vent.cambiarCajaNav(panel_logo);
			vent.cambiarCajaCuenta(null);
			
		}
	}

}
