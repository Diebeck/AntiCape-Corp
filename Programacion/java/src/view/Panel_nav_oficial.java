package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import control.Listener;

public class Panel_nav_oficial extends JPanel {

	public Panel_nav_oficial(Listener list) {
		setBackground(new Color(116, 27, 71));
		setLayout(null);
		setSize(362, 410);
		
		JButton btn_citas = new JButton("Citas");
		btn_citas.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		btn_citas.setFont(new Font("Century Schoolbook", Font.PLAIN, 20));
		btn_citas.setBackground(new Color(255, 255, 255));
		btn_citas.setBounds(41, 56, 280, 62);
		add(btn_citas);
		btn_citas.addActionListener(list);
		
		JButton btn_clientes = new JButton("Clientes");
		btn_clientes.setFont(new Font("Century Schoolbook", Font.PLAIN, 20));
		btn_clientes.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		btn_clientes.setBackground(Color.WHITE);
		btn_clientes.setBounds(41, 174, 280, 62);
		add(btn_clientes);
		btn_clientes.addActionListener(list);
		
		JButton btn_empleados = new JButton("Empleados");
		btn_empleados.setFont(new Font("Century Schoolbook", Font.PLAIN, 20));
		btn_empleados.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		btn_empleados.setBackground(Color.WHITE);
		btn_empleados.setBounds(41, 292, 280, 62);
		add(btn_empleados);
		btn_empleados.addActionListener(list);
	}
}
