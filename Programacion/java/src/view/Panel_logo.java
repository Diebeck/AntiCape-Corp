package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import control.Listener;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Panel_logo extends JPanel {

	public Panel_logo(Listener list) {
		setBackground(new Color(240, 240, 240));
		setLayout(null);
		setSize(362, 410);
		
		JLabel logo = new JLabel("Poner aquí el logo");
		logo.setHorizontalAlignment(SwingConstants.CENTER);
		logo.setBounds(127, 102, 108, 32);
		add(logo);
	}
}
