package view;

import control.Listener;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JComboBox;

@SuppressWarnings("serial")
public class Panel_trajes extends JPanel {
	
	private JButton btn_cancelar = new JButton("Cancelar");
	private JButton btn_confirmar;
	private JLabel lbl_traje;
	private JTextField tfNombre;
	private JLabel lblNombre;
	private JLabel lblCliente;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBox;
	private JLabel lblEnunciado;
	@SuppressWarnings("rawtypes")
	public Panel_trajes(Listener list) {
		setLayout(null);
		setSize(723, 545);
		
		lbl_traje = new JLabel("Creación de traje");
		lbl_traje.setForeground(new Color(116, 27, 71));
		lbl_traje.setFont(new Font("Century Schoolbook", Font.PLAIN, 40));
		lbl_traje.setBackground(new Color(76, 17, 48));
		lbl_traje.setBounds(38, 68, 437, 52);
		add(lbl_traje);
		btn_cancelar.setForeground(Color.WHITE);
		btn_cancelar.setFont(new Font("Century Schoolbook", Font.BOLD, 15));
		btn_cancelar.setBackground(new Color(76, 17, 48));
		btn_cancelar.setBounds(38, 455, 221, 44);
		add(btn_cancelar);
		btn_cancelar.addActionListener(list);
		
		btn_confirmar = new JButton("Confirmar");
		btn_confirmar.setForeground(Color.WHITE);
		btn_confirmar.setFont(new Font("Century Schoolbook", Font.BOLD, 15));
		btn_confirmar.setBackground(new Color(76, 17, 48));
		btn_confirmar.setBounds(454, 455, 221, 44);
		add(btn_confirmar);
		
		lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Century Schoolbook", Font.PLAIN, 18));
		lblNombre.setBounds(38, 182, 87, 20);
		add(lblNombre);
		
		tfNombre = new JTextField();
		tfNombre.setFont(new Font("Century Schoolbook", Font.PLAIN, 15));
		tfNombre.setBounds(135, 181, 507, 25);
		add(tfNombre);
		tfNombre.setColumns(10);
		
		lblCliente = new JLabel("Cliente:");
		lblCliente.setFont(new Font("Century Schoolbook", Font.PLAIN, 18));
		lblCliente.setBounds(38, 278, 87, 20);
		add(lblCliente);
		
		comboBox = new JComboBox();
		comboBox.setBounds(138, 279, 507, 25);
		add(comboBox);
		
		lblEnunciado = new JLabel("Asigne un nuevo traje a un cliente existente");
		lblEnunciado.setForeground(new Color(128, 128, 128));
		lblEnunciado.setFont(new Font("Century Schoolbook", Font.ITALIC, 16));
		lblEnunciado.setBounds(48, 130, 427, 20);
		add(lblEnunciado);
		
		btn_confirmar.addActionListener(list);
	}

	/**
	 * @return the btn_homeClientes
	 */
	public JButton getBtn_cancelar() {
		return btn_cancelar;
	}
	
	public JButton getBtn_confirmar() {
		return btn_confirmar;
	}
	
	public JLabel getLbl_traje() {
		return lbl_traje;
	}
	
	public JTextField getTfNombre() {
		return tfNombre;
	}
	
	@SuppressWarnings("rawtypes")
	public JComboBox getComboBox() {
		return comboBox;
	}
}
