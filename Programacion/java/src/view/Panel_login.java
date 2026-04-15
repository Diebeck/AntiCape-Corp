package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import control.Listener;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public class Panel_login extends JPanel {
	private JLabel lbl_login1; 
	private JTextField textField_usuario;
	private JPasswordField passwordField_contrasena;
	private JButton btn_login;
	private JLabel lblUsuarioIcono;
	private JLabel lblContraIcono;
	
	public Panel_login(Listener list) {
		setLayout(null);
		setSize(723, 545);
		
		lbl_login1 = new JLabel("Employee Login");
		lbl_login1.setForeground(new Color(116, 27, 71));
		lbl_login1.setFont(new Font("Century Schoolbook", Font.ITALIC, 60));
		lbl_login1.setBounds(61, 46, 579, 88);
		add(lbl_login1);
		
		
		textField_usuario = new JTextField();
		textField_usuario.setFont(new Font("Century Schoolbook", Font.PLAIN, 30));
		textField_usuario.setBorder(new LineBorder(new Color(171, 173, 179), 3, true));
		textField_usuario.setToolTipText("usuario");
		textField_usuario.setBounds(161, 168, 432, 60);
		add(textField_usuario);
		textField_usuario.setColumns(10);
		
		passwordField_contrasena = new JPasswordField();
		passwordField_contrasena.setFont(new Font("Century Schoolbook", Font.PLAIN, 30));
		passwordField_contrasena.setBorder(new LineBorder(new Color(171, 173, 179), 3, true));
		passwordField_contrasena.setEchoChar('*');
		passwordField_contrasena.setColumns(10);
		passwordField_contrasena.setBounds(161, 269, 432, 60);
		add(passwordField_contrasena);
		
		btn_login = new JButton("Login");
		btn_login.addActionListener(list);
		btn_login.setForeground(new Color(255, 255, 255));
		btn_login.setBackground(new Color(76, 17, 48));
		btn_login.setFont(new Font("Century Schoolbook", Font.BOLD, 15));
		btn_login.setBounds(61, 383, 532, 60);
		add(btn_login);
		
		lblUsuarioIcono = new JLabel("");
		lblUsuarioIcono.setIcon(new ImageIcon(Panel_login.class.getResource("/img/usuarioLogin.png")));
		lblUsuarioIcono.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsuarioIcono.setBounds(61, 168, 60, 60);
		add(lblUsuarioIcono);
		
		lblContraIcono = new JLabel("");
		lblContraIcono.setIcon(new ImageIcon(Panel_login.class.getResource("/img/contraLogin.png")));
		lblContraIcono.setHorizontalAlignment(SwingConstants.CENTER);
		lblContraIcono.setBounds(61, 269, 60, 60);
		add(lblContraIcono);
	}
	
	public JLabel getLbl_login1() {
		return lbl_login1;
	}

	public JTextField getTextField_usuario() {
		return textField_usuario;
	}

	public String getPasswordField_contrasena() {
		char [] con = passwordField_contrasena.getPassword();
		String contraseña = new String(con);
		return contraseña;
	}

	public JButton getBtn_login() {
		return btn_login;
	}

	public JLabel getLblUsuarioIcono() {
		return lblUsuarioIcono;
	}

	public JLabel getLblContraIcono() {
		return lblContraIcono;
	}

}
