package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import control.Listener;

public class Panel_cuenta extends JPanel {
	
	JButton btn_home = new JButton("Home");
	
	public JButton getBotonHome() {
		return btn_home;
	}
	
	public Panel_cuenta(Listener list) {
		
		setBackground(new Color(76, 17, 48));
		setBounds(0, 0, 362, 127);
		setLayout(null);
		
		JLabel lbl_nombreEmpleado = new JLabel("Nombre Empleado");
		lbl_nombreEmpleado.setFont(new Font("Century Schoolbook", Font.BOLD, 20));
		lbl_nombreEmpleado.setForeground(new Color(255, 255, 255));
		lbl_nombreEmpleado.setBounds(109, 29, 218, 30);
		add(lbl_nombreEmpleado);
		
		JLabel lbl_categoria = new JLabel("Categoria");
		lbl_categoria.setForeground(Color.WHITE);
		lbl_categoria.setFont(new Font("Century Schoolbook", Font.PLAIN, 20));
		lbl_categoria.setBounds(109, 69, 218, 30);
		add(lbl_categoria);
		
		JButton btn_logout = new JButton("Logout");
		btn_logout.setFont(new Font("Century Schoolbook", Font.BOLD, 10));
		btn_logout.setBackground(new Color(76, 17, 48));
		btn_logout.setForeground(new Color(255, 255, 255));
		btn_logout.setBorder(null);
		btn_logout.setBounds(289, 97, 63, 20);
		add(btn_logout);
		btn_logout.addActionListener(list);
		
		btn_home.setForeground(Color.WHITE);
		btn_home.setFont(new Font("Century Schoolbook", Font.BOLD, 10));
		btn_home.setBorder(null);
		btn_home.setBackground(new Color(76, 17, 48));
		btn_home.setBounds(234, 97, 45, 20);
		add(btn_home);
		btn_home.addActionListener(list);
	}
	
	
}
