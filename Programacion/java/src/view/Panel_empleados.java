package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import control.Listener;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

public class Panel_empleados extends JPanel {
	
	// El modo determina que accion se va a hacer (crear / modificar)
	String modo = "Crear";
	JLabel lbl_yEmpleado = new JLabel(modo +" un empleado");
	private JTextField tfNombre;
	private JTextField tfContrasena;
	
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
		
		lbl_yEmpleado.setForeground(new Color(116, 27, 71));
		lbl_yEmpleado.setFont(new Font("Century Schoolbook", Font.PLAIN, 30));
		lbl_yEmpleado.setBackground(new Color(76, 17, 48));
		lbl_yEmpleado.setBounds(38, 25, 437, 52);
		add(lbl_yEmpleado);
		
		JButton btn_homeEmpleado = new JButton("Home");
		btn_homeEmpleado.setForeground(Color.WHITE);
		btn_homeEmpleado.setFont(new Font("Century Schoolbook", Font.BOLD, 15));
		btn_homeEmpleado.setBackground(new Color(76, 17, 48));
		btn_homeEmpleado.setBounds(38, 455, 221, 44);
		add(btn_homeEmpleado);
		btn_homeEmpleado.addActionListener(list);
		
		JButton btn_cEmpleado = new JButton("Confirmar");
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
		lblCategoria.setBounds(41, 141, 124, 36);
		add(lblCategoria);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Century Schoolbook", Font.PLAIN, 18));
		lblNombre.setBounds(38, 222, 93, 20);
		add(lblNombre);
		
		ButtonGroup bg = new ButtonGroup();
		
		JRadioButton rdbtnAprendiz = new JRadioButton("Aprendiz");
		rdbtnAprendiz.setSelected(true);
		rdbtnAprendiz.setFont(new Font("Century Schoolbook", Font.PLAIN, 18));
		rdbtnAprendiz.setBounds(223, 141, 124, 20);
		add(rdbtnAprendiz);
		bg.add(rdbtnAprendiz);
		
		JRadioButton rdbtnOficial = new JRadioButton("Oficial");
		rdbtnOficial.setFont(new Font("Century Schoolbook", Font.PLAIN, 18));
		rdbtnOficial.setBounds(373, 141, 102, 20);
		add(rdbtnOficial);
		bg.add(rdbtnOficial);
		
		JRadioButton rdbtnMaestro = new JRadioButton("Maestro");
		rdbtnMaestro.setFont(new Font("Century Schoolbook", Font.PLAIN, 18));
		rdbtnMaestro.setBounds(531, 141, 102, 20);
		add(rdbtnMaestro);
		bg.add(rdbtnMaestro);
		
		tfNombre = new JTextField();
		tfNombre.setFont(new Font("Century Schoolbook", Font.PLAIN, 15));
		tfNombre.setColumns(10);
		tfNombre.setBounds(168, 220, 507, 25);
		add(tfNombre);
		
		JLabel lblContrasena = new JLabel("Contraseña:");
		lblContrasena.setFont(new Font("Century Schoolbook", Font.PLAIN, 18));
		lblContrasena.setBounds(38, 292, 127, 20);
		add(lblContrasena);
		
		tfContrasena = new JTextField();
		tfContrasena.setFont(new Font("Century Schoolbook", Font.PLAIN, 15));
		tfContrasena.setColumns(10);
		tfContrasena.setBounds(168, 290, 507, 25);
		add(tfContrasena);
		btn_cEmpleado.addActionListener(list);
	}
}
