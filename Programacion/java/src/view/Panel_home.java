package view;

import control.Listener;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class Panel_home extends JPanel {
	
	private JTable tablaClientes;
	private JTable tablaTalleres;
	private JLabel lbl_home1;
	private JScrollPane scrollPane;
	private JLabel lbl_talleres;
	private JScrollPane scrollPane_1;
	private JLabel lblLogo;

	public Panel_home(Listener list) {
		setLayout(null);
		setSize(723, 545);
		
		lbl_home1 = new JLabel("Proximas Citas");
		lbl_home1.setBounds(108, 33, 373, 52);
		lbl_home1.setForeground(new Color(116, 27, 71));
		lbl_home1.setFont(new Font("Century Schoolbook", Font.ITALIC, 32));
		add(lbl_home1);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(49, 100, 625, 135);
		add(scrollPane);
		
		tablaClientes = new JTable();
		scrollPane.setViewportView(tablaClientes);
		tablaClientes.setForeground(new Color(0, 0, 0)); 
	    tablaClientes.setFont(new Font("Century Schoolbook", Font.ITALIC, 10)); 
		
		lbl_talleres = new JLabel("Ocupación Talleres");
		lbl_talleres.setForeground(new Color(116, 27, 71));
		lbl_talleres.setFont(new Font("Century Schoolbook", Font.ITALIC, 28));
		lbl_talleres.setBounds(49, 275, 373, 52);
		add(lbl_talleres);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(49, 354, 489, 128);
		add(scrollPane_1);
		
		tablaTalleres = new JTable();
		scrollPane_1.setViewportView(tablaTalleres);
		tablaTalleres.setForeground(new Color(0, 0, 0)); 
	    tablaTalleres.setFont(new Font("Century Schoolbook", Font.ITALIC, 10)); 
		
		lblLogo = new JLabel("");
		lblLogo.setIcon(Ventana.escalarImagen("/img/logo.png", 50, 50));
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo.setBounds(36, 25, 74, 65);
		add(lblLogo);
	}

	public JTable getTablaClientes() {
		return tablaClientes;
	}

	public JTable getTablaTalleres() {
		return tablaTalleres;
	}

	public JLabel getLbl_home1() {
		return lbl_home1;
	}

	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	public JLabel getLbl_talleres() {
		return lbl_talleres;
	}

	public JScrollPane getScrollPane_1() {
		return scrollPane_1;
	}

	public JLabel getLblLogo() {
		return lblLogo;
	}
	
}
