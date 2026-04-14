package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import control.Listener;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.border.LineBorder;

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
		
		JLabel lblTipo = new JLabel("Tipo de Taller:");
		lblTipo.setFont(new Font("Century Schoolbook", Font.PLAIN, 18));
		lblTipo.setBounds(41, 140, 124, 20);
		add(lblTipo);
		
		ButtonGroup bg = new ButtonGroup();
		
		JRadioButton rdbtnDiseno = new JRadioButton("Diseño");
		rdbtnDiseno.setSelected(true);
		rdbtnDiseno.setFont(new Font("Century Schoolbook", Font.PLAIN, 18));
		rdbtnDiseno.setBounds(223, 140, 102, 20);
		add(rdbtnDiseno);
		bg.add(rdbtnDiseno);
		
		JRadioButton rdbtnCostura = new JRadioButton("Costura");
		rdbtnCostura.setFont(new Font("Century Schoolbook", Font.PLAIN, 18));
		rdbtnCostura.setBounds(373, 140, 102, 20);
		add(rdbtnCostura);
		bg.add(rdbtnCostura);
		
		JRadioButton rdbtnPruebas = new JRadioButton("Pruebas");
		rdbtnPruebas.setFont(new Font("Century Schoolbook", Font.PLAIN, 18));
		rdbtnPruebas.setBounds(531, 140, 102, 20);
		add(rdbtnPruebas);
		bg.add(rdbtnPruebas);
		
		JLabel lblInfo = new JLabel("Asigne la ubicación y tipo del taller");
		lblInfo.setForeground(new Color(128, 128, 128));
		lblInfo.setFont(new Font("Century Schoolbook", Font.ITALIC, 12));
		lblInfo.setBounds(39, 79, 519, 20);
		add(lblInfo);
		
		JLabel lblNombre = new JLabel("Nombre de la Sala:");
		lblNombre.setFont(new Font("Century Schoolbook", Font.PLAIN, 18));
		lblNombre.setBounds(38, 219, 180, 20);
		add(lblNombre);
		
		JComboBox cbNombre = new JComboBox();
		cbNombre.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cbNombre.setBounds(223, 219, 410, 25);
		add(cbNombre);
		btn_cTaller.addActionListener(list);
	}
}
