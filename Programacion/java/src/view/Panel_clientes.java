package view;

import control.Listener;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;

@SuppressWarnings("serial")
public class Panel_clientes extends JPanel {
	
	private JButton btn_homeClientes = new JButton("");
	private JButton btn_cCliente;
	private JLabel lbl_yClientes;
	private JTextField tfNombre;
	private JTextField tfColores;
	private JTextField tfSuperpoder;
	private JTextField tfNombreT;
	private JLabel lblNombre;
	private JLabel lbl_cliente;
	private JLabel lblColores;
	private JLabel lblSuperpoder;
	private JRadioButton rdbtnDiseno;
	private JRadioButton rdbtnCostura;
	private JRadioButton rdbtnTaller;
	private JLabel lbl_traje;
	private JLabel lblNombreT;
	private JLabel lblEstado;
	private ButtonGroup bg;
	private ButtonGroup alineacion;
	private JRadioButton rdbtnHeroe;
	private JRadioButton rdbtnVillano;
	private JButton btnEditarTraje;
	@SuppressWarnings("rawtypes")
	private JComboBox comboTrajes;

	
	// El modo determina que accion se va a hacer (crear / modificar)
	String modo = "Crear";
	
	/** Cambia el modo del panel y actualiza su label
	 * @param modo
	 */
	public void setModo(String modo) {
		this.modo = modo;
		lbl_yClientes.setText(modo +" un cliente");
		System.out.println("Cambiado el modo del panel clientes a "+ modo);
	}
	
	/** Devuelve el modo del panel
	 * @return modo
	 */
	public String getModo() {
		return modo;
	}
	
	@SuppressWarnings("rawtypes")
	public Panel_clientes(Listener list) {
		setLayout(null);
		setSize(723, 545);
		
		lbl_yClientes = new JLabel(modo +" un cliente");
		lbl_yClientes.setForeground(new Color(116, 27, 71));
		lbl_yClientes.setFont(new Font("Century Schoolbook", Font.PLAIN, 30));
		lbl_yClientes.setBackground(new Color(76, 17, 48));
		lbl_yClientes.setBounds(38, 25, 437, 52);
		add(lbl_yClientes);
		
		btn_homeClientes.setIcon(new ImageIcon(Panel_clientes.class.getResource("/img/home.png")));
		btn_homeClientes.setForeground(Color.WHITE);
		btn_homeClientes.setFont(new Font("Century Schoolbook", Font.BOLD, 15));
		btn_homeClientes.setBackground(new Color(76, 17, 48));
		btn_homeClientes.setBounds(38, 455, 221, 44);
		add(btn_homeClientes);
		btn_homeClientes.addActionListener(list);
		
		btn_cCliente = new JButton("Confirmar");
		btn_cCliente.setForeground(Color.WHITE);
		btn_cCliente.setFont(new Font("Century Schoolbook", Font.BOLD, 15));
		btn_cCliente.setBackground(new Color(76, 17, 48));
		btn_cCliente.setBounds(454, 455, 221, 44);
		add(btn_cCliente);
		
		lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Century Schoolbook", Font.PLAIN, 18));
		lblNombre.setBounds(38, 149, 87, 20);
		add(lblNombre);
		
		lbl_cliente = new JLabel("Datos del Cliente");
		lbl_cliente.setForeground(new Color(0, 0, 0));
		lbl_cliente.setFont(new Font("Century Schoolbook", Font.ITALIC, 24));
		lbl_cliente.setBackground(new Color(76, 17, 48));
		lbl_cliente.setBounds(38, 80, 437, 52);
		add(lbl_cliente);
		
		tfNombre = new JTextField();
		tfNombre.setFont(new Font("Century Schoolbook", Font.PLAIN, 15));
		tfNombre.setBounds(168, 148, 253, 25);
		add(tfNombre);
		tfNombre.setColumns(10);
		
		lblColores = new JLabel("Colores:");
		lblColores.setFont(new Font("Century Schoolbook", Font.PLAIN, 18));
		lblColores.setBounds(38, 195, 87, 20);
		add(lblColores);
		
		tfColores = new JTextField();
		tfColores.setFont(new Font("Century Schoolbook", Font.PLAIN, 15));
		tfColores.setColumns(10);
		tfColores.setBounds(168, 194, 507, 25);
		add(tfColores);
		
		lblSuperpoder = new JLabel("Superpoder:");
		lblSuperpoder.setFont(new Font("Century Schoolbook", Font.PLAIN, 18));
		lblSuperpoder.setBounds(38, 240, 120, 20);
		add(lblSuperpoder);
		
		tfSuperpoder = new JTextField();
		tfSuperpoder.setFont(new Font("Century Schoolbook", Font.PLAIN, 15));
		tfSuperpoder.setColumns(10);
		tfSuperpoder.setBounds(168, 239, 507, 25);
		add(tfSuperpoder);
		
		lbl_traje = new JLabel("Datos del Traje");
		lbl_traje.setForeground(Color.BLACK);
		lbl_traje.setFont(new Font("Century Schoolbook", Font.ITALIC, 24));
		lbl_traje.setBackground(new Color(76, 17, 48));
		lbl_traje.setBounds(38, 287, 437, 52);
		add(lbl_traje);
		
		tfNombreT = new JTextField();
		tfNombreT.setFont(new Font("Century Schoolbook", Font.PLAIN, 15));
		tfNombreT.setColumns(10);
		tfNombreT.setBounds(168, 348, 253, 25);
		add(tfNombreT);
		
		lblNombreT = new JLabel("Nombre:");
		lblNombreT.setFont(new Font("Century Schoolbook", Font.PLAIN, 18));
		lblNombreT.setBounds(38, 349, 87, 20);
		add(lblNombreT);
		
		lblEstado = new JLabel("Estado:");
		lblEstado.setFont(new Font("Century Schoolbook", Font.PLAIN, 18));
		lblEstado.setBounds(38, 392, 87, 20);
		add(lblEstado);
		
		bg = new ButtonGroup();
		alineacion = new ButtonGroup();
		
		rdbtnDiseno = new JRadioButton("Diseño");
		rdbtnDiseno.setSelected(true);
		rdbtnDiseno.setFont(new Font("Century Schoolbook", Font.PLAIN, 18));
		rdbtnDiseno.setBounds(168, 392, 102, 20);
		add(rdbtnDiseno);
		bg.add(rdbtnDiseno);
		
		rdbtnCostura = new JRadioButton("Costura");
		rdbtnCostura.setFont(new Font("Century Schoolbook", Font.PLAIN, 18));
		rdbtnCostura.setBounds(339, 392, 102, 20);
		add(rdbtnCostura);
		bg.add(rdbtnCostura);
		
		rdbtnTaller = new JRadioButton("Taller");
		rdbtnTaller.setFont(new Font("Century Schoolbook", Font.PLAIN, 18));
		rdbtnTaller.setBounds(528, 392, 102, 20);
		add(rdbtnTaller);
		bg.add(rdbtnTaller);
		
		rdbtnHeroe = new JRadioButton("Heroe ");
		rdbtnHeroe.setFont(new Font("Century Schoolbook", Font.PLAIN, 18));
		rdbtnHeroe.setBounds(469, 149, 102, 20);
		add(rdbtnHeroe);
		alineacion.add(rdbtnHeroe);
		
		rdbtnVillano = new JRadioButton("Villano");
		rdbtnVillano.setFont(new Font("Century Schoolbook", Font.PLAIN, 18));
		rdbtnVillano.setBounds(573, 149, 102, 20);
		add(rdbtnVillano);
		alineacion.add(rdbtnVillano);
		
		btnEditarTraje = new JButton("Editar traje");
		btnEditarTraje.setFont(new Font("Century Schoolbook", Font.PLAIN, 13));
		btnEditarTraje.setForeground(new Color(255, 255, 255));
		btnEditarTraje.setBackground(new Color(74, 17, 48));
		btnEditarTraje.setBounds(454, 349, 221, 32);
		add(btnEditarTraje);
		
		comboTrajes = new JComboBox();
		comboTrajes.setFont(new Font("Century Schoolbook", Font.PLAIN, 13));
		comboTrajes.setBounds(168, 349, 253, 28);
		add(comboTrajes);
		
		btn_cCliente.addActionListener(list);
		btnEditarTraje.addActionListener(list);
		
		
	}

