package view;

import javax.swing.JFrame;

import control.Listener;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.CardLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.GridLayout;

public class Ventana extends JFrame {

	Listener list;
	
	// El estado determina en que apartado estamos
	// ej: si estamos en citas, se vera "Gestión de citas" y la tabla tendrá los datos de las citas
	private String estado = "citas"; 
	
	// El modo determina que accion se va a hacer (crear / modificar)
	private String modo = "Crear";
	
	// Panel primario
	JPanel panel_primario = new JPanel();
	CardLayout cartas_panel_primario = new CardLayout(0, 0);
	
	// Panel secundario
	JPanel panel_secundario = new JPanel();
	CardLayout cartas_panel_secundario = new CardLayout(0, 0);
	
	// Panel nav (secundario -> ambos -> nav)
	JPanel panel_nav = new JPanel();
	CardLayout cartas_panel_nav = new CardLayout(0, 0);
	
	JLabel lbl_gestionX = new JLabel("Gestión de "+ estado);
	JLabel lbl_yCitas = new JLabel(modo +" una cita");
	JLabel lbl_yClientes = new JLabel(modo +" un cliente");
	JLabel lbl_yTaller = new JLabel(modo +" un taller");
	JLabel lbl_yEmpleado = new JLabel(modo +" un empleado");
	
	// Constructor
	public Ventana(Listener list) {
		this.list = list;
		asignarLayouts();
		iniciar();
	}
	
	// Asignamos los layouts a los paneles que tienen CardLayout
	private void asignarLayouts() {
		panel_primario.setLayout(cartas_panel_primario);
		panel_secundario.setLayout(cartas_panel_secundario);
		panel_nav.setLayout(cartas_panel_nav);
		
	}

	// Funciones para cambiar la carta de los paneles con CardLayout
	public void cambiarPanelPrimario(String nombrePanel) {
		cartas_panel_primario.show(panel_primario, nombrePanel);
		System.out.println("Cambiado panel primario: "+ nombrePanel);
	}
	public void cambiarPanelSecundario(String nombrePanel) {
		cartas_panel_secundario.show(panel_secundario, nombrePanel);
		System.out.println("Cambiado panel secundario: "+ nombrePanel);
	}
	public void cambiarPanelNav(String nombrePanel) {
		cartas_panel_nav.show(panel_nav, nombrePanel);
		System.out.println("Cambiado panel nav: "+ nombrePanel);
	}
	
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
	// Pone el modo y actualiza los componentes
	public void setModo(String modo) {
		this.modo = modo;
		lbl_yCitas.setText(modo +" una cita");
		lbl_yClientes.setText(modo +" un cliente");
		lbl_yTaller.setText(modo +" un taller");
		lbl_yEmpleado.setText(modo +" un empleado");
		System.out.println("Cambiado el modo a "+ modo);
	}
	public String getModo() {
		return modo;
	}
	
