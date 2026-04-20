package view;

import control.Listener;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class Panel_nav_aprendiz extends JPanel {

	private JLabel lblLogoPanel;
	
	public Panel_nav_aprendiz(Listener list) {
		setBackground(new Color(116, 27, 71));
		setLayout(null);
		setSize(362, 410);
		
		lblLogoPanel = new JLabel("");
		lblLogoPanel.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogoPanel.setIcon(new ImageIcon(Panel_nav_aprendiz.class.getResource("/img/logo.png")));
		lblLogoPanel.setBounds(-16, 48, 446, 456);
		add(lblLogoPanel);
	}
}
