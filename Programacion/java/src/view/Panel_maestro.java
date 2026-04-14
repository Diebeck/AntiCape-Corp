package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import control.Listener;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class Panel_maestro extends JPanel {

	public Panel_maestro(Listener list) {
		setBackground(new Color(116, 27, 71));
		setLayout(null);
		setSize(362, 410);
		
		ImageIcon calendario = new ImageIcon(Panel_maestro.class.getResource("/img/calendario.png"));
		
		JLabel lblImgCalendar = new JLabel("");
		lblImgCalendar.setIcon(calendario);
//		lblImgCalendar.setBounds(255, 32, 62, 61);
//		add(lblImgCalendar);
		
		JButton btn_citas = new JButton(calendario);
		btn_citas.setToolTipText("Calendario");
		btn_citas.setText("Citas");
		btn_citas.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		btn_citas.setFont(new Font("Century Schoolbook", Font.PLAIN, 20));
		btn_citas.setBackground(new Color(255, 255, 255));
		btn_citas.setBounds(41, 32, 280, 62);
		//btn_citas.add(lblImgCalendar);
		add(btn_citas);
		btn_citas.addActionListener(list);
		
		JButton btn_clientes = new JButton("Clientes");
		btn_clientes.setFont(new Font("Century Schoolbook", Font.PLAIN, 20));
		btn_clientes.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		btn_clientes.setBackground(Color.WHITE);
		btn_clientes.setBounds(41, 126, 280, 62);
		add(btn_clientes);
		btn_clientes.addActionListener(list);
		
		JButton btn_talleres = new JButton("Talleres");
		btn_talleres.setFont(new Font("Century Schoolbook", Font.PLAIN, 20));
		btn_talleres.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		btn_talleres.setBackground(Color.WHITE);
		btn_talleres.setBounds(41, 220, 280, 62);
		add(btn_talleres);
		btn_talleres.addActionListener(list);
		
		JButton btn_empleados = new JButton("Empleados");
		btn_empleados.setFont(new Font("Century Schoolbook", Font.PLAIN, 20));
		btn_empleados.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		btn_empleados.setBackground(Color.WHITE);
		btn_empleados.setBounds(41, 314, 280, 62);
		add(btn_empleados);
		
		
		btn_empleados.addActionListener(list);
	}
}
