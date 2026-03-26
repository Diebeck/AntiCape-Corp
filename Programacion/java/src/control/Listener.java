package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.Ventana;

public class Listener implements ActionListener {

	private Ventana vent;
	
	public void setVentana(Ventana vent) {
		this.vent = vent;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		System.out.println("===\nBoton presionado: "+ cmd);
		if (cmd.equals("Login")) {
			vent.cambiarPanelPrimario("panel_home");
			vent.cambiarPanelSecundario("panel_ambos");
			
		} else if (cmd.equals("Logout")) {
			vent.cambiarPanelPrimario("panel_login");
			vent.cambiarPanelSecundario("panel_logo");
			
		} else if (cmd.equals("Citas")) {
			vent.cambiarPanelPrimario("panel_citas");
			vent.setEstado("citas");
			vent.setModo("Crear");
			
		} else if (cmd.equals("Clientes")) {
			vent.cambiarPanelPrimario("panel_clientes");
			vent.setEstado("clientes");
			vent.setModo("Crear");
			
		} else if (cmd.equals("Empleados")) {
			vent.cambiarPanelPrimario("panel_empleados");
			vent.setEstado("empleados");
			vent.setModo("Crear");
			
		} else if (cmd.equals("Talleres")) {
			vent.cambiarPanelPrimario("panel_talleres");
			vent.setEstado("talleres");
			vent.setModo("Crear");
			
		} else if (cmd.equals("Crear") || cmd.equals("Modificar")) {
			if (vent.getEstado().equals("citas")) {
				vent.cambiarPanelPrimario("panel_citas");
			} else if (vent.getEstado().equals("clientes")) {
				vent.cambiarPanelPrimario("panel_clientes");
			} else if (vent.getEstado().equals("talleres")) {
				vent.cambiarPanelPrimario("panel_talleres");
			} else if (vent.getEstado().equals("empleados")) {
				vent.cambiarPanelPrimario("panel_empleados");
			}
			vent.setModo(cmd); // Pone el modo a "Crear" o "Modificar"
			
		} else if (cmd.equals("Home")) {
			vent.cambiarPanelPrimario("panel_x");
		} else if (cmd.equals("Confirmar")) {
			vent.cambiarPanelPrimario("panel_x");
		}
	}

}
