package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;

import control.Listener;

public class Panel_x extends JPanel {
	
	// El estado determina en que apartado estamos
	// ej: si estamos en citas, se vera "Gestión de citas" y la tabla tendrá los datos de las citas
	String estado = "citas";
	JLabel lbl_gestionX = new JLabel("Gestión de "+ estado);
	
	// Pone el estado y actualiza los componentes
	public void setEstado(String estado) {
		this.estado = estado;
		lbl_gestionX.setText("Gestión de "+ estado);
		System.out.println("Cambiado el estado a "+ estado);
	}
	// Devuelve el estado
	public String getEstado() {
		return estado;
	}
	
	public Panel_x(Listener list) {
		setLayout(null);
		setSize(723, 545);
		
		lbl_gestionX.setBackground(new Color(76, 17, 48));
		lbl_gestionX.setForeground(new Color(116, 27, 71));
		lbl_gestionX.setFont(new Font("Century Schoolbook", Font.PLAIN, 28));
		lbl_gestionX.setBounds(40, 21, 302, 52);
		add(lbl_gestionX);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(40, 101, 632, 374);
		add(scrollPane);
		
		JTable table;
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel_botones = new JPanel();
		panel_botones.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		panel_botones.setBounds(350, 21, 322, 52);
		add(panel_botones);
		panel_botones.setLayout(new GridLayout(0, 3, 0, 0));
		
		JButton btn_crear = new JButton("Crear");
		btn_crear.setBackground(new Color(255, 255, 255));
		btn_crear.setFont(new Font("Century Schoolbook", Font.BOLD, 13));
		panel_botones.add(btn_crear);
		btn_crear.addActionListener(list);
		
		JButton btn_modificar = new JButton("Modificar");
		btn_modificar.setBackground(new Color(255, 255, 255));
		btn_modificar.setFont(new Font("Century Schoolbook", Font.BOLD, 13));
		panel_botones.add(btn_modificar);
		btn_modificar.addActionListener(list);
		
		JButton btn_eliminar = new JButton("Eliminar");
		btn_eliminar.setBackground(new Color(255, 255, 255));
		btn_eliminar.setFont(new Font("Century Schoolbook", Font.BOLD, 13));
		panel_botones.add(btn_eliminar);
		btn_eliminar.addActionListener(list);
	}
}
