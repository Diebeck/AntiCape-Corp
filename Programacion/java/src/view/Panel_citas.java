package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import control.Listener;

public class Panel_citas extends JPanel {
	
	// El modo determina que accion se va a hacer (crear / modificar)
	String modo = "Crear";
	JLabel lbl_yCitas = new JLabel(modo +" una cita");
	
	// Pone el modo y actualiza los componentes
	public void setModo(String modo) {
		this.modo = modo;
		lbl_yCitas.setText(modo +" una cita");
		System.out.println("Cambiado el modo del panel citas a "+ modo);
	}
	
	public String getModo() {
		return modo;
	}
	
	public Panel_citas(Listener list) {
		setLayout(null);
		setSize(723, 545);
		
		lbl_yCitas.setForeground(new Color(116, 27, 71));
		lbl_yCitas.setFont(new Font("Century Schoolbook", Font.PLAIN, 30));
		lbl_yCitas.setBackground(new Color(76, 17, 48));
		lbl_yCitas.setBounds(38, 25, 437, 52);
		add(lbl_yCitas);
		
		JButton btn_homeCitas = new JButton("Home");
		btn_homeCitas.setForeground(Color.WHITE);
		btn_homeCitas.setFont(new Font("Century Schoolbook", Font.BOLD, 15));
		btn_homeCitas.setBackground(new Color(76, 17, 48));
		btn_homeCitas.setBounds(38, 455, 221, 44);
		add(btn_homeCitas);
		btn_homeCitas.addActionListener(list);
		
		JButton btn_cCita = new JButton("Confirmar");
		btn_cCita.setForeground(Color.WHITE);
		btn_cCita.setFont(new Font("Century Schoolbook", Font.BOLD, 15));
		btn_cCita.setBackground(new Color(76, 17, 48));
		btn_cCita.setBounds(454, 455, 221, 44);
		add(btn_cCita);
		btn_cCita.addActionListener(list);
	}
}
