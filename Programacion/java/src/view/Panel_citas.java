package view;

import control.Listener;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpinnerDateModel;

import java.util.Date;
import java.util.Calendar;

@SuppressWarnings("serial")
public class Panel_citas extends JPanel {
	
	private JButton btn_homeCitas;
	private JButton btn_cCita;
	private JLabel lblCliente;
	@SuppressWarnings("rawtypes")
	private JComboBox cbCliente;
	private JLabel lblTaller;
	@SuppressWarnings("rawtypes")
	private JComboBox cbTaller;
	private JLabel lblDuracion;
	private JSpinner spDuracion;
	private JLabel lblFecha;
	private JSpinner spFecha;
	private JLabel lblTraje;
	private ButtonGroup bg;
	private JLabel lblEncargado;
	@SuppressWarnings("rawtypes")
	private JComboBox cbEncargado;
	@SuppressWarnings("rawtypes")
	private JComboBox cbTrajes;
	private JLabel lbl_yCitas;
	private JButton btnNuevoCliente;
	private JButton btnNuevoTraje;
	private JLabel lblAyudante1;
	private JLabel lblAyudante2;
	@SuppressWarnings("rawtypes")
	private JComboBox cbAyudante1;
	@SuppressWarnings("rawtypes")
	private JComboBox cbAyudante2;
	
	// El modo determina que accion se va a hacer (crear / modificar)
	String modo = "Crear";
	
	/** Cambia el modo del panel y actualiza su label
	 * @param modo
	 */
	public void setModo(String modo) {
		this.modo = modo;
		lbl_yCitas.setText(modo +" una cita");
		System.out.println("Cambiado el modo del panel citas a "+ modo);
	}
	
	/** Devuelve el modo del panel
	 * @return modo
	 */
	public String getModo() {
		return modo;
	}
	