	/**
	 * @return the btn_homeClientes
	 */
	public JButton getBtn_homeClientes() {
		return btn_homeClientes;
	}
	
	public JButton getBtn_cCliente() {
		return btn_cCliente;
	}
	
	public JLabel getLbl_yClientes() {
		return lbl_yClientes;
	}
	
	public JTextField getTfNombre() {
		return tfNombre;
	}
	
	public JTextField getTfColores() {
		return tfColores;
	}
	
	public JTextField getTfSuperpoder() {
		return tfSuperpoder;
	}
	
	public JTextField getTfNombreT() {
		return tfNombreT;
	}
	
	public JRadioButton getRdbtnDiseno() {
		return rdbtnDiseno;
	}
	
	public JRadioButton getRdbtnCostura() {
		return rdbtnCostura;
	}
	
	public JRadioButton getRdbtnTaller() {
		return rdbtnTaller;
	}

	public JRadioButton getRdbtnHeroe() {
		return rdbtnHeroe;
	}

	public JRadioButton getRdbtnVillano() {
		return rdbtnVillano;
	}

	public JLabel getLblNombre() {
		return lblNombre;
	}

	public JLabel getLbl_cliente() {
		return lbl_cliente;
	}

	public JLabel getLblColores() {
		return lblColores;
	}

	public JLabel getLblSuperpoder() {
		return lblSuperpoder;
	}

	public JLabel getLbl_traje() {
		return lbl_traje;
	}

	public JLabel getLblNombreT() {
		return lblNombreT;
	}

	public JLabel getLblEstado() {
		return lblEstado;
	}

	public ButtonGroup getBg() {
		return bg;
	}

	public ButtonGroup getAlineacion() {
		return alineacion;
	}

	public JButton getBtnEditarTraje() {
		return btnEditarTraje;
	}

	@SuppressWarnings("rawtypes")
	public JComboBox getComboTrajes() {
		return comboTrajes;
	}
	
	
}
