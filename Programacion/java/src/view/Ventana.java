/**
 * @author Breixo García Canovacas
 * @author Robinson Tamayo Guerrero
 * @author Romeo Rey Alonso
 * @author Sara Cardeña Carpio 
 */
package view;

import control.Listener;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.JScrollPane;

/**
 * Clase que modela la estructura general del programa 
 */
@SuppressWarnings("serial")
public class Ventana extends JFrame {

	Listener list;
	
	private JPanel panel_secundario;
	private JScrollPane caja_primario = new JScrollPane();
	private JScrollPane caja_nav = new JScrollPane();
	private JScrollPane caja_cuenta = new JScrollPane();
	
	// Constructor
	public Ventana(Listener list) {
		setResizable(false);
		this.list = list;
		iniciar();
	}

	/** Cambia el panel del apartado primario (grande a la derecha)
	 * @param panel
	 */
	public void cambiarCajaPrimario(JPanel panel) {
		caja_primario.setViewportView(panel);
	}
	/** Cambia el panel del apartado de navegacion (pequeño abajo a la izquierda)
	 * @param panel
	 */
	public void cambiarCajaNav(JPanel panel) {
		caja_nav.setViewportView(panel);
	}
	/** Cambia el panel del apartado de cuenta (pequeño arriba a la izquierda)
	 * @param panel
	 */
	public void cambiarCajaCuenta(JPanel panel) {
		caja_cuenta.setViewportView(panel);
	}
	
	private void iniciar() {
		setSize(1153, 604);
		setLocationRelativeTo(null); // Situa la ventana en el medio de la pantalla
		setTitle("Anticape Software"); // Pone el titulo de la ventana
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/logo.png"))); // Le pone el icono
		
		getContentPane().setBackground(new Color(192, 192, 192));
		getContentPane().setLayout(null);
		
		caja_primario.setBorder(new LineBorder(new Color(0, 0, 0), 5));
		caja_primario.setBounds(406, 10, 723, 545);
		getContentPane().add(caja_primario);

		panel_secundario = new JPanel();
		panel_secundario.setBackground(new Color(0, 0, 0));
		panel_secundario.setBorder(new LineBorder(new Color(0, 0, 0), 5));
		panel_secundario.setBounds(10, 10, 372, 545);
		panel_secundario.setLayout(null);
		getContentPane().add(panel_secundario);
		panel_secundario.add(caja_nav);
		panel_secundario.add(caja_cuenta);
		
		caja_nav.setBorder(null);
		caja_nav.setBounds(5, 130, 362, 410);
		caja_nav.setBackground(new Color(116, 27, 71));
				
		caja_cuenta.setBorder(null);
		caja_cuenta.setBounds(5, 5, 362, 127);
		
	}
	
	/** Devuelve una imagen reescalada
	 * @param link El link a la imagen
	 * @param ancho El ancho de la imagen en pixeles
	 * @param alto El alto de la imagen en pixeles
	 * @return La imagen reescalada
	 */
	public static ImageIcon escalarImagen(String link, int ancho, int alto) {
		ImageIcon icono = new ImageIcon(Panel_cuenta.class.getResource(link));
		Image imagen = icono.getImage();
		Image imagenEscalada = imagen.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
		return new ImageIcon(imagenEscalada);
	}
}
