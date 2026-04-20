package view;

import control.Listener;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class Panel_cuenta extends JPanel {
	
	private JButton btn_home = new JButton("");
	private JButton btn_logout = new JButton("");
	private JLabel lbl_nombreEmpleado;
	private JLabel lbl_categoria;
	private JLabel lblImagen;
	
	public Panel_cuenta(Listener list) {
		
		setBackground(new Color(76, 17, 48));
		setBounds(0, 0, 362, 127);
		setLayout(null);
		
		lbl_nombreEmpleado = new JLabel("Nombre Empleado");
		lbl_nombreEmpleado.setFont(new Font("Century Schoolbook", Font.BOLD, 20));
		lbl_nombreEmpleado.setForeground(new Color(255, 255, 255));
		lbl_nombreEmpleado.setBounds(109, 29, 218, 30);
		add(lbl_nombreEmpleado);
		
		lbl_categoria = new JLabel("Categoria");
		lbl_categoria.setForeground(Color.WHITE);
		lbl_categoria.setFont(new Font("Century Schoolbook", Font.PLAIN, 20));
		lbl_categoria.setBounds(109, 69, 101, 30);
		add(lbl_categoria);
		
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
		btn_home.addActionListener(list);
		
		lblImagen = new JLabel("");
		lblImagen.setHorizontalAlignment(SwingConstants.CENTER);
		lblImagen.setIcon(Ventana.escalarImagen("/img/cuenta.png", 80, 80));
		lblImagen.setBounds(10, 0, 89, 127);
		add(lblImagen);
	}

	/**
	 * @return el btn_logout
	 */
	public JButton getBtn_logout() {
		return btn_logout;
	}
	
	/**
	 * Esconde el boton de home
	 */
	public void esconderHome() {
		remove(btn_home);
	}
	
	/**
	 * Muestra el boton de home
	 */
	public void mostrarHome() {
		add(btn_home);
	}
	
	public JButton getBotonHome() {
		return btn_home;
	}

	public JButton getBtn_home() {
		return btn_home;
	}

	public JLabel getLbl_nombreEmpleado() {
		return lbl_nombreEmpleado;
	}

	public JLabel getLbl_categoria() {
		return lbl_categoria;
	}

	public JLabel getLblImagen() {
		return lblImagen;
	}
	
	
}
