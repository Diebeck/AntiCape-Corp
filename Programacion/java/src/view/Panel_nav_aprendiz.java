package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import control.Listener;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class Panel_nav_aprendiz extends JPanel {

	public Panel_nav_aprendiz(Listener list) {
		setBackground(new Color(116, 27, 71));
		setLayout(null);
		setSize(362, 410);
		
		JLabel lblLogoPanel = new JLabel("");
		lblLogoPanel.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogoPanel.setIcon(new ImageIcon(Panel_nav_aprendiz.class.getResource("/img/logo.png")));
		lblLogoPanel.setBounds(-16, 48, 446, 456);
		add(lblLogoPanel);
	}
}
