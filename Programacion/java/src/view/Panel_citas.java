package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import control.Listener;

import javax.swing.ImageIcon;

import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;
import javax.swing.JRadioButton;


public class Panel_citas extends JPanel {
	
	JButton btn_homeCitas = new JButton("");
	
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
		
		
		btn_homeCitas.setIcon(new ImageIcon(Panel_citas.class.getResource("/img/home.png")));
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
		
		JLabel lblCliente = new JLabel("Cliente:");
		lblCliente.setFont(new Font("Century Schoolbook", Font.PLAIN, 18));
		lblCliente.setBounds(38, 120, 87, 20);
		add(lblCliente);
		
		JComboBox cbCliente = new JComboBox();
		cbCliente.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cbCliente.setBounds(126, 120, 290, 25);
		add(cbCliente);
		
		JLabel lblTaller = new JLabel("Taller:");
		lblTaller.setFont(new Font("Century Schoolbook", Font.PLAIN, 18));
		lblTaller.setBounds(38, 180, 87, 20);
		add(lblTaller);
		
		JComboBox cbTaller = new JComboBox();
		cbTaller.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cbTaller.setBounds(126, 180, 290, 25);
		add(cbTaller);
		
		JLabel lblDuracion = new JLabel("Duración:");
		lblDuracion.setFont(new Font("Century Schoolbook", Font.PLAIN, 18));
		lblDuracion.setBounds(454, 180, 87, 20);
		add(lblDuracion);
		
		JSpinner spDuracion = new JSpinner();
		spDuracion.setFont(new Font("Tahoma", Font.PLAIN, 15));
		spDuracion.setModel(new SpinnerNumberModel(1, 1, 5, 1));
		spDuracion.setBounds(551, 180, 124, 25);
		add(spDuracion);
		
		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setFont(new Font("Century Schoolbook", Font.PLAIN, 18));
		lblFecha.setBounds(38, 243, 87, 20);
		add(lblFecha);
		
		JSpinner spFecha = new JSpinner();
		spFecha.setFont(new Font("Century Schoolbook", Font.PLAIN, 15));
		spFecha.setModel(new SpinnerDateModel(new Date(1776117600000L), null, null, Calendar.DAY_OF_YEAR));
		spFecha.setBounds(126, 243, 549, 25);
		add(spFecha);
		
		JLabel lblTraje = new JLabel("Traje:");
		lblTraje.setFont(new Font("Century Schoolbook", Font.PLAIN, 18));
		lblTraje.setBounds(38, 308, 87, 20);
		add(lblTraje);
		
		ButtonGroup bg = new ButtonGroup();
		
		JRadioButton rdbtnPrincipal = new JRadioButton("Principal");
		rdbtnPrincipal.setSelected(true);
		rdbtnPrincipal.setFont(new Font("Century Schoolbook", Font.PLAIN, 18));
		rdbtnPrincipal.setBounds(126, 308, 127, 20);
		add(rdbtnPrincipal);
		bg.add(rdbtnPrincipal);
		
		JRadioButton rdbtnEspecifico = new JRadioButton("Especifico");
		rdbtnEspecifico.setFont(new Font("Century Schoolbook", Font.PLAIN, 18));
		rdbtnEspecifico.setBounds(300, 308, 127, 20);
		add(rdbtnEspecifico);
		bg.add(rdbtnEspecifico);
		
		JLabel lblEncargado = new JLabel("Encargado:");
		lblEncargado.setFont(new Font("Century Schoolbook", Font.PLAIN, 18));
		lblEncargado.setBounds(38, 375, 107, 20);
		add(lblEncargado);
		
		JComboBox cbEncargado = new JComboBox();
		cbEncargado.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cbEncargado.setBounds(155, 375, 520, 25);
		add(cbEncargado);
		btn_cCita.addActionListener(list);
	}

	/**
	 * @return the btn_homeCitas
	 */
	public JButton getBtn_homeCitas() {
		return btn_homeCitas;
	}
	
	
}
