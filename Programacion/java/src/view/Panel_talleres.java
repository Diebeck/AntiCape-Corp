package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import control.Listener;

public class Panel_talleres extends JPanel {

	// El modo determina que accion se va a hacer (crear / modificar)
	String modo = "Crear";
	JLabel lbl_yTaller = new JLabel(modo +" un taller");
	
	// Pone el modo y actualiza los componentes
	public void setModo(String modo) {
		this.modo = modo;
		lbl_yTaller.setText(modo +" un taller");
		System.out.println("Cambiado el modo del panel talleres a "+ modo);
	}
	
	public String getModo() {
		return modo;
	}
	
	public Panel_talleres(Listener list) {
		setLayout(null);
		setSize(723, 545);
		
		lbl_yTaller.setForeground(new Color(116, 27, 71));
		lbl_yTaller.setFont(new Font("Century Schoolbook", Font.PLAIN, 30));
		lbl_yTaller.setBackground(new Color(76, 17, 48));
		lbl_yTaller.setBounds(38, 25, 437, 52);
		add(lbl_yTaller);
		
		JButton btn_homeTaller = new JButton("Home");
		btn_homeTaller.setForeground(Color.WHITE);
		btn_homeTaller.setFont(new Font("Century Schoolbook", Font.BOLD, 15));
		btn_homeTaller.setBackground(new Color(76, 17, 48));
		btn_homeTaller.setBounds(38, 455, 221, 44);
		add(btn_homeTaller);
		btn_homeTaller.addActionListener(list);
		
		JButton btn_cTaller = new JButton("Confirmar");
		btn_cTaller.setForeground(Color.WHITE);
		btn_cTaller.setFont(new Font("Century Schoolbook", Font.BOLD, 15));
		btn_cTaller.setBackground(new Color(76, 17, 48));
		btn_cTaller.setBounds(454, 455, 221, 44);
		add(btn_cTaller);
		btn_cTaller.addActionListener(list);
	}
}
