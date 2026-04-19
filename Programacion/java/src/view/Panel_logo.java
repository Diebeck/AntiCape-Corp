package view;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.SwingConstants;

import control.Listener;
import javax.swing.JLabel;

public class Panel_logo extends JPanel {
	
	public Panel_logo(Listener list) {
		
		setBackground(new Color(240, 240, 240));
		setLayout(null);
		setSize(362, 410);
		
		
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo.setIcon(Ventana.escalarImagen("/img/logo.png", 300, 300));
		lblLogo.setBounds(22, 0, 340, 310);
		add(lblLogo);

	}
	
}