	@SuppressWarnings("rawtypes")
	public Panel_citas(Listener list) {
		setLayout(null);
		setSize(723, 545);
		
		lbl_yCitas = new JLabel(modo +" una cita");
		lbl_yCitas.setForeground(new Color(116, 27, 71));
		lbl_yCitas.setFont(new Font("Century Schoolbook", Font.PLAIN, 30));
		lbl_yCitas.setBackground(new Color(76, 17, 48));
		lbl_yCitas.setBounds(38, 25, 437, 52);
		add(lbl_yCitas);
		
		btn_homeCitas = new JButton("");
		btn_homeCitas.setIcon(new ImageIcon(Panel_citas.class.getResource("/img/home.png")));
		btn_homeCitas.setForeground(Color.WHITE);
		btn_homeCitas.setFont(new Font("Century Schoolbook", Font.BOLD, 15));
		btn_homeCitas.setBackground(new Color(76, 17, 48));
		btn_homeCitas.setBounds(38, 455, 221, 44);
		add(btn_homeCitas);
		btn_homeCitas.addActionListener(list);
		
		btn_cCita = new JButton("Confirmar");
		btn_cCita.setForeground(Color.WHITE);
		btn_cCita.setFont(new Font("Century Schoolbook", Font.BOLD, 15));
		btn_cCita.setBackground(new Color(76, 17, 48));
		btn_cCita.setBounds(454, 455, 221, 44);
		add(btn_cCita);
		btn_cCita.addActionListener(list);
		
		lblCliente = new JLabel("Cliente:");
		lblCliente.setFont(new Font("Century Schoolbook", Font.PLAIN, 18));
		lblCliente.setBounds(38, 120, 87, 20);
		add(lblCliente);
		
		cbCliente = new JComboBox();
		cbCliente.setFont(new Font("Century Schoolbook", Font.PLAIN, 15));
		cbCliente.setBounds(126, 120, 290, 25);
		add(cbCliente);
		
		lblTaller = new JLabel("Taller:");
		lblTaller.setFont(new Font("Century Schoolbook", Font.PLAIN, 18));
		lblTaller.setBounds(38, 155, 87, 20);
		add(lblTaller);
		
		cbTaller = new JComboBox();
		cbTaller.setFont(new Font("Century Schoolbook", Font.PLAIN, 15));
		cbTaller.setBounds(126, 155, 290, 25);
		add(cbTaller);
		
		lblDuracion = new JLabel("Duración:");
		lblDuracion.setFont(new Font("Century Schoolbook", Font.PLAIN, 18));
		lblDuracion.setBounds(454, 155, 87, 20);
		add(lblDuracion);
		
		spDuracion = new JSpinner();
		spDuracion.setFont(new Font("Century Schoolbook", Font.PLAIN, 15));
		spDuracion.setModel(new SpinnerNumberModel(1, 1, 5, 1));
		spDuracion.setBounds(551, 155, 124, 25);
		add(spDuracion);
		
		lblFecha = new JLabel("Fecha:");
		lblFecha.setFont(new Font("Century Schoolbook", Font.PLAIN, 18));
		lblFecha.setBounds(38, 189, 87, 20);
		add(lblFecha);
		
		spFecha = new JSpinner();
		spFecha.setFont(new Font("Century Schoolbook", Font.PLAIN, 15));
		spFecha.setModel(new SpinnerDateModel(new Date(1776117600000L), null, null, Calendar.AM_PM));
		spFecha.setBounds(126, 189, 549, 25);
		add(spFecha);
		
		lblTraje = new JLabel("Traje:");
		lblTraje.setFont(new Font("Century Schoolbook", Font.PLAIN, 18));
		lblTraje.setBounds(38, 225, 87, 20);
		add(lblTraje);
		
		bg = new ButtonGroup();
		
		lblEncargado = new JLabel("Encargado:");
		lblEncargado.setFont(new Font("Century Schoolbook", Font.PLAIN, 18));
		lblEncargado.setBounds(38, 300, 107, 20);
		add(lblEncargado);
		
		cbEncargado = new JComboBox();
		cbEncargado.setFont(new Font("Century Schoolbook", Font.PLAIN, 15));
		cbEncargado.setBounds(175, 299, 500, 25);
		add(cbEncargado);
		
		btnNuevoCliente = new JButton("Nuevo Cliente");
		btnNuevoCliente.setIcon(Ventana.escalarImagen("/img/add.png", 20, 20));
		btnNuevoCliente.setFont(new Font("Century Schoolbook", Font.PLAIN, 15));
		btnNuevoCliente.setForeground(new Color(255, 255, 255));
		btnNuevoCliente.setBackground(new Color(78, 17, 48));
		btnNuevoCliente.setBounds(454, 120, 221, 25);
		add(btnNuevoCliente);
		btnNuevoCliente.addActionListener(list);

		cbTrajes = new JComboBox();
		cbTrajes.setFont(new Font("Century Schoolbook", Font.PLAIN, 15));
		cbTrajes.setBounds(126, 224, 290, 25);
		add(cbTrajes);
		
		btnNuevoTraje = new JButton("Nuevo Traje");
		btnNuevoTraje.setForeground(Color.WHITE);
		btnNuevoTraje.setIcon(Ventana.escalarImagen("/img/add.png", 20, 20));
		btnNuevoTraje.setFont(new Font("Century Schoolbook", Font.PLAIN, 15));
		btnNuevoTraje.setBackground(new Color(78, 17, 48));
		btnNuevoTraje.setBounds(454, 224, 221, 25);
		btnNuevoTraje.addActionListener(list);
		add(btnNuevoTraje);
		
		lblAyudante1 = new JLabel("Ayudante #1:");
		lblAyudante1.setFont(new Font("Century Schoolbook", Font.PLAIN, 18));
		lblAyudante1.setBounds(38, 344, 134, 20);
		add(lblAyudante1);
		
		lblAyudante2 = new JLabel("Ayudante #2:");
		lblAyudante2.setFont(new Font("Century Schoolbook", Font.PLAIN, 18));
		lblAyudante2.setBounds(38, 387, 134, 20);
		add(lblAyudante2);
		
		cbAyudante1 = new JComboBox();
		cbAyudante1.setFont(new Font("Century Schoolbook", Font.PLAIN, 15));
		cbAyudante1.setBounds(175, 342, 500, 25);
		add(cbAyudante1);
		
		cbAyudante2 = new JComboBox();
		cbAyudante2.setFont(new Font("Century Schoolbook", Font.PLAIN, 15));
		cbAyudante2.setBounds(175, 384, 500, 25);
		add(cbAyudante2);
				
	}

	/**
	 * @return the btn_homeCitas
	 */
	public JButton getBtn_homeCitas() {
		return btn_homeCitas;
	}


	public JButton getBtn_cCita() {
		return btn_cCita;
	}

	public JLabel getLblCliente() {
		return lblCliente;
	}

	@SuppressWarnings("rawtypes")
	public JComboBox getCbCliente() {
		return cbCliente;
	}

	public JLabel getLblTaller() {
		return lblTaller;
	}

	@SuppressWarnings("rawtypes")
	public JComboBox getCbTaller() {
		return cbTaller;
	}

	public JLabel getLblDuracion() {
		return lblDuracion;
	}

	public JSpinner getSpDuracion() {
		return spDuracion;
	}

	public JLabel getLblFecha() {
		return lblFecha;
	}

	public JSpinner getSpFecha() {
		return spFecha;
	}

	public JLabel getLblTraje() {
		return lblTraje;
	}

	public ButtonGroup getBg() {
		return bg;
	}

	public JLabel getLblEncargado() {
		return lblEncargado;
	}

	@SuppressWarnings("rawtypes")
	public JComboBox getCbEncargado() {
		return cbEncargado;
	}

	public JLabel getLbl_yCitas() {
		return lbl_yCitas;
	}

	@SuppressWarnings("rawtypes")
	public JComboBox getCbTrajes() {
		return cbTrajes;
	}
}
