package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import control.Listener;

import javax.swing.ImageIcon;

import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.JTextField;


public class Panel_empleados extends JPanel {
	
	private JButton btn_homeEmpleado = new JButton("");
	private JButton btn_cEmpleado;
	private JLabel lbl_yEmpleado;
	private JTextField tfNombre;
	private JTextField tfContrasena;
	private JTextField tfApellidos;
	private JTextField tfUsuario;
	private JRadioButton rdbtnAprendiz;
	private JRadioButton rdbtnOficial;
	private JRadioButton rdbtnMaestro;
	private JLabel lblUsuario;
	
	// El modo determina que accion se va a hacer (crear / modificar)
	String modo = "Crear";
	
	// Pone el modo y actualiza los componentes
	public void setModo(String modo) {
		this.modo = modo;
		lbl_yEmpleado.setText(modo +" un empleado");
		System.out.println("Cambiado el modo del panel empleados a "+ modo);
	}
	
	public String getModo() {
		return modo;
	}
	
	public Panel_empleados(Listener list) {
		setLayout(null);
		setSize(723, 545);
		
		lbl_yEmpleado = new JLabel(modo +" un empleado");
		lbl_yEmpleado.setForeground(new Color(116, 27, 71));
		lbl_yEmpleado.setFont(new Font("Century Schoolbook", Font.PLAIN, 30));
		lbl_yEmpleado.setBackground(new Color(76, 17, 48));
		lbl_yEmpleado.setBounds(38, 25, 437, 52);
		add(lbl_yEmpleado);
		
		
		btn_homeEmpleado.setIcon(new ImageIcon(Panel_empleados.class.getResource("/img/home.png")));
		btn_homeEmpleado.setForeground(Color.WHITE);
		btn_homeEmpleado.setFont(new Font("Century Schoolbook", Font.BOLD, 15));
		btn_homeEmpleado.setBackground(new Color(76, 17, 48));
		btn_homeEmpleado.setBounds(38, 455, 221, 44);
		add(btn_homeEmpleado);
		btn_homeEmpleado.addActionListener(list);
		
		btn_cEmpleado = new JButton("Confirmar");
		btn_cEmpleado.setForeground(Color.WHITE);
		btn_cEmpleado.setFont(new Font("Century Schoolbook", Font.BOLD, 15));
		btn_cEmpleado.setBackground(new Color(76, 17, 48));
		btn_cEmpleado.setBounds(454, 455, 221, 44);
		add(btn_cEmpleado);
		
		JLabel lblIngreseLosDatos = new JLabel("Ingrese los datos del empleado");
		lblIngreseLosDatos.setForeground(Color.GRAY);
		lblIngreseLosDatos.setFont(new Font("Century Schoolbook", Font.ITALIC, 12));
		lblIngreseLosDatos.setBounds(39, 80, 519, 20);
		add(lblIngreseLosDatos);
		
		JLabel lblCategoria = new JLabel("Categoria:");
		lblCategoria.setVerticalAlignment(SwingConstants.TOP);
		lblCategoria.setFont(new Font("Century Schoolbook", Font.PLAIN, 18));
		lblCategoria.setBounds(38, 110, 124, 36);
		add(lblCategoria);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Century Schoolbook", Font.PLAIN, 18));
		lblNombre.setBounds(35, 169, 93, 20);
		add(lblNombre);
		
		ButtonGroup bg = new ButtonGroup();
		
		rdbtnAprendiz = new JRadioButton("Aprendiz");
		rdbtnAprendiz.setSelected(true);
		rdbtnAprendiz.setFont(new Font("Century Schoolbook", Font.PLAIN, 18));
		rdbtnAprendiz.setBounds(168, 111, 124, 20);
		add(rdbtnAprendiz);
		bg.add(rdbtnAprendiz);
		
		rdbtnOficial = new JRadioButton("Oficial");
		rdbtnOficial.setFont(new Font("Century Schoolbook", Font.PLAIN, 18));
		rdbtnOficial.setBounds(345, 111, 102, 20);
		add(rdbtnOficial);
		bg.add(rdbtnOficial);
		
		rdbtnMaestro = new JRadioButton("Maestro");
		rdbtnMaestro.setFont(new Font("Century Schoolbook", Font.PLAIN, 18));
		rdbtnMaestro.setBounds(507, 111, 102, 20);
		add(rdbtnMaestro);
		bg.add(rdbtnMaestro);
		
		tfNombre = new JTextField();
		tfNombre.setFont(new Font("Century Schoolbook", Font.PLAIN, 15));
		tfNombre.setColumns(10);
		tfNombre.setBounds(157, 168, 507, 25);
		add(tfNombre);
		
		JLabel lblContrasena = new JLabel("Contraseña:");
		lblContrasena.setFont(new Font("Century Schoolbook", Font.PLAIN, 18));
		lblContrasena.setBounds(35, 343, 127, 20);
		add(lblContrasena);
		
		tfContrasena = new JTextField();
		tfContrasena.setFont(new Font("Century Schoolbook", Font.PLAIN, 15));
		tfContrasena.setColumns(10);
		tfContrasena.setBounds(157, 342, 507, 25);
		add(tfContrasena);
		
		JLabel lblApellidos = new JLabel("Apellidos:");
		lblApellidos.setFont(new Font("Century Schoolbook", Font.PLAIN, 18));
		lblApellidos.setBounds(38, 220, 93, 20);
		add(lblApellidos);
		
		tfApellidos = new JTextField();
		tfApellidos.setFont(new Font("Century Schoolbook", Font.PLAIN, 15));
		tfApellidos.setColumns(10);
		tfApellidos.setBounds(157, 219, 507, 25);
		add(tfApellidos);
		
		lblUsuario = new JLabel("Usuario:");
		lblUsuario.setFont(new Font("Century Schoolbook", Font.PLAIN, 18));
		lblUsuario.setBounds(35, 276, 127, 20);
		add(lblUsuario);
		
		tfUsuario = new JTextField();
		tfUsuario.setFont(new Font("Century Schoolbook", Font.PLAIN, 15));
		tfUsuario.setColumns(10);
		tfUsuario.setBounds(157, 276, 507, 25);
		add(tfUsuario);
		btn_cEmpleado.addActionListener(list);
	}

	/**
	 * @return the btn_homeEmpleado
	 */
	public JButton getBtn_homeEmpleado() {
		return btn_homeEmpleado;
	}
	
	public JButton getBtn_cEmpleado() {
		return btn_cEmpleado;
	}
	
	public JLabel getLbl_yEmpleado() {
		return lbl_yEmpleado;
	}
	
	public JTextField getTfNombre() {
		return tfNombre;
	}
	
	public JTextField getTfContrasena() {
		return tfContrasena;
	}
	
	public JTextField getTfApellidos() {
		return tfApellidos;
	}
	
	public JTextField getTfUsuario() {
		return tfUsuario;
	}
	
	public JRadioButton getRdbtnAprendiz() {
		return rdbtnAprendiz;
	}
	
	public JRadioButton getRdbtnOficial() {
		return rdbtnOficial;
	}
	
	public JRadioButton getRdbtnMaestro() {
		return rdbtnMaestro;
	}
}
