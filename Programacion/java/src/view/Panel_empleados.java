package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import control.Listener;
import javax.swing.ImageIcon;

public class Panel_empleados extends JPanel {
	
	// El modo determina que accion se va a hacer (crear / modificar)
	String modo = "Crear";
	JLabel lbl_yEmpleado = new JLabel(modo +" un empleado");
	
	// Pone el modo y actualiza los componentes
	public void setModo(String modo) {
		this.modo = modo;
		lbl_yEmpleado.setText(modo +" un empleado");
		System.out.println("Cambiado el modo del panel empleados a "+ modo);
	}
	
	public String getModo() {
		return modo;
	}
	
	public Panel_empleados(Listener list) {
		setLayout(null);
		setSize(723, 545);
		
		lbl_yEmpleado.setForeground(new Color(116, 27, 71));
		lbl_yEmpleado.setFont(new Font("Century Schoolbook", Font.PLAIN, 30));
		lbl_yEmpleado.setBackground(new Color(76, 17, 48));
		lbl_yEmpleado.setBounds(38, 25, 437, 52);
		add(lbl_yEmpleado);
		
		JButton btn_homeEmpleado = new JButton("Home");
		btn_homeEmpleado.setIcon(new ImageIcon(Panel_empleados.class.getResource("/img/home.png")));
		btn_homeEmpleado.setForeground(Color.WHITE);
		btn_homeEmpleado.setFont(new Font("Century Schoolbook", Font.BOLD, 15));
		btn_homeEmpleado.setBackground(new Color(76, 17, 48));
		btn_homeEmpleado.setBounds(38, 455, 221, 44);
		add(btn_homeEmpleado);
		btn_homeEmpleado.addActionListener(list);
		
		JButton btn_cEmpleado = new JButton("Confirmar");
		btn_cEmpleado.setForeground(Color.WHITE);
		btn_cEmpleado.setFont(new Font("Century Schoolbook", Font.BOLD, 15));
		btn_cEmpleado.setBackground(new Color(76, 17, 48));
		btn_cEmpleado.setBounds(454, 455, 221, 44);
		add(btn_cEmpleado);
		btn_cEmpleado.addActionListener(list);
	}
}
