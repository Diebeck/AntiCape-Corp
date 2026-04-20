package view;

import control.Listener;

import java.awt.Color;
import java.awt.Font;

import javax.swing.*;

@SuppressWarnings("serial")
public class Panel_talleres extends JPanel {
	
	private JButton btn_homeTaller = new JButton("");
	private JButton btn_cTaller;
	private JLabel lbl_yTaller;
	private JLabel lblTipo;
	private JLabel lblInfo;
	private JLabel lblNombre;
	private JTextField txtNombre;
	private JRadioButton rdbtnDiseno;
	private JRadioButton rdbtnCostura;
	private JRadioButton rdbtnPruebas;
	private ButtonGroup bg;
	
	// El modo determina que accion se va a hacer (crear / modificar)
	String modo = "Crear";
	
	/** Cambia el modo del panel y actualiza su label
	 * @param modo
	 */
	public void setModo(String modo) {
		this.modo = modo;
		lbl_yTaller.setText(modo +" un taller");
		System.out.println("Cambiado el modo del panel talleres a "+ modo);
	}
	
	/** Devuelve el modo del panel
	 * @return modo
	 */
	public String getModo() {
		return modo;
	}
	
	public Panel_talleres(Listener list) {
		setLayout(null);
		setSize(723, 545);
		
		lbl_yTaller = new JLabel(modo +" un taller");
		lbl_yTaller.setForeground(new Color(116, 27, 71));
		lbl_yTaller.setFont(new Font("Century Schoolbook", Font.PLAIN, 30));
		lbl_yTaller.setBackground(new Color(76, 17, 48));
		lbl_yTaller.setBounds(38, 25, 437, 52);
		add(lbl_yTaller);
		
		btn_homeTaller.setIcon(new ImageIcon(Panel_talleres.class.getResource("/img/home.png")));
		btn_homeTaller.setForeground(Color.WHITE);
		btn_homeTaller.setFont(new Font("Century Schoolbook", Font.BOLD, 15));
		btn_homeTaller.setBackground(new Color(76, 17, 48));
		btn_homeTaller.setBounds(38, 455, 221, 44);
		add(btn_homeTaller);
		btn_homeTaller.addActionListener(list);
		
		btn_cTaller = new JButton("Confirmar");
		btn_cTaller.setForeground(Color.WHITE);
		btn_cTaller.setFont(new Font("Century Schoolbook", Font.BOLD, 15));
		btn_cTaller.setBackground(new Color(76, 17, 48));
		btn_cTaller.setBounds(454, 455, 221, 44);
		add(btn_cTaller);
		
		lblTipo = new JLabel("Tipo de Taller:");
		lblTipo.setFont(new Font("Century Schoolbook", Font.PLAIN, 18));
		lblTipo.setBounds(41, 140, 124, 20);
		add(lblTipo);
		
		bg = new ButtonGroup();
		
		rdbtnDiseno = new JRadioButton("Diseño");
		rdbtnDiseno.setSelected(true);
		rdbtnDiseno.setFont(new Font("Century Schoolbook", Font.PLAIN, 18));
		rdbtnDiseno.setBounds(223, 140, 102, 20);
		add(rdbtnDiseno);
		bg.add(rdbtnDiseno);
		
		rdbtnCostura = new JRadioButton("Costura");
		rdbtnCostura.setFont(new Font("Century Schoolbook", Font.PLAIN, 18));
		rdbtnCostura.setBounds(373, 140, 102, 20);
		add(rdbtnCostura);
		bg.add(rdbtnCostura);
		
		rdbtnPruebas = new JRadioButton("Pruebas");
		rdbtnPruebas.setFont(new Font("Century Schoolbook", Font.PLAIN, 18));
		rdbtnPruebas.setBounds(531, 140, 102, 20);
		add(rdbtnPruebas);
		bg.add(rdbtnPruebas);
		
		lblInfo = new JLabel("Asigne la ubicación y tipo del taller");
		lblInfo.setForeground(new Color(128, 128, 128));
		lblInfo.setFont(new Font("Century Schoolbook", Font.ITALIC, 12));
		lblInfo.setBounds(39, 79, 519, 20);
		add(lblInfo);
		
		lblNombre = new JLabel("Nombre de la Sala:");
		lblNombre.setFont(new Font("Century Schoolbook", Font.PLAIN, 18));
		lblNombre.setBounds(38, 219, 180, 20);
		add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(223, 220, 410, 25);
		add(txtNombre);
		txtNombre.setColumns(10);
		btn_cTaller.addActionListener(list);
	}

	/**
	 * @return the btn_homeTaller
	 */
	public JButton getBtn_homeTaller() {
		return btn_homeTaller;
	}
	
	public JButton getBtn_cTaller() {
		return btn_cTaller;
	}
	
	public JLabel getLbl_yTaller() {
		return lbl_yTaller;
	}
	
	public JTextField getTxtNombre() {
		return txtNombre;
	}
	
	public JRadioButton getRdbtnDiseno() {
		return rdbtnDiseno;
	}
	
	public JRadioButton getRdbtnCostura() {
		return rdbtnCostura;
	}
	
	public JRadioButton getRdbtnPruebas() {
		return rdbtnPruebas;
	}
}
