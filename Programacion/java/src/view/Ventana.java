package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.border.BevelBorder;
import javax.swing.SwingConstants;
import java.awt.CardLayout;
import javax.swing.border.EtchedBorder;
import java.awt.Label;
import javax.swing.border.SoftBevelBorder;

import control.Listener;

import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JMenuBar;

@SuppressWarnings("serial")
public class Ventana extends JFrame {
	private Listener list;
	
	private JTable table;
	private JTextField txtUsuario;
	private JPasswordField passwordField;
	private JTable table_1;
	
	private JPanel panel_nav = new JPanel();
	private CardLayout nav_cl = new CardLayout(0, 0);
	private JPanel panel_primario = new JPanel();
	private CardLayout primario_cl = new CardLayout(0, 0);
	private JTable table_2;
	
	private String apartado = "citas";
	
	public Ventana(Listener list) {
		this.list = list;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(new Color(192, 192, 192));
		setResizable(false);
		iniciar();
	}
	
	public void cambiarPanelPrimario(String carta) {
		primario_cl.show(panel_primario, carta);
		System.out.println("Cambiado a "+ carta);
	}
	
	private void iniciar() {
		setSize(1000, 601);
		setTitle("AntiCape Corp");
		getContentPane().setLayout(null);
		
		JPanel panel_cuenta = new JPanel();
		panel_cuenta.setBackground(new Color(75, 18, 48));
		panel_cuenta.setBounds(10, 10, 290, 152);
		panel_cuenta.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		getContentPane().add(panel_cuenta);
		panel_cuenta.setLayout(null);
		
		Label label_usuario = new Label("Nombre y apellidos");
		label_usuario.setForeground(new Color(255, 255, 255));
		label_usuario.setBackground(new Color(75, 18, 48));
		label_usuario.setFont(new Font("Century Schoolbook", Font.BOLD | Font.ITALIC, 15));
		label_usuario.setBounds(93, 37, 187, 35);
		panel_cuenta.add(label_usuario);
		
		Label label_categoria = new Label("Categoria");
		label_categoria.setForeground(Color.WHITE);
		label_categoria.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
		label_categoria.setBackground(new Color(75, 18, 48));
		label_categoria.setBounds(93, 81, 187, 35);
		panel_cuenta.add(label_categoria);
		
		
		panel_nav.setBackground(new Color(113, 26, 73));
		panel_nav.setBounds(10, 172, 290, 385);
		panel_nav.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		getContentPane().add(panel_nav);
		panel_nav.setLayout(nav_cl);
		
		JPanel nav_maestro = new JPanel();
		nav_maestro.setBackground(new Color(113, 26, 73));
		panel_nav.add(nav_maestro, "nav_maestro");
		nav_maestro.setLayout(null);
		
		JButton btn_clientes = new JButton("Clientes");
		btn_clientes.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		btn_clientes.setFont(new Font("Century Schoolbook", Font.PLAIN, 18));
		btn_clientes.setBounds(20, 120, 243, 50);
		nav_maestro.add(btn_clientes);
		
		JButton btn_citas = new JButton("Citas");
		btn_citas.setFont(new Font("Century Schoolbook", Font.PLAIN, 18));
		btn_citas.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		btn_citas.setBounds(20, 35, 243, 50);
		nav_maestro.add(btn_citas);
		
		JButton btn_empleados = new JButton("Empleados");
		btn_empleados.setFont(new Font("Century Schoolbook", Font.PLAIN, 18));
		btn_empleados.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		btn_empleados.setBounds(20, 205, 243, 50);
		nav_maestro.add(btn_empleados);
		
		JButton btn_talleres = new JButton("Talleres");
		btn_talleres.setFont(new Font("Century Schoolbook", Font.PLAIN, 18));
		btn_talleres.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		btn_talleres.setBounds(20, 290, 243, 50);
		nav_maestro.add(btn_talleres);
		
		JPanel nav_oficial = new JPanel();
		nav_oficial.setBackground(new Color(113, 26, 73));
		panel_nav.add(nav_oficial, "nav_oficial");
		nav_oficial.setLayout(null);
		
		JButton ofi_modificar = new JButton("Modificar");
		ofi_modificar.setFont(new Font("Century Schoolbook", Font.PLAIN, 18));
		ofi_modificar.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		ofi_modificar.setBounds(20, 166, 243, 50);
		nav_oficial.add(ofi_modificar);
		
		JButton ofi_crear = new JButton("Crear");
		ofi_crear.setFont(new Font("Century Schoolbook", Font.PLAIN, 18));
		ofi_crear.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		ofi_crear.setBounds(20, 81, 243, 50);
		nav_oficial.add(ofi_crear);
		
		JButton ofi_borrar = new JButton("Borrar");
		ofi_borrar.setFont(new Font("Century Schoolbook", Font.PLAIN, 18));
		ofi_borrar.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		ofi_borrar.setBounds(20, 251, 243, 50);
		nav_oficial.add(ofi_borrar);
		
		JPanel nav_aprendiz = new JPanel();
		nav_aprendiz.setBackground(new Color(113, 26, 73));
		panel_nav.add(nav_aprendiz, "nav_aprendiz");
		nav_aprendiz.setLayout(null);
		
		JLabel lbl_proxcitas = new JLabel("Próximas citas");
		lbl_proxcitas.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_proxcitas.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl_proxcitas.setForeground(new Color(255, 255, 255));
		lbl_proxcitas.setBounds(64, 10, 155, 31);
		nav_aprendiz.add(lbl_proxcitas);
		
		table = new JTable();
		table.setBounds(26, 51, 232, 299);
		nav_aprendiz.add(table);
		
		JPanel nav_logo = new JPanel();
		panel_nav.add(nav_logo, "nav_logo");
		
		panel_primario.setBackground(new Color(255, 255, 255));
		panel_primario.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_primario.setBounds(310, 10, 666, 547);
		getContentPane().add(panel_primario);
		panel_primario.setLayout(primario_cl);
		
		JPanel prim_login = new JPanel();
		panel_primario.add(prim_login, "prim_login");
		prim_login.setLayout(null);
		
		JLabel lbl_login = new JLabel("Employee Login");
		lbl_login.setFont(new Font("Century Schoolbook", Font.BOLD | Font.ITALIC, 30));
		lbl_login.setForeground(new Color(113, 26, 73));
		lbl_login.setBounds(29, 29, 486, 69);
		prim_login.add(lbl_login);
		
		txtUsuario = new JTextField();
		txtUsuario.setFont(new Font("Century Schoolbook", Font.PLAIN, 18));
		txtUsuario.setToolTipText("Usuario");
		txtUsuario.setBounds(29, 137, 331, 29);
		prim_login.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setToolTipText("Contraseña");
		passwordField.setFont(new Font("Century Schoolbook", Font.PLAIN, 18));
		passwordField.setBounds(29, 198, 331, 29);
		prim_login.add(passwordField);
		
		JLabel log_txtu = new JLabel("Usuario");
		log_txtu.setFont(new Font("Century Schoolbook", Font.PLAIN, 12));
		log_txtu.setBounds(29, 115, 331, 12);
		prim_login.add(log_txtu);
		
		JLabel log_txtc = new JLabel("Contraseña");
		log_txtc.setFont(new Font("Century Schoolbook", Font.PLAIN, 12));
		log_txtc.setBounds(29, 176, 331, 12);
		prim_login.add(log_txtc);
		
		JButton log_login = new JButton("Login");
		log_login.setBackground(new Color(113, 26, 73));
		log_login.setForeground(new Color(255, 255, 255));
		log_login.setFont(new Font("Century Schoolbook", Font.PLAIN, 15));
		log_login.setBounds(25, 291, 120, 29);
		prim_login.add(log_login);
		log_login.addActionListener(list);
		
		JPanel prim_interaccion = new JPanel();
		panel_primario.add(prim_interaccion, "prim_interaccion");
		prim_interaccion.setLayout(null);
		
		JLabel inter_gdx = new JLabel("Gestor de "+ apartado);
		inter_gdx.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		inter_gdx.setHorizontalAlignment(SwingConstants.CENTER);
		inter_gdx.setBackground(new Color(255, 255, 255));
		inter_gdx.setForeground(new Color(113, 26, 73));
		inter_gdx.setFont(new Font("Century Schoolbook", Font.BOLD, 20));
		inter_gdx.setBounds(22, 22, 251, 44);
		prim_interaccion.add(inter_gdx);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(50, 101, 560, 397);
		prim_interaccion.add(scrollPane);
		
		table_1 = new JTable();
		scrollPane.setColumnHeaderView(table_1);
		
		JButton inter_eliminar = new JButton("Eliminar");
		inter_eliminar.setEnabled(false);
		inter_eliminar.setFont(new Font("Century Schoolbook", Font.PLAIN, 16));
		inter_eliminar.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		inter_eliminar.setBounds(513, 22, 97, 29);
		prim_interaccion.add(inter_eliminar);
		
		JButton inter_modificar = new JButton("Modificar");
		inter_modificar.setEnabled(false);
		inter_modificar.setFont(new Font("Century Schoolbook", Font.PLAIN, 16));
		inter_modificar.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		inter_modificar.setBounds(406, 22, 97, 29);
		prim_interaccion.add(inter_modificar);
		
		JButton inter_crear = new JButton("Crear");
		inter_crear.setFont(new Font("Century Schoolbook", Font.PLAIN, 16));
		inter_crear.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		inter_crear.setBounds(299, 22, 97, 29);
		prim_interaccion.add(inter_crear);
		
		JPanel prim_solocitas = new JPanel();
		panel_primario.add(prim_solocitas, "prim_solocitas");
		prim_solocitas.setLayout(null);
		
		JLabel inter_gdx_1 = new JLabel("Gestor de citas");
		inter_gdx_1.setBounds(231, 5, 198, 46);
		inter_gdx_1.setHorizontalAlignment(SwingConstants.CENTER);
		inter_gdx_1.setForeground(new Color(113, 26, 73));
		inter_gdx_1.setFont(new Font("Century Schoolbook", Font.BOLD, 20));
		inter_gdx_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		inter_gdx_1.setBackground(Color.WHITE);
		prim_solocitas.add(inter_gdx_1);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(52, 90, 555, 407);
		prim_solocitas.add(scrollPane_2);
		
		table_2 = new JTable();
		scrollPane_2.setViewportView(table_2);
		
		
	}
}
