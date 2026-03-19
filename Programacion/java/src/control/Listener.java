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
		System.out.println(cmd);
		if (cmd.equals("Login")) {
			vent.cambiarPanelPrimario("prim_interaccion");
		}
	}

}
