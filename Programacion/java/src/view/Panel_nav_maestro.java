/**
 * @author Breixo García Canovacas
 * @author Robinson Tamayo Guerrero
 * @author Romeo Rey Alonso
 * @author Sara Cardeña Carpio 
 */
package view;

import control.Listener;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 * Clase que modela el panel de navegacion del maestro 
 */
@SuppressWarnings("serial")
public class Panel_nav_maestro extends JPanel {
	
	private int iconSize = 20;

	private JButton btn_citas;
	private JButton btn_clientes;
	private JButton btn_talleres;
	private JButton btn_empleados;
	
	public Panel_nav_maestro(Listener list) {
		setBackground(new Color(116, 27, 71));
		setLayout(null);
		setSize(362, 410);
		
		btn_citas = new JButton("Citas");
		btn_citas.setIcon(Ventana.escalarImagen("/img/calendario.png", iconSize, iconSize));
		btn_citas.setFont(new Font("Century Schoolbook", Font.PLAIN, 20));
		btn_citas.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		btn_citas.setBackground(new Color(255, 255, 255));
		btn_citas.setBounds(41, 32, 280, 62);
		add(btn_citas);
		btn_citas.addActionListener(list);
		
		btn_clientes = new JButton("Clientes");
		btn_clientes.setIcon(Ventana.escalarImagen("/img/clientes.png", iconSize, iconSize));
		btn_clientes.setFont(new Font("Century Schoolbook", Font.PLAIN, 20));
		btn_clientes.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		btn_clientes.setBackground(Color.WHITE);
		btn_clientes.setBounds(41, 126, 280, 62);
		add(btn_clientes);
		btn_clientes.addActionListener(list);
		
		btn_talleres = new JButton("Talleres");
		btn_talleres.setIcon(Ventana.escalarImagen("/img/talleres.png", iconSize, iconSize));
		btn_talleres.setFont(new Font("Century Schoolbook", Font.PLAIN, 20));
		btn_talleres.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		btn_talleres.setBackground(Color.WHITE);
		btn_talleres.setBounds(41, 220, 280, 62);
		add(btn_talleres);
		btn_talleres.addActionListener(list);
		
		btn_empleados = new JButton("Empleados");
		btn_empleados.setIcon(Ventana.escalarImagen("/img/empleados.png", iconSize, iconSize));
		btn_empleados.setFont(new Font("Century Schoolbook", Font.PLAIN, 20));
		btn_empleados.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		btn_empleados.setBackground(Color.WHITE);
		btn_empleados.setBounds(41, 314, 280, 62);
		add(btn_empleados);
		btn_empleados.addActionListener(list);
	}
}
