package view;

import control.Listener;

import java.awt.Color;
import java.awt.Font;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

/**
 * Librerias externas de GitHub utilizadas para el calendario y la hora
 */
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.components.TimePicker;
import com.github.lgooddatepicker.components.TimePickerSettings;
import com.github.lgooddatepicker.optionalusertools.TimeVetoPolicy;

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
	private DatePicker dpFecha;
	private TimePicker tpHora;
	
	
	// El modo determina que accion se va a hacer (crear / modificar)
	String modo = "Crear";
	private JLabel lblHora;
	
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
		lblCliente.setBounds(38, 275, 90, 30);
		add(lblCliente);
		
		cbCliente = new JComboBox();
		cbCliente.setFont(new Font("Century Schoolbook", Font.PLAIN, 14));
		cbCliente.setBounds(120, 276, 238, 30);
		add(cbCliente);
		
		btnNuevoCliente = new JButton("Nuevo Cliente");
		btnNuevoCliente.setIcon(Ventana.escalarImagen("/img/add.png", 18, 18));
		btnNuevoCliente.setFont(new Font("Century Schoolbook", Font.PLAIN, 13));
		btnNuevoCliente.setForeground(Color.WHITE);
		btnNuevoCliente.setBackground(new Color(78, 17, 48));
		btnNuevoCliente.setBounds(385, 276, 189, 30);
		add(btnNuevoCliente);
		btnNuevoCliente.addActionListener(list);
		

		lblTraje = new JLabel("Traje:");
		lblTraje.setFont(new Font("Century Schoolbook", Font.PLAIN, 18));
		lblTraje.setBounds(38, 318, 90, 30);
		add(lblTraje);
		
		cbTrajes = new JComboBox();
		cbTrajes.setFont(new Font("Century Schoolbook", Font.PLAIN, 14));
		cbTrajes.setBounds(120, 319, 238, 30);
		add(cbTrajes);
		
		btnNuevoTraje = new JButton("Nuevo Traje");
		btnNuevoTraje.setIcon(Ventana.escalarImagen("/img/add.png", 18, 18));
		btnNuevoTraje.setForeground(Color.WHITE);
		btnNuevoTraje.setFont(new Font("Century Schoolbook", Font.PLAIN, 13));
		btnNuevoTraje.setBackground(new Color(78, 17, 48));
		btnNuevoTraje.setBounds(385, 320, 189, 30);
		btnNuevoTraje.addActionListener(list);
		add(btnNuevoTraje);
		
		lblTaller = new JLabel("Taller:");
		lblTaller.setFont(new Font("Century Schoolbook", Font.PLAIN, 18));
		lblTaller.setBounds(38, 370, 90, 30);
		add(lblTaller);
		
		cbTaller = new JComboBox();
		cbTaller.setFont(new Font("Century Schoolbook", Font.PLAIN, 14));
		cbTaller.setBounds(120, 371, 238, 30);
		add(cbTaller);
		
		lblEncargado = new JLabel("Encargado:");
		lblEncargado.setFont(new Font("Century Schoolbook", Font.PLAIN, 18));
		lblEncargado.setBounds(38, 99, 100, 30);
		add(lblEncargado);
		
		cbEncargado = new JComboBox();
		cbEncargado.setFont(new Font("Century Schoolbook", Font.PLAIN, 14));
		cbEncargado.setBounds(168, 100, 190, 30);
		add(cbEncargado);
		
		lblAyudante1 = new JLabel("Asistente #1:");
		lblAyudante1.setFont(new Font("Century Schoolbook", Font.PLAIN, 18));
		lblAyudante1.setBounds(38, 140, 120, 30);
		add(lblAyudante1);
		
		cbAyudante1 = new JComboBox();
		cbAyudante1.setFont(new Font("Century Schoolbook", Font.PLAIN, 14));
		cbAyudante1.setBounds(168, 140, 190, 30);
		add(cbAyudante1);
		
		lblAyudante2 = new JLabel("Asistente #2:");
		lblAyudante2.setFont(new Font("Century Schoolbook", Font.PLAIN, 18));
		lblAyudante2.setBounds(38, 185, 120, 30);
		add(lblAyudante2);
		
		cbAyudante2 = new JComboBox();
		cbAyudante2.setFont(new Font("Century Schoolbook", Font.PLAIN, 14));
		cbAyudante2.setBounds(168, 186, 190, 30);
		add(cbAyudante2);
		
		
		// Fecha
		lblFecha = new JLabel("Fecha:");
		lblFecha.setFont(new Font("Century Schoolbook", Font.PLAIN, 18));
		lblFecha.setBounds(385, 99, 80, 30);
		add(lblFecha);
		
		// import de la configuracion del datepicker
		DatePickerSettings dateSettings = new DatePickerSettings();
		
		
		dpFecha = new DatePicker(dateSettings);
		dpFecha.getComponentToggleCalendarButton().setVisible(true);
		dpFecha.getComponentToggleCalendarButton().setBackground(new Color(78, 17, 48));
		dpFecha.getComponentToggleCalendarButton().setForeground(Color.white);
		dpFecha.getComponentDateTextField().setFont(new Font("Tahoma", Font.PLAIN, 14));
		dpFecha.setBounds(475, 100, 205, 32);
		add(dpFecha);
		
		// formato del rango de fechas (del sysdate en adelante)
		dateSettings.setDateRangeLimits(LocalDate.now(), null);
		//fecha en el formato sql
		dateSettings.setFormatForDatesCommonEra("yyyy-MM-dd");
		
		// Hora
		lblHora = new JLabel("Hora:");
		lblHora.setFont(new Font("Century Schoolbook", Font.PLAIN, 18));
		lblHora.setBounds(385, 140, 80, 30);
		add(lblHora);
		
		// formato de hora minima y maxima 
		TimePickerSettings ajustes = new TimePickerSettings();
		
		tpHora = new TimePicker(ajustes);
		tpHora.getComponentTimeTextField().setFont(new Font("Tahoma", Font.PLAIN, 14));
		tpHora.getComponentToggleTimeMenuButton().setBackground(new Color(78, 17, 48));
		tpHora.getComponentToggleTimeMenuButton().setForeground(Color.white);
		tpHora.setBounds(475, 141, 120, 32);
		add(tpHora);
		
	
		// Implementacion de TimeVetoPolicy para limitar horas mínimas y máximas
		ajustes.setVetoPolicy(new TimeVetoPolicy() {
		    @Override
		    public boolean isTimeAllowed(LocalTime time) {
		        // Hora mínima: 09:00, Hora máxima: 20:00
		        LocalTime minTime = LocalTime.of(9, 0);
		        LocalTime maxTime = LocalTime.of(20, 0);
		        
		        // No permitir horas fuera del rango
		        return !time.isBefore(minTime) && !time.isAfter(maxTime);
		    }
		});
		
		// Duración
		lblDuracion = new JLabel("Duración:");
		lblDuracion.setFont(new Font("Century Schoolbook", Font.PLAIN, 18));
		lblDuracion.setBounds(385, 185, 100, 30);
		add(lblDuracion);
		
		spDuracion = new JSpinner();
		spDuracion.setFont(new Font("Century Schoolbook", Font.PLAIN, 16));
		spDuracion.setModel(new SpinnerNumberModel(1, 1, 5, 1));
		spDuracion.setBounds(476, 185, 98, 32);
		add(spDuracion);
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

	public DatePicker getDpFecha() {
		return dpFecha;
	}

	public TimePicker getTpHora() {
		return tpHora;
	}
	
	@SuppressWarnings("rawtypes")
	public JComboBox getCbAyudante1() {
		return cbAyudante1;
	}
	
	@SuppressWarnings("rawtypes")
	public JComboBox getCbAyudante2() {
		return cbAyudante2;
	}
}