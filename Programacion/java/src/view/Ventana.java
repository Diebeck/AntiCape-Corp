package view;

import javax.swing.JFrame;

import control.Listener;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.JScrollPane;

public class Ventana extends JFrame {

	Listener list;
	
	JScrollPane caja_primario = new JScrollPane();
	JScrollPane caja_nav = new JScrollPane();
	JScrollPane caja_cuenta = new JScrollPane();
	
	// Constructor
	public Ventana(Listener list) {
		this.list = list;
		iniciar();
	}

	// Funciones para cambiar lo que se ve en cada panel
	public void cambiarCajaPrimario(JPanel panel) {
		caja_primario.setViewportView(panel);
	}
	public void cambiarCajaNav(JPanel panel) {
		caja_nav.setViewportView(panel);
	}
	public void cambiarCajaCuenta(JPanel panel) {
		caja_cuenta.setViewportView(panel);
	}
	
	// El metodo grande principal
	private void iniciar() {
		setSize(1153, 604);
		
		getContentPane().setBackground(new Color(192, 192, 192));
		getContentPane().setLayout(null);
		
		caja_primario.setBorder(new LineBorder(new Color(0, 0, 0), 5));
		caja_primario.setBounds(406, 10, 723, 545);
		getContentPane().add(caja_primario);

		JPanel panel_secundario = new JPanel();
		panel_secundario.setBackground(new Color(0, 0, 0));
		panel_secundario.setBorder(new LineBorder(new Color(0, 0, 0), 5));
		panel_secundario.setBounds(10, 10, 372, 545);
		panel_secundario.setLayout(null);
		getContentPane().add(panel_secundario);
		panel_secundario.add(caja_nav);
		panel_secundario.add(caja_cuenta);
		
		caja_nav.setBorder(null);
		caja_nav.setBounds(5, 130, 362, 410);
		caja_nav.setBackground(new Color(116, 27, 71));
				
		caja_cuenta.setBorder(null);
		caja_cuenta.setBounds(5, 5, 362, 127);
		

	}
}
