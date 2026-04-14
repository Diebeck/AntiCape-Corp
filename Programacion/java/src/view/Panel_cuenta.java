package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import control.Listener;
import javax.swing.SwingConstants;

public class Panel_cuenta extends JPanel {
	
	JButton btn_home = new JButton("");
	JButton btn_logout;
	
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
		lbl_categoria.setBounds(109, 69, 101, 30);
		add(lbl_categoria);
		
		btn_logout = new JButton("");
		btn_logout.setIcon(new ImageIcon(Panel_cuenta.class.getResource("/img/logout.png")));
		btn_logout.setFont(new Font("Century Schoolbook", Font.BOLD, 10));
		btn_logout.setBackground(new Color(76, 17, 48));
		btn_logout.setForeground(new Color(255, 255, 255));
		btn_logout.setBorder(null);
		btn_logout.setBounds(320, 87, 32, 30);
		btn_logout.setFocusPainted(false);
		add(btn_logout);
		btn_logout.addActionListener(list);
		btn_home.setIcon(new ImageIcon(Panel_cuenta.class.getResource("/img/home.png")));
		
		btn_home.setForeground(Color.WHITE);
		btn_home.setFont(new Font("Century Schoolbook", Font.BOLD, 10));
		btn_home.setBorder(null);
		btn_home.setBackground(new Color(76, 17, 48));
		btn_home.setBounds(278, 87, 32, 30);
		btn_home.setFocusPainted(false);
		add(btn_home);
		
		
		
		JLabel lblImagen = new JLabel("");
		lblImagen.setHorizontalAlignment(SwingConstants.CENTER);
		ImageIcon iconoPerfil = new ImageIcon(Panel_cuenta.class.getResource("/img/cuenta.png"));
		Image imagenPerfil = iconoPerfil.getImage();
		int anchoDeseado = 100; 
		int altoDeseado = 100;
		Image imagenEscalada = imagenPerfil.getScaledInstance(anchoDeseado, altoDeseado, Image.SCALE_SMOOTH);
		lblImagen.setIcon(new ImageIcon(imagenEscalada));
		lblImagen.setBounds(10, 0, 89, 127);
		add(lblImagen);
		
		
		btn_home.addActionListener(list);
	}

	/**
	 * @return the btn_logout
	 */
	public JButton getBtn_logout() {
		return btn_logout;
	}
	
	
}