	// El metodo grande principal
	private void iniciar() {
		setSize(1153, 604);
		
		getContentPane().setBackground(new Color(192, 192, 192));
		getContentPane().setLayout(null);
		
		panel_primario.setBorder(new LineBorder(new Color(0, 0, 0), 5));
		panel_primario.setBounds(406, 10, 723, 545);
		getContentPane().add(panel_primario);
		
		JPanel panel_login = new JPanel();
		panel_primario.add(panel_login, "panel_login");
		panel_login.setLayout(null);
		
		JLabel lbl_login1 = new JLabel("Employee Login");
		lbl_login1.setForeground(new Color(116, 27, 71));
		lbl_login1.setFont(new Font("Century Schoolbook", Font.ITALIC, 60));
		lbl_login1.setBounds(61, 46, 579, 88);
		panel_login.add(lbl_login1);
		
		JTextField textField_usuario;
		textField_usuario = new JTextField();
		textField_usuario.setFont(new Font("Century Schoolbook", Font.PLAIN, 30));
		textField_usuario.setBorder(new LineBorder(new Color(171, 173, 179), 3, true));
		textField_usuario.setToolTipText("usuario");
		textField_usuario.setBounds(161, 168, 432, 60);
		panel_login.add(textField_usuario);
		textField_usuario.setColumns(10);
		
		JPasswordField passwordField_contrasena;
		passwordField_contrasena = new JPasswordField();
		passwordField_contrasena.setFont(new Font("Century Schoolbook", Font.PLAIN, 30));
		passwordField_contrasena.setBorder(new LineBorder(new Color(171, 173, 179), 3, true));
		passwordField_contrasena.setEchoChar('*');
		passwordField_contrasena.setColumns(10);
		passwordField_contrasena.setBounds(161, 269, 432, 60);
		panel_login.add(passwordField_contrasena);
		
		JButton btn_login = new JButton("Login");
		btn_login.addActionListener(list);
		btn_login.setForeground(new Color(255, 255, 255));
		btn_login.setBackground(new Color(76, 17, 48));
		btn_login.setFont(new Font("Century Schoolbook", Font.BOLD, 15));
		btn_login.setBounds(61, 383, 532, 60);
		panel_login.add(btn_login);
		
		JPanel panel_home = new JPanel();
		panel_primario.add(panel_home, "panel_home");
		panel_home.setLayout(null);
		
		JLabel lbl_home1 = new JLabel("Home");
		lbl_home1.setBounds(40, 33, 441, 52);
		lbl_home1.setForeground(new Color(116, 27, 71));
		lbl_home1.setFont(new Font("Century Schoolbook", Font.PLAIN, 30));
		panel_home.add(lbl_home1);
		
		JPanel panel_x = new JPanel();
		panel_primario.add(panel_x, "panel_x");
		panel_x.setLayout(null);
		
		lbl_gestionX.setBackground(new Color(76, 17, 48));
		lbl_gestionX.setForeground(new Color(116, 27, 71));
		lbl_gestionX.setFont(new Font("Century Schoolbook", Font.PLAIN, 28));
		lbl_gestionX.setBounds(40, 21, 302, 52);
		panel_x.add(lbl_gestionX);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(40, 101, 632, 374);
		panel_x.add(scrollPane);
		
		JTable table;
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel_botones = new JPanel();
		panel_botones.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		panel_botones.setBounds(350, 21, 322, 52);
		panel_x.add(panel_botones);
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
		
		JPanel panel_citas = new JPanel();
		panel_primario.add(panel_citas, "panel_citas");
		panel_citas.setLayout(null);
		
		
		lbl_yCitas.setForeground(new Color(116, 27, 71));
		lbl_yCitas.setFont(new Font("Century Schoolbook", Font.PLAIN, 30));
		lbl_yCitas.setBackground(new Color(76, 17, 48));
		lbl_yCitas.setBounds(38, 25, 437, 52);
		panel_citas.add(lbl_yCitas);
		
		JButton btn_homeCitas = new JButton("Home");
		btn_homeCitas.setForeground(Color.WHITE);
		btn_homeCitas.setFont(new Font("Century Schoolbook", Font.BOLD, 15));
		btn_homeCitas.setBackground(new Color(76, 17, 48));
		btn_homeCitas.setBounds(38, 455, 221, 44);
		panel_citas.add(btn_homeCitas);
		btn_homeCitas.addActionListener(list);
		
		JButton btn_cCita = new JButton("Confirmar");
		btn_cCita.setForeground(Color.WHITE);
		btn_cCita.setFont(new Font("Century Schoolbook", Font.BOLD, 15));
		btn_cCita.setBackground(new Color(76, 17, 48));
		btn_cCita.setBounds(454, 455, 221, 44);
		panel_citas.add(btn_cCita);
		btn_cCita.addActionListener(list);
		
		JPanel panel_clientes = new JPanel();
		panel_clientes.setLayout(null);
		panel_primario.add(panel_clientes, "panel_clientes");
		
		lbl_yClientes.setForeground(new Color(116, 27, 71));
		lbl_yClientes.setFont(new Font("Century Schoolbook", Font.PLAIN, 30));
		lbl_yClientes.setBackground(new Color(76, 17, 48));
		lbl_yClientes.setBounds(38, 25, 437, 52);
		panel_clientes.add(lbl_yClientes);
		
		JButton btn_homeClientes = new JButton("Home");
		btn_homeClientes.setForeground(Color.WHITE);
		btn_homeClientes.setFont(new Font("Century Schoolbook", Font.BOLD, 15));
		btn_homeClientes.setBackground(new Color(76, 17, 48));
		btn_homeClientes.setBounds(38, 455, 221, 44);
		panel_clientes.add(btn_homeClientes);
		btn_homeClientes.addActionListener(list);
		
		JButton btn_cCliente = new JButton("Confirmar");
		btn_cCliente.setForeground(Color.WHITE);
		btn_cCliente.setFont(new Font("Century Schoolbook", Font.BOLD, 15));
		btn_cCliente.setBackground(new Color(76, 17, 48));
		btn_cCliente.setBounds(454, 455, 221, 44);
		panel_clientes.add(btn_cCliente);
		btn_cCliente.addActionListener(list);
		
		JPanel panel_talleres = new JPanel();
		panel_talleres.setLayout(null);
		panel_primario.add(panel_talleres, "panel_talleres");
		
		lbl_yTaller.setForeground(new Color(116, 27, 71));
		lbl_yTaller.setFont(new Font("Century Schoolbook", Font.PLAIN, 30));
		lbl_yTaller.setBackground(new Color(76, 17, 48));
		lbl_yTaller.setBounds(38, 25, 437, 52);
		panel_talleres.add(lbl_yTaller);
		
		JButton btn_homeTaller = new JButton("Home");
		btn_homeTaller.setForeground(Color.WHITE);
		btn_homeTaller.setFont(new Font("Century Schoolbook", Font.BOLD, 15));
		btn_homeTaller.setBackground(new Color(76, 17, 48));
		btn_homeTaller.setBounds(38, 455, 221, 44);
		panel_talleres.add(btn_homeTaller);
		btn_homeTaller.addActionListener(list);
		
		JButton btn_cTaller = new JButton("Confirmar");
		btn_cTaller.setForeground(Color.WHITE);
		btn_cTaller.setFont(new Font("Century Schoolbook", Font.BOLD, 15));
		btn_cTaller.setBackground(new Color(76, 17, 48));
		btn_cTaller.setBounds(454, 455, 221, 44);
		panel_talleres.add(btn_cTaller);
		btn_cTaller.addActionListener(list);
		
		JPanel panel_empleados = new JPanel();
		panel_empleados.setLayout(null);
		panel_primario.add(panel_empleados, "panel_empleados");
		
		lbl_yEmpleado.setForeground(new Color(116, 27, 71));
		lbl_yEmpleado.setFont(new Font("Century Schoolbook", Font.PLAIN, 30));
		lbl_yEmpleado.setBackground(new Color(76, 17, 48));
		lbl_yEmpleado.setBounds(38, 25, 437, 52);
		panel_empleados.add(lbl_yEmpleado);
		
		JButton btn_homeEmpleado = new JButton("Home");
		btn_homeEmpleado.setForeground(Color.WHITE);
		btn_homeEmpleado.setFont(new Font("Century Schoolbook", Font.BOLD, 15));
		btn_homeEmpleado.setBackground(new Color(76, 17, 48));
		btn_homeEmpleado.setBounds(38, 455, 221, 44);
		panel_empleados.add(btn_homeEmpleado);
		btn_homeEmpleado.addActionListener(list);
		
		JButton btn_cEmpleado = new JButton("Confirmar");
		btn_cEmpleado.setForeground(Color.WHITE);
		btn_cEmpleado.setFont(new Font("Century Schoolbook", Font.BOLD, 15));
		btn_cEmpleado.setBackground(new Color(76, 17, 48));
		btn_cEmpleado.setBounds(454, 455, 221, 44);
		panel_empleados.add(btn_cEmpleado);
		panel_secundario.setBackground(new Color(0, 0, 0));
		btn_cEmpleado.addActionListener(list);
		
		panel_secundario.setBorder(new LineBorder(new Color(0, 0, 0), 5));
		panel_secundario.setBounds(10, 10, 372, 545);
		getContentPane().add(panel_secundario);
		
		JPanel panel_logo = new JPanel();
		panel_secundario.add(panel_logo, "panel_logo");
		panel_logo.setLayout(null);
		
		JLabel lbl_logo = new JLabel("Aqui va el logo");
		lbl_logo.setBounds(140, 246, 128, 43);
		panel_logo.add(lbl_logo);
		
		JPanel panel_ambos = new JPanel();
		panel_ambos.setBackground(new Color(0, 0, 0));
		panel_secundario.add(panel_ambos, "panel_ambos");
		panel_ambos.setLayout(null);
		
		JPanel panel_cuenta = new JPanel();
		panel_cuenta.setBackground(new Color(76, 17, 48));
		panel_cuenta.setBounds(0, 0, 362, 127);
		panel_ambos.add(panel_cuenta);
		panel_cuenta.setLayout(null);
		
		JLabel lbl_nombreEmpleado = new JLabel("Nombre Empleado");
		lbl_nombreEmpleado.setFont(new Font("Century Schoolbook", Font.BOLD, 20));
		lbl_nombreEmpleado.setForeground(new Color(255, 255, 255));
		lbl_nombreEmpleado.setBounds(109, 29, 218, 30);
		panel_cuenta.add(lbl_nombreEmpleado);
		
		JLabel lbl_categoria = new JLabel("Categoria");
		lbl_categoria.setForeground(Color.WHITE);
		lbl_categoria.setFont(new Font("Century Schoolbook", Font.PLAIN, 20));
		lbl_categoria.setBounds(109, 69, 218, 30);
		panel_cuenta.add(lbl_categoria);
		
		JButton btn_logout = new JButton("Logout");
		btn_logout.setFont(new Font("Century Schoolbook", Font.BOLD, 10));
		btn_logout.setBackground(new Color(76, 17, 48));
		btn_logout.setForeground(new Color(255, 255, 255));
		btn_logout.setBorder(null);
		btn_logout.setBounds(289, 97, 63, 20);
		panel_cuenta.add(btn_logout);
		btn_logout.addActionListener(list);
		
		panel_nav.setBackground(new Color(116, 27, 71));
		panel_nav.setBounds(0, 125, 362, 410);
		panel_ambos.add(panel_nav);
		
		JPanel panel_maestro = new JPanel();
		panel_maestro.setBackground(new Color(116, 27, 71));
		panel_nav.add(panel_maestro, "panel_maestro");
		panel_maestro.setLayout(null);
		
		JButton btn_citas = new JButton("Citas");
		btn_citas.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		btn_citas.setFont(new Font("Century Schoolbook", Font.PLAIN, 20));
		btn_citas.setBackground(new Color(255, 255, 255));
		btn_citas.setBounds(41, 32, 280, 62);
		panel_maestro.add(btn_citas);
		btn_citas.addActionListener(list);
		
		JButton btn_clientes = new JButton("Clientes");
		btn_clientes.setFont(new Font("Century Schoolbook", Font.PLAIN, 20));
		btn_clientes.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		btn_clientes.setBackground(Color.WHITE);
		btn_clientes.setBounds(41, 126, 280, 62);
		panel_maestro.add(btn_clientes);
		btn_clientes.addActionListener(list);
		
		JButton btn_talleres = new JButton("Talleres");
		btn_talleres.setFont(new Font("Century Schoolbook", Font.PLAIN, 20));
		btn_talleres.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		btn_talleres.setBackground(Color.WHITE);
		btn_talleres.setBounds(41, 220, 280, 62);
		panel_maestro.add(btn_talleres);
		btn_talleres.addActionListener(list);
		
		JButton btn_empleados = new JButton("Empleados");
		btn_empleados.setFont(new Font("Century Schoolbook", Font.PLAIN, 20));
		btn_empleados.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		btn_empleados.setBackground(Color.WHITE);
		btn_empleados.setBounds(41, 314, 280, 62);
		panel_maestro.add(btn_empleados);
		btn_empleados.addActionListener(list);
	}
}
