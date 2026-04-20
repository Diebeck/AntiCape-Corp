package view;

import control.Listener;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class Panel_logo extends JPanel {
	
	private JLabel lblLogo;
	
	public Panel_logo(Listener list) {
		setBackground(new Color(240, 240, 240));
		setLayout(null);
		setSize(362, 410);
		
		lblLogo = new JLabel("");
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo.setIcon(Ventana.escalarImagen("/img/logo.png", 300, 300));
		lblLogo.setBounds(22, 0, 340, 310);
		add(lblLogo);

	}
	
}
